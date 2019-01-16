<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container p-4">
<div>
	<a href="login"
		class="text-center btn btn-outline-primary mt-3 mb-3 col-3">
		Volver </a>
</div>
<h3 class="text-center mb-3 mt-1">${(opm == 'baja') ? 'MULTAS ANULADAS':'MULTAS' }</h3>
<table
	class="table table-hover ${(opm == 'baja') ? 'responsive':'' } nowrap tablaOrdenable">
	<thead class="thead-light">
		<tr>
			<th>Matricula</th>
			<c:choose>
				<c:when test="${opm != 'baja'}">
					<th>Fecha</th>
				</c:when>
				<c:otherwise>
					<th>Fecha Baja</th>
					<th>Fecha Alta</th>
					<th></th>
				</c:otherwise>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${opm=='baja'}">
				<c:forEach items="${multas}" var="m">
					<tr>
						<td>${m.coche.matricula}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm"
							value="${m.fechaBaja}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${m.fechaAlta}" /></td>
							<td><span class="btn btn-primary float-left"
							onclick="location='privado/multas?op=ver&idmulta=${m.id}&opm=${opm}'">Ver</span></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach items="${multas}" var="m">
					<tr
						onclick="location='privado/multas?op=ver&idmulta=${m.id}&opm=${opm}'">
						<td>${m.coche.matricula}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm"
								value="${m.fechaAlta}" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</main>
<%@include file="../../includes/footer.jsp"%>