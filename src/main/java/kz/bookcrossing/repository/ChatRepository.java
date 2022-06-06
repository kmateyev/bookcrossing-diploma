package kz.bookcrossing.repository;

import kz.bookcrossing.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> getAllByDeletedAtIsNull();
}
