package kz.bookcrossing.repository;

import kz.bookcrossing.entity.BookcrossingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookcrossingZoneRepository extends JpaRepository<BookcrossingZone, Long> {
}
