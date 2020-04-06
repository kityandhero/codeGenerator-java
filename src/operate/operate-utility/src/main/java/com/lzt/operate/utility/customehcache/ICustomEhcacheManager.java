package com.lzt.operate.utility.customehcache;

import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
public interface ICustomEhcacheManager {

	/**
	 * 启动时，从数据库获取所有缓存数据，放入缓存中
	 */
	default void initCacheData() {
	}

	/**
	 * 初始化缓存管理器
	 */
	void initCache();

	String getCacheKey();

	/**
	 * 往本缓中存入一条记录，如果key已存在，则覆盖并返回原值
	 * 参考MAP的put方法
	 *
	 * @param key   key
	 * @param value value
	 */
	void put(Object key, Object value);

	/**
	 * getAsString
	 *
	 * @param key key
	 * @return String
	 */
	default String getAsString(Object key) {
		Object v = get(key);

		if (Optional.ofNullable(v).isPresent()) {
			return v.toString();
		}

		return "";
	}

	/**
	 * 根据key获取缓存记录数据
	 *
	 * @param key key
	 * @return Object
	 */
	Object get(Object key);

	/**
	 * 删除键为KEY的缓存记录
	 *
	 * @param key key
	 */
	void evict(Object key);

	/**
	 * 清除该缓存实例的所有缓存记录
	 */
	void clear();

	/**
	 * 重新刷新该缓存记录，通常是clear()和initCacheFormSql()的组合
	 */
	void refresh();

	/**
	 * 将Map映射到缓存中
	 *
	 * @param map map
	 */
	void put(Map<String, Object> map);

}
