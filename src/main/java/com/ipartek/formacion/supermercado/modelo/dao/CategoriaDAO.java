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

		LOG.trace("GET BY ID");

		Categoria registro = new Categoria();
		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_getbyid(?)}");) {

			LOG.debug(cs);

			// parametro entrada
			cs.setInt(1, id);

			try (ResultSet rs = cs.executeQuery();) {
				// TODO mapper
				while (rs.next()) {
					registro.setId(rs.getInt("id"));
					registro.setNombre(rs.getString("nombre"));

				}
			}

			catch (Exception e) {
				LOG.error(e);
			}

		} catch (SQLException e1) {
			LOG.error(e1);
			e1.printStackTrace();
		}

		return registro;
	}

	@Override
	public Categoria delete(int id) throws Exception {

		LOG.trace("DELETE de categoria");

		Categoria registro = new Categoria();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_delete(?,?,?)}");) {

			// parametro entrada
			cs.setInt(1, id);

			// parametro salida
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);

			// ejecuto producto almacenado //CUIDADO NO ES UNA SELECT =>EXECUTEQUERY
			cs.executeUpdate();

			// una vez ejecutado recuperamos parametro de salida 2
			registro.setId(cs.getInt(2));
			registro.setNombre(cs.getString(3));
		}

		return registro;
	}

	@Override
	public Categoria update(int id, Categoria pojo) throws Exception {

		LOG.trace("UPDATE de categoria");

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_update(?,?,?)}");) {

			// parametro entrada
			cs.setString(2, pojo.getNombre());
			cs.setInt(1, pojo.getId());

			// parametro salida
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);

			LOG.debug(cs);

			// ejecuto producto almacenado //CUIDADO NO ES UNA SELECT =>EXECUTEQUERY
			cs.executeUpdate();

			// una vez ejecutado recuperamos parametro de salida 2
			pojo.setId(cs.getInt(1));
			pojo.setNombre(cs.getString(2));
		}

		return pojo;
	}

	@Override
	public Categoria create(Categoria pojo) throws Exception {

		LOG.trace("insertar nueva categoruia");

		Categoria registro = pojo;

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{CALL pa_categoria_insert(?,?)}");) {

			// parametro entrada
			cs.setString(1, pojo.getNombre());

			// parametro salida
			cs.registerOutParameter(2, java.sql.Types.INTEGER);

			LOG.debug(cs);

			// ejecuto producto almacenado //CUIDADO NO ES UNA SELECT =>EXECUTEQUERY
			cs.executeUpdate();

			// una vez ejecutado recuperamos parametro de salida 2
			pojo.setId(cs.getInt(2));
		}

		return registro;
	}

}
