package com.ipartek.formacion.supermercado.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.pojo.Rol;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class SeguridadBackOfficeFilter
 */
@WebFilter("/seguridad/*")
public class SeguridadBackOfficeFilter implements Filter {

	private final static Logger LOG = Logger.getLogger(SeguridadBackOfficeFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession sesion = req.getSession();
		Usuario uLogeado = (Usuario) sesion.getAttribute("usuarioLogeado");

		if (uLogeado != null && uLogeado.getRol().getId() == Rol.ROL_ADMIN) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			LOG.warn("acceso denegado por seguridad " + uLogeado);
			sesion.invalidate();
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
