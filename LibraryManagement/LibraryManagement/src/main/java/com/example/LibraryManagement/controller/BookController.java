package com.example.LibraryManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LibraryManagement.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    // Store books in ArrayList
    List<Book> books = new ArrayList<>();

    // b) Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // c) Retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {

        return books;
    }

    // c) Retrieve book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // d) Update book details
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setAvailableCopies(updatedBook.getAvailableCopies());

                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // e) Delete book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                books.remove(book);

                return new ResponseEntity<>(
                        "Book deleted successfully",
                        HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(
                "Book not found",
                HttpStatus.NOT_FOUND);
    }
}