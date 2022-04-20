package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.AutorDto;
import es.seresco.biblioteca.controller.dto.NewAutorDto;
import es.seresco.biblioteca.model.Autor;

@Mapper(componentModel="spring")
public interface AutorMapper {
		
	public static final AutorMapper autorMapper = null;
	
	@Mapping(source="identificador",target="identificador")
	@Mapping(source="nombre",target="nombre")
	@Mapping(source="apellidos",target="apellidos")
	@Mapping(source="nacionalidad",target="nacionalidad")
	public Autor newAutorDtoToAutor(NewAutorDto newAutorDto);
	
	@InheritInverseConfiguration
	public NewAutorDto autorTonewAutorDto(Autor autor);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newAutorDtoToAutor")
	public Autor autorDtoToAutor(AutorDto autorDto);
	
	@InheritInverseConfiguration
	public AutorDto autorToAutorDto(Autor autor);
	
	public List<AutorDto> autorTomapAutorDtoList(List<Autor> autores);
}
