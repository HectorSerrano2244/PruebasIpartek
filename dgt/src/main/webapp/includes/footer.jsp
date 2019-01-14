  	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  	<script type="text/javascript" charset="utf8" src="js/datatables.js"></script>
  	<script>
	  	$(document).ready(function() {
	    	$('.tablaOrdenable').DataTable( {
	    		responsive: true,
	    		language: {
	    				"sProcessing":     "Procesando...",
	    				"sLengthMenu":     "Mostrar _MENU_ multas",
	    				"sZeroRecords":    "No se encontraron resultados",
	    				"sEmptyTable":     "Ningún dato disponible en esta tabla",
	    				"sInfo":           "Mostrando multas del número _START_ al _END_ de un total de _TOTAL_ multas",
	    				"sInfoEmpty":      "",
	    				"sInfoFiltered":   "(filtrado de un total de _MAX_ multas)",
	    				"sInfoPostFix":    "",
	    				"sSearch":         "Buscar:",
	    				"sUrl":            "",
	    				"sInfoThousands":  ",",
	    				"sLoadingRecords": "Cargando...",
	    				"oPaginate": {
	    					"sFirst":    "Primero",
	    					"sLast":     "Último",
	    					"sNext":     "Siguiente",
	    					"sPrevious": "Anterior"
	    				},
	    				"oAria": {
	    					"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	    					"sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    				}
	    			}
	    		}
	    	);
		});
	</script>
  </body>
</html>