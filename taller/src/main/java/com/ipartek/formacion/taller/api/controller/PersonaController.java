package com.ipartek.formacion.taller.api.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.pojo.Persona;

@CrossOrigin
@RestController
public class PersonaController {
	@RequestMapping(value = "/api/persona", method = RequestMethod.GET)
	public ArrayList<Persona> listar() throws SQLException {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Persona p = new Persona();
		p.setNombre("Josemari");
		personas.add(p);
		
		p = new Persona();
		p.setNombre("Manolo");
		personas.add(p);
		return personas;
	}
}
