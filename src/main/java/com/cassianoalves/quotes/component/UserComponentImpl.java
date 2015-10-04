package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.model.SignUp;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.model.UserConfirmation;
import com.cassianoalves.quotes.repository.InviteRepository;
import com.cassianoalves.quotes.repository.UserConfirmationRepository;
import com.cassianoalves.quotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

@Component
public class UserComponentImpl implements UserComponent {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private EmailComponent emailComponent;
    @Autowired
    private UserConfirmationRepository userConfirmationRepository;

    @Override
    public User signUp(SignUp signUp) {
        // Por enquanto só para convidados
        if(signUp.getInviteId() == null) {
            throw new ComponentException("At moment, guests only. Sorry.");
        }

        Invite invite = inviteRepository.findOne(signUp.getInviteId());
        if(invite == null) {
            throw new ComponentException("Invite " + signUp.getInviteId() + " is invalid.");
        }

        if(!signUp.getPassword().equals(signUp.getPasswordConfirm())) {
            throw new ComponentException("Passwords don't match.");
        }

        User user = new User();
        user.setEmail(signUp.getEmail());
        user.setName(signUp.getName());
        user.setPasswordHash(DigestUtils.md5DigestAsHex(signUp.getPassword().getBytes(Charset.forName("UTF-8"))));
        user.setStatus(User.Status.NOT_CONFIRMED);
        User userSaved = userRepository.save(user);

        inviteRepository.delete(invite);

        UserConfirmation confirmation = new UserConfirmation(user);
        UserConfirmation confirmationCreated = userConfirmationRepository.save(confirmation);

        emailComponent.sendConfirmation(confirmationCreated);

        return userSaved;
    }
}
