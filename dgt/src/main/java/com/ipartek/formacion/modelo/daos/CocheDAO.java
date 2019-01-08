package com.ipartek.formacion.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDAO {

	private static CocheDAO INSTANCE = null;
	private String dondeEstoy = "";

	private static final String SQL_GETMATRICULA = "SELECT id, matricula, modelo, km FROM coche ORDER BY id DESC LIMIT 100;";

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
	
	public ArrayList<Coche> getMatriculas() {
		ArrayList<Coche> matriculas = new ArrayList<Coche>();
		dondeEstoy = "getMatriculas";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETMATRICULA);) {

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