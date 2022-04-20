package es.seresco.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.AutorDto;
import es.seresco.biblioteca.controller.dto.NewAutorDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.AutorMapper;
import es.seresco.biblioteca.model.Autor;
import es.seresco.biblioteca.respository.AutoresRepository;
import es.seresco.biblioteca.service.AutoresService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AutoresServiceImpl implements AutoresService {
	@Autowired
	private AutoresRepository autoresRepository;

	@Autowired
	private AutorMapper autorMapper;
	
	@Override
	public AutorDto createAutor(NewAutorDto newAutor) {
		
		Autor autor = autorMapper.newAutorDtoToAutor(newAutor);
		autor = autoresRepository.save(autor);
		AutorDto dto = autorMapper.autorToAutorDto(autor);
		
		return dto;
	}
	
	@Override
	public AutorDto getAutor(Long idAutor) {
		log.info("Buscando el autor {}", idAutor);
		
		Autor autor = autoresRepository.getById(idAutor);
		
		AutorDto dto = autorMapper.autorToAutorDto(autor);
		
		return dto;
	}

	@Override
	public List<AutorDto> findAutores() {
		List<Autor> autores = autoresRepository.findAll();

		List<AutorDto> dtos = new ArrayList<>();
		for (Autor autor : autores) {
			dtos.add(autorMapper.autorToAutorDto(autor));
		}
		return dtos;
	}
	
	@Override
	public AutorDto actualizaAutor(AutorDto updatedAutor) throws MiValidationException {
		
		Autor autor = autorMapper.autorDtoToAutor(updatedAutor);
		autor = autoresRepository.save(autor);
		AutorDto dto = autorMapper.autorToAutorDto(autor);
		
		return dto;
	}

	@Override
	public void delete(Long idAutor) throws MiValidationException {

		autoresRepository.deleteById(idAutor);
	}
}
