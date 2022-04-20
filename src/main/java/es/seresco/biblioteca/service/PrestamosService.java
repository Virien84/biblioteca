package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.PrestamoDto;
import es.seresco.biblioteca.controller.dto.NewPrestamoDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface PrestamosService {
	
	PrestamoDto getPrestamo(Long idPrestamo);
	
	PrestamoDto createPrestamo(NewPrestamoDto newPrestamo) throws MiValidationException;
	
	List<PrestamoDto> findPrestamos () throws MiValidationException;

	PrestamoDto actualizaPrestamo(PrestamoDto updatedPrestamo) throws MiValidationException;
	
	PrestamoDto finalizaPrestamo(Long idPrestamo) throws MiValidationException;

	void delete(Long idPrestamo) throws MiValidationException;

}
