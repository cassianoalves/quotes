package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.model.User;
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
    @Autowired
    private ErrorComponent errorComponent;

    @Override
    public void initialize(Invite initialInvite) {
        if (userRepository.count() > 0 || inviteRepository.count() > 0) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.ALREADY_INITIALIZED);
        }

        Invite savedInvite = inviteRepository.save(initialInvite);
        emailComponent.sendInvite(savedInvite);
    }

    @Override
    public Invite findOne(String id) {
        return inviteRepository.findOne(id);
    }

    @Override
    public Invite newInvite(Invite invite) {
        User user = userRepository.findByEmail(invite.getGuestEmail());
        if(user != null) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.USER_EXISTS);
        }
        Invite i = inviteRepository.findByGuestEmail(invite.getGuestEmail());
        if(i != null) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.INVITE_EXISTS);
        }
        Invite savedInvite = inviteRepository.save(invite);
        emailComponent.sendInvite(savedInvite);
        return savedInvite;
    }


}
