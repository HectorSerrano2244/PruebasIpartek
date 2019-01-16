<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container p-4">
<div class="row border ">
	<a href="login"	class=" btn btn-outline-primary mt-3 mb-3 col-3">Volver </a>
	<span class="align-middle text-center mb-3 mt-1 col-9">${(opm == 'baja') ? 'MULTAS ANULADAS':'MULTAS' }</span>
</div>
<table
	class="table table-hover tablaOrdenable">
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
				</c:otherwise>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">
			<tr onclick="location='privado/multas?op=ver&idmulta=${m.id}&opm=${opm}'">
				<td>${m.coche.matricula}</td>
				<c:if test="${opm=='baja' }">
				<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm"
					value="${m.fechaBaja}" /></td>
				</c:if>
				<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${m.fechaAlta}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table> 	
</main>
<%@include file="../../includes/footer.jsp"%>