package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.food.model.Administrador;

public interface IAdministradorRepository extends JpaRepository<Administrador, Integer> {

}
