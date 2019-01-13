<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container">
	<h3 class="text-center w100 mb-3 mt-3">${(opm == 'baja') ? 'MULTAS ANULADAS':'MULTAS' }</h3>
	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th>Matricula</th>
				<c:choose>
					<c:when test="${opm != 'baja'}">
						<th>Fecha</th>			
					</c:when>
					<c:otherwise>
						<th>Fecha Alta</th>
						<th>Fecha Baja</th>
					</c:otherwise>
				</c:choose>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${multas}" var="m">
				<tr onclick="location='privado/multas?op=ver&idmulta=${m.id}&opm=${opm}'">
					<td>${m.coche.matricula}</td>
					<td>${m.fechaAlta}</td>
					<c:if test="${opm == 'baja'}">
						<td>${m.fechaBaja}</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="login" class="btn btn-outline-primary btn-block">Volver al inicio</a>
</main>
<%@include file="../../includes/footer.jsp"%>