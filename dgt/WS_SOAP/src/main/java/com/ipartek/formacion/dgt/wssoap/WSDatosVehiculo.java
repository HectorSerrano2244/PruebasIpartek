package com.ipartek.formacion.dgt.wssoap;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.pojo.Coche;

public class WSDatosVehiculo {
	private void ini() {
		CocheDAO cochedao=CocheDAO.getInstance();

	}
	public Coche obtenerDatos(String matricula) {
	
		Coche c =new Coche();
		
		return c;
	}
}
