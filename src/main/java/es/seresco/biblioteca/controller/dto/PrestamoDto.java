package es.seresco.biblioteca.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.seresco.biblioteca.model.Copia;
import es.seresco.biblioteca.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDto{

	@JsonProperty("id")
	@NotNull
	private Long id;

	@JsonProperty("fechaInicio")
	@NotNull
	private Date fechaInicio;

	@JsonProperty("fechaFin")
	private Date fechaFin;	
	
	@JsonProperty("copia")
	@NotNull
	private Copia copia;
	
	@JsonProperty("usuario")
	@NotNull
	private Usuario usuario;
}
