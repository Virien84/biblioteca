package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.AutorDto;
import es.seresco.biblioteca.controller.dto.NewAutorDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface AutoresService {
	
	AutorDto getAutor(Long idAutor);
	
	AutorDto createAutor(NewAutorDto newAutor);
	
	List<AutorDto> findAutores () throws MiValidationException;

	AutorDto actualizaAutor(AutorDto updatedAutor) throws MiValidationException;

	void delete(Long idAutor) throws MiValidationException;

}
