package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.QuoteComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteService {

    @Autowired
    private QuoteComponent quoteComponent;


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/lala")
    @ResponseBody
    String lala() {
        return quoteComponent.lala();
    }


}
