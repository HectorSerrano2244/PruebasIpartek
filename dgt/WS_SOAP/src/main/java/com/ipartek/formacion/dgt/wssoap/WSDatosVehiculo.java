package com.ipartek.formacion.dgt.wssoap;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.pojo.Coche;

public class WSDatosVehiculo {

	private static CocheDAO cocheDAO;

	public WSDatosVehiculo() {
		super();
		cocheDAO = CocheDAO.getInstance();
	}

	public Coche obtenerDatos(String matricula) {
		Coche c = cocheDAO.getByMatricula(matricula);
		if (c == null) {
			c = new Coche();
		}
		return c;
	}
}
