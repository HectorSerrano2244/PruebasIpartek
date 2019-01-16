package com.ipartek.formacion.modelo.pojo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Agente {
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	@Size(min = 6, max = 6)
	private int placa;
	@NotEmpty
	private String password;
	
	public Agente(Long id, String nombre, int placa, String password) {
		this();
		setId(id);
		setNombre(nombre);
		setPlaca(placa);
		setPassword(password);
	}

	public Agente() {
		super();
		this.id = -1l;
		this.nombre = "";
		this.placa = 0;
		this.password = "";
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


	public int getPlaca() {
		return placa;
	}

	public void setPlaca(int placa) {
		this.placa = placa;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Agente [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", password=" + password + "]";
	}
	
}
