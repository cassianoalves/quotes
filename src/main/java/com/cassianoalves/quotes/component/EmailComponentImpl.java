package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.Invite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailComponentImpl implements EmailComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailComponentImpl.class);
    @Override
    public void sendInvite(Invite invite) {
        LOGGER.debug("Sending invite: " + invite);
    }
}
