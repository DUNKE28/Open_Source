package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IMenuInsumoService;
import com.rest.food.model.MenuInsumo;
import com.rest.food.model.repository.IMenuInsumoRepository;

@Service
@Transactional(readOnly=true)
public class MenuInsumoServiceImpl implements IMenuInsumoService {
	
	@Autowired
	private IMenuInsumoRepository menuInsumoRepository;
	
	@Override
	@Transactional
	public MenuInsumo registrar(MenuInsumo t) {
		return menuInsumoRepository.save(t);
	}

	@Override
	@Transactional
	public MenuInsumo modificar(MenuInsumo t) {
		return menuInsumoRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		menuInsumoRepository.deleteById(id);
		
	}

	@Override
	public Optional<MenuInsumo> listarPorId(int id) {
		return menuInsumoRepository.findById(id);
	}

	@Override
	public List<MenuInsumo> listar() {
		return menuInsumoRepository.findAll();
	}
}
