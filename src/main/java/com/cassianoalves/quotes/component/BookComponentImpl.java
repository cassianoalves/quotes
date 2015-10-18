package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Quote;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.repository.BookRepository;
import com.cassianoalves.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class BookComponentImpl implements BookComponent {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private Random random;

    @Override
    public Quote newQuote(String bookId, Quote quote) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        Quote newQuote = new Quote(bookId, loggedUser, quote.getPhrase(), quote.getAuthor());
        return quoteRepository.save(newQuote);
    }

    @Override
    public List<Quote> getAllQuotes(String bookId) {
        return quoteRepository.findByBookId(bookId);
    }

    @Override
    public void deleteQuote(String quoteId) {
        quoteRepository.delete(quoteId);
    }

    @Override
    public Quote getRandomQuote(String bookId) {
        Long quoteQty = quoteRepository.countByBookId(bookId);
        if(quoteQty == 0) {
            return null;
        }
        long index = Math.abs(random.nextLong()) % quoteQty;
        Page<Quote> quotePage = quoteRepository.findByBookId(bookId, new PageRequest((int) index, 1));
        return quotePage.getContent().get(0);
    }
}
