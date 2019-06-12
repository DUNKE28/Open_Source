package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.Orden;

public interface IOrdenRepository extends JpaRepository<Orden, Integer>{

}
