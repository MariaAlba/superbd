package com.ipartek.formacion.supermercado.controller.mipanel;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Alerta;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/mipanel/perfil")
public class PerfilController extends HttpServlet {

	private final static Logger LOG = Logger.getLogger(PerfilController.class);

	private static final long serialVersionUID = 1L;

	private static UsuarioDAO dao;

	// Crear Factoria y Validador
	ValidatorFactory factory;
	Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = UsuarioDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		factory = null;
		validator = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogeado");
		dao.getById(u.getId());
		request.setAttribute("miUsuario", u);

		request.getRequestDispatcher("perfil/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogeado");

		String nueva = request.getParameter("nuevoPass");
		u.setContrasenia(nueva);

		try {
			dao.update(u.getId(), u);
			request.setAttribute("mensajeAlerta",
					new Alerta(Alerta.TIPO_PRIMARY, "Contraseña modificada correctamente"));
		} catch (Exception e) {
			LOG.error(e);
			request.setAttribute("mensajeAlerta",
					new Alerta(Alerta.TIPO_DANGER, "No se ha podido modificar correctamente"));
		}

		request.setAttribute("miUsuario", u);
		request.getRequestDispatcher("perfil/index.jsp").forward(request, response);
	}

}
