package com.bodegas.modelo;

public class InventarioModel {
	private int id;
	private int codigo;
	private int id_empresa;
	private String nombre;
	private String nombreEmpresa;
	private int cantidad;
	private int numero_bodega;
	
	public InventarioModel() {
	}
	
	
	
	public InventarioModel(int codigo, int id_empresa, String nombre, int cantidad, int numero_bodega) {
		super();
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}



	public InventarioModel(int id, int codigo, int id_empresa, String nombre, String nombreEmpresa, int cantidad, int numero_bodega) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.nombreEmpresa = nombreEmpresa;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}

	public InventarioModel(int id, int codigo, int id_empresa, String nombre, int cantidad, int numero_bodega) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.numero_bodega = numero_bodega;
	}

	public InventarioModel(int id, int codigo, int id_empresa, String nombre, int numero_bodega) {
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
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
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
