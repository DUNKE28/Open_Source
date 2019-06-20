package com.rest.food.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="menu")
@Data
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable=false, length=15)
	private String nombre;
	
	@Column(name="fecha", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToMany
	private List<Insumo> insumos;
}
