<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container">
<table class="table table-hover">
	<thead class="thead-light">
		<tr>
			<th>Matricula</th>
			<th>Fecha</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">

			
			<tr onclick="location='privado/multas?op=ver&multa=${m.id}'">
				<td>${m.coche.matricula}</td>
				<td>${m.fecha}</td>
			</tr>

		</c:forEach>
	</tbody>
</table>
</main>
<%@include file="../../includes/footer.jsp"%>