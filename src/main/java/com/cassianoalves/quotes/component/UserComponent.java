package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.User;

public interface UserComponent {
    User signUp(User user, String inviteId);
    User confirmUser(String confirmKey);
    User authenticate(String email, String openPassword);
    User update(User user, String currentPassword);
    User findById(String id);
}
