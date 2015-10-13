package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Invite;
import org.springframework.http.ResponseEntity;

public interface InviteComponent {
    void initialize(Invite initialInvite);
    Invite findOne(String id);
    Invite newInvite(Invite invite);
}
