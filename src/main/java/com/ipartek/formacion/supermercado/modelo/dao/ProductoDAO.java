package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

public class ProductoDAO implements IProductoDAO {

	private final static Logger LOG = Logger.getLogger(ProductoDAO.class);

	private static ProductoDAO INSTANCE;

	private static final String SQL_GET_ALL = " SELECT p.id as id_producto, p.nombre as nombre_producto, p.descripcion, p.imagen, p.precio, p.descuento, "
			+ " u.nombre as nombre_usuario, u.id as id_usuario " + " FROM producto  AS p "
			+ " INNER JOIN usuario AS u ON p.id_usuario = u.id " + " ORDER BY p.nombre ASC LIMIT 500;";

	private static final String SQL_GET_BY_ID = "SELECT p.id as id_producto, p.nombre as nombre_producto,"
			+ " p.descripcion, p.imagen, p.precio, p.descuento, u.nombre as nombre_usuario, u.id as id_usuario "
			+ " FROM producto  AS p " + "	INNER JOIN usuario AS u ON p.id_usuario = u.id"
			+ " WHERE p.id = ? ORDER BY p.nombre ASC LIMIT 500;";

	private static final String SQL_GET_INSERT = "INSERT INTO producto (nombre, descripcion, imagen, precio, descuento, id_usuario) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?);";

	private static final String SQL_GET_UPDATE = "UPDATE producto SET " + " nombre = ?, " + " descripcion = ?, "
			+ " imagen = ?, " + " precio = ?, " + " descuento = ?, " + " id_usuario = ? " + " WHERE (id = ?);";

	private static final String SQL_DELETE = "DELETE FROM producto WHERE id = ? ;";

	private static final String SQL_GET_ALL_BY_USER = " SELECT p.id as id_producto, p.nombre as nombre_producto, p.descripcion, p.imagen, p.precio, p.descuento, "
			+ " u.nombre as nombre_usuario, u.id as id_usuario " + " FROM producto  AS p "
			+ " INNER JOIN usuario AS u ON p.id_usuario = u.id " + " WHERE p.id_usuario = ? "
			+ " ORDER BY p.nombre ASC LIMIT 500;";

	private ProductoDAO() {
		super();
	}

	public static synchronized ProductoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ProductoDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Producto> getAll() {

		ArrayList<Producto> lista = new ArrayList<Producto>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery()) {

			LOG.debug("PST: " + pst);

			while (rs.next()) {
				lista.add(mapper(rs));
			}

		} catch (SQLException e) {
			LOG.error("EXCEPCION " + e);
		}

		return lista;
	}

	public List<Producto> getAllByUser(int usuarioId) {

		ArrayList<Producto> lista = new ArrayList<Producto>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_USER)) {

			// sustituyo parametros en la SQL, en este caso 1º ? por id
			pst.setInt(1, usuarioId);

			LOG.debug("PST: " + pst);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				lista.add(mapper(rs));
			}

		} catch (SQLException e) {
			LOG.error("EXCEPCION: " + e);
		}

		return lista;
	}

	@Override
	public Producto getById(int id) {

		Producto registro = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);) {

			// sustituyo parametros en la SQL, en este caso 1º ? por id
			pst.setInt(1, id);

			LOG.debug("PST: " + pst);

			// ejecuto la consulta
			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					registro = mapper(rs);
				}
			}

		} catch (SQLException e) {
			LOG.error("EXCEPCION: " + e);
		}

		return registro;
	}

	@Override
	public Producto delete(int id) throws Exception {

		Producto registro = null;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE)) {

			pst.setInt(1, id);

			LOG.debug("PST: " + pst);

			registro = this.getById(id); // recuperar

			int affectedRows = pst.executeUpdate(); // eliminar
			if (affectedRows != 1) {
				registro = null;
				throw new Exception("No se puede eliminar " + registro);
			}

		} catch (SQLException e) {
			LOG.error("EXCEPCION: " + e);
		}
		return registro;
	}

	@Override
	public Producto update(int id, Producto pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_UPDATE)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getDescripcion());
			pst.setString(3, pojo.getImagen());
			pst.setFloat(4, pojo.getPrecio());
			pst.setInt(5, pojo.getDescuento());
			pst.setInt(6, pojo.getUsuario().getId());
			pst.setInt(7, id);

			LOG.debug("PST: " + pst);

			int affectedRows = pst.executeUpdate(); // lanza una excepcion si nombre repetido
			if (affectedRows == 1) {
				pojo.setId(id);
			} else {
				throw new Exception("No se encontro registro para id=" + id);
			}

		}
		return pojo;
	}

	@Override
	public Producto create(Producto pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_INSERT, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getDescripcion());
			pst.setString(3, pojo.getImagen());
			pst.setFloat(4, pojo.getPrecio());
			pst.setInt(5, pojo.getDescuento());
			pst.setInt(6, pojo.getUsuario().getId());

			LOG.debug("PST: " + pst);

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				// conseguimos el ID que acabamos de crear
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					pojo.setId(rs.getInt(1));
				}

			}

		}

		return pojo;
	}

	/**
	 * Utilidad par mapear un result set a un producto
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Producto mapper(ResultSet rs) throws SQLException {

		Producto p = new Producto();
		p.setId(rs.getInt("id_producto"));
		p.setNombre(rs.getString("nombre_producto"));
		p.setDescripcion(rs.getString("descripcion"));
		p.setImagen(rs.getString("imagen"));
		p.setPrecio(rs.getFloat("precio"));
		p.setDescuento(rs.getInt("descuento"));

		Usuario u = new Usuario();
		u.setNombre(rs.getString("nombre_usuario"));
		u.setId(rs.getInt("id_usuario"));

		p.setUsuario(u);
		return p;
	}
}
