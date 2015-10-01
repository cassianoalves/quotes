package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.InviteComponent;
import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/invite")
public class InviteService {
    @Autowired
    InviteComponent inviteComponent;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    Invite getInvite(@PathVariable("id") String id) {
        return inviteComponent.findOne(id);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    void initializeWithInvite(@RequestBody Invite initialInvite) {
        inviteComponent.initialize(initialInvite);
    }
}
