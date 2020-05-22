<%@ page session="true" language="java" import="java.util.*"
%> <%@ page import="com.bodegas.pack.conexion, java.sql.*" %>
<%
int role = 0;
String nombreUser;
String apellido;
String usuario=request.getParameter("usuario");
String clave=request.getParameter("clave");
conexion con = new conexion();
//buscará una coincidencia (count usuario), si es correcto podrá loguearse
con.setRs("select count(nombre_usuario), nombre_usuario, id_rol, nombre, apellido from cuenta where nombre_usuario='"+usuario+"' and contrasena=SHA2('"+ clave +"',256)");
//Se esta usando SHA2 con claves de 256 bits
ResultSet rs = con.getRs();
rs.next();
if(rs.getInt(1)==1){ 
//solo una coincidencia es permitida
//si se puede loguear, hay que evitar que quede una conexión activa
role = rs.getInt("id_rol");
nombreUser = rs.getString("nombre");
apellido = rs.getString("apellido");
rs.close();
con.cerrarConexion();
//se asignan los parámetros de sesión
HttpSession sesionOk = request.getSession();
sesionOk.setAttribute("usuario",usuario);
sesionOk.setAttribute("role", role);
sesionOk.setAttribute("nombreUser", nombreUser);
sesionOk.setAttribute("apellido", apellido);
%>
<jsp:forward page="home.jsp" />
<%
}
else{
%>
<jsp:forward page="login.jsp">
<jsp:param name="error" value="Usuario y/o clave incorrecto. Vuelve a intentarlo."/>
</jsp:forward>
<%
}
rs.close();
con.cerrarConexion();
%>
