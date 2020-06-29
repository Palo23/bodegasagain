<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
              	HttpSession sesionNueva = request.getSession();
    			int roles = (int)sesionNueva.getAttribute("role");
    			String usuario = (String) sesionNueva.getAttribute("usuario");
    			String nombre = (String) sesionNueva.getAttribute("nombreUser");
    			String apellido = (String) sesionNueva.getAttribute("apellido");
    			int empresa = (int)sesionNueva.getAttribute("empresa");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>SysInvent</title>
    <!-- Hoja externa de estilos materialize
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"> -->
    <link rel="stylesheet" href="materialize-v1.0.0/materialize/css/materialize.css">
    <!-- Iconos externos para estilos -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="CSS2/colores.css" type="text/css" >
    <link rel="stylesheet" href="CSS2/margenes.css" type="text/css" >
</head>
<body style="background-image: url('Images/fondo1.jpg');">
	<nav>
    <div class="nav-wrapper">
            <a href="home.jsp" class="brand-logo"><img class="responsive-image" src="Images/logo3.png"></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="cerrarsesion.jsp"><%=nombre + " "%><%=apellido%> (Cerrar Sesión)</a></li>
      </ul>
    </div>
  </nav>
	<% if(roles == 1){ %>
    <div class="ventana2">
            <h5 class="bienvenido2">¿Que deseas gestionar?</h5>
        <div class="collection">
                <a href="Usuario?action=list" class="collection-item brown-text text-darken-3">Usuarios</a>
                <a href="Empresa?action=listEmpresa" class="collection-item brown-text text-darken-3">Empresas</a>
                <a href="Inventario?action=show" class="collection-item brown-text text-darken-3">Inventarios</a>
                <a href="Registro?action=show" class="collection-item brown-text text-darken-3">Reportes</a>
        </div>
    </div>	
			<% } else if(roles == 2 || roles == 3){ %>
			<div class="ventana2">
            <h5 class="bienvenido2">¿Que deseas gestionar?</h5>
        <div class="collection">
                <a href="Inventario?action=mine&emp=<%=empresa%>" class="collection-item brown-text text-darken-3">Inventarios</a>
        		<% if(roles == 2){ %>
        		<a href="Registro?action=show" class="collection-item brown-text text-darken-3">Reportes</a>
        		<% } //fin if %>
        		<% if(roles == 3){ %>
        		<a href="Registro?action=showExt&reporte=<%=empresa%>" class="collection-item brown-text text-darken-3">Reportes</a>
        		<% } //fin if %>
        
        </div>
    </div>		
			
			<% } //fin if %>
			

    <footer class="pie">
        <!-- Disclaimer de la pagina -->
            <p>
                <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
            </p> 
        </footer>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>