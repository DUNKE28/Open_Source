package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.Menu;

public interface IMenuRepository extends JpaRepository<Menu, Integer>{
	
}
