package kz.bookcrossing.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Book> books;

    @Column(name = "online")
    private Boolean online;

    @OneToMany(mappedBy = "userFav")
    private List<Favorite> favorites;
}
