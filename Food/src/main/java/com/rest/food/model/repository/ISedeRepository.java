package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.Sede;

public interface ISedeRepository extends JpaRepository<Sede, Integer> {
	
}
