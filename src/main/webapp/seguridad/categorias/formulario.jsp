<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/includes/header.jsp"%>


<form class="form p-5 border" action="seguridad/categorias" method="post">

	<input type="hidden" name="id" value="${categoria.id}">
	<input type="hidden" name="accion" value="guardar"> 
	
	
	<div class="form-group row">
		<div class="col-lg-6">
			<label>Nombre</label><br /> 
			<input type="text" name="nombre" value="${categoria.nombre}" class="form-control">
		</div>
	</div>
	

	<div class="form-group row">
	<div class="text-center col-lg-6">
 		<input type="submit" class="btn btn-primary px-4" value="${(categoria.id>0)?"Modificar":"Crear" }">
	</div>
	</div>

	<c:if test="${categoria.id > 0}">

	<div class="row form-group">
		<div class="col-lg-6 text-center">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">Eliminar</button>
		</div>
	</div>

	</c:if>
		
</form>

<c:if test="${categoria.id > 0}">


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<a class="btn btn-danger"
						href="seguridad/categorias?id=${categoria.id}&accion=eliminar">Eliminar</a>
				</div>
			</div>
		</div>
	</div>


</c:if>


<%@ include file="/includes/footer.jsp"%>
