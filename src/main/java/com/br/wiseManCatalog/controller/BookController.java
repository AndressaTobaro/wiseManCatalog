package com.br.wiseManCatalog.controller;

import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.impl.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        log.info("New request received at getAllBooks");
        return bookService.findAllBooks();

    }
}
