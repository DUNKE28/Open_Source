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
import com.rest.food.controller.service.IClienteService;
import com.rest.food.model.Cliente;
import com.rest.food.model.Comprobante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/cliente")
@Api(tags="Cliente", value="Servicio Web RESTFul de Clientes")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping
	@ApiOperation(value="Crear Cliente", notes="Servicio para crear un nuevo cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente){
		Cliente clienteNew = new Cliente();
		clienteNew = clienteService.registrar(cliente);
		return new ResponseEntity<Cliente>(clienteNew,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Cliente", notes="Servicio para actualizar un cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente actualizado correctamente"),
						  @ApiResponse(code=404, message="Cliente no encontrado")})
	public ResponseEntity<Cliente> actualizar(@Valid @RequestBody Cliente cliente){
		clienteService.modificar(cliente);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Cliente", notes="Servicio para eliminar un cliente")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente eliminado correctamente"),
						  @ApiResponse(code=404, message="Cliente no encontrado")})
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clienteService.listarPorId(id);
		
		if(cliente.isPresent())
			clienteService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Clientes", notes="Servicio para listar a todos los clientes")
	@ApiResponses(value= {@ApiResponse(code=201, message="Clientes encontrados"),
						  @ApiResponse(code=404, message="Clientes no encontrados")})
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Cliente por Id", notes="Servicio para listar un cliente por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Cliente encontrado"),
						  @ApiResponse(code=404, message="Cliente no encontrado")})
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id){
		Optional<Cliente> cliente = clienteService.listarPorId(id);
		if(!cliente.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Cliente>(cliente.get(),HttpStatus.OK);
	}
}
