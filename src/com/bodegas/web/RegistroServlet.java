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

import com.bodegas.modelo.*;
import com.bodegas.pack.*;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet(name="Registro", urlPatterns={"/Registro"})
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioConexion userConect;
	private EmpresaConexion empresaConect;
	private InventarioConexion inventarioConect;
	private RegistroConexion registroConect;
  
    public RegistroServlet() {
    	this.userConect = new UsuarioConexion();
    	this.empresaConect = new EmpresaConexion();
    	this.inventarioConect = new InventarioConexion();
    	this.registroConect = new RegistroConexion();
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "show":
				//Mostramos la vista de reportes
				showEmp(request, response);
				break;
			case "showExt":
				//Mostramos la vista de reportes externo
				showExt(request, response);
				break;
			case "all":
				//Mostramos las opciones para generar el reporte
				showAllProd(request, response);
				break;
			case "showReport":
				//Mostramos el reporte
				showReport(request, response);
				break;
			case "showReportExt":
				//Mostramos el reporte
				showReportExt(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void showAllProd(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//El usuario seleccionará el producto de cual quiere ver el reporte
		int id = Integer.parseInt(request.getParameter("idEmpresa"));
		List<InventarioModel> listProduct = inventarioConect.selectAllProductos(id);
		request.setAttribute("listProduct", listProduct);
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		EmpresaModel existingEmpresa = empresaConect.selectEmpresa(id);
		request.setAttribute("nombreEmpresa", existingEmpresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEmp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Mostramos la lista de empresas de las cuales podemos ver reportes
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showExt(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Mostramos la lista de empresas de las cuales podemos ver reportes
		int id = Integer.parseInt(request.getParameter("reporte"));
		EmpresaModel existingEmpresa = empresaConect.selectEmpresa(id);
		request.setAttribute("nombreEmpresa", existingEmpresa);
		List<InventarioModel> listProduct = inventarioConect.selectAllProductos(id);
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Recogemos los datos para crear el reporte
		int empresa = Integer.parseInt(request.getParameter("empresaID"));
		String producto = request.getParameter("productoID");
		String movimiento = request.getParameter("tipoMov");
		String sd = request.getParameter("sd");
		String ed = request.getParameter("ed");
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		EmpresaModel existingEmpresa = empresaConect.selectEmpresa(empresa);
		request.setAttribute("titulo", existingEmpresa);
		
		if(producto == "" && movimiento == "") {
			//Reporte si solo se seleccionó empresa
			List<RegistroModel> listRegistro = registroConect.registroPorEmpresa(empresa, sd, ed);
			request.setAttribute("listRegistro", listRegistro);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
			dispatcher.forward(request, response);
			
		}else if(producto != "" && movimiento == "") {
			//Reporte si se seleccionó empresa y producto
			int idProd = Integer.parseInt(producto);

			List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProducto(empresa, idProd, sd, ed);
			request.setAttribute("listRegistro", listRegistro);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
			dispatcher.forward(request, response);
			
		}else if(movimiento != "" && producto == "") {
			//Reporte si se seleccionó empresa y movimiento
			//Si es entrada
			int idMov = Integer.parseInt(movimiento);
			if(idMov == 1) {
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYEntrada(empresa, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
				dispatcher.forward(request, response);
			//Si es salida
			}else if(idMov == 2) {
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYSalida(empresa, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
				dispatcher.forward(request, response);
			}
			
		}else if(movimiento != "" && producto != "") {
			//Reporte si se seleccionó empresa, producto y movimiento
			int idProd = Integer.parseInt(producto);
			int idMov = Integer.parseInt(movimiento);
			
			if(idMov == 1) {;
			//Si es entrada
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProdEntrada(empresa, idProd, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
				dispatcher.forward(request, response);
				
			}else if(idMov == 2) {
				//Si es salida
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProdSalida(empresa, idProd, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporte.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		
	}
	
	private void showReportExt(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//Recogemos los datos para crear el reporte
		int empresa = Integer.parseInt(request.getParameter("empresaID"));
		String producto = request.getParameter("productoID");
		String movimiento = request.getParameter("tipoMov");
		String sd = request.getParameter("sd");
		String ed = request.getParameter("ed");
		List<EmpresaModel> listEmpresa = empresaConect.selectAllEmpresa();
		request.setAttribute("listEmpresa", listEmpresa);
		EmpresaModel existingEmpresa = empresaConect.selectEmpresa(empresa);
		request.setAttribute("nombreEmpresa", existingEmpresa);
		
		if(producto == "" && movimiento == "") {
			//Reporte si solo se seleccionó empresa
			List<RegistroModel> listRegistro = registroConect.registroPorEmpresa(empresa, sd, ed);
			request.setAttribute("listRegistro", listRegistro);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
			dispatcher.forward(request, response);
			
		}else if(producto != "" && movimiento == "") {
			//Reporte si se seleccionó empresa y producto
			int idProd = Integer.parseInt(producto);

			List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProducto(empresa, idProd, sd, ed);
			request.setAttribute("listRegistro", listRegistro);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
			dispatcher.forward(request, response);
			
		}else if(movimiento != "" && producto == "") {
			//Reporte si se seleccionó empresa y movimiento
			//Si es entrada
			int idMov = Integer.parseInt(movimiento);
			if(idMov == 1) {
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYEntrada(empresa, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
				dispatcher.forward(request, response);
			//Si es salida
			}else if(idMov == 2) {
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYSalida(empresa, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
				dispatcher.forward(request, response);
			}
			
		}else if(movimiento != "" && producto != "") {
			//Reporte si se seleccionó empresa, producto y movimiento
			int idProd = Integer.parseInt(producto);
			int idMov = Integer.parseInt(movimiento);
			
			if(idMov == 1) {;
			//Si es entrada
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProdEntrada(empresa, idProd, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
				dispatcher.forward(request, response);
				
			}else if(idMov == 2) {
				//Si es salida
				List<RegistroModel> listRegistro = registroConect.registroPorEmpresaYProdSalida(empresa, idProd, sd, ed);
				request.setAttribute("listRegistro", listRegistro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("reporteExterno.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
