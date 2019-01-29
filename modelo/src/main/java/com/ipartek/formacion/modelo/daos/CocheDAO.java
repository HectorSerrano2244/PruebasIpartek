package com.ipartek.formacion.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.cm.ConnectionManager;
import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDAO {

	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static CocheDAO INSTANCE = null;

	private static final String SQL_GETMATRICULA = "{call pa_coche_getByMatricula(?)}";
	private static final String SQL_GETBYID = "SELECT id, matricula, modelo, km FROM coche WHERE id = ?";

	// constructor privado, solo acceso por getInstance()
	private CocheDAO() {
		super();
	}

	public synchronized static CocheDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CocheDAO();
		}
		return INSTANCE;
	}

	private Coche rowMapper(ResultSet rs) throws SQLException {
		Coche coche = new Coche();
		coche.setId(rs.getLong("id"));
		coche.setMatricula(rs.getString("matricula"));
		coche.setModelo(rs.getString("modelo"));
		coche.setKm(rs.getInt("km"));
		return coche;
	}

	/**
	 * Obtenemos un Coche por su matrícula
	 * 
	 * @param matricula variable en String
	 * @return Si lo encuentra Coche con datos, null en caso contrario
	 */
	public Coche getByMatricula(String matricula) {
		Coche coche = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMATRICULA);) {

			cs.setString(1, matricula);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						coche = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return coche;
	}

	/**
	 * Obtener un coche por su id
	 * 
	 * @param id variable que identifica al coche
	 * @return Si existe obtiene los datos de un coche, si no se devuelve nulo
	 */
	public Coche getById(Long id) {
		Coche c = null;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement cs = conn.prepareStatement(SQL_GETBYID);) {

			cs.setLong(1, id);

			try (ResultSet rs = cs.executeQuery()) {
				try {
					while (rs.next()) {
						c = rowMapper(rs);
					}
				} catch (Exception e) {
					LOG.warn(e);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return c;
	}

	/**
	 * Todos los coches en una colección ordenador por id descendente y limitado a
	 * 100
	 * 
	 * @return si no existe ninguno colección inicializada a cero
	 * @throws SQLException
	 */
	public ArrayList<Coche> getAll() throws SQLException {

		ArrayList<Coche> coches = new ArrayList<Coche>();
		String sql = "SELECT * FROM coche ORDER BY id DESC LIMIT 100";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			try {
				while (rs.next()) {
					coches.add(rowMapper(rs));
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		return coches;
	}

	/**
	 * Introduce una nueva matrícula
	 * 
	 * @param coche
	 * @return true si se modifica, false en caso contrario
	 * @throws SQLException Si la matrícula ya existe
	 */

	public Coche insert(String matricula, String modelo, int km) throws SQLException {
		Coche coche = null;
		String sql = "INSERT INTO coche (matricula, modelo, km) VALUES (?, ?, ?);";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setString(1, matricula);
			pst.setString(2, modelo);
			pst.setInt(3, km);
			if (pst.executeUpdate() == 1) {
				coche = new Coche();
				coche.setMatricula(matricula);
				coche.setModelo(modelo);
				coche.setKm(km);
			} else {
				throw new SQLException("No se puede insertar el coche " + coche);
			}
		}
		return coche;
	}

	/**
	 * Elimina un coche de la bbdd
	 * 
	 * @param id Identifica al coche
	 * @return true si es posible eliminarlo, false si no
	 * @throws SQLException Si el coche tiene alguna multa asociada (integridad
	 *                      referencial)
	 */
	public boolean delete(Long id) throws SQLException {
		boolean r = false;
		String sql = "DELETE FROM coche WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}

	/**
	 * Actualiza SOLO el modelo de un coche
	 * 
	 * @param modelo variable que contiene el nuevo modelo al que se va a cambiar el
	 *               coche
	 * @param id     variable que identifica al coche
	 * @return true si se pudo actualizar, false en caso contrario
	 * @throws SQLException
	 */
	public boolean updatePatch(String modelo, long id) throws SQLException {
		boolean r = false;
		String sql = "UPDATE coche SET modelo = ? WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, modelo);
			pst.setLong(2, id);
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}
	
	public boolean update(Coche coche) throws SQLException {
		boolean r = false;
		String sql = "UPDATE coche SET modelo = ? matricula = ? km = ? WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, coche.getModelo());
			pst.setString(2, coche.getMatricula());
			pst.setInt(3, coche.getKm());
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}

	/**
	 * Eliminado lógico de un coche de una bbdd
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean darDeBaja(Long id) throws SQLException {
		boolean r = false;
		String sql = "UPDATE coche SET modelo = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			if (pst.executeUpdate() == 1) {
				r = true;
			}
		}
		return r;
	}
}