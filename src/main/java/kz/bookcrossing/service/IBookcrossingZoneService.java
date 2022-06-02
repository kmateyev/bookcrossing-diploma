package kz.bookcrossing.service;


import kz.bookcrossing.entity.BookcrossingZone;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IBookcrossingZoneService {
    BookcrossingZone create(BookcrossingZone bcz);
    String delete(Long id);
    List<BookcrossingZone> getAll();
    Page<BookcrossingZone> getPage(Map<String, String> pageParams);
    BookcrossingZone getById(Long id);
}
