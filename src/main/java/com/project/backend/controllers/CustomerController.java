package com.project.backend.controllers;
import com.project.backend.general.AddDateUtils;
import com.project.backend.models.Purchase;
import com.project.backend.models.User;
import com.project.backend.repositories.ImageRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.services.PurchaseService;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for Logged In User
@Controller
public class CustomerController {

    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserService userService;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/customer/profile")
    public String profile(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUserName(userName);
        for(Purchase p: user.getPurchases()){
            p.feDateCreated = AddDateUtils.dateWithoutTime(p.getDateCreated());
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/customer/purchases")
    public String orderHistory(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUserName(userName);
        List<Purchase> purchases = user.getPurchases();
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    @GetMapping("/customer/purchase/{id}")
    public String viewOrder(@PathVariable("id") Long id, Model model)  {
        Purchase purchase = purchaseService.getByPurchaseId(id);
        model.addAttribute("purchase", purchase);
        return "purchase";
    }
}
