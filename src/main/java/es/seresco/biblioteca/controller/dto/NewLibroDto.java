package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import es.seresco.biblioteca.model.Autor;
import es.seresco.biblioteca.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewLibroDto{

	@NotNull
	private String isbn;

	@NotNull
	private String titulo;
	
	@NotNull
	private String editorial;	
	
	@NotNull
	private Categoria categoria;
	
	@NotNull
	private Autor autor;
}
