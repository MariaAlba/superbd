package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;
import com.ipartek.formacion.supermercado.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Alerta;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;
import com.ipartek.formacion.supermercado.modelo.pojo.Rol;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	private static UsuarioDAO usuarioDao = UsuarioDAO.getInstance();
	private static ProductoDAO productoDao = ProductoDAO.getInstance();

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

		String view = "login.jsp";

		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("contrasenya");

		try {

			Usuario usuario = usuarioDao.exist(nombre, pass);

			if (usuario != null) {

				LOG.info("login correcto " + usuario);
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogeado", usuario);
				session.setMaxInactiveInterval(60 * 3); // 3min

				if (usuario.getRol().getId() == Rol.ROL_ADMIN) {
					// accedemos backoffice
					ArrayList<Producto> todos = (ArrayList<Producto>) productoDao.getAll();
					request.setAttribute("productosTodosNum", todos.size());
					request.setAttribute("productosAdminNum", productoDao.getAllByUser(usuario.getId()).size());
					request.setAttribute("usuariosTodosNum", usuarioDao.getAll().size());
					view = "seguridad/index.jsp";
				} else {
					// accedemos frontoffice
					ArrayList<Producto> productos = (ArrayList<Producto>) productoDao.getAllByUser(usuario.getId());
					request.setAttribute("miUsuario", usuario);
					request.setAttribute("misProductos", productos.size());
					view = "mipanel/index.jsp";
				}

			} else {

				request.setAttribute("mensajeAlerta",
						new Alerta(Alerta.TIPO_DANGER, "Credenciales incorrectas, prueba de nuevo"));

			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {

			request.getRequestDispatcher(view).forward(request, response);
		}

	}

}
