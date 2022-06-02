package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.Book;
import kz.bookcrossing.repository.BookRepository;
import kz.bookcrossing.repository.UserRepository;
import kz.bookcrossing.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getBooksPageable(Map<String, String> pageParams) {
        PageRequest pageRequest = createPageRequest(pageParams);
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public String deleteBook(Long id) {
        Book deleteBook = bookRepository.findById(id).get();
        if (deleteBook.getOwnerId() != null) {
            return "The book is assigned to user!";
        } else {
            bookRepository.deleteById(id);
            return "The book has successfully deleted!";
        }
    }


    @Override
    public Book addBookToUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).get();
        book.setOwnerId(userId);
        return bookRepository.save(book);
    }

    @Override
    public Book removeBookFromUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).get();
        book.setOwnerId(null);
        return bookRepository.save(book);
    }

    @Override
    public Set<Book> getBooksByUserId(Long userId) {
        Set<Book> books = userRepository.findById(userId).get().getBooks();
        return books;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    PageRequest createPageRequest(Map<String, String> pageParams) {
        int page = 0;
        int size = 5;
        Sort sort = Sort.by("id");
        if (pageParams.containsKey("page") && pageParams.containsKey("size")) {
            page = Integer.parseInt(pageParams.get("page"));
            size = Integer.parseInt(pageParams.get("size"));
        }
        if (pageParams.containsKey("sortBy"))
            sort = Sort.by(pageParams.get("sortBy"));
        if (pageParams.containsKey("sortDirection")) {
            if (pageParams.get("sortDirection").equals("asc")) {
                sort.ascending();
            } else {
                sort.descending();
            }
        }

        return PageRequest.of(page, size, sort);
    }
}
