package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.UserComponent;
import com.cassianoalves.quotes.model.SignUp;
import com.cassianoalves.quotes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserService {
    @Autowired
    private UserComponent userComponent;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    User signUp(@RequestBody SignUp signUp)
    {
        return userComponent.signUp(signUp);
    }
}
