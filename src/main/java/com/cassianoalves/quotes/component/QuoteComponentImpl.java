package com.cassianoalves.quotes.component;

import org.springframework.stereotype.Component;

@Component
public class QuoteComponentImpl implements QuoteComponent {
    @Override
    public String lala() {
        return "lala";
    }
}
