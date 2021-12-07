package com.project.backend.controllers;

import com.project.backend.repositories.ImageRepository;
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

@Controller
public class ProductController {

    @Autowired
    ImageRepository imageRepo;

    @GetMapping("/customer/createproduct")
    public String showUploadForm() {
        return "imageuploadToDelete";
    }

    @PostMapping("/customer/upload")
    public String uploadImage(@RequestParam MultipartFile image, Model model) throws Exception {
        if (image != null) {
            System.out.println("Saving file: " + image.getOriginalFilename());

            Image uploadFile = new Image();
            uploadFile.setName(image.getOriginalFilename());
            uploadFile.setContent(image.getBytes());
            uploadFile.setProduct_id(1l);
            Long id = imageRepo.save(uploadFile).getId();
            model.addAttribute("id", id);
        }
        return "imageuploadToDelete";
    }

    @GetMapping(value = "/customer/product/{id}")
    public void getProductById(HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        response.setContentType("image/jpeg");

        byte[] bytes = imageRepo.getById(id).getContent();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}