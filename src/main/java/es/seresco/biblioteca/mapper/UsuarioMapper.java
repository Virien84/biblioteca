package es.seresco.biblioteca.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.seresco.biblioteca.controller.dto.UsuarioDto;
import es.seresco.biblioteca.controller.dto.NewUsuarioDto;
import es.seresco.biblioteca.model.Usuario;

@Mapper(componentModel="spring")
public interface UsuarioMapper {
		
	public static final UsuarioMapper usuarioMapper = null;
	
	@Mapping(source="identificador",target="identificador")
	@Mapping(source="nombre",target="nombre")
	@Mapping(source="apellidos",target="apellidos")
	public Usuario newUsuarioDtoToUsuario(NewUsuarioDto newUsuarioDto);
	
	@InheritInverseConfiguration
	public NewUsuarioDto usuarioTonewUsuarioDto(Usuario usuario);
	
	@Mapping(source="id", target="id")
	@InheritConfiguration(name="newUsuarioDtoToUsuario")
	public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
	
	@InheritInverseConfiguration
	public UsuarioDto usuarioToUsuarioDto(Usuario usuario);
	
	public List<UsuarioDto> usuarioTomapUsuarioDtoList(List<Usuario> usuarios);
}
