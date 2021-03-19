package ti.nepsther.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ti.nepsther.bookstore.model.Book;
import ti.nepsther.bookstore.model.Category;
import ti.nepsther.bookstore.repository.BookRepository;
import ti.nepsther.bookstore.service.execption.ObjectNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryService categoryService;

	public Book findById(Integer id) {
		Optional<Book> book = repository.findById(id);
		return book.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi encotrado nenhum livro com este id " + id /* + ", tipo:" + Category.class.getName() */));
	}

	public List<Book> findAll(Integer id_cat) {
		categoryService.findById(id_cat);
		return repository.findAllByCategory(id_cat);
	}

	public Book update(Integer id, Book objBook) {
		Book newObj = findById(id);
		updateData(newObj, objBook);
		return repository.save(newObj);
	}

	private static void updateData(Book newObj, Book objBook) {
		newObj.setTitle(objBook.getTitle());
		newObj.setText(objBook.getText());
		newObj.setAuthor(objBook.getAuthor());

	}

	public Book create(Integer id_cat, Book newBook) {
		newBook.setId(null);
		Category category = categoryService.findById(id_cat);
		newBook.setCategory(category);

		return repository.save(newBook);
	}

	public void delete(Integer id) {
		Book obj =findById(id);
		repository.delete(obj);
		
	}

}
