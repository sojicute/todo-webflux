package com.github.sojicute.todowebflux.security;

import com.github.sojicute.todowebflux.domain.User;
import com.github.sojicute.todowebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).switchIfEmpty(Mono.defer(() -> {
            return Mono.error(new UsernameNotFoundException("User Not Found"));
        })).map(User::toUserDetails);
    }
}
