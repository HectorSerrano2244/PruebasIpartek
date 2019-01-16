<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
<main role="main" class="container p-4">
	<form novalidate class="form-signin" action="login" method="post">
		<h1 class="h3 mb-3 font-weight-normal">
			Iniciar Sesión
		</h1>
		<label for="placa" class="sr-only">Nº Placa
				</label> 
				<input type="text" id="placa"
			name="placa" class="form-control" placeholder="Nº Placa"
			value="${usuario}" required autofocus>
			<label for="password" class="sr-only">
			Contraseña:</label> 
			<input type="password" id="password" class="form-control" placeholder="Contraseña" name="password" value="${password}"
			required>
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Acceder
		</button>
		<%@include file="includes/mensajes.jsp"%>
	</form>
</main>
<%@include file="includes/footer.jsp"%>