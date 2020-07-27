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

h2{
align: center;
}
</style>
</head>
<body>

	<script>
function Confirm() {
  !confirm("Esta seguro de eliminar el usuario");
}
</script>

	<div class="container">
		<form method="Post" action="srvLstUsuarios">
			<h2>Listado de usuarios</h2>
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
						<th>
							<form method="GET" action="srvLstUsuarios">								
								<input type="hidden" class="btn-info" name="edit" value="Editar"> <input
									type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
							</form>
						</th>
						<th>
							<form method="GET" action="srvLstUsuarios" onclick="Confirm())">								
								<input type="submit" class="btn-danger" name="delete" value="Eliminar"> <input
									type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
							</form>
						</th>
						<th>
							<form method="GET" action="srvLstUsuarios">
								<input type="submit" class="btn-info" name="action" value="Agregar">
							</form>
						</th>
					</tr>
					<tr>
					</tr>
					<%
						}
					%>
				
				<tbody>
			</table>

			<div class="container">
				<div class="row">
					<div class="back col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<button type="button" class="btn-info" onclick="history.back()">Volver</button>
					</div>
					<!--   
					<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
						<form method="get" action="srvLstUsuarios" value="add">							
							<input type="submit" class="btn-info" name="action"
								value="Agregar">
						</form>
					</div>
					-->
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
