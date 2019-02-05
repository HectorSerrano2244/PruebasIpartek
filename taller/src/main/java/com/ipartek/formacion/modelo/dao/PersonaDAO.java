package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.ipartek.formacion.modelo.config.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.modelo.pojo.Rol;
import com.ipartek.formacion.modelo.pojo.Vehiculo;

@Repository
public class PersonaDAO {
	private static final String SQL_GET_ALL = "SELECT p.id 'id_persona', p.nombre 'nombre', r.id 'id_rol', r.nombre 'rol' FROM persona_has_rol pr, persona p, rol r WHERE pr.id_persona = p.id AND pr.id_rol = r.id ORDER BY p.id LIMIT 100;";
	private static final String SQL_GET_ALL_VEHICULOS_USER = "SELECT m.nombre 'modelo', numero_bastidor, matricula FROM persona p, vehiculo v, modelo m WHERE v.id_propietario = p.id AND m.id = v.id_modelo AND p.id = 2;";

	public HashMap<Long, Persona> getAll() {
		HashMap<Long, Persona> hmPersonas = new HashMap<Long, Persona>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				long idPersona = rs.getInt("id_persona");
				Persona p = hmPersonas.get(idPersona);

				if (p == null) { // si no encuentro crear
					p = new Persona();
					p.setId(idPersona);
					p.setNombre(rs.getString("nombre"));
				}
				Rol rol = new Rol();
				rol.setId(rs.getLong("id_rol"));
				rol.setNombre(rs.getString("rol"));

				ArrayList<Rol> roles = p.getRoles();
				roles.add(rol);
				p.setRoles(roles);

				hmPersonas.put(idPersona, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hmPersonas;
	}
	public HashMap<Long, Vehiculo> getAllVehiculosByUser(long id) {
		HashMap<Long, Vehiculo> hmVehiculos = new HashMap<Long, Vehiculo>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL_VEHICULOS_USER);
				ResultSet rs = pst.executeQuery();) {
			pst.setLong(1, id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hmVehiculos;
	}
}
