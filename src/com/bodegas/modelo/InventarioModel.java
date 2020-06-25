package com.bodegas.modelo;

public class InventarioModel {
	private int id;
	private String codigo;
	private int id_empresa;
	private String nombre;
	private String nombreEmpresa;
	private int cantidad;
	private int numero_bodega;
	
	public InventarioModel() {
	}
	
	
	//Sobrecarga de operadores para las diferentes opciones, dependiendo del tipo de request que se hará
	public InventarioModel(int cantidad) {
		super();
		this.cantidad = cantidad;
	}
	

	public InventarioModel(int id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}



	public InventarioModel(String codigo, int id_empresa, String nombre, int cantidad, int numero_bodega) {
		super();
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}



	public InventarioModel(int id, String codigo, int id_empresa, String nombre, String nombreEmpresa, int cantidad, int numero_bodega) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.nombreEmpresa = nombreEmpresa;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}

	public InventarioModel(int id, String codigo, int id_empresa, String nombre, int cantidad, int numero_bodega) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}

	public InventarioModel(int id, String codigo, int id_empresa, String nombre, int numero_bodega) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.numero_bodega = numero_bodega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getNumero_bodega() {
		return numero_bodega;
	}
	public void setNumero_bodega(int numero_bodega) {
		this.numero_bodega = numero_bodega;
	}
	
	
}
