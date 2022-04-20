package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CopiaDto{

	@JsonProperty("id")
	@NotNull
	private Long id;

	@JsonProperty("codigo")
	@NotNull
	private String codigo;
	
	@JsonProperty("estado")
	@NotNull
	private TipoEstado estado;	
	
	@JsonProperty("libro")
	@NotNull
	private Libro libro;
}
