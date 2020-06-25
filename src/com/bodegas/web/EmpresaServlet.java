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

import com.bodegas.modelo.EmpresaModel;
import com.bodegas.pack.EmpresaConexion;

//Le colocamos un nombre al servlet para enviarle los datos
@WebServlet(name="Empresa", urlPatterns={"/Empresa"})
public class EmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpresaConexion empresaConnect;
    

    public void init() {
        empresaConnect = new EmpresaConexion();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cuando se llame al servlet, este recibirá una acción, según ella procesará los datos
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "newEmpresa":
				//Muestra el formulario para crear nueva empresa
				showNewEForm(request, response);
				break;
			case "inactivaEmpresa":
				//Muestra las empresas inactivas
				listIEmpresa(request, response);
				break;
			case "insertEmpresa":
				//Inserta una nueva empresa a la bd
				insertEmpresa(request, response);
				break;
			case "deleteEmpresa":
				//Desactiva una empresa
				deleteEmpresa(request, response);
				break;
			case "editEmpresa":
				//Muestra el formulario para editar una empresa
				showEditEForm(request, response);
				break;
			case "updateEmpresa":
				//Actualiza los datos de la empresa
				updateEmpresa(request, response);
				break;
			case "activarEmpresa":
				//Activa una empresa
				activarEmpresa(request, response);
				break;
			case "listEmpresa":
				//Muestra todas las empresas activas
				listEmpresa(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Al listar todas las empresas llamamos a la función en EmpresaCOnexión, las guardamos en una variable
		List<EmpresaModel> listEmpresa = empresaConnect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		//Colocamos esa variable en un atributo y se lo pasamos a la vista
		RequestDispatcher dispatcher = request.getRequestDispatcher("empresa.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listIEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Obtenemos todas las empresas inactivas y las enviamos como un atributo dentro de una variable
		List<EmpresaModel> listEmpresa = empresaConnect.selectIEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		//Le mandamos una variable "inactivo" para indicarle que cambie la vista a las empresas inactivas
		request.setAttribute("inactivo", "inactivo");
		RequestDispatcher dispatcher = request.getRequestDispatcher("empresa.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewEForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Mostramos la vista para agregar empresa
		RequestDispatcher dispatcher = request.getRequestDispatcher("nuevaEmpresa.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditEForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		//Recibimos el id de la empresa que queremos editar, con eso se va a buscarla a la bd
		EmpresaModel existingEmpresa = empresaConnect.selectEmpresa(id);
		//La obtenemos y guardamos como variable que enviamos como atributo a la vista
		RequestDispatcher dispatcher = request.getRequestDispatcher("newEmpresa.jsp");
		request.setAttribute("empresa", existingEmpresa);
		dispatcher.forward(request, response);

	}

	private void insertEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//Obtenemos los parámetros de la empresa que crearemos y la insertamos
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		EmpresaModel newEmpresa = new EmpresaModel(nombre, telefono, direccion);
		empresaConnect.insertEmpresa(newEmpresa);
		//Devolvemos la vista a las empresas activas
		response.sendRedirect("Empresa?action=listEmpresa");
	}

	private void updateEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//Obtenemos los parámetros de la empresa a actualizar
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		//Los enviamos a EmpresaConexión y los procesa
		EmpresaModel book = new EmpresaModel(id, nombre, telefono, direccion);
		empresaConnect.updateEmpresa(book);
		//Regresamos a la vista de empresas activas
		response.sendRedirect("Empresa?action=listEmpresa");
	}

	private void deleteEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		//Enviamos el parámetro para desactivar la empresa
		empresaConnect.deleteEmpresa(id);
		response.sendRedirect("Empresa?action=listEmpresa");

	}
	
	private void activarEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		//Enviamos el parámetro para activar la empresa
		empresaConnect.activarEmpresa(id);
		response.sendRedirect("Empresa?action=listEmpresa");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
