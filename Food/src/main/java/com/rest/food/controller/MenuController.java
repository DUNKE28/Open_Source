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
import com.rest.food.controller.service.IMenuService;
import com.rest.food.model.Menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/menu")
@Api(tags="Menu", value="Servicio Web RESTFul de Menus")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@PostMapping
	@ApiOperation(value="Crear Menu", notes="Servicio para crear un nuevo menu")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	
	public ResponseEntity<Menu> registrar(@Valid @RequestBody Menu menu){
		Menu menuNew = new Menu();
		menuNew = menuService.registrar(menu);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(menuNew.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Menu", notes="Servicio para actualizar un menu")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu actualizado correctamente"),
						  @ApiResponse(code=404, message="Menu no encontrado")})
	public ResponseEntity<Menu> actualizar(@Valid @RequestBody Menu menu){
		menuService.modificar(menu);
		return new ResponseEntity<Menu>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Menu", notes="Servicio para eliminar un menu")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu eliminado correctamente"),
						  @ApiResponse(code=404, message="Menu no encontrado")})
	
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<Menu> menu = menuService.listarPorId(id);
		
		if(menu.isPresent())
			menuService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Menus", notes="Servicio para listar a todos los menus")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menus encontrados"),
						  @ApiResponse(code=404, message="Menus no encontrados")})
	
	public ResponseEntity<List<Menu>> listar(){
		List<Menu> menus = new ArrayList<Menu>();
		menus = menuService.listar();
		return new ResponseEntity<List<Menu>>(menus,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Menu por Id", notes="Servicio para listar un Menu por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu encontrado"),
						  @ApiResponse(code=404, message="Menu no encontrado")})
	
	public ResponseEntity<Menu> listarPorId(@PathVariable("id") Integer id){
		Optional<Menu> menu = menuService.listarPorId(id);
		if(!menu.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<Menu>(menu.get(),HttpStatus.OK);
	}
}
