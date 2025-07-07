package com.br.wiseManCatalog.impl;

import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.impl.BookServiceImpl;
import com.br.wiseManCatalog.domain.entity.Book;
import com.br.wiseManCatalog.domain.repository.BookRepository;
import com.br.wiseManCatalog.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book = new Book(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true);
        bookDTO = new BookDTO(1L, "Book Title", "Author", "Genre", "Description", 50.00, 4, true);

    }

    @Test
    void testFindAllBooks() {
        try (MockedStatic<BookMapper> mockedStatic = mockStatic(BookMapper.class)) {
            mockedStatic.when(() -> BookMapper.daoToResponseDto(book)).thenReturn(bookDTO);

            Pageable pageable = PageRequest.of(0, 10);
            Page<Book> pageMock = new PageImpl<>(Arrays.asList(book), pageable, 1);
            when(bookRepository.findAll(pageable)).thenReturn(pageMock);

            Page<BookDTO> result = bookService.getAllBooks(pageable);

            assertEquals(1, result.getTotalElements());
            assertEquals(bookDTO, result.getContent().get(0));
            verify(bookRepository, times(1)).findAll(pageable);
        }
    }

    @Test
    void testGetByIdWhenBookExists() {
        try (MockedStatic<BookMapper> mockedStatic = mockStatic(BookMapper.class)) {
            mockedStatic.when(() -> BookMapper.daoToResponseDto(book)).thenReturn(bookDTO);

            Long bookId = 1L;
            when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

            BookDTO result = bookService.getById(bookId);

            assertNotNull(result);
            assertEquals(bookDTO, result);
            verify(bookRepository, times(1)).findById(bookId);
        }
    }

    @Test
    void testGetByGenre() {
        try (MockedStatic<BookMapper> mockedStatic = mockStatic(BookMapper.class)) {
            mockedStatic.when(() -> BookMapper.daoToResponseDto(book)).thenReturn(bookDTO);

            String genre = "Genre Name";
            List<Book> daoList = Arrays.asList(book);
            when(bookRepository.findByGenre(genre)).thenReturn(daoList);

            List<BookDTO> result = bookService.getByGenre(genre);

            assertEquals(1, result.size());
            assertEquals(bookDTO, result.get(0));
            verify(bookRepository, times(1)).findByGenre(genre);
        }
    }

    @Test
    void testGetByAuthor() {
        try (MockedStatic<BookMapper> mockedStatic = mockStatic(BookMapper.class)) {
            mockedStatic.when(() -> BookMapper.daoToResponseDto(book)).thenReturn(bookDTO);

            String author = "Author Name";
            List<Book> daoList = Arrays.asList(book);
            when(bookRepository.findByAuthor(author)).thenReturn(daoList);

            List<BookDTO> result = bookService.getByAuthor(author);

            assertEquals(1, result.size());
            assertEquals(bookDTO, result.get(0));
            verify(bookRepository, times(1)).findByAuthor(author);
        }
    }

    @Test
    void testGetBySale() {
        try (MockedStatic<BookMapper> mockedStatic = mockStatic(BookMapper.class)) {
            mockedStatic.when(() -> BookMapper.daoToResponseDto(book)).thenReturn(bookDTO);

            boolean sale = true;
            List<Book> daoList = Arrays.asList(book);
            when(bookRepository.findBySale(sale)).thenReturn(daoList);

            List<BookDTO> result = bookService.getBooksAreSale(sale);

            assertEquals(1, result.size());
            assertEquals(bookDTO, result.get(0));
            verify(bookRepository, times(1)).findBySale(sale);
        }
    }

    @Test
    void testGetBooksByAuthorThrowsNotFoundExceptionWhenListIsEmpty() {
        when(bookRepository.findByAuthor("Unknown")).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> bookService.getByAuthor("Unknown")
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Book not found", exception.getReason());
    }
}

