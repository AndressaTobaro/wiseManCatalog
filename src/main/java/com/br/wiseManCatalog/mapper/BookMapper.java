package com.br.wiseManCatalog.mapper;

import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.domain.entity.Book;

public class BookMapper {

    public static BookDTO daoToResponseDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setGenre(book.getGenre());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setRating(book.getRating());
        dto.setSale(book.isSale());
        return dto;
    }
}