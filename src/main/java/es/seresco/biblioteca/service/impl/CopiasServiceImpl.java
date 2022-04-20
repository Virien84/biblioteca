package es.seresco.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.CopiaDto;
import es.seresco.biblioteca.controller.dto.LibroDto;
import es.seresco.biblioteca.controller.dto.NewCopiaDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.CopiaMapper;
import es.seresco.biblioteca.mapper.LibroMapper;
import es.seresco.biblioteca.model.Copia;
import es.seresco.biblioteca.respository.CopiasRepository;
import es.seresco.biblioteca.service.CopiasService;
import es.seresco.biblioteca.service.LibrosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CopiasServiceImpl implements CopiasService {
	
	@Autowired
	private CopiasRepository copiasRepository;
	
	@Autowired
	private CopiaMapper copiaMapper;
	
	@Autowired
	private LibrosService librosService;

	@Autowired
	private LibroMapper libroMapper;

	@Override
	public CopiaDto createCopia(NewCopiaDto newCopia) {
				
		LibroDto libroDto = librosService.getLibro(newCopia.getLibro().getId());
		newCopia.setLibro(libroMapper.libroDtoToLibro(libroDto));
		
		Copia copia = copiaMapper.newCopiaDtoToCopia(newCopia);
		copia = copiasRepository.save(copia);
		CopiaDto dto = copiaMapper.copiaToCopiaDto(copia);
		
		return dto;
	}
	
	@Override
	public CopiaDto getCopia(Long idCopia) {
		log.info("Buscando el copia {}", idCopia);
		Copia copia = copiasRepository.getById(idCopia);
		CopiaDto dto = copiaMapper.copiaToCopiaDto(copia);
		
		return dto;
	}
	
	@Override
	public List<CopiaDto> findCopias() {
		List<Copia> copias = copiasRepository.findAll();

		List<CopiaDto> dtos = new ArrayList<>();
		for (Copia copia : copias) {
			dtos.add(copiaMapper.copiaToCopiaDto(copia));
		}
		return dtos;
	}

	@Override
	public CopiaDto actualizaCopia(CopiaDto updatedCopia) throws MiValidationException {
		
		LibroDto libroDto = librosService.getLibro(updatedCopia.getLibro().getId());
		updatedCopia.setLibro(libroMapper.libroDtoToLibro(libroDto));
		
		Copia copia = copiaMapper.copiaDtoToCopia(updatedCopia);
		
		CopiaDto dto = copiaMapper.copiaToCopiaDto(copia);
		
		copia = copiasRepository.save(copia);
		
		
		return dto;
	}

	@Override
	public void delete(Long idCopia) throws MiValidationException {

		copiasRepository.deleteById(idCopia);
	}

}
