package kz.bookcrossing.controller;

import kz.bookcrossing.entity.BookcrossingZone;
import kz.bookcrossing.service.IBookcrossingZoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookcrossingzone")
@AllArgsConstructor
public class BookCrossingZonesController {

    private final IBookcrossingZoneService service;

    @PostMapping
    public BookcrossingZone create(@RequestBody BookcrossingZone bcz) {
        return service.create(bcz);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public BookcrossingZone getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<BookcrossingZone> getAll() {
        return service.getAll();
    }

    @GetMapping("/paging")
    public Page<BookcrossingZone> getPage(@RequestParam Map<String, String> pageParam) {
        return service.getPage(pageParam);
    }
}
