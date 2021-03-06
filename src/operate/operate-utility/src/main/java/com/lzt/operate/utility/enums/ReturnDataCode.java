package com.lzt.operate.utility.enums;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ReturnMessage;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 操作返回码
 *
 * @author luzhitao
 */
public enum ReturnDataCode {

	/**
	 * 未知
	 */
	Empty(0, "", false),

	/**
	 * 未知
	 */
	Unknown(-1, "未知", false),

	/**
	 * OK
	 */
	Ok(BaseResultData.CODE_ACCESS_SUCCESS, BaseResultData.MESSAGE_ACCESS_SUCCESS, true),

	/**
	 * 1XX错误
	 */
	HTTP_ERROR_100(100, "1XX错误", false),

	/**
	 * 3XX错误
	 */
	HTTP_ERROR_300(300, "3XX错误", false),

	/**
	 * 4XX错误
	 */
	HTTP_ERROR_400(400, "4XX错误", false),

	/**
	 * 5XX错误
	 */
	HTTP_ERROR_500(500, "5XX错误", false),

	/**
	 * 签名错误
	 */
	SIGN_ERROR(120, "签名错误", false),

	/**
	 * 访问超时
	 */
	TIME_OUT(130, "访问超时", false),

	/**
	 * 您已经在其他地方登录，请重新登录！
	 */
	KICK_OUT(300, "您已经在其他地方登录，请重新登录！", false),

	/**
	 * 参数解析失败
	 */
	BAD_REQUEST(407, "参数解析失败", false),

	/**
	 * 无效的授权码
	 */
	INVALID_TOKEN(401, "无效的授权码", false),

	/**
	 * 无效的密钥
	 */
	INVALID_CLIENTID(402, "无效的密钥", false),

	/**
	 * 访问地址不存在！
	 */
	REQUEST_NOT_FOUND(404, "访问地址不存在！", false),

	/**
	 * 不支持当前请求方法
	 */
	METHOD_NOT_ALLOWED(405, "不支持当前请求方法", false),

	/**
	 * 请求重复提交
	 */
	REPEAT_REQUEST_NOT_ALLOWED(406, "请求重复提交", false),

	/**
	 * 服务器运行异常
	 */
	SYSTEM_ERR(500, "服务器运行异常", false),

	/**
	 * 无可用数据
	 */
	ParamError(1001, "参数错误", false),

	/**
	 * 无可用数据
	 */
	NoData(1002, "无数据", false),

	/**
	 * 无可用数据
	 */
	NoChange(1003, "无操作反馈", false),

	/**
	 * 无可用数据
	 */
	DataError(1004, "数据错误", false),

	/**
	 * 需要登录
	 */
	Authentication_FAIL(2001, "Token无效", false),

	/**
	 * 无可用数据
	 */
	IgnoreHandle(2002, "忽略处理", false),

	/**
	 * 无可用数据
	 */
	PasswordNotMatch(3001, "密码不匹配", false),

	/**
	 * 无可用数据
	 */
	Exception(5001, "程序异常", false),

	/**
	 * 无可用数据
	 */
	NeedOverride(5002, "方法需要重载实现", false),

	/**
	 * 该用户不存在或密码错误
	 */
	NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误", false),

	/**
	 * 用户名或密码为空
	 */
	NOT_PARAM_USER_OR_ERROR_PWD(10006, "用户名或密码为空", false),

	/**
	 * 该用户已登录
	 */
	LOGINED_IN(10001, "该用户已登录", false),

	/**
	 * 该商家不存在
	 */
	NOT_EXIST_BUSINESS(10002, "该商家不存在", false),

	/**
	 * 您没有该权限
	 */
	UNAUTHORIZED_ERROR(10004, "您没有该权限", false),

	/**
	 * redis异常
	 */
	REDIS_ERROR(10006, "redis异常", false),

	/**
	 * redis连接异常
	 */
	REDIS_CONNECT_ERROR(10007, "redis连接异常", false),

	/**
	 * 请绑定手机号
	 */
	BIND_PHONE(10010, "请绑定手机号", false),

	/**
	 * 上传文件异常
	 */
	UPLOAD_ERROR(20000, "上传文件异常", false),

	/**
	 * 无效的验证码
	 */
	INVALID_CAPTCHA(30005, "无效的验证码", false),

	/**
	 * 该用户已被冻结
	 */
	USER_FROZEN(40000, "该用户已被冻结", false),

	/**
	 * 程序异常
	 */
	EXCEPTION_ERROR(50002, "程序异常", false),

	/**
	 * 密码错误
	 */
	PASSWORD_ERROR(50003, "密码错误", false);

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "returnDataCodeList";

	private final int code;
	private final String message;
	private final boolean success;

	ReturnDataCode(int code, String message, boolean success) {
		this.code = code;
		this.message = message;
		this.success = success;
	}

	public static Optional<ReturnDataCode> valueOfCode(@NonNull Integer code) {
		ReturnDataCode[] values = ReturnDataCode.values();

		for (ReturnDataCode d : values) {
			if (code.equals(d.getCode())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<ReturnDataCode> valuesToList() {
		return Arrays.asList(ReturnDataCode.values());
	}

	public static boolean existCode(@NonNull Integer flag) {
		Optional<ReturnDataCode> optional = valueOfCode(flag);

		return optional.isPresent();
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public ReturnMessage appendMessage(String... otherMessageList) {
		List<String> list = new ArrayList<>();

		list.add(this.message);
		list.addAll(Arrays.asList(otherMessageList));

		return this.toMessage(StringAssist.join(list));
	}

	public ReturnMessage toMessage() {
		return ReturnMessage.create(this.getCode(), this.getMessage(), this.getSuccess());
	}

	public ReturnMessage toMessage(boolean success) {
		return ReturnMessage.create(this.getCode(), this.getMessage(), success);
	}

	public ReturnMessage toMessage(String message) {
		return ReturnMessage.create(this.getCode(), message, this.getSuccess());
	}

	public ReturnMessage toMessage(int code) {
		return ReturnMessage.create(code, this.getMessage(), this.getSuccess());
	}

}
