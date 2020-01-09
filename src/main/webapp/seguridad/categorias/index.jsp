<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    
<fmt:setLocale value="es_ES"/>	
	
	<a href="seguridad/categorias?accion=formulario" class="btn btn-primary mb-4">Nueva Categoría</a>
	
	<section class="my-3">

	<table class="tabla display" style="width: 100%">
		<thead>
			<tr>
				<th>#id</th>
				<th>Nombre</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorias}" var="c">
				<tr>
					<td>${c.id}</td>
					<td>${c.nombre }</td>
					<td><a class="btn btn-primary" href="seguridad/categorias?accion=formulario&id=${c.id}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</section>
	
	

<%@ include file="/includes/footer.jsp" %> 