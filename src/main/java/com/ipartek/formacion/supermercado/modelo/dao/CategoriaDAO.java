package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;

public class CategoriaDAO implements ICategoriaDAO {

	private final static Logger LOG = Logger.getLogger(CategoriaDAO.class);

	// <SINGLETON>
	private static CategoriaDAO INSTANCE;

	private CategoriaDAO() {
		super();
	}

	public static synchronized CategoriaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CategoriaDAO();
		}

		return INSTANCE;
	}
	// </SINGLETON>

	@Override
	public List<Categoria> getAll() {

		LOG.trace("recuperar todas las categorias");

		List<Categoria> registros = new ArrayList<Categoria>();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_getall()}");) {

			LOG.debug(cs);

			try (ResultSet rs = cs.executeQuery();) {
				// TODO mapper
				while (rs.next()) {

					Categoria c = new Categoria();
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					registros.add(c);

				}
			}

			catch (Exception e) {
				LOG.error(e);
			}

		} catch (SQLException e1) {
			LOG.error(e1);
			e1.printStackTrace();
		}
		return registros;
	}

	@Override
	public Categoria getById(int id) {

		LOG.trace("recuperar categoria por id " + id);
		Categoria registro = new Categoria();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_getbyid(?) }");) {

			cs.setInt(1, id);
			LOG.debug(cs);

			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					registro = mapper(rs);
				} else {
					registro = null;
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return registro;
	}

	@Override
	public Categoria delete(int id) throws Exception {

		LOG.trace("DELETE de categoria");

		// recuperar categoria antes de eliminar
		Categoria registro = getById(id);
		if (registro == null) {
			throw new Exception("registro no encontrado");
		}

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_delete(?)}");) {

			// parametro entrada
			cs.setInt(1, id);

			LOG.debug(cs);

			// ejecuto producto almacenado //CUIDADO NO ES UNA SELECT =>EXECUTEQUERY
			cs.executeUpdate();

		}

		return registro;
	}

	@Override
	public Categoria update(int id, Categoria pojo) throws Exception {

		// registro y pojo apuntan a la misma posicion de memoria

		LOG.trace("UPDATE de categoria por id :" + id + " pojo " + pojo);

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_update(?,?)}");) {

			// parametro entrada
			cs.setInt(1, pojo.getId());
			cs.setString(2, pojo.getNombre());

			LOG.debug(cs);

			// ejecuto producto almacenado //CUIDADO NO ES UNA SELECT =>EXECUTEQUERY
			if (cs.executeUpdate() == 1) {
				pojo.setId(id);
			} else {
				throw new Exception("No se puede modificar registro " + pojo + " por id " + id);
			}
		}

		return pojo;
	}

	@Override
	public Categoria create(Categoria pojo) throws Exception {

		LOG.trace("insertar nueva categoria " + pojo);

		Categoria registro = pojo;

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_insert(?,?)}");) {

			// parametro entrada
			cs.setString(1, pojo.getNombre());

			// parametro salida
			cs.registerOutParameter(2, java.sql.Types.INTEGER);

			LOG.debug(cs);

			// executamos el procedimiento almacenado executeUpdate, CUIDADO no es una
			// SELECT => executeQuery
			int affectedRows = cs.executeUpdate();
			LOG.debug("registros creados " + affectedRows);

			// una vez ejecutado recuperamos parametro de salida 2
			pojo.setId(cs.getInt(2));
		}

		return registro;
	}

	private Categoria mapper(ResultSet rs) throws SQLException {

		Categoria c = new Categoria();
		c.setId(rs.getInt("id"));
		c.setNombre(rs.getString("nombre"));

		return c;

	}
}
