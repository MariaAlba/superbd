<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="es_ES"/>

<%@ page contentType="text/html; charset=UTF-8" %>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Ander Uraga">
    <title>Supermercado</title>
    
   <base href="${pageContext.request.contextPath}/" >

   <!-- Bootstrap core CSS -->
   <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css" rel="stylesheet">

	<!-- datatables -->
	<link href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"  rel="stylesheet">

   <!-- nuestro css -->
   <link rel="stylesheet" href="css/custom.css">

  </head>
  <body id="top">
  
  	
	<%-- <%@ include file="snow.jsp" %>    --%>
	  
    <nav class="site-header sticky-top py-1">
        <div class="container d-flex flex-column flex-md-row justify-content-between">
            <a class="py-2" href="inicio">
              Inicio
            </a>
            
            <c:if test="${empty usuarioLogeado }" >
            	<a class="py-2 d-none d-md-inline-block" href="login.jsp">Login</a>
            </c:if>
            
            <c:if test="${usuarioLogeado.rol.id eq 2 }" >
            	<div class="nav-item dropdown show">
        			<a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown">Productos</a>
			        <div class="dropdown-menu" aria-labelledby="dropdown01">
			          <a class="dropdown-item" href="seguridad/productos?accion=listar">Todos</a>
			          <a class="dropdown-item" href="seguridad/productos?accion=formulario">Nuevo</a>
			          
			        </div>
      			</div>
      			<div class="nav-item dropdown show">
        			<a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown">Usuarios</a>
			        <div class="dropdown-menu" aria-labelledby="dropdown02">
			          <a class="dropdown-item" href="seguridad/usuarios?accion=listar">Todos</a>
			          <a class="dropdown-item" href="seguridad/usuarios?accion=formulario">Nuevo</a>			          
			        </div>
      			</div>
      			<div class="nav-item dropdown show d-none d-md-inline-block">
        			<a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown">${sessionScope.usuarioLogeado.nombre}</a>
			        <div class="dropdown-menu" aria-labelledby="dropdown02">
			          <a class="dropdown-item" href="logout">Cerrar Sessión</a>
					          
			        </div>
      			</div>
       
            </c:if>	            
           
            <c:if test="${usuarioLogeado.rol.id eq 1 }" >
            	<div class="nav-item dropdown show">
        			<a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown">Productos</a>
			        <div class="dropdown-menu" aria-labelledby="dropdown01">
			          <a class="dropdown-item" href="mipanel/productos?accion=listar">Mis productos</a>
			          <a class="dropdown-item" href="mipanel/productos?accion=formulario">Nuevo</a>
			          
			        </div>
      			</div>
      			<div class="nav-item dropdown show d-none d-md-inline-block">
        			<a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown">${sessionScope.usuarioLogeado.nombre}</a>
			        <div class="dropdown-menu" aria-labelledby="dropdown02">
			          <a class="dropdown-item" href="mipanel/perfil">Mi perfil</a>
			          <a class="dropdown-item" href="logout">Cerrar Sessión</a>
					          
			        </div>
      			</div>
            	<!-- <a class="py-2 d-none d-md-inline-block" href="seguridad/productos?accion=listar">Tabla</a>
            	<a class="py-2 d-none d-md-inline-block" href="seguridad/productos?accion=formulario">Formulario</a> -->
         <!--    	<a class="py-2 d-none d-md-inline-block" href="logout">Cerrar Sessión</a> -->
            </c:if>	       
           
        </div>
    </nav>

    <main class="container py-5">
    
   
	    <c:if test="${not empty mensajeAlerta }">
	    
		    <div class="alert alert-${mensajeAlerta.tipo} alert-dismissible fade show mt-3" role="alert">
			  ${mensajeAlerta.texto}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		
		</c:if>
    
    