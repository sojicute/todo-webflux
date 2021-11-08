package com.github.sojicute.todowebflux.api;

import com.github.sojicute.todowebflux.domain.Task;
import com.github.sojicute.todowebflux.repository.TaskRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskRedisRepository taskRedisRepository;

    @Autowired
    public TaskController(TaskRedisRepository taskRedisRepository) {
        this.taskRedisRepository = taskRedisRepository;
    }

    @GetMapping("/task")
    public ResponseEntity<Flux<Task>> getAll() {
        return new ResponseEntity<>(taskRedisRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Mono<Task>> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(taskRedisRepository.get(id), HttpStatus.OK);
    }

    @PostMapping("/task")
    public Mono<Boolean> add(@RequestBody Task task) {
        return taskRedisRepository.add(task);
    }

    @PutMapping("/task/{id}")
    public Mono<Boolean> put(@PathVariable("id") String id, @RequestBody Task task) {
        return taskRedisRepository.put(id, task);
    }

    @DeleteMapping("/task/{id}")
    public Mono<Boolean> put(@PathVariable("id") String id) {
        return taskRedisRepository.delete(id);
    }
}
