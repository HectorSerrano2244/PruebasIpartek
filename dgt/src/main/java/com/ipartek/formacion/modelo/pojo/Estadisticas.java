package com.ipartek.formacion.modelo.pojo;

import java.math.BigDecimal;

public class Estadisticas {
	private String mes;
	private Float importe = 0F;
	
	BigDecimal bd;
	
	public Estadisticas(String mes, Float importe) {
		setMes(mes);
		setImporte(importe);
	}
	
	public Estadisticas(String mes) {
		this.mes = mes;
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		bd = new BigDecimal(Float.toString(importe));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		importe = bd.floatValue();
		this.importe = bd.floatValue();
	}
	
	@Override
	public String toString() {
		return "Estadisticas [mes=" + mes + ", importe=" + importe + "]";
	}
}