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

import com.ipartek.formacion.modelo.pojo.Rol;
import com.ipartek.formacion.taller.api.pojo.Mensaje;
import com.ipartek.formacion.taller.service.RolService;
import com.ipartek.formacion.taller.service.exception.RolException;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RolController {

	@Autowired
	RolService rolService;
	@Autowired
	Validator validator;

	String errores = null;

	@RequestMapping(value = "/api/rol/", method = RequestMethod.GET)
	ResponseEntity<ArrayList<Rol>> listar() {
		ResponseEntity<ArrayList<Rol>> respuesta;
		respuesta = new ResponseEntity<ArrayList<Rol>>(HttpStatus.NOT_FOUND);
		try {
			ArrayList<Rol> lista = (ArrayList<Rol>) rolService.listar();
			if (!lista.isEmpty()) {
				respuesta = new ResponseEntity<ArrayList<Rol>>(lista, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<ArrayList<Rol>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/rol/{id}", method = RequestMethod.GET)
	ResponseEntity<Rol> detalle(@PathVariable long id) {
		ResponseEntity<Rol> respuesta;
		respuesta = new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
		try {
			Rol rol = rolService.detalle(id);
			if (rol != null) {
				respuesta = new ResponseEntity<Rol>(rol, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<Rol>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/rol/", method = RequestMethod.POST)
	ResponseEntity<Mensaje> crear(@RequestBody Rol rol) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			Set<ConstraintViolation<Rol>> violations = validator.validate(rol);
			if (violations.isEmpty()) {
				if (rolService.crear(rol)) {
					respuesta = new ResponseEntity<Mensaje>(HttpStatus.CREATED);
				} else {
					respuesta = new ResponseEntity<Mensaje>(HttpStatus.CONFLICT);
				}
			} else {
				respuesta = new ResponseEntity<Mensaje>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/rol/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Mensaje> eliminar(@PathVariable long id) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (rolService.eliminar(id)) {
				respuesta = new ResponseEntity<Mensaje>(HttpStatus.OK);
			}
		} catch (RolException e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(new Mensaje(e.getMessage()), HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = new ResponseEntity<Mensaje>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}

	@RequestMapping(value = "/api/rol/", method = RequestMethod.PUT)
	ResponseEntity modificar(@RequestBody Rol rol) {
		ResponseEntity<Mensaje> respuesta;
		respuesta = new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		try {
			if (rolService.modificar(rol)) {
				respuesta = new ResponseEntity(rol, HttpStatus.OK);
			} else {
				respuesta = new ResponseEntity("Validación incorrecta", HttpStatus.BAD_REQUEST);
			}
		} catch (RolException e) {

			Mensaje mensaje = new Mensaje(e.getMessage());
			Set<ConstraintViolation<Rol>> violations = e.getViolations();

			if (violations != null) {
				mensaje.addViolationsR(violations);
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
