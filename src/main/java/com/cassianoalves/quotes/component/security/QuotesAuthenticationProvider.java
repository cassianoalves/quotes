package com.cassianoalves.quotes.component.security;

import com.cassianoalves.quotes.component.UserComponent;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class QuotesAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserComponent userComponent;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getDetails() != null && authentication.getDetails() instanceof User) {
            return authentication;
        }
        User user = userComponent.authenticate((String) authentication.getPrincipal(), (String) authentication.getCredentials());
        if(user == null) {
            return null;
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredentials()
        );
        token.setDetails(user);
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
