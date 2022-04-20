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

import es.seresco.biblioteca.controller.dto.CopiaDto;
import es.seresco.biblioteca.controller.dto.NewCopiaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.CopiasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Copias" })
@RestController
@RequestMapping(path = "/api/copias")
@Slf4j
public class CopiasController {

	@Autowired
	private CopiasService copiasService;

	@ApiOperation(value = "Devuelve copia por Id", notes = "Obtiene un copia según el parámetro Id")
	@GetMapping(path = "/{idCopia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CopiaDto> getCopia(@PathVariable Long idCopia) {

		CopiaDto copia = copiasService.getCopia(idCopia);

		if (copia != null) {
			return ResponseEntity.status(HttpStatus.OK).body(copia);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un copia", notes = "Añade un copia pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CopiaDto> createCopia(@Validated @RequestBody NewCopiaDto newCopia) {

		CopiaDto copia = copiasService.createCopia(newCopia);

		if (copia != null) {
			return ResponseEntity.status(HttpStatus.OK).body(copia);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un copia", notes = "Actualiza un copia pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CopiaDto> actualizaCopia(@Validated @RequestBody CopiaDto updatedCopia)
			throws MiValidationException {

		CopiaDto copia = copiasService.actualizaCopia(updatedCopia);

		if (copia != null) {
			return ResponseEntity.status(HttpStatus.OK).body(copia);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los copias", notes = "Devuelve todos los copias de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CopiaDto>> getCopias() throws MiValidationException {

		List<CopiaDto> copiasEncontrados = copiasService.findCopias();

		if (copiasEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(copiasEncontrados);
		}
	}

	@ApiOperation(value = "Borra copia por Id", notes = "Borra un copia determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idCopia}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCopia(@PathVariable Long idCopia) throws MiValidationException {

		copiasService.delete(idCopia);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
