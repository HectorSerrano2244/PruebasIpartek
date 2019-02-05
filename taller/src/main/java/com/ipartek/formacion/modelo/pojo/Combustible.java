package com.ipartek.formacion.modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Combustible {
	private Long id;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 45)
	private String nombre;
	
	public Combustible() {
		super();
		this.id = -1L;
		this.nombre = "";
	}
	
	public Combustible(Long id, String nombre) {
		this();
		this.setId(id);
		this.setNombre(nombre);
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
