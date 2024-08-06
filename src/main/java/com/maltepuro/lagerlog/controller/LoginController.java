package com.maltepuro.lagerlog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maltepuro.lagerlog.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    protected String login(@RequestParam("username") String user, @RequestParam("password") String password,
                        HttpSession session, HttpServletResponse response, RedirectAttributes redirectAttributes)
                        throws IOException {

        if (authenticationService.authentication(user, password)) {
            System.out.println("Autenticação bem-sucedida!");
            session.setAttribute("user", user);
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            System.out.println("Erro de autenticação!");
            redirectAttributes.addAttribute("erro", "true");
            return "redirect:/login";
        }
    }

     @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    
}
