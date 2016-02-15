package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.ContactMessage;

public interface ContactComponent {
    void sendContactMessageToMasterMail(ContactMessage contactMessage);
}
