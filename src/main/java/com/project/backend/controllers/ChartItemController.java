package com.project.backend.controllers;

import com.project.backend.models.ChartItem;
import com.project.backend.models.User;
import com.project.backend.services.ChartItemService;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChartItemController {

    @Autowired
    ChartItemService chartService;
    @Autowired
    UserService userService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        User user = userService.loggedInUser();
        List<ChartItem> cartItems= chartService.listChartItems(user);
        System.out.println(" I am : "+ user.getUserName());
        System.out.println(" My first item is: "+ cartItems.get(0).getProduct().getTitle());
        model.addAttribute("cartItems", cartItems);
        return "shoppingcart";
    }
}
