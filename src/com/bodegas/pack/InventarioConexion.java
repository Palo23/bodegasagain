package com.bodegas.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bodegas.modelo.RegistroModel;
import com.bodegas.modelo.InventarioModel;

public class InventarioConexion {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bodegas?useSSl=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO producto" + "  (codigo, id_empresa, nombre, cantidad, numero_bodega) VALUES "
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_EMPRESA_BY_ID = "select p.id_producto, p.codigo, p.nombre, p.id_empresa, e.nombre AS nombre_empresa, p.cantidad, p.numero_bodega from producto p JOIN empresa e ON e.id_empresa = p.id_empresa where p.id_empresa=?";
	private static final String UPDATE_PRODUCT_CANT_SQL = "update producto set cantidad=? where id_producto=?;";
	private static final String UPDATE_PRODUCT_BOD_SQL = "update producto set numero_bodega=? where id_empresa=?;";
	private static final String CANT_PRODUCT_SQL = "select cantidad from producto where id_producto=?;";
	private static final String INSERT_REGISTRO_SQL = "INSERT INTO registro" + "  (entrada, salida, id_producto, cantidad, usuario_recibe, usuario_entrega) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";
	
	public InventarioConexion() {
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
	
	public List<InventarioModel> selectAllProductos(int idEmpresa) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<InventarioModel> producto = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESA_BY_ID);) {
			preparedStatement.setInt(1, idEmpresa);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_producto");
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombre_empresa");
				int cantidad = rs.getInt("cantidad");
				int numero_bodega = rs.getInt("numero_bodega");
				producto.add(new InventarioModel(id, codigo, id_empresa, nombre, nombreEmpresa, cantidad, numero_bodega));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return producto;
	}
	
	public void insertProducto(InventarioModel producto) throws SQLException {
		System.out.println(INSERT_PRODUCT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
			preparedStatement.setString(1, producto.getCodigo());
			preparedStatement.setInt(2, producto.getId_empresa());
			preparedStatement.setString(3, producto.getNombre());
			preparedStatement.setInt(4, producto.getCantidad());
			preparedStatement.setInt(5, producto.getNumero_bodega());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertRegistro(RegistroModel producto) throws SQLException {
		System.out.println(INSERT_REGISTRO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REGISTRO_SQL)) {
			preparedStatement.setInt(1, producto.getEntrada());
			preparedStatement.setInt(2, producto.getSalida());
			preparedStatement.setInt(3, producto.getId_producto());
			preparedStatement.setInt(4, producto.getCantidad());
			preparedStatement.setInt(5, producto.getUsuario_recibe());
			preparedStatement.setInt(6, producto.getUsuario_entrega());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public InventarioModel selectProducto(int idEmpresa) {
		InventarioModel producto = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESA_BY_ID);) {
			preparedStatement.setInt(1, idEmpresa);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_producto");
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int id_empresa = rs.getInt("id_empresa");
				String nombreEmpresa = rs.getString("nombre_empresa");
				int cantidad = rs.getInt("cantidad");
				int numero_bodega = rs.getInt("numero_bodega");
				producto = new InventarioModel(id, codigo, id_empresa, nombre, nombreEmpresa, cantidad, numero_bodega);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return producto;
	}
	
	public InventarioModel selectCantProducto(int idProd) {
		InventarioModel producto = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(CANT_PRODUCT_SQL);) {
			preparedStatement.setInt(1, idProd);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int cantidad = rs.getInt("cantidad");
				producto = new InventarioModel(cantidad);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return producto;
	}
	
	public boolean updateProductoC(InventarioModel producto) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_CANT_SQL);) {
			statement.setInt(1, producto.getCantidad());
			statement.setInt(2, producto.getId());
			//System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean updateProductoB(InventarioModel producto) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_BOD_SQL);) {
			statement.setInt(1, producto.getNumero_bodega());
			statement.setInt(2, producto.getId());
			//System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
