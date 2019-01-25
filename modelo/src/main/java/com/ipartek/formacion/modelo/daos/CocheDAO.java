package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDAO {

	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static CocheDAO INSTANCE = null;

	private static final String SQL_GETMATRICULA = "{call pa_coche_getByMatricula(?)}";
	private static final String SQL_GETBYID = "SELECT id, matricula, modelo, km FROM coche WHERE id = ?";

	// constructor privado, solo acceso por getInstance()
	private CocheDAO() {
		super();
	}

	public synchronized static CocheDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CocheDAO();
		}
		return INSTANCE;
	}

	private Coche rowMapper(ResultSet rs) throws SQLException {
		Coche c = new Coche();
		c.setId(rs.getLong("id"));
		c.setMatricula(rs.getString("matricula"));
		c.setModelo(rs.getString("modelo"));
		c.setKm(rs.getInt("km"));
		return c;
	}

	public Coche getByMatricula(String mat) {
		Coche c = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMATRICULA);) {

			cs.setString(1, mat);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						c = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}
	
	public Coche getById(Long id) {
		Coche c = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement cs = conn.prepareStatement(SQL_GETBYID);) {

			cs.setLong(1, id);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						c = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}

	public ArrayList<Coche> getAll() {

		ArrayList<Coche> coches = new ArrayList<Coche>();
		String sql = "SELECT * FROM coche ORDER BY id DESC LIMIT 100";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				try {
					Coche coche = new Coche();
					coche.setId(rs.getLong("id"));
					coche.setMatricula(rs.getString("matricula"));
					coche.setModelo(rs.getString("modelo"));
					coche.setKm(rs.getInt("km"));
					coches.add(coche);
				} catch (Exception e) {
					System.out.println("coche no valido");
					LOG.error(e);
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return coches;
	}
	
	public boolean insert(Coche c) throws SQLException {
		boolean r = false;
		String sql = "INSERT INTO coche (matricula, modelo, km) VALUES (?, ?, ?);";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, c.getMatricula());
			pst.setString(2, c.getModelo());
			pst.setInt(3, c.getKm());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				r = true;
			}
		}
		return r;
	}
	
	public boolean delete(Long id) throws SQLException {
		boolean r = false;
		String sql = "DELETE FROM coche WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				r = true;
			}
		}
		return r;
	}
	
	public boolean updatePatch(String modelo, long id) throws SQLException {
		boolean r = false;
		String sql = "UPDATE coche SET modelo = ? WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, modelo);
			pst.setLong(2, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				r = true;
			}
		}
		return r;
	}
	
//	public boolean darDeBaja(Long id) throws SQLException {
//		boolean r = false;
//		String sql = "UPDATE coche SET modelo = ?";
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(sql);) {
//			pst.setLong(1, id);
//			int affectedRows = pst.executeUpdate();
//			if (affectedRows == 1) {
//				r = true;
//			}
//		}
//		return r;
//	}
}