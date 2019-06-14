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
import com.rest.food.controller.service.IComprobanteService;
import com.rest.food.model.Comprobante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/comprobante")
@Api(tags="Comprobante", value="Servicio Web RESTFul de Comprobantes")
public class ComprobanteController {
	
	@Autowired
	private IComprobanteService comprobanteService;
	
	@PostMapping
	@ApiOperation(value="Crear Comprobante", notes="Servicio para crear un nuevo comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Comprobante creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<Comprobante> registrar(@Valid @RequestBody Comprobante comprobante){
		Comprobante comprobanteNew = new Comprobante();
		comprobanteNew = comprobanteService.registrar(comprobante);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(comprobanteNew.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Comprobante", notes="Servicio para actualizar un comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Comprobante actualizado correctamente"),
						  @ApiResponse(code=404, message="Comprobante no encontrado")})
	public ResponseEntity<Comprobante> actualizar(@Valid @RequestBody Comprobante comprobante){
		comprobanteService.modificar(comprobante);
		return new ResponseEntity<Comprobante>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Comprobante", notes="Servicio para eliminar un comprobante")
	@ApiResponses(value= {@ApiResponse(code=201, message="Comprobante eliminado correctamente"),
						  @ApiResponse(code=404, message="Comprobante no encontrado")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Comprobante> comprobante = comprobanteService.listarPorId(id);
		
		if(comprobante.isPresent())
			comprobanteService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Comprobantes", notes="Servicio para listar a todos los comprobantes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Comprobantes encontrados"),
						  @ApiResponse(code=404, message="Comprobantes no encontrados")})
	public ResponseEntity<List<Comprobante>> listar(){
		List<Comprobante> comprobantes = new ArrayList<Comprobante>();
		comprobantes = comprobanteService.listar();
		return new ResponseEntity<List<Comprobante>>(comprobantes,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Comprobante por Id", notes="Servicio para listar un Comprobante por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Comprobante encontrado"),
						  @ApiResponse(code=404, message="Comprobante no encontrado")})
	public ResponseEntity<Comprobante> listarPorId(@PathVariable("id") Integer id){
		Optional<Comprobante> comprobante = comprobanteService.listarPorId(id);
		if(!comprobante.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Comprobante>(comprobante.get(),HttpStatus.OK);
	}
	

}
