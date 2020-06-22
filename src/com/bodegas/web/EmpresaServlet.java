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


@WebServlet(name="Empresa", urlPatterns={"/Empresa"})
public class EmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpresaConexion empresaConnect;
    

    public void init() {
        empresaConnect = new EmpresaConexion();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "newEmpresa":
				showNewEForm(request, response);
				break;
			case "inactivaEmpresa":
				listIEmpresa(request, response);
				break;
			case "insertEmpresa":
				insertEmpresa(request, response);
				break;
			case "deleteEmpresa":
				deleteEmpresa(request, response);
				break;
			case "editEmpresa":
				showEditEForm(request, response);
				break;
			case "updateEmpresa":
				updateEmpresa(request, response);
				break;
			case "activarEmpresa":
				activarEmpresa(request, response);
				break;
			case "listEmpresa":
				listEmpresa(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EmpresaModel> listEmpresa = empresaConnect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("empresa.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listIEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EmpresaModel> listEmpresa = empresaConnect.selectIEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		request.setAttribute("inactivo", "inactivo");
		RequestDispatcher dispatcher = request.getRequestDispatcher("empresa.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewEForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("nuevaEmpresa.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditEForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EmpresaModel existingEmpresa = empresaConnect.selectEmpresa(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("newEmpresa.jsp");
		request.setAttribute("empresa", existingEmpresa);
		dispatcher.forward(request, response);

	}

	private void insertEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		EmpresaModel newEmpresa = new EmpresaModel(nombre, telefono, direccion);
		empresaConnect.insertEmpresa(newEmpresa);
		response.sendRedirect("Empresa?action=listEmpresa");
	}

	private void updateEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		EmpresaModel book = new EmpresaModel(id, nombre, telefono, direccion);
		empresaConnect.updateEmpresa(book);
		response.sendRedirect("Empresa?action=listEmpresa");
	}

	private void deleteEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		empresaConnect.deleteEmpresa(id);
		response.sendRedirect("Empresa?action=listEmpresa");

	}
	
	private void activarEmpresa(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
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
