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
import com.rest.food.controller.service.ISedeService;
import com.rest.food.model.Orden;
import com.rest.food.model.Sede;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/sede")
@Api(tags="Sede", value="Servicio Web RESTFul de Sedes")
public class SedeController {
	@Autowired
	private ISedeService sedeService;
	
	@PostMapping
	@ApiOperation(value="Crear Sede", notes="Servicio para crear una nueva Sede")
	@ApiResponses(value= {@ApiResponse(code=201, message="Sede creada correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<Sede> registrar(@Valid @RequestBody Sede sede){
		Sede sedeNew = new Sede();
		sedeNew = sedeService.registrar(sede);
		return new ResponseEntity<Sede>(sedeNew,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Sede", notes="Servicio para actualizar una sede")
	@ApiResponses(value= {@ApiResponse(code=201, message="Sede actualizada correctamente"),
						  @ApiResponse(code=404, message="Sede no encontrada")})
	public ResponseEntity<Sede> actualizar(@Valid @RequestBody Sede sede){
		sedeService.modificar(sede);
		return new ResponseEntity<Sede>(HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Sede", notes="Servicio para eliminar un sede")
	@ApiResponses(value= {@ApiResponse(code=201, message="Sede eliminada correctamente"),
						  @ApiResponse(code=404, message="Sede no encontrada")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Sede> sede = sedeService.listarPorId(id);
		
		if(sede.isPresent())
			sedeService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Sedes", notes="Servicio para listar a todas las sedes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Sedes encontradas"),
						  @ApiResponse(code=404, message="Sedes no encontradas")})
	public ResponseEntity<List<Sede>> listar(){
		List<Sede> sedes = new ArrayList<Sede>();
		sedes = sedeService.listar();
		return new ResponseEntity<List<Sede>>(sedes,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Sede por Id", notes="Servicio para listar una sede por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Sede encontrada"),
						  @ApiResponse(code=404, message="Sede no encontrada")})
	public ResponseEntity<Sede> listarPorId(@PathVariable("id") Integer id){
		Optional<Sede> sede = sedeService.listarPorId(id);
		if(!sede.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Sede>(sede.get(),HttpStatus.OK);
	}
}
