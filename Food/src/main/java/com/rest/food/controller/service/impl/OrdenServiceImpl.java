package com.rest.food.controller.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IOrdenService;
import com.rest.food.model.Orden;
import com.rest.food.model.repository.IOrdenRepository;

@Service
@Transactional(readOnly=true)
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	@Transactional
	public Orden registrar(Orden t) {
		return ordenRepository.save(t);
	}

	@Override
	@Transactional
	public Orden modificar(Orden t) {
		return ordenRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		ordenRepository.deleteById(id);
	}

	@Override
	public Optional<Orden> listarPorId(int id) {
		return ordenRepository.findById(id);
	}

	@Override
	public List<Orden> listar() {
		return ordenRepository.findAll();
	}

}
