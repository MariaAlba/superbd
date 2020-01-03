package com.ipartek.formacion.supermercado.controller.seguridad;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
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
@WebServlet("/seguridad/usuarios")
public class UsuariosController extends HttpServlet {

	private final static Logger LOG = Logger.getLogger(UsuariosController.class);

	private static final long serialVersionUID = 1L;

	private static final String VIEW_TABLA = "usuarios/index.jsp";
	private static final String VIEW_FORM = "usuarios/formulario.jsp";

	private static String vistaSeleccionda = VIEW_TABLA;
	private static UsuarioDAO dao;

	// acciones
	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_IR_FORMULARIO = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	// Crear Factoria y Validador
	ValidatorFactory factory;
	Validator validator;

	// parametros
	String pAccion;
	String pId;
	String pNombre;
	String pContrasenia;

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
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recoger parametros
		pAccion = request.getParameter("accion");
		pId = request.getParameter("id");
		pNombre = request.getParameter("nombre");
		pContrasenia = request.getParameter("contrasenia");

		try {
			switch (pAccion) {
			case ACCION_LISTAR:
				listar(request, response);
				break;
			case ACCION_ELIMINAR:
				eliminar(request, response);
				break;
			case ACCION_GUARDAR:
				guardar(request, response);
				break;
			case ACCION_IR_FORMULARIO:
				irFormulario(request, response);
				break;
			default:
				listar(request, response);
				break;
			}

		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();

		} finally {

			request.getRequestDispatcher(vistaSeleccionda).forward(request, response);
		}

	}

	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {

		Usuario uEditar = new Usuario();

		if (pId != null) {

			int id = Integer.parseInt(pId);
			uEditar = dao.getById(id);

		}

		request.setAttribute("usuario", uEditar);
		vistaSeleccionda = VIEW_FORM;

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(pId);
		Usuario uGuardar = new Usuario();
		uGuardar.setId(id);
		uGuardar.setNombre(pNombre);
		uGuardar.setContrasenia(pContrasenia);

		Set<ConstraintViolation<Usuario>> validaciones = validator.validate(uGuardar);
		if (validaciones.size() > 0) {
			mensajeValidacion(request, validaciones);
		} else {

			try {

				if (id > 0) { // modificar

					dao.update(id, uGuardar);
					request.setAttribute("mensajeAlerta",
							new Alerta(Alerta.TIPO_PRIMARY, "Usuario " + uGuardar + " modificado con éxito "));

				} else { // crear
					dao.create(uGuardar);
					request.setAttribute("mensajeAlerta",
							new Alerta(Alerta.TIPO_PRIMARY, "Nuevo usuario " + uGuardar + " creado con éxito "));
				}

			} catch (Exception e) { // validacion a nivel de base datos
				LOG.fatal(e);
				request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "Problema en la BBDD"));
			}

		}

		request.setAttribute("usuario", uGuardar);
		vistaSeleccionda = VIEW_FORM;

	}

	private void mensajeValidacion(HttpServletRequest request, Set<ConstraintViolation<Usuario>> validaciones) {

		StringBuilder mensaje = new StringBuilder();
		for (ConstraintViolation<Usuario> cv : validaciones) {

			mensaje.append("<p>");
			mensaje.append(cv.getPropertyPath()).append(": ");
			mensaje.append(cv.getMessage());
			mensaje.append("</p>");

		}

		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, mensaje.toString()));

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(pId);
		try {
			Usuario uEliminado = dao.delete(id);
			request.setAttribute("mensajeAlerta",
					new Alerta(Alerta.TIPO_PRIMARY, "Eliminado " + uEliminado.getNombre()));
		} catch (Exception e) {
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "No se puede eliminar el usuario"));

		}

		listar(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuarios", dao.getAll());
		vistaSeleccionda = VIEW_TABLA;

	}

}
