package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.User;
import kz.bookcrossing.repository.UserRepository;
import kz.bookcrossing.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);

        return "User deleted successfully";
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> getPage(Map<String, String> pageParams) {
        PageRequest pageRequest = createPageRequest(pageParams);
        return repository.findAll(pageRequest);
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
