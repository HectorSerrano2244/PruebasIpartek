package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.modelo.config.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Rol;

@Repository
public class RolDAO implements IDAO<Rol> {
	private static final String SQL_GET_ALL = "SELECT id, nombre FROM rol ORDER BY id DESC;";
	private static final String SQL_GET_BY_ID = "SELECT id, nombre FROM rol WHERE id = ?;";
	private static final String SQL_INSERT = "INSERT INTO rol (nombre) VALUES (?);";
	private static final String SQL_UPDATE = "UPDATE rol SET Nombre = ? WHERE id = ?;";
	private static final String SQL_DELETE = "DELETE FROM rol WHERE id = ?;";

	@Override
	public List<Rol> getAll() {
		ArrayList<Rol> lista = new ArrayList<Rol>();
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
	public Rol detalle(long id) {
		Rol rol = new Rol();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ID);) {
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				rol = rowMapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rol;
	}

	@Override
	public boolean crear(Rol rol) {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {
			pst.setString(1, rol.getNombre());
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean modificar(Rol rol) throws SQLException {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, rol.getNombre());
			pst.setLong(2, rol.getId());
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

	private Rol rowMapper(ResultSet rs) throws SQLException {
		Rol rol = new Rol();
		rol.setId(rs.getLong(1));
		rol.setNombre(rs.getString(2));
		return rol;
	}

}
