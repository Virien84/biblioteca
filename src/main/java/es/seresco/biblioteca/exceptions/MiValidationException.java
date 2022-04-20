package es.seresco.biblioteca.exceptions;

import java.util.Date;

import lombok.Getter;

@Getter
public class MiValidationException extends Exception {


	private static final long serialVersionUID = -4586491617192745239L;
	
	private final Date fechaError;
	private final String codError;
	
	
	
	public MiValidationException(String codError, String mensaje) {
		super(mensaje);
		this.fechaError = new Date();
		this.codError = codError;
	}
	
}
