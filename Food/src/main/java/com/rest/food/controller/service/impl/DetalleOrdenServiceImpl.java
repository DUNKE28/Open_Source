package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IDetalleOrdenService;
import com.rest.food.model.DetalleOrden;
import com.rest.food.model.repository.IDetalleOrdenRepository;

@Service
@Transactional(readOnly=true)
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;
	
	@Override
	@Transactional
	public DetalleOrden registrar(DetalleOrden t) {
		return detalleOrdenRepository.save(t);
	}

	@Override
	@Transactional
	public DetalleOrden modificar(DetalleOrden t) {
		return detalleOrdenRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		detalleOrdenRepository.deleteById(id);
	}

	@Override
	public Optional<DetalleOrden> listarPorId(int id) {
		return detalleOrdenRepository.findById(id);
	}

	@Override
	public List<DetalleOrden> listar() {
		return detalleOrdenRepository.findAll();
	}

}
