package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
import com.ipartek.formacion.modelo.pojo.Multa;

/**
 * Servlet implementation class MultasController
 */
@WebServlet("/privado/multas")
public class MultasController extends HttpServlet {
	private static final String VISTA_PRAL = "../index.jsp";
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
	String imp="";
	String concep="";
	String id_coche="";
	HttpSession session;
	String mensaje="";
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
			request.setAttribute("mensaje", null);
			vista = VISTA_BUSCAR;
			break;
		case "buscar":
			c = daoCoche.getByMatri(mat);
			if (c != null) {
				request.setAttribute("coche", c);
				request.setAttribute("fecha", new Date());
				vista = VISTA_FORM;
			} else {
				request.setAttribute("mensaje", "caca");
				vista = VISTA_BUSCAR;
			}
			break;
		case "multar":
			Multa m=new Multa();
			Coche c= new Coche();
			Agente a;
			Long id_agente;
			m.setImporte(Float.parseFloat(imp));
			m.setConcepto(concep);
			c.setId(Long.parseLong(id_coche));
			m.setCoche(c);
			m.setAgente((Agente) session.getAttribute("agenteLogueado"));
			try {
				if(daoMulta.insert(m)) {
					mensaje="ok";
					vista = VISTA_PRAL;
				}else {
					mensaje="ko";
					vista = VISTA_FORM;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("op", op);
		request.getRequestDispatcher(vista).forward(request, response);
	}

	private void getParameters(HttpServletRequest request) {
		session=request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");
		op = request.getParameter("op");
		multaStr = request.getParameter("multa");
		mat = request.getParameter("matricula");
		imp = request.getParameter("importe");
		concep = request.getParameter("concepto");
		id_coche=request.getParameter("idcoche");
		if (op == null) {
			op = "ver";
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
