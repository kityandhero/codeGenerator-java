package com.lzt.operate.code.generator.app.ehcache;

import com.lzt.operate.code.generator.app.components.CacheManagerConfig;
import com.lzt.operate.utility.customehcache.BaseCustomEhcacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luzhitao
 */
@Component
public class CustomEhcacheManager extends BaseCustomEhcacheManager {

	protected CacheManagerConfig springCacheManager;

	@Autowired
	public CustomEhcacheManager(CacheManagerConfig springCacheManager) {
		super(springCacheManager.ehCacheCacheManager());
	}

	public CacheManagerConfig getSpringCacheManager() {
		return springCacheManager;
	}

	@Override
	public String getCacheKey() {
		return "DICT_CACHE";
	}

}
