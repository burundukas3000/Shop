package com.project.backend.controllers;

import com.project.backend.models.*;
import com.project.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    PurchasesProductsRepository ppRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/test")
    public String showUploadForm(Model model) {
        Discount dis = discountRepo.getById(1l);
        System.out.println(dis.getPercentage());
        dis.setPercentage(dis.getPercentage()+5);
        discountRepo.save(dis);
        dis=discountRepo.getById(1l);
        System.out.println("new discount is: "+dis.getPercentage());
        List<User> cust = userRepo.findAll();
        model.addAttribute("customers",cust);

        // Updating extra field in Associated Table (composite primary key + entity for associated table)
        Purchase pur = purchaseRepo.getById(3l);
        System.out.println("Purchase to be updated: " + pur);
        Product pr = productRepo.getById(1l);
        System.out.println("Product to be updated: "+ pr);
            // getting entity from with composite primary key
        PurchaseProductId id = new PurchaseProductId();
        id.setPurchaseId(3l);
        id.setProductId(1l);
        PurchasesProducts pp = ppRepo.getById(id);
            // changing amount
        pp.setAmount(4);
        System.out.println("Updated entity in associated table: " + pp);
        ppRepo.save(pp);

        // Getting extra field from associated table
        List<Product> topSoldList = productRepo.getTopProductsByCategory(Category.TOYS);
        for(Product p: topSoldList) {
            System.out.println("Product: "+ p.toString());
        }


        /*Image im = imageRepo.getById(1l);
        System.out.println(im.toString());

        Product prod = productRepo.getById(1l);
        System.out.println(prod.toString());

        Purchase pur = purchaseRepo.getById(1l);
        System.out.println(pur.toString());

        Role role = roleRepo.getById(1l);
        System.out.println(role.toString());

        User user = userRepo.getById(1l);
        System.out.println(user.toString());*/

        return "test";
    }
}
