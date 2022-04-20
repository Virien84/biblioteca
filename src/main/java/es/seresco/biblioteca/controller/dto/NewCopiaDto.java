package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import es.seresco.biblioteca.model.Libro;
import es.seresco.biblioteca.model.tipos.TipoEstado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCopiaDto{

	@NotNull
	private String codigo;
	
	@NotNull
	private TipoEstado estado;	
	
	@NotNull
	private Libro libro;
}
