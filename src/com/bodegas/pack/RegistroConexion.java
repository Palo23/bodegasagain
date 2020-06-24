package com.bodegas.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bodegas.modelo.*;

public class RegistroConexion {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bodegas?useSSl=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	//Reporte por empresa
	private static final String SEARCH_EMPRESA_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ?\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPRESA_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ?\n" + 
			" ORDER BY fecha_ingresa DESC;";
	//Reporte por empresa y producto
	private static final String SEARCH_EMPROD_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ? AND o.id_producto = ?\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPROD_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ? AND o.id_producto = ?\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	//Reporte por empresa y entrada
	private static final String SEARCH_EMPENT_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ? AND o.entrada = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPENT_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ? AND o.entrada = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	//Reporte por empresa y salida
	private static final String SEARCH_EMPSAL_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ? AND o.salida = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPSAL_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ? AND o.salida = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	//Reporte por empresa, producto y entrada
	private static final String SEARCH_EMPRODENT_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ? AND o.id_producto = ? AND o.entrada = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPRODENT_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ? AND o.id_producto = ? AND o.entrada = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	//Reporte por empresa, producto y salida
	private static final String SEARCH_EMPROSAL_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) > ? AND date(o.fecha_ingresa) <= ? AND em.id_empresa = ? AND o.id_producto = ? AND o.salida = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	private static final String SEARCH_EMPROSAL_SAME_SQL = "SELECT o.*, em.id_empresa, em.nombre AS nombreEmpresa, p.nombre AS nombreProducto, e.nombre_usuario AS entrega, r.nombre_usuario AS recibe  \n" + 
			" FROM registro o \n" + 
			" JOIN cuenta e ON e.id_usuario = o.usuario_entrega\n" + 
			" JOIN cuenta r ON r.id_usuario = o.usuario_recibe\n" + 
			" JOIN producto p ON p.id_producto = o.id_producto\n" +
			" JOIN empresa em ON em.id_empresa = p.id_empresa\n" +
			" WHERE date(o.fecha_ingresa) = ? AND em.id_empresa = ? AND o.id_producto = ? AND o.salida = 1\n" + 
			" ORDER BY fecha_ingresa DESC;";
	
	public RegistroConexion() {
	}
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<RegistroModel> registroPorEmpresa(int idEmpresa, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRESA_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRESA_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	public List<RegistroModel> registroPorEmpresaYProducto(int idEmpresa, int idProducto, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPROD_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				preparedStatement.setInt(3, idProducto);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha_ingresa");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPROD_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			preparedStatement.setInt(4, idProducto);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	public List<RegistroModel> registroPorEmpresaYEntrada(int idEmpresa, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPENT_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha_ingresa");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPENT_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	public List<RegistroModel> registroPorEmpresaYSalida(int idEmpresa, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPSAL_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha_ingresa");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPSAL_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	public List<RegistroModel> registroPorEmpresaYProdEntrada(int idEmpresa, int idProducto, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRODENT_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				preparedStatement.setInt(3, idProducto);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha_ingresa");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRODENT_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			preparedStatement.setInt(4, idProducto);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	public List<RegistroModel> registroPorEmpresaYProdSalida(int idEmpresa, int idProducto, String sd, String ed) {
		if(sd == ed) {
			// using try-with-resources to avoid closing resources (boiler plate code)
			List<RegistroModel> registro = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRODENT_SAME_SQL);) {
				preparedStatement.setString(1, sd);
				preparedStatement.setInt(2, idEmpresa);
				preparedStatement.setInt(3, idProducto);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id_registro");
					String fecha = rs.getString("fecha_ingresa");
					int entrada = rs.getInt("entrada");
					int salida = rs.getInt("salida");
					int id_producto = rs.getInt("id_producto");
					int cantidad = rs.getInt("cantidad");
					int id_empresa = rs.getInt("id_empresa");
					String nombreEmpresa = rs.getString("nombreEmpresa");
					String nombreProducto = rs.getString("nombreProducto");
					String entrega = rs.getString("entrega");
					String recibe= rs.getString("recibe");
					registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return registro;
		}else {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RegistroModel> registro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPRODENT_SQL);) {
			preparedStatement.setString(1, sd);
			preparedStatement.setString(2, ed);
			preparedStatement.setInt(3, idEmpresa);
			preparedStatement.setInt(4, idProducto);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_registro");
				String fecha = rs.getString("fecha_ingresa");
				int entrada = rs.getInt("entrada");
				int salida = rs.getInt("salida");
				int id_producto = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombreEmpresa");
				String nombreProducto = rs.getString("nombreProducto");
				String entrega = rs.getString("entrega");
				String recibe= rs.getString("recibe");
				registro.add(new RegistroModel(id, fecha, entrada, salida, id_producto, cantidad, id_empresa, nombreEmpresa, nombreProducto, entrega, recibe));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return registro;
		}
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
