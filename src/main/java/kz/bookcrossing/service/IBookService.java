package kz.bookcrossing.service;

import kz.bookcrossing.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IBookService {
    List<Book> getBooks();
    Page<Book> getBooksPageable(Map<String, String> pageParams);
    String deleteBook(Long id);
    Book addBookToUser(Long bookId, Long userId);
    Book removeBookFromUser(Long bookId, Long userId);
    Set<Book> getBooksByUserId(Long userId);

    Book getById(Long id);
}
