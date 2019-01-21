package com.ipartek.formacion.modelo.pojo;

import java.math.BigDecimal;

public class Estadistica {
	private int mes;
	private Float importe = 0F;

	BigDecimal bd;

	public Estadistica(int mes, Float importe) {
		this();
		setMes(mes);
		setImporte(importe);
	}
	
	public Estadistica() {
		super();
		this.mes=-1;
		this.importe=0F;
	}


	public int getMes() {
		return mes;
	}

	public void setMes(int i) {
		this.mes = i;
	}

	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float d) {
		/*
		 * bd = new BigDecimal(Float.toString(importe)); bd = bd.setScale(2,
		 * BigDecimal.ROUND_HALF_UP); importe = bd.floatValue(); this.importe =
		 * bd.floatValue();
		 */
		this.importe = d;
	}

	@Override
	public String toString() {
		return "Estadisticas [mes=" + mes + ", importe=" + importe + "]";
	}
}