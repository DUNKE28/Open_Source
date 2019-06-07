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
@Table(name="menuInsumo")
@Data
public class MenuInsumo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="menu_id", nullable=false)
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name="insumo_id", nullable=false)
	private Insumo insumo;
}
