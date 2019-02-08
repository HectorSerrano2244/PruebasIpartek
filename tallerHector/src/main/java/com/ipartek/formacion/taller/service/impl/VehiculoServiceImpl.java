package com.ipartek.formacion.taller.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.taller.modelo.dao.CombustibleDAO;
import com.ipartek.formacion.taller.modelo.dao.ModeloDAO;
import com.ipartek.formacion.taller.modelo.dao.PersonaDAO;
import com.ipartek.formacion.taller.modelo.dao.VehiculoDAO;
import com.ipartek.formacion.taller.modelo.pojo.Vehiculo;
import com.ipartek.formacion.taller.modelo.pojo.validactions.VehiculosPostCheck;
import com.ipartek.formacion.taller.service.VehiculoService;
import com.ipartek.formacion.taller.service.exception.VehiculoException;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	VehiculoDAO vehiculoDAO;

	@Autowired
	CombustibleDAO combustibleDAO;

	@Autowired
	PersonaDAO propietarioDAO;

	@Autowired
	ModeloDAO modeloDAO;
	
	@Autowired
	Validator validator;

	@Override
	public List<Vehiculo> listar() {

		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos = (ArrayList<Vehiculo>) vehiculoDAO.getAll();
		return vehiculos;
	}

	@Override
	public Vehiculo detalle(int idVehiculo) {
		return null;
	}

	@Override
	public boolean eliminar(int idVehiculo) {
		return false;
	}

	@Override
	public boolean crear(Vehiculo vehiculo) throws VehiculoException {
		boolean isCreado = false;
			Set<ConstraintViolation<Vehiculo>> violations = validator.validate(vehiculo, VehiculosPostCheck.class);
			if (violations.isEmpty()) {
				try {
					isCreado = vehiculoDAO.insert(vehiculo);
					if (isCreado) {
						vehiculo.setModelo(modeloDAO.getById(vehiculo.getModelo().getId()));
						vehiculo.setCombustible(combustibleDAO.getById(vehiculo.getCombustible().getId()));
						vehiculo.setPropietario(propietarioDAO.getById(vehiculo.getPropietario().getId()));
					}
				}
				catch (Exception e) {
					throw new VehiculoException(e.getMessage());
				}
			}
			else {
				throw new VehiculoException(violations);
			}
		return isCreado;
	}

	@Override
	public boolean modificar(Vehiculo vehiculo) {
		return false;
	}

}
