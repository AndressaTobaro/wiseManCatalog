package com.br.wiseManCatalog.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String genre;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int rating;

    @Column
    private boolean sale;

}
