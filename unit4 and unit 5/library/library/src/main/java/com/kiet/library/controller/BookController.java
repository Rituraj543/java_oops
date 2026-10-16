package com.kiet.library.controller;

import com.kiet.library.model.Book;
import com.kiet.library.service.BookService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return service.getBookById(bookId);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable int bookId,
            @RequestBody Book book) {
        return service.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        boolean result = service.deleteBook(bookId);

        if (result) {
            return "Book deleted successfully";
        } else {
            return "Book not found";
        }
    }

    @GetMapping("/search")
    public List<Book> searchByCategory(
            @RequestParam String category) {
        return service.searchByCategory(category);
    }

    @GetMapping("/published")
    public List<Book> findPublishedFromYear(
            @RequestParam int fromYear) {
        return service.findPublishedFromYear(fromYear);
    }
}