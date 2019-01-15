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
import com.ipartek.formacion.modelo.pojo.Mensaje;

public class CocheDAO {
	
	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static CocheDAO INSTANCE = null;
	private Mensaje mensaje = null;

	private static final String SQL_GETMATRICULAS = "{call pa_coche_getMatriculas()}";
	private static final String SQL_GETMATRICULA = "{call pa_coche_getMatricula(?)}";

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
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(SQL_GETMATRICULA);) {

			cs.setString(1, mat);

			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					c = rowMapper(rs);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}
	
	public ArrayList<Coche> getMatriculas() {
		ArrayList<Coche> matriculas = new ArrayList<Coche>();
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMATRICULAS);) {

			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					try {
						matriculas.add(rowMapper(rs));
					} catch (Exception e) {
						mensaje = new Mensaje(Mensaje.TIPO_WARNING, "No se encontró ninguna matricula");
						LOG.warn(mensaje.getTexto(), e);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return matriculas;
	}
}