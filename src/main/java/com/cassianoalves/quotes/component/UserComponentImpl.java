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
    @Autowired
    private ErrorComponent errorComponent;

    @Override
    public User signUp(User user, String inviteId) {
        // Por enquanto só para convidados
        if(inviteId == null) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.GUESTS_ONLY);
        }

        Invite invite = inviteRepository.findOne(inviteId);
        if(invite == null) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.INVALID_INVITE);
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

    @Override
    public User update(User user, String currentPassword) {
        User currentUser = userRepository.findOne(user.getId());
        if(currentUser == null) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.USER_NOT_FOUND);
        }
        String passwordHash = DigestUtils.md5DigestAsHex(currentPassword.getBytes(Charset.forName("UTF-8")));
        if(!currentUser.getPassword().equals(passwordHash)) {
            throw errorComponent.getComponentException(ComponentException.ErrorCode.INVALID_PASSWORD);
        }

        user.setStatus(currentUser.getStatus());
        boolean passwordChanged = false;
        if(user.getPassword() == null) {
            user.setPassword(currentUser.getPassword()); // mantém hash da senha
        } else {
            // Nova senha como hash
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(Charset.forName("UTF-8"))));
            passwordChanged = true;
        }

        User updatedUser = userRepository.save(user);
        emailComponent.sendDataChange(user, passwordChanged);
        return updatedUser;
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(id);
    }
}
