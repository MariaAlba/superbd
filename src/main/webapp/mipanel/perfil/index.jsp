<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    
<fmt:setLocale value="es_ES"/>	
	
	
	<section class="my-3">

	<table class="tabla display" style="width: 100%">
		<thead>
			<tr>
				<th>#id</th>
				<th>Nombre</th>
				<th>Password</th>
				<th><th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${miUsuario.id}</td>
					<td>${miUsuario.nombre }</td>
					<td>${miUsuario.contrasenia }</td>
					<td>
					<button data-toggle="modal" data-target="#exampleModal" class="btn btn-primary">Cambiar contraseña</button></td>				
				</tr>
		</tbody>
	</table>

</section>
	
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Nueva Contraseña</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
				<form action="mipanel/perfil" method="post" class="form">
				
					<div class="row form-group">
						<div class="col lg-12">
							<label for="" class="control-label"> Contraseña actual</label>
							<input type="text" name="currentPass" value="${miUsuario.contrasenia }" class="form-control"/>
						</div>
					</div>
					<div class="row form-group">
						<div class="col lg-12">
							<label for="" class="control-label"> Nueva Contraseña</label>
							<input type="text" name="nuevoPass" class="form-control"/>
						</div>
					</div>
					<div class="row form-group">
						<div class="col lg-12 text-center">
							<input type="submit" value="Modificar" class="btn btn-primary"/>
						</div>
					</div>
				
				</form>
				
				</div>
				
			</div>
		</div>
	</div>

<%@ include file="/includes/footer.jsp" %> 