package com.javabysatish.springbootDevelopment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Book> getBook() {
		
		return this.bookservice.getAllbook();

	}

	/**
	 * Get the data based on Id.
	 */
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable("id") int id) {
		
		return this.bookservice.getBookById(id);

	}
	
	
	/**
	 * add element in the list
	 */
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		
		Book b = this.bookservice.addBook(book);
		System.out.println("Book added in list!! " + b);
		
		return b;
		
	}
	
	/**
	 * Upodate the element i.e PUT method
	 */
	@PutMapping("books/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		
		 this.bookservice.updateBook(book,id);
		 return book;
	}
	
	/**
	 * Delete books data from list using delete method
	 */
	
	@DeleteMapping("books/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		
		this.bookservice.deleteBook(id);
		
	}
}
