package com.duan.springbootcommondemo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/9/18.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableCaching // 使用 redis 时 spring boot 默认配置了 JedisConnectionFactory RedisTemplate StringRedisTemplate
public class CacheConfig {
/*

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${spring.redis.pool.maxTotal}")
    private int maxTotal;

    @Value("${spring.redis.pool.maxWait}")
    private int maxWait;

    @Value("${spring.redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.pool.testOnReturn}")
    private boolean testOnReturn;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);

        return jedisPoolConfig;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());

        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate() {

        final RedisTemplate<String, Long> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;

    }
*/

}
