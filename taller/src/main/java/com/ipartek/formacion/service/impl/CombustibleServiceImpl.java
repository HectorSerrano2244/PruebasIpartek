package com.ipartek.formacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.modelo.dao.CombustibleDAO;
import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.taller.service.CombustibleService;
import com.ipartek.formacion.taller.service.exception.CombustibleException;

@Service
public class CombustibleServiceImpl implements CombustibleService {

	@Autowired
	CombustibleDAO daoCombustible;
	
	@Override
	public List<Combustible> listar() {
		return null;
	}

	@Override
	public Combustible detalle(long id) {
		return null;
	}

	@Override
	public boolean eliminar(long id) throws CombustibleException {
		return false;
	}

	@Override
	public boolean crear(Combustible combustible) throws CombustibleException {
		return false;
	}

	@Override
	public boolean modificar(Combustible combustible) throws CombustibleException {
		return false;
	}

}
