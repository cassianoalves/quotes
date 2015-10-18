package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.UserComponent;
import com.cassianoalves.quotes.model.SignUp;
import com.cassianoalves.quotes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserService {
    @Autowired
    private UserComponent userComponent;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public User signUp(@RequestBody User user, @RequestParam(value = "inviteId", required = false) String inviteId)
    {
        return userComponent.signUp(user, inviteId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public User update(@RequestBody Map<String, Object> userMap)
    {
        User user = new User(userMap);
        return userComponent.update(user, (String) userMap.get("currentPassword"));
    }

    @RequestMapping(value = "/confirm/{confirmKey}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> confirm(@PathVariable("confirmKey") String confirmKey)
    {
        User userConfirmed = userComponent.confirmUser(confirmKey);
        if(userConfirmed == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userConfirmed, HttpStatus.OK);
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserLogged() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User currentUser = userComponent.findById(loggedUser.getId());
        return ResponseEntity.ok().body(currentUser);
    }
}
