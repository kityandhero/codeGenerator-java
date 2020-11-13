package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.common.utils.CustomConstants;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.dao.repositories.AccountRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface AccountService extends BaseService<AccountRepository, Account> {

	/**
	 * 通过登录名获取信息
	 *
	 * @param userName 登录名
	 * @return Optional<Operator>
	 */
	Optional<Account> findByUserName(String userName);

	/**
	 * 是否存在任意用户
	 *
	 * @param channel channel
	 * @return boolean
	 */
	boolean existAny(int channel);

	/**
	 * 是否有效
	 *
	 * @param id id
	 * @return boolean
	 */
	boolean existEffective(long id);

	/**
	 * 校验用户名是否符合规则
	 *
	 * @param userName userName
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult verifyUserName(String userName) {
		String v = Optional.ofNullable(userName).orElse("");

		if (StringAssist.isNullOrEmpty(v) || v.length() <= CustomConstants.ACCOUNT_PASSWORD_MIN_LENGTH || v
				.length() > CustomConstants.ACCOUNT_USER_NAME_MAX_LENGTH) {
			return new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage(
					StringAssist.merge(
							GlobalString.ACCOUNT_USERNAME,
							"账户名不能为空，长度应在",
							CustomConstants.ACCOUNT_USER_NAME_MIN_LENGTH.toString(),
							"~",
							CustomConstants.ACCOUNT_USER_NAME_MAX_LENGTH.toString(),
							"之间")));
		}

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

	/**
	 * 校验用户密码是否符合规则
	 *
	 * @param password password
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult verifyPassword(String password) {
		return verifyPassword(password, "", false);
	}

	/**
	 * 校验用户密码是否符合规则
	 *
	 * @param password    password
	 * @param rePassword  rePassword
	 * @param bothCompare bothCompare
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult verifyPassword(String password, String rePassword, boolean bothCompare) {
		String v = Optional.ofNullable(password).orElse("");

		if (StringAssist.isNullOrEmpty(v) || v.length() <= CustomConstants.ACCOUNT_PASSWORD_MIN_LENGTH || v
				.length() > CustomConstants.ACCOUNT_USER_NAME_MAX_LENGTH) {
			return new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage(
					StringAssist.merge(
							GlobalString.ACCOUNT_PASSWORD,
							"密码不能为空，长度应在",
							CustomConstants.ACCOUNT_USER_NAME_MIN_LENGTH.toString(),
							"~",
							CustomConstants.ACCOUNT_USER_NAME_MAX_LENGTH.toString(),
							"之间")));
		}

		if (bothCompare) {
			String cv = Optional.ofNullable(rePassword).orElse("");

			if (StringAssist.isNullOrEmpty(cv)) {
				return new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage(StringAssist.merge(GlobalString.RE_PASSWORD, "确认密码不能为空")));
			}

			if (!v.equals(cv)) {
				return new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage("密码与确认密码不一致"));
			}
		}

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

}
