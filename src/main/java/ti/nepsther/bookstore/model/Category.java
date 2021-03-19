package ti.nepsther.bookstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Um nome é requerido")
	@Length(min = 3, max = 50, message = "O nome deve estar entre 3 a 20 caracteres")
	private String name;
	
	@NotEmpty(message = "Uma descrição é requerido")
	@Length(min = 3, message = "A descrição deve ter no minimo 3 caracteres")	
	private String description;
	
	@OneToMany(mappedBy = "category")
	private List<Book> books = new ArrayList<>();

	public Category(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;

	}

}
