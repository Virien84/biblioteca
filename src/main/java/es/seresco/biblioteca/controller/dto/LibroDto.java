package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class LibroDto{

	@JsonProperty("id")
	@NotNull
	private Long id;

	@JsonProperty("isbn")
	@NotNull
	private String isbn;

	@JsonProperty("titulo")
	@NotNull
	private String titulo;
	
	@JsonProperty("editorial")
	@NotNull
	private String editorial;	
	
	@JsonProperty("categoria")
	@NotNull
	private Categoria categoria;
	
	@JsonProperty("autor")
	@NotNull
	private Autor autor;
}
