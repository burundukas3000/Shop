package com.project.backend.controllers;

import com.project.backend.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/home")
    public String home() {return "home";}

    @GetMapping("/shop")
    public String shop() {return "shop";}

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/registration")
    public String registration() {return "registration";}

    @GetMapping("/about")
    public String about() {return "about";}

    @GetMapping("/contact")
    public String contact() {return "contact";}

    @GetMapping("/cart")
    public String cart() {return "cart";}

    @GetMapping("/iteam")
    public String iteam() {return "iteam";}

    @GetMapping("/product")
    public String product() {return "product";}

}
