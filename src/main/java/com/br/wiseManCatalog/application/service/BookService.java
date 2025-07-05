package com.br.wiseManCatalog.application.service;


import com.br.wiseManCatalog.application.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAllBooks();

    BookDTO findById(Long id);
}
