package com.ipartek.formacion.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDAO {

	private static CocheDAO INSTANCE = null;
	private String dondeEstoy = "";

	private static final String SQL_GETMATRICULAS = "SELECT id, matricula, modelo, km FROM coche ORDER BY id DESC LIMIT 100;";
	private static final String SQL_GETMATRICULA = "SELECT * FROM coche WHERE matricula= ? ;";

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
		return c;
	}
	
	public Coche getByMatri(String mat) {
		Coche c = null;
		String sql = SQL_GETMATRICULA;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, mat);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					c = rowMapper(rs);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public ArrayList<Coche> getMatriculas() {
		ArrayList<Coche> matriculas = new ArrayList<Coche>();
		dondeEstoy = "getMatriculas";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETMATRICULAS);) {

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					try {
						matriculas.add(rowMapper(rs));
					} catch (Exception e) {
						System.out.println("usuario no valido");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matriculas;
	}
}