package com.github.sojicute.todowebflux.config;

import com.github.sojicute.todowebflux.domain.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class TaskConfig {

    @Bean
    ReactiveRedisOperations<String, Task> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Task> serializer = new Jackson2JsonRedisSerializer<Task>(Task.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Task> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Task> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
