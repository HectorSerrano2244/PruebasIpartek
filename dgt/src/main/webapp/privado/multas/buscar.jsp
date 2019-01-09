<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/navbar.jsp"%>
<%@ include file="../../includes/mensajes.jsp"%>
<main role="main" class="container"> 
<form action="privado/multas" method="post">
	<input type="hidden" name="op" value="buscar">
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
		<label for="matricula">Matricula</label> <input type="text" name="matricula" class="form-control">
	</div>
	<button type="submit" class="btn btn-outline-primary btn-block">BUSCAR</button>
<%-- 	<div class="form-group">
		<label for="concepto">Concepto</label> <input type="text"
			name="concepto" value="${multa.concepto}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div>
	<div class="form-group">
		<label for="importe">Importe</label> <input type="number"
			name="importe" value="${multa.importe}" class="form-control"
			${(op == 'ver') ? "readonly" : ""}>
	</div> --%>
	<%-- <c:choose>
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
	</c:choose> --%>
</form>
</main>
<%@ include file="../../includes/footer.jsp"%>