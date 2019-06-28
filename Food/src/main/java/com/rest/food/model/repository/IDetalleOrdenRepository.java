package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.DetalleOrden;

public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
	
}
