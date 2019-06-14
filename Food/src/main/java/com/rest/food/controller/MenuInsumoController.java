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
import com.rest.food.controller.service.IMenuInsumoService;
import com.rest.food.model.MenuInsumo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/menuinsumo")
@Api(tags="Menu Insumo", value="Servicio Web RESTFul de Menu Insumo")
public class MenuInsumoController {
	@Autowired
	private IMenuInsumoService menuInsumoService;
	
	@PostMapping
	@ApiOperation(value="Crear Menu Insumo", notes="Servicio para crear un nuevo menu insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu Insumo creado correctamente"),
						  @ApiResponse(code=400, message="Solicitud Inválida")})
	
	public ResponseEntity<MenuInsumo> registrar(@Valid @RequestBody MenuInsumo menuInsumo){
		MenuInsumo menuInsumoNew = new MenuInsumo();
		menuInsumoNew = menuInsumoService.registrar(menuInsumo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(menuInsumoNew.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Actualizar Menu Insumo", notes="Servicio para actualizar un menu insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu Insumo actualizado correctamente"),
						  @ApiResponse(code=404, message="Menu Insumo no encontrado")})
	public ResponseEntity<MenuInsumo> actualizar(@Valid @RequestBody MenuInsumo menuInsumo){
		menuInsumoService.modificar(menuInsumo);
		return new ResponseEntity<MenuInsumo>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Eliminar Menu Insumo", notes="Servicio para eliminar un menu insumo")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu Insumo eliminado correctamente"),
						  @ApiResponse(code=404, message="Menu Insumo no encontrado")})
	
	public void eliminar(@PathVariable("id") Integer id) {
		Optional<MenuInsumo> menuInsumo = menuInsumoService.listarPorId(id);
		
		if(menuInsumo.isPresent())
			menuInsumoService.eliminar(id);		
		else		
			throw new ModelNotFoundException("ID "+id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Menus Insumos", notes="Servicio para listar a todos los menus insumos")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menus Insumos encontrados"),
						  @ApiResponse(code=404, message="Menus Insumos no encontrados")})
	
	public ResponseEntity<List<MenuInsumo>> listar(){
		List<MenuInsumo> menusInsumos = new ArrayList<MenuInsumo>();
		menusInsumos = menuInsumoService.listar();
		return new ResponseEntity<List<MenuInsumo>>(menusInsumos,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value="Listar Menu Insumo por Id", notes="Servicio para listar un Menu Insumo por Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Menu Insumo encontrado"),
						  @ApiResponse(code=404, message="Menu Insumo no encontrado")})
	
	public ResponseEntity<MenuInsumo> listarPorId(@PathVariable("id") Integer id){
		Optional<MenuInsumo> menuInsumo = menuInsumoService.listarPorId(id);
		if(!menuInsumo.isPresent())
			throw new ModelNotFoundException("ID: "+id);
		
		return new ResponseEntity<MenuInsumo>(menuInsumo.get(),HttpStatus.OK);
	}
	
}
