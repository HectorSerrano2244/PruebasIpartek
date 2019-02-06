package com.ipartek.formacion.taller.service;

import java.util.List;

import com.ipartek.formacion.modelo.pojo.Persona;
import com.ipartek.formacion.taller.service.exception.PersonaException;

public interface PersonaService {
	
	/**
	 * Listado persona ordenado por id descendente
	 * @return List<Persona>, Lista vacia si no hay datos
	 */
	List<Persona> listar();
	
	/**
	 * Obtener detalle persona
	 * @param id identificador del persona
	 * @return si encuentra <b>Persona</b>, si no <b>null</b>
	 */
	Persona detalle(long id);
	
	/**
	 * Elimina persona por su id
	 * @param id identificador del persona
	 * @return <b>true</b> si lo elimina, <b>false</b> si no lo encuentra
	 * @throws personaException 
	 * @see personaException.EXCEPTION_CONSTRAINT
	 */
	boolean eliminar(long id) throws PersonaException;
	
	/**
	 * Crea un nuevo persona, al crear con exito actualiza el <b>id</b>
	 * @param persona persona a crear
	 * @return <b>true</b> si lo crea <b>false</b> si no lo puede crear. <b>Nombre:</b> mínimo una letra y máximo 45
	 * @throws personaException Si el persona ya existe o hay un error en la validación 
	 * @see personaException.EXCEPTION_EXIST
	 */
	boolean crear(Persona persona) throws PersonaException;
	
	/**
	 * Modifica el <b>nombre</b> de un persona identificado por su <b>id</b>
	 * @param persona persona que queremos modificar
	 * @return <b>true</b> si lo modifica, <b>false</b> si no lo encuentra
	 * @throws personaException @see personaException.EXCEPTION_EXIST
	 */
	boolean modificar(Persona persona) throws PersonaException;
}
