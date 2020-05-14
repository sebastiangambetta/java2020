
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">   		
        <script src="js/bootstrap.min.js"></script>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
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
                        <button class="btn-info">Editar</button>
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>" >
                    </form>
                    </th>
                    <th>
                    <form method="GET" action="srvLstUsuarios" onclick="Confirm())">
                        <button class="btn-danger">Eliminar</button>
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>" >
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

                <div id="new">
                    <!--<a class="btn-info" href="srvLstUsuarios?action=insert">Nuevo</a>-->                    
                    <form method="get" action="srvLstUsuarios">
                        <button class="btn-info">Agregar</button>
                        <input type="hidden" name="action" value="add">
                    </form>                    
                </div>            
            </form>

            <a class="btn-info" href="index.html">Volver</a>  
        </div>
    </body>
</html>
