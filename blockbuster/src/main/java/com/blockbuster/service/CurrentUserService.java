package com.blockbuster.service;

import com.blockbuster.entity.User;
import com.blockbuster.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(Jwt jwt) {
        String sub = jwt.getSubject();
        return userRepository.findByAuth0Sub(sub)
                .orElseGet(() -> userRepository.save(User.builder().auth0Sub(sub).build()));
    }
}
