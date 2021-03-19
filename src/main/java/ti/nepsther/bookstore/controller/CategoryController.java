package ti.nepsther.bookstore.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ti.nepsther.bookstore.model.Category;
import ti.nepsther.bookstore.model.dto.CategoryDTO;
import ti.nepsther.bookstore.service.CategoryService;


@Api(value = "Bookstore")
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "Retorna uma  categoria pelo seu id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok().body(category);

	}

	@ApiOperation(value = "Retorna lista de categorias")
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(value = "Criação de uma categoria ")
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
		category = categoryService.create(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		// return ResponseEntity.created(uri).body(category);
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Actualiza  uma categoria ")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@Valid @PathVariable Integer id, @RequestBody CategoryDTO categoryDto) {
		Category category = categoryService.update(id, categoryDto);
		return ResponseEntity.ok().body(new CategoryDTO(category));
	}

	@ApiOperation(value = "Elimina  uma categoria pelo id ")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
