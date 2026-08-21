package com.blockbuster.repository;

import com.blockbuster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAuth0Sub(String auth0Sub);
}
