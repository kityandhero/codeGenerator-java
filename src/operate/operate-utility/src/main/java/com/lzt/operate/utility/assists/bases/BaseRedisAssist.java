package com.lzt.operate.utility.assists.bases;

import com.lzt.operate.utility.general.ConstantCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author luzhitao
 */
public abstract class BaseRedisAssist {

	private final RedisTemplate<String, Object> redisTemplate;

	protected BaseRedisAssist(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		if (Optional.ofNullable(redisTemplate).isPresent()) {
			return redisTemplate;
		}

		throw new RuntimeException("RedisTemplate<String, Object>不存在");
	}

	/**
	 * 指定缓存失效时间
	 *
	 * @param key  键
	 * @param time 时间(秒)
	 * @return boolean
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				getRedisTemplate().expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据key 获取过期时间
	 *
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public Optional<Long> getExpire(@NotNull String key) {
		Long expireSecond = getRedisTemplate().getExpire(key, TimeUnit.SECONDS);

		return Optional.of(expireSecond);
	}

	/**
	 * 判断key是否存在
	 *
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return getRedisTemplate().hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除缓存
	 *
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				getRedisTemplate().delete(key[0]);
			} else {
				getRedisTemplate().delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	// ============================String=============================

	/**
	 * 普通缓存获取
	 *
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		return key == null ? null : getRedisTemplate().opsForValue().get(key);
	}

	/**
	 * 普通缓存放入
	 *
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean set(String key, Object value) {
		try {
			getRedisTemplate().opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				getRedisTemplate().opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 递增
	 *
	 * @param key   键
	 * @param delta 要增加几(大于0)
	 */
	public Long increase(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return getRedisTemplate().opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 *
	 * @param key   键
	 * @param delta 要减少几(小于0)
	 * @return long
	 */
	public Long decrease(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return getRedisTemplate().opsForValue().increment(key, -delta);
	}

	/**
	 * HashGet
	 *
	 * @param key  键 不能为 null
	 * @param item 项 不能为 null
	 * @return 值
	 */
	public Object hashGet(String key, String item) {
		return getRedisTemplate().opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Map<Object, Object> mapGet(String key) {
		return getRedisTemplate().opsForHash().entries(key);
	}

	/**
	 * HashSet
	 *
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public boolean mapSet(String key, Map<String, Object> map) {
		try {
			getRedisTemplate().opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * HashSet 并设置时间
	 *
	 * @param key  键
	 * @param map  对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
	 */
	public boolean mapSet(String key, Map<String, Object> map, long time) {
		try {
			getRedisTemplate().opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @return true 成功 false失败
	 */
	public boolean hashSet(String key, String item, Object value) {
		try {
			getRedisTemplate().opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
	 */
	public boolean hashSet(String key, String item, Object value, long time) {
		try {
			getRedisTemplate().opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除hash表中的值
	 *
	 * @param key  键 不能为null
	 * @param item 项 可以使多个 不能为 null
	 */
	public void hashDelete(String key, Object... item) {
		getRedisTemplate().opsForHash().delete(key, item);
	}

	/**
	 * 判断hash表中是否有该项的值
	 *
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return true 存在 false 不存在
	 */
	public boolean hashHasKey(String key, String item) {
		return getRedisTemplate().opsForHash().hasKey(key, item);
	}

	/**
	 * hash递增 如果不存在,就会创建一个 并把新增后的值返回
	 *
	 * @param key  键
	 * @param item 项
	 * @param by   要增加几(大于0)
	 * @return double
	 */
	public double hashIncrease(String key, String item, double by) {
		return getRedisTemplate().opsForHash().increment(key, item, by);
	}

	/**
	 * hash递减
	 *
	 * @param key  键
	 * @param item 项
	 * @param by   要减少记(小于0)
	 * @return
	 */
	public double hashDecrease(String key, String item, double by) {
		return getRedisTemplate().opsForHash().increment(key, item, -by);
	}

	/**
	 * 根据key获取Set中的所有值
	 *
	 * @param key 键
	 * @return Set<Object>
	 */
	public Set<Object> setMembers(String key) {
		try {
			return getRedisTemplate().opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据value从一个set中查询,是否存在
	 *
	 * @param key   键
	 * @param value 值
	 * @return true 存在  false 不存在
	 */
	public boolean setIsMember(String key, Object value) {
		try {
			return getRedisTemplate().opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将数据放入set缓存
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long setAdd(String key, Object... values) {
		try {
			return getRedisTemplate().opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将set数据放入缓存
	 *
	 * @param key    键
	 * @param time   时间(秒)
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public Long setAndWithTime(String key, long time, Object... values) {
		try {
			Long count = getRedisTemplate().opsForSet().add(key, values);

			if (time > 0) {
				expire(key, time);
			}

			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantCollection.ZERO_LONG;
		}
	}

	/**
	 * 获取set缓存的长度
	 *
	 * @param key 键
	 * @return long
	 */
	public long setGetSize(String key) {
		try {
			return getRedisTemplate().opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 移除值为value的
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 移除的个数
	 */
	public long setRemove(String key, Object... values) {
		try {
			return getRedisTemplate().opsForSet().remove(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取list缓存的内容
	 *
	 * @param key   键
	 * @param start 开始
	 * @param end   结束 0 到 -1代表所有值
	 * @return List<Object>
	 */
	public List<Object> listRange(String key, long start, long end) {
		try {
			return getRedisTemplate().opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取list缓存的长度
	 *
	 * @param key 键
	 * @return long
	 */
	public long listGetSize(String key) {
		try {
			return getRedisTemplate().opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 *
	 * @param key   键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return Object
	 */
	public Object listGetIndex(String key, long index) {
		try {
			return getRedisTemplate().opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将list放入缓存
	 *
	 * @param key   键
	 * @param value 值
	 * @return boolean
	 */
	public boolean listRightPush(String key, Object value) {
		try {
			getRedisTemplate().opsForList().rightPush(key, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return boolean
	 */
	public boolean listRightPush(String key, Object value, long time) {
		try {
			getRedisTemplate().opsForList().rightPush(key, value);

			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 *
	 * @param key   键
	 * @param value 值
	 * @return boolean
	 */
	public boolean listRightPush(String key, List<Object> value) {
		try {
			getRedisTemplate().opsForList().rightPushAll(key, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return boolean
	 */
	public boolean listRightPush(String key, List<Object> value, long time) {
		try {
			getRedisTemplate().opsForList().rightPushAll(key, value);

			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据索引修改list中的某条数据
	 *
	 * @param key   键
	 * @param index 索引
	 * @param value 值
	 * @return boolean
	 */
	public boolean listSet(String key, long index, Object value) {
		try {
			getRedisTemplate().opsForList().set(key, index, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移除N个值为value
	 *
	 * @param key   键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 */
	public long listRemove(String key, long count, Object value) {
		try {
			return getRedisTemplate().opsForList().remove(key, count, value);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
