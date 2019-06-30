package com.rest.food.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="cliente")
@Data
public class Cliente {
	
	@Id
	@Column(name="dni", nullable=false, length=8)
	private Integer dni;
	
	@Column(name="nombre", nullable=false, length=30)
	private String nombre;
	
    @Column(name="tipo", nullable=false)
	private int tipo;
	
	@Size(min=8, message="La contraseña debe tener un mínimo de 8 caracteres")
	@Column(name="contraseña", nullable=false, length=15)
	private String contraseña;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Orden> ordenes;
	@JsonIgnore
	@ManyToMany
	private List<Insumo> platosFavoritos;

}
