package com.ipartek.formacion.modelo.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Rol {
	private Long id;
	@NotNull
	@NotBlank
	private String nombre;

	public Rol(Long id, String nombre) {
		this();
		setId(id);
		setNombre(nombre);
	}

	public Rol() {
		super();
		this.id = -1L;
		this.nombre = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
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
