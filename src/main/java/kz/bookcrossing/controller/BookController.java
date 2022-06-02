package kz.bookcrossing.controller;

import kz.bookcrossing.entity.Book;
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
    private final IBookService bookService;
    private final BookRepository bookRepository;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getBooks();
    }

    @GetMapping("/paging")
    public Page<Book> getPageable(@RequestParam Map<String, String> pageParams) {
        return bookService.getBooksPageable(pageParams);
    }

    @GetMapping("/add/{bookId}/{userId}")
    public Book addBookToUser(@PathVariable Long bookId,
                              @PathVariable Long userId) {
        return bookService.addBookToUser(bookId, userId);
    }

    @GetMapping("/get/userId/{userId}")
    public Set<Book> getBooksByUserId(@PathVariable Long userId) {
        return bookService.getBooksByUserId(userId);
    }

    @GetMapping("/remove/{bookId}/{userId}")
    public Book removeFromUser(@PathVariable Long bookId,
                               @PathVariable Long userId) {
        return bookService.removeBookFromUser(bookId, userId);
    }
}
