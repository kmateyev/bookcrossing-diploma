package kz.bookcrossing.controller;

import kz.bookcrossing.entity.Genre;
import kz.bookcrossing.service.IGenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genre")
@AllArgsConstructor
public class GenreController {

    private final IGenreService service;

    @PostMapping
    public Genre create(@RequestBody Genre genre) {
        return service.create(genre);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public Genre getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Genre> getAll() {
        return service.getAll();
    }

    @GetMapping("/paging")
    public Page<Genre> getPage(@RequestParam Map<String, String> pageParams) {
        return service.getPage(pageParams);
    }
}
