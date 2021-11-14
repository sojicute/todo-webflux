package com.github.sojicute.todowebflux.repository;

import com.github.sojicute.todowebflux.domain.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TaskRepository extends ReactiveCrudRepository<Task, String> {
    Mono<Task> findByText(String text);
}
