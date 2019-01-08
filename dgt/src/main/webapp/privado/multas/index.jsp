<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container">
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th>Matricula</th>
			<th>Fecha</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">
			<tr>
				<td>${m.coche.matricula}</td>
				<td>${m.fecha}</td>
				<td><a href="privado/multas?op=ver&multa=${m.id}" class="btn btn-outline-dark">Detalles</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</main>
<%@include file="../../includes/footer.jsp"%>