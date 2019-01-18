package com.ipartek.formacion.modelo.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Estadisticas;

public class EstadisticasDAO {
	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static EstadisticasDAO INSTANCE = null;
	private static final String SQL_GETESTADISTICAS = "SELECT MONTH(fecha_alta), SUM(importe) as 'recaudado' FROM multa WHERE id_agente = ? AND YEAR(fecha_alta) = ? GROUP BY YEAR(fecha_alta), MONTH(fecha_alta) ORDER BY MONTH(fecha_alta);";

	double totalMes = 0;
	
	public synchronized static EstadisticasDAO getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new EstadisticasDAO();
		}
		return INSTANCE;
	}
	
	public ArrayList<Estadisticas> getEstadisticas(HttpServletRequest request, Long idagente, int anyo) throws IOException {
		Properties prop = new Properties();        
        prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("meses.properties"));
       
        ArrayList<Estadisticas>estadisticas = new ArrayList<Estadisticas>();
        
        int fechaActual = mesActual();
       
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(SQL_GETESTADISTICAS)) {
            pst.setLong(1, idagente);
            pst.setInt(2, anyo);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                totalMes = 0;
                for(int i = 1; i <= 12; i++) { // i = posiciones de las filas. 1 = Enero, 2 = Febrero, etc.
                	try {
                        if (i == rs.getInt(1)) {  
                            // TODO redondear Floats a 2 decimales
                            estadisticas.add(new Estadisticas(prop.getProperty(String.valueOf(i)), rs.getFloat(2)));
                            if (fechaActual == rs.getInt(1)) {
                            	totalMes = rs.getFloat(2);
                            	request.setAttribute("totalMes", totalMes);
                            }
                            rs.next();
                        }
                        else {
                            estadisticas.add(new Estadisticas(prop.getProperty(String.valueOf(i))));
                        }
                    }
                    catch (Exception e) {
                        estadisticas.add(new Estadisticas(prop.getProperty(String.valueOf(i))));
                        if (i == 12 && totalMes == 0) {
                        	request.setAttribute("totalMes", 0);
                        }
                    }
                }
            }
            catch (Exception e) {
                LOG.error(e);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        
        return estadisticas;
    }

	private int mesActual() {
		Calendar fecha = Calendar.getInstance();
        int fechaActual = fecha.get(Calendar.MONTH);
        fechaActual++;
		return fechaActual;
	}
}
