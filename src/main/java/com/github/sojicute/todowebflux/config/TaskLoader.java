package com.github.sojicute.todowebflux.config;

import com.github.sojicute.todowebflux.domain.Task;
import com.github.sojicute.todowebflux.domain.User;
import com.github.sojicute.todowebflux.repository.TaskRepository;
import com.github.sojicute.todowebflux.repository.UserRepository;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class TaskLoader {
    private final ReactiveRedisConnectionFactory factory;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskLoader(ReactiveRedisConnectionFactory factory, TaskRepository taskRepository, UserRepository userRepository) {
        this.factory = factory;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void loadDataTask() {
        factory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(
                        Flux
                            .just("Buy milk", "Buy bread")
                            .flatMap(
                                    text -> {
                                        String id = UUID.randomUUID().toString();
                                        return taskRepository.save(new Task(id, text));
                                    }
                            )
                )
                .thenMany(
                        Flux
                            .just("kime", "admin", "lox")
                            .flatMap(
                                    username -> {
                                        String id = UUID.randomUUID().toString();
                                        return userRepository.save(new User(id, username, "111", "USER"));
                                    }
                            )
                )
                .subscribe();


//        taskRepository.save(new User("1", "Anton", "123", "USER"));
    }


}