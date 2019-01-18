package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.modelo.daos.EstadisticasDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Estadisticas;

@WebServlet("/privado/estadisticas")
public class StatsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Estadisticas> estadisticasActuales;
	ArrayList<Estadisticas> estadisticasCombo;
	double objetivoAnualActual = 12000;
	double objetivoAnualCombo = objetivoAnualActual;
	double totalActual = 0;
	double totalActualCombo = 0;
	Object totalMes = "";
	private EstadisticasDAO daoEstadisticas;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoEstadisticas = EstadisticasDAO.getInstance();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Agente a = (Agente) session.getAttribute("agenteLogueado");

		int anyoActual = fAnyoActual();
		int anyoCombo;
		try {
			anyoCombo = Integer.parseInt(request.getParameter("anyoCombo"));
		} catch (NumberFormatException e) {
			anyoCombo = anyoActual;
		}

		estadisticasCombo = daoEstadisticas.getEstadisticas(request, a.getId(), anyoCombo);

		totalActual = totalAnual(false);
		totalActualCombo = totalAnual(true);
		totalMes = request.getAttribute("totalMes");

		request.setAttribute("totalMesActual", new Double(totalMes.toString()));

		request.setAttribute("anyoActual", anyoActual);

		request.setAttribute("totalAnualActual", totalActual);

		request.setAttribute("objetivoAnualActual", objetivoAnualActual);

//		request.setAttribute("objetivoAnualActual", estadisticasActuales);

		request.setAttribute("objetivo", estadisticasCombo);

		request.getRequestDispatcher("multas/estadisticas.jsp").forward(request, response);
	}

	private int fAnyoActual() {
		Calendar fecha = Calendar.getInstance();
		int anyo = fecha.get(Calendar.YEAR);
		return anyo;
	}

	private double totalAnual(boolean isCombo) {
		totalActual = 0;
		for (Estadisticas e : estadisticasCombo ) {
			totalActual += e.getImporte();
		}
		return totalActual;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
