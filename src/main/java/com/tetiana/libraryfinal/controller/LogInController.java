package com.tetiana.libraryfinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LogInController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
