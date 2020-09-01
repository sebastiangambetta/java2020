<%-- 
    Document   : user
    Created on : 10/02/2020, 19:38:11
    Author     : giuli
--%>
<%@page import="entities.VideoClub"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@page import="entities.Usuario"%>
<%@page import="entities.Socio"%>
<%@page import="entities.Banco"%>
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

.buttons {
	margin-top: 45px;
}

.usu {
	text-align: center;
}

</style>
<title>Usuario</title>
</head>
<body>

	<%		
		VideoClub parametro = (VideoClub) request.getAttribute("parametro");
	%>

	<form method="post" action="srvVideoClub">
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
				<h1>Datos de la Parametrización</h1>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<input type="hidden" class="form-control" name="id"
						value="<%=parametro.getId()%>">
				</div>
			</div>
			<div class="usu row">
				<div class="col-md-3">
					<label for="plazoMax">Plazo máximo de devolucion de peliculas</label> <input type="number"
						class="form-control" name="plazoMax"
						value="<%=parametro.getPlazoMaxADevolver()%>"
						placeholder="plazo maximo a devolver" required>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="cantMaxPend">Cantidad maxima de peliculas pendientes</label> <input type="number"
							class="form-control" name="cantMaxPend"
							value="<%= parametro.getCantMaxPelPendientes() %>"
							placeholder="cantidad maxima pendiente" required>
					</div>
				</div>
				<div class="col-md-3">
					<label for="lblimporteDiario">Importe diario</label> <input type="text"
						class="form-control" name="importeDiario"
						value="<%= parametro.getImportePorDia() %>"
						placeholder="importe por dia" required>
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
	<script type="text/javascript">
	function redirect(){
		<% 
		
		RequestDispatcher rd = request.getRequestDispatcher("./Error.jsp");
		rd.forward(request, response);
		
		%>
	}
	</script>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
