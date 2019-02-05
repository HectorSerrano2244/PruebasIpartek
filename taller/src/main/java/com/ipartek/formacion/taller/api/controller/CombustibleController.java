package com.ipartek.formacion.taller.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.taller.service.CombustibleService;

@RestController
@RequestMapping("/api/combustible/")
public class CombustibleController {

	@Autowired
	CombustibleService combustibleService;
	
	ResponseEntity<ArrayList<Combustible>> respuesta;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	ResponseEntity<ArrayList<Combustible>> listar() {
		respuesta = new ResponseEntity<ArrayList<Combustible>>(HttpStatus.NOT_FOUND);
		try {
			ArrayList<Combustible> combustibles = (ArrayList<Combustible>) combustibleService.listar();
			if (!combustibles.isEmpty()) {
				respuesta = new ResponseEntity<ArrayList<Combustible>>(combustibles, HttpStatus.OK);
			}
		} catch (Exception e) {
			respuesta = new ResponseEntity<ArrayList<Combustible>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
}
