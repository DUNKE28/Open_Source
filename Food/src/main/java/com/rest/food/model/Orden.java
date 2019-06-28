package com.rest.food.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="orden")
@Data
public class Orden {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="horaEntrega")
	private Date horaEntrega;
	

	@Column(name="estado", length=1)
	private int estado;
	
	@ManyToOne
	@JoinColumn(name="sede_id")
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "orden", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleOrden> detallesOrden;
}
