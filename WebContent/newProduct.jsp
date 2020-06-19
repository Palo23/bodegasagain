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
        <div class="ventana6">
            <h5 class="bienvenido5">Agregar Producto</h5>
                <div class="muneco">
                    <img class="responsive-img" src="Images/muneco.png">
                </div>
                <!-- bloque 1-->
                <div class="formulario">
                <div class="row">
                <form action="Inventario?action=insert" method="POST" class="col s12">
                    <div class="row">
                    	<div class="seleccion">
                            <label>Seleccionar Empresa</label>
                            <select id="empresa" name="empresa" class="browser-default" style="width: 80%;">
                                <option value="" disabled selected>-- Opciones --</option>
                                <c:forEach var="emp" items="${listEmpresa}">
                                <option value="${emp.id}">${emp.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-field col s6" style="width: 30%;">
                            <input id="codigo" name="codigo" type="text" class="validate">
                            <label for="codigo">Código de producto</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6"style="width: 30%;">
                            <input id="nombre" type="text" name="nombre" class="validate">
                            <label for="nombre">Nombre de Producto</label>
                        </div>
                        <div class="input-field col s6"style="width: 30%;">
                            <input id="cantidad" name="cantidad" type="text" value="0" class="validate">
                            <label for="cantidad">Cantidad</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6"style="width: 30%;">
                            <input id="bodega" name="bodega" type="text" class="validate">
                            <label for="bodega">Número de bodega</label>
                        </div>
                    </div>
                    <div class="rowbtns">
                        <a href="Inventario?action=show" class="waves-effect waves-light btn-small brown" 
                        style="float: right; margin-right: 5px; top: 15%;">Cancelar</a>
                            <button type="submit" class="waves-effect waves-light btn-small brown" 
                            style="float: right; margin-right: 5px; top: 15%;">Agregar</button>
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
            
    
    </body>
    </html>