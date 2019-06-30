package com.rest.food.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.food.Exception.ModelNotFoundException;
import com.rest.food.controller.service.IDetalleOrdenService;
import com.rest.food.model.DetalleOrden;
import com.rest.food.model.Insumo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/detalleorden")
@Api(tags="Detalle Orden", value="Servicio Web RESTFul de Detalle Orden")
public class DetalleOrdenController {
	@Autowired
	private IDetalleOrdenService detalleOrdenService;
	
	@PostMapping
	@ApiOperation(value="Crear Detalle Orden", notes="Servicio para crear un nuevo detalle orden")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Orden creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<DetalleOrden> registrar(@Valid @RequestBody DetalleOrden detalleOrden){
		DetalleOrden detalleOrdenNew = new DetalleOrden();
		detalleOrdenNew = detalleOrdenService.registrar(detalleOrden);
		return new ResponseEntity<DetalleOrden>(detalleOrdenNew,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Detalle Orden", notes="Servicio para actualizar un detalle orden")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Orden actualizado correctamente"),
						  @ApiResponse(code=404, message="Detalle Orden no encontrado")})
	public ResponseEntity<DetalleOrden> actualizar(@Valid @RequestBody DetalleOrden detalleOrden){
		detalleOrdenService.modificar(detalleOrden);
		return new ResponseEntity<DetalleOrden>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Detalle Orden", notes="Servicio para eliminar un detalle orden")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Orden eliminado correctamente"),
						  @ApiResponse(code=404, message="Detalle Orden no encontrado")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<DetalleOrden> detalleOrden = detalleOrdenService.listarPorId(id);
		
		if(detalleOrden.isPresent())
			detalleOrdenService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Detalle Orden", notes="Servicio para listar a todos los detalles ordenes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalles Ordenes encontrados"),
						  @ApiResponse(code=404, message="Detalles Ordenes no encontrados")})
	public ResponseEntity<List<DetalleOrden>> listar(){
		List<DetalleOrden> detallesOrdenes = new ArrayList<DetalleOrden>();
		detallesOrdenes = detalleOrdenService.listar();
		return new ResponseEntity<List<DetalleOrden>>(detallesOrdenes,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Detalle Orden por Id", notes="Servicio para listar un Detalle Orden por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Orden encontrado"),
						  @ApiResponse(code=404, message="Detalle Orden no encontrado")})
	public ResponseEntity<DetalleOrden> listarPorId(@PathVariable("id") Integer id){
		Optional<DetalleOrden> detalleOrden = detalleOrdenService.listarPorId(id);
		if(!detalleOrden.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<DetalleOrden>(detalleOrden.get(),HttpStatus.OK);
	}
	
	
}
