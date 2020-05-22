package com.bodegas.modelo;

public class UsuarioModel {
	private int id;
	private String nombre;
	private String apellido;
	private String dui;
	private String direccion;
	private int telefono;
	private String username;
	private String correo;
	private int id_rol;
	private String contrasena;
	private int id_empresa;
	
	public UsuarioModel() {
	}
	
	public UsuarioModel(String nombre, String apellido, String dui, String direccion, int telefono, String username,
			String correo, int id_rol, String contrasena, int id_empresa) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dui = dui;
		this.direccion = direccion;
		this.telefono = telefono;
		this.username = username;
		this.correo = correo;
		this.id_rol = id_rol;
		this.contrasena = contrasena;
		this.id_empresa = id_empresa;
	}

	public UsuarioModel(int id, String nombre, String apellido, String dui, String direccion, int telefono,
			String username, String correo, int id_rol, int id_empresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dui = dui;
		this.direccion = direccion;
		this.telefono = telefono;
		this.username = username;
		this.correo = correo;
		this.id_rol = id_rol;
		this.id_empresa = id_empresa;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	
	
	
}