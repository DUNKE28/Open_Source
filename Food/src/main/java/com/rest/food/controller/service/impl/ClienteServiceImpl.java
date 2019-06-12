package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IClienteService;
import com.rest.food.model.Cliente;
import com.rest.food.model.repository.IClienteRepository;

@Service
@Transactional(readOnly=true)
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	@Transactional
	public Cliente registrar(Cliente t) {
		return clienteRepository.save(t);
	}

	@Override
	@Transactional
	public Cliente modificar(Cliente t) {
		return clienteRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		clienteRepository.deleteById(id);
		
	}

	@Override
	public Optional<Cliente> listarPorId(int id) {
		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

}
