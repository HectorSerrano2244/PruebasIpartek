package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Agente;

/**
 * Servlet implementation class MultasController
 */
@WebServlet("/privado/multas")
public class MultasController extends HttpServlet {
	private static final String VISTA_INDEX = "multas/index.jsp";
	private static final long serialVersionUID = 1L;
	private MultaDAO daoMulta;
	private String op = null;
	Agente a = null;
	String vista = "";
	
	@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			super.init(config);
			daoMulta = MultaDAO.getInstance();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getParameters(request);
		
		switch (op) {
			default:
				request.setAttribute("multas", daoMulta.getAllUsu(a.getId()));
				vista = VISTA_INDEX;
				break;
		}
		request.getRequestDispatcher(vista).forward(request, response);
	}

	private void getParameters(HttpServletRequest request) {
		HttpSession session = request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");
		op = request.getParameter("op");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
