package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<Quote, String> {
}
