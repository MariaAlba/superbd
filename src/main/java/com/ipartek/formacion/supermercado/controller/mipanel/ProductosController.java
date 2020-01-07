package com.ipartek.formacion.supermercado.controller.mipanel;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;
import com.ipartek.formacion.supermercado.modelo.dao.ProductoException;
import com.ipartek.formacion.supermercado.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Alerta;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/mipanel/productos")
public class ProductosController extends HttpServlet {

	private final static Logger LOG = Logger.getLogger(ProductosController.class);

	private static final long serialVersionUID = 1L;

	private static final String VIEW_TABLA = "productos/index.jsp";
	private static final String VIEW_FORM = "productos/formulario.jsp";
	private static String vistaSeleccionda = VIEW_TABLA;

	private static ProductoDAO dao;
	private static UsuarioDAO daoUsuario;

	private static Usuario uLogeado;

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
	String pPrecio;
	String pImagen;
	String pDescripcion;
	String pDescuento;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = ProductoDAO.getInstance();
		daoUsuario = UsuarioDAO.getInstance();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		daoUsuario = null;
		factory = null;
		validator = null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

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
		pPrecio = request.getParameter("precio");
		pImagen = request.getParameter("imagen");
		pDescripcion = request.getParameter("descripcion");
		pDescuento = request.getParameter("descuento");

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

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();
		usuario = (Usuario) session.getAttribute("usuarioLogeado");
		request.setAttribute("productos", dao.getAllByUser(usuario.getId()));
		vistaSeleccionda = VIEW_TABLA;

	}

	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {

		Producto pEditar = new Producto();
		HttpSession sesion = request.getSession();
		uLogeado = (Usuario) sesion.getAttribute("usuarioLogeado");

		if (pId != null) {

			int id = Integer.parseInt(pId);
			// pEditar = dao.getById(id);
			try {
				pEditar = dao.getByIdByUser(id, uLogeado.getId());
				LOG.debug(pEditar);
			} catch (ProductoException e) {
				LOG.warn(e);
				request.setAttribute("mensajeAlerta",
						new Alerta(Alerta.TIPO_DANGER, ProductoException.EXCEPTION_UNAUTHORIZED));
			}
		}

		request.setAttribute("producto", pEditar);
		request.setAttribute("usuarios", daoUsuario.getAll());
		vistaSeleccionda = VIEW_FORM;

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(pId);

		HttpSession sesion = request.getSession();
		uLogeado = (Usuario) sesion.getAttribute("usuarioLogeado");

		Producto pGuardar;
		try {
			pGuardar = dao.getByIdByUser(id, uLogeado.getId());

//			if (pGuardar == null) {
//				request.setAttribute("mensajeAlerta",
//						new Alerta(Alerta.TIPO_DANGER, ProductoException.EXCEPTION_UNAUTHORIZED));
//				request.setAttribute("usuarios", daoUsuario.getAll());
//				request.setAttribute("producto", pGuardar);
//				vistaSeleccionda = VIEW_FORM;
//
//			} else {
			Set<ConstraintViolation<Producto>> validaciones = validator.validate(pGuardar);
			if (validaciones.size() > 0) {
				mensajeValidacion(request, validaciones);
			} else {

				try {

					if (id > 0) { // modificar

						if (verificarProductoUsuario(request, response, pGuardar)) {

							dao.updateByUser(id, uLogeado.getId(), pGuardar);
							request.setAttribute("mensajeAlerta",
									new Alerta(Alerta.TIPO_PRIMARY, " Producto modificado con éxito"));
							request.setAttribute("usuarios", daoUsuario.getAll());
							request.setAttribute("producto", pGuardar);
							vistaSeleccionda = VIEW_FORM;
						} else {
							LOG.warn("Usuario intenta modificar producto sin permisos");
							request.getSession().invalidate();
							request.setAttribute("mensajeAlerta",
									new Alerta(Alerta.TIPO_DANGER, "No puede modificar este producto"));
							vistaSeleccionda = "/login.jsp";
						}

					} else { // crear
						// evitar que se envie el parametro usuario id desde el formulario
						pGuardar.setId(id);
						pGuardar.setNombre(pNombre);
						pGuardar.setDescripcion(pDescripcion);
						pGuardar.setImagen(pImagen);
						pGuardar.setPrecio(Float.parseFloat(pPrecio));
						pGuardar.setDescuento(Integer.parseInt(pDescuento));
						pGuardar.setUsuario(uLogeado);
						dao.create(pGuardar);
						request.setAttribute("mensajeAlerta",
								new Alerta(Alerta.TIPO_PRIMARY, " Producto guardado con éxito"));
						request.setAttribute("usuarios", daoUsuario.getAll());
						request.setAttribute("producto", pGuardar);
						vistaSeleccionda = VIEW_FORM;
					}
				} catch (Exception e) { // validacion a nivel de base datos
					LOG.fatal(e);
					request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "Problema en la BBDD"));
					request.setAttribute("usuarios", daoUsuario.getAll());
					request.setAttribute("producto", pGuardar);
					vistaSeleccionda = VIEW_FORM;
				}

			}
			// }

		} catch (ProductoException e1) {
			LOG.error(e1);
			request.setAttribute("mensajeAlerta",
					new Alerta(Alerta.TIPO_DANGER, ProductoException.EXCEPTION_UNAUTHORIZED));
			request.setAttribute("usuarios", daoUsuario.getAll());
			request.setAttribute("producto", new Producto());
			vistaSeleccionda = VIEW_FORM;
		}

	}

	private void mensajeValidacion(HttpServletRequest request, Set<ConstraintViolation<Producto>> validaciones) {

		StringBuilder mensaje = new StringBuilder();
		for (ConstraintViolation<Producto> cv : validaciones) {

			mensaje.append("<p>");
			mensaje.append(cv.getPropertyPath()).append(": ");
			mensaje.append(cv.getMessage());
			mensaje.append("</p>");

		}

		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, mensaje.toString()));

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {

		HttpSession sesion = request.getSession();
		uLogeado = (Usuario) sesion.getAttribute("usuarioLogeado");

		int id = Integer.parseInt(pId);
		try {

			Producto p = dao.getById(id);
			if (verificarProductoUsuario(request, response, p)) {

				Producto pEliminado = dao.deleteByUser(id, uLogeado.getId());
				request.setAttribute("mensajeAlerta",
						new Alerta(Alerta.TIPO_PRIMARY, "Eliminado " + pEliminado.getNombre()));
				listar(request, response);
			} else {
				LOG.warn("El usuario intenta eliminar un producto que no le corresponde");
				request.getSession().invalidate();
				request.setAttribute("mensajeAlerta",
						new Alerta(Alerta.TIPO_DANGER, "No puede eliminar este producto"));
				vistaSeleccionda = "/login.jsp";
			}

		} catch (ProductoException e) {
			request.setAttribute("mensajeAlerta",
					new Alerta(Alerta.TIPO_DANGER, ProductoException.EXCEPTION_UNAUTHORIZED));
		} catch (Exception e) {
			LOG.fatal(e);
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "No se puede Eliminar el producto"));

		}

	}

	private boolean verificarProductoUsuario(HttpServletRequest request, HttpServletResponse response, Producto p) {

		boolean verificado = false;

		HttpSession sesion = request.getSession();
		uLogeado = (Usuario) sesion.getAttribute("usuarioLogeado");

		if (uLogeado.getId() == p.getUsuario().getId()) {
			verificado = true;
		}

		return verificado;
	}
}
