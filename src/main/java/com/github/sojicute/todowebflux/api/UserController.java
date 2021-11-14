package com.github.sojicute.todowebflux.api;


import com.github.sojicute.todowebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository repository;

    public UserController(@Qualifier("userRepositoryImpl") UserRepository repository) {
        this.repository = repository;
    }
}
