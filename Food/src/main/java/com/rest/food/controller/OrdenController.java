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
import com.rest.food.controller.service.IOrdenService;
import com.rest.food.model.Orden;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/orden")
@Api(tags="Orden", value="Servicio Web RESTFul de Ordenes")
public class OrdenController {
	@Autowired
	private IOrdenService ordenService;
	
	//----REGISTRAR
		@PostMapping
		@ApiOperation(value="Crear Orden", notes="Servicio para crear una nueva orden")
		@ApiResponses(value= {@ApiResponse(code=201, message="Orden creada correctamente"),
							  @ApiResponse(code=400, message="Solicitud Inválida")})
		public ResponseEntity<Orden> registrar(@Valid @RequestBody Orden orden){
			Orden ordenNew = new Orden();
			ordenNew = ordenService.registrar(orden);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(ordenNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		//----MODIFICAR
		@PutMapping
		@ApiOperation(value="Actualizar Orden", notes="Servicio para actualizar una orden")
		@ApiResponses(value= {@ApiResponse(code=201, message="Orden actualizada correctamente"),
							  @ApiResponse(code=404, message="Orden no encontrada")})
		public ResponseEntity<Orden> actualizar(@Valid @RequestBody Orden orden){
			ordenService.modificar(orden);
			return new ResponseEntity<Orden>(HttpStatus.OK);
		}
		
		//----BORRAR
		@DeleteMapping(value="/{id}")
		@ApiOperation(value="Eliminar Orden", notes="Servicio para eliminar una orden")
		@ApiResponses(value= {@ApiResponse(code=201, message="Orden eliminada correctamente"),
							  @ApiResponse(code=404, message="Orden no encontrada")})
		public void eliminar(@PathVariable("id") Integer id) {
			Optional<Orden> orden = ordenService.listarPorId(id);
			
			if(orden.isPresent())
				ordenService.eliminar(id);		
			else		
				throw new ModelNotFoundException("ID "+id);
		}
		
		//----LISTAR
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value="Listar Ordenes", notes="Servicio para listar a todas las Ordenes")
		@ApiResponses(value= {@ApiResponse(code=201, message="Ordenes encontradas"),
							  @ApiResponse(code=404, message="Ordenes no encontradas")})
		public ResponseEntity<List<Orden>> listar(){
			List<Orden> ordenes = new ArrayList<Orden>();
			ordenes = ordenService.listar();
			return new ResponseEntity<List<Orden>>(ordenes,HttpStatus.OK);
		}
		
		//----LISTAR POR ID
		@GetMapping(value="/{id}")
		@ApiOperation(value="Listar Orden por Id", notes="Servicio para listar una Orden por Id")
		@ApiResponses(value= {@ApiResponse(code=201, message="Orden encontrada"),
							  @ApiResponse(code=404, message="Orden no encontrada")})
		public ResponseEntity<Orden> listarPorId(@PathVariable("id") Integer id){
			Optional<Orden> orden = ordenService.listarPorId(id);
			if(!orden.isPresent())
				throw new ModelNotFoundException("ID: "+id);
			
			return new ResponseEntity<Orden>(orden.get(),HttpStatus.OK);
		}
}
