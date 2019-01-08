package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.daos.AgenteDAO;
import com.ipartek.formacion.modelo.pojo.Agente;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	private ValidatorFactory factory;
	private Validator validator;
	private AgenteDAO daoAgente;
	Agente a = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		daoAgente = AgenteDAO.getInstance();
		a = daoAgente.getById(4);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("agenteLogueado", a);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
}