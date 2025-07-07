package com.br.wiseManCatalog.mapper;

import com.br.wiseManCatalog.domain.entity.Book;
import org.junit.jupiter.api.Test;
import com.br.wiseManCatalog.application.dto.BookDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookMapperTest {

    @Test
    void testDaoToResponseDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setDescription("Book Description");
        book.setGenre("Fantasy");
        book.setAuthor("Author Name");

        BookDTO bookDTO = BookMapper.daoToResponseDto(book);

        assertNotNull(bookDTO);
        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getDescription(), bookDTO.getDescription());
        assertEquals(book.getGenre(), bookDTO.getGenre());
        assertEquals(book.getAuthor(), bookDTO.getAuthor());
    }
}
