package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.model.UserConfirmation;

public interface EmailComponent {
    void sendInvite(Invite invite);
    void sendConfirmation(UserConfirmation userConfirmation);
    void sendDataChange(User user, boolean passwordChanged);
}
