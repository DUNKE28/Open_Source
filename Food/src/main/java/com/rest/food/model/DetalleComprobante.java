package com.rest.food.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="detalleComprobante")
@Data
public class DetalleComprobante {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="descuento", nullable=true)
	private double descuento;
	
	@Column(name="precio", nullable=false)
	private double precio;
	
	@Column(name="cantidad", nullable=false)
	private int cantidad;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="detalleOrden_id")
	private DetalleOrden detalleOrden;
}

