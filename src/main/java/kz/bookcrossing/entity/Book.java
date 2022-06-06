package kz.bookcrossing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "ownerId", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;

    @OneToOne
    @JsonIgnore
    private Favorite userFav;
}
