package com.github.sojicute.todowebflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

public class WebReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private ReactiveRedisOperations<String, org.springframework.security.core.userdetails.User> userOps;

    @Autowired
    private HashOperations hashOperations;



    @Override
    public Mono<UserDetails> findByUsername(String s) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user = User.withUsername(defUser.getUsername())
//                .password(encoder.encode(defUser.getPassword()))
//                .roles(defUser.getRole())
//                .build();

        return null;
    }
}
