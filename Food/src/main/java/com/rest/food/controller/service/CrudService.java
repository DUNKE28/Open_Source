package com.rest.food.controller.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
	T registrar(T t);
	T modificar(T t);
	void eliminar(int id);
	Optional<T> listarPorId(int id);
	List<T> listar();
}
