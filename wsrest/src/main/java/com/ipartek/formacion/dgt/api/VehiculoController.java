package com.ipartek.formacion.dgt.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.pojo.Coche;

@CrossOrigin
@RestController
@RequestMapping("/api/vehiculo/")
public class VehiculoController {
	private final static Logger LOG = Logger.getLogger(VehiculoController.class);
	ResponseEntity<Coche> respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
	private ValidatorFactory factory;
	private Validator validator;
	private static CocheDAO daoCoche;
	
	public VehiculoController() {
		super();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		daoCoche = CocheDAO.getInstance();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Coche> listar() throws SQLException {
		return daoCoche.getAll();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Coche> detalle(@PathVariable String id) {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		try {
			long identificador = Long.parseLong(id);
			Coche coche = daoCoche.getById(identificador);
			if (coche != null) {
				respuesta = new ResponseEntity<Coche>(coche, HttpStatus.OK);
			}
		}
		catch(NumberFormatException e) {
			respuesta = new ResponseEntity<Coche>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			LOG.error(e);
			respuesta = new ResponseEntity<Coche>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Coche> eliminar(@PathVariable long id) {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		if (id > 0) {
			try {
				if (daoCoche.delete(id)) {
					respuesta = new ResponseEntity<Coche>(HttpStatus.OK);
				}
			}
			catch (SQLException e) {
				respuesta = new ResponseEntity<Coche>(HttpStatus.CONFLICT);
			}
			catch (Exception e) {
				respuesta = new ResponseEntity<Coche>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return respuesta;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Coche> updatePatch(@PathVariable long id, @RequestBody Coche c) throws SQLException {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		String modelo = c.getModelo();
		c = daoCoche.getById(id);
		if (c != null) {
			if (daoCoche.updatePatch(modelo, id)) {
				c.setModelo(modelo);
				respuesta = new ResponseEntity<Coche>(c, HttpStatus.OK);
			}
			else {
				respuesta = new ResponseEntity<Coche>(c, HttpStatus.BAD_REQUEST);
			}
		}
		return respuesta;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Coche> update(@PathVariable long id, @RequestBody Coche coche) throws SQLException {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		if (coche != null) {
			if (daoCoche.update(coche)) {
				respuesta = new ResponseEntity<Coche>(coche, HttpStatus.OK);
			}
			else {
				respuesta = new ResponseEntity<Coche>(coche, HttpStatus.BAD_REQUEST);
			}
		}
		return respuesta;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Coche> crear(@RequestBody Coche coche) throws SQLException {
		respuesta = new ResponseEntity<Coche>(coche, HttpStatus.NOT_FOUND);
		try {
			Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
			if(violations.size() > 0) {
				throw new Exception();
			}
			String matricula = coche.getMatricula();
			String modelo = coche.getModelo();
			int km = coche.getKm();
			coche = daoCoche.insert(matricula, modelo, km);
			if (coche != null) {
				respuesta = new ResponseEntity<Coche>(coche, HttpStatus.CREATED);
			}
			else {
				throw new Exception();
			}
		}
		catch(Exception e) {
			respuesta = new ResponseEntity<Coche>(coche, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}
}