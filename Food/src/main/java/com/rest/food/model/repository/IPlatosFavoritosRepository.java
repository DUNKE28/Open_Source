package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.PlatosFavoritos;

public interface IPlatosFavoritosRepository extends JpaRepository<PlatosFavoritos, Integer>{

}
