package com.ipartek.formacion.dgt.wssoap.cliente;

import java.rmi.RemoteException;

import com.ipartek.formacion.dgt.wssoap.WSDatosVehiculoProxy;

public class App {

	public static void main(String[] args) throws RemoteException {
		WSDatosVehiculoProxy cliente= new WSDatosVehiculoProxy();
		cliente.obtenerDatos("3548MKZ");
		System.out.println(cliente);
	}

}
