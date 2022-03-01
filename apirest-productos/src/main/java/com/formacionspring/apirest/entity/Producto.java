package com.formacionspring.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	/*
	 * Producto(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion)
	 */
	
	@Id
	private String codigo;
	
	private String tipo;
	private double cantidad;
	
	@Column(nullable=false)
	private String marca;
	
	@Column(name="fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fecha_ingreso;
	
	private String descripcion;
	
	@PrePersist
	public void prePersist() {
		if (fecha_ingreso == null) {
			fecha_ingreso = new Date();
		}
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/** */
	private static final long serialVersionUID = 1L;
}
