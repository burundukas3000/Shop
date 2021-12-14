package com.project.backend.controllers;

import com.project.backend.models.*;
import com.project.backend.services.DiscountService;
import com.project.backend.services.ImageService;
import com.project.backend.services.ProductService;
import com.project.backend.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class VisitorController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ImageService imageService;
    @Autowired
    DiscountService discountService;

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

    // View all products within category
    @GetMapping("/products/category/{name}")
    public String productsByCategory(@PathVariable("name") String name, Model model) {
        List<Product> products = productService.findByCategory(name);
        ProductListContainer productList = new ProductListContainer();
        productList.setProducts(products);
        model.addAttribute("Products", productList);
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("listOfDiscounts", discounts);
        return "products";
    }

    // View all top sold products within category
    @GetMapping("/products/category/topsold/{name}")
    public String topSoldByCategory(@PathVariable("name") String name, Model model) {
        List<Product> products = productService.getTopSoldProductsByCategory(name);
        ProductListContainer productList = new ProductListContainer();
        productList.setProducts(products);
        model.addAttribute("Products", productList);
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("listOfDiscounts", discounts);
        return "products";
    }

    // View all products withing category by price asc
    @GetMapping("/products/category/bypriceasc/{name}")
    public String byPriceAscCategory(@PathVariable("name") String name, Model model) {
        List<Product> products = productService.getByPriceAscByCategory(name);
        ProductListContainer productList = new ProductListContainer();
        productList.setProducts(products);
        model.addAttribute("Products", productList);
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("listOfDiscounts", discounts);
        return "products";
    }

    // View one product by Id
    @GetMapping("/product/{id}")
    public String productsByCategoryById(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    // Converting image for each category display picture in the list
    @GetMapping(value = {"/products/category/image/{id}",
            "/product/image/{id}",
            "products/image/{id}",
            "/product/removediscount/image/{id}",
            "/products/category/topsold/image/{id}",
            "/products/category/bypriceasc/image/{id}"})
    public void getProductById(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        response.setContentType("image/jpeg");

        byte[] bytes = imageService.getById(id).getContent();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

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

    // where spring security forwards when processing registration form
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

    @PostMapping("/product/addtochart/{id}")
    public String addToChart(@PathVariable("id") Long id, BindingResult br, Model model) {

        return "home";
    }
}
