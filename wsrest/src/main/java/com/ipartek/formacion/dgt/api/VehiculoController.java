package com.ipartek.formacion.dgt.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.pojo.Coche;

@RestController
public class VehiculoController {
	private final static Logger LOG = Logger.getLogger(VehiculoController.class);
	ResponseEntity<Coche> respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
	private static CocheDAO daoCoche;
	
	public VehiculoController() {
		super();
		daoCoche = CocheDAO.getInstance();
	}
	
	@RequestMapping(value = "/api/vehiculo", method = RequestMethod.GET)
	public ArrayList<Coche> listar() {
		return daoCoche.getAll();
	}
	
	@RequestMapping(value = "/api/vehiculo/{id}", method = RequestMethod.GET)
	public Coche detalle(@PathVariable long id) {
		Coche c = new Coche();
		if (id > 0) {
		c = new Coche(2L, "9605EFH", "Fiat Multipla", 800);
		c.setId(id);
		}
		return c;
	}
	
	@RequestMapping(value = "/api/vehiculo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Coche> eliminar(@PathVariable long id) {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		if (id > 0) {
			try {
				if (daoCoche.delete(id)) {
					respuesta = new ResponseEntity<Coche>(HttpStatus.OK);
				}
			} catch (SQLException e) {
				respuesta = new ResponseEntity<Coche>(HttpStatus.BAD_REQUEST);
				LOG.error(e);
			}
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/vehiculo/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Coche> updatePatch(@PathVariable long id, @RequestBody Coche c) throws SQLException {
		respuesta = new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		c = daoCoche.getById(id);
		if (c != null) {
			if (daoCoche.updatePatch(c.getModelo(), id)) {
				respuesta = new ResponseEntity<Coche>(c, HttpStatus.OK);
			}
			else {
				respuesta = new ResponseEntity<Coche>(c, HttpStatus.BAD_REQUEST);
			}
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/api/vehiculo", method = RequestMethod.POST)
	public ResponseEntity<Coche> crear(@RequestBody Coche c) throws SQLException {
		respuesta = new ResponseEntity<Coche>(c, HttpStatus.NOT_FOUND);
		try {
			if (daoCoche.insert(c)) {
				respuesta = new ResponseEntity<Coche>(c, HttpStatus.CREATED);
			}
			else {
				throw new Exception();
			}
		}
		catch(Exception e) {
			respuesta = new ResponseEntity<Coche>(c, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}
}