package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
