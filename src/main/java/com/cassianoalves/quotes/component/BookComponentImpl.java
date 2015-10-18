package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Quote;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.repository.BookRepository;
import com.cassianoalves.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BookComponentImpl implements BookComponent {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote newQuote(String bookId, Quote quote) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        Quote newQuote = new Quote(bookId, loggedUser, quote.getPhrase(), quote.getAuthor());
        return quoteRepository.save(newQuote);
    }
}
