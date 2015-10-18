package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Quote;

import java.util.List;

public interface BookComponent {
    Quote newQuote(String bookId, Quote quote);
    List<Quote> getAllQuotes(String bookId);
    void deleteQuote(String quoteId);
}
