package com.cassianoalves.quotes.controller;

import com.cassianoalves.quotes.component.ContactComponent;
import com.cassianoalves.quotes.model.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contact")
public class ContactService {
    @Autowired
    private ContactComponent contactComponent;

    @RequestMapping(value = "/mailme", method = RequestMethod.POST)
    public void mailMe(@RequestBody ContactMessage contactMessage) {
        contactComponent.sendContactMessageToMasterMail(contactMessage);
    }
}
