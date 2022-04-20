package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.UsuarioDto;
import es.seresco.biblioteca.controller.dto.NewUsuarioDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface UsuariosService {
	
	UsuarioDto getUsuario(Long idUsuario);
	
	UsuarioDto createUsuario(NewUsuarioDto newUsuario);
	
	List<UsuarioDto> findUsuarios () throws MiValidationException;

	UsuarioDto actualizaUsuario(UsuarioDto updatedUsuario) throws MiValidationException;

	void delete(Long idUsuario) throws MiValidationException;

}
