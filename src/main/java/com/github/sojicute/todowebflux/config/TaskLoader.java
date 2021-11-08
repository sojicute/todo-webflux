package com.github.sojicute.todowebflux.config;

import com.github.sojicute.todowebflux.domain.Task;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;

@Component
public class TaskLoader {
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Task> taskOps;

    public TaskLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Task> taskOps) {
        this.factory = factory;
        this.taskOps = taskOps;
    }

    @PostConstruct
    public void loadData() {
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                Flux.just("Buy milk", "Buy bread", "Buy coffe")
                        .map(text -> new Task(UUID.randomUUID().toString(), text))
                        .flatMap(task -> taskOps.opsForValue().set(task.getId(), task)))
                        .thenMany(taskOps.keys("*")
                        .flatMap(taskOps.opsForValue()::get))
                        .subscribe(System.out::println);
    }
}
