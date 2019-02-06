package com.ipartek.formacion.modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Persona {
	private Long id;
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
//	private ArrayList<Rol> roles;
	
	public Persona() {
		super();
		this.id = -1L;
		this.nombre = "";
		this.apellidos = "";
		this.DNI = "";
		this.telefono = "";
//		this.roles = new ArrayList<Rol>();
	}

	public Persona(Long id, String nombre, String apellidos, String DNI, String telefono/*, Rol roles*/) {
		super();
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDNI(DNI);
		setTelefono(telefono);
//		setRoles(roles);
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

//	public ArrayList<Rol> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(ArrayList<Rol> roles) {
//		this.roles = roles;
//	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI
				+ ", telefono=" + telefono /*+ ", roles=" +  roles */+ "]";
	}

}
