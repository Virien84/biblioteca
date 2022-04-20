package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.CopiaDto;
import es.seresco.biblioteca.controller.dto.NewCopiaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface CopiasService {
	
	CopiaDto getCopia(Long idCopia);
	
	CopiaDto createCopia(NewCopiaDto newCopia);
	
	List<CopiaDto> findCopias () throws MiValidationException;

	CopiaDto actualizaCopia(CopiaDto updatedCopia) throws MiValidationException;

	void delete(Long idCopia) throws MiValidationException;

}
