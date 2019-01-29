package com.ipartek.formacion.modelo.pojo;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Coche {
	private Long id;
	@Size(min=6, max=10)
	@NotBlank
	private String matricula;
	@NotBlank
	@Size(min = 2, max = 45)
	private String modelo;
	@Min(0)
	private Integer km;
	private Date fechaBaja;
	
	public Coche(Long id, String matricula, String modelo, Integer km, Date fechaBaja) {
		this();
		setId(id);
		setMatricula(matricula);
		setModelo(modelo);
		setKm(km);
		setFechaBaja(fechaBaja);
	}

	public Coche() {
		super();
		this.id=-1l;
		this.matricula="";
		this.modelo="";
		this.km=0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", km=" + km + ", fechaBaja="
				+ fechaBaja + "]";
	}
}
