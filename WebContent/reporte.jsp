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
                    <% if(roles == 1){ %>
                    <!-- Solo se muestra si es administrador, elegirá la empresa para ver su reporte -->
                        <div class="row" style="width: 643px; top: 15%; position:relative;">
                            <form action="Registro?action=all" method="POST">
                                <select id="idEmpresa" name="idEmpresa" class="browser-default" onchange="this.form.submit()" style="width: 50%; float: left;">
   									<option value="" disabled selected>-- Selecciona la empresa --</option>
   									<option value="0">Todas</option>
   									<c:forEach var="emp" items="${listEmpresa}">
                   					<option value="${emp.id}">${emp.nombre}</option>
                   					</c:forEach>
   								</select>
                            </form>
                        </div>
                        	<c:if test="${titulo != null}">
                        		<h5 class="bienvenido4">Reporte de
                                    <c:out value="${titulo.nombre}"></c:out>
                                </h5>
                                <input type="hidden" id="empresaID" name="empresaID" value="<c:out value='${titulo.id}'/>" class="form-control">
                        	</c:if>
                            <c:if test="${nombreEmpresa != null}">
                                <h5 class="bienvenido4">Reporte de
                                    <c:out value="${nombreEmpresa.nombre}"></c:out>
                                </h5>
                                <!-- Formulario para elegir las opciones del reporte -->
                                <form action="Registro?action=showReport" method="POST">
                                
                                <input type="hidden" id="empresaID" name="empresaID" value="<c:out value='${nombreEmpresa.id}'/>" class="form-control">
								<br><br><br>
								<div class="row">
								
								<div class="col s3">
									<select id="productoID" name="productoID" class="browser-default">
										<option value="">--  Producto --</option>
										<c:forEach var="producto" items="${listProduct}">
                    					<option value="${producto.id}">${producto.nombre}</option>
                    					</c:forEach>
									</select>
								</div>
								
								<div class="col s3">
									<select id="tipoMov" name="tipoMov" class="browser-default">
										<option value="">--  Tipo de movimiento --</option>
										<option value="1">Entrada</option>
										<option value="2">Salida</option>
									</select>
								</div>
                                
                                <div class="col s3">
									<input type="date" id="sd" name="sd" class="form-control">
								</div>
								<div class="col s3">
									<input type="date" id="ed" name="ed" class="form-control">
								</div>
									<button type="submit">Ver Reporte</button>
								</div>
								</form>
                            </c:if>
                            <table class="tabla2">
                                <thead>
                                    <tr>
                                        <th class="btns2">Tipo de movimiento</th>
                                        <th class="btns2">Cantidad</th>
                                        <th class="btns2">Producto</th>
                                        <th class="btns2">Entregó</th>
                                        <th class="btns2">Recibió</th>
                                        <th class="btns2">Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                			<c:if test="${listRegistro != null}">
                                            <c:forEach var="registro" items="${listRegistro}">
                                                <tr>
                                                <c:if test="${registro.entrada == 1}">
                                                	<td class="btns2">Entrada</td>
                                                </c:if>
                                                <c:if test="${registro.salida == 1}">
                                                	<td class="btns2">Salida</td>
                                                </c:if>
                                                    <td class="btns2">${registro.cantidad}</td>
                                                    <td class="btns2">${registro.nombre_producto}</td>
                                                    <td class="btns2">${registro.entrega}</td>
                                                    <td class="btns2">${registro.recibe}</td>
                                                    <td class="btns2">${registro.fecha}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <% }else if(roles != 1){ %>
                                                <h5 class="bienvenido6">Esta página no puede ser mostrada</h5>
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
                    const selectEmpresa = document.getElementById('idEmpresa');
                    empresa = document.getElementById('empresaID').value
                    if(empresa != null ){
                        for (const option of selectEmpresa.options) {
                              if (option.value == empresa) {
                                  option.setAttribute('selected', '');
                              } else {
                                option.removeAttribute('selected');
                              }
                        }
                    }
                </script>

            </body>


            </html>