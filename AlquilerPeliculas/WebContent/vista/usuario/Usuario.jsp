<%-- 
    Document   : user
    Created on : 10/02/2020, 19:38:11
    Author     : giuli
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entities.Usuario"%>
<%@page import="entities.Socio"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.title {
	text-align: center;
	margin-bottom: 50px;
	margin-top: 30px;
}

.container {
	margin-top: 50px;
	background-color: #e9ecef;
}

.savebtn {
	text-align: right;
}
.buttons{
margin-top: 45px;
}

.usu{
text-align: center;
}
</style>
<title>Usuario</title>
</head>
<body>

	<%            
	Usuario user = (Usuario) request.getAttribute("usuario");
            Socio socio = (Socio) request.getAttribute("socio");
        %>

	<form method="post" action="srvUsuario">
		<div class="container card card-body">
			<%
								String error = (String) request.getAttribute("Error");
							if (error != null) {
							%><div class="alert alert-warning">
				<%=error%>
			</div>
			<%
								}
							%>
			<div class="title">
				<h1>Datos del Socio</h1>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<input type="hidden" class="form-control" name="id"
						value="<%=socio.getNroSocio()%>">
				</div>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<label for="nombre">Nombre</label> <input
						type="text" class="form-control" name="nombre"
						value="<%=socio.getNombre() != null? socio.getNombre(): ""%>"
						placeholder="Nombre" required>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="apellido">Apellido</label> <input
							type="text" class="form-control" name="apellido"
							value="<%=socio.getApellido() != null? socio.getApellido(): ""%>"
							placeholder="Apellido" required>
					</div>
				</div>
				<div class="col-md-3">
					<label for="domicilio">Domicilio</label> <input
						type="text" class="form-control" name="domicilio"
						value="<%=socio.getDomicilio() != null? socio.getDomicilio(): ""%>"
						placeholder="Domicilio" required>
				</div>
				<div class="col-md-3">
					<label for="telefono">Telefono</label> <input
						type="text" class="form-control" name="telefono"
						value="<%=socio.getTelefono() != null? socio.getTelefono(): ""%>"
						placeholder="Telefono" required>
				</div>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<label for="nroTarjeta">N° Tarjeta del banco </label> 
					<input type="text" class="form-control" name="nroTarjeta"
						value="<%=socio.getNroTarjeta() != 0? socio.getNroTarjeta(): ""%>"
						placeholder="N° Tarjeta del banco">
				</div>
				<div class="col-md-3">
					<div class="row">
						<div class="col-md-8">
							<label for="sel1">Recibe notificaciones de extrenos :</label>
						</div>
						<div class="col-md-4">
							<select class="form-control" name="estado"
								value="<%=socio.getEstado()%>">
								<option value=""></option>
								<option value="1">Si</option>
								<option value="0">NO</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="title">
					<h1>Datos del Usuario</h1>
				</div>
			<div class="usu row">				
				<div class="col-md-3">
					<label  for="Email">Email</label> 
					<input type="text" class="form-control" id="uEmail"
					value="<%=user.getEmail() != null? user.getEmail(): ""%>"
						name="uEmail" placeholder="email" required>
				</div>
				<div class="col-md-3">
				<!--  class="sr-only" para que no se vea el label -->
					<label for="contrasena">Contraseña:</label> 
					<input type="password" class="form-control" id="contrasena"
					value="<%=user.getContrasena() != null? user.getContrasena(): ""%>"
						name="contrasena" placeholder="Contraseña" required>
				</div>
			</div>
			<div class="buttons row">
				<div class="col-md-6">
					<button type="button" class="btn btn-info" onclick="history.back()">Volver</button>
				</div>
				<div class="savebtn col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<input class="btn btn-info" type="submit" name="save"
						value="Guardar">
				</div>
			</div>
		</div>
	</form>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
