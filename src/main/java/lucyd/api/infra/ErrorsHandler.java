package lucyd.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorsHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handleError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<NotValidArgumentResponsePayload> handleError400(MethodArgumentNotValidException exception) {
		var error = exception.getFieldError();
		
		return ResponseEntity.badRequest().body(new NotValidArgumentResponsePayload(error));
	}
}
