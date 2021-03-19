package ti.nepsther.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ti.nepsther.bookstore.model.Category;
import ti.nepsther.bookstore.model.dto.CategoryDTO;
import ti.nepsther.bookstore.repository.CategoryRepository;
import ti.nepsther.bookstore.service.execption.ObjectNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi encontrada uma categoria com o id " + id /* + ", tipo:" + Category.class.getName() */));

	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category create(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

	public Category update(Integer id, CategoryDTO categoryDto) {
		Category category = findById(id);
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return categoryRepository.save(category);
	}

	public void delete(Integer id) {

		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ti.nepsther.bookstore.service.execption.DataIntegrityViolationException(
					"A categoria possui livros associados, não pode ser eliminda");

		}

	}
}
