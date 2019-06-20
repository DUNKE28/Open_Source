package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.Insumo;

public interface IInsumoRepository extends JpaRepository<Insumo, Integer> {
	
}
