package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.Book;
import kz.bookcrossing.entity.Favorite;
import kz.bookcrossing.entity.User;
import kz.bookcrossing.repository.BookRepository;
import kz.bookcrossing.repository.FavoriteRepository;
import kz.bookcrossing.repository.UserRepository;
import kz.bookcrossing.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

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
        return userRepository.findById(userId).get().getBooks();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getFavorites(Long userId) {
        List<Favorite> favorites = favoriteRepository.findAllByUserId(userId);
        List<Book> books = new ArrayList<>();
        favorites.forEach(fav -> {
            bookRepository.findById(fav.getBookId()).ifPresent(books::add);
        });

        return books;
    }

    @Override
    public String addBookToFavorites(Long bookId, Long userId) {
        if (favoriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            return "The book is already in favorites";
        }

        Book book = bookRepository.findById(bookId).get();
        User user = userRepository.findById(userId).get();

        Favorite favorite = new Favorite();
        favorite.setBook(book);
        favorite.setBookId(bookId);
        favorite.setUserFav(user);
        favorite.setUserId(userId);

        user.getFavorites().add(favorite);
        userRepository.save(user);
        favoriteRepository.save(favorite);
        book.setUserFav(favorite);
        return "Book added to favorites";
    }

    @Override
    public Book removeFromFavorites(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).get();
        User user = userRepository.findById(userId).get();
        Favorite favorite = book.getUserFav();
        book.setUserFav(null);
        user.getFavorites().remove(favorite);
        favoriteRepository.delete(favorite);
        return bookRepository.save(book);
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
