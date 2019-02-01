package com.ipartek.formacion.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Combustible {
	private Long id;
	@NotNull
	@NotBlank
	private String nombre;
	
	public Combustible(Long id, String nombre) {
		super();
		setId(id);
		setNombre(nombre);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModelo() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nombre=" + nombre + "]";
	}
}
