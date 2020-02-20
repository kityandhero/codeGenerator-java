package com.lzt.operate.codetools.app.services;

import com.lzt.operate.codetools.app.assists.OperatorAssist;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.OperatorStatus;
import com.lzt.operate.codetools.common.enums.RoleUniversalStatus;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.common.utils.Constants;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.CustomConfigServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.OperatorRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.CustomConfig;
import com.lzt.operate.codetools.entities.Operator;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.secret.Md5Assist;
import com.lzt.operate.utility.services.bases.BaseCustomApplicationInit;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
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

	private CustomConfigService customConfigService;

	@Autowired
	public CustomApplicationInit(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			OperatorServiceImpl operatorService,
			OperatorRoleServiceImpl operatorRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService,
			CustomConfigServiceImpl customConfigService) {
		this.operatorAssist = new OperatorAssist(
				customJsonWebTokenConfig,
				operatorService,
				operatorRoleService,
				roleUniversalService,
				roleCodeToolsService);

		this.customConfigService = customConfigService;
	}

	@Override
	public void init() {
		this.checkSuperRoleCompleteness();
		this.checkExistAnyOperator();
		this.checkDataIntegrity();
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

	/**
	 * 检测配饰数据的完整性
	 */
	private void checkDataIntegrity() {
		this.checkLoginConfig();
	}

	/**
	 * 检测时候启用账户登陆设置
	 */
	private void checkLoginConfig() {
		final String needLogin = "needLogin";

		CustomConfig customConfig = new CustomConfig();
		customConfig.setName(needLogin);

		ExampleMatcher matcher = ExampleMatcher.matching()
											   .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
											   .withIgnorePaths("createTime", "value", "description");

		Example<CustomConfig> example = Example.of(customConfig, matcher);

		ExecutiveResult<CustomConfig> result = this.customConfigService.findOne(example);

		if (!result.getSuccess()) {
			customConfig = new CustomConfig();

			customConfig.setName(needLogin);
			customConfig.setValue("");
			customConfig.setDescription("请设置是否需要登陆使用，如数据需要保密，请启用账户登陆");

			this.customConfigService.save(customConfig);
		} else {
			var value = customConfig.getValue();

			var set = new HashSet<String>();

			set.add("");
			set.add("0");
			set.add("1");

			if (!set.contains(value)) {
				customConfig.setValue("");

				this.customConfigService.save(customConfig);
			}
		}
	}

}
