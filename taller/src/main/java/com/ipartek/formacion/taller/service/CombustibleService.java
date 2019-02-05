package com.ipartek.formacion.taller.service;

import java.sql.SQLException;
import java.util.List;

import com.ipartek.formacion.modelo.pojo.Combustible;
import com.ipartek.formacion.taller.service.exception.CombustibleException;

public interface CombustibleService {
	
	/**
	 * Listado Combustible ordenado por id descendente
	 * @return List<Combustible>, Lista vacia si no hay datos
	 */
	List<Combustible> listar();
	
	/**
	 * Obtener detalle Combustible
	 * @param id identificador del Combustible
	 * @return si encuentra <b>Combustible</b>, si no <b>null</b>
	 */
	Combustible detalle(long id);
	
	/**
	 * Elimina Combustible por su id
	 * @param id identificador del Combustible
	 * @return <b>true</b> si lo elimina, <b>false</b> si no lo encuentra
	 * @throws CombustibleException 
	 * @see CombustibleException.EXCEPTION_CONSTRAINT
	 */
	boolean eliminar(long id) throws CombustibleException;
	
	/**
	 * Crea un nuevo Combustible, al crear con exito actualiza el <b>id</b>
	 * @param combustible Combustible a crear
	 * @return <b>true</b> si lo crea <b>false</b> si no lo puede crear. <b>Nombre:</b> mínimo una letra y máximo 45
	 * @throws CombustibleException @see CombustibleException.EXCEPTION_EXIST
	 */
	boolean crear(Combustible combustible) throws CombustibleException;
	
	/**
	 * Modifica el <b>nombre</b> de un combustible identificado por su <b>id</b>
	 * @param combustible Combustible que queremos modificar
	 * @return <b>true</b> si lo modifica, <b>false</b> si no lo encuentra
	 * @throws CombustibleException @see CombustibleException.EXCEPTION_EXIST
	 */
	boolean modificar(Combustible combustible) throws CombustibleException;
}
