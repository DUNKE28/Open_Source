package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.rest.food.controller.service.ISedeService;

import com.rest.food.model.Sede;

import com.rest.food.model.repository.ISedeRepository;

@Service
@Transactional(readOnly=true)
public class SedeServiceImpl implements ISedeService{

	@Autowired
	private ISedeRepository sedeRepository;
	
	@Override
	@Transactional
	public Sede registrar(Sede t) {
		return sedeRepository.save(t);
	}

	@Override
	@Transactional
	public Sede modificar(Sede t) {
		return sedeRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		sedeRepository.deleteById(id);
		
	}

	@Override
	public Optional<Sede> listarPorId(int id) {
		return sedeRepository.findById(id);
	}

	@Override
	public List<Sede> listar() {
		return sedeRepository.findAll();
	}

}
