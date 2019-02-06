package com.ipartek.formacion.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.modelo.dao.RolDAO;
import com.ipartek.formacion.modelo.pojo.Rol;
import com.ipartek.formacion.taller.service.RolService;
import com.ipartek.formacion.taller.service.exception.CombustibleException;
import com.ipartek.formacion.taller.service.exception.RolException;

@Service
public class IRolService implements RolService {

	@Autowired
	RolDAO daoRol;
	
	@Autowired
	Validator validator;

	@Override
	public List<Rol> listar() {
		return daoRol.getAll();
	}

	@Override
	public Rol detalle(long id) {
		return daoRol.detalle(id);
	}

	@Override
	public boolean eliminar(long id) throws RolException {
		boolean r = false;
		try {
			r = daoRol.eliminar(id);
		}
		catch (Exception e) {
			throw new RolException(CombustibleException.EXCEPTION_CONSTRAINT);
		}
		return r;
	}

	@Override
	public boolean crear(Rol rol) throws RolException {
		return daoRol.crear(rol);
	}

	@Override
	public boolean modificar(Rol rol) throws RolException {
		boolean r = false;
		try {
			Set<ConstraintViolation<Rol>> violations = validator.validate(rol);
			if(violations.isEmpty()) {
				r = daoRol.modificar(rol);
			}
			else {
				throw new RolException(RolException.EXCEPTION_VIOLATIONS, violations);
			}
		} catch (Exception e) {
			throw new RolException(RolException.EXCEPTION_EXIST);
		}
		return r;
	}

}
