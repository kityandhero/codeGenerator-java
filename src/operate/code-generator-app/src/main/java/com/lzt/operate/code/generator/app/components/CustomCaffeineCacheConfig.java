package com.lzt.operate.code.generator.app.components;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.cache.CacheManager;
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
public class CustomCaffeineCacheConfig {

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
	@Bean("caffeineCacheManager")
	public CacheManager caffeineCacheManager() {
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

}
