package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.repository.InviteRepository;
import com.cassianoalves.quotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InviteComponentImpl implements InviteComponent {
    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailComponent emailComponent;

    @Override
    public void initialize(Invite initialInvite) {
        if (userRepository.count() > 0 || inviteRepository.count() > 0) {
            throw new ComponentException("System is already initialized.");
        }

        Invite savedInvite = inviteRepository.save(initialInvite);
        emailComponent.sendInvite(savedInvite);
    }

    @Override
    public Invite findOne(String id) {
        return inviteRepository.findOne(id);
    }

}
