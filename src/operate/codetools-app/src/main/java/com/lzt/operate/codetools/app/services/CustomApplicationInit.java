package com.lzt.operate.codetools.app.services;

import com.lzt.operate.codetools.app.assists.OperatorAssist;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.OperatorStatus;
import com.lzt.operate.codetools.common.enums.RoleUniversalStatus;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.common.utils.Constants;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.OperatorRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
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
import java.util.Optional;

/**
 * 自定义初始化,系统初始化后执行一些业务操作
 *
 * @author lzt
 */
@Service
@Slf4j
public class CustomApplicationInit extends BaseCustomApplicationInit {

	private OperatorAssist operatorAssist;

	@Autowired
	public CustomApplicationInit(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			OperatorServiceImpl operatorService,
			OperatorRoleServiceImpl operatorRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.operatorAssist = new OperatorAssist(
				customJsonWebTokenConfig,
				operatorService,
				operatorRoleService,
				roleUniversalService,
				roleCodeToolsService);
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
		RoleUniversalService roleUniversalService = operatorAssist.getRoleUniversalService();

		boolean exist = roleUniversalService.existSuper(Channel.CodeTools.getFlag());

		if (!exist) {
			RoleUniversal roleUniversal = new RoleUniversal();

			roleUniversal.setName(Constants.SUPER_ROLE_NAME);
			roleUniversal.setWhetherSuper(WhetherSuper.Yes.getValue());
			roleUniversal.setChannel(Channel.CodeTools.getFlag());
			roleUniversal.setChannelNote(Channel.CodeTools.getNote());
			roleUniversal.setStatus(RoleUniversalStatus.Enabled.getValue());
			roleUniversal.setStatusNote(RoleUniversalStatus.Enabled.getDescription());

			roleUniversalService.save(roleUniversal);
		}
	}

	/**
	 * 检测是否存在任意用户，不存在则创建默认账户
	 */
	private void checkExistAnyOperator() {
		OperatorService operatorService = operatorAssist.getOperatorService();

		boolean exist = operatorService.existAny(Channel.CodeTools.getFlag());

		if (!exist) {

			try {
				var operator = new Operator();

				operator.setUserName(Constants.DEFAULT_OPERATOR_USER_NAME);
				operator.setSlat(StringAssist.randomAlphanumeric(6)
											 .toLowerCase());
				operator.setPassword(Md5Assist.toMd5(Constants.DEFAULT_OPERATOR_PASSWORD, operator.getSlat()));
				operator.setChannel(Channel.CodeTools.getFlag());
				operator.setChannelNote(Channel.CodeTools.getNote());
				operator.setStatus(OperatorStatus.Enabled.getFlag());
				operator.setStatusNote(RoleUniversalStatus.Enabled.getDescription());

				operatorService.save(operator);

				Optional<RoleUniversal> optionalRoleUniversal = operatorAssist.getRoleUniversalService()
																			  .findSuper(Channel.CodeTools.getFlag());

				optionalRoleUniversal.ifPresent(roleUniversal -> this.operatorAssist.changeRoleUniversal(operator.getId(), roleUniversal));
			} catch (NoSuchAlgorithmException e) {
				log.error("创建默认账户失败", e);

				e.printStackTrace();
			}
		}
	}

}
