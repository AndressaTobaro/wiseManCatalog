package com.br.wiseManCatalog.controller;

import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.BookService;
import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        log.info("New request received at getAllBooks");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        log.info("New request received at getBookById, id: {}", id);
        BookDTO book = bookService.getById(id);
        log.info("Response at getBookById: {}", book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@RequestParam String genre) {
        if (genre == null || genre.isBlank()) {
            throw new BadRequestException("Genre parameter is mandatory");
        }
        log.info("New request received at getBookByGenre, genre: {}", genre);
        List<BookDTO> books = bookService.getByGenre(genre);
        log.info("Response at getBookByGenre: {}", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/author")
    public ResponseEntity<List<BookDTO>> getByAuthor(@RequestParam String author) {
        if (author == null || author.isBlank()) {
            throw new BadRequestException("Author parameter is mandatory");
        }
        log.info("New request received at getBookByAuthor, author: {}", author);
        List<BookDTO> books = bookService.getByAuthor(author);
        log.info("Response at getBookByAuthor: {}", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/sale")
    public ResponseEntity<List<BookDTO>> getBooksAreSale(@RequestParam boolean sale) {
        log.info("New request received at getBooksAreSale, author: {}", sale);
        List<BookDTO> books = bookService.getBooksAreSale(sale);
        log.info("Response at getBooksIsSale: {}", books);
        return ResponseEntity.ok(books);
    }
}
