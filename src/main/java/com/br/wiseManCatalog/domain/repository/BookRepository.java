package com.br.wiseManCatalog.domain.repository;

import com.br.wiseManCatalog.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.genre ILIKE %:genre%")
    List<Book> findByGenre(String genre);

    @Query("SELECT b FROM Book b WHERE b.author ILIKE %:author%")
    List<Book> findByAuthor(String author);

    List<Book> findBySale(boolean sale);
}
