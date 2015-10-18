package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.model.Quote;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class HealthCheckService {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    String isAlive() {
        return "<h1>The Quotes is Alive!!!</h1>";
    }

}
