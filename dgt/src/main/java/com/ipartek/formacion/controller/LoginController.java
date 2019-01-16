package com.ipartek.formacion.controller;

import java.io.IOException;
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

import com.ipartek.formacion.modelo.daos.LoginDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Mensaje;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	
	private Mensaje mensaje;
	
	private ValidatorFactory factory;
	private Validator validator;
	
	private LoginDAO daoLogin;
	Agente a = null;
	String placa = "";
	String password = "";
	String vista = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoLogin = LoginDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);	
	}
	
	private void getParameters(HttpServletRequest request) {
		placa = request.getParameter("placa");
		password = request.getParameter("password");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getParameters(request);
		try {
			a = daoLogin.login(Integer.parseInt(placa), password);
			Set<ConstraintViolation<Agente>> violations = validator.validate(a);
			if (violations.size() > 0) {
				String errores = "<ul class='list-unlisted'>";
				for (ConstraintViolation<Agente> violation : violations) {
					errores += "<li>" + violation.getPropertyPath() + ": " + violation.getMessage() + "</li>";
				}
				errores += "</ul>";
				mensaje = new Mensaje(Mensaje.TIPO_DANGER, errores);
				LOG.debug(mensaje.getTexto());
				request.setAttribute("placa", placa);
				request.setAttribute("pasword", password);
				vista = "login.jsp";
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("agenteLogueado", a);
				request.setAttribute("titulo", "Menú | App Multas");
				vista = "principal.jsp";
			}
		}
		catch (Exception e) {
			LOG.error(e);
			mensaje = new Mensaje(Mensaje.TIPO_WARNING, e.getMessage());
			vista = "login.jsp";
		}
		request.getRequestDispatcher(vista).forward(request, response);	
	}
}