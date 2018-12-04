<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
    pageEncoding="ISO-8859-15"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Formulario</title>
</head>
<body>
	<section>
		<form action="AltaLibro">
			<fieldset>
				<legend>Datos de libro</legend>
				<p>
					<label for="id">ID</label>
					<input name="id" id="id" type="text"/>
				</p>
				<p>
					<label for="isbn">ISBN</label>
					<input name="isbn" id="isbn" type="text"/>
				</p>
				<p>
					<label for="titulo">Titulo</label>
					<input name="titulo" id="titulo" type="text"/>
				</p>
				<p>
					<label for="autor">Autor</label>
					<input name="autor" id="autor" type="text"/>
				</p>
				<p>
					<label for="editorial">Editorial</label>
					<input name="editorial" id="editorial" type="text"/>
				</p>
				<p>
					<label for="precio">Precio</label>
					<input name="precio" id="precio" type="text"/>
				</p>
				<p>
					<button>Añadir</button>
				</p>
			</fieldset>
		</form>
	</section>
</body>
</html>