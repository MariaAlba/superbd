 </main>

    <div class="footer-wrapper">
        <footer class="container py-5">
            <div class="row">
                <div class="col-12 col-md">
                    Supermercado
                    <small class="d-block mb-3 text-muted">&copy; 2019</small>
                </div>
    
                <div class="col-6 col-md offset-6">
                    <h5>Enlaces de Interes</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="politica-privacidad.html">Politica Privacidad</a></li>
                        <li><a class="text-muted" href="localizacion.html">Localización</a></li>
                        <li><a class="text-muted" href="contacto.html">Contacto</a></li>                    
                    </ul>
                </div>

            </div>    
        </footer>
    </div>    

    <a id="btn-top" href="#top" class="btn btn-primary">top</a>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>    
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    
    <script>
	    $(document).ready(function() {
	        $('.tabla').DataTable({
	        	
	            language: {
	                "sProcessing": "Procesando...",
	                "sLengthMenu": "Mostrar _MENU_ registros",
	                "sZeroRecords": "No se encontraron resultados",
	                "sEmptyTable": "Ningún dato disponible en esta tabla =(",
	                "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	                "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
	                "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
	                "sInfoPostFix": "",
	                "sSearch": "Buscar:",
	                "sUrl": "",
	                "sInfoThousands": ",",
	                "sLoadingRecords": "Cargando...",
	                "oPaginate": {
	                  "sFirst": "Primero",
	                  "sLast": "Último",
	                  "sNext": "Siguiente",
	                  "sPrevious": "Anterior"
	                },
	                "oAria": {
	                  "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
	                  "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	                },
	                "buttons": {
	                  "copy": "Copiar",
	                  "colvis": "Visibilidad"
	                }
	              },
	        });
	    } );
    </script>

    </body>

</html>
