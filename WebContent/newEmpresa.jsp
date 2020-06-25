<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <!-- Hoja externa de estilos materialize 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"> -->
    <link rel="stylesheet" href="materialize-v1.0.0/materialize/css/materialize.css">
    <!-- Iconos externos para estilos 
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
    <link rel="stylesheet" href="CSS2/colores.css" type="text/css">
    <link rel="stylesheet" href="CSS2/margenes.css" type="text/css">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/jquery.inputmask.bundle.js"></script>
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
        
    <div class="ventana6">
    <!-- Se hace la distinción, si el request trae datos es un edit, sino es un insert -->
    <c:if test="${empresa != null}">
    	<h5 class="bienvenido5">Editar Empresa</h5>
    </c:if>
    <c:if test="${empresa == null}">
    	<h5 class="bienvenido5">Agregar nueva Empresa</h5>
    </c:if>
            <div class="muneco">
                <img class="responsive-img" src="Images/muneco.png">
            </div>
            <!-- bloque 1-->
            <div class="formulario">
            <div class="row">
            <c:if test="${empresa != null}">
            	<form action="Empresa?action=updateEmpresa" method="POST" class="col s12">
            	<input type="hidden" id="id" name="id" value="<c:out value='${empresa.id}'/>">
            </c:if>
            <c:if test="${empresa == null}">
                <form action="Empresa?action=insertEmpresa" method="POST" class="col s12">
            </c:if>
                <div class="row">
                    <div class="input-field col s6" style="width: 30%;">
                        <input id="nombre" name="nombre" value="<c:out value='${empresa.nombre}' />" type="text" class="validate">
                        <label for="nombre">Nombre de Empresa</label>
                    </div>
                    <div class="input-field col s6" style="width: 30%;">
                        <input id="direccion" name="direccion" value="<c:out value='${empresa.direccion}'/>" type="text" class="validate">
                        <label for="direccion">Dirección de Empresa</label>
                    </div>
                </div>
            <!-- bloque 2-->
                <div class="row">
                    
                </div>
                <div class="row">
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="telefono" name="telefono" value="<c:out value='${empresa.telefono}' />" type="text" class="validate">
                        <label for="telefono">Teléfono empresa</label>
                    </div>
                </div>
                <div class="row">
                    <a href="Empresa?action=listEmpresa" class="waves-effect waves-light btn-small brown" style="float: right; margin-right: 5px;">Cancelar</a>
                    <c:if test="${empresa != null}">
                    <button type="submit" class="waves-effect waves-light btn-small brown" style="float: right; margin-right: 5px;">Actualizar</button>
                    </c:if>
                    <c:if test="${empresa == null}">
                    <button type="submit" class="waves-effect waves-light btn-small brown" style="float: right; margin-right: 5px;">Agregar</button>
                    </c:if>
                    
                </div>
            </form>
    </div>

    <footer class="pie4">
        <!-- Disclaimer de la pagina -->
            <p>
                <h5>SysInvent - Todos los derechos reservados - Año 2020 </h5>
            </p> 
        </footer>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script type="text/javascript">
        $(":input").inputmask();

        $('#telefono').inputmask({"mask": "99999999"});
        </script>

</body>
</html>