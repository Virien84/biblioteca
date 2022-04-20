package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.CategoriaDto;
import es.seresco.biblioteca.controller.dto.NewCategoriaDto;
import es.seresco.biblioteca.model.Categoria;

@Mapper(componentModel="spring")
public interface CategoriaMapper {
		
	public static final CategoriaMapper categoriaMapper = null;
	
	@Mapping(source="codigo",target="codigo")
	@Mapping(source="descripcion",target="descripcion")
	public Categoria newCategoriaDtoToCategoria(NewCategoriaDto newCategoriaDto);
	
	@InheritInverseConfiguration
	public NewCategoriaDto categoriaTonewCategoriaDto(Categoria categoria);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newCategoriaDtoToCategoria")
	public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto);
	
	@InheritInverseConfiguration
	public CategoriaDto categoriaToCategoriaDto(Categoria categoria);
	
	public List<CategoriaDto> categoriaTomapCategoriaDtoList(List<Categoria> categorias);
}
