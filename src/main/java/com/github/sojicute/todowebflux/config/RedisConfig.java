package com.github.sojicute.todowebflux.config;

import com.github.sojicute.todowebflux.domain.Task;
import com.github.sojicute.todowebflux.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;


@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisOperations<String, Object> taskRedisOperations(ReactiveRedisConnectionFactory factory) {

        RedisSerializationContext<String, Object> context = RedisSerializationContext
                .<String, Object>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new GenericToStringSerializer<>(Object.class))
                .hashKey(new StringRedisSerializer())
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

//    @Bean
//    public ReactiveRedisOperations<String, User> userRedisOperations(ReactiveRedisConnectionFactory factory) {
//
//        RedisSerializationContext<String, User> serializationContext = RedisSerializationContext
//                .<String, User>newSerializationContext(new StringRedisSerializer())
//                .key(new StringRedisSerializer())
//                .value(new GenericToStringSerializer<>(User.class))
//                .hashKey(new StringRedisSerializer())
//                .hashValue(new GenericJackson2JsonRedisSerializer())
//                .build();
//        return new ReactiveRedisTemplate<>(factory, serializationContext);
//    }
}
