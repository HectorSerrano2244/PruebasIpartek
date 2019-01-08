package com.ipartek.formacion.modelo.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;

public class MultaDAO {

	private static MultaDAO INSTANCE = null;
	private String dondeEstoy="";

	private static final String SQL_GETBYID = "SELECT m.id, m.fecha, m.importe, m.concepto, c.matricula, modelo, km FROM multa m, coche c WHERE m.id_coche = c.id AND m.id = ?;";
//	private static final String SQL_GETALL = "SELECT v.id as 'id_video', u.id as 'id_usuario', email, password, nombre, codigo FROM video as v, usuario as u WHERE v.id_usuario = u.id ORDER BY v.id DESC LIMIT 1000;";
	private static final String SQL_GETALL_USU = "SELECT m.id, m.fecha, c.matricula, c.modelo FROM multa m, coche c WHERE m.id_coche = c.id AND m.id_agente = ? ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_INSERT = "INSERT INTO multa  (importe, concepto, fecha, id_coche, id_agente) VALUES( ? , ? , ? , ? , ?);";
//	private static final String SQL_UPDATE = "UPDATE video SET nombre = ? , codigo = ? , id_usuario = ?  WHERE id = ?;";
//	private static final String SQL_DELETE = "DELETE FROM video WHERE id = ?;";

	// constructor privado, solo acceso por getInstance()
	private MultaDAO() {
		super();
	}

	public synchronized static MultaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MultaDAO();
		}
		return INSTANCE;
	}

	public Multa getById(long id) {

		Multa m = null;
		
		dondeEstoy = "getById";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYID);) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					m = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

//	public ArrayList<Multa> getAll() {
//
//		ArrayList<Multa> videos = new ArrayList<Multa>();
//
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
//				ResultSet rs = pst.executeQuery()) {
//
//			while (rs.next()) {
//				try {
//					videos.add(rowMapper(rs));
//				} catch (Exception e) {
//					System.out.println("usuario no valido");
//					e.printStackTrace();
//				}
//			} // while
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return videos;
//	}

	public ArrayList<Multa> getAllUsu(long id) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		dondeEstoy = "getAllUsu";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETALL_USU);) {

			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					try {
						multas.add(rowMapper(rs));
					} catch (Exception e) {
						System.out.println("usuario no valido");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return multas;
	}

	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;
		dondeEstoy="Insert";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {

			pst.setFloat(1, m.getImporte());
			pst.setString(2, m.getConcepto());
			pst.setDate(3, (Date) m.getFecha());
			pst.setLong(4, m.getCoche().getId());
			pst.setLong(5, m.getAgente().getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;

	}

//	public boolean update(Multa m) throws SQLException {
//
//		boolean resul = false;
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {
//
//			pst.setString(1, v.getNombre());
//			pst.setString(2, v.getCodigo());
//			pst.setLong(3, v.getUsuario().getId());
//			pst.setLong(4, v.getId());
//
//			int affectedRows = pst.executeUpdate();
//			if (affectedRows == 1) {
//				resul = true;
//			}
//		}
//		return resul;
//
//	}
//
//	public boolean delete(long id) throws SQLException {
//
//		boolean resul = false;
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(SQL_DELETE);) {
//
//			pst.setLong(1, id);
//
//			int affectedRows = pst.executeUpdate();
//			if (affectedRows == 1) {
//				resul = true;
//			}
//
//		}
//		return resul;
//
//	}

	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		Coche c = new Coche();
		m.setFecha(rs.getDate("fecha"));
		m.setId(rs.getLong("id"));
		c.setMatricula(rs.getString("matricula"));
		if("getById".equals(dondeEstoy)) {	
			m.setImporte(rs.getFloat("importe"));
			m.setConcepto(rs.getString("concepto"));
			c.setId(rs.getLong("id"));
			c.setModelo(rs.getString("modelo"));
			c.setKm(rs.getFloat("km"));
		}
//		Agente a = new Agente();
//		a.setId(rs.getLong("id"));
//		a.setNombre(rs.getString("nombre"));
//		a.setPlaca(rs.getString("placa"));
//		m.setAgente(a);
		
		m.setCoche(c);
		return m;
	}

}
