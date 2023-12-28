package com.javabysatish.springbootDevelopment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javabysatish.springbootDevelopment.entity.Book;
import com.javabysatish.springbootDevelopment.service.BookService;

/**
 * 
 * @author satish
 *
 */
@RestController
public class BookController {

	@Autowired
	private BookService bookservice;

	// to check the health of this application
	@GetMapping("/health")
	@ResponseBody
	public String gethealth() {

		return "Application is up and running !!";
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBook() {

		List<Book> list = this.bookservice.getAllbook();

		if (list.size() <= 0) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	/**
	 * Get the data based on Id.
	 */
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {

		Book book = bookservice.getBookById(id);

		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(book));

	}

	/**
	 * add element in the list
	 */
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {

		Book bookDetails = null;

		try {
			bookDetails = this.bookservice.addBook(book);
			return ResponseEntity.of(Optional.of(bookDetails));

		} catch (Exception e2) {
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	/**
	 * Upodate the element i.e PUT method
	 */
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {

		try {
			this.bookservice.updateBook(book, id);

			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Delete books data from list using delete method
	 */

	@DeleteMapping("books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		try {
			this.bookservice.deleteBook(id);

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	/**
	 * Rest API for the FileUpload
	 */
}
