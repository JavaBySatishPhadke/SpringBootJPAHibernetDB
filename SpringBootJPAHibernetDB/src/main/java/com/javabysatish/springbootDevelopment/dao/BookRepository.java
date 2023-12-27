package com.javabysatish.springbootDevelopment.dao;

import org.springframework.data.repository.CrudRepository;

import com.javabysatish.springbootDevelopment.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
	
	
	public Book findById(int id);

}
