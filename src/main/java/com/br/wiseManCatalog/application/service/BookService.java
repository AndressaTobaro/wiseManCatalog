package com.br.wiseManCatalog.application.service;


import com.br.wiseManCatalog.application.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getById(Long id);

    List<BookDTO> getByGenre(String genre);

}
