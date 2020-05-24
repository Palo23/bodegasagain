package com.bodegas.pack;
import com.bodegas.modelo.UsuarioModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UsuarioConexion {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bodegas?useSSl=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO cuenta" + "  (nombre, apellido, dui, direccion, telefono, nombre_usuario, correo, id_rol, contrasena, id_empresa) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id_usuario,nombre,apellido,dui, direccion, telefono, nombre_usuario, correo, id_rol, id_empresa from cuenta where id_usuario =?";
	private static final String SELECT_ALL_USERS = "select * from cuenta";
	private static final String DELETE_USERS_SQL = "delete from cuenta where id_usuario = ?;";
	private static final String UPDATE_USERS_SQL = "update cuenta set nombre = ?,nombre_usuario= ?, correo =? where id_usuario = ?;";
	
	public UsuarioConexion() {
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
	
	public void insertUser(UsuarioModel user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getApellido());
			preparedStatement.setString(3, user.getDui());
			preparedStatement.setString(4, user.getDireccion());
			preparedStatement.setInt(5, user.getTelefono());
			preparedStatement.setString(6, user.getUsername());
			preparedStatement.setString(7, user.getCorreo());
			preparedStatement.setInt(8, user.getId_rol());
			preparedStatement.setString(9, user.getContrasena());
			preparedStatement.setInt(10, user.getId_empresa());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public UsuarioModel selectUser(int id) {
		UsuarioModel user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dui = rs.getString("dui");
				String direccion = rs.getString("direccion");
				int telefono = rs.getInt("telefono");
				String username = rs.getString("nombre_usuario");
				String correo = rs.getString("correo");
				int idRol = rs.getInt("id_rol");
				int idEmpresa = rs.getInt("id_empresa");
				user = new UsuarioModel(id, nombre, apellido, dui, direccion, telefono, username, correo, idRol, idEmpresa);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public List<UsuarioModel> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<UsuarioModel> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dui = rs.getString("dui");
				String direccion = rs.getString("direccion");
				int telefono = rs.getInt("telefono");
				String username = rs.getString("nombre_usuario");
				String correo = rs.getString("correo");
				int idRol = rs.getInt("id_rol");
				int idEmpresa = rs.getInt("id_empresa");
				users.add(new UsuarioModel(id, nombre, apellido, dui, direccion, telefono, username, correo, idRol, idEmpresa));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateUser(UsuarioModel user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getNombre());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getCorreo());
			statement.setInt(4, user.getId());

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
