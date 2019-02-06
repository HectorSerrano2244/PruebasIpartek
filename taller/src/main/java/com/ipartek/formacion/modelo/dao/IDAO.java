package com.ipartek.formacion.modelo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para que los daos implementen las operaciones básicas de CRUD
 * @author Curso
 */
public interface IDAO<P> {
	List<P> getAll();
	
	P detalle(long id);
	
	boolean crear(P pojo);
	
	boolean modificar(P pojo) throws SQLException;
	
	boolean eliminar(long id) throws SQLException;
	
//	P rowMapper(ResultSet rs) throws SQLException;
}
