package es.seresco.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.CategoriaDto;
import es.seresco.biblioteca.controller.dto.NewCategoriaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.CategoriaMapper;
import es.seresco.biblioteca.model.Categoria;
import es.seresco.biblioteca.respository.CategoriasRepository;
import es.seresco.biblioteca.service.CategoriasService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriasServiceImpl implements CategoriasService {
	@Autowired
	private CategoriasRepository categoriasRepository;

	@Autowired
	private CategoriaMapper categoriaMapper;
	
	@Override
	public CategoriaDto createCategoria(NewCategoriaDto newCategoria) {
		
		Categoria categoria = categoriaMapper.newCategoriaDtoToCategoria(newCategoria);
		categoria = categoriasRepository.save(categoria);
		CategoriaDto dto = categoriaMapper.categoriaToCategoriaDto(categoria);
		
		return dto;
	}
	
	@Override
	public CategoriaDto getCategoria(Long idCategoria) {
		
		Categoria categoria = categoriasRepository.getById(idCategoria);
		CategoriaDto dto = categoriaMapper.categoriaToCategoriaDto(categoria);
		
		return dto;
	}

	@Override
	public List<CategoriaDto> findCategorias() {
		List<Categoria> categorias = categoriasRepository.findAll();

		List<CategoriaDto> dtos = new ArrayList<>();
		for (Categoria categoria : categorias) {
			dtos.add(categoriaMapper.categoriaToCategoriaDto(categoria));
		}
		return dtos;
	}
	
	@Override
	public CategoriaDto actualizaCategoria(CategoriaDto updatedCategoria) throws MiValidationException {
		
		Categoria categoria = categoriaMapper.categoriaDtoToCategoria(updatedCategoria);
		
		CategoriaDto dto = categoriaMapper.categoriaToCategoriaDto(categoria);
		
		categoria = categoriasRepository.save(categoria);
				
		return dto;
	}

	@Override
	public void delete(Long idCategoria) throws MiValidationException {

		categoriasRepository.deleteById(idCategoria);
	}
}
