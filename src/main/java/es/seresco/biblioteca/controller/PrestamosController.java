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

import es.seresco.biblioteca.controller.dto.PrestamoDto;
import es.seresco.biblioteca.controller.dto.NewPrestamoDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.PrestamosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Prestamos" })
@RestController
@RequestMapping(path = "/api/prestamos")
@Slf4j
public class PrestamosController {

	@Autowired
	private PrestamosService prestamosService;

	@ApiOperation(value = "Devuelve prestamo por Id", notes = "Obtiene un prestamo según el parámetro Id")
	@GetMapping(path = "/{idPrestamo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrestamoDto> getPrestamo(@PathVariable Long idPrestamo) {

		PrestamoDto prestamo = prestamosService.getPrestamo(idPrestamo);

		if (prestamo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prestamo);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un prestamo", notes = "Añade un prestamo pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrestamoDto> createPrestamo(@Validated @RequestBody NewPrestamoDto newPrestamo)  throws MiValidationException{

		PrestamoDto prestamo = prestamosService.createPrestamo(newPrestamo);

		if (prestamo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prestamo);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un prestamo", notes = "Actualiza un prestamo pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrestamoDto> actualizaPrestamo(@Validated @RequestBody PrestamoDto updatedPrestamo)
			throws MiValidationException {

		PrestamoDto prestamo = prestamosService.actualizaPrestamo(updatedPrestamo);

		if (prestamo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prestamo);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@ApiOperation(value = "Finaliza un prestamo", notes = "Actualiza un prestamo como finalizado cuando entregan el libro")
	@PutMapping(path = "/{idPrestamo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrestamoDto> finalizaPrestamo(@PathVariable Long idPrestamo)
			throws MiValidationException {

		PrestamoDto prestamo = prestamosService.finalizaPrestamo(idPrestamo);

		if (prestamo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prestamo);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los prestamos", notes = "Devuelve todos los prestamos de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PrestamoDto>> getPrestamos() throws MiValidationException {

		List<PrestamoDto> prestamosEncontrados = prestamosService.findPrestamos();

		if (prestamosEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(prestamosEncontrados);
		}
	}

	@ApiOperation(value = "Borra prestamo por Id", notes = "Borra un prestamo determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idPrestamo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrestamo(@PathVariable Long idPrestamo) throws MiValidationException {

		prestamosService.delete(idPrestamo);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
