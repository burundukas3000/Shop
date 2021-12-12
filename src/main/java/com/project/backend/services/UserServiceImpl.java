package com.project.backend.services;

import com.project.backend.models.Role;
import com.project.backend.models.User;
import com.project.backend.repositories.RoleRepository;
import com.project.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public User getByUserName(String userName) {
        User user = userRepo.findByUserName(userName);
        return user;
    }

    @Transactional
    public boolean saveUser(User user) {
        Role role = new Role();
        boolean saved = false;

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        // by default role = USER
        role = roleRepo.findRoleByRoleName("ROLE_CUSTOMER");
        user.addRole(role);
        userRepo.save(user);
        if(userRepo.findByUserName(user.getUserName())!=null){
            saved = true;
        }
        return saved;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public boolean isLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }

    public String loggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User loggedInUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()+"************* getAuthentication().getPrincipal() - Principal.toString()");
        System.out.println(SecurityContextHolder.getContext().getAuthentication()+" .getAuthentication()");
        User user = null;
        if(isLoggedIn()) {
            user = userRepo.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return user;
    }

    public final boolean hasRole(String role) {
        boolean hasRole = false;
        UserDetails userDetails = loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDetails != null) {
            if (isRolePresent((Collection<GrantedAuthority>) userDetails.getAuthorities(), role)) {
                hasRole = true;
            }
        }
        return hasRole;
    }
    private boolean isRolePresent(Collection<GrantedAuthority> authorities, String role) {
        boolean isRolePresent = false;
        for (GrantedAuthority grantedAuthority : authorities) {
            isRolePresent = grantedAuthority.getAuthority().equals(role);
            if (isRolePresent) break;
        }
        return isRolePresent;
    }
}
