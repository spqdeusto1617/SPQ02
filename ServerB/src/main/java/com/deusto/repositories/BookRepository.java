package com.deusto.repositories;

import org.springframework.stereotype.Repository;

import com.deusto.models.Book;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/*!
 * BookRepository class saves the created Book objects in a mongo repository.
 * It exposes basic CRUD operations.
 */

@Repository
public interface BookRepository extends MongoRepository<Book,String>{
	List<Book> findAll();
	List<Book> findByTitle(String title);
	Book findBookById(String id);
	List<Book> findAllByAuthorFirstName(String authorFirstName);
	List<Book> findAllByAuthorLastName(String authorLastName);
	List<Book> findAllByGenre(String genre);
	Book findAllById(String id);
}
