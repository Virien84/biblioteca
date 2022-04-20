package es.seresco.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.seresco.biblioteca.controller.dto.CategoriaDto;
import es.seresco.biblioteca.controller.dto.NewCategoriaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.CategoriasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Categorias" })
@RestController
@RequestMapping(path = "/api/categorias")
@Slf4j
public class CategoriasController {

	@Autowired
	private CategoriasService categoriasService;

	@ApiOperation(value = "Devuelve categoria por Id", notes = "Obtiene un categoria según el parámetro Id")
	@GetMapping(path = "/{idCategoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaDto> getCategoria(@PathVariable Long idCategoria) {

		CategoriaDto categoria = categoriasService.getCategoria(idCategoria);

		if (categoria != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un categoria", notes = "Añade un categoria pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaDto> createCategoria(@Validated @RequestBody NewCategoriaDto newCategoria) {

		CategoriaDto categoria = categoriasService.createCategoria(newCategoria);

		if (categoria != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un categoria", notes = "Actualiza un categoria pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaDto> actualizaCategoria(@Validated @RequestBody CategoriaDto updatedCategoria)
			throws MiValidationException {

		CategoriaDto categoria = categoriasService.actualizaCategoria(updatedCategoria);

		if (categoria != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los categorias", notes = "Devuelve todos los categorias de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoriaDto>> getCategorias() throws MiValidationException {

		List<CategoriaDto> categoriasEncontrados = categoriasService.findCategorias();

		if (categoriasEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(categoriasEncontrados);
		}
	}

	@ApiOperation(value = "Borra categoria por Id", notes = "Borra un categoria determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idCategoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategoria(@PathVariable Long idCategoria) throws MiValidationException {

		categoriasService.delete(idCategoria);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
