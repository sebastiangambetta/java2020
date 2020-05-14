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
        <script src="js/bootstrap.min.js"></script>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
    </head>
    <body>       

        <%
            Usuario user = (Usuario) request.getAttribute("user");
            Socio socio = (Socio) request.getAttribute("socio");
        %>

        <div id="container" style="margin: 20px 20px 20px 20px">
            <div style="text-align: center; margin-bottom: 40px"><h1>Alta de Usuario</h1></div>
            <form  method="POST" action="srvLstUsuarios" name="frmAddUser">                
                <input type="hidden" name="action" value="add" />
                <div class="row">
                    <div class="col-md-3">                        
                        <input type="hidden" class="form-control" name="id" value="<%=socio.getNroSocio()  != 0? socio.getNroSocio(): null%>">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="sr-only" for="nombre">Nombre :</label>
                        <input type="text" class="form-control" name="nombre" value="<%=socio.getNombre() != null? socio.getNombre(): ""%>" placeholder="Nombre" required>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="sr-only" for="apellido">Apellido :</label>
                            <input type="text" class="form-control" name="apellido" value="<%=socio.getApellido() != null? socio.getApellido(): ""%>" placeholder="Apellido" required>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <label class="sr-only" for="domicilio">Domicilio :</label>
                        <input type="text" class="form-control" name="domicilio" value="<%=socio.getDomicilio() != null? socio.getDomicilio(): ""%>" placeholder="Domicilio" required>
                    </div> 
                    <div class="col-md-3">
                        <label class="sr-only" for="telefono">Telefono :</label>
                        <input type="text" class="form-control" name="telefono" value="<%=socio.getTelefono() != null? socio.getTelefono(): ""%>" placeholder="Telefono" required>
                    </div>
                </div>
                <div class="row">                    
                    <div class="col-md-3">
                        <label class="sr-only" for="nroTarjeta">N° Tarjeta del banco :</label>
                        <input type="text" class="form-control" name="nroTarjeta" value="<%=socio.getNroTarjeta() != null? socio.getNroTarjeta(): ""%>" placeholder="N° Tarjeta del banco">
                    </div>
                    <div class="col-md-3">    
                        <div class="row">
                        <div class="col-md-8">
                            <label for="sel1" >Recibe notificaciones de extrenos :</label>    
                        </div>
                        <div class="col-md-4">
                            <select class="form-control" name="estado" value="<%=socio.getEstado()%>">
                                <option value="1">Si</option>
                                <option value="0">NO</option>    
                            </select>
                        </div>
                            </div>
                    </div>

                    <div class="col-md-3">
                        <!--<input type="text" name="email" value="<%= user.getEmail() != null? user.getEmail(): ""%>" required placehorlder="Email" />-->
                        <label class="sr-only" for="email">Email :</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                    </div>
                    <div class="col-md-3">
                   <!--<input type="password" name="contrasena" <%= user.getContrasena() %> required placehorlder="Contraseña" />-->
                        <label class="sr-only" for="contrasena">Contraseña:</label>
                        <input type="password" class="form-control" id="contrasena" name="contrasena" placeholder="Contraseña" required>
                    </div>
                </div>                
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                                
                                    <!--<button class="btn-success">Guardar</button>  -->
                                    <form method="POST" action="srvLstUsuarios">
                        <button class="btn-success">Guardar</button>
                        <input type="hidden" name="action" value="save">                        
                    </form>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <form method="G" action="srvLstUsuarios">
                                <button class="btn btn-success">Cancelar</button>
                                <input type="hidden" name="action" value="lstUser">
                    </form>
                            </div>
                        </div>
                    </div>
                </div>
                        <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                                
                                <a class="btn-info" href="index.html"></a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </form>
</div>
</body>
</html>
