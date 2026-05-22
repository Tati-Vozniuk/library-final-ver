package com.tetiana.libraryfinal.controller;

import com.tetiana.libraryfinal.service.AuthorService;
import com.tetiana.libraryfinal.model.Book;
import com.tetiana.libraryfinal.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;


    @GetMapping
    public String list(@RequestParam(required = false) String search,
                       @RequestParam(required = false) String filterType,
                       Model model) {
        model.addAttribute("books", bookService.search(search, filterType));
        model.addAttribute("search", search);
        model.addAttribute("filterType", filterType);
        return "books";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book,
                       @RequestParam("coverFile") MultipartFile coverFile) throws IOException {
        if (!coverFile.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + coverFile.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/img/books");
            Files.createDirectories(uploadPath);
            Files.copy(coverFile.getInputStream(), uploadPath.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            book.setCoverImage(filename);
        }
        bookService.save(book);
        return "redirect:/books";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");
        PrintWriter writer = response.getWriter();
        writer.println("ID,Title,Author,Genre,Year");
        for (Book b : bookService.findAll()) {
            writer.printf("%d,%s,%s,%s,%d%n",
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor() != null ? b.getAuthor().getName() : "",
                    b.getGenre(),
                    b.getYear());
        }
    }

    @GetMapping("/{id}")
    public String bookInfo(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book-info";
    }
}