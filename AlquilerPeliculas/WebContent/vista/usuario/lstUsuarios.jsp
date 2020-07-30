<%@page import="java.util.ArrayList"%>
<%@page import="entities.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>Listado de usuarios</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
h2 {
	align: center;
}

.agregar {
	text-align: -webkit-right;
}

.container {
	margin-top: 50px;
	background-color: #e9ecef;
}

.title {
	text-align: center;
	margin-bottom: 50px;
}
</style>
</head>
<body>

	<script>
		function Confirm() {
			!confirm("Esta seguro de eliminar el usuario");
		}
	</script>

	<div class="container card card-body">
		<form method="Post" action="srvLstUsuarios">
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
				<h2>Listado de usuarios</h2>
			</div>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Id Usuario</th>
						<th>Usuario</th>
						<th>Actualizar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("lstUsuarios");
					for (Usuario usu : list) {

					%>
					<tr>
						<th><%=usu.getIdUsuario()%></th>
						<th><%=usu.getEmail()%></th>
						<th><a class="btn btn-info"
							href="srvUsuario?action=edit&id=<%=usu.getIdUsuario()%>">Editar</a>
						</th>
						<th><a class="btn btn-danger"
							href="srvUsuario?action=delete&id=<%=usu.getIdUsuario()%>" onclick="Confirm()">Eliminar</a>
						</th>						
					</tr>
					<%
					}
					%>
				
				<tbody>
			</table>

			<div class="container">
				<div class="row">
					<div class="back col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<button type="button" class="btn btn-info"
							onclick="history.back()">Volver</button>
					</div>
					<div class="agregar col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<form method="GET" action="srvLstUsuarios">
							<input type="submit" class="btn btn-info" name="action"
								value="Agregar">
						</form>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
