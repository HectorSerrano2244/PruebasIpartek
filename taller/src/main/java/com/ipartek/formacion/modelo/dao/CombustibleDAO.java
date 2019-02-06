package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.modelo.config.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Combustible;

@Repository
public class CombustibleDAO implements IDAO<Combustible> {
	private static final String SQL_GET_ALL = "SELECT id, nombre FROM combustible ORDER BY id DESC;";
	private static final String SQL_GET_BY_ID = "SELECT id, nombre FROM combustible WHERE id = ?;";
	private static final String SQL_INSERT = "INSERT INTO combustible (nombre) VALUES (?);";
	private static final String SQL_UPDATE = "UPDATE combustible SET Nombre = ? WHERE id = ?;";
	private static final String SQL_DELETE = "DELETE FROM combustible WHERE id = ?;";

	@Override
	public ArrayList<Combustible> getAll() {
		ArrayList<Combustible> lista = new ArrayList<Combustible>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				lista.add(rowMapper(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Combustible detalle(long id) {
		Combustible c = new Combustible();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ID);) {
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				c = rowMapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean crear(Combustible c) {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, c.getNombre());
			if (pst.executeUpdate() == 1) {
				try (ResultSet rs = pst.getGeneratedKeys();) {
					rs.next();
					c.setId(rs.getLong(1));
				}
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean modificar(Combustible c) throws SQLException {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, c.getNombre());
			pst.setLong(2, c.getId());
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}

	@Override
	public boolean eliminar(long id) throws SQLException {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_DELETE);) {
			pst.setLong(1, id);
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}

	private Combustible rowMapper(ResultSet rs) throws SQLException {
		Combustible c = new Combustible();
		c.setId(rs.getLong(1));
		c.setNombre(rs.getString(2));
		return c;
	}
}