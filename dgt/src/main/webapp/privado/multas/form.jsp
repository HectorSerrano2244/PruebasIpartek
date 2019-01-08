<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/navbar.jsp"%>
<main role="main" class="container"> ${multa}
<form action="multas" method="post">
	<input type="hidden" name="id" value="${(op == 'ver') ? '${multa.id}' : 0}">
	<div class="form-group">
		<label for="matricula">Matrícula</label>
		<c:choose>
			<c:when test="${op == 'ver'}">
				<input type="text" name="matricula" value="${multa.coche.matricula}"
					class="form-control" readonly>
			</c:when>
			<c:otherwise>
				<select class="form-control" name="matricula" id="matricula">
					<option value="-1">-- Selecciona --</option>
					<c:forEach items="${coches}" var="c">
						<option value="${c.id}">${c.matricula}</option>
					</c:forEach>
				</select>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="form-group">
		<label for="fecha">Fecha</label> <input type="text" name="fecha"
			value="${multa.fecha}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<div class="form-group">
		<label for="concepto">Concepto</label> <input type="text"
			name="concepto" value="${multa.concepto}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<div class="form-group">
		<label for="importe">Importe</label> <input type="number"
			name="importe" value="${multa.importe}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<c:choose>
	<c:when test="${op == 'ver'}">
		<div class="form-group">
			<label for="modelo">Modelo</label> <input type="text" name="modelo"
				value="${multa.coche.modelo}" class="form-control" readonly>
		</div>
		<div class="form-group">
			<label for="km">Kilómetros</label> <input type="number" name="km"
				value="${multa.coche.km}" class="form-control" readonly>
		</div>
	</c:when>
	<c:otherwise>
		<button type="submit" class="btn btn-outline-primary btn-block">GUARDAR</button>
	</c:otherwise>
	</c:choose>
</form>
</main>
<%@ include file="../../includes/footer.jsp"%>