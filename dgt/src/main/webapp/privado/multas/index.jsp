<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<main role="main" class="container p-4">
<div class="row align-items-center">
	<a href="login" class="btn btn-outline-primary mt-3 mb-3 col-3">
		Volver
	</a>
	<h3 class="text-center  mb-3 col-9 ">${(opm == 'baja') ? 'MULTAS ANULADAS':'MULTAS' }</h3>
</div>
	<table class="table table-hover tablaOrdenable">
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
					<td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${m.fechaAlta}"/></td>
					<c:if test="${opm == 'baja'}">
						<td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${m.fechaBaja}"/></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</main>
<%@include file="../../includes/footer.jsp"%>