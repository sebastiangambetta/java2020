<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alquiler Peliculas</title>
<script>
	function Valida() {
		if (document.form.email.value === "") {
			alert("Please enter Login Name.");
			document.loginform.userName.focus();
			return false;
		}
		if (document.form.contrasena.value === "") {
			alert("Please enter password.");
			document.userform.password.focus();
			return false;
		}
		alert("Welcome User");
		return true;
	}
</script>
</head>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">

<body>
	<form method="post" action="srvLogin" onsubmit="Valida()">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>

				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="jumbotron">

						<%
								String error = (String) request.getAttribute("Error");
							if (error != null) {
							%><div class="alert alert-warning">
							<%=error%> </div>
							<%
								}
							%>
						
						<h1 class="text-center">Login</h1>
						<br>

						<div class="form-group">
							<label class="control-table" for="usuario">Usuario</label> <input
								type="text" name="email" class="form-control"
								placeholder="Usuario">
						</div>

						<div class="form-group">
							<label class="control-label" for="contraseña"></label> <input
								type="password" name="contrasena" class="form-control"
								placeholder="Contraseña">
						</div>

						<div class="form-group">
							<input type="submit" value="Iniciar sesión"
								class="form-control btn-info">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>