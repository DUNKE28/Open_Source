package com.rest.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="detalleOrden")
@Data
public class DetalleOrden {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="precio", nullable=false)
	private double precio;
	
	@Column(name="cantidad", nullable=false)
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="insumo_id")
	private Insumo insumo;
	
	@ManyToOne	
	@JoinColumn(name="orden_id")
	private Orden orden;

	@OneToOne
	private DetalleComprobante detalleComprobante;
}
