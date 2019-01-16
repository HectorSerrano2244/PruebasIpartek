package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.modelo.daos.LoginDAO;
import com.ipartek.formacion.modelo.pojo.Agente;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDAO daoLogin;
	Agente a = null;
	String placa = "";
	String password = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoLogin = LoginDAO.getInstance();
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
		
		HttpSession session = request.getSession();
		a = daoLogin.getById(4); // Busca el agente mediante su id
		session.setAttribute("agenteLogueado", a); // Pasa el objeto agente como atributo de sesión
		
		request.setAttribute("titulo", "Menú | App Multas");
		request.getRequestDispatcher("principal.jsp").forward(request, response);		
	}
}