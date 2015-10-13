package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.InviteComponent;
import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.repository.InviteRepository;
import com.cassianoalves.quotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/invite")
public class InviteService {
    @Autowired
    InviteComponent inviteComponent;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Invite> getInvite(@PathVariable("id") String id) {

        Invite invite = inviteComponent.findOne(id);

        if(invite == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(invite, HttpStatus.OK);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    void initializeWithInvite(@RequestBody Invite initialInvite) {
        inviteComponent.initialize(initialInvite);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Invite> newInvite(@RequestBody Invite invite) {
        return new ResponseEntity(inviteComponent.newInvite(invite), HttpStatus.CREATED);
    }

}
