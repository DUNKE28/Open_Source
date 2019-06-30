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
import com.rest.food.controller.service.IDetalleComprobanteService;
import com.rest.food.model.DetalleComprobante;
import com.rest.food.model.DetalleOrden;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/detallecomprobante")
@Api(tags="Detalle Comprobante", value="Servicio Web RESTFul de Detalle Comprobante")
public class DetalleComprobanteController {
	@Autowired
	private IDetalleComprobanteService detalleComprobanteService;
	
	@PostMapping
	@ApiOperation(value="Crear Detalle Comprobante", notes="Servicio para crear un nuevo detalle comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Comprobante creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<DetalleComprobante> registrar(@Valid @RequestBody DetalleComprobante detalleComprobante){
		DetalleComprobante detalleComprobanteNew = new DetalleComprobante();
		detalleComprobanteNew = detalleComprobanteService.registrar(detalleComprobante);
		return new ResponseEntity<DetalleComprobante>(detalleComprobanteNew,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Detalle Comprobante", notes="Servicio para actualizar un detalle comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Comprobante actualizado correctamente"),
						  @ApiResponse(code=404, message="Detalle Comprobante no encontrado")})
	public ResponseEntity<DetalleComprobante> actualizar(@Valid @RequestBody DetalleComprobante detalleComprobante){
		detalleComprobanteService.modificar(detalleComprobante);
		return new ResponseEntity<DetalleComprobante>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Detalle Comprobante", notes="Servicio para eliminar un detalle comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Comprobante eliminado correctamente"),
						  @ApiResponse(code=404, message="Detalle Comprobante no encontrado")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<DetalleComprobante> detalleComprobante = detalleComprobanteService.listarPorId(id);
		
		if(detalleComprobante.isPresent())
			detalleComprobanteService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Detalles Comprobantes", notes="Servicio para listar a todos los detalles comprobantes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalles Comprobantes encontrados"),
						  @ApiResponse(code=404, message="Detalles Comprobantes no encontrados")})
	public ResponseEntity<List<DetalleComprobante>> listar(){
		List<DetalleComprobante> detallesComprobantes = new ArrayList<DetalleComprobante>();
		detallesComprobantes = detalleComprobanteService.listar();
		return new ResponseEntity<List<DetalleComprobante>>(detallesComprobantes,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Detalle Comprobante por Id", notes="Servicio para listar un Detalle Comprobante por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Detalle Comprobante encontrado"),
						  @ApiResponse(code=404, message="Detalle Comprobante no encontrado")})
	public ResponseEntity<DetalleComprobante> listarPorId(@PathVariable("id") Integer id){
		Optional<DetalleComprobante> detalleComprobante = detalleComprobanteService.listarPorId(id);
		if(!detalleComprobante.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<DetalleComprobante>(detalleComprobante.get(),HttpStatus.OK);
	}
	
	
	
	
}
