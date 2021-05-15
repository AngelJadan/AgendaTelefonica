<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" session="true" %>
<%@page import="ec.edu.ups.modelo.Usuario" %>

<% HttpSession sesion=request.getSession();
Usuario usuario=new Usuario();
usuario = (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar telefono</title>
</head>
<body>

	<div>
	<a href="/AgendaTelefonica/JSPs/inicio_usuario.jsp">Volver</a>
	<h5>Usuario: <%out.print(usuario.getNombre()+" "+usuario.getApellido());%></h5>
		<form id="form-1"  style="margin-left: 100px; margin-top: 50px;" 
		action="/AgendaTelefonica/CrearTelefonoController" method="POST">
			
			<input id="input-1" name="usuario" type="text" hidden="true" value="${usuario.getCedula()}"/>
			<br>
			<label for="lblnumero">Número</label>
			<br>
			<input id="numero" name="numero" placeholder="Número" type="text"/>
			<br>
			<label for="lbltipo">Tipo</label>
			<br>
			<select name="tipo" id="tipo">
				<option value="movil">Movil</option>
				<option value="fijo">Fijo</option>
			</select>
			<br>
			<label for="lbloperadora">Operadora</label>
			<br>
			<input id="operadora" name="operadora" placeholder="Operadora" type="text"/>
			<br>
			<input type="submit" value="Guardar" id="button-1"/>
		</form>
	</div>

</body>
</html>