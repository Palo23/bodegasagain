<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
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
    <% if(roles == 1 || roles == 2){ %>
   		<div class="select">
   			<form action="Inventario?action=all" method="POST">
   				<select id="idEmpresa" name="idEmpresa" class="browser-default" style="width: 80%;">
   					<option value="" disabled selected>-- Selecciona la empresa --</option>
   					<c:forEach var="emp" items="${listEmpresa}">
                    <option value="${emp.id}">${emp.nombre}</option>
                    </c:forEach>
   				</select>
   				<button type="submit" class="waves-effect waves-light btn-small brown">Ver inventario</button>
   			</form>
   		</div>
    <a href="Inventario?action=new" class="waves-effect waves-light btn-small brown" style="width: 100px;">Agregar</a>
          <% } %>
          <c:if test="${nombreEmpresa != null}">
          	<h5 class="bienvenido4">Inventario de <c:out value="${nombreEmpresa.nombre}"></c:out> </h5>
          </c:if>
          <c:if test="${nombreEmpresa == null}">
          <h5 class="bienvenido4">Tabla de contenido</h5>
          </c:if>
            <table class="tabla2">
                <thead>
                  <tr>
                      <th class="btns2">Código</th>
                      <th class="btns2">Nombre</th>
                      <th class="btns2">Empresa</th>
                      <th class="btns2">Cantidad</th>
                      <th class="btns2">Acciones</th>
                  </tr>
                </thead>
                <tbody>
                <% if(roles == 1 || roles == 2){ %>
                <c:if test="${listProduct != null}">
                <c:forEach var="prod" items="${listProduct}">
                	<tr>
                  	<td class="btns2">{prod.id}</td>
                    <td class="btns2">{prod.nombre}</td>
                    <td class="btns2">{prod.nombreEmpresa}</td>
                    <td class="btns2">{prod.cantidad}</td>
                    	<th>
                 		<a href="#" class="waves-effect waves-light btn-small brown">Editar</a>
                 		</th>
                  </tr>
                </c:forEach>
                </c:if>
                <c:if test="${listProduct == null}">
                	<p>No has seleccionado ninguna empresa</p>
                </c:if>
                <% }else if(roles == 3){ %>
                	<c:if test="${nombreEmpresa.id == empresa }"> 
                		<c:forEach var="prod" items="${listProduct}">
                	<tr>
                  	<td class="btns2">{prod.id}</td>
                    <td class="btns2">{prod.nombre}</td>
                    <td class="btns2">{prod.nombreEmpresa}</td>
                    <td class="btns2">{prod.cantidad}</td>
                    	<th>
                 		<a href="#" class="waves-effect waves-light btn-small brown">Editar</a>
                 		</th>
                  </tr>
                </c:forEach>
                	</c:if>
                	<c:if test="${nombreEmpresa.id != empresa }">
                		<p>Esta página no puede ser mostrada</p>
                	</c:if>
                <% } %>
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