package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.modelo.daos.CocheDAO;
import com.ipartek.formacion.modelo.daos.MultaDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Coche;

/**
 * Servlet implementation class MultasController
 */
@WebServlet("/privado/multas")
public class MultasController extends HttpServlet {
	private static final String VISTA_INDEX = "multas/index.jsp";
	private static final String VISTA_FORM = "multas/form.jsp";
	private static final String VISTA_BUSCAR = "multas/buscar.jsp";

	private static final long serialVersionUID = 1L;

	private MultaDAO daoMulta;
	private CocheDAO daoCoche;

	private String op = null;
	Agente a = null;
	String vista = "";
	String multaStr = "";
	String mat = "";
	Coche c = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		daoMulta = MultaDAO.getInstance();
		daoCoche = CocheDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getParameters(request);

		switch (op) {
		case "ver":
			if (multaStr == null) {
				request.setAttribute("multas", daoMulta.getAllUsu(a.getId()));
				vista = VISTA_INDEX;
			} else {
				long multa = Long.parseLong(multaStr);
				request.setAttribute("multa", daoMulta.getById(multa));
				vista = VISTA_FORM;
			}
			break;
		case "ir_a":
			vista = VISTA_BUSCAR;
			break;
		case "buscar":
			c = daoCoche.getByMatri(mat);
			if (c != null) {
				request.setAttribute("coche", c);
				vista = VISTA_FORM;
			} else {
				request.setAttribute("mensaje", "caca");
				vista = VISTA_BUSCAR;
			}
			break;
		case "multar":
			request.setAttribute("coches", daoCoche.getMatriculas());
			break;
		}
		request.setAttribute("op", op);
		request.getRequestDispatcher(vista).forward(request, response);
	}

	private void getParameters(HttpServletRequest request) {
		HttpSession session = request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");
		op = request.getParameter("op");
		multaStr = request.getParameter("multa");
		mat = request.getParameter("matricula");
		if (op == null) {
			op = "ver";
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
