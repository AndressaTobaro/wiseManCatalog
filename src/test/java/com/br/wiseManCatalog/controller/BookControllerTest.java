package com.br.wiseManCatalog.controller;


import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookServiceImpl bookService;
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<BookDTO> books = List.of(new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true));
        Page<BookDTO> pageBooks = new PageImpl<>(books);

        when(bookService.getAllBooks(pageable)).thenReturn(pageBooks);

        ResponseEntity<Page<BookDTO>> response = bookController.getAllBooks(pageable);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getContent().size());
        verify(bookService, times(1)).getAllBooks(pageable);
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        BookDTO book = new BookDTO(bookId, "Book Title", "Author", "Genre", "Description", 50.00, 4, true);

        when(bookService.getById(bookId)).thenReturn(book);

        ResponseEntity<BookDTO> response = bookController.getBookById(bookId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
    }

    @Test
    void testGetBooksByGenre() {
        String genre = "Science Fiction";
        List<BookDTO> books = List.of(new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true));

        when(bookService.getByGenre(genre)).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getBooksByGenre(genre);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetBooksByAuthor() {
        String author = "Author Name";
        List<BookDTO> books = List.of(new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true));

        when(bookService.getByAuthor(author)).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getByAuthor(author);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).getByAuthor(author);
    }

}
