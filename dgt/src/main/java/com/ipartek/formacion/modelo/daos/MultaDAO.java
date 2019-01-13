package com.ipartek.formacion.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Mensaje;
import com.ipartek.formacion.modelo.pojo.Multa;

public class MultaDAO {
	private final static Logger LOG = Logger.getLogger(MultaDAO.class);
	private static MultaDAO INSTANCE = null;
	private boolean isGetById = false;
	private boolean isBaja = false;
	private Mensaje mensaje = null;

	private static final String SQL_GETBYID = "SELECT m.id, m.fecha_alta, m.importe, m.concepto, c.matricula, modelo, km FROM multa m, coche c WHERE m.id_coche = c.id AND m.id = ?;";
	private static final String SQL_GETBYID_BAJA = "SELECT m.id, m.fecha_alta, m.fecha_baja, m.importe, m.concepto, c.matricula, modelo, km FROM multa m, coche c WHERE m.id_coche = c.id AND m.id = ?;";
	private static final String SQL_GETALL_BYUSER = "SELECT m.id, m.fecha_alta, c.matricula, c.modelo FROM multa m, coche c WHERE m.id_coche = c.id AND m.id_agente = ? AND fecha_baja IS NULL ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_GETALL_BYUSER_BAJA = "SELECT m.id, m.fecha_alta, m.fecha_baja, c.matricula, c.modelo FROM multa m, coche c WHERE m.id_coche = c.id AND m.id_agente = ? AND fecha_baja IS NOT NULL ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_INSERT = "INSERT INTO multa (importe, concepto, id_coche, id_agente) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE multa SET fecha_baja = CURRENT_TIMESTAMP  WHERE id = ?;";
//	private static final String SQL_UPDATE_RECUPERAR = "UPDATE multa SET fecha_baja = NULL WHERE id = ?;";

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

	public Multa getById(long id, String opm) {

		Multa m = null;
		
		isGetById = true;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(("baja".equals(opm)) ? SQL_GETBYID_BAJA : SQL_GETBYID);) {
			pst.setLong(1, id);
			
			if ("baja".equals(opm)) {
				isBaja = true;
			}
			else {
				isBaja = false;
			}
			
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					m = rowMapper(rs);
				}
				LOG.debug("Multa encontrada");
			}
		} catch (Exception e) {
			mensaje = new Mensaje(Mensaje.TIPO_DANGER, "Multa no encontrada");
			LOG.error(mensaje.getTexto(), e);
		}
		return m;
	}

	public ArrayList<Multa> getAllByUser(long id, String opm) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		isGetById = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(("baja".equals(opm)) ? SQL_GETALL_BYUSER_BAJA : SQL_GETALL_BYUSER);) {
			if ("baja".equals(opm)) {
				isBaja = true;
			}
			else {
				isBaja = false;
			}
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					try {
						multas.add(rowMapper(rs));
					} catch (Exception e) {
						mensaje = new Mensaje(Mensaje.TIPO_DANGER, "Error en la sentencia SQL");
						LOG.error(mensaje.getTexto(), e);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return multas;
	}

	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;
		isGetById = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {

			pst.setFloat(1, m.getImporte());
			pst.setString(2, m.getConcepto());
			pst.setLong(3, m.getCoche().getId());
			pst.setLong(4, m.getAgente().getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;

	}

	public boolean update(Multa m) throws SQLException {

	boolean resul = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {

			pst.setLong(1, m.getId());
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		}
		return resul;

	}


	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		Coche c = new Coche();
		m.setFechaAlta(rs.getDate("fecha_alta"));
		if (isBaja) {
			m.setFechaBaja(rs.getDate("fecha_baja"));
		}
		m.setId(rs.getLong("id"));
		c.setMatricula(rs.getString("matricula"));
		if(isGetById) {	
			m.setImporte(rs.getFloat("importe"));
			m.setConcepto(rs.getString("concepto"));
			c.setId(rs.getLong("id"));
			c.setModelo(rs.getString("modelo"));
			c.setKm(rs.getInt("km"));
		}
		m.setCoche(c);
		return m;
	}

}
