package com.project.backend.controllers;

import com.project.backend.models.User;
import com.project.backend.repositories.ImageRepository;
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

@Controller("/manager")
public class ManagerController {

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/manager/customers")
    public String allCustomers(Model model) {
        List<User> customers = userService.findAllUsers();
        System.out.println(customers.get(0).toString());
        model.addAttribute("customers", customers);
        return "customers";
    }
}