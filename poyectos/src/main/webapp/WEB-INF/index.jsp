<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="servlet.Proyecto, java.util.ArrayList"%>

<%
	Object oProyectos = request.getAttribute("proyectos");
	ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) oProyectos;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Insert title here</title>
</head>
<body>
<section>
<h2>Últimos Proyectos</h2>
<%
	for (Proyecto p: proyectos)
	{%>    
	    <article>
	       
	        <p><img src="media/proyecto <%= p.getId() %> .jpg" alt=""></p>
	        
	        <header>
	            <h3><%= p.getNombre() %></h3>
	        </header>
	        
	        <p><%=p.getDescripcion() %></p>
	        
	        <p><a href="<%=p.getRutaenlace() %>">leer más</a></p>
	        
	    </article>		
	<%}%>    
</section> 
</body>
</html>