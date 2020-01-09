package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.dao.CategoriaDAO;
import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Alerta;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioController.class);
	private static ProductoDAO dao;
	private static CategoriaDAO daoCategoria;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = ProductoDAO.getInstance();
		daoCategoria = CategoriaDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		daoCategoria = null;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (null == ConnectionManager.getConnection()) {
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		} else {

			super.service(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// prueba rapida de crear categoria
		try {
			Categoria c = new Categoria();
			c.setNombre("mock" + System.currentTimeMillis());
			daoCategoria.create(c);

			c.setNombre(c.getNombre() + "updated");
			daoCategoria.update(c.getId(), c);

			daoCategoria.delete(c.getId());
		} catch (Exception e) {
			LOG.debug(e);
			e.printStackTrace();
		}

		// llamar al DAO capa modelo
		ArrayList<Producto> productos = (ArrayList<Producto>) dao.getAll();
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) daoCategoria.getAll();

		request.setAttribute("productos", productos);
		request.setAttribute("categorias", categorias);
		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Los últimos productos destacados."));

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
