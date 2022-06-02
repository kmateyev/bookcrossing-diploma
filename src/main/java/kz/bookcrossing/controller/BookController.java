package kz.bookcrossing.controller;

import kz.bookcrossing.entity.Book;
import kz.bookcrossing.entity.Favorite;
import kz.bookcrossing.repository.BookRepository;
import kz.bookcrossing.service.IBookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final IBookService service;
    private final BookRepository repository;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        return service.deleteBook(id);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getBooks();
    }

    @GetMapping("/paging")
    public Page<Book> getPageable(@RequestParam Map<String, String> pageParams) {
        return service.getBooksPageable(pageParams);
    }

    @GetMapping("/add/{bookId}/{userId}")
    public Book addBookToUser(@PathVariable Long bookId,
                              @PathVariable Long userId) {
        return service.addBookToUser(bookId, userId);
    }

    @GetMapping("/remove/{bookId}/{userId}")
    public Book removeFromUser(@PathVariable Long bookId,
                               @PathVariable Long userId) {
        return service.removeBookFromUser(bookId, userId);
    }

    @GetMapping("/get/userId/{userId}")
    public Set<Book> getBooksByUserId(@PathVariable Long userId) {
        return service.getBooksByUserId(userId);
    }

    @GetMapping("/get/favorites/{userId}")
    public List<Book> getFavorites(@PathVariable Long userId) {
        return service.getFavorites(userId);
    }

    @GetMapping("/favorite/add/{bookId}/{userId}")
    public String addToFavorite(@PathVariable Long bookId, @PathVariable Long userId) {
        return service.addBookToFavorites(bookId, userId);
    }

    @GetMapping("/favorite/remove/{bookId}/{userId}")
    public Book removeFromFavorite(@PathVariable Long bookId, @PathVariable Long userId) {
        return service.removeFromFavorites(bookId, userId);
    }
}
