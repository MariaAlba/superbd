<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    	
	<h2 class="text-center py-2">Dashboard de ${usuarioLogeado.nombre }</h2>
	
	<p class="alert alert-danger">Existen ${categoriasTodosNum} categorias de productos</p>
	<p class="alert alert-primary"> Hay ${productosTodosNum} productos creados</p>
	<p class="alert alert-success"> Ud ha creado ${productosAdminNum} productos</p>
	<p class="alert alert-warning"> Hay ${usuariosTodosNum} usuarios creados</p>

<%@ include file="/includes/footer.jsp" %> 