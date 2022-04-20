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

import es.seresco.biblioteca.controller.dto.AutorDto;
import es.seresco.biblioteca.controller.dto.NewAutorDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.AutoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Autores" })
@RestController
@RequestMapping(path = "/api/autores")
@Slf4j
public class AutoresController {

	@Autowired
	private AutoresService autoresService;

	@ApiOperation(value = "Devuelve autor por Id", notes = "Obtiene un autor según el parámetro Id")
	@GetMapping(path = "/{idAutor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AutorDto> getAutor(@PathVariable Long idAutor) {

		AutorDto autor = autoresService.getAutor(idAutor);

		if (autor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(autor);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un autor", notes = "Añade un autor pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AutorDto> createAutor(@Validated @RequestBody NewAutorDto newAutor) {

		AutorDto autor = autoresService.createAutor(newAutor);

		if (autor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(autor);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un autor", notes = "Actualiza un autor pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AutorDto> actualizaAutor(@Validated @RequestBody AutorDto updatedAutor)
			throws MiValidationException {

		AutorDto autor = autoresService.actualizaAutor(updatedAutor);

		if (autor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(autor);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los autores", notes = "Devuelve todos los autores de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AutorDto>> getAutores() throws MiValidationException {

		List<AutorDto> autoresEncontrados = autoresService.findAutores();

		if (autoresEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(autoresEncontrados);
		}
	}

	@ApiOperation(value = "Borra autor por Id", notes = "Borra un autor determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idAutor}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAutor(@PathVariable Long idAutor) throws MiValidationException {

		autoresService.delete(idAutor);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
