package com.project.backend.controllers;

import com.project.backend.models.User;
import com.project.backend.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String home(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("name", username);
        model.addAttribute("role", authorities.toArray()[0]);
        return "home";
    }

    @GetMapping("/error")
    public String error() {
        
        return "error";
    }

    @GetMapping("/manager/customers")
    public String allCustomers(Model model) {
        List<User> customers = userService.findAllUsers();
        System.out.println(customers.get(0).toString());
        model.addAttribute("customers", customers);
        return "customers";
    }

}