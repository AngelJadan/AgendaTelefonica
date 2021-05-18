
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Persona Encontrada</title>
</head>
<body>
	<a href="/AgendaTelefonica/LoginUsuarioController?logout">Cerrar sesion</a>
	<c:set var="u" scope="request" value="${usuario}" />
	<h1>Bienvenido</h1>
	<p> Nombre: ${u.nombre}</p>	
	<a href="/AgendaTelefonica/JSPs/crearTelefono.jsp">Agregar nuevo telefono</a>
	<div>
		<form id="form-1" action="/AgendaTelefonica/BuscarUsuarioContactoController" method="get">
		<label for="input-1">Buscar contacto:</label>
		<input id="identificacion" name="identificacion" placeholder="Correo/cedula" type="text"/>
		<input id="cedula" name="cedula" placeholder="Text" type="text" hidden="true" value="${u.cedula}"/>
		<input type="submit" value="Buscar" id="button-1"/>
		</form>
	</div>
	<div>
		<c:set var="telfns" scope="request" value="${telefonos}" />
		<h5>Mis telefonos</h5>
		<table id="table-1">
			<thead>
				<tr>
					<th>N°</th>
					<th>Número</th>
					<th>Tipo</th>
					<th>Operadora</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="t" items="${u.getTelefonos()}">
					<tr>
						<td>${t.codigo}</td>
						<td>${t.numero}</td>
						<td>${t.tipo}</td>
						<td>${t.operadora}</td>
						<td>
							<form id="form-2" action="/AgendaTelefonica/BuscarTelefonoController" method="get">
								<input id="codigo" name="codigo" value="${t.codigo}" placeholder="Text" type="text" hidden="true"/>
								<input type="submit" value="Editar" id="button-2"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>