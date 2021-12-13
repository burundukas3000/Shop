package com.project.backend.controllers;

import com.project.backend.models.User;
import com.project.backend.repositories.ImageRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.services.UserServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.project.backend.models.Image;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ProductRepository productRepo;

    @Autowired
    UserServiceImpl userService;

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
            uploadFile.setProduct(productRepo.getById(3l));
            uploadFile.setName(image.getOriginalFilename());
            uploadFile.setContent(image.getBytes());
            Long id = imageRepo.save(uploadFile).getId();
            List<Image> images = productRepo.getById(3l).getImages();
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
}