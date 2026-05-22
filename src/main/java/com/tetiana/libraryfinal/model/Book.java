package com.tetiana.libraryfinal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    @Column(name = "publish_year")
    private Integer year;
    private String description;
    @Column(name = "cover_image")
    private String coverImage;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
