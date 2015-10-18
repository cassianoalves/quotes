package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.BookComponent;
import com.cassianoalves.quotes.model.Quote;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.cassianoalves.quotes.view.QuoteView;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookService {

    @Autowired
    private BookComponent bookComponent;

    @RequestMapping(value = "/{bookId}/quote", method = RequestMethod.POST)
    @ResponseBody
    Quote newQuote(@RequestBody Quote quote, @PathVariable("bookId") String bookId) {
        return bookComponent.newQuote(bookId, quote);
    }

    @RequestMapping(value = "/{bookId}/quote", method = RequestMethod.GET)
    @ResponseBody
    List<Quote> getBookAllQuotes(@PathVariable("bookId") String bookId) {
        return bookComponent.getAllQuotes(bookId);
    }

    @RequestMapping(value = "/{bookId}/quote/{quoteId}", method = RequestMethod.DELETE)
    @ResponseBody
    void deleteQuote(@PathVariable("quoteId") String quoteId) {
        bookComponent.deleteQuote(quoteId);
    }

    @JsonView(QuoteView.Random.class)
    @RequestMapping(value = "/{bookId}/quote/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Quote getRandomQuote(@PathVariable("bookId") String bookId) {
        return bookComponent.getRandomQuote(bookId);
    }

    @RequestMapping(value = "/{bookId}/quote/random", method = RequestMethod.GET)
    @ResponseBody
    String getRandomQuoteText(@PathVariable("bookId") String bookId) {
        Quote quote = bookComponent.getRandomQuote(bookId);
        return quote.getPhrase() + "\n" + "-- " + quote.getAuthor();
    }
}
