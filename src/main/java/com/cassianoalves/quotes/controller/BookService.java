package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.BookComponent;
import com.cassianoalves.quotes.component.HistoryComponent;
import com.cassianoalves.quotes.model.History;
import com.cassianoalves.quotes.model.Quote;
import com.fasterxml.jackson.annotation.JsonView;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cassianoalves.quotes.view.QuoteView;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookService {

    @Autowired
    private BookComponent bookComponent;
    @Autowired
    private HistoryComponent historyComponent;

    @RequestMapping(value = "/{bookId}/quote", method = RequestMethod.POST)
    @ResponseBody
    Quote newQuote(@RequestBody Quote quote, @PathVariable("bookId") String bookId) {
        historyComponent.insertEvent(History.Event.INSERT_QUOTE);
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

    @RequestMapping(value = "/{bookId}/quote/random", method = RequestMethod.GET)
    @ResponseBody
    String getRandomQuoteText(@PathVariable("bookId") String bookId) {
        Quote quote = bookComponent.getRandomQuote(bookId);
        return quote.getPhrase() + "\n" + "-- " + quote.getAuthor();
    }


    @RequestMapping(value = "/{bookId}/quote/random", method = RequestMethod.GET, params = "charset")
    @ResponseBody
    byte[] getRandomQuoteTextCharset(
            @PathVariable("bookId") String bookId,
            @RequestParam("charset") String charset) throws UnsupportedEncodingException {
        System.out.println("charset: " + charset);
        return getRandomQuoteText(bookId).getBytes(charset);
    }

    @JsonView(QuoteView.Random.class)
    @RequestMapping(value = "/{bookId}/quote/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Quote getRandomQuote(@PathVariable("bookId") String bookId) {
        return bookComponent.getRandomQuote(bookId);
    }

}
