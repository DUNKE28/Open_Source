package com.rest.food.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="orden")
@Data
public class Orden {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="fecha", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name="horaEntrega", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date horaEntrega;
	

	@Column(name="estado", nullable=false, length=1)
	private int estado;
	
	@ManyToOne
	@JoinColumn(name="sede_id", nullable=false)
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	@OneToMany
	private List<DetalleOrden> detallesOrden;
}
