package sandro.lesson.spring.service.user;

import sandro.lesson.spring.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    boolean create(User user);

    Optional<User> findById(Long id);

    boolean delete(User user);

    boolean deleteById(Long id);
}
