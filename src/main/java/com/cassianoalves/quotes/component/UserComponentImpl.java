package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
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
    public User signUp(User user, String inviteId) {
        // Por enquanto só para convidados
        if(inviteId == null) {
            throw new ComponentException("At moment, guests only. Sorry.");
        }

        Invite invite = inviteRepository.findOne(inviteId);
        if(invite == null) {
            throw new ComponentException("Invite " + inviteId + " is invalid.");
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(Charset.forName("UTF-8"))));
        user.setStatus(User.Status.NOT_CONFIRMED);
        User userSaved = userRepository.save(user);

        inviteRepository.delete(invite);

        UserConfirmation confirmation = new UserConfirmation(user);
        UserConfirmation confirmationCreated = userConfirmationRepository.save(confirmation);

        emailComponent.sendConfirmation(confirmationCreated);

        return userSaved;
    }

    @Override
    public User confirmUser(String confirmKey) {
        UserConfirmation userConfirmation = userConfirmationRepository.findOne(confirmKey);
        if(userConfirmation == null) {
            return null;
        }

        User user = userConfirmation.getUser();
        user.setStatus(User.Status.ACTIVE);
        User updatedUser = userRepository.save(user);
        userConfirmationRepository.delete(userConfirmation);

        return updatedUser;
    }

    @Override
    public User authenticate(String email, String openPassword) {
        if(email == null || openPassword == null) {
            return null;
        }
        User user = userRepository.findByEmail(email);
        String passwordHash = DigestUtils.md5DigestAsHex(openPassword.getBytes(Charset.forName("UTF-8")));
        if(user.getPassword().equals(passwordHash)) {
            return user;
        }
        return null;
    }
}
