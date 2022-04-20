package es.seresco.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.AutorDto;
import es.seresco.biblioteca.controller.dto.CategoriaDto;
import es.seresco.biblioteca.controller.dto.LibroDto;
import es.seresco.biblioteca.controller.dto.NewLibroDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.AutorMapper;
import es.seresco.biblioteca.mapper.CategoriaMapper;
import es.seresco.biblioteca.mapper.LibroMapper;
import es.seresco.biblioteca.model.Libro;
import es.seresco.biblioteca.respository.LibrosRepository;
import es.seresco.biblioteca.service.AutoresService;
import es.seresco.biblioteca.service.CategoriasService;
import es.seresco.biblioteca.service.LibrosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LibrosServiceImpl implements LibrosService {
	@Autowired
	private LibrosRepository librosRepository;

	@Autowired
	private LibroMapper libroMapper;
	
	@Autowired
	private CategoriasService categoriasService;
	@Autowired
	private CategoriaMapper categoriaMapper;
	
	@Autowired
	private AutoresService autoresService;
	@Autowired
	private AutorMapper autorMapper;

	@Override
	public LibroDto createLibro(NewLibroDto newLibro) {
		
		CategoriaDto categoriaDto = categoriasService.getCategoria(newLibro.getCategoria().getId());
		newLibro.setCategoria(categoriaMapper.categoriaDtoToCategoria(categoriaDto));
		
		AutorDto autorDto = autoresService.getAutor(newLibro.getAutor().getId());
		newLibro.setAutor(autorMapper.autorDtoToAutor(autorDto));
		
		Libro libro = libroMapper.newLibroDtoToLibro(newLibro);
		libro = librosRepository.save(libro);
		LibroDto dto = libroMapper.libroToLibroDto(libro);
		
		return dto;
	}
	
	@Override
	public LibroDto getLibro(Long idLibro) {
		log.info("Buscando el libro {}", idLibro);
		Libro libro = librosRepository.getById(idLibro);
		LibroDto dto = libroMapper.libroToLibroDto(libro);
		
		return dto;
	}
	
	@Override
	public List<LibroDto> findLibros() {
		List<Libro> libros = librosRepository.findAll();

		List<LibroDto> dtos = new ArrayList<>();
		for (Libro libro : libros) {
			dtos.add(libroMapper.libroToLibroDto(libro));
		}
		return dtos;
	}

	@Override
	public LibroDto actualizaLibro(LibroDto updatedLibro) throws MiValidationException {
		
		CategoriaDto categoriaDto = categoriasService.getCategoria(updatedLibro.getCategoria().getId());
		updatedLibro.setCategoria(categoriaMapper.categoriaDtoToCategoria(categoriaDto));
		
		AutorDto autorDto = autoresService.getAutor(updatedLibro.getAutor().getId());
		updatedLibro.setAutor(autorMapper.autorDtoToAutor(autorDto));
		
		Libro libro = libroMapper.libroDtoToLibro(updatedLibro);
		LibroDto dto = libroMapper.libroToLibroDto(libro);
		
		libro = librosRepository.save(libro);
				
		return dto;
	}

	@Override
	public void delete(Long idLibro) throws MiValidationException {

		librosRepository.deleteById(idLibro);
	}

}
