package com.lzt.operate.code.generator.app.components;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * @author luzhitao
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig extends CachingConfigurerSupport {

	private final CacheProperties cacheProperties;

	@Autowired
	CacheManagerConfig(CacheProperties cacheProperties) {
		this.cacheProperties = cacheProperties;
	}

	// @Bean
	// public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
	//
	// 	RedisTemplate<String, Object> template = new RedisTemplate<>();
	//
	// 	RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	//
	// 	Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	// 	ObjectMapper om = new ObjectMapper();
	// 	om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	// 	om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	// 	jackson2JsonRedisSerializer.setObjectMapper(om);
	//
	// 	template.setConnectionFactory(factory);
	// 	//key序列化方式
	// 	template.setKeySerializer(redisSerializer);
	// 	//value序列化
	// 	template.setValueSerializer(jackson2JsonRedisSerializer);
	// 	//value hashmap序列化
	// 	template.setHashValueSerializer(jackson2JsonRedisSerializer);
	//
	// 	return template;
	// }

	// @Primary
	// @Bean
	// public CacheManager cacheManager(RedisConnectionFactory factory) {
	// 	RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	// 	Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	//
	// 	//解决查询缓存转换异常的问题
	// 	ObjectMapper om = new ObjectMapper();
	// 	om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	// 	om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	// 	jackson2JsonRedisSerializer.setObjectMapper(om);
	//
	// 	// 配置序列化（解决乱码的问题）,过期时间30秒
	// 	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
	// 															.entryTtl(Duration.ofSeconds(30))
	// 															.serializeKeysWith(RedisSerializationContext.SerializationPair
	// 																	.fromSerializer(redisSerializer))
	// 															.serializeValuesWith(RedisSerializationContext.SerializationPair
	// 																	.fromSerializer(jackson2JsonRedisSerializer))
	// 															.disableCachingNullValues();
	//
	// 	RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
	// 													  .cacheDefaults(config)
	// 													  .build();
	// 	return cacheManager;
	// }

	// /**
	//  * 创建ehCacheCacheManager
	//  */
	// @Bean
	// public EhCacheCacheManager ehCacheCacheManager() {
	// 	Resource location = this.cacheProperties
	// 			.resolveConfigLocation(this.cacheProperties.getEhcache().getConfig());
	// 	return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(location));
	// }

	@Bean
	public CacheManager cacheManagerWithCacheLoading() {
		Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
													//cache的初始容量值
													.initialCapacity(100)
													//maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight(最大权重)不可以同时使用，
													.maximumSize(1000)
													//最后一次写入或者访问后过久过期
													.expireAfterAccess(500, TimeUnit.SECONDS)
													//创建或更新之后多久刷新,需要设置cacheLoader
													.refreshAfterWrite(10, TimeUnit.SECONDS);

		CaffeineCacheManager cacheManager = new CaffeineCacheManager();

		cacheManager.setCaffeine(caffeine);
		cacheManager.setCacheLoader(cacheLoader());
		cacheManager.setCacheNames(this.cacheProperties.getCacheNames());

		//是否允许值为空
		cacheManager.setAllowNullValues(false);

		return cacheManager;
	}

	/**
	 * 定义缓存,可直接使用 loadingCache.put("name", "test1");
	 *
	 * @return LoadingCache<String, Object>
	 */
	@Bean
	public LoadingCache<String, Object> expiryCache() {
		return Caffeine.newBuilder()
					   .initialCapacity(100)
					   .maximumSize(1000)
					   //缓存写入/删除监控
					   .writer(new CacheWriter<Object, Object>() {
						   /**
							* 此方法是同步阻塞的
							* @param key key
							* @param value value
							*/
						   @Override
						   public void write(@NonNull Object key, @NonNull Object value) {
							   System.out.println("--缓存写入--:key=" + key + ", value=" + value);
						   }

						   @Override
						   public void delete(@NonNull Object key, Object value, @NonNull RemovalCause cause) {
							   System.out.println("--缓存删除--:key=" + key);
						   }
					   })
					   //过期时间
					   .expireAfterAccess(1, TimeUnit.MINUTES)
					   //刷新时候调用
					   .build((String key) -> null);
	}

	@Primary
	@Bean(name = "caffeine")
	public CacheManager cacheManagerWithCaffeine() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();

		Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
													//cache的初始容量值
													.initialCapacity(100)
													//maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight(最大权重)不可以同时使用，
													.maximumSize(1000)
													//最后一次写入或者访问后过久过期
													.expireAfterAccess(500, TimeUnit.SECONDS)
													//创建或更新之后多久刷新,需要设置cacheLoader
													.refreshAfterWrite(10, TimeUnit.SECONDS);
		cacheManager.setCaffeine(caffeine);
		cacheManager.setCacheLoader(cacheLoader());
		cacheManager.setCacheNames(this.cacheProperties.getCacheNames());

		//是否允许值为空
		cacheManager.setAllowNullValues(false);

		return cacheManager;
	}

	@Bean(name = "caffeineSpec")
	public CacheManager cacheManagerWithCaffeineFromSpec() {
		CaffeineSpec spec = CaffeineSpec.parse(this.cacheProperties.getCaffeine().getSpec());

		Caffeine<Object, Object> caffeine = Caffeine.from(spec);

		CaffeineCacheManager cacheManager = new CaffeineCacheManager();

		cacheManager.setCaffeine(caffeine);
		cacheManager.setCacheLoader(cacheLoader());
		cacheManager.setCacheNames(this.cacheProperties.getCacheNames());

		//是否允许值为空
		cacheManager.setAllowNullValues(false);

		return cacheManager;
	}

	/**
	 * 必须要指定这个Bean，refreshAfterWrite配置属性才生效
	 *
	 * @return CacheLoader<Object, Object>
	 */
	@Bean
	public CacheLoader<Object, Object> cacheLoader() {
		return new CacheLoader<Object, Object>() {
			@Nullable
			@Override
			public Object load(@NonNull Object key) {
				return null;
			}

			/**
			 * 重写这个方法将oldValue值返回回去，进而刷新缓存
			 * @param key key
			 * @param oldValue oldValue
			 * @return Object
			 */
			@Override
			public Object reload(@NonNull Object key, @NonNull Object oldValue) {
				System.out.println("--refresh--:" + key);
				return oldValue;
			}
		};
	}

	// /**
	//  * cacheManager名字
	//  */
	// public interface CacheManagerNames {
	//
	// 	/**
	// 	 * redis
	// 	 */
	// 	String REDIS_CACHE_MANAGER = "redisCacheManager";
	//
	// 	/**
	// 	 * ehCache
	// 	 */
	// 	String EHCACHE_CACHE_MAANGER = "ehCacheCacheManager";
	//
	// }

}
