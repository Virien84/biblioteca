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

import es.seresco.biblioteca.controller.dto.LibroDto;
import es.seresco.biblioteca.controller.dto.NewLibroDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.LibrosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Libros" })
@RestController
@RequestMapping(path = "/api/libros")
@Slf4j
public class LibrosController {

	@Autowired
	private LibrosService librosService;

	@ApiOperation(value = "Devuelve libro por Id", notes = "Obtiene un libro según el parámetro Id")
	@GetMapping(path = "/{idLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LibroDto> getLibro(@PathVariable Long idLibro) {

		LibroDto libro = librosService.getLibro(idLibro);

		if (libro != null) {
			return ResponseEntity.status(HttpStatus.OK).body(libro);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un libro", notes = "Añade un libro pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LibroDto> createLibro(@Validated @RequestBody NewLibroDto newLibro) {

		LibroDto libro = librosService.createLibro(newLibro);

		if (libro != null) {
			return ResponseEntity.status(HttpStatus.OK).body(libro);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un libro", notes = "Actualiza un libro pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LibroDto> actualizaLibro(@Validated @RequestBody LibroDto updatedLibro)
			throws MiValidationException {

		LibroDto libro = librosService.actualizaLibro(updatedLibro);

		if (libro != null) {
			return ResponseEntity.status(HttpStatus.OK).body(libro);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los libros", notes = "Devuelve todos los libros de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LibroDto>> getLibros() throws MiValidationException {

		List<LibroDto> librosEncontrados = librosService.findLibros();

		if (librosEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(librosEncontrados);
		}
	}

	@ApiOperation(value = "Borra libro por Id", notes = "Borra un libro determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLibro(@PathVariable Long idLibro) throws MiValidationException {

		librosService.delete(idLibro);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
