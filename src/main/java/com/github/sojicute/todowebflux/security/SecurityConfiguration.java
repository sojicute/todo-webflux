package com.github.sojicute.todowebflux.security;

import com.github.sojicute.todowebflux.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfiguration  {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                    .pathMatchers("/api/**").authenticated()
                .and()
                .authorizeExchange()
                    .anyExchange()
                    .authenticated()
                .and()
                    .httpBasic().and()
                    .formLogin()
                .and().build();
    }


    @Bean
    public MyReactiveUserDetailsService userDetailsService() {
        return new MyReactiveUserDetailsService();
    }
}
