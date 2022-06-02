package kz.bookcrossing.repository;

import kz.bookcrossing.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUserId(Long userId);
    Favorite findByUserIdAndBookId(Long userId, Long bookId);
    Boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
