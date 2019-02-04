package com.ipartek.formacion.service.impl;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.modelo.dao.PersonaDAO;
import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.modelo.pojo.Vehiculo;
import com.ipartek.formacion.taller.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	//Instancia e implementa patron singleton - inyección de dependencias
	@Autowired
	PersonaDAO daoPersona;
	
	@Override
	public ArrayList<Persona> listar() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		personas.addAll(daoPersona.getAll().values());
		return personas;
	}

	@Override
	public ArrayList<Vehiculo> vehiculos(long idPersona) {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos.addAll(daoPersona.getAllVehiculosByUser().values());
		return null;
	}

}
