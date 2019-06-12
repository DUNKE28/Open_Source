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
import com.rest.food.controller.service.IPlatosFavoritosService;
import com.rest.food.model.PlatosFavoritos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/platosfavoritos")
@Api(tags="Platos Favoritos", value="Servicio Web RESTFul de Platos Favoritos")
public class PlatosFavoritosController {
	@Autowired
	private IPlatosFavoritosService platosFavoritosService;

	//----REGISTRAR
		@PostMapping
		@ApiOperation(value="Crear Plato Favorito", notes="Servicio para crear un nuevo Plato Favorito")
		@ApiResponses(value= {@ApiResponse(code=201, message="Plato Favorito creado correctamente"),
							  @ApiResponse(code=400, message="Solicitud Inválida")})
		public ResponseEntity<PlatosFavoritos> registrar(@Valid @RequestBody PlatosFavoritos platoFavorito){
			PlatosFavoritos platoFavoritoNew = new PlatosFavoritos();
			platoFavoritoNew = platosFavoritosService.registrar(platoFavorito);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(platoFavoritoNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		//----MODIFICAR
		@PutMapping
		@ApiOperation(value="Actualizar Plato Favorito", notes="Servicio para actualizar un Plato Favorito")
		@ApiResponses(value= {@ApiResponse(code=201, message="Plato Favorito actualizado correctamente"),
							  @ApiResponse(code=404, message="Plato Favorito no encontrado")})
		public ResponseEntity<PlatosFavoritos> actualizar(@Valid @RequestBody PlatosFavoritos platoFavorito){
			platosFavoritosService.modificar(platoFavorito);
			return new ResponseEntity<PlatosFavoritos>(HttpStatus.OK);
		}
		
		//----BORRAR
		@DeleteMapping(value="/{id}")
		@ApiOperation(value="Eliminar Plato Favorito", notes="Servicio para eliminar un Plato Favorito")
		@ApiResponses(value= {@ApiResponse(code=201, message="Plato Favorito eliminado correctamente"),
							  @ApiResponse(code=404, message="Plato Favorito no encontrado")})
		public void eliminar(@PathVariable("id") Integer id) {
			Optional<PlatosFavoritos> platoFavorito = platosFavoritosService.listarPorId(id);
			
			if(platoFavorito.isPresent())
				platosFavoritosService.eliminar(id);		
			else		
				throw new ModelNotFoundException("ID "+id);
		}
		
		//----LISTAR
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value="Listar Platos Favoritos", notes="Servicio para listar a todos los Platos Favoritos")
		@ApiResponses(value= {@ApiResponse(code=201, message="Platos Favoritos encontrados"),
							  @ApiResponse(code=404, message="Platos Favoritos no encontrados")})
		public ResponseEntity<List<PlatosFavoritos>> listar(){
			List<PlatosFavoritos> platosFavoritos = new ArrayList<PlatosFavoritos>();
			platosFavoritos = platosFavoritosService.listar();
			return new ResponseEntity<List<PlatosFavoritos>>(platosFavoritos,HttpStatus.OK);
		}
		
		//----LISTAR POR ID
		@GetMapping(value="/{id}")
		@ApiOperation(value="Listar Plato Favorito por Id", notes="Servicio para listar un Plato Favorito por Id")
		@ApiResponses(value= {@ApiResponse(code=201, message="Plato Favorito encontrado"),
							  @ApiResponse(code=404, message="Plato Favorito no encontrado")})
		public ResponseEntity<PlatosFavoritos> listarPorId(@PathVariable("id") Integer id){
			Optional<PlatosFavoritos> platoFavorito = platosFavoritosService.listarPorId(id);
			if(!platoFavorito.isPresent())
				throw new ModelNotFoundException("ID: "+id);
			
			return new ResponseEntity<PlatosFavoritos>(platoFavorito.get(),HttpStatus.OK);
		}
}
