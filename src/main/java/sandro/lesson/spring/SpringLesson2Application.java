package sandro.lesson.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sandro.lesson.spring.dto.User;
import sandro.lesson.spring.service.user.UserService;

@SpringBootApplication
@Slf4j
public class SpringLesson2Application implements CommandLineRunner {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Value("${author-name}")
    private String authorName;


    public static void main(String[] args) {
        SpringApplication.run(SpringLesson2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.create(new User(null, "Иван"));
        log.info("All users: '{}'", userService.findAll());
        log.info("author-name = '{}'", authorName);
    }
}
