package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuoteRepository extends MongoRepository<Quote, String> {
    List<Quote> findByBookId(String bookId);
    @Query(count = true, value = "{ bookId: ?0 }")
    Long countByBookId(String bookId);
    Page<Quote> findByBookId(String bookId, Pageable pageable);
}
