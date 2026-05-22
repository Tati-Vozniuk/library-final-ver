package com.tetiana.libraryfinal.controller;

import com.tetiana.libraryfinal.service.AuthorService;
import com.tetiana.libraryfinal.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String home(Model model) {
        var books = bookService.findAll();
        var authors = authorService.findAll();
        model.addAttribute("popularBooks", books.stream().limit(4).toList());
        model.addAttribute("popularAuthors", authors.stream().limit(3).toList());
        return "index";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}