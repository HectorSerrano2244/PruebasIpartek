package com.ipartek.formacion.service.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.modelo.dao.PersonaDAO;
import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.taller.service.PersonaService;
import com.ipartek.formacion.taller.service.exception.PersonaException;

@Service
public class IPersonaService implements PersonaService {

	//Instancia e implementa patron singleton - inyección de dependencias
	@Autowired
	PersonaDAO daoPersona;
	
	@Autowired
	Validator validator;
	
	@Override
	public ArrayList<Persona> listar() {
//		ArrayList<Persona> personas = new ArrayList<Persona>();
//		personas.addAll(daoPersona.getAll().values());
//		return personas;
		return (ArrayList<Persona>) daoPersona.getAll();
	}

	@Override
	public Persona detalle(long id) {
		return daoPersona.detalle(id);
	}

	@Override
	public boolean eliminar(long id) throws PersonaException {
		boolean r = false;
			try {
				r = daoPersona.eliminar(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}

	@Override
	public boolean crear(Persona persona) throws PersonaException {
		boolean r = false;
			Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
			if (violations.isEmpty()) {			
				r = daoPersona.crear(persona);				
			}else {
				throw new PersonaException(PersonaException.EXCEPTION_VIOLATIONS, violations);
			}	
		return r;
	}

	@Override
	public boolean modificar(Persona persona) throws PersonaException {
		boolean r = false;
		try {
			Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
			if(violations.isEmpty()) {
				r = daoPersona.modificar(persona);
			}
			else {
				throw new PersonaException(PersonaException.EXCEPTION_VIOLATIONS, violations);
			}
		} catch (Exception e) {
			throw new PersonaException(PersonaException.EXCEPTION_EXIST);
		}
		return r;
	}
	
}
