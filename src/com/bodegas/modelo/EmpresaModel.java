package com.bodegas.modelo;

public class EmpresaModel {
	private int id;
	private String nombre;
	private String telefono;
	private String direccion;
	private int estado;
	
	public EmpresaModel() {
	}
	//Sobrecarga de operadores para las diferentes opciones, dependiendo del tipo de request que se hará
	public EmpresaModel(int id, String nombre, String telefono, String direccion, int estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.estado = estado;
	}

	public EmpresaModel(String nombre, String telefono, String direccion, int estado) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.estado = estado;
	}

	public EmpresaModel(String nombre, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public EmpresaModel(int id, String nombre, String telefono, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
