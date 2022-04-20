package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.PrestamoDto;
import es.seresco.biblioteca.controller.dto.NewPrestamoDto;
import es.seresco.biblioteca.model.Prestamo;

@Mapper(componentModel="spring")
public interface PrestamoMapper {
		
	public static final PrestamoMapper prestamoMapper = null;
	
	@Mapping(source="fechaInicio",target="fechaInicio")
	@Mapping(source="fechaFin",target="fechaFin")
	@Mapping(source="copia.id",target="copia.id")
	@Mapping(source="usuario.id",target="usuario.id")
	public Prestamo newPrestamoDtoToPrestamo(NewPrestamoDto newPrestamoDto);
	
	@InheritInverseConfiguration
	public NewPrestamoDto prestamoTonewPrestamoDto(Prestamo prestamo);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newPrestamoDtoToPrestamo")
	public Prestamo prestamoDtoToPrestamo(PrestamoDto prestamoDto);
	
	@InheritInverseConfiguration
	public PrestamoDto prestamoToPrestamoDto(Prestamo prestamo);
	
	public List<PrestamoDto> prestamoTomapPrestamoDtoList(List<Prestamo> prestamos);
}
