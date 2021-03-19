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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ti.nepsther.bookstore.model.Book;
import ti.nepsther.bookstore.model.dto.BookDTO;
import ti.nepsther.bookstore.service.BookService;
@Api(value = "Bookstore")
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "Retorna um  livro pelo seu id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id) {
		Book book = bookService.findById(id);
		return ResponseEntity.ok().body(book);

	}

	// localhost:8080/book?category=1
	@ApiOperation(value = "Retorna uma lista de  livros por categoria")
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {
		List<Book> list = bookService.findAll(id_cat);
		List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	@ApiOperation(value = "Actualiza um   livro")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Integer id,@Valid @RequestBody Book objBook) {
		Book bookUpdated = bookService.update(id, objBook);
		return ResponseEntity.ok().body(bookUpdated);
	}
	@ApiOperation(value = "Actualiza um   livro")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Book> updatePatch(@PathVariable Integer id, @Valid @RequestBody Book objBook) {
		Book bookUpdated = bookService.update(id, objBook);
		return ResponseEntity.ok().body(bookUpdated);
	}
	@ApiOperation(value = "Insere  um   livro")
	@PostMapping
	public ResponseEntity<Book> create( @RequestParam(value = "category", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Book book) {
		Book newBook = bookService.create(id_cat, book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
		 return ResponseEntity.created(uri).body(newBook);
		 //return ResponseEntity.created(uri).build();
	}
	@ApiOperation(value = "Elimina  um   livro")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
