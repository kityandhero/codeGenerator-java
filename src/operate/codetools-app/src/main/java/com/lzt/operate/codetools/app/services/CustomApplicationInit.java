package com.lzt.operate.codetools.app.services;

import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.OperatorStatus;
import com.lzt.operate.codetools.common.enums.RoleUniversalStatus;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.common.utils.Constants;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.Operator;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.secret.Md5Assist;
import com.lzt.operate.utility.services.bases.BaseCustomApplicationInit;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * 自定义初始化,系统初始化后执行一些业务操作
 *
 * @author lzt
 */
@Service
@Slf4j
public class CustomApplicationInit extends BaseCustomApplicationInit {

	private OperatorServiceImpl operatorServiceImpl;

	private final RoleUniversalServiceImpl roleUniversalServiceImpl;

	@Autowired
	public CustomApplicationInit(OperatorServiceImpl operatorServiceImpl, RoleUniversalServiceImpl roleUniversalServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
		this.roleUniversalServiceImpl = roleUniversalServiceImpl;
	}

	@Override
	public void init() {
		this.checkSuperRoleCompleteness();
		this.checkExistAnyOperator();
	}

	/**
	 * 检测超级管理员角色的完备性
	 */
	private void checkSuperRoleCompleteness() {
		boolean exist = this.roleUniversalServiceImpl.existSuper(Channel.CodeTools.getFlag());

		if (!exist) {
			RoleUniversal roleUniversal = new RoleUniversal();

			roleUniversal.setName(Constants.SUPER_ROLE_NAME);
			roleUniversal.setWhetherSuper(WhetherSuper.Yes.getValue());
			roleUniversal.setChannel(Channel.CodeTools.getFlag());
			roleUniversal.setChannelNote(Channel.CodeTools.getNote());
			roleUniversal.setStatus(RoleUniversalStatus.Enabled.getValue());
			roleUniversal.setStatusNote(RoleUniversalStatus.Enabled.getDescription());

			this.roleUniversalServiceImpl.save(roleUniversal);
		}
	}

	/**
	 * 检测是否存在任意用户，不存在则创建默认账户
	 */
	private void checkExistAnyOperator() {
		boolean exist = this.operatorServiceImpl.existAny(Channel.CodeTools.getFlag());

		if (!exist) {

			try {
				var operator = new Operator();

				operator.setUserName(Constants.DEFAULT_OPERATOR_USER_NAME);
				operator.setSlat(StringAssist.randomAlphanumeric(6)
											 .toLowerCase());
				operator.setPassword(Md5Assist.toMd5(Constants.DEFAULT_OPERATOR_PASSWORD, operator.getSlat()));
				operator.setChannel(Channel.CodeTools.getFlag());
				operator.setChannelNote(Channel.CodeTools.getNote());
				operator.setStatus(OperatorStatus.Enabled.getValue());
				operator.setStatusNote(RoleUniversalStatus.Enabled.getDescription());

				this.operatorServiceImpl.save(operator);
			} catch (NoSuchAlgorithmException e) {
				log.error("创建默认账户失败", e);

				e.printStackTrace();
			}
		}
	}

}
