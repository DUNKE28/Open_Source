package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IComprobanteService;
import com.rest.food.model.Comprobante;
import com.rest.food.model.repository.IComprobanteRepository;

@Service
@Transactional(readOnly=true)
public class ComprobanteServiceImpl implements IComprobanteService {

	@Autowired
	private IComprobanteRepository comprobanteRepository;
	
	@Override
	@Transactional
	public Comprobante registrar(Comprobante t) {
		return comprobanteRepository.save(t);
	}

	@Override
	@Transactional
	public Comprobante modificar(Comprobante t) {
		return comprobanteRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		comprobanteRepository.deleteById(id);
		
	}

	@Override
	public Optional<Comprobante> listarPorId(int id) {
		return comprobanteRepository.findById(id);
	}

	@Override
	public List<Comprobante> listar() {
		return comprobanteRepository.findAll();
	}

}
