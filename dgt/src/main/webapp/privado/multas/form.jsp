<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/navbar.jsp"%>
<main role="main" class="container"> ${multa}
<form action="multas" method="post">
	<input type="hidden" name="idmulta" value="${(op == 'ver') ? multa.id : 0}">
	<input type="hidden" name="idcoche" value="${coche.id}">
	<input type="hidden" name="op" value="multar">
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
		<label for="matricula">Matricula</label> <input type="text" name="matricula"
			value="${(op == 'ver') ? multa.coche.matricula : coche.matricula}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="fecha">Fecha</label> <input type="text" name="fecha"
			value="<fmt:formatDate pattern = "dd/MM/yyyy HH:MM" value = "${(op == 'ver') ? multa.fecha : fecha}" />"
			class="form-control" readonly>
	</div>
	<div class="form-group">
		<span>Detalles del vehiculo</span>
	</div>
	<div class="form-group">
		<label for="modelo">Modelo</label> <input type="text" name="modelo"
			value="${(op == 'ver') ? multa.coche.modelo : coche.modelo}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="km">KM</label> <input type="text" name="km"
			value="${(op == 'ver') ? multa.coche.km : coche.km}" class="form-control" readonly>
	</div>
	<div class="form-group">
		<label for="concepto">Concepto</label> <input type="text"
			name="concepto" value="${(op == 'ver') ? multa.concepto : ''}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<div class="form-group">
		<label for="importe">Importe</label> <input type="number"
			name="importe" value="${(op == 'ver') ? multa.importe : ''}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
		<a href="privado/multas?op=ir_a" class="btn btn-outline-primary btn-block">Cambiar de
			Vehiculo</a>
	<c:if test="${op == 'buscar'}">
		<input type="submit"class="btn btn-outline-success btn-block mt-3 mb-3" value="GUARDAR">
	</c:if>
	<a href="../index.jsp" class="btn btn-outline-primary btn-block">Volver al inicio</a>



</form>
</main>
<%@ include file="../../includes/footer.jsp"%>