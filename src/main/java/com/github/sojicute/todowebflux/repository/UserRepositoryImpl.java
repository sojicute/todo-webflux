package com.github.sojicute.todowebflux.repository;

import com.github.sojicute.todowebflux.domain.User;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class UserRepositoryImpl implements UserRepository {

    private final static String KEY = "USERS";

    private final ReactiveRedisOperations<String, Object> redisOperations;
    private final ReactiveHashOperations<String, String, User> hashOperations;

    @Autowired
    public UserRepositoryImpl(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }


    @Override
    public Mono<Boolean> save(User user) {
        return hashOperations.put(KEY, user.getId(), user);
    }

    @Override
    public Mono<User> findById(String id) {
        return hashOperations.get(KEY, id);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return hashOperations.values(KEY)
                .filter(user -> user.getUsername().equals(username))
                .singleOrEmpty();
    }



    @Override
    public <S extends User> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends User> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }


    @Override
    public Mono<User> findById(Publisher<String> id) {
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
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Flux<User> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public Flux<User> findAllById(Publisher<String> idStream) {
        return null;
    }

    @Override
    public Mono<Long> count() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Publisher<String> id) {
        return null;
    }

    @Override
    public Mono<Void> delete(User entity) {
        return null;
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends User> entities) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends User> entityStream) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }

}
