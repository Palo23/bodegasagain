<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
              	HttpSession sesionNueva = request.getSession();
    			int roles = (int)sesionNueva.getAttribute("role");
    			String usuario = (String) sesionNueva.getAttribute("usuario");
    			String nombre = (String) sesionNueva.getAttribute("nombreUser");
    			String apellido = (String) sesionNueva.getAttribute("apellido");
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
    <div class="ventana4">
            <h5 class="bienvenido4">Tabla de contenido</h5>
            <table class="tabla2">
                <thead>
                  <tr>
                      <th class="btns2">ID Producto</th>
                      <th class="btns2">Nombre</th>
                      <th class="btns2">Empresa</th>
                      <th class="btns2">Cantidad</th>
                      <th class="btns2">Acciones</th>
                  </tr>
                </thead>
        
                <tbody>
                  <tr>
                  	<td class="btns2">00012</td>
                    <td class="btns2">Pan Sandwich</td>
                    <td class="btns2">Pan Sinaí</td>
                    <td class="btns2">4</td>
                    <th><a class="waves-effect waves-light btn-small brown">Agregar</a>
                 		<a class="waves-effect waves-light btn-small brown">Sacar</a></th>
                  </tr>
                  <tr>
                  	<td class="btns2">00012</td>
                    <td class="btns2">Pan Sandwich</td>
                    <td class="btns2">Pan Sinaí</td>
                    <td class="btns2">4</td>
                    <th><a class="waves-effect waves-light btn-small brown">Agregar</a>
                 		<a class="waves-effect waves-light btn-small brown">Sacar</a></th>
                  </tr>
                  <tr>
                  	<td class="btns2">00012</td>
                    <td class="btns2">Pan Sandwich</td>
                    <td class="btns2">Pan Sinaí</td>
                    <td class="btns2">4</td>
                    <th><a class="waves-effect waves-light btn-small brown">Agregar</a>
                 		<a class="waves-effect waves-light btn-small brown">Sacar</a></th>
                  </tr>
                  <tr>
                  	<td class="btns2">00012</td>
                    <td class="btns2">Pan Sandwich</td>
                    <td class="btns2">Pan Sinaí</td>
                    <td class="btns2">4</td>
                    <th><a class="waves-effect waves-light btn-small brown">Agregar</a>
                 		<a class="waves-effect waves-light btn-small brown">Sacar</a></th>
                  </tr>
                </tbody>
              </table>
    
    </div>

    <footer class="pie3">
        <!-- Disclaimer de la pagina -->
            <p>
                <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
            </p> 
        </footer>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>