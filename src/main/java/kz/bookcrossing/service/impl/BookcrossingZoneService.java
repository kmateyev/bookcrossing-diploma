package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.BookcrossingZone;
import kz.bookcrossing.repository.BookcrossingZoneRepository;
import kz.bookcrossing.service.IBookcrossingZoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BookcrossingZoneService implements IBookcrossingZoneService {

    private final BookcrossingZoneRepository repository;

    @Override
    public BookcrossingZone create(BookcrossingZone bcz) {
        return repository.save(bcz);
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Book crossing zone successfully deleted!";
    }

    @Override
    public List<BookcrossingZone> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<BookcrossingZone> getPage(Map<String, String> pageParams) {
        PageRequest pageRequest = createPageRequest(pageParams);
        return repository.findAll(pageRequest);
    }

    @Override
    public BookcrossingZone getById(Long id) {
        return repository.findById(id).get();
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
