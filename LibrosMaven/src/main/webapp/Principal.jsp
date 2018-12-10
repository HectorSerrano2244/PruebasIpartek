<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="pojos.Libro, java.util.ArrayList"%>

<%
	ArrayList<Libro> libros = (ArrayList<Libro>)request.getAttribute("libros");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>principal</title>
</head>
<body>
<section>
	<table>
		<tr>
			<th>ID</th>
			<th>ISBN</th>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Editorial</th>
			<th>Precio</th>
		</tr>
		<tr>
		<% for(Libro l: libros){ %>
			<td><%= l.getId() %></td>
			<td><%= l.getIsbn() %></td>
			<td><%= l.getTitulo() %></td>
			<td><%= l.getAutor() %></td>
			<td><%= l.getEditorial() %></td>
			<td><%= l.getPrecio() %></td>
		<%}%>	
		</tr>
	</table>
</section>
<section>
	<nav>
		<ul>
			<li><a href="AltaLibro">Nuevo libro</a></li>
		</ul>
	</nav>
</section>
</body>
</html>