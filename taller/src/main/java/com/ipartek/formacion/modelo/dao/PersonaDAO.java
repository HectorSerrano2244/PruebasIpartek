package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.modelo.config.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.modelo.pojo.Vehiculo;

@Repository
public class PersonaDAO implements IDAO<Persona>{
//	private static final String SQL_GET_ALL = "SELECT p.id 'id_persona', p.nombre 'nombre', r.id 'id_rol', r.nombre 'rol' FROM persona_has_rol pr, persona p, rol r WHERE pr.id_persona = p.id AND pr.id_rol = r.id ORDER BY p.id LIMIT 100;";
	private static final String SQL_GET_ALL = "SELECT id, nombre, apellidos, dni, telefono FROM persona;";
	private static final String SQL_GET_BY_ID = "SELECT id, nombre, apellidos, dni, telefono FROM persona WHERE id = ?;";
	private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellidos, dni, telefono) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE nombre SET Nombre = ?, Apellidos = ?, DNI = ?, telefono = ? WHERE id = ?;";
	private static final String SQL_DELETE = "DELETE FROM persona WHERE id = ?;";
//	private static final String SQL_GET_ALL_VEHICULOS_USER = "SELECT m.nombre 'modelo', numero_bastidor, matricula FROM persona p, vehiculo v, modelo m WHERE v.id_propietario = p.id AND m.id = v.id_modelo AND p.id = 2;";
	
	@Override
	public List<Persona> getAll() {
		ArrayList<Persona> lista = new ArrayList<Persona>();
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
	public Persona detalle(long id) {
		Persona p = new Persona();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ID);) {
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				p = rowMapper(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	@Override
	public boolean crear(Persona p) {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getApellidos());
			pst.setString(3, p.getDNI());
			pst.setString(4, p.getTelefono());
			if (pst.executeUpdate() == 1) {
				try (ResultSet rs = pst.getGeneratedKeys();) {
					rs.next();
					p.setId(rs.getLong(1));
				}
				r = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public boolean modificar(Persona p) throws SQLException {
		boolean r = false;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getApellidos());
			pst.setString(3, p.getDNI());
			pst.setString(4, p.getTelefono());
			pst.setLong(5, p.getId());
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
	
	private Persona rowMapper(ResultSet rs) throws SQLException {
		Persona p = new Persona();
		p.setId(rs.getLong("id"));
		p.setNombre(rs.getString("nombre"));
		p.setApellidos(rs.getString("apellidos"));
		p.setDNI(rs.getString("dni"));
		p.setTelefono(rs.getString("telefono"));
		return p;
	}
	
//	public HashMap<Long, Persona> getAll() {
//		HashMap<Long, Persona> hmPersonas = new HashMap<Long, Persona>();
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL);
//				ResultSet rs = pst.executeQuery();) {
//			while (rs.next()) {
//				long idPersona = rs.getInt("id_persona");
//				Persona p = hmPersonas.get(idPersona);
//
//				if (p == null) { // si no encuentro crear
//					p = new Persona();
//					p.setId(idPersona);
//					p.setNombre(rs.getString("nombre"));
//				}
//				Rol rol = new Rol();
//				rol.setId(rs.getLong("id_rol"));
//				rol.setNombre(rs.getString("rol"));
//
//				ArrayList<Rol> roles = p.getRoles();
//				roles.add(rol);
//				p.setRoles(roles);
//
//				hmPersonas.put(idPersona, p);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return hmPersonas;
//	}
//	public HashMap<Long, Vehiculo> getAllVehiculosByUser(long id) {
//		HashMap<Long, Vehiculo> hmVehiculos = new HashMap<Long, Vehiculo>();
//		try (Connection conn = ConnectionManager.getConnection();
//				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL_VEHICULOS_USER);
//				ResultSet rs = pst.executeQuery();) {
//			pst.setLong(1, id);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return hmVehiculos;
//	}
}
