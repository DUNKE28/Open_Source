package com.rest.food.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@ManyToMany(targetEntity = Sede.class)
	private Set<Sede> sedes;

	@ManyToMany(targetEntity = Insumo.class)
	private Set<Insumo> insumos;
}
