<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    
<fmt:setLocale value="es_ES"/>	
	
	<a href="seguridad/usuarios?accion=formulario" class="btn btn-primary mb-4">Nuevo Usuario</a>
	
	<section class="my-3">

	<table class="tabla display" style="width: 100%">
		<thead>
			<tr>
				<th>#id</th>
				<th>Nombre</th>
				<th>Password</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.nombre }</td>
					<td>${u.contrasenia }</td>
					
					<td><a href="seguridad/usuarios?accion=formulario&id=${u.id}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</section>
	
	

<%@ include file="/includes/footer.jsp" %> 