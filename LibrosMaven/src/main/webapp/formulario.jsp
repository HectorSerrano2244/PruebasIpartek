<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "modelos.Libro"%>
    <% Libro libro= (Libro)request.getAttribute("libro");
    	String accion = (String)request.getAttribute("accion");
    	String accget=request.getParameter("accion");
    	String ira="",txtbot="",txterror="";
    	
    	if ("nuevo".equals(accget)){
        		ira="altalibro";
        		txtbot="Agregar";
    	}
    	else if ("editar".equals(accget)){
        		
    		 if(libro==null){
        		ira="altalibro";
        		txtbot="Agregar";
        		txterror="No se ha encontrado el libro";
        		}
        	 else if (libro!=null){
        		ira="editarlibro";
        		txtbot="Modificar";
        		}
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario</title>
</head>
<body>
	<section>

		<form action="<%=ira%>">
			<fieldset>
				<legend>Datos de libro</legend>
				<p>
					<%=txterror%>
				</p>
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
					<button><%=txtbot%></button>
				</p>
			</fieldset>
		</form>
	</section>
</body>
</html>