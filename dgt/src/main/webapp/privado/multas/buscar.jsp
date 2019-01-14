<%@ include file="../../includes/header.jsp"%>
<%@ include file="../../includes/navbar.jsp"%>

<main role="main" class="container p-4"> 
	<form action="privado/multas" method="post">
		<%@ include file="../../includes/mensajes.jsp"%>
		<input type="hidden" name="op" value="buscar">
			<div class="form-group">
				<label for="matricula">Matricula</label> <input type="text" name="matricula" class="form-control" placeholder="4 numeros, 3 letras" value="${matricula}" required>
			</div>
			<button type="submit" class="btn btn-outline-primary btn-block">BUSCAR</button>
			<a href="login" class="btn btn-outline-primary btn-block">
				Volver al inicio
			</a>
	</form>
</main>
<%@ include file="../../includes/footer.jsp"%>