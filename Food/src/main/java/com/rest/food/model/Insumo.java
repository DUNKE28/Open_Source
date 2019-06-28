package com.rest.food.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="insumo")
@Data
public class Insumo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Column(name="precio", nullable=false)
	private double precio;
	
    @Column(name="tipo", nullable=false)
	private int tipo;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Menu.class)
	private Set<Menu> menus;
	
	
	
}
