package com.ipartek.formacion.dgt.wssoap.cliente;

import java.rmi.RemoteException;

import com.ipartek.formacion.dgt.wssoap.WSDatosVehiculoProxy;
import com.ipartek.formacion.modelo.pojo.Coche;

public class App {

	public static void main(String[] args) throws RemoteException {
		WSDatosVehiculoProxy cliente= new WSDatosVehiculoProxy();
		Coche coche=cliente.obtenerDatos("3548MKZ");
		System.out.println(coche);
	}

}
