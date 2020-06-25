package com.bodegas.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bodegas.pack.UsuarioConexion;
import com.bodegas.modelo.UsuarioModel;
import com.bodegas.pack.EmpresaConexion;
import com.bodegas.modelo.EmpresaModel;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name="Usuario", urlPatterns={"/Usuario"})
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioConexion userConect;
	private EmpresaConexion empresaConect;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
    	this.userConect = new UsuarioConexion();
    	this.empresaConect = new EmpresaConexion();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "new":
				//Mostramos el formulario para nuevos usuarios
				showNewForm(request, response);
				break;
			case "insert":
				//Insertamos un nuevo usuario
				insertUser(request, response);
				break;
			case "delete":
				//Borramos usuarios
				deleteUser(request, response);
				break;
			case "edit":
				//Muestra el formulario de edición
				showEditForm(request, response);
				break;
			case "update":
				//Actualiza los datos de un usuario
				updateUser(request, response);
				break;
			case "list":
				//Lista los usuarios del sistema
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Obtenemos todos los usuarios del sistema y los mandamos a la vista
		List<UsuarioModel> listUser = userConect.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Obtenemos la lista de empresas para seleccionar a cual pertenece el nuevo usuario
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("newUser.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		//Obtenemos el usuario a editar y una lista de empresas para seleccionarla
		int id = Integer.parseInt(request.getParameter("id"));
		UsuarioModel existingUser = userConect.selectUser(id);
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("newUser.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//Obtenemos los datos para la inserción de un nuevo usuario
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dui = request.getParameter("dui");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String username = request.getParameter("username");
		String correo = request.getParameter("correo");
		int idRol = Integer.parseInt(request.getParameter("rol"));
		String contrasena = request.getParameter("pass");
		int idEmpresa = Integer.parseInt(request.getParameter("empresa"));
		UsuarioModel newUser = new UsuarioModel(nombre, apellido, dui, direccion, telefono, username, correo, idRol, contrasena,  idEmpresa);
		userConect.insertUser(newUser);
		response.sendRedirect("Usuario?action=list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//obtenemos los datos para editar el usuario
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dui = request.getParameter("dui");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String username = request.getParameter("username");
		String correo = request.getParameter("correo");
		int idRol = Integer.parseInt(request.getParameter("rol"));
		int idEmpresa = Integer.parseInt(request.getParameter("empresa"));

		UsuarioModel book = new UsuarioModel(id, nombre, apellido, dui, direccion, telefono, username, correo, idRol,  idEmpresa);
		userConect.updateUser(book);
		response.sendRedirect("Usuario?action=list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//Obtenemos el id para eliminar el usuario
		int id = Integer.parseInt(request.getParameter("id"));
		userConect.deleteUser(id);
		response.sendRedirect("Usuario?action=list");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doGet(request, response);
	}

}
 
