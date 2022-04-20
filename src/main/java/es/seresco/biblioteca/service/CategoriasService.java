package es.seresco.biblioteca.service;

import java.util.List;

import es.seresco.biblioteca.controller.dto.CategoriaDto;
import es.seresco.biblioteca.controller.dto.NewCategoriaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;

public interface CategoriasService {
	
	CategoriaDto getCategoria(Long idCategoria);
	
	CategoriaDto createCategoria(NewCategoriaDto newCategoria);
	
	List<CategoriaDto> findCategorias () throws MiValidationException;

	CategoriaDto actualizaCategoria(CategoriaDto updatedCategoria) throws MiValidationException;

	void delete(Long idCategoria) throws MiValidationException;

}
