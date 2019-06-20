package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IInsumoService;
import com.rest.food.model.Insumo;
import com.rest.food.model.repository.IInsumoRepository;

@Service
@Transactional(readOnly=true)
public class InsumoServiceImpl implements IInsumoService{

	@Autowired
	private IInsumoRepository insumoRepository;
	
	@Override
	@Transactional
	public Insumo registrar(Insumo t) {
		return insumoRepository.save(t);
	}

	@Override
	@Transactional
	public Insumo modificar(Insumo t) {
		return insumoRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		insumoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Insumo> listarPorId(int id) {
		return insumoRepository.findById(id);
	}

	@Override
	public List<Insumo> listar() {
		return insumoRepository.findAll();
	}
	
}
