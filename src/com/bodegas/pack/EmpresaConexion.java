package com.bodegas.pack;

import com.bodegas.modelo.EmpresaModel;
import com.bodegas.modelo.UsuarioModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaConexion {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bodegas?useSSl=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_EMPRESA_SQL = "INSERT INTO empresa" + "  (nombre, telefono, direccion) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_EMPRESA_BY_ID = "select id_empresa,nombre,telefono, direccion from empresa where id_empresa=?";
	private static final String SELECT_ALL_EMPRESA = "select * from empresa where estado=1";
	private static final String SELECT_I_EMPRESA = "select * from empresa where estado=0";
	private static final String DELETE_EMPRESA_SQL = "update empresa set estado=0 where id_empresa=?;";
	private static final String ACTIVE_EMPRESA_SQL = "update empresa set estado=1 where id_empresa=?;";
	private static final String UPDATE_EMPRESA_SQL = "update empresa set nombre=?, telefono=?, direccion=? where id_empresa=?;";
	
	public EmpresaConexion() {
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
	
	public void insertEmpresa(EmpresaModel empresa) throws SQLException {
		System.out.println(INSERT_EMPRESA_SQL);
		// Si el try falla, cerrará la conexión
		try (Connection connection = getConnection();
				//Obtenemos el sql que vamos a usar para insertar
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPRESA_SQL)) {
			//Le mandamos los parámetros en el orden que va a recibirlos
			preparedStatement.setString(1, empresa.getNombre());
			preparedStatement.setString(2, empresa.getTelefono());
			preparedStatement.setString(3, empresa.getDireccion());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public EmpresaModel selectEmpresa(int id) {
		EmpresaModel empresa = null;
		// Step 1: Establecer la conexión
		try (Connection connection = getConnection();
				// Step 2:Crear un statement usando la sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPRESA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Ejecutar el query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Procesar los resultsets
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String direccion = rs.getString("direccion");
				empresa = new EmpresaModel(id, nombre, telefono, direccion);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return empresa;
	}
	
	public List<EmpresaModel> selectAllEmpresa() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<EmpresaModel> empresa = new ArrayList<>();
		// Step 1: Establecer la conexión
		try (Connection connection = getConnection();

				// Step 2:Crear el statement
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPRESA);) {
			System.out.println(preparedStatement);
			// Step 3: Ejecutar la query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Procesar el resultset
			while (rs.next()) {
				int id = rs.getInt("id_empresa");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String direccion = rs.getString("direccion");
				int estado = rs.getInt("estado");
				empresa.add(new EmpresaModel(id, nombre, telefono, direccion, estado));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return empresa;
	}
	
	public List<EmpresaModel> selectIEmpresa() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<EmpresaModel> empresa = new ArrayList<>();
		//Conexión
		try (Connection connection = getConnection();

				// Statement
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_I_EMPRESA);) {
			System.out.println(preparedStatement);
			// Ejecutar query
			ResultSet rs = preparedStatement.executeQuery();

			// Resultset
			while (rs.next()) {
				int id = rs.getInt("id_empresa");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String direccion = rs.getString("direccion");
				int estado = rs.getInt("estado");
				empresa.add(new EmpresaModel(id, nombre, telefono, direccion, estado));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return empresa;
	}
	
	public boolean deleteEmpresa(int id) throws SQLException {
		boolean rowDeleted;
		//Preparar la conexión
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPRESA_SQL);) {
			//Pasamos los parámetros
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean activarEmpresa(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ACTIVE_EMPRESA_SQL);) {
			//Pasamos los parámetros
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateEmpresa(EmpresaModel empresa) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPRESA_SQL);) {
			//Pasamos los parámetros para el update
			statement.setString(1, empresa.getNombre());
			statement.setString(2, empresa.getTelefono());
			statement.setString(3, empresa.getDireccion());
			statement.setInt(4, empresa.getId());
			//System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				//Procesamos las excepciones en caso que la aplicación falle
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
