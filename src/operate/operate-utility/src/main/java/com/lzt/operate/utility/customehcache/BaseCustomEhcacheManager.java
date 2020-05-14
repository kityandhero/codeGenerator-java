package com.lzt.operate.utility.customehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author luzhitao
 */
public abstract class BaseCustomEhcacheManager implements ICustomEhcacheManager {

	protected Cache springCache;

	private final EhCacheCacheManager ehCacheCacheManager;

	protected BaseCustomEhcacheManager(EhCacheCacheManager ehCacheCacheManager) {
		this.ehCacheCacheManager = ehCacheCacheManager;
	}

	public EhCacheCacheManager getEhCacheCacheManager() {
		return ehCacheCacheManager;
	}

	@Override
	public void clear() {
		springCache.clear();
	}

	@Override
	public abstract String getCacheKey();

	@Override
	public Object get(Object key) {
		ValueWrapper valueWrapper = springCache.get(key);
		if (valueWrapper != null) {
			return Objects.requireNonNull(springCache.get(key)).get();
		} else {
			return null;
		}
	}

	@Override
	public void put(Object key, Object value) {
		springCache.put(key, value);
	}

	@Override
	public void refresh() {
		clear();
		initCacheData();
	}

	@Override
	public void evict(Object key) {
		springCache.evict(key);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void put(Map map) {
		Set<Map.Entry> set = map.entrySet();

		for (Map.Entry entry : set) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 执行先后顺序: Constructor > @PostConstruct > InitializingBean > init-method
	 */
	@Override
	@PostConstruct
	public void initCache() {
		springCache = getEhCacheCacheManager().getCache(getCacheKey());

		if (springCache == null) {
			throw new RuntimeException("请检查ehcache.xml 中是否配置 name=" + getCacheKey());
		}

		initCacheData();
	}

}
