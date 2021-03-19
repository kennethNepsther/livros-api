package ti.nepsther.bookstore.resource.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();

	}

	public ValidationError(Long timestamp, Integer status, String message) {
		super(timestamp, status, message);

	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
