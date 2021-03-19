package ti.nepsther.bookstore.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ti.nepsther.bookstore.model.Book;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "Um titulo é requerido")
	@Length(min = 3, max = 50, message = "O titulo deve estar entre 3 a 20 caracteres")
	private String title;
	
	public BookDTO(Book objBook) {
		super();
		this.id = objBook.getId();
		this.title = objBook.getTitle();
	}
	
	
	
}
