package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IDetalleComprobanteService;
import com.rest.food.model.DetalleComprobante;
import com.rest.food.model.repository.IDetalleComprobanteRepository;

@Service
@Transactional(readOnly=true)
public class DetalleComprobanteServiceImpl implements IDetalleComprobanteService {

	@Autowired
	private IDetalleComprobanteRepository detalleComprobanteRepository;
	
	@Override
	@Transactional
	public DetalleComprobante registrar(DetalleComprobante t) {
		return detalleComprobanteRepository.save(t);
	}

	@Override
	@Transactional
	public DetalleComprobante modificar(DetalleComprobante t) {
		return detalleComprobanteRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		detalleComprobanteRepository.deleteById(id);
		
	}

	@Override
	public Optional<DetalleComprobante> listarPorId(int id) {
		return detalleComprobanteRepository.findById(id);
	}

	@Override
	public List<DetalleComprobante> listar() {
		return detalleComprobanteRepository.findAll(); 
	}

}
