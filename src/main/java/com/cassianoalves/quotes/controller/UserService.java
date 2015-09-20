package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserService {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    String getAllUsers() {
        return "Get All Users";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    Map<String, String>  signUp(@RequestBody Map<String, String> user) {
        return user;
    }
}
