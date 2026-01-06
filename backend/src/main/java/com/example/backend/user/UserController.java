package com.example.backend.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return repository.findById(id)
            .map(existing -> {
                existing.setName(user.getName());
                return repository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
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
