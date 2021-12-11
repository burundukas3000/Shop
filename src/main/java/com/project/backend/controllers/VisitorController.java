package com.project.backend.controllers;

import com.project.backend.models.User;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class VisitorController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String basic(Model model) {
        model.addAttribute("userin", userService.isLoggedIn());
        model.addAttribute("userName", userService.loggedInUserName());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("userin", userService.isLoggedIn());
        model.addAttribute("userName", userService.loggedInUserName());
        return "home";}

    @GetMapping("/shop")
    public String shop() {return "shop";}

    @GetMapping("/loginopt")
    public String loginopt() {return "loginopt";}

    @GetMapping("/login")
    public String login() {return "login";}

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

    // registration pages
    @GetMapping("/registration")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // check if user with username exists
    @RequestMapping("/registration/validateuser")
    public @ResponseBody
    String validateApp(@RequestParam("username") String username, ModelMap model) {
        String mssg= null;
        User checkedUser = userService.getByUserName(username);
        if(checkedUser!=null) {
            mssg="User with name: "+ checkedUser.getUsername() + " exists. ";
        }
        return mssg;
    }

    @PostMapping("/registration/processform")
    public String sumbitRegistration(@ModelAttribute("user") User user, BindingResult br, Model model) {

        String userName = user.getUsername();
        logger.info("processing registration for user: " + userName);

        // if submitted data not @Valid
        if (br.hasErrors()) {
            return "registration";
        }
        // if user already exists
        if (userService.getByUserName(userName) != null) {
            model.addAttribute("user", new User());
            model.addAttribute("errorWarning", "Username has been taken");
            return "registration";
        }
        boolean saved = userService.saveUser(user);
        logger.info("Successfully created user: " + user.getUsername()+ " "+saved);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userin", userService.isLoggedIn());
        return "home";
    }
}
