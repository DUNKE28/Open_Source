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
import com.rest.food.controller.service.IAdministradorService;
import com.rest.food.model.Administrador;
import com.rest.food.model.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/administrador")
@Api(tags="Administrador", value="Servicio Web RESTFul de Administradores")
public class AdministradorController {
	
	@Autowired
	private IAdministradorService administradorService;
	
	//----REGISTRAR
	@PostMapping
	@ApiOperation(value="Crear Administrador", notes="Servicio para crear un nuevo administrador")
	@ApiResponses(value= {@ApiResponse(code=201, message="Administrador creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<Administrador> registrar(@Valid @RequestBody Administrador administrador){
		Administrador administradorNew = new Administrador();
		administradorNew = administradorService.registrar(administrador);
		return  new ResponseEntity<Administrador>(administradorNew,HttpStatus.OK);
	}
	
	//----MODIFICAR
	@PutMapping
	@ApiOperation(value="Actualizar Administrador", notes="Servicio para actualizar un administrador")
	@ApiResponses(value= {@ApiResponse(code=201, message="Administrador actualizado correctamente"),
						  @ApiResponse(code=404, message="Administrador no encontrado")})
	public ResponseEntity<Administrador> actualizar(@Valid @RequestBody Administrador administrador){
		administradorService.modificar(administrador);
		return new ResponseEntity<Administrador>(HttpStatus.OK);
	}
	
	//----BORRAR
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Administrador", notes="Servicio para eliminar un administrador")
	@ApiResponses(value= {@ApiResponse(code=201, message="Administrador eliminado correctamente"),
						  @ApiResponse(code=404, message="Administrador no encontrado")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Administrador> administrador = administradorService.listarPorId(id);
		
		if(administrador.isPresent())
			administradorService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	//----LISTAR
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Administrador", notes="Servicio para listar a todos los Administradores")
	@ApiResponses(value= {@ApiResponse(code=201, message="Administradores encontrados"),
						  @ApiResponse(code=404, message="Administradores no encontrados")})
	public ResponseEntity<List<Administrador>> listar(){
		List<Administrador> administradores = new ArrayList<Administrador>();
		administradores = administradorService.listar();
		return new ResponseEntity<List<Administrador>>(administradores,HttpStatus.OK);
	}
	
	//----LISTAR POR ID
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Administrador por Id", notes="Servicio para listar un administrador por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Administrador encontrado"),
						  @ApiResponse(code=404, message="Administrador no encontrado")})
	public ResponseEntity<Administrador> listarPorId(@PathVariable("id") Integer id){
		Optional<Administrador> administrador = administradorService.listarPorId(id);
		if(!administrador.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Administrador>(administrador.get(),HttpStatus.OK);
	}
}
