package com.rest.food.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name="horaEntrega", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date fecha;
	
	@OneToMany(mappedBy="menu")
	private List<MenuInsumo> menuInsumos;
}
