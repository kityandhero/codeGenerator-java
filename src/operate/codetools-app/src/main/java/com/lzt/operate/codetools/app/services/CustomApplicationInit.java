package com.lzt.operate.codetools.app.services;

import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.AccountStatus;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.RoleUniversalStatus;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.common.utils.Constants;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.CustomConfigServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.codetools.entities.CustomConfig;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.OperatorCollection;
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

	private AccountAssist accountAssist;

	private CustomConfigService customConfigService;

	@Autowired
	public CustomApplicationInit(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService,
			CustomConfigServiceImpl customConfigService) {
		this.accountAssist = new AccountAssist(
				customJsonWebTokenConfig,
				accountService,
				accountRoleService,
				roleUniversalService,
				roleCodeToolsService);

		this.customConfigService = customConfigService;
	}

	@Override
	public void init() {
		this.checkSuperRoleCompleteness();
		this.checkExistAnyAccount();
		this.checkDataIntegrity();
	}

	/**
	 * 检测超级管理员角色的完备性
	 */
	private void checkSuperRoleCompleteness() {
		RoleUniversalService roleUniversalService = accountAssist.getRoleUniversalService();

		boolean exist = roleUniversalService.existSuper(Channel.CodeTools.getFlag());

		if (!exist) {
			RoleUniversal roleUniversal = new RoleUniversal();

			roleUniversal.setName(Constants.SUPER_ROLE_NAME);
			roleUniversal.setWhetherSuper(WhetherSuper.Yes.getValue());
			roleUniversal.setChannel(Channel.CodeTools.getFlag());
			roleUniversal.setChannelNote(Channel.CodeTools.getNote());
			roleUniversal.setStatus(RoleUniversalStatus.Enabled.getValue());
			roleUniversal.setStatusNote(RoleUniversalStatus.Enabled.getDescription());
			roleUniversal.setCreateOperatorId(OperatorCollection.System.getId());
			roleUniversal.setUpdateOperatorId(OperatorCollection.System.getId());

			roleUniversalService.save(roleUniversal);
		}
	}

	/**
	 * 检测是否存在任意用户，不存在则创建默认账户
	 */
	private void checkExistAnyAccount() {
		AccountService accountService = accountAssist.getAccountService();

		boolean exist = accountService.existAny(Channel.CodeTools.getFlag());

		if (!exist) {

			try {
				var account = new Account();

				account.setUserName(Constants.DEFAULT_OPERATOR_USER_NAME);
				account.setSlat(StringAssist.randomAlphanumeric(6)
											.toLowerCase());
				account.setPassword(Md5Assist.toMd5(Constants.DEFAULT_OPERATOR_PASSWORD, account.getSlat()));
				account.setChannel(Channel.CodeTools.getFlag());
				account.setChannelNote(Channel.CodeTools.getNote());
				account.setStatus(AccountStatus.Enabled.getFlag());
				account.setStatusNote(RoleUniversalStatus.Enabled.getDescription());
				account.setCreateOperatorId(OperatorCollection.System.getId());
				account.setUpdateOperatorId(OperatorCollection.System.getId());

				accountService.save(account);

				Optional<RoleUniversal> optionalRoleUniversal = accountAssist.getRoleUniversalService()
																			 .findSuper(Channel.CodeTools.getFlag());

				optionalRoleUniversal.ifPresent(roleUniversal -> this.accountAssist.changeRoleUniversal(account.getId(), roleUniversal));
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

		Optional<CustomConfig> result = this.customConfigService.findOne(example);

		if (!result.isPresent()) {
			customConfig = new CustomConfig();

			customConfig.setName(needLogin);
			customConfig.setValue("");
			customConfig.setDescription("请设置是否需要登陆使用，如数据需要保密，请启用账户登陆");
			customConfig.setCreateOperatorId(OperatorCollection.System.getId());
			customConfig.setUpdateOperatorId(OperatorCollection.System.getId());

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
