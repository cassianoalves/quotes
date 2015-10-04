package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.InviteComponent;
import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.repository.InviteRepository;
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
}
