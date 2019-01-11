package com.ipartek.formacion.modelo.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class Multa {
	private Long id;
	@NotNull
	private Float importe;
	@NotNull
	private String concepto;
	private Date fecha;
	private Agente agente;
	private Coche coche;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	public Multa(Long id, Float importe, String concepto, Date fecha, Agente agente, Coche coche) {
		this();
		setId(id);
		setImporte(importe);
		setConcepto(concepto);
		setFecha(fecha);
		setAgente(agente);
		setCoche(coche);
	}
	public Multa() {
		super();
		this.id=-1l;
		this.importe=0.0f;
		this.concepto="";
		this.fecha=null; 
		this.agente=new Agente();
		this.coche=new Coche();
	}
	@Override
	public String toString() {
		return "Multa [id=" + id + ", importe=" + importe + ", concepto=" + concepto + ", fecha=" + fecha + ", agente="
				+ agente + ", coche=" + coche + "]";
	}
	
	
	
	


}
