package com.project.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/rest")
public class RestDogController {

    @Autowired
    RestDogService restDogService;

    @GetMapping(path="/breeds")
    public String getAllBreeds(Model model){
        model.addAttribute("breeds", restDogService.getAllBreeds() );
        return "restbreeds";
    }
    @GetMapping(path="/randombreed")
    public String getRandomBreed(Model model){
        Random id = new Random();
        model.addAttribute("dog", restDogService.getAllBreeds().get(id.nextInt(30)) );
        return "restrandombreed";
    }
}
