package com.example.backend.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    // 起動時にサンプルデータを挿入
    @PostConstruct
    public void init() {
        if(repository.count() == 0) {  // 既にデータがあれば追加しない
            repository.save(new User("Alice"));
            repository.save(new User("Bob"));
            repository.save(new User("Charlie"));
        }
    }
}
