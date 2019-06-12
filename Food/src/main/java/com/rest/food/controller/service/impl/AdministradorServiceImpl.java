package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rest.food.controller.service.IAdministradorService;
import com.rest.food.model.Administrador;
import com.rest.food.model.repository.IAdministradorRepository;

@Service
@Transactional(readOnly=true)
public class AdministradorServiceImpl implements IAdministradorService{

	@Autowired
	private IAdministradorRepository administradorRepository;
	
	@Override
	@Transactional
	public Administrador registrar(Administrador t) {
		return administradorRepository.save(t);
	}

	@Override
	@Transactional
	public Administrador modificar(Administrador t) {
		return administradorRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		administradorRepository.deleteById(id);
	}

	@Override
	public Optional<Administrador> listarPorId(int id) {
		return administradorRepository.findById(id);
	}

	@Override
	public List<Administrador> listar() {
		return administradorRepository.findAll();
	}
	
}
