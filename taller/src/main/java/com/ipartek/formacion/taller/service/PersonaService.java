package com.ipartek.formacion.taller.service;

import java.util.ArrayList;

import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.modelo.pojo.Vehiculo;

public interface PersonaService {
	
	/**
	 * Listado Personas
	 * @return ArrayList<Persona>
	 */
	ArrayList<Persona> listar();
	
	/**
	 * Listado vehiculos de una persona
	 * @param idPersona identeificador de la Persona
	 * @return listado ArrayList<Vehiculo>
	 */
	ArrayList<Vehiculo> vehiculos(long idPersona);
}
