package com.benz.reactive.config;

import com.benz.reactive.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory()
    {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost",6379));
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Employee> redisTemplate()
    {
       RedisTemplate<String,Employee> redisTemplate=new RedisTemplate<>();
       redisTemplate.setConnectionFactory(jedisConnectionFactory());

       return redisTemplate;

    }

}
