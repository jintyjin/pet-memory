package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.LoginForm;
import com.family.petmemory.validation.loginForm.LoginFormLoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginFormLoginValidator loginFormLoginValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(loginFormLoginValidator);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "/login/loginForm";
    }

    @PostMapping("login")
    public String login(@Validated LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        return "redirect:/";
    }
}
