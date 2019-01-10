<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/navbar.jsp"%>
<main role="main" class="container"> ${multa}
<form action="multas" method="post">
	<input type="hidden" name="id"
		value="${(op == 'ver') ? '${multa.id}' : 0}">
	<%-- 	<div class="form-group">
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
	</div> --%>
	<div class="form-group">
		<label for="fecha">Matricula</label> <input type="text" name="fecha"
			value="${coche.matricula}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="fecha">Fecha</label> <input type="text" name="fecha"
			value="<fmt:formatDate pattern = "dd/MM/yyyy HH:MM" value = "${fecha}" />"
			class="form-control" readonly>
	</div>
	<div class="form-group">
		<span>Detalles del vehiculo</span>
	</div>
	<div class="form-group">
		<label for="fecha">Modelo</label> <input type="text" name="fecha"
			value="${coche.modelo}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="fecha">KM</label> <input type="text" name="fecha"
			value="${coche.km}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="concepto">Concepto</label> <input type="text"
			name="concepto" value="${(op == 'ver') ? multa.concepto:''}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<div class="form-group">
		<label for="importe">Importe</label> <input type="number"
			name="importe" value="${multa.importe}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>

	<a href="privado/multas?op=ir_a" class="btn btn-outline-primary btn-block">Cambiar de
		Vehiculo</a>
	<button type="submit"
		class="btn btn-outline-success btn-block mt-3 mb-3">GUARDAR</button>
	<a class="btn btn-outline-primary btn-block">Volver al inicio</a>


</form>
</main>
<%@ include file="../../includes/footer.jsp"%>