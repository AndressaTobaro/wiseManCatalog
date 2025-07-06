package com.br.wiseManCatalog.application.service;


import com.br.wiseManCatalog.application.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookDTO> getAllBooks(Pageable pageable);

//    List<BookDTO> getAllBooks();

    BookDTO getById(Long id);

    List<BookDTO> getByGenre(String genre);

    List<BookDTO> getByAuthor(String author);

    List<BookDTO> getBooksAreSale(boolean sale);
}
