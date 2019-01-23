package com.ipartek.formacion.dgt.wssoap;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.pojo.Coche;

public class WSDatosVehiculo {

	public Coche obtenerDatos(String matricula) {
		CocheDAO cocheDAO = CocheDAO.getInstance();
		Coche c = cocheDAO.getByMatricula(matricula);
		if (c == null) {
			c = new Coche();
		}
		return c;
	}
}
