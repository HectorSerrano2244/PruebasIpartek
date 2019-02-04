package com.ipartek.formacion.taller.api.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.modelo.pojo.Vehiculo;
import com.ipartek.formacion.taller.service.PersonaService;

@CrossOrigin
@RestController
public class PersonaController {
	@Autowired
	PersonaService personaService;
	
	@RequestMapping(value = "/api/persona", method = RequestMethod.GET)
	public ArrayList<Persona> listar() throws SQLException {
		return personaService.listar();
	}
	
	@RequestMapping(value = "/api/persona/{id}/vehiculo", method = RequestMethod.GET)
	public ArrayList<Vehiculo> vehiculos(@PathVariable long id) throws SQLException {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		Vehiculo v = new Vehiculo();
		v.setId(id);
		v.setMatricula("BI000SD");
		vehiculos.add(v);
		return vehiculos;
		//return personaService.vehiculos(id);
	}
}
