package kz.bookcrossing.controller;

import kz.bookcrossing.entity.User;
import kz.bookcrossing.repository.UserRepository;
import kz.bookcrossing.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final IUserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/paging")
    public Page<User> getPage(@RequestParam Map<String, String> pageParams) {
        return service.getPage(pageParams);
    }
}
