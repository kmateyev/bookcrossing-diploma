package kz.bookcrossing.service;

import kz.bookcrossing.entity.Genre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IGenreService {
    Genre create(Genre genre);
    String delete(Long id);
    List<Genre> getAll();
    Page<Genre> getPage(Map<String, String> pageParams);

    Genre getById(Long id);
}
