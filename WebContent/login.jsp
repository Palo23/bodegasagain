<%@page session="true" language="java" import="java.util.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>SysInvent</title>
    <!-- Hoja externa de estilos materialize -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Iconos externos para estilos -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="CSS2/colores.css" type="text/css" >
    <link rel="stylesheet" href="CSS2/margenes.css" type="text/css" >
</head>

<body style="background-image: url('Images/fondo1.jpg');">
    
    <div class="ventana">
        <div class="logo">
            <img class="responsive-image" src="Images/logo2.png">
        </div>
        <h6 class="bienvenido">Bienvenido, por favor ingresa</h6>
        <form action="checklogin.jsp" method="post" class="loggin">
            <input type="text" id="usuario" placeholder="Usuario" name="usuario" required><br>
            <input type="password" id="clave" placeholder="Contraseña" name="clave" required><br><br>
            <button class="boton" type="submit">Ingresar</button>
        </form>
        <%
			if (request.getParameter("error") != null) {
		%>
			<!-- <div class="alert alert-danger">
			<strong>Error!</strong>
			<br>
			</div> -->
			<p><small><%=request.getParameter("error")%></small></p>
		<%
			}//Findelif
			%>
    </div>
    

    <footer class="pie">
    <!-- Disclaimer de la pagina -->
        <p>
            <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
        </p> 
    </footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>