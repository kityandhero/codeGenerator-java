package com.lzt.operate.utility.assists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luzhitao
 */
public class ValidatorAssist {
	private static final Logger log = LoggerFactory.getLogger(ValidatorAssist.class);

	/**
	 * 是否boolean值范围
	 */
	private static final Set<String> TRUE_SET = new HashSet() {
		private static final long serialVersionUID = -4172497703574134267L;

		{
			add("true");
			add("是");
			add("y");
			add("yes");
			add("1");
		}
	};

	private static final Set<String> FALSE_SET = new HashSet() {
		private static final long serialVersionUID = 4081063522698495608L;

		{
			add("false");
			add("否");
			add("n");
			add("no");
			add("0");
		}
	};

	/***
	 * 对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			return isEmpty((String) obj);
		} else if (obj instanceof Collection) {
			return isEmpty((Collection) obj);
		} else if (obj instanceof Map) {
			return isEmpty((Map) obj);
		} else if (obj instanceof String[]) {
			return isEmpty((String[]) obj);
		} else {
			return obj == null;
		}
	}

	/***
	 * 集合为空
	 * @param s s
	 * @return
	 */
	public static <T> boolean isEmpty(String s) {
		return StringAssist.isNullOrEmpty(s);
	}

	/***
	 * 集合为空
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> list) {
		return list == null || list.isEmpty();
	}

	/***
	 * Map为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Map obj) {
		return obj == null || obj.isEmpty();
	}

	/***
	 * 对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean notEmpty(Object obj) {
		if (obj instanceof String) {
			return notEmpty((String) obj);
		} else if (obj instanceof Collection) {
			return notEmpty((Collection) obj);
		} else if (obj instanceof Map) {
			return notEmpty((Map) obj);
		} else if (obj instanceof String[]) {
			return notEmpty((String[]) obj);
		} else {
			return obj != null;
		}
	}

	/***
	 * 集合不为空
	 * @param s s
	 * @return boolean
	 */
	public static boolean notEmpty(String s) {
		return !StringAssist.isNullOrEmpty(s);
	}

	/***
	 * 集合不为空
	 * @param list
	 * @return
	 */
	public static <T> boolean notEmpty(Collection<T> list) {
		return list != null && !list.isEmpty();
	}

	/***
	 * 对象不为空且不为0
	 * @param longObj
	 * @return
	 */
	public static boolean notEmptyOrZero(Long longObj) {
		return longObj != null && longObj.longValue() != 0;
	}

	/***
	 * 对象不为空且不为0
	 * @param intObj
	 * @return
	 */
	public static boolean notEmptyOrZero(Integer intObj) {
		return intObj != null && intObj.intValue() != 0;
	}

	/***
	 * Map为空
	 * @param obj
	 * @return
	 */
	public static boolean notEmpty(Map obj) {
		return obj != null && !obj.isEmpty();
	}

	/**
	 * 判断是否为数字（允许小数点）
	 *
	 * @param str
	 * @return true Or false
	 */
	public static boolean isNumber(String str) {
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return str.matches(regex);
	}

	/**
	 * 判断是否为正确的邮件格式
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	}

	/**
	 * 判断字符串是否为电话号码
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhone(String str) {
		if (isEmpty(str)) {
			return false;
		}
		boolean valid = str.matches("^1\\d{10}$");
		if (!valid) {
			valid = str.matches("^[0|4]\\d{2,3}-?\\d{7,8}$");
		}
		return valid;
	}

	/**
	 * 是否为boolean类型
	 *
	 * @param value
	 * @return
	 */
	public static boolean isValidBoolean(String value) {
		if (value == null) {
			return false;
		}
		value = StringAssist.trim(value).toLowerCase();
		return TRUE_SET.contains(value) || FALSE_SET.contains(value);
	}

	/**
	 * 转换为boolean类型, 并判定是否为true
	 *
	 * @param s 源字符串
	 * @return 转换结果
	 */
	public static boolean isTrue(String s) {
		if (s == null) {
			return false;
		}

		s = StringAssist.trim(s).toLowerCase();

		return TRUE_SET.contains(s);
	}

