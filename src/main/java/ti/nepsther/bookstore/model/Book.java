package ti.nepsther.bookstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Um titulo é requerido")
	@Length(min = 3, max = 50, message = "Campo nome deve estar entre 3 a 20 caracteres")
	private String title;
	
	@NotEmpty(message = "Um Texto é requerido")
	@Length(min = 10, message = "O texto deve estar entre 3 a 20 caracteres")
	private String text;
	
	@NotEmpty(message = "Um nome é requerido")
	@Length(min = 3, max = 50, message = "O nome do autor deve estar entre 3 a 50 caracteres")
	private String author;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Category category;
}
