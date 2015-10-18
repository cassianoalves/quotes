package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Quote;

public interface BookComponent {
    Quote newQuote(String bookId, Quote quote);
}
