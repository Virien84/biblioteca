package es.seresco.biblioteca.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUsuarioDto{

	@NotNull
	private String identificador;

	@NotNull
	private String nombre;

	@NotNull
	private String apellidos;
}
