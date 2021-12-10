package com.project.backend.controllers;

import com.project.backend.repositories.ImageRepository;
import com.project.backend.repositories.ProductRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.project.backend.models.Image;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Controller("/customer")
public class CustomerController {

    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ProductRepository productRepo;

    @GetMapping("/customer/createproduct")
    public String showUploadForm() {
        return "imageup";
    }

    @PostMapping("/customer/upload")
    public String uploadImage(@RequestParam MultipartFile image, Model model) throws Exception {
        if (image != null) {
            System.out.println("Saving file: " + image.getOriginalFilename());

            Image uploadFile = new Image();
            uploadFile.setProduct(productRepo.getById(1l));
            uploadFile.setName(image.getOriginalFilename());
            uploadFile.setContent(image.getBytes());
            Long id = imageRepo.save(uploadFile).getId();
            List<Image> images = productRepo.getById(1l).getImages();
            model.addAttribute("id", id);
            model.addAttribute("image", images.get(images.size()-1));
        }
        return "imageup";
    }

    @GetMapping(value = "/customer/product/{id}")
    public void getProductById(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        response.setContentType("image/jpeg");

        byte[] bytes = imageRepo.getById(id).getContent();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}