package com.ipartek.formacion.taller.api.controller;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.taller.api.pojo.Mensaje;
import com.ipartek.formacion.taller.service.CombustibleService;
import com.ipartek.formacion.taller.service.exception.CombustibleException;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CombustibleController {

	@Autowired
	CombustibleService combustibleService;
	@Autowired
	Validator validator;

	String errores = null;

	@RequestMapping(value = "/api/combustible/", method = RequestMethod.GET)
	ResponseEntity<ArrayList<Combustible>> listar() {
		ResponseEntity<ArrayList<Combustible>> respuesta;
		respuesta = new ResponseEntity<ArrayList<Combustible>>(HttpStatus.NOT_FOUND);
		try {
			ArrayList<Combustible> combustibles = (ArrayList<Combustible>) combustibleService.listar();
			if (!combustibles.isEmpty()) {
				respuesta = new ResponseEntity<ArrayList<Combustible>>(combustibles, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<ArrayList<Combustible>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/combustible/{id}", method = RequestMethod.GET)
	ResponseEntity<Combustible> detalle(@PathVariable long id) {
		ResponseEntity<Combustible> respuesta;
		respuesta = new ResponseEntity<Combustible>(HttpStatus.NOT_FOUND);
		try {
			Combustible c = combustibleService.detalle(id);
			if (c != null) {
				respuesta = new ResponseEntity<Combustible>(c, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<Combustible>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/combustible/", method = RequestMethod.POST)
	ResponseEntity<Mensaje> crear(@RequestBody Combustible combustible) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Set<ConstraintViolation<Combustible>> violations = validator.validate(combustible);
			if (violations.isEmpty()) {
				if (combustibleService.crear(combustible)) {
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

	@RequestMapping(value = "/api/combustible/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Mensaje> eliminar(@PathVariable long id) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (combustibleService.eliminar(id)) {
				respuesta = new ResponseEntity<Mensaje>(HttpStatus.OK);
			}
		} catch (CombustibleException e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/combustible/", method = RequestMethod.PUT)
	ResponseEntity modificar(@RequestBody Combustible combustible) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (combustibleService.modificar(combustible)) {
				respuesta = new ResponseEntity(combustible, HttpStatus.OK);
			} else {
				respuesta = new ResponseEntity("Validación incorrecta", HttpStatus.BAD_REQUEST);
			}
		} catch (CombustibleException e) {

			Mensaje mensaje = new Mensaje(e.getMessage());
			Set<ConstraintViolation<Combustible>> violations = e.getViolations();

			if (violations != null) {
				mensaje.addViolationsC(violations);
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
}
