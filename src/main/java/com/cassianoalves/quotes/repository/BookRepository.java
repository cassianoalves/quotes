package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
