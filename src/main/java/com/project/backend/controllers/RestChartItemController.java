package com.project.backend.controllers;

import com.project.backend.services.ChartItemService;
import com.project.backend.services.ProductService;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestChartItemController {

    @Autowired
    ChartItemService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @PostMapping("/cart/add/{id}/{qnt}")
    public String addProductToShoppingCart(@PathVariable("id") Long id, @PathVariable("qnt") int quantity) {
        if(userService.loggedInUser()!=null && !userService.loggedInUser().getUserName().equals("anonymousUser")) {
            Integer currentQuantity = cartService.addCartItem(productService.findProductById(id), quantity, userService.loggedInUser());
            return "The product quantity is: "+currentQuantity;
        }else{
            return "You must log in to use a shopping cart";
        }
    }

    @GetMapping("/checkavailability")
    public @ResponseBody
    String checkAvailability(@RequestParam("q") int qnt, @RequestParam("id") Long id) {
        String mssg= null;
        int available = productService.findProductById(id).getAmount();
        if(available>=qnt) {
            mssg="";
        }
        else {
            mssg="Available only: "+available+" items";
        }
        return mssg;
    }

}
