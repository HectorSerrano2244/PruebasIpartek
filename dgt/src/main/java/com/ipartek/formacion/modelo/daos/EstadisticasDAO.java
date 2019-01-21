package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Estadistica;

public class EstadisticasDAO {
	private final static Logger LOG = Logger.getLogger(EstadisticasDAO.class);
	private static EstadisticasDAO INSTANCE = null;
	private static final String SQL_OBJETIVO_ANUAL = "SELECT objetivo FROM objetivo where Anio=?;";
	private static final String SQL_DEVUELVE_TOTALES = "{call pa_multa_devuelveTotales(?,?,?,?,?)}";
	private static final String SQL_TOTAL_MESES_ANIO = "SELECT ROUND(SUM(importe), 2) as recaudacion, MONTH(fecha_alta) AS mes FROM	multa WHERE	id_agente = ? AND YEAR(fecha_alta) = ? 	AND fecha_baja IS NULL GROUP BY mes ORDER BY mes;";
	private ArrayList<Estadistica> estadisticas;
	private Estadistica estadistica;

	private EstadisticasDAO() {
		super();
	}

	public synchronized static EstadisticasDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new EstadisticasDAO();
		}
		return INSTANCE;
	}

	public Integer getObjetivoAnual(int Anio) {
		Integer objetivo = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_OBJETIVO_ANUAL)) {
			pst.setLong(1, Anio);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					objetivo = rs.getInt("Objetivo");
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return objetivo;
	}

	public Float getTotales(String opcion, Long idAgente, int Mes, int Anio) throws SQLException {
		Float recaudacion = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_DEVUELVE_TOTALES);) {

			cs.setString(1, opcion);
			cs.setLong(2, idAgente);
			cs.setInt(3, Anio);
			cs.setInt(4, Mes);
			cs.registerOutParameter(5, Types.FLOAT);
			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				recaudacion = cs.getFloat(5);
			}

		}
		return recaudacion;
	}

	public ArrayList<Estadistica> getMesesAnio(Long idagente, int anyo) {
		estadisticas = new ArrayList<Estadistica>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_TOTAL_MESES_ANIO)) {
			pst.setLong(1, idagente);
			pst.setInt(2, anyo);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					estadistica = new Estadistica();
					estadistica.setMes(rs.getInt("mes"));
					estadistica.setImporte(rs.getFloat("recaudacion"));
					estadisticas.add(estadistica);
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return estadisticas;
	}
}
