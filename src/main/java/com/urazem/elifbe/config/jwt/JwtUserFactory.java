package com.urazem.elifbe.config.jwt;

import com.urazem.elifbe.model.user.Status;
import com.urazem.elifbe.model.user.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }
}
