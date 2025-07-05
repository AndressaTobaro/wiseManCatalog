package com.br.wiseManCatalog.application.service.impl;


import com.br.wiseManCatalog.application.dto.BookDTO;
import com.br.wiseManCatalog.application.service.BookService;
import com.br.wiseManCatalog.domain.entity.Book;
import com.br.wiseManCatalog.domain.repository.BookRepository;
import com.br.wiseManCatalog.mapper.BookMapper;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    @Cacheable(value = "books", keyGenerator = "keyGenerator", unless = "#result == null or #result.empty")
    public List<BookDTO> findAllBooks() {
        List<Book> daoPage = repository.findAll();
        List<BookDTO> responseDTO = new ArrayList<>();
        daoPage.forEach(bookDAO -> responseDTO.add(BookMapper.daoToResponseDto(bookDAO)));
        return responseDTO;
    }

    @Override
    @Cacheable(value = "bookById", key = "#id", unless = "#result == null")
    public BookDTO findById(Long id){
        Book book = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id not found"));
        return BookMapper.daoToResponseDto(book);
    }

}
