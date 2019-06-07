package com.rest.food.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="platosFavoritos")
@Data
public class PlatosFavoritos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="insumo_id", nullable=false)
	private Insumo insumo;
}
