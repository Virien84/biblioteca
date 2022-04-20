package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.CopiaDto;
import es.seresco.biblioteca.controller.dto.NewCopiaDto;
import es.seresco.biblioteca.model.Copia;

@Mapper(componentModel="spring")
public interface CopiaMapper {
		
	public static final CopiaMapper copiaMapper = null;
	
	@Mapping(source="codigo",target="codigo")
	@Mapping(source="estado",target="estado")
	@Mapping(source="libro.id",target="libro.id")
	public Copia newCopiaDtoToCopia(NewCopiaDto newCopiaDto);
	
	@InheritInverseConfiguration
	public NewCopiaDto copiaTonewCopiaDto(Copia copia);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newCopiaDtoToCopia")
	public Copia copiaDtoToCopia(CopiaDto copiaDto);
	
	@InheritInverseConfiguration
	public CopiaDto copiaToCopiaDto(Copia copia);
	
	public List<CopiaDto> copiaTomapCopiaDtoList(List<Copia> copias);
}
