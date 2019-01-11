package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Mensaje;
import com.ipartek.formacion.modelo.pojo.Multa;

/**
 * Servlet implementation class MultasController
 */
@WebServlet("/privado/multas")
public class MultasController extends HttpServlet {
	private final static Logger LOG = Logger.getLogger(MultasController.class);
	private Mensaje mensaje;
	private static final String VISTA_PRAL = "../index.jsp";
	private static final String VISTA_INDEX = "multas/index.jsp";
	private static final String VISTA_FORM = "multas/form.jsp";
	private static final String VISTA_BUSCAR = "multas/buscar.jsp";
	
	private ValidatorFactory factory;
	private Validator validator;
	
	private static final long serialVersionUID = 1L;

	private MultaDAO daoMulta;
	private CocheDAO daoCoche;

	private String op = null;
	String opm = "";
	
	// Parametros
	String imp = "";
	String concep = "";
	String id_coche = "";
	String vista = "";
	String multaStr = "";
	String mat = "";
	
	// Objectos
	Agente a = null;
	Coche c = null;
	Multa m = null;

	HttpSession session;
	
	/**
	 * Método de inicio de servidor que declara el validador
	 * Declaración de los 'Data Access Object' de los objetos multa y coche
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		daoMulta = MultaDAO.getInstance();
		daoCoche = CocheDAO.getInstance();
	}
	
	/**
	 * Métodos 'doGet' y 'doPost' que llaman al método principal 'doProcess'
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	/**
	 * Método con el proceso principal de la aplicación que llama a los demás métodos
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getParameters(request);
		switch (op) {
		case "ver":
			opVer(request);
			break;
		case "ir_a":
			opIrA();
			break;
		case "buscar":
			opBuscar(request);
			break;
		case "multar":
			opMultar();
			break;
		case "anular":
			opAnular(request);
			break;
			default: 
				mensaje = new Mensaje(Mensaje.TIPO_DANGER, "Operación incorrecta");
				vista = VISTA_PRAL;
				LOG.debug(mensaje.getTexto());
				break;
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("op", op);
		request.setAttribute("opm", opm);
		LOG.debug("Mostrar la vista");
		request.getRequestDispatcher(vista).forward(request, response);
	}
	/**
	 * Recoge todos los parámetros al llevarnos a la vista para:
	 * 1. Buscar matrículas
	 * 2. Rellenar la lista de multas de un policía
	 * 3. Rellenar la lista de multas anuladas de un policía
	 * 4. Mostrar el formulario con los datos de una multa sacada de cualquiera
	 * de las 2 listas
	 * 
	 * Parametros:
	 * 
	 * - a 
	 * a es el objeto agente en session 'agenteLogueado'.
	 * - op
	 * La variable de operación que se usará para llamar al resto de métodos
	 * - opm
	 * 
	 */
	private void getParameters(HttpServletRequest request) {
		session = request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");
		try {
			op = request.getParameter("op");
			if (op == null) {
				op = "ver";
			}
			opm = request.getParameter("opm");
			multaStr = request.getParameter("multa");
			mat = request.getParameter("matricula");
			imp = request.getParameter("importe");
			concep = request.getParameter("concepto");
			id_coche = request.getParameter("idcoche");
			LOG.debug("Parametros recuperados satisfactoriamente");
		} catch (Exception e) {
			mensaje = new Mensaje(Mensaje.TIPO_DANGER, "Parámetros introducidos incorrectos");
			vista = VISTA_PRAL;
			LOG.error(mensaje.getTexto());
		}
	}
	/**
	 * En caso de tener o no una multa almacenada en 'multaStr':
	 * - Nos retorna el DAO de las multas que ha puesto un agente identificado por su id
	 * - Nos retorna el objeto multa que contiene una multa en concreto
	 * En caso de excepción:
	 * 
	 */
	private void opVer(HttpServletRequest request) {
		if (multaStr == null) {
			try {
				request.setAttribute("multas", daoMulta.getAllUsu(a.getId(), opm));
				LOG.info("Buscando todas las multas puestas por el agente");
			}
			catch (Exception e) {
				mensaje = new Mensaje(Mensaje.TIPO_DANGER, "No es posible multar al coche. Revise el importe y el concepto");
				LOG.error(mensaje.getTexto());
			}
			vista = VISTA_INDEX;
		} else {
			long multa = Long.parseLong(multaStr);
			request.setAttribute("multa", daoMulta.getById(multa));
			vista = VISTA_FORM;
			LOG.info("Información de la multa "+multa);
		}
	}
	
