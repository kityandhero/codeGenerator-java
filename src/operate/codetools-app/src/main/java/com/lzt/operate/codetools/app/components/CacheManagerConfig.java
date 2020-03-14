package com.lzt.operate.codetools.app.components;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author luzhitao
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig extends CachingConfigurerSupport {

	private final CacheProperties cacheProperties;

	CacheManagerConfig(CacheProperties cacheProperties) {
		this.cacheProperties = cacheProperties;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<String, Object> template = new RedisTemplate<>();

		RedisSerializer<String> redisSerializer = new StringRedisSerializer();

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		template.setConnectionFactory(factory);
		//key序列化方式
		template.setKeySerializer(redisSerializer);
		//value序列化
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//value hashmap序列化
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		return template;
	}

	@Primary
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		//解决查询缓存转换异常的问题
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// 配置序列化（解决乱码的问题）,过期时间30秒
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
																.entryTtl(Duration.ofSeconds(30))
																.serializeKeysWith(RedisSerializationContext.SerializationPair
																		.fromSerializer(redisSerializer))
																.serializeValuesWith(RedisSerializationContext.SerializationPair
																		.fromSerializer(jackson2JsonRedisSerializer))
																.disableCachingNullValues();

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
														  .cacheDefaults(config)
														  .build();
		return cacheManager;
	}

	/**
	 * 创建ehCacheCacheManager
	 */
	@Bean
	public EhCacheCacheManager ehCacheCacheManager() {
		Resource location = this.cacheProperties
				.resolveConfigLocation(this.cacheProperties.getEhcache().getConfig());
		return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(location));
	}

	/**
	 * h
	 * cacheManager名字
	 */
	public interface CacheManagerNames {

		/**
		 * redis
		 */
		String REDIS_CACHE_MANAGER = "redisCacheManager";

		/**
		 * ehCache
		 */
		String EHCACHE_CACHE_MAANGER = "ehCacheCacheManager";

	}

}
