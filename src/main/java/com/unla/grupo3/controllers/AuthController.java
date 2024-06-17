package com.unla.grupo3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";  // nombre de la vista login.html
    }

    @GetMapping("/loginsuccess")
    public String loginSuccess() {
        return "home";  // nombre de la vista que muestra después de iniciar sesión
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";  // nombre de la vista logout.html
    }
}
