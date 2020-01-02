<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    
<fmt:setLocale value="es_ES"/>	
	
	<a href="mipanel/productos?accion=formulario" class="btn btn-primary mb-4">Nuevo Producto</a>
	
	<section class="my-3">

	<table class="tabla display" style="width: 100%">
		<thead>
			<tr>
				<th>#id</th>
				<th>Imagen</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Precio</th>
				<th>Descuento</th>
				<th>Usuario</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="p">
				<tr>
					<td>${p.id}</td>
					<td> <img src="${p.imagen }" alt="${p.nombre }" width="75" height="75"></td>
					<td>${p.nombre }</td>
					<td>${p.descripcion }</td>
					<td><fmt:formatNumber minFractionDigits="2" type="currency" currencySymbol="€"
		value="${p.precio}" /></td>
					<td>${p.descuento}%
					</td>
					<td>${p.usuario.nombre }</td>
					<td><a href="mipanel/productos?accion=formulario&id=${p.id}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</section>
	
	

<%@ include file="/includes/footer.jsp" %> 