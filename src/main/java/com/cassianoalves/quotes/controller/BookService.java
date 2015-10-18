package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.BookComponent;
import com.cassianoalves.quotes.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
