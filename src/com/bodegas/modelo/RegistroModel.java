package com.bodegas.modelo;

public class RegistroModel {
	private int id;
	private String fecha;
	private int entrada;
	private int salida;
	private int id_producto;
	private int cantidad;
	private int id_empresa;
	private int usuario_recibe;
	private int usuario_entrega;
	private String nombre_producto;
	private String nombre_empresa;
	private String entrega;
	private String recibe;
	
	
	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

	public int getId() {
		return id;
	}
	
	public RegistroModel() {
	}
	
	

	public RegistroModel(int id, String fecha, int entrada, int salida, int id_producto, int cantidad, int id_empresa,
			String nombre_producto, String nombre_empresa, String entrega, String recibe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.entrada = entrada;
		this.salida = salida;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.id_empresa = id_empresa;
		this.nombre_producto = nombre_producto;
		this.nombre_empresa = nombre_empresa;
		this.entrega = entrega;
		this.recibe = recibe;
	}

	public RegistroModel(int id, String fecha, int entrada, int salida, int id_producto, int cantidad,
			int usuario_recibe, int usuario_entrega, String nombre_producto, String nombre_empresa, String entrega,
			String recibe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.entrada = entrada;
		this.salida = salida;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.usuario_recibe = usuario_recibe;
		this.usuario_entrega = usuario_entrega;
		this.nombre_producto = nombre_producto;
		this.nombre_empresa = nombre_empresa;
		this.entrega = entrega;
		this.recibe = recibe;
	}

	public RegistroModel(int entrada, int salida, int id_producto, int cantidad, int usuario_recibe, int usuario_entrega) {
		super();
		this.entrada = entrada;
		this.salida = salida;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.usuario_recibe = usuario_recibe;
		this.usuario_entrega = usuario_entrega;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public String getRecibe() {
		return recibe;
	}

	public void setRecibe(String recibe) {
		this.recibe = recibe;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getEntrada() {
		return entrada;
	}
	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}
	public int getSalida() {
		return salida;
	}
	public void setSalida(int salida) {
		this.salida = salida;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public int getUsuario_recibe() {
		return usuario_recibe;
	}
	public void setUsuario_recibe(int usuario_recibe) {
		this.usuario_recibe = usuario_recibe;
	}
	public int getUsuario_entrega() {
		return usuario_entrega;
	}
	public void setUsuario_entrega(int usuario_entrega) {
		this.usuario_entrega = usuario_entrega;
	}
	
	
}
