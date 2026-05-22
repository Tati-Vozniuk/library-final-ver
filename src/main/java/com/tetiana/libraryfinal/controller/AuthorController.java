package com.tetiana.libraryfinal.controller;

import com.tetiana.libraryfinal.model.Author;
import com.tetiana.libraryfinal.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("author", new Author());
        return "authors";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Author author,
                       @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        if (!photoFile.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + photoFile.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/img/authors");
            Files.createDirectories(uploadPath);
            Files.copy(photoFile.getInputStream(), uploadPath.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            author.setPhoto(filename);
        }
        authorService.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("/{id}")
    public String authorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author-info";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }
}
