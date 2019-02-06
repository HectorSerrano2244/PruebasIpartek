package com.ipartek.formacion.taller.service.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.modelo.pojo.Persona;

public class PersonaException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final String EXCEPTION_EXIST = "La persona ya existe";
	
	public static final String EXCEPTION_VIOLATIONS = "No cumple las condiciones de Validación";

	private Set<ConstraintViolation<Persona>> violations;
	
	public PersonaException(String message) {
		super(message);				
		this.violations = null;
	}


	public PersonaException(String message, Set<ConstraintViolation<Persona>> violations) {
		this(message);
		this.setViolations(violations);
	}


	public Set<ConstraintViolation<Persona>> getViolations() {
		return violations;
	}


	public void setViolations(Set<ConstraintViolation<Persona>> violations) {
		this.violations = violations;
	}
	
	
}