package com.project.backend.services;

import com.project.backend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAllUsers();

    User getByUserName(String userName);

    boolean saveUser(User user);

    boolean isLoggedIn();

    String loggedInUserName();

    User loggedInUser();

    boolean hasRole(String role);
}