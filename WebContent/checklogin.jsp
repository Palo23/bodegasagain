<%@ page session="true" language="java" import="java.util.*"
%> <%@ page import="com.bodegas.pack.conexion, java.sql.*" %>
<%
int role = 0;
int idUser = 0;
String nombreUser;
String apellido;
int id_empresa = 0;
String usuario=request.getParameter("usuario");
String clave=request.getParameter("clave");
conexion con = new conexion();
//buscará una coincidencia (count usuario), si es correcto podrá loguearse
con.setRs("select count(nombre_usuario), id_usuario, nombre_usuario, id_rol, id_empresa, nombre, apellido from cuenta where nombre_usuario='"+usuario+"' and contrasena=SHA2('"+ clave +"',256)");
//Se esta usando SHA2 con claves de 256 bits
ResultSet rs = con.getRs();
rs.next();
if(rs.getInt(1)==1){ 
//solo una coincidencia es permitida
//si se puede loguear, hay que evitar que quede una conexión activa
role = rs.getInt("id_rol");
idUser = rs.getInt("id_usuario");
nombreUser = rs.getString("nombre");
apellido = rs.getString("apellido");
id_empresa = rs.getInt("id_empresa");
rs.close();
con.cerrarConexion();
//se asignan los parámetros de sesión
HttpSession sesionOk = request.getSession();
sesionOk.setAttribute("usuario",usuario);
sesionOk.setAttribute("role", role);
sesionOk.setAttribute("idUser", idUser);
sesionOk.setAttribute("nombreUser", nombreUser);
sesionOk.setAttribute("apellido", apellido);
sesionOk.setAttribute("empresa", id_empresa);
%>
<jsp:forward page="home.jsp" />
<%
}
else{
%>
<jsp:forward page="login.jsp">
<jsp:param name="error" value="error"/>
</jsp:forward>
<%
}
rs.close();
con.cerrarConexion();
%>
