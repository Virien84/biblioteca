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
public class NewCategoriaDto{

	@NotNull
	private String codigo;

	@NotNull
	private String descripcion;
}
