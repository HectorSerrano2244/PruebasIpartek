package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.daos.EstadisticasDAO;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Estadistica;
import com.ipartek.formacion.modelo.pojo.EstadisticaExtendida;

@WebServlet("/privado/estadisticas")
public class StatsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(StatsController.class);
	private static final String VISTA_OBJETIVOS = "multas/estadisticas.jsp";
	private Agente agente = new Agente();
	private HttpSession session;
	private String aniocombo = "";
	ArrayList<Estadistica> estadisticasMesesAnio;

	private Calendar fechasistema;
	private EstadisticasDAO estadisticasDAO;
	private int enteroaniocombo;

	private static final ArrayList<String> MESES = new ArrayList<String>(Arrays.asList("Enero", "Febrero", "Marzo",
			"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		estadisticasDAO = EstadisticasDAO.getInstance();
		fechasistema = Calendar.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	private void getParameters(HttpServletRequest request) {
		session = request.getSession();
		agente = (Agente) session.getAttribute("agenteLogueado");
		aniocombo = request.getParameter("anyocombo");
		if (aniocombo == null) {
			enteroaniocombo = fechasistema.get(Calendar.YEAR);
			aniocombo = String.valueOf(enteroaniocombo);
		}
	}

	private ArrayList<EstadisticaExtendida> construirArrayCompleto() {
		ArrayList<EstadisticaExtendida> listacompleta = new ArrayList<EstadisticaExtendida>();
		ArrayList<Estadistica> listainicial;
		listainicial = (ArrayList<Estadistica>) estadisticasDAO.getMesesAnio(agente.getId(),
				Integer.parseInt(aniocombo));
		for (int i = 0; i < 12; i++) {
			Estadistica linea = new Estadistica(); // Objeto con el número del mes (linea)
			EstadisticaExtendida lineacompleta = new EstadisticaExtendida(); // Objecto con el nombre del mes (lineacompleta)
			try {
				linea = listainicial.get(i);
				lineacompleta.setMes(linea.getMes()); 
				lineacompleta.setImporte(Float.parseFloat(String.valueOf(linea.getImporte())));
				lineacompleta.setNombremes(MESES.get(i));

			} catch (Exception e) {
				lineacompleta.setMes(i);
				lineacompleta.setImporte(0F);
				lineacompleta.setNombremes(MESES.get(i));
			}
			listacompleta.add(lineacompleta);
		}
		return listacompleta;
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		int mesactual = fechasistema.get(Calendar.MONTH) + 1;
		int anioactual = fechasistema.get(Calendar.YEAR);
		String mesactualStr = "0";
		if (mesactual < 10) {
			 mesactualStr += String.valueOf(mesactual);
		}
		else {
			mesactualStr = String.valueOf(mesactual);
		}
		getParameters(request);
		request.setAttribute("objetivoAnualActual", estadisticasDAO.getObjetivoAnual(anioactual));
		request.setAttribute("totalMesActual",
				estadisticasDAO.getTotales("totalMensualAnio", agente.getId(), mesactual, anioactual));
		request.setAttribute("totalAnualActual",
				estadisticasDAO.getTotales("totalAnual", agente.getId(), 0, anioactual));
		request.setAttribute("anyoActual", anioactual);
		request.setAttribute("anyocombo", aniocombo);
		request.setAttribute("totalAnualCombo",
				estadisticasDAO.getTotales("totalAnual", agente.getId(), 0, Integer.parseInt(aniocombo)));
		request.setAttribute("objetivoAnioCombo", estadisticasDAO.getObjetivoAnual(Integer.parseInt(aniocombo)));
		request.setAttribute("objetivo", construirArrayCompleto());
		request.setAttribute("titulo", "Estadísticas "+anioactual+"-"+mesactualStr+"| App Multas");
		request.getRequestDispatcher(VISTA_OBJETIVOS).forward(request, response);

	}
}
