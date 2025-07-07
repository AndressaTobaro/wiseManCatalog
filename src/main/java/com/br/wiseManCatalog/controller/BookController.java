package com.br.wiseManCatalog.controller;

import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
@Tag(name = "Book", description = "Book operations")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Find all books")
    public ResponseEntity<Page<BookDTO>> getAllBooks(Pageable pageable) {
        log.info("New request received at getAllBooks");
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by id")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        log.info("New request received at getBookById, id: {}", id);
        BookDTO book = bookService.getById(id);
        log.info("Response at getBookById: {}", book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/genre")
    @Operation(summary = "Find book by genre")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@RequestParam String genre) {
        if (genre == null || genre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genre parameter is mandatory");
        }
        log.info("New request received at getBookByGenre, genre: {}", genre);
        List<BookDTO> books = bookService.getByGenre(genre);
        log.info("Response at getBookByGenre: {}", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/author")
    @Operation(summary = "Find book by Author")
    public ResponseEntity<List<BookDTO>> getByAuthor(@RequestParam String author) {
        if (author == null || author.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author parameter is mandatory");
        }
        log.info("New request received at getBookByAuthor, author: {}", author);
        List<BookDTO> books = bookService.getByAuthor(author);
        log.info("Response at getBookByAuthor: {}", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/sale")
    @Operation(summary = "Find book for sale")
    public ResponseEntity<List<BookDTO>> getBooksAreSale(@RequestParam(required = false, defaultValue = "true") boolean sale) {
        log.info("New request received at getBooksAreSale, sale: {}", sale);
        List<BookDTO> books = bookService.getBooksAreSale(sale);
        log.info("Response at getBooksIsSale: {}", books);
        return ResponseEntity.ok(books);
    }
}
