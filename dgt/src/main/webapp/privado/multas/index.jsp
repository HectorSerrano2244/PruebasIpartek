<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>Matricula</th>
				<th>Fecha</th>
			</tr>
		</thead>
		<c:forEach items="${multas}" var="m">
			<tbody>
				<tr>
					<td>${m.coche.matricula}</td>
					<td>${m.fecha}</td>
				</tr>
			</tbody>
		</c:forEach> 
	</table>
</main>
<%@include file="../../includes/footer.jsp"%>