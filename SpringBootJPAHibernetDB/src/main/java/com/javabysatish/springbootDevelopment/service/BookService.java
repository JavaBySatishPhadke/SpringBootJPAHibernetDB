package com.javabysatish.springbootDevelopment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javabysatish.springbootDevelopment.dao.BookRepository;
import com.javabysatish.springbootDevelopment.entity.Book;

public class BookService {



	@Autowired BookRepository bookrepository;
	public List<Book> getAllbook() {
		
		List<Book> list = (List)this.bookrepository.findAll();

		return list;

	}

	

	
	public Book getBookById(int id) {
		Book book;
	//	book = list.stream().filter(p -> p.getId() == id).findFirst().get();
		return this.bookrepository.findById(id);
	}
	 
	 
	 
	public Book addBook(Book book) {
		//list.add(book);
		Book result = bookrepository.save(book);     
		return result;

	}
	
	public void deleteBook(int id) {
		 
		
		//list.stream().filter(p -> p.getId() != id).collect(Collectors.toList());
		
		this.bookrepository.deleteById(id);
	}
	  
	public void updateBook(Book book, int id) {
		book.setId(id);
		
		bookrepository.save(book);
	}


}
