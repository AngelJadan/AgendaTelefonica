<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editat teléfono</title>
</head>
<body>
<c:set var="t" scope="request" value="${telefono}"/>
<a href="/AgendaTelefonica/JSPs/inicio_usuario.jsp">Volver</a>
<br>
<label>${t.usuario.getNombre()} ${t.usuario.getApellido()}</label>
	
<form id="form-1" action="/AgendaTelefonica/BuscarTelefonoController" method="post">

	<input id="codigo" name="codigo" type="text" value="${t.codigo}" hidden="True" />
	<br>
	
	<label for="input-1">Número:</label>
	<input name="numero" id="input-1" placeholder="Número" type="text" value="${t.numero}"/>
	
	<br>
	<label for="input-2">Operadora</label>
	<select name ="tipo">
		<option value="movil">Movil</option>
		<option value="fijo">Fijo</option>
	</select>
	<br>
	
	<label for="input-2">Operadora</label>
	<input name="operadora" id="input-2" placeholder="Operadora" type="text" value="${t.operadora}"/>
	<br>
	
	<input type="submit" value="Actualizar" id="button-1"/>
</form>
</body>
</html>