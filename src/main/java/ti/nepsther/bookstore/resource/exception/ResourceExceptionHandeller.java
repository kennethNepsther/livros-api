package ti.nepsther.bookstore.resource.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ti.nepsther.bookstore.service.execption.DataIntegrityViolationException;
import ti.nepsther.bookstore.service.execption.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandeller {
	@ExceptionHandler(ObjectNotFoundException.class)
	public static ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException er,
			ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				er.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public static ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException er,
			ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				er.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public static ResponseEntity<StandardError> ViolationError(MethodArgumentNotValidException er,
			ServletRequest request) {
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro de validação de campos");
		for (FieldError x : er.getBindingResult().getFieldErrors()) {
			error.addErrors(x.getField(), x.getDefaultMessage());

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
