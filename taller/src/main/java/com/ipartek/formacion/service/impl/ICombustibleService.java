package com.ipartek.formacion.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.modelo.dao.CombustibleDAO;
import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.taller.service.CombustibleService;
import com.ipartek.formacion.taller.service.exception.CombustibleException;

@Service
public class ICombustibleService implements CombustibleService {

	@Autowired
	CombustibleDAO daoCombustible;
	
	@Autowired
	Validator validator;
	
	@Override
	public List<Combustible> listar() {
		return daoCombustible.getAll();
	}

	@Override
	public Combustible detalle(long id) {
		return daoCombustible.detalle(id);
	}

	@Override
	public boolean eliminar(long id) throws CombustibleException {
		boolean r = false;
		try {
			r = daoCombustible.eliminar(id);
		}
		catch (Exception e) {
			throw new CombustibleException(CombustibleException.EXCEPTION_CONSTRAINT);
		}
		return r;
	}

	@Override
	public boolean crear(Combustible combustible) throws CombustibleException {
		boolean r = false;
		try {
			
			Set<ConstraintViolation<Combustible>> violations = validator.validate(combustible);
			if (violations.isEmpty()) {			
				r = daoCombustible.crear(combustible);				
			}else {
				throw new CombustibleException(CombustibleException.EXCEPTION_VIOLATIONS, violations );
			}	
		} catch (Exception e) {			
			throw new CombustibleException(CombustibleException.EXCEPTION_CONSTRAINT );
		}			
		return r;
	}

	@Override
	public boolean modificar(Combustible combustible) throws CombustibleException {
		boolean r = false;
		try {
			Set<ConstraintViolation<Combustible>> violations = validator.validate(combustible);
			if(violations.isEmpty()) {
				r = daoCombustible.modificar(combustible);
			}
			else {
				throw new CombustibleException(CombustibleException.EXCEPTION_VIOLATIONS, violations);
			}
		} catch (Exception e) {
			throw new CombustibleException(CombustibleException.EXCEPTION_EXIST);
		}
		return r;
	}

}
