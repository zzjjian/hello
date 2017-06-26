package com.mcs.cysoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.mcs.cysoft.common.entity.UserRedisEntity;
import com.mcs.cysoft.common.util.RedisObjectSerializer;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
	
	// 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例  
//    @Autowired  
//    private RestTemplateBuilder builder;  
//  
//        // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例  
//    @Bean  
//    public RestTemplate restTemplate() {  
//        return builder.build();  
//    }  
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, UserRedisEntity> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, UserRedisEntity> template = new RedisTemplate<String, UserRedisEntity>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
