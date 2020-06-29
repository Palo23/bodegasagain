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
    <div class="navbar-fixed">
	<nav>
    <div class="nav-wrapper">
            <a href="home.jsp" class="brand-logo"><img class="responsive-image" src="Images/logo3.png"></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="cerrarsesion.jsp"><%=nombre + " "%><%=apellido%> (Cerrar Sesión)</a></li>
      </ul>
    </div>
  </nav>
</div>
    <div class="ventana5">
    
    
    <!-- Dropdown Trigger -->
    <div style="top: 8%; position: absolute;  left: 83%;">
  <a class="dropdown-trigger btn-floating btn tooltipped waves-effect waves-light btn-large brown" href='#' data-target='dropdown1' data-position="right" data-tooltip="Opciones"><i class="large material-icons">expand_more</i></a>
    </div>
    <!-- Dropdown Structure -->
  <ul id='dropdown1' alignment="right" class='dropdown-content'>
    <c:if test="${inactivo == 'inactivo'}">
    	<a href="Empresa?action=newEmpresa" class="waves-effect waves-teal btn-flat brown-text text-darken-1" style="width: 100px;">Agregar</a>
    	<a href="Empresa?action=listEmpresa" class="waves-effect waves-teal btn-flat brown-text text-darken-1" style="width: 100px;">Activas</a>
    </c:if>
    <c:if test="${inactivo != 'inactivo'}">
    	<a href="Empresa?action=newEmpresa" class="waves-effect waves-teal btn-flat brown-text text-darken-1" style="width: 100px;">Agregar</a>
    	<a href="Empresa?action=inactivaEmpresa" class="waves-effect waves-teal btn-flat brown-text text-darken-1" style="width: 100px;">Inactivas</a>
    </c:if>
  </ul>
    
            <h5 class="bienvenido3">Tabla de contenido</h5>
            <table class="tabla3">
                <thead>
                    <tr style="width: 100%;">
                        <th class="btns3">ID</th>
                        <th class="btns3">Nombre</th>
                        <th class="btns3">Direccion</th>
                        <th class="btns3">Acciones</th>
                    </tr>
                </thead>
        
                <tbody>
                  <!--   for (Todo todo: todos) { recorremos las empresas en la bd y las listamos  -->
            
              <c:forEach var="empresa" items="${listEmpresa}">
                <tr>
                
                  <td class="btns3"><c:out value="${empresa.id}" /></td>
                  <td class="btns3"><c:out value="${empresa.nombre}" /></td>
                  <td class="btns3"><c:out value="${empresa.direccion}" /></td>
                  <td style="text-align: center;">
                    <div class="row" style="height: 15px; width: 160px;">
                    <a href="Empresa?action=editEmpresa&id=<c:out value='${empresa.id}' />" 
                    class="btn-floating btn-small btn tooltipped waves-effect brown modal-trigger" data-position="bottom" data-tooltip="Editar empresa"><i class="material-icons">edit</i></a>
                    <c:if test="${inactivo != 'inactivo'}">
                    <a href="#<c:out value='${empresa.id}'/>" 
                    class="btn-floating btn-small btn tooltipped waves-effect brown modal-trigger" style="margin-left: 5px;" data-position="bottom" data-tooltip="Eliminar empresa"><i class="material-icons">not_interested</i></a>
                    </c:if>
                    <c:if test="${inactivo == 'inactivo'}">
                        <a href="#<c:out value='${empresa.id}'/>" 
                    class="btn-floating btn-small btn tooltipped waves-effect brown modal-trigger" style="margin-left: 5px;" data-position="bottom" data-tooltip="Activar empresa"><i class="material-icons">spellcheck</i></a>
                    </c:if>
                    </div>
                  </td>	
                </tr>
                  				 
                                     <!-- Modal Structure -->
                        <div id="<c:out value='${empresa.id}'/>" class="modal">
                          <div class="modal-content">
                              <h4>Confirmar</h4>
                              <c:if test="${inactivo == 'inactivo'}">
                              <h5>¿Deseás activar a <c:out value='${empresa.nombre}'/>?</h5>
                              </c:if>
                              <c:if test="${inactivo != 'inactivo'}">
                              <h5>¿Deseás eliminar a <c:out value='${empresa.nombre}'/>?</h5>
                              </c:if>
                          </div>
                        <div class="modal-footer">
                        <c:if test="${inactivo == 'inactivo'}">
                        <a href="Empresa?action=activarEmpresa&id=<c:out value='${empresa.id}' />" class="modal-close waves-effect waves-green btn-flat">Activar</a>
                        </c:if>
                        <c:if test="${inactivo != 'inactivo'}">
                        <a href="Empresa?action=deleteEmpresa&id=<c:out value='${empresa.id}' />" class="modal-close waves-effect waves-green btn-flat">Eliminar</a>
                        </c:if>
                            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
                        </div>
                        </div>
                                    
        
                
              </c:forEach>
            
              <!-- } -->
                </tbody>
              </table>
    
    </div>

    <footer class="pie5">
        <!-- Disclaimer de la pagina -->
            <p>
                <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
            </p> 
        </footer>
        <script>
            $(document).ready(function(){
                $('.tooltipped').tooltip();
            });
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script>
        $(document).ready(function(){
            $('.modal').modal();
            });
        
          $('.dropdown-trigger').dropdown();
            </script>

</body>
</html>