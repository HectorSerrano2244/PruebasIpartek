<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container">
<table class="table table-hover">
	<thead class="thead-light">
		<tr>
			<th>Matricula</th>
			<th>Fecha</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">

			
			<tr onclick="location='privado/multas?op=ver&multa=${m.id}&opm=${opm }'">
				<td>${m.coche.matricula}</td>
				<td>${m.fecha}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>
<a href="index.jsp" class="btn btn-outline-primary btn-block">Volver al inicio</a>
</main>
<%@include file="../../includes/footer.jsp"%>