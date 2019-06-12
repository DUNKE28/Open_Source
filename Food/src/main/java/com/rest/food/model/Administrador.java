package com.rest.food.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="administrador")
@Data
public class Administrador {

	@Id
	@Column(name="dni", nullable=false, length=8)
	private Integer dni;
	
	@Column(name="nombre", nullable=false, length=30)
	private String nombre;
	
	@Size(min=8, message="La contraseña debe tener un mínimo de 8 digitos")
	@Column(name="contraseña", nullable=false, length=15)
	private String contraseña;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sede_id")
	private Sede sede;
}
