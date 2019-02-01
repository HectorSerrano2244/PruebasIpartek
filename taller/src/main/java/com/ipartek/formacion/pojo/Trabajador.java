package com.ipartek.formacion.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Trabajador {
	private Long id;
	private Puesto puesto;
	@NotNull
	@NotBlank
	private String nombre;
	@NotNull
	@NotBlank
	private String apellidos;
	@Pattern(regexp = "\\d{8}[A-Z]{1}")
	private String DNI;
	@Pattern(regexp = "[\\+]{0,1}\\d{9,11}")
	private String telefono;

	public Trabajador(Long id, Puesto puesto, String nombre, String apellidos, String DNI, String telefono) {
		super();
		setId(id);
		setPuesto(puesto);
		setNombre(nombre);
		setApellidos(apellidos);
		setDNI(DNI);
		setTelefono(telefono);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Puesto getPuesto() {
		return puesto;
	}
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
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
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", puesto=" + puesto + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", DNI=" + DNI + ", telefono=" + telefono + "]";
	}

}
