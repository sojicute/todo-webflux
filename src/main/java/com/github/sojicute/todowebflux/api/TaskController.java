package com.github.sojicute.todowebflux.api;

import com.github.sojicute.todowebflux.domain.Task;
import com.github.sojicute.todowebflux.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    public TaskController(@Qualifier("taskRepositoryImpl") TaskRepository repository) {
        this.repository  = repository;
    }


    @GetMapping("/task")
    public ResponseEntity<Flux<Task>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Mono<Task>> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<Mono<Task>> save(@RequestBody Task task) {
        return new ResponseEntity<>(repository.save(task), HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Mono<Void>> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(repository.deleteById(id), HttpStatus.OK);
    }
}
