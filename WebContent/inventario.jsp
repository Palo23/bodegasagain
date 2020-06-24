<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%
              	HttpSession sesionNueva = request.getSession();
          int roles = (int)sesionNueva.getAttribute("role");
          int idUser = (int)sesionNueva.getAttribute("idUser");
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
                <link rel="stylesheet" href="CSS2/colores.css" type="text/css">
                <link rel="stylesheet" href="CSS2/margenes.css" type="text/css">
                <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
            </head>

            <body style="background-image: url('Images/fondo1.jpg');">
                <nav>
                    <div class="nav-wrapper">
                        <a href="home.jsp" class="brand-logo"><img class="responsive-image" src="Images/logo3.png"></a>
                        <ul id="nav-mobile" class="right hide-on-med-and-down">
                            <li>
                                <a href="cerrarsesion.jsp">
                                    <%=nombre + " "%>
                                        <%=apellido%> (Cerrar Sesión)</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="ventana4">
                    <% if(roles == 1 || roles == 2){ %>
                        <div class="row" style="width: 643px; top: 15%; position:relative;">
                            <form action="Inventario?action=all" method="POST">
                                <select id="idEmpresa" name="idEmpresa" class="browser-default" onchange="this.form.submit()" style="width: 50%; float: left;">
   					<option value="" disabled selected>-- Selecciona la empresa --</option>
   					<c:forEach var="emp" items="${listEmpresa}">
                    <option value="${emp.id}">${emp.nombre}</option>
                    </c:forEach>
   				</select>
                            </form>
                            <a href="Inventario?action=new" class="btn-floating btn-large waves-effect brown" style="float: right;"><i class="material-icons">add</i></a>
                        </div>
                        <% } %>
                            <c:if test="${nombreEmpresa != null}">
                                <h5 class="bienvenido4">Inventario de
                                    <c:out value="${nombreEmpresa.nombre}"></c:out>
                                </h5>
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
                                                    <td class="btns2">${prod.id}</td>
                                                    <td class="btns2">${prod.nombre}</td>
                                                    <td class="btns2">${prod.nombreEmpresa}</td>
                                                    <td class="btns2">${prod.cantidad}</td>
                                                    <td class="btns2"><a href="#<c:out value='${prod.id}'/>" class="btn-floating btn-small waves-effect brown modal-trigger"><i class="material-icons">edit</i></a></td>
                                                </tr>
                                                <!-- Modal para registro de entradas/salidas -->
                                                <div id="<c:out value='${prod.id}'/>" class="modal" style="top: -25%; width: 80%;">
                                                    <div class="modal-content">
                                                        <input type="text" value="<c:out value='${nombreEmpresa.nombre}'/>" disabled>
                                                        <form id="registrarForm" action="Inventario?action=updateCant" method="POST">
                                                            <input type="hidden" id="empresaRecarga" name="empresaRecarga" value="<c:out value='${nombreEmpresa.id}'/>">
                                                            <input id="userYo" name="userYo" type="hidden" value="<%=idUser%>">
                                                            <input id="productoID" name="productoID" type="hidden" value="<c:out value='${prod.id}'/>">
                                                            <select id="movimiento" name="movimiento" class="browser-default" style="width: 80%;">
                                                                          <option value="" disabled selected>-- Tipo de movimiento --</option>
                                                                          <option value="1">Entrada</option>
                                                                          <option value="2">Salida</option>
                                                                    </select>
                                                            <select id="userExt" name="userExt" class="browser-default" style="width: 80%;">
                                                                          <option value="" disabled selected>-- Usuario externo encargado --</option>
                                                                          <c:forEach var="user" items="${listUsers}">
                                                                            <option value="${user.id}">${user.nombre}</option>
                                                                          </c:forEach>
                                                                    </select>
                                                            <input id="cantidad" name="cantidad" placeholder="Cantidad que entra/sale">
                                                            <label>Producto disponible</label>
                                                            <input id="disponible" name="disponible" value="<c:out value='${prod.cantidad}'/>" disabled>
                                                            <div class="modal-footer">
                                                                <button onclick="comprobar()" class="modal-close waves-effect waves-green btn-flat">Registrar</button>
                                                                <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>


                                                </th>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${listProduct == null}">
                                        <br><br><br>
                                            <h5 class="bienvenido6">No has seleccionado ninguna empresa</h5>
                                        </c:if>
                                        <% }else if(roles == 3){ %>
                                            <c:if test="${nombreEmpresa.id == empresa }">
                                                <c:forEach var="prod" items="${listProduct}">
                                                    <tr>
                                                        <td class="btns2">${prod.id}</td>
                                                        <td class="btns2">${prod.nombre}</td>
                                                        <td class="btns2">${prod.nombreEmpresa}</td>
                                                        <td class="btns2">${prod.cantidad}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${nombreEmpresa.id != empresa }">
                                            <br><br><br>
                                                <h5 class="bienvenido6">Esta página no puede ser mostrada</h5>
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
                <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
                <script>
                    $(document).ready(function() {
                        $('.modal').modal();
                    });

                    function comprobar() {
                        event.preventDefault()
                        cantidadActual = document.getElementById('disponible').value
                        accion = document.getElementById('movimiento').value
                        usuario = document.getElementById('userExt').value
                        cantidadModificar = document.getElementById('cantidad').value

                        if (usuario == "") {
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'Debes seleccionar un usuario',
                            })
                        } else {
                            if (accion == 1) {
                                if (cantidadModificar <= 0) {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Error',
                                        text: 'No puedes ingresar valores menores a 0',
                                    })
                                } else if (cantidadModificar > 0) {
                                    document.getElementById('registrarForm').submit();
                                }
                            } else if (accion == 2) {
                                if (cantidadModificar > cantidadActual) {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Error',
                                        text: 'La existencia en inventario no es suficiente',
                                    })
                                } else if (cantidadModificar <= 0) {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Error',
                                        text: 'No puedes ingresar valores menores a 0',
                                    })
                                } else if (cantidadModificar > 0 && cantidadModificar < cantidadActual) {
                                    document.getElementById('registrarForm').submit();
                                }
                            } else if (accion == "") {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: 'Debes seleccionar el tipo de movimiento',
                                })
                            }
                        }



                    }
                </script>

            </body>


            </html>