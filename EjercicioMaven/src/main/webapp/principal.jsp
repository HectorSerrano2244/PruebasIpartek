<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="ISO-8859-15" import="paquete donde esta la clase"%>
<% 
		Object objeto = session.getAtrribute("usuario");
    	Usuario usuario = (Usuario)objeto;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-15">
<title>Insert title here</title>
</head>
<body>
	bienvenido
	<%=usuario.getMail() %>
</body>
</html>