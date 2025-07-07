package com.br.wiseManCatalog.controller;


import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;


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

    @Test
    void getBooksByGenreShouldThrowBadRequestWhenGenreIsNull() {
        // Arrange
        String genre = null;

        // Act & Assert
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> bookController.getBooksByGenre(genre)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Genre parameter is mandatory", exception.getReason());
    }

    @Test
    void getBooksByGenreShouldThrowBadRequestWhenAuthorIsBlank() {
        String genre = "   ";

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> bookController.getBooksByGenre(genre)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Genre parameter is mandatory", exception.getReason());
    }

    @Test
    void getBooksByAuthorShouldThrowBadRequestWhenAuthorIsNull() {
        // Arrange
        String author = null;

        // Act & Assert
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> bookController.getByAuthor(author)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Author parameter is mandatory", exception.getReason());
    }

    @Test
    void getBooksByAuthorShouldThrowBadRequestWhenGenreIsBlank() {
        String author = "   ";

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> bookController.getByAuthor(author)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Author parameter is mandatory", exception.getReason());
    }
    @Test
    void getBooksAreSaleShouldReturnBooksWhenSaleIsTrue() {
        boolean sale = true;
        List<BookDTO> mockBooks = List.of(new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true));
        Mockito.when(bookService.getBooksAreSale(sale)).thenReturn(mockBooks);

        ResponseEntity<List<BookDTO>> response = bookController.getBooksAreSale(sale);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void getBooksAreSaleShouldReturnBooksWhenSaleIsFalse() {
        boolean sale = false;
        List<BookDTO> mockBooks = List.of(new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, false));
        Mockito.when(bookService.getBooksAreSale(sale)).thenReturn(mockBooks);

        ResponseEntity<List<BookDTO>> response = bookController.getBooksAreSale(sale);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

}
