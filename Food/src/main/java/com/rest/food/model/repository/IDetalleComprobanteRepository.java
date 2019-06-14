package com.rest.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.food.model.DetalleComprobante;

public interface IDetalleComprobanteRepository extends JpaRepository<DetalleComprobante, Integer> {

}
