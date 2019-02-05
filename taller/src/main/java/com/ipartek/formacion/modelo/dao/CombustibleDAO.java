package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.modelo.config.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Combustible;

@Repository
public class CombustibleDAO {
	private static final String SQL_GET_ALL = "SELECT id, nombre FROM combustible ORDER BY id DESC;";
	private static final String SQL_GET_BY_ID = "SELECT id, nombre FROM combustible WHERE id = ?;";
	private static final String SQL_INSERT = "INSERT INTO combustible (nombre) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE FROM combustible SET Nombre = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM combustible WHERE id = ?";

	public ArrayList<Combustible> getAll() {
		ArrayList<Combustible> lista = new ArrayList<Combustible>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				lista.add(mapeo(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Combustible getById(long id) {
		Combustible c = new Combustible();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ID);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				c = mapeo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	private Combustible mapeo(ResultSet rs) throws SQLException {
		Combustible c = new Combustible();
		c.setId(rs.getLong(1));
		c.setNombre(rs.getString(2));
		return c;
	}
}