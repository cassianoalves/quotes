package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Invite;

public interface EmailComponent {
    void sendInvite(Invite invite);
}
