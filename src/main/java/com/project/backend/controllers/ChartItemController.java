package com.project.backend.controllers;

import com.project.backend.models.ChartItem;
import com.project.backend.models.Product;
import com.project.backend.models.User;
import com.project.backend.services.ChartItemService;
import com.project.backend.services.ProductService;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChartItemController {

    @Autowired
    ChartItemService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        User user = userService.loggedInUser();
        List<ChartItem> cartItems= cartService.listChartItems(user);
        if(cartItems.size()==0) {
            model.addAttribute("emptyCart", "Your cart is empty.");
        } else {
            model.addAttribute("cartItems", cartItems);
        }
        return "shoppingcart";
    }
    @PostMapping("/cart/addproduct/{id}")
    public String addProductToCart(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
            CsrfToken csrfToken = new HttpSessionCsrfTokenRepository().loadToken(request);
            System.out.println(csrfToken+" got it");
        User user = userService.loggedInUser();
        Product product = productService.findProductById(id);
        cartService.addCartItem(product, 1, user);
        List<ChartItem> cartItems= cartService.listChartItems(user);
        model.addAttribute("cartItems", cartItems);
        return "shoppingcart";
    }

    @PostMapping("/cart/deleteproduct/{id}")
    public String deleteProductFromCart(@PathVariable("id") Long id, Model model) {
        User user = userService.loggedInUser();
        Product product = productService.findProductById(id);
        cartService.deleteCartItem(product, user);
        List<ChartItem> cartItems= cartService.listChartItems(user);
        model.addAttribute("cartItems", cartItems);
        return "shoppingcart";
    }
}
