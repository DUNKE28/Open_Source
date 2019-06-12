package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IMenuService;
import com.rest.food.model.Menu;
import com.rest.food.model.repository.IMenuRepository;

@Service
@Transactional(readOnly=true)
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private IMenuRepository menuRepository;
	
	@Override
	@Transactional
	public Menu registrar(Menu t) {
		return menuRepository.save(t);
	}

	@Override
	@Transactional
	public Menu modificar(Menu t) {
		return menuRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		menuRepository.deleteById(id);
		
	}

	@Override
	public Optional<Menu> listarPorId(int id) {
		return menuRepository.findById(id);
	}

	@Override
	public List<Menu> listar() {
		return menuRepository.findAll();
	}
}
