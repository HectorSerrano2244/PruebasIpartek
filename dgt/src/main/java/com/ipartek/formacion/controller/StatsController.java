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
	ArrayList<Estadisticas> estadisticas;
	double objetivoAnual = 12000;
	double total = 0;
	Object totalMes = "";
	private EstadisticasDAO daoEstadisticas;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoEstadisticas = EstadisticasDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Agente a = (Agente) session.getAttribute("agenteLogueado");
		int anyo = anyoActual();
		estadisticas = daoEstadisticas.getEstadisticas(request, a.getId(), anyo); // Año actual para agente en sesión
		total = totalAnual();
		totalMes = request.getAttribute("totalMes");
		request.setAttribute("totalMes", new Double(totalMes.toString()));
		request.setAttribute("anyoActual", anyo);
		request.setAttribute("totalAnual", total);
		request.setAttribute("objetivoAnual", objetivoAnual);
		request.setAttribute("objetivo", estadisticas);
		request.getRequestDispatcher("multas/estadisticas.jsp").forward(request, response);
	}

	private int anyoActual() {
		Calendar fecha = Calendar.getInstance();
        int anyo = fecha.get(Calendar.YEAR);
		return anyo;
	}

	private double totalAnual() {
		total = 0;
		for(Estadisticas e : estadisticas) {
            total += e.getImporte();
        }
		return total;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
