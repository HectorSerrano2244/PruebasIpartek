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

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		daoMulta = MultaDAO.getInstance();
		daoCoche = CocheDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getParameters(request);

		switch (op) {
		case "ver":
			if (multaStr == null) {
				request.setAttribute("multas", daoMulta.getAllUsu(a.getId(), opm));
				vista = VISTA_INDEX;
				LOG.info("Buscando todas las multas puestas por el usuario");
			} else {
				long multa = Long.parseLong(multaStr);
				request.setAttribute("multa", daoMulta.getById(multa));
				vista = VISTA_FORM;
				LOG.info("Información de la multa"+multa);
			}
			break;
		case "ir_a":
			mensaje = new Mensaje(Mensaje.TIPO_INFO, "Introduzca una matrícula");
			vista = VISTA_BUSCAR;
			LOG.info("Accediendo a la busqueda de matriculas");
			break;
		case "buscar":
			try {
				c = daoCoche.getByMatri(mat);
				LOG.debug("Matricula conseguida");
			} catch (Exception e) {
				mensaje = new Mensaje(Mensaje.TIPO_DANGER, "No es posible multar al coche revise el importe y el concepto");
				LOG.warn(mensaje.getTexto());
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
			break;
		case "multar":
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
					LOG.debug(mensaje.getTexto());
				}
			}
			break;
		case "anular":
			try {
				request.setAttribute("multa", daoMulta.update(daoMulta.getById(Long.parseLong(multaStr))));
				op = "ver";
				opm = "baja";
				vista = VISTA_INDEX;
			} catch (SQLException e) {
				LOG.error(e);
			}
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
