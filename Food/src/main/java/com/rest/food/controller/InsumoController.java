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
import com.rest.food.controller.service.IInsumoService;
import com.rest.food.model.Insumo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/insumo")
@Api(tags="Insumo", value="Servicio Web RESTFul de Insumos")
public class InsumoController {

	@Autowired
	private IInsumoService insumoService;
	
	@PostMapping
	@ApiOperation(value="Crear Insumo", notes="Servicio para crear un nuevo insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Insumo creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	
	public ResponseEntity<Insumo> registrar(@Valid @RequestBody Insumo insumo){
		Insumo insumoNew = new Insumo();
		insumoNew = insumoService.registrar(insumo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(insumoNew.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Insumo", notes="Servicio para actualizar un insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Insumo actualizado correctamente"),
						  @ApiResponse(code=404, message="Insumo no encontrado")})
	public ResponseEntity<Insumo> actualizar(@Valid @RequestBody Insumo insumo){
		insumoService.modificar(insumo);
		return new ResponseEntity<Insumo>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Insumo", notes="Servicio para eliminar un insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Insumo eliminado correctamente"),
						  @ApiResponse(code=404, message="Insumo no encontrado")})
	
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Insumo> cliente = insumoService.listarPorId(id);
		
		if(cliente.isPresent())
			insumoService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Insumo", notes="Servicio para listar a todos los insumos")
	@ApiResponses(value= {@ApiResponse(code=201, message="Insumos encontrados"),
						  @ApiResponse(code=404, message="Insumos no encontrados")})
	
	public ResponseEntity<List<Insumo>> listar(){
		List<Insumo> insumos = new ArrayList<Insumo>();
		insumos = insumoService.listar();
		return new ResponseEntity<List<Insumo>>(insumos,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Insumo por Id", notes="Servicio para listar un Insumo por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Insumo encontrado"),
						  @ApiResponse(code=404, message="Insumo no encontrado")})
	
	public ResponseEntity<Insumo> listarPorId(@PathVariable("id") Integer id){
		Optional<Insumo> insumo = insumoService.listarPorId(id);
		if(!insumo.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Insumo>(insumo.get(),HttpStatus.OK);
	}
}
