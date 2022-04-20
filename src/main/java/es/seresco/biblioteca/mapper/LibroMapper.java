package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.LibroDto;
import es.seresco.biblioteca.controller.dto.NewLibroDto;
import es.seresco.biblioteca.model.Libro;

@Mapper(componentModel="spring")
public interface LibroMapper {
		
	public static final LibroMapper libroMapper = null;
	
	@Mapping(source="isbn",target="isbn")
	@Mapping(source="titulo",target="titulo")
	@Mapping(source="editorial",target="editorial")
	@Mapping(source="categoria.id",target="categoria.id")
	@Mapping(source="autor.id",target="autor.id")
	public Libro newLibroDtoToLibro(NewLibroDto newLibroDto);
	
	@InheritInverseConfiguration
	public NewLibroDto libroTonewLibroDto(Libro libro);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newLibroDtoToLibro")
	public Libro libroDtoToLibro(LibroDto libroDto);
	
	@InheritInverseConfiguration
	public LibroDto libroToLibroDto(Libro libro);
	
	public List<LibroDto> libroTomapLibroDtoList(List<Libro> libros);
}
