package com.ipartek.formacion.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Cliente {
	private Long id;
	@NotNull
	@NotBlank
	private String nombre;
	@NotNull
	@NotBlank
	private String apellidos;
	@Pattern(regexp = "[\\+]{0,1}\\d{9,11}")
	private String telefono;
	@Pattern(regexp = "\\d{8}[A-Z]{1}")
	private String DNI;

	public Cliente(Long id, String nombre, String apellidos, String telefono, String dni) {
		super();
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setTelefono(telefono);
		setDNI(dni);
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dni) {
		DNI = dni;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", DNI=" + DNI + "]";
	}
}