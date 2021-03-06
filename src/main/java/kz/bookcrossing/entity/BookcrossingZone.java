package kz.bookcrossing.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "bookcrossing_zone")
public class BookcrossingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "book_id")
    @ManyToOne
    private Book book;
    private String address;
    private String count;
}
