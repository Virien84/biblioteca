package es.seresco.biblioteca.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.CopiaDto;
import es.seresco.biblioteca.controller.dto.NewPrestamoDto;
import es.seresco.biblioteca.controller.dto.PrestamoDto;
import es.seresco.biblioteca.controller.dto.UsuarioDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.CopiaMapper;
import es.seresco.biblioteca.mapper.PrestamoMapper;
import es.seresco.biblioteca.mapper.UsuarioMapper;
import es.seresco.biblioteca.model.Prestamo;
import es.seresco.biblioteca.model.tipos.TipoEstado;
import es.seresco.biblioteca.respository.PrestamosRepository;
import es.seresco.biblioteca.service.CopiasService;
import es.seresco.biblioteca.service.PrestamosService;
import es.seresco.biblioteca.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrestamosServiceImpl implements PrestamosService {
	@Autowired
	private PrestamosRepository prestamosRepository;
	@Autowired
	private PrestamoMapper prestamoMapper;
	
	@Autowired
	private UsuariosService usuariosService;
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private CopiasService copiasService;
	@Autowired
	private CopiaMapper copiaMapper;
	
	SimpleDateFormat dateFormat = new 
            SimpleDateFormat ("yyyy-MM-dd");

	@Override
	public PrestamoDto createPrestamo(NewPrestamoDto newPrestamo) throws MiValidationException {
		
		CopiaDto copiaDto = copiasService.getCopia(newPrestamo.getCopia().getId());
		
		if(copiaDto.getEstado().equals(TipoEstado.REPARACION)) {
			throw new MiValidationException("260","No se puede prestar un libro en reparación");
		}
		
		newPrestamo.setCopia(copiaMapper.copiaDtoToCopia(copiaDto));
		
		UsuarioDto usuarioDto = usuariosService.getUsuario(newPrestamo.getUsuario().getId());
		newPrestamo.setUsuario(usuarioMapper.usuarioDtoToUsuario(usuarioDto));
		
		Prestamo prestamo = prestamoMapper.newPrestamoDtoToPrestamo(newPrestamo);
		prestamo.setFechaInicio(new Date(System.currentTimeMillis()));
		prestamo = prestamosRepository.save(prestamo);
		PrestamoDto dto = prestamoMapper.prestamoToPrestamoDto(prestamo);
		
		copiaDto.setEstado(TipoEstado.PRESTADO);
		copiasService.actualizaCopia(copiaDto);
		
		return dto;
	}
	
	@Override
	public PrestamoDto getPrestamo(Long idPrestamo) {
		
		Prestamo prestamo = prestamosRepository.getById(idPrestamo);
		PrestamoDto dto = prestamoMapper.prestamoToPrestamoDto(prestamo);
		
		return dto;
	}
	
	@Override
	public List<PrestamoDto> findPrestamos() {
		List<Prestamo> prestamos = prestamosRepository.findAll();

		List<PrestamoDto> dtos = new ArrayList<>();
		for (Prestamo prestamo : prestamos) {
			dtos.add(prestamoMapper.prestamoToPrestamoDto(prestamo));
		}
		return dtos;
	}

	@Override
	public PrestamoDto actualizaPrestamo(PrestamoDto updatedPrestamo) throws MiValidationException {
		
		UsuarioDto usuarioDto = usuariosService.getUsuario(updatedPrestamo.getUsuario().getId());
		updatedPrestamo.setUsuario(usuarioMapper.usuarioDtoToUsuario(usuarioDto));
		
		CopiaDto copiaDto = copiasService.getCopia(updatedPrestamo.getCopia().getId());
		updatedPrestamo.setCopia(copiaMapper.copiaDtoToCopia(copiaDto));
		
		Prestamo prestamo = prestamoMapper.prestamoDtoToPrestamo(updatedPrestamo);
		
		PrestamoDto dto = prestamoMapper.prestamoToPrestamoDto(prestamo);
		
		prestamo = prestamosRepository.save(prestamo);
		
		return dto;
	}
	
	@Override
	public PrestamoDto finalizaPrestamo(Long idPrestamo) throws MiValidationException {
		
		int milisecondsByDay = 86400000;
		
		PrestamoDto prestamoDto = prestamoMapper.prestamoToPrestamoDto(prestamosRepository.findPrestamoById(idPrestamo));
		prestamoDto.setFechaFin(new Date(System.currentTimeMillis()));
		
		int dias = (int) ((prestamoDto.getFechaFin().getTime()-prestamoDto.getFechaInicio().getTime()) / milisecondsByDay);
		
		if (dias>=3) {
			log.error("EL PRESTAMO SE DEVUELVE CON RETRASO");
		}
		
		UsuarioDto usuarioDto = usuariosService.getUsuario(prestamoDto.getUsuario().getId());
		prestamoDto.setUsuario(usuarioMapper.usuarioDtoToUsuario(usuarioDto));
		
		CopiaDto copiaDto = copiasService.getCopia(prestamoDto.getCopia().getId());
		copiaDto.setEstado(TipoEstado.LIBRE);
		copiasService.actualizaCopia(copiaDto);
		
		prestamoDto.setCopia(copiaMapper.copiaDtoToCopia(copiaDto));
		prestamosRepository.save(prestamoMapper.prestamoDtoToPrestamo(prestamoDto));
	
		return prestamoDto;
	}

	@Override
	public void delete(Long idPrestamo) throws MiValidationException {

		prestamosRepository.deleteById(idPrestamo);
	}

}
