
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session="true" language="java" import="java.util.*"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <!-- Hoja externa de estilos materealize 
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
    <div class="ventana5">
    
    
    <!-- Dropdown Trigger -->
  <a class='dropdown-trigger btn waves-effect waves-light btn-small brown' href='#' data-target='dropdown1'>Acciones</a>
    
    <!-- Dropdown Structure -->
  <ul id='dropdown1' alignment="left" class='dropdown-content'>
    <a href="Empresa?action=newEmpresa" class="waves-effect waves-light btn-small brown">Agregar</a>
    <c:if test="${inactivo == 'inactivo'}">
    	<a href="Empresa?action=listEmpresa" class="waves-effect waves-light btn-small brown">Activas</a>
    </c:if>
    <c:if test="${inactivo != 'inactivo'}">
    	<a href="Empresa?action=inactivaEmpresa" class="waves-effect waves-light btn-small brown">Inactivas</a>
    </c:if>
  </ul>
    
            <h5 class="bienvenido3">Tabla de contenido</h5>
            <table class="tabla3">
                <thead>
                  <tr>
                  	  <th class="btns3">ID</th>
                      <th class="btns3">Nombre</th>
                      <th class="btns3">Dirección</th>
                      <th class="btns3">Acciones</th>
                  </tr>
                </thead>
        
                <tbody>
                  <!--   for (Todo todo: todos) {  -->
              <c:forEach var="empresa" items="${listEmpresa}">
    
                <tr>
                  <td><c:out value="${empresa.id}" /></td>
                  <td><c:out value="${empresa.nombre}" /></td>
                  <td><c:out value="${empresa.direccion}" /></td>
                  <td>
                    <a class="waves-effect waves-light btn-small brown" href="Empresa?action=editEmpresa&id=<c:out value='${empresa.id}' />">Editar</a>
                    <a class="waves-effect waves-light btn-small brown modal-trigger" href="#<c:out value='${empresa.id}'/>">Eliminar</a>
                  </td>					 
                                     <!-- Modal Structure -->
                        <div id="<c:out value='${empresa.id}'/>" class="modal">
                          <div class="modal-content">
                              <h4>Confirmar</h4>
                              <h5>¿Deseás eliminar a <c:out value='${empresa.nombre}'/>?</h5>
                          </div>
                        <div class="modal-footer">
                            <a href="Empresa?action=deleteEmpresa&id=<c:out value='${empresa.id}' />" class="modal-close waves-effect waves-green btn-flat">Eliminar</a>
                            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
                        </div>
                        </div>
                                    
        
                </tr>
              </c:forEach>
              <!-- } -->
                </tbody>
              </table>
    
    </div>

    <footer class="pie2">
        <!-- Disclaimer de la pagina -->
            <p>
                <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
            </p> 
        </footer>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script>
          $('.dropdown-trigger').dropdown();
            </script>

</body>
</html>