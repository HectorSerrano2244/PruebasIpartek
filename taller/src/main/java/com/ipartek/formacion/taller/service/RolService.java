package com.ipartek.formacion.taller.service;

import java.util.List;

import com.ipartek.formacion.modelo.pojo.Rol;
import com.ipartek.formacion.taller.service.exception.RolException;

public interface RolService {
	
	/**
	 * Listado Rol ordenado por id descendente
	 * @return List<Rol>, Lista vacia si no hay datos
	 */
	List<Rol> listar();
	
	/**
	 * Obtener detalle Rol
	 * @param id identificador del Rol
	 * @return si encuentra <b>Rol</b>, si no <b>null</b>
	 */
	Rol detalle(long id);
	
	/**
	 * Elimina Rol por su id
	 * @param id identificador del Rol
	 * @return <b>true</b> si lo elimina, <b>false</b> si no lo encuentra
	 * @throws RolException 
	 * @see RolException.EXCEPTION_CONSTRAINT
	 */
	boolean eliminar(long id) throws RolException;
	
	/**
	 * Crea un nuevo Rol, al crear con exito actualiza el <b>id</b>
	 * @param Rol Rol a crear
	 * @return <b>true</b> si lo crea <b>false</b> si no lo puede crear. <b>Nombre:</b> mínimo una letra y máximo 45
	 * @throws RolException @see RolException.EXCEPTION_EXIST
	 */
	boolean crear(Rol rol) throws RolException;
	
	/**
	 * Modifica el <b>nombre</b> de un Rol identificado por su <b>id</b>
	 * @param Rol Rol que queremos modificar
	 * @return <b>true</b> si lo modifica, <b>false</b> si no lo encuentra
	 * @throws RolException @see RolException.EXCEPTION_EXIST
	 */
	boolean modificar(Rol rol) throws RolException;
}
