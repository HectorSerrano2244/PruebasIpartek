package com.ipartek.formacion.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Agente;

public class AgenteDAO {
	private static AgenteDAO INSTANCE = null;
	private static final String SQL_GETBYUSER = "SELECT v.id as 'id_video', u.id as 'id_usuario', nombre, codigo, email, `password` FROM video as v, usuario as u WHERE v.id_usuario = u.id AND v.id_usuario = ? ORDER BY v.id DESC LIMIT 5;";
	// Constructor privado, solo acceso por getInstance()
	private AgenteDAO(){
		super();
	}
	
	public synchronized static AgenteDAO getInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new AgenteDAO();
        }
        return INSTANCE;
    }
	
	/**
	 * comprobar si existe el usuario en la bbdd
	 * 
	 * @param email
	 *            String
	 * @param pass
	 *            String pass
	 * @return usuario con datos si existe, null si no existe
	 */
	public Agente login(String email, String password) {

		Agente agente = null;
		String sql = "SELECT id, email, password FROM usuario WHERE email = ? AND password = ?;";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, email);
			pst.setString(2, password);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) { // hemos encontrado usuario
					agente = new Agente();
					agente.setId(rs.getLong("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agente;
	}

	public ArrayList<Agente> getAll() {

		ArrayList<Agente> agentes = new ArrayList<Agente>();
		String sql = "SELECT id, email, password FROM usuario ORDER BY id DESC LIMIT 100";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				try {
					Agente agente = new Agente();
					agente.setId(rs.getLong("id"));
					// insertar en array
					agentes.add(agente);
				} catch (Exception e) {
					System.out.println("usuario no valido");
					e.printStackTrace();
				}
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentes;
	}
	
	public Agente getByUser(Agente a) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYUSER);) {
			pst.setLong(1, a.getId());

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					a = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
//	public boolean insert(Usuario u) throws SQLException {
//		boolean r = false;
//		String sql = "INSERT INTO `usuario` (`email`, `password`) VALUES (?, ?);";
//		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
//			pst.setString(1, u.getEmail());
//			pst.setString(2, u.getPassword());
//			int affectedRows = pst.executeUpdate();
//			if (affectedRows == 1) {
//				r = true;
//			}
//		}
//		return r;
//	}
//	
//	public boolean update(Usuario u) throws SQLException {
//		boolean r = false;
//		String sql = "UPDATE usuario SET email = ?, `password` = ? WHERE id = ?;";
//		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
//			pst.setString(1, u.getEmail());
//			pst.setString(2, u.getPassword());
//			pst.setLong(3, u.getId());
//			int affectedRows = pst.executeUpdate();
//			if (affectedRows == 1) {
//				r = true;
//			}
//		}
//		return r;
//	}
//	
//	public Usuario getById(long id) {
//
//		Usuario u = null;
//		String sql = "SELECT id, email, password FROM usuario WHERE id= ?;";
//		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
//
//			pst.setLong(1, id);
//
//			try (ResultSet rs = pst.executeQuery()) {
//
//				while (rs.next()) {
//					u = new Usuario();
//					u.setId(rs.getLong("id"));
//					u.setEmail(rs.getString("email"));
//					u.setPassword(rs.getString("password"));
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return u;
//	}
	
	public boolean delete(long id) {
		boolean r = false;
		String sql = "DELETE FROM `usuario` WHERE (`id` = ?);";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				r = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		a.setId(rs.getLong("id"));
		return a;
	}
}