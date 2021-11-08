package com.github.sojicute.todowebflux.repository;

import com.github.sojicute.todowebflux.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Repository
public class TaskRedisRepository {
    private final ReactiveRedisOperations<String, Task> taskOps;

    @Autowired
    public TaskRedisRepository(ReactiveRedisOperations<String, Task> taskOps) {
        this.taskOps = taskOps;
    }

    public Flux<Task> getAll() {
        return taskOps.keys("*").flatMap(taskOps.opsForValue()::get);
    }

    public Mono<Task> get(String key) {
        return taskOps.opsForValue().get(key);
    }

    public Mono<Boolean> add(Task task) {
        return taskOps.opsForValue().set(task.getId(), task);
    }

    public Mono<Boolean> put(String key, Task task) {
        return taskOps.opsForValue().set(key, task);
    }

    public Mono<Boolean> delete(String key) {
        return taskOps.opsForValue().delete(key);
    }
}

