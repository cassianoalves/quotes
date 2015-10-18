package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuoteRepository extends MongoRepository<Quote, String> {
    List<Quote> findByBookId(String bookId);
}
