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
            <h5 class="bienvenido5">Agregar nuevo Empleado</h5>
            <div class="muneco">
                <img class="responsive-img" src="Images/muneco.png">
            </div>
            <!-- bloque 1-->
            <div class="formulario">
            <div class="row">
                <form action="Empresa?action=insert" method="POST" class="col s12">
                <div class="row">
                    <div class="input-field col s6" style="width: 30%;">
                        <input id="nombre" name="nombre" type="text" class="validate">
                        <label for="nombre">Nombre</label>
                    </div>
                    <div class="input-field col s6" style="width: 30%;">
                        <input id="apellido" name="apellido" type="text" class="validate">
                        <label for="apellido">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="dui" type="text" name="dui" class="validate">
                        <label for="dui">Documento de Identidad</label>
                    </div>
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="email" name="email" type="text" class="validate">
                        <label for="email">Dirección</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="telefono" name="telefono" type="text" class="validate">
                        <label for="telefono">Teléfono</label>
                    </div>
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="username" name="username" type="text" class="validate">
                        <label for="username">Nombre de Usuario</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="correo" name="correo" type="text" class="validate">
                        <label for="correo">Correo</label>
                    </div>
                    <div class="input-field col s6"style="width: 30%;">
                        <input id="pass" name="pass" type="password" class="validate">
                        <label for="pass">Contraseña</label>
                    </div>
                </div>
            <!-- bloque 2-->
                <div class="row">
                    <div class="seleccion">
                        <label>Seleccionar Empresa</label>
                        <select id="empresa" name="empresa" class="browser-default" style="width: 50%;">
                            <option value="" disabled selected>-- Opciones --</option>
                            <c:forEach var="emp" items="${listEmpresa}">
                            <option value="${emp.id}">${emp.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="seleccion">
                    <label>Seleccionar rol</label>
                        <select id="rol" name="rol" class="browser-default" style="width: 50%;">
                            <option value="" disabled selected>-- Opciones --</option>
                            <option value="1">Administrador</option>
                            <option value="2">Inventarista</option>
                            <option value="3">Externo</option>
                        </select>
                      </div>
                </div>
                <div class="row">
                    <a href="Empresa?action=list" class="waves-effect waves-light btn-small brown" style="float: right; margin-right: 5px;">Cancelar</a>
                    <button type="submit" class="waves-effect waves-light btn-small brown" style="float: right; margin-right: 5px;">Agregar</button>
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
     <script>
    $(":input").inputmask();

    $("#dui").inputmask({"mask": "99999999-9"});
    $("#telefono").inputmask({"mask": "9999-9999"});
    </script>

</body>
</html>