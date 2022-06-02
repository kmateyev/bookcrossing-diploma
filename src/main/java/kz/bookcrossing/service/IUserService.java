package kz.bookcrossing.service;

import kz.bookcrossing.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IUserService {
    User create(User user);
    String delete(Long id);
    User getById(Long id);
    List<User> getAll();
    Page<User> getPage(Map<String, String> pageParams);
}
