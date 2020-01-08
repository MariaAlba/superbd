<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="includes/header.jsp" %>   
    	
    	<p class="alert alert-success">Categoria 1 => ${cat1.nombre }</p>
    	<p class="alert alert-info">Categoria 2 => ${cat2.nombre }</p>
    	<p class="alert alert-dangers">Categoria 3 => ${cat3.nombre }</p>
    	<p class="alert alert-warning">Categoria 4 => ${cat4.nombre }</p>

        <div class="row contenedor-productos">
        
        	<c:forEach items="${productos}" var="p">

	            <div class="col mb-5">
	
	                <!-- producto -->
	                <div class="producto">
	                <p class="text-center bg-primary text-light py-1 mb-0">${p.categoria.nombre }</p>
	                  <p class="text-center bg-warning mt-0">${p.usuario.nombre }</p>
	                    <span class="descuento">${p.descuento}%</span>
	                    <img src="${p.imagen}" alt="imagen de ${p.nombre}">
	
	                    <div class="body">
	                        <p>
	                            <span class="precio-descuento">	                            	
	                            	<fmt:formatNumber minFractionDigits="2" type="currency" currencySymbol="€" value="${p.precio}" />	                            
	                            </span>
	                            <span class="precio-original">
	                            	<fmt:formatNumber minFractionDigits="2" type="currency" currencySymbol="€" value="${p.precioDescuento}" />
	                            </span>
	                        </p>
	                        <p class="text-muted precio-unidad ">${p.nombre}</p>
	                        <p class="descripcion text-truncate">${p.descripcion}</p>
	                        
	                    </div>
	
	                    <div class="botones">
	                        <button class="minus">-</button>
	                        <input type="text" value="1">
	                        <button class="plus">+</button>
	                    </div>
	
	                      
	                    <button class="carro">añadir al carro</button>
	
	                </div>
	                <!-- /.producto -->
	
	            </div>  
	            <!-- /.col -->          
	
			</c:forEach>
           
        </div>
        <!-- row contenedor-productos -->

<%@ include file="includes/footer.jsp" %> 