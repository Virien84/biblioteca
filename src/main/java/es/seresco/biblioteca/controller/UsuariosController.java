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

import es.seresco.biblioteca.controller.dto.NewUsuarioDto;
import es.seresco.biblioteca.controller.dto.UsuarioDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.service.UsuariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "Usuarios" })
@RestController
@RequestMapping(path = "/api/usuarios")
@Slf4j
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;

	@ApiOperation(value = "Devuelve usuario por Id", notes = "Obtiene un usuario según el parámetro Id")
	@GetMapping(path = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDto> getUsuario(@PathVariable Long idUsuario) {

		UsuarioDto usuario = usuariosService.getUsuario(idUsuario);

		if (usuario != null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	
	@ApiOperation(value = "Graba un usuario", notes = "Añade un usuario pasada mediante RequestBody")
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDto> createUsuario(@Validated @RequestBody NewUsuarioDto newUsuario) {

		UsuarioDto usuario = usuariosService.createUsuario(newUsuario);

		if (usuario != null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ApiOperation(value = "Actualiza un usuario", notes = "Actualiza un usuario pasada mediante RequestBody")
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDto> actualizaUsuario(@Validated @RequestBody UsuarioDto updatedUsuario)
			throws MiValidationException {

		UsuarioDto usuario = usuariosService.actualizaUsuario(updatedUsuario);

		if (usuario != null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

	@ApiOperation(value = "Devuelve todos los usuarios", notes = "Devuelve todos los usuarios de la base de datos")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UsuarioDto>> getUsuarios() throws MiValidationException {

		List<UsuarioDto> usuariosEncontrados = usuariosService.findUsuarios();

		if (usuariosEncontrados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(usuariosEncontrados);
		}
	}

	@ApiOperation(value = "Borra usuario por Id", notes = "Borra un usuario determinado seleccionado mediante Id")
	@DeleteMapping(path = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUsuario(@PathVariable Long idUsuario) throws MiValidationException {

		usuariosService.delete(idUsuario);

		// Retorna void (no retorno datos). Pero indico con un anotación el
		// codigo http de la respuesta 204.
	}

}
