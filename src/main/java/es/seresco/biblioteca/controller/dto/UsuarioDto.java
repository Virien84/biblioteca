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
public class UsuarioDto{

	@JsonProperty("id")
	@NotNull
	private Long id;

	@JsonProperty("identificador")
	@NotNull
	private String identificador;

	@JsonProperty("nombre")
	@NotNull
	private String nombre;

	@JsonProperty("apellidos")
	@NotNull
	private String apellidos;
}
