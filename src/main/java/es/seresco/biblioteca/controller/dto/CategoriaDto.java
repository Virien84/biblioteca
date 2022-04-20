package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto{

	@JsonProperty("id")
	@NotNull
	private Long id;

	@JsonProperty("codigo")
	@NotNull
	private String codigo;

	@JsonProperty("descripcion")
	@NotNull
	private String descripcion;
}
