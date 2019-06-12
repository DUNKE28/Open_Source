package com.rest.food.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.food.controller.service.IPlatosFavoritosService;
import com.rest.food.model.PlatosFavoritos;
import com.rest.food.model.repository.IPlatosFavoritosRepository;

@Service
@Transactional(readOnly=true)
public class PlatosFavoritosService implements IPlatosFavoritosService{

	@Autowired
	private IPlatosFavoritosRepository platosFavoritosRepository;
	
	@Override
	@Transactional
	public PlatosFavoritos registrar(PlatosFavoritos t) {
		return platosFavoritosRepository.save(t);
	}

	@Override
	@Transactional
	public PlatosFavoritos modificar(PlatosFavoritos t) {
		return platosFavoritosRepository.save(t);
	}

	@Override
	@Transactional
	public void eliminar(int id) {
		platosFavoritosRepository.deleteById(id);
	}

	@Override
	public Optional<PlatosFavoritos> listarPorId(int id) {
		return platosFavoritosRepository.findById(id);
	}

	@Override
	public List<PlatosFavoritos> listar() {
		return platosFavoritosRepository.findAll();
	}

}
