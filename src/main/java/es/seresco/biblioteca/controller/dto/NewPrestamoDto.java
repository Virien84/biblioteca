package es.seresco.biblioteca.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
public class NewPrestamoDto{

	private Date fechaInicio;

	private Date fechaFin;	
	
	@NotNull
	private Copia copia;
	
	@NotNull
	private Usuario usuario;
}
