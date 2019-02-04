package com.ipartek.formacion.modelo.pojo;

import java.util.Calendar;

public class Reparacion {
	private Long id;
	private Persona persona;
	private Vehiculo vehiculo;
	private Calendar fecha;
	private float precio;
	private String observaciones;
	
	public Reparacion(Long id, Persona persona, Vehiculo vehiculo, Calendar fecha, float precio,
			String observaciones) {
		super();
		setId(id);
		setPersona(persona);
		setVehiculo(vehiculo);
		setFecha(fecha);
		setPrecio(precio);
		setObservaciones(observaciones);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "Reparacion [id=" + id + ", persona=" + persona + ", vehiculo=" + vehiculo + ", fecha=" + fecha
				+ ", precio=" + precio + ", observaciones=" + observaciones + "]";
	}
	
}
