package kz.bookcrossing.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String yearOfPublication;
    private String cityOfPublication;
    private String genre;
    private String author;
    private String translator;
    private String publishingHouse;
    private String ageLimit;
    private String language;
    private String publishedLanguage;
    private String isbn;
    private Long ownerId;
    private boolean active;
    private String description;
    private String comments;
    private String views;
}
