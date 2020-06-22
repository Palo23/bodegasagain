package com.bodegas.modelo;

public class RegistroModel {
	private int id;
	private String fecha;
	private int entrada;
	private int salida;
	private int id_producto;
	private int cantidad;
	private int usuario_recibe;
	private int usuario_entrega;
	public int getId() {
		return id;
	}
	
	public RegistroModel() {
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
