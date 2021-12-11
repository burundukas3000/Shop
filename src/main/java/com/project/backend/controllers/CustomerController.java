package com.project.backend.controllers;

import com.project.backend.models.Purchase;
import com.project.backend.models.User;
import com.project.backend.repositories.ImageRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.services.PurchaseService;
import com.project.backend.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.project.backend.models.Image;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

// Controller for Logged In User
@Controller("/customer")
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
    public String viewOrder(@PathVariable("id") Long id, Model model) {
        Purchase purchase = purchaseService.getByPurchaseId(id);
        model.addAttribute("purchase", purchase);
        return "purchase";
    }

    @GetMapping("/customer/createproduct")
    public String showUploadForm() {
        return "imageup";
    }
}