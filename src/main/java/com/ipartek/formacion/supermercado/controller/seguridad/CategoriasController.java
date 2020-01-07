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

import com.ipartek.formacion.supermercado.modelo.dao.CategoriaDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Alerta;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class ProductosController
 */

@WebServlet("/seguridad/categorias")
public class CategoriasController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final static Logger LOG = Logger.getLogger(CategoriasController.class);

	private static CategoriaDAO daoCategoria;

	// Crear Factoria y Validador
	ValidatorFactory factory;
	Validator validator;

	// Vistas
	private static final String VIEW_TABLA = "categorias/index.jsp";
	private static final String VIEW_FORM = "categorias/formulario.jsp";
	private static String vistaSeleccionda = VIEW_TABLA;

	// Acciones
	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_IR_FORMULARIO = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	// Parametros
	String pAccion;
	String pId;
	String pNombre;

	Usuario uLogeado;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoCategoria = CategoriaDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		super.destroy();
		daoCategoria = null;
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
		uLogeado = (Usuario) request.getSession().getAttribute("uLogeado");

	}

	private void mensajeValidacion(HttpServletRequest request, Set<ConstraintViolation<Categoria>> validaciones) {

		StringBuilder mensaje = new StringBuilder();
		for (ConstraintViolation<Categoria> cv : validaciones) {

			mensaje.append("<p>");
			mensaje.append(cv.getPropertyPath()).append(": ");
			mensaje.append(cv.getMessage());
			mensaje.append("</p>");

		}

		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, mensaje.toString()));

	}

}
