package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

public class CategoriaDAO implements IDAO<Categoria>{
	
	private final static Logger LOG = Logger.getLogger(CategoriaDAO.class);
	
	private final static String SQL_GET_ALL= "SELECT id, nombre FROM categoria;";

	
	//Singleton pattern
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
	
	@Override
	public List<Categoria> getAll() {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		
		
		return categorias;
	}

	@Override
	public Categoria getById(int id) {
		
		return null;
	}

	@Override
	public Categoria delete(int id) throws Exception {
		
		return null;
	}

	@Override
	public Categoria update(int id, Categoria pojo) throws Exception {
		
		return null;
	}

	@Override
	public Categoria create(Categoria pojo) throws Exception {
		
		return null;
	}

	/**
	 * Utilidad par mapear un result set a una Categoria
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Categoria mapper(ResultSet rs) throws SQLException {

		Categoria c = new Categoria();
		c.setId(rs.getInt("id"));
		c.setNombre(rs.getString("nombre"));

		return c;
	}
}
