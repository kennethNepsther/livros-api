package ti.nepsther.bookstore.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;
import ti.nepsther.bookstore.model.Category;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "Um nome é requerido")
	@Length(min = 3, max = 50, message = "O nome deve estar entre 3 a 20 caracteres")
	private String name;
	
	@NotEmpty(message = "Uma descrição é requerido")
	@Length(min = 3, message = "A descrição deve ter no minimo 3 caracteres")
	private String description;

	public CategoryDTO(Category catDTO) {
		super();
		this.id = catDTO.getId();
		this.name = catDTO.getName();
		this.description = catDTO.getDescription();
	}

}
