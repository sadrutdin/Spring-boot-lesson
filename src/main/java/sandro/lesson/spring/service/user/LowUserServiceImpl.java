package sandro.lesson.spring.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sandro.lesson.spring.annotation.Profiling;
import sandro.lesson.spring.dto.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Profiling
public class LowUserServiceImpl implements UserService {
    private final List<User> userRepository = new ArrayList<>();
    private long nextId = 0;

    @PostConstruct
    public void init() {
        log.info("Инициализация Low user service");
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository);
    }

    @Override
    public boolean create(User user) {
        if (userRepository.contains(user) || user.getId() != null) {
            return false;
        }
        user.setId(++nextId);
        return userRepository.add(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return id == null
                ? Optional.empty()
                : userRepository.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findAny();
    }

    @Override
    public boolean delete(User user) {
        return userRepository.remove(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return id != null && userRepository.removeIf(user -> Objects.equals(id, user.getId()));
    }
}
