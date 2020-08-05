<%-- 
    Document   : lstTarjetaCredito
    Created on : 23/02/2020, 18:22:11
    Author     : giuli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.Banco"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
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
<title>Bancos</title>
</head>
<body>
	<script>
		function Confirm() {
			!confirm("Esta seguro de eliminar el Banco");
		}
	</script>

	<div class="container card card-body">
		<form method="Get" action="srvTarjetaCredito">
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
				<h2>Listado de bancos</h2>
			</div>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Banco</th>						
						<th>Actualizar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<Banco> list = (ArrayList<Banco>) request.getAttribute("bancos");
								if(!list.isEmpty())
								{ 
								for (Banco tarj : list) {
					%>
					<tr>					
						<th><%=tarj.getNombreTarjeta()%></th>						
						<th><a class="btn btn-info"
							href="srvLstUsuarios?action=edit&id=<%= tarj.getiIdTarjeta() %>">Editar</a>
						</th>
						<th><a class="btn btn-danger"
							href="srvLstUsuarios?action=delete&id=<%= tarj.getiIdTarjeta() %>"
							onclick="Confirm()">Eliminar</a></th>
					</tr>
					<%
					}}
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

						<input type="submit" class="btn btn-info" name="action"
							value="Agregar">

					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>
