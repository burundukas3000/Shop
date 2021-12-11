package com.project.backend.services;

import com.project.backend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAllUsers();

    User getByUserName(String userName);

    boolean saveUser(User user);

    public boolean isLoggedIn();

    public String loggedInUserName();
}