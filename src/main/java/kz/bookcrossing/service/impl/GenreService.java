package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.Genre;
import kz.bookcrossing.repository.GenreRepository;
import kz.bookcrossing.service.IGenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GenreService implements IGenreService {

    private final GenreRepository repository;

    @Override
    public Genre create(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "Genre deleted!";
    }

    @Override
    public List<Genre> getAll() {

        return repository.findAll();
    }

    @Override
    public Page<Genre> getPage(Map<String, String> pageParams) {
        PageRequest pageRequest = createPageRequest(pageParams);
        return repository.findAll(pageRequest);
    }

    @Override
    public Genre getById(Long id) {
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
