package com.ipartek.formacion.taller.api.controller;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.taller.api.pojo.Mensaje;
import com.ipartek.formacion.taller.service.PersonaService;
import com.ipartek.formacion.taller.service.exception.PersonaException;

@CrossOrigin
@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PersonaController {
	@Autowired
	PersonaService personaService;
	@Autowired
	Validator validator;
	
	@RequestMapping(value = "/api/persona/", method = RequestMethod.GET)
	ResponseEntity<ArrayList<Persona>> listar() {
		ResponseEntity<ArrayList<Persona>> respuesta;
		respuesta = new ResponseEntity<ArrayList<Persona>>(HttpStatus.NOT_FOUND);
		try {
			ArrayList<Persona> personas = (ArrayList<Persona>) personaService.listar();
			if (!personas.isEmpty()) {
				respuesta = new ResponseEntity<ArrayList<Persona>>(personas, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<ArrayList<Persona>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/persona/{id}", method = RequestMethod.GET)
	ResponseEntity<Persona> detalle(@PathVariable long id) {
		ResponseEntity<Persona> respuesta;
		respuesta = new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
		try {
			Persona c = personaService.detalle(id);
			if (c != null) {
				respuesta = new ResponseEntity<Persona>(c, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<Persona>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/persona/", method = RequestMethod.POST)
	ResponseEntity<Mensaje> crear(@RequestBody Persona persona) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
			if (violations.isEmpty()) {
				if (personaService.crear(persona)) {
					respuesta = new ResponseEntity<Mensaje>(HttpStatus.CREATED);
				} else {
					respuesta = new ResponseEntity<Mensaje>(HttpStatus.CONFLICT);
				}
			} else {
				respuesta = new ResponseEntity<Mensaje>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/persona/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Mensaje> eliminar(@PathVariable long id) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (personaService.eliminar(id)) {
				respuesta = new ResponseEntity<Mensaje>(HttpStatus.OK);
			}
		} catch (PersonaException e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/persona/", method = RequestMethod.PUT)
	ResponseEntity modificar(@RequestBody Persona persona) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (personaService.modificar(persona)) {
				respuesta = new ResponseEntity(persona, HttpStatus.OK);
			} else {
				respuesta = new ResponseEntity("Validación incorrecta", HttpStatus.BAD_REQUEST);
			}
		} catch (PersonaException e) {

			Mensaje mensaje = new Mensaje(e.getMessage());
			Set<ConstraintViolation<Persona>> violations = e.getViolations();

			if (violations != null) {
				mensaje.addViolationsP(violations);
				respuesta = new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			} else {
				respuesta = new ResponseEntity(mensaje, HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			respuesta = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
//	@RequestMapping(value = "/api/persona/{id}/vehiculo", method = RequestMethod.GET)
//	public ArrayList<Vehiculo> vehiculos(@PathVariable long id) throws SQLException {
//		return personaService.vehiculos(id);
//	}
}
