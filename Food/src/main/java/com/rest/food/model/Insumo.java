package com.rest.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="insumo")
@Data
public class Insumo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable=false, length=15)
	private String nombre;
	
	@Column(name="precio", nullable=false)
	private double precio;
	
	@Size(min=1, message="Solo se puede ingresar un dígito")
    @Column(name="tipo", nullable=false, length=1)
	private int tipo;
}
