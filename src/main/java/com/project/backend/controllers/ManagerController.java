package com.project.backend.controllers;

import com.project.backend.models.*;
import com.project.backend.repositories.ImageRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.services.DiscountService;
import com.project.backend.services.ProductService;
import com.project.backend.services.UserService;
import com.project.backend.services.UserServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    DiscountService discountService;

    @GetMapping("/manager/customers")
    public String allCustomers(Model model) {
        List<User> customers = userService.findAllUsers();
        System.out.println(customers.get(0).toString());
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/manager/createproduct")
    public String showUploadForm() {
        return "imageup";
    }

    @PostMapping("/manager/upload")
    public String uploadImage(@RequestParam MultipartFile image, Model model) throws Exception {
        if (image != null) {
            System.out.println("Saving file: " + image.getOriginalFilename());
            Image uploadFile = new Image();
            uploadFile.setProduct(productService.findProductById(3l));
            uploadFile.setName(image.getOriginalFilename());
            uploadFile.setContent(image.getBytes());
            Long id = imageRepo.save(uploadFile).getId();
            List<Image> images = productService.findProductById(3l).getImages();
            model.addAttribute("id", id);
            model.addAttribute("image", images.get(images.size()-1));
        }
        return "imageup";
    }

    @GetMapping(value = "/manager/product/{id}")
    public void getProductById(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        response.setContentType("image/jpeg");

        byte[] bytes = imageRepo.getById(id).getContent();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @PostMapping(value = "/products/adddiscount")
    public String addDiscountToProducts(@ModelAttribute("Products") ProductListContainer productList, BindingResult br, Model model) throws Exception {
        List<Product> products = productList.getProducts();
        productService.addDiscountToProducts(products);
        List<Product> updatedProducts = productService.findByCategory(products.get(0).getCategory().toString());

        productList.setProducts(updatedProducts);
        model.addAttribute("Products", productList);
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("listOfDiscounts", discounts);

        return "products";
    }

    @PostMapping(value = "/product/removediscount/{id}")
    public String removeDiscountFromProduct(@PathVariable("id") Long id, Model model){
        productService.removeDiscountFromProduct(id);

        List<Product> products = productService.findByCategory(productService.findProductById(id).getCategory().toString());
        ProductListContainer productList = new ProductListContainer();
        productList.setProducts(products);
        model.addAttribute("Products", productList);
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("listOfDiscounts", discounts);

        return "products";
    }

}