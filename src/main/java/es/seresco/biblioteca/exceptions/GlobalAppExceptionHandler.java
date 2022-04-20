package es.seresco.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.seresco.biblioteca.controller.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalAppExceptionHandler{
	
	// exception handler personalizado
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorDto> controlExcepciones(Exception ex) {
		ErrorDto dto = new ErrorDto();
		
		log.warn("Gestionando excepción en Handler Global...", ex);

		if (ex instanceof MiValidationException) {
			MiValidationException exValidacion = (MiValidationException) ex;
			dto.setFecha(exValidacion.getFechaError().toString());
			dto.setCodigoError(exValidacion.getCodError());
			dto.setMensaje(exValidacion.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		} else {
			dto.setCodigoError("1");
			dto.setMensaje("Error controlado por mi exception");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
		}
	}
	
	
	/*@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}*/
	
}