	/**
	 * 根据指定规则校验字符串的值是否合法
	 *
	 * @param value
	 * @param validation
	 * @return
	 */
	public static String validate(String value, String validation) {
		if (isEmpty(validation)) {
			return null;
		}
		List<String> errorMsgList = new ArrayList<>();
		String[] rules = validation.split(",");
		for (String rule : rules) {
			if ("NotNull".equalsIgnoreCase(rule)) {
				if (isEmpty(value)) {
					errorMsgList.add("不能为空");
				}
			} else if ("Number".equalsIgnoreCase(rule)) {
				if (!isNumber(value)) {
					errorMsgList.add("非数字格式");
				}
			} else if ("Boolean".equalsIgnoreCase(rule)) {
				if (!isValidBoolean(value)) {
					errorMsgList.add("非Boolean格式");
				}
			} else if ("Date".equalsIgnoreCase(rule)) {
				if (ConvertAssist.stringToFuzzyDate(value) == null) {
					errorMsgList.add("非日期格式");
				}
			} else if (rule.toLowerCase().startsWith("length")) {
				String range = rule.substring(rule.indexOf("(") + 1, rule.lastIndexOf(")"));
				if (range.contains("-")) {
					String[] arr = range.split("-");
					if (notEmpty(arr[0])) {
						if (ValidatorAssist.isEmpty(value) || value.length() < Integer.parseInt(arr[0])) {
							errorMsgList.add("长度少于最小限制数: " + arr[0]);
						}
					}
					if (notEmpty(arr[1])) {
						if (ValidatorAssist.notEmpty(value)) {
							if (value.length() > Integer.parseInt(arr[1])) {
								errorMsgList.add("长度超出最大限制数: " + arr[1]);
							}
						}
					}
				} else {
					if (ValidatorAssist.isEmpty(value) || !(value.length() == Integer.parseInt(range))) {
						errorMsgList.add("长度限制: " + range + "位");
					}
				}
			} else if ("Email".equalsIgnoreCase(rule)) {
				if (!isEmail(value)) {
					errorMsgList.add("非Email格式");
				}
			} else if ("Phone".equalsIgnoreCase(rule)) {
				if (!isPhone(value)) {
					errorMsgList.add("非电话号码格式");
				}
			} else {
				//TODO 无法识别的格式
			}
		}
		// 返回校验不通过的结果
		if (errorMsgList.isEmpty()) {
			return null;
		} else {
			return StringAssist.join(errorMsgList);
		}
	}

	/***
	 * 判定两个对象是否不同类型或不同值
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean notEquals(Object source, Object target) {
		return !equals(source, target);
	}

	/***
	 * 判定两个对象是否类型相同值相等
	 * @param source
	 * @param target
	 * @return
	 */
	public static <T> boolean equals(T source, T target) {
		if (source == null && target == null) {
			return true;
		} else if (source == null || target == null) {
			return false;
		}
		// 不为空，调用equals比较
		else if (source instanceof Comparable) {
			return (source).equals(target);
		} else if (source instanceof Collection) {
			Collection sourceList = (Collection) source, targetList = (Collection) target;

			// size不等
			if (sourceList.size() != targetList.size()) {
				return false;
			}
			for (Object obj : sourceList) {
				if (!targetList.contains(obj)) {
					return false;
				}
			}
			for (Object obj : targetList) {
				if (!sourceList.contains(obj)) {
					return false;
				}
			}
			return true;
		} else {
			log.warn("暂未实现类型 " + source.getClass().getSimpleName() + "-" + target.getClass().getSimpleName() + " 的比对！");
			return false;
		}
	}

	/**
	 * 解析所有的验证错误信息，转换为JSON
	 *
	 * @param result
	 * @return
	 */
	public static String getBindingError(BindingResult result) {
		if (result == null || !result.hasErrors()) {
			return null;
		}

		List<ObjectError> errors = result.getAllErrors();
		List<String> allErrors = new ArrayList<>(errors.size());

		for (ObjectError error : errors) {
			String message = error.getDefaultMessage();

			if (!StringAssist.isNullOrEmpty(message)) {
				allErrors.add(message.replaceAll("\"", "'"));
			}

		}
		return StringAssist.join(allErrors);
	}
}
