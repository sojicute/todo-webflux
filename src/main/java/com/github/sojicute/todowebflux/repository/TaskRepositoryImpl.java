package com.github.sojicute.todowebflux.repository;

import com.github.sojicute.todowebflux.domain.Task;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private final static String KEY = "TASKS";

    private final ReactiveRedisOperations<String, Object> redisOperations;
    private final ReactiveHashOperations<String, String, Task> hashOperations;

    @Autowired
    public TaskRepositoryImpl(@Qualifier("taskRedisOperations") ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }

    @Override
    public Mono<Task> save(Task task) {
        return hashOperations.put(KEY, task.getId(), task).thenReturn(task);
    }

    @Override
    public Mono<Task> findById(String id) {
        return hashOperations.get(KEY, id);
    }

    @Override
    public Flux<Task> findAll() {
        return hashOperations.values(KEY);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return hashOperations.remove(KEY, id).then();
    }



    //Others...

    @Override
    public <S extends Task> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Task> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

    @Override
    public Mono<Task> findById(Publisher<String> id) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(String s) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Publisher<String> id) {
        return null;
    }

    @Override
    public Flux<Task> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public Flux<Task> findAllById(Publisher<String> idStream) {
        return null;
    }

    @Override
    public Mono<Long> count() {
        return null;
    }


    @Override
    public Mono<Void> deleteById(Publisher<String> id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Task entity) {
        return null;
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends Task> entities) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends Task> entityStream) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }

    @Override
    public Mono<Task> findByText(String text) {
        return null;
    }
}
