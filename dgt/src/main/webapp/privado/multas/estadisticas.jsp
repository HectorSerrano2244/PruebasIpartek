<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container p-4">
<div class="container mb-3">
	<div class="row d-flex align-items-center mb-3">
		<div class="col-4">
			<a href="privado/principal.jsp"	class=" btn btn-outline-primary btn-block">Volver</a>
		</div>
		<div class="text-center align-middle col-8">
			<span style="font-size:25px">ESTADÍSTICAS</span>
		</div>
	</div>
	<!-- Actual -->
	<span>Actual</span>
	<table id="actual" class="table">
		<tr>
			<td>Mes</td>
			<td>0 / 0</td>
		</tr>
		<tr>
			<td>Año</td>
			<td>0 / 0</td>
		</tr>
	</table>
	
	<hr>
	
	<!-- Histórico -->
	<span>Historico</span>
	<table id="historico" class="table">
		<thead class="thead-light">
			<tr>
				<th>Mes</th>
				<th>Rec / Obj</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table> 	
</div>

</main>
<%@include file="../../includes/footer.jsp"%>