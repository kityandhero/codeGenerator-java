package com.lzt.operate.codetools.app.services;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.app.util.CommandUtil;
import com.lzt.operate.codetools.common.enums.AccountStatus;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.RoleUniversalStatus;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.dao.service.AccessWayService;
import com.lzt.operate.codetools.dao.service.AccountRoleService;
import com.lzt.operate.codetools.dao.service.AccountService;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import com.lzt.operate.codetools.dao.service.ErrorLogService;
import com.lzt.operate.codetools.dao.service.GeneralLogService;
import com.lzt.operate.codetools.dao.service.RoleCodeToolsService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.codetools.entities.CustomConfig;
import com.lzt.operate.codetools.entities.GeneralLog;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.custommessagequeue.custommessagequeue.accessway.AccessWayConsumer;
import com.lzt.operate.custommessagequeue.custommessagequeue.accessway.AccessWayQueueRunner;
import com.lzt.operate.custommessagequeue.custommessagequeue.errorlog.ErrorLogConsumer;
import com.lzt.operate.custommessagequeue.custommessagequeue.errorlog.ErrorLogQueueRunner;
import com.lzt.operate.custommessagequeue.custommessagequeue.generallog.GeneralLogConsumer;
import com.lzt.operate.custommessagequeue.custommessagequeue.generallog.GeneralLogProducer;
import com.lzt.operate.custommessagequeue.custommessagequeue.generallog.GeneralLogProducerFactory;
import com.lzt.operate.custommessagequeue.custommessagequeue.generallog.GeneralLogQueueRunner;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.OperatorCollection;
import com.lzt.operate.utility.general.Constants;
import com.lzt.operate.utility.secret.Md5Assist;
import com.lzt.operate.utility.services.bases.BaseCustomApplicationInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.ThreadFactory;

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

	private AccessWayService accessWayService;

	private ErrorLogService errorLogService;

	private GeneralLogService generalLogService;

	@Autowired
	public CustomApplicationInit(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountService accountService,
			AccountRoleService accountRoleService,
			RoleUniversalService roleUniversalService,
			RoleCodeToolsService roleCodeToolsService,
			CustomConfigService customConfigService,
			AccessWayService accessWayService,
			ErrorLogService errorLogService,
			GeneralLogService generalLogService) {
		this.accountAssist = new AccountAssist(
				customJsonWebTokenConfig,
				accountService,
				accountRoleService,
				roleUniversalService,
				roleCodeToolsService);

		this.customConfigService = customConfigService;
		this.accessWayService = accessWayService;
		this.errorLogService = errorLogService;
		this.generalLogService = generalLogService;
	}

	public CustomConfigService getCustomConfigService() {
		Optional<CustomConfigService> optional = Optional.ofNullable(this.customConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("CustomConfigService获取失败");
	}

	public AccessWayService getAccessWayService() {
		Optional<AccessWayService> optional = Optional.ofNullable(this.accessWayService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("AccessWayService获取失败");
	}

	public ErrorLogService getErrorLogService() {
		Optional<ErrorLogService> optional = Optional.ofNullable(this.errorLogService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ErrorLogService获取失败");
	}

	public GeneralLogService getGeneralLogService() {
		Optional<GeneralLogService> optional = Optional.ofNullable(this.generalLogService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("GeneralLogService获取失败");
	}

	@Override
	public void init() {
		this.checkSuperRoleCompleteness();
		this.checkExistAnyAccount();
		this.checkDataIntegrity();
		this.startAccessWayRunner();
		this.startErrorLogRunner();
		this.startGeneralLogRunner();
		this.openOperationPanel();

		GeneralLogProducer producer = GeneralLogProducerFactory.getInstance().getProducer();

		GeneralLog generalLog = new GeneralLog();

		generalLog.setMessage("codeTools App 启动");

		producer.push(generalLog);
	}

	/**
	 * 检测超级管理员角色的完备性
	 */
	private void checkSuperRoleCompleteness() {
		RoleUniversalService roleUniversalService = this.accountAssist.getRoleUniversalService();

		boolean exist = roleUniversalService.existSuper(Channel.CodeTools.getFlag());

		if (!exist) {
			RoleUniversal roleUniversal = new RoleUniversal();

			roleUniversal.setName(Constants.SUPER_ROLE_NAME);
			roleUniversal.setWhetherSuper(WhetherSuper.Yes.getValue());
			roleUniversal.setChannel(Channel.CodeTools);
			roleUniversal.setStatus(RoleUniversalStatus.Enabled, RoleUniversalStatus::getValue, RoleUniversalStatus::getName);
			roleUniversal.setCreateOperatorId(OperatorCollection.System.getId());
			roleUniversal.setUpdateOperatorId(OperatorCollection.System.getId());

			roleUniversalService.save(roleUniversal);
		}
	}

	/**
	 * 检测是否存在任意用户，不存在则创建默认账户
	 */
	private void checkExistAnyAccount() {
		AccountService accountService = this.accountAssist.getAccountService();

		boolean exist = accountService.existAny(Channel.CodeTools.getFlag());

		if (!exist) {

			try {
				Account account = new Account();

				account.setUserName(Constants.DEFAULT_OPERATOR_USER_NAME);
				account.setSlat(StringAssist.randomAlphanumeric(6)
											.toLowerCase());
				account.setPassword(Md5Assist.toMd5(Constants.DEFAULT_OPERATOR_PASSWORD, account.getSlat()));
				account.setChannel(Channel.CodeTools);
				account.setStatus(AccountStatus.Enabled, AccountStatus::getValue, AccountStatus::getName);
				account.setCreateOperatorId(OperatorCollection.System.getId());
				account.setUpdateOperatorId(OperatorCollection.System.getId());

				accountService.save(account);

				Optional<RoleUniversal> optionalRoleUniversal = this.accountAssist.getRoleUniversalService()
																				  .findSuper(Channel.CodeTools.getFlag());

				optionalRoleUniversal.ifPresent(roleUniversal -> this.accountAssist.changeRoleUniversal(account.getId(), roleUniversal));
			} catch (NoSuchAlgorithmException e) {
				CustomApplicationInit.log.error("创建默认账户失败", e);

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

		Optional<CustomConfig> result = this.getCustomConfigService().findOne(example);

		if (!result.isPresent()) {
			customConfig = new CustomConfig();

			customConfig.setName(needLogin);
			customConfig.setValue("");
			customConfig.setDescription("请设置是否需要登陆使用，如数据需要保密，请启用账户登陆");
			customConfig.setCreateOperatorId(OperatorCollection.System.getId());
			customConfig.setUpdateOperatorId(OperatorCollection.System.getId());

			this.getCustomConfigService().save(customConfig);
		} else {
			String value = customConfig.getValue();

			HashSet<String> set = new HashSet<>();

			set.add("");
			set.add("0");
			set.add("1");

			if (!set.contains(value)) {
				customConfig.setValue("");

				this.getCustomConfigService().save(customConfig);
			}
		}
	}

	/**
	 * startAccessWayRunner
	 */
	private void startAccessWayRunner() {
		AccessWayQueueRunner runner = new AccessWayQueueRunner(this.getAccessWayService(), new AccessWayConsumer());

		ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("accessWay").build();

		Thread t = factory.newThread(runner);

		t.start();
	}

	/**
	 * startErrorLogRunner
	 */
	private void startErrorLogRunner() {

		ErrorLogQueueRunner runner = new ErrorLogQueueRunner(this.getErrorLogService(), new ErrorLogConsumer());

		ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("errorLog").build();

		Thread t = factory.newThread(runner);

		t.start();
	}

	/**
	 * startErrorLogRunner
	 */
	private void startGeneralLogRunner() {
		GeneralLogQueueRunner runner = new GeneralLogQueueRunner(this.getGeneralLogService(), new GeneralLogConsumer());

		ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("generalLog").build();

		Thread t = factory.newThread(runner);

		t.start();
	}

	/**
	 * 打开操作面板
	 */
	private void openOperationPanel() {
		try {
			CommandUtil.browse(new URI("http://localhost:9090/index.html"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
