package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.BookComponent;
import com.cassianoalves.quotes.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
