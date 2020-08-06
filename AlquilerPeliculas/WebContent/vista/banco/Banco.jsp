<%-- 
    Document   : TarjetaCredito
    Created on : 23/02/2020, 18:10:02
    Author     : giuli
--%>

<%@page import="entities.Banco"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tarjetas</title>
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

.buttons {
	margin-top: 45px;
}

.usu {
	text-align: center;
}
</style>

</head>
<body>

	<%
		Banco bank = (Banco) request.getAttribute("tarjeta");
		//Socio socio = (Socio) request.getAttribute("socio");
		//ArrayList<TarjetaCredito> tarjetas = (ArrayList<TarjetaCredito>) request.getAttribute("tarjetas");
	%>

	<form method="post" action="srvUsuario">
		<div class="container card card-body">

			<%
				String error = (String) request.getAttribute("Error");
			if (error != null) {
			%>

			<div class="alert alert-warning">
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
						value="<%=bank.getIdBanco()%>">
				</div>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<label for="nombre">Nombre</label> <input type="text"
						class="form-control" name="nombre"
						value="<%=bank.getNombreBanco() != null ? bank.getNombreBanco() : ""%>"
						placeholder="Nombre" required>
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
