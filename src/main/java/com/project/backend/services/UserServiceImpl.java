package com.project.backend.services;

import com.project.backend.models.Role;
import com.project.backend.models.User;
import com.project.backend.models.UserActivity;
import com.project.backend.models.UserDao;
import com.project.backend.repositories.RoleRepository;
import com.project.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, ApplicationRunner {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

/*    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUserName " + username);
        String[] userNameAndEmail = StringUtils.split(username, String.valueOf(Character.LINE_SEPARATOR));
        if (userNameAndEmail == null || userNameAndEmail.length != 2) {
            throw new UsernameNotFoundException("Username and email must be provided");
        }
        User user = userRepo.getUserByUserNameAndEmail(userNameAndEmail[0], userNameAndEmail[1]);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username not found for email, userName=%s, email=%s", userNameAndEmail[0], userNameAndEmail[1]));
        }
        return new UserDao(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()), user.getEmail());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public List<UserActivity> findAllUsersByMoneySpent() { return userRepo.getByMoneySpent(); }

    public List<UserActivity> findAllUsersByPurchaseFrequency() { return userRepo.getByPurchaseFreq(); }

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Currently registered users: "+userRepo.count());
    }
}
