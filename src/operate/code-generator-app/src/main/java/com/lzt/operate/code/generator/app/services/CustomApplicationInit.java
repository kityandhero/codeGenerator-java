package com.lzt.operate.code.generator.app.services;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lzt.operate.code.generator.app.assists.AccountAssist;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.app.core.util.CommandUtil;
import com.lzt.operate.code.generator.common.enums.AccountStatus;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.CustomConfigCollection;
import com.lzt.operate.code.generator.common.enums.HelpCategoryStatus;
import com.lzt.operate.code.generator.common.enums.RoleUniversalStatus;
import com.lzt.operate.code.generator.common.enums.WhetherSuper;
import com.lzt.operate.code.generator.common.enums.mybatis.GeneratorType;
import com.lzt.operate.code.generator.common.utils.CustomConstants;
import com.lzt.operate.code.generator.custommessagequeue.accessway.AccessWayConsumer;
import com.lzt.operate.code.generator.custommessagequeue.accessway.AccessWayQueueRunner;
import com.lzt.operate.code.generator.custommessagequeue.customconfig.CustomConfigConsumer;
import com.lzt.operate.code.generator.custommessagequeue.customconfig.CustomConfigQueueRunner;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogConsumer;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogQueueRunner;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogAssist;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogConsumer;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogQueueRunner;
import com.lzt.operate.code.generator.dao.service.AccessWayService;
import com.lzt.operate.code.generator.dao.service.AccountService;
import com.lzt.operate.code.generator.dao.service.CustomConfigService;
import com.lzt.operate.code.generator.dao.service.ErrorLogService;
import com.lzt.operate.code.generator.dao.service.GeneralLogService;
import com.lzt.operate.code.generator.dao.service.HelpCategoryService;
import com.lzt.operate.code.generator.dao.service.RoleUniversalService;
import com.lzt.operate.code.generator.dao.service.impl.AccessWayServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.CustomConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.ErrorLogServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.GeneralLogServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.HelpCategoryServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.code.generator.entities.HelpCategory;
import com.lzt.operate.code.generator.entities.RoleUniversal;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.OperatorCollection;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.secret.Md5Assist;
import com.lzt.operate.utility.services.bases.BaseCustomApplicationInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

	private final LoadingCache<String, Object> loadingCache;
	private final Environment environment;

	private final AccountAssist accountAssist;
	private final CustomConfigService customConfigService;
	private final AccessWayService accessWayService;
	private final ErrorLogService errorLogService;
	private final GeneralLogService generalLogService;
	private final HelpCategoryService helpCategoryService;

	@Autowired
	public CustomApplicationInit(
			LoadingCache<String, Object> loadingCache,
			Environment environment,
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService,
			CustomConfigServiceImpl customConfigService,
			AccessWayServiceImpl accessWayService,
			ErrorLogServiceImpl errorLogService,
			GeneralLogServiceImpl generalLogService,
			HelpCategoryServiceImpl helpCategoryService) {
		this.loadingCache = loadingCache;

		this.environment = environment;

		this.accountAssist = new AccountAssist(
				loadingCache,
				customJsonWebTokenConfig,
				accountService,
				accountRoleService,
				roleUniversalService,
				roleCodeToolsService);

		this.customConfigService = customConfigService;
		this.accessWayService = accessWayService;
		this.errorLogService = errorLogService;
		this.generalLogService = generalLogService;
		this.helpCategoryService = helpCategoryService;
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

	public HelpCategoryService getHelpCategoryService() {
		Optional<HelpCategoryService> optional = Optional.ofNullable(this.helpCategoryService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("HelpCategoryService获取失败");
	}

	@Override
	public void init() {
		this.checkSuperRoleCompleteness();
		this.checkExistAnyAccount();
		this.startAccessWayRunner();
		this.startErrorLogRunner();
		this.startGeneralLogRunner();
		this.startCustomConfigRunner();
		this.openOperationPanel();
		this.recordStartLog();
		this.checkHelpCategory();
		this.initDefaultMainFolder();

	}

	/**
	 * 检测超级管理员角色的完备性
	 */
	private void checkSuperRoleCompleteness() {
		RoleUniversalService roleUniversalService = this.accountAssist.getRoleUniversalService();

		boolean exist = roleUniversalService.existSuper(Channel.CodeGenerator.getFlag());

		if (!exist) {
			RoleUniversal roleUniversal = new RoleUniversal();

			roleUniversal.setName(ConstantCollection.SUPER_ROLE_NAME);
			roleUniversal.setWhetherSuper(WhetherSuper.Yes.getFlag());
			roleUniversal.setChannel(Channel.CodeGenerator);
			roleUniversal.setStatus(RoleUniversalStatus.Enabled, RoleUniversalStatus::getFlag, RoleUniversalStatus::getName);
			roleUniversal.setCreateOperatorId(OperatorCollection.System.getFlag());
			roleUniversal.setUpdateOperatorId(OperatorCollection.System.getFlag());

			roleUniversalService.save(roleUniversal);
		}
	}

	/**
	 * 检测是否存在任意用户，不存在则创建默认账户
	 */
	private void checkExistAnyAccount() {
		AccountService accountService = this.accountAssist.getAccountService();

		boolean exist = accountService.existAny(Channel.CodeGenerator.getFlag());

		if (!exist) {
			this.createAdminAccount();
			this.createSupermanAccount();
		}
	}

	private void createAdminAccount() {
		AccountService accountService = this.accountAssist.getAccountService();

		try {
			Account account = new Account();

			account.setUserName(ConstantCollection.DEFAULT_OPERATOR_USER_NAME);
			account.setName(ConstantCollection.DEFAULT_OPERATOR_NAME);
			account.setSlat(StringAssist.randomAlphanumeric(6)
										.toLowerCase());
			account.setPassword(Md5Assist.toMd5(ConstantCollection.DEFAULT_OPERATOR_PASSWORD, account.getSlat()));
			account.setChannel(Channel.CodeGenerator);
			account.setStatus(AccountStatus.Enabled, AccountStatus::getFlag, AccountStatus::getName);
			account.setCreateOperatorId(OperatorCollection.System.getFlag());
			account.setUpdateOperatorId(OperatorCollection.System.getFlag());

			accountService.save(account);

			Optional<RoleUniversal> optionalRoleUniversal = this.accountAssist.getRoleUniversalService()
																			  .findSuper(Channel.CodeGenerator.getFlag());

			optionalRoleUniversal.ifPresent(roleUniversal -> this.accountAssist.changeRoleUniversal(account.getId(), roleUniversal));
		} catch (NoSuchAlgorithmException e) {
			CustomApplicationInit.log.error("创建默认账户失败", e);

			e.printStackTrace();
		}
	}

	private void createSupermanAccount() {
		AccountService accountService = this.accountAssist.getAccountService();

		try {
			Account account = new Account();

			account.setUserName(ConstantCollection.DEFAULT_OPERATOR_SUPER_USER_NAME);
			account.setName(ConstantCollection.DEFAULT_OPERATOR_SUPER_NAME);
			account.setSlat(StringAssist.randomAlphanumeric(6)
										.toLowerCase());
			account.setPassword(Md5Assist.toMd5(ConstantCollection.DEFAULT_OPERATOR_SUPER_PASSWORD, account.getSlat()));
			account.setChannel(Channel.CodeGenerator);
			account.setStatus(AccountStatus.Enabled, AccountStatus::getFlag, AccountStatus::getName);
			account.setCreateOperatorId(OperatorCollection.System.getFlag());
			account.setUpdateOperatorId(OperatorCollection.System.getFlag());

			accountService.save(account);

			Optional<RoleUniversal> optionalRoleUniversal = this.accountAssist.getRoleUniversalService()
																			  .findSuper(Channel.CodeGenerator.getFlag());

			optionalRoleUniversal.ifPresent(roleUniversal -> this.accountAssist.changeRoleUniversal(account.getId(), roleUniversal));
		} catch (NoSuchAlgorithmException e) {
			CustomApplicationInit.log.error("创建默认账户失败", e);

			e.printStackTrace();
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
	 * startErrorLogRunner
	 */
	private void startCustomConfigRunner() {
		CustomConfigQueueRunner runner = new CustomConfigQueueRunner(this.getCustomConfigService(), new CustomConfigConsumer());

		ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("customConfig").build();

		Thread t = factory.newThread(runner);

		t.start();
	}

	/**
	 * 打开操作面板
	 */
	private void openOperationPanel() {
		Optional<CustomConfig> optional = this.getCustomConfigService()
											  .findByUuid(CustomConfigCollection.AutoOpenOperatePanel.getUuid());

		boolean autoOpenOperationPanel;

		if (optional.isPresent()) {
			CustomConfig customConfig = optional.get();

			autoOpenOperationPanel = customConfig.getValue().equals(ConstantCollection.YES_STRING);
		} else {
			autoOpenOperationPanel = CustomConfigCollection.AutoOpenOperatePanel.getDefaultValue()
																				.equals(ConstantCollection.YES_STRING);
		}

		if (autoOpenOperationPanel) {
			try {
				String port = this.environment.getProperty("local.server.port");

				CommandUtil.browse(new URI(StringAssist.merge("http://localhost:", port, StringAssist.merge("/", CustomConstants.OPERATION_PANEL))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 记录启动日志
	 */
	private void recordStartLog() {
		Optional<CustomConfig> optional = this.getCustomConfigService()
											  .findByUuid(CustomConfigCollection.RecordStartLog.getUuid());

		boolean recordLog;

		if (optional.isPresent()) {
			CustomConfig customConfig = optional.get();

			recordLog = customConfig.getValue().equals(ConstantCollection.YES_STRING);
		} else {
			recordLog = CustomConfigCollection.RecordStartLog.getDefaultValue().equals(ConstantCollection.YES_STRING);
		}

		if (recordLog) {
			GeneralLogAssist.quickRecord("codeGenerator App 启动");

		}
	}

	/**
	 * 检测默认帮助分类
	 */
	private void checkHelpCategory() {
		Long helpCategoryCount = this.getHelpCategoryService().count();

		if (helpCategoryCount.equals(ConstantCollection.ZERO_LONG)) {
			HelpCategory helpCategory = new HelpCategory();

			helpCategory.setName("未分类");
			helpCategory.setDescription("");
			helpCategory.setChannel(Channel.CodeGenerator);
			helpCategory.setStatus(HelpCategoryStatus.Enabled, HelpCategoryStatus::getFlag, HelpCategoryStatus::getName);

			this.getHelpCategoryService().save(helpCategory);
		}
	}

	private void initDefaultMainFolder() {
		String defaultMainGenerateFolderPath = StringAssist.merge(CommandUtil.getUserHomePath(), CustomConstants.DEFAULT_MAIN_GENERATE_FOLDER);

		File file = new File(defaultMainGenerateFolderPath);

		boolean canCreateGenerateResultByGeneratorFolder = false;

		if (!file.exists()) {
			boolean mkdirsResult = file.mkdirs();

			if (!mkdirsResult) {
				ErrorLog errorLog = new ErrorLog();

				errorLog.setMessage(StringAssist.merge("创建默认生成文件夹失败，默认文件将路径为：", file.getAbsolutePath()));

				ErrorLogProducerFactory.getInstance().getProducer().push(errorLog);
			} else {
				canCreateGenerateResultByGeneratorFolder = true;
			}
		} else {
			canCreateGenerateResultByGeneratorFolder = true;
		}

		if (canCreateGenerateResultByGeneratorFolder) {
			ExecutiveResult<String> executiveResult = this.createGenerateResultByGeneratorFolder(defaultMainGenerateFolderPath);

			if (!executiveResult.getSuccess()) {
				ErrorLog errorLog = new ErrorLog();

				errorLog.setMessage(StringAssist.merge("创建生成器结果生成文件夹失败，默认文件将路径为：", executiveResult.getData()));

				ErrorLogProducerFactory.getInstance().getProducer().push(errorLog);
			}
		}
	}

	protected ExecutiveResult<String> createGenerateResultByGeneratorFolder(String rootFolder) {
		List<GeneratorType> list = GeneratorType.valuesToList();

		for (GeneratorType generatorType : list) {
			ExecutiveResult<String> executiveResult = this.createEveryGenerateResultFolder(rootFolder, generatorType);

			if (!executiveResult.getSuccess()) {
				return executiveResult;
			}
		}

		return new ExecutiveResult<>(ReturnDataCode.Ok, "");
	}

	protected ExecutiveResult<String> createEveryGenerateResultFolder(String rootFolder, GeneratorType generatorType) {
		try {
			String folder = StringAssist.merge(StringAssist.trim(rootFolder), StringAssist.trim(generatorType.getName()), "/");

			File file = new File(folder);

			if (!file.exists()) {
				boolean mkdirsResult = file.mkdirs();

				if (!mkdirsResult) {
					ErrorLog errorLog = new ErrorLog();

					errorLog.setMessage(StringAssist.merge("创建生成文件夹失败，默认文件将路径为：", file.getAbsolutePath()));

					ErrorLogProducerFactory.getInstance().getProducer().push(errorLog);

					return new ExecutiveResult<>(ReturnDataCode.Exception, "");
				}

				return new ExecutiveResult<>(ReturnDataCode.Ok, file.getAbsolutePath());
			} else {
				if (!file.isDirectory()) {
					return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage("存在同名文件，创建文件夹失败"), "");
				}

				return new ExecutiveResult<>(ReturnDataCode.Ok, file.getAbsolutePath());
			}
		} catch (Exception e) {
			ErrorLogProducerFactory.getInstance()
								   .getProducer()
								   .pushException(e, StringAssist.merge("创建生成文件夹失败,文件夹名称", StringAssist.trim(generatorType
										   .getName())));

			return new ExecutiveResult<>(ReturnDataCode.Exception, e.getMessage());
		}
	}

}
