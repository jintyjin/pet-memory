package com.family.petmemory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}
