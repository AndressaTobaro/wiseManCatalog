package com.br.wiseManCatalog.application.service.impl;


import com.br.wiseManCatalog.application.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAllBooks();

}
