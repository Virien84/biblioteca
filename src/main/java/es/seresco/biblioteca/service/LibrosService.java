package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.LibroDto;
import es.seresco.biblioteca.controller.dto.NewLibroDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface LibrosService {
	
	LibroDto getLibro(Long idLibro);
	
	LibroDto createLibro(NewLibroDto newLibro);
	
	List<LibroDto> findLibros () throws MiValidationException;

	LibroDto actualizaLibro(LibroDto updatedLibro) throws MiValidationException;

	void delete(Long idLibro) throws MiValidationException;

}
