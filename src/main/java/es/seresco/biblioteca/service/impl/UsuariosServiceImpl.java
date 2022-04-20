package es.seresco.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.seresco.biblioteca.controller.dto.UsuarioDto;
import es.seresco.biblioteca.controller.dto.NewUsuarioDto;
import es.seresco.biblioteca.exceptions.MiValidationException;
import es.seresco.biblioteca.mapper.UsuarioMapper;
import es.seresco.biblioteca.model.Usuario;
import es.seresco.biblioteca.respository.UsuariosRepository;
import es.seresco.biblioteca.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService {
	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Override
	public UsuarioDto createUsuario(NewUsuarioDto newUsuario) {
		
		Usuario usuario = usuarioMapper.newUsuarioDtoToUsuario(newUsuario);
		usuario = usuariosRepository.save(usuario);
		UsuarioDto dto = usuarioMapper.usuarioToUsuarioDto(usuario);
		
		return dto;
	}
	
	@Override
	public UsuarioDto getUsuario(Long idUsuario) {
		log.info("Buscando el usuario {}", idUsuario);
		Usuario usuario = usuariosRepository.getById(idUsuario);
		UsuarioDto dto = usuarioMapper.usuarioToUsuarioDto(usuario);
		
		return dto;
	}

	@Override
	public List<UsuarioDto> findUsuarios() {
		List<Usuario> usuarios = usuariosRepository.findAll();

		List<UsuarioDto> dtos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			dtos.add(usuarioMapper.usuarioToUsuarioDto(usuario));
		}
		return dtos;
	}
	
	@Override
	public UsuarioDto actualizaUsuario(UsuarioDto updatedUsuario) throws MiValidationException {
		
		Usuario usuario = usuarioMapper.usuarioDtoToUsuario(updatedUsuario);
		
		UsuarioDto dto = usuarioMapper.usuarioToUsuarioDto(usuario);
		
		usuario = usuariosRepository.save(usuario);
				
		return dto;
	}

	@Override
	public void delete(Long idUsuario) throws MiValidationException {

		usuariosRepository.deleteById(idUsuario);
	}
}