	/**
	 * Cuando queramos multar un coche, este método nos conducirá a un buscador de matriculas de coche.
	 * Se compone de un pequeño formulario con un input donde introducir la matrícula
	 * Se trata del método anterior a 'opBuscar'
	 */
	private void opIrA() {
		mensaje = new Mensaje(Mensaje.TIPO_INFO, "Introduzca una matrícula");
		vista = VISTA_BUSCAR;
		LOG.info("Accediendo a la busqueda de matriculas");
	}
	
	/**
	 *  Obtiene la matricula y guarda el objeto del coche en la variable 'c'.
	 *  Nos enviará un mensaje de exito al formulario si existe
	 *  Si no, nos avisará de que no se ha encontrado ninguna matricula en el formulario de búsqueda
	 *  Se trata del método posterior a 'opIrA'
	 */
	private void opBuscar(HttpServletRequest request) {
		try {
			c = daoCoche.getByMatri(mat);
			LOG.debug("Matricula conseguida");
		} catch (Exception e) {
			mensaje = new Mensaje(Mensaje.TIPO_DANGER, "No es posible multar al coche revise el importe y el concepto");
			LOG.warn(mensaje.getTexto(), e);
		}
		if (c != null) {
			request.setAttribute("coche", c);
			request.setAttribute("fecha", new Date());
			vista = VISTA_FORM;
			LOG.debug("Enviando datos de un coche");
		} else {
			mensaje = new Mensaje(Mensaje.TIPO_DANGER, "La matrícula no existe");
			vista = VISTA_BUSCAR;
			LOG.warn(mensaje.getTexto());
		}
	}
	
	private void opMultar() {
		m = new Multa();
		c = new Coche();
		m.setImporte(Float.parseFloat(imp));
		m.setConcepto(concep);
		c.setId(Long.parseLong(id_coche));
		m.setCoche(c);
		m.setAgente((Agente) session.getAttribute("agenteLogueado"));
		Set<ConstraintViolation<Multa>> violations = validator.validate(m);
		if (violations.size() > 0) {
			String errores = "<ul class='list-unlisted'>";
			for (ConstraintViolation<Multa> violation : violations) {
				errores += "<li>" + violation.getPropertyPath() + ": " + violation.getMessage() + "</li>";
			}
			vista = VISTA_BUSCAR;
			errores += "</ul>";
			mensaje = new Mensaje(Mensaje.TIPO_DANGER, errores);
			LOG.debug(mensaje.getTexto());
		}
		else {
			LOG.debug("No hay violaciones. Se procede a crear una multa");
			try {
				if (daoMulta.insert(m)) {
					mensaje = new Mensaje(Mensaje.TIPO_SUCCESS, "Coche multado");
					vista = VISTA_PRAL;
					LOG.debug(mensaje.getTexto());
				} else {
					mensaje = new Mensaje(Mensaje.TIPO_WARNING, "No es posible multar al coche revise el importe y el concepto");
					vista = VISTA_FORM;
					LOG.debug(mensaje.getTexto());
				}
			} catch (SQLException e) {
				mensaje = new Mensaje(Mensaje.TIPO_DANGER, "Parámetros incorrectos");
				vista = VISTA_FORM;
				LOG.debug(mensaje.getTexto(), e);
			}
		}
	}

	private void opAnular(HttpServletRequest request) {
		try {
			request.setAttribute("multa", daoMulta.update(daoMulta.getById(Long.parseLong(multaStr))));
			op = "ver";
			opm = "baja";
			vista = VISTA_INDEX;
		} catch (SQLException e) {
			LOG.error("No se puede anular la multa", e);
		}
	}
}
