package com.ipartek.formacion.taller.service.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.ipartek.formacion.taller.modelo.pojo.Vehiculo;

public class VehiculoException extends Exception {
	private static final long serialVersionUID = 1L;

	public static final String EXCEPTION_INTEGRITY = "El nombre del vehiculo ya existe.";
	
	public static final String EXCEPTION_VIOLATIONS = "No cumple las condiciones de Validación";
	
	private Set<ConstraintViolation<Vehiculo>> violations;
	
	public VehiculoException(String message) {
		super(message);				
		this.violations = null;				
	}
	
	public VehiculoException(String message, Set<ConstraintViolation<Vehiculo>> violations) {
		this(message);
		this.setViolations(violations);
	}
	
	public VehiculoException(Set<ConstraintViolation<Vehiculo>> violations) {
		this.setViolations(violations);
	}
	
	public Set<ConstraintViolation<Vehiculo>> getViolations() {
		return violations;
	}

	public void setViolations(Set<ConstraintViolation<Vehiculo>> violations) {
		this.violations = violations;
	}

}