package com.project.backend.controllers;

import com.project.backend.models.*;
import com.project.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This controller will be deleted. Created for testing Hibernate mappings only.
@Controller
public class TestController {

    @Autowired
    DiscountRepository discountRepo;
    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    PurchaseRepository purchaseRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/test")
    public String showUploadForm() {
        Discount dis = discountRepo.getById(1l);
       System.out.println(dis.toString());

        Image im = imageRepo.getById(1l);
        System.out.println(im.toString());

        Product prod = productRepo.getById(1l);
        System.out.println(prod.toString());

        Purchase pur = purchaseRepo.getById(1l);
        System.out.println(pur.toString());

        Role role = roleRepo.getById(1l);
        System.out.println(role.toString());

        User user = userRepo.getById(1l);
        System.out.println(user.toString());

        return "test";
    }
}
