package com.ipartek.formacion.modelo.pojo;

import javax.validation.constraints.Pattern;

public class Vehiculo {
	private Long id;
	private Combustible combustible;
	private Modelo modelo;
	private Persona persona;
	@Pattern(regexp = "\\w{17}")
	private String numBastidor;
	@Pattern(regexp = "\\d{2}")
	private String matricula;
	
	public Vehiculo() {
		super();
		this.id = -1L;
		this.combustible = null;
		this.modelo = null;
		this.persona = null;
		this.numBastidor = "";
		this.matricula = "";
	}
	
	public Vehiculo(Long id, Combustible combustible, Modelo modelo, Persona persona, String numBastidor,
			String matricula) {
		super();
		setId(id);
		setCombustible(combustible);
		setModelo(modelo);
		setPersona(persona);
		setNumBastidor(numBastidor);
		setMatricula(matricula);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getNumBastidor() {
		return numBastidor;
	}

	public void setNumBastidor(String numBastidor) {
		this.numBastidor = numBastidor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", combustible=" + combustible + ", modelo=" + modelo + ", persona=" + persona
				+ ", numBastidor=" + numBastidor + ", matricula=" + matricula + "]";
	}
	
}
