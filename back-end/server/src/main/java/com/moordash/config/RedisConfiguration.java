package com.moordash.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("Start creating redis template object...");

        RedisTemplate redisTemplate = new RedisTemplate();

        // Set the redis connection factory object
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Set the serializer for the redis key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
