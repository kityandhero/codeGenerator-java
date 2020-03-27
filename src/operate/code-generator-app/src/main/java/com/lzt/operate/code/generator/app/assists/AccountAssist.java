package com.lzt.operate.code.generator.app.assists;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.caches.ListCompetenceCache;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.AccountRoleCreateMode;
import com.lzt.operate.code.generator.common.enums.WhetherSuper;
import com.lzt.operate.code.generator.dao.service.AccountRoleService;
import com.lzt.operate.code.generator.dao.service.AccountService;
import com.lzt.operate.code.generator.dao.service.RoleCodeToolsService;
import com.lzt.operate.code.generator.dao.service.RoleUniversalService;
import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.code.generator.entities.AccountRole;
import com.lzt.operate.code.generator.entities.RoleCodeTools;
import com.lzt.operate.code.generator.entities.RoleUniversal;
import com.lzt.operate.code.generator.entities.bases.BaseRole;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.BaseOperator;
import com.lzt.operate.utility.pojo.Competence;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Account辅助方法集合
 *
 * @author luzhitao
 */
public class AccountAssist {

	private final LoadingCache<String, Object> loadingCache;

	private final CustomJsonWebTokenConfig jwtConfig;

	private final AccountService accountService;

	private final AccountRoleService accountRoleService;

	private final RoleUniversalService roleUniversalService;

	private final RoleCodeToolsService roleCodeToolsService;

	public AccountAssist(
			LoadingCache<String, Object> customEhcacheManager,
			CustomJsonWebTokenConfig jwtConfig,
			AccountService accountService,
			AccountRoleService accountRoleService,
			RoleUniversalService roleUniversalService,
			RoleCodeToolsService roleCodeToolsService) {
		this.jwtConfig = jwtConfig;
		this.loadingCache = customEhcacheManager;
		this.accountService = accountService;
		this.accountRoleService = accountRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	public AccountService getAccountService() {
		Optional<AccountService> optional = Optional.ofNullable(this.accountService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("AccountService获取失败");
	}

	public CustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		Optional<CustomJsonWebTokenConfig> optional = Optional.ofNullable(this.jwtConfig);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("CustomJsonWebTokenConfig获取失败");
	}

	public LoadingCache<String, Object> getLoadingCache() {
		Optional<LoadingCache<String, Object>> optional = Optional.ofNullable(this.loadingCache);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("LoadingCache<String, Object>获取失败");
	}

	private AccountRoleService getAccountRoleService() {
		Optional<AccountRoleService> optional = Optional.ofNullable(this.accountRoleService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("AccountRoleService获取失败");
	}

	public RoleUniversalService getRoleUniversalService() {
		Optional<RoleUniversalService> optional = Optional.ofNullable(this.roleUniversalService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("RoleUniversalService获取失败");
	}

	private RoleCodeToolsService getRoleCodeToolsService() {
		Optional<RoleCodeToolsService> optional = Optional.ofNullable(this.roleCodeToolsService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("RoleCodeToolsService获取失败");
	}

	public Optional<Account> getCurrent() {
		ExecutiveResult<CustomJsonWebToken> result = CustomJsonWebToken.getFromCurrentHttpToken(jwtConfig);

		if (result.getSuccess()) {
			Optional<BaseOperator> resultBaseOperator = result.getData().getOperator();

			if (resultBaseOperator.isPresent()) {
				return accountService.get(resultBaseOperator.get().getOperatorId());
			}
		}

		return Optional.empty();
	}

	private List<RoleUniversal> getRoleUniversalCollection(long accountId) {
		AccountRoleService accountRoleService = this.getAccountRoleService();

		RoleUniversalService roleUniversalService = this.getRoleUniversalService();

		Optional<AccountRole> resultAccountRole = accountRoleService.findByAccountId(accountId);

		if (resultAccountRole.isPresent()) {
			List<String> list = Arrays.asList(Optional.of(resultAccountRole.get().getRoleUniversalCollection())
													  .orElse("")
													  .split(","));

			List<Long> roleIdList = list.stream()
										.filter(o -> !StringAssist.isNullOrEmpty(o))
										.map(o -> ConvertAssist.stringToLong(o, 0))
										.filter(o -> o > 0).collect(Collectors.toList());

			return roleUniversalService.findByIdCollection(roleIdList);
		}

		return new ArrayList<>();
	}

	private List<RoleCodeTools> getRoleCodeToolsCollection(long accountId) {
		AccountRoleService accountRoleService = this.getAccountRoleService();

		Optional<AccountRole> resultAccountRole = accountRoleService.findByAccountId(accountId);

		if (resultAccountRole.isPresent()) {
			List<String> list = Arrays.asList(Optional.of(resultAccountRole.get().getRoleUniversalCollection())
													  .orElse("")
													  .split(","));

			List<Long> roleIdList = list.stream()
										.filter(o -> !StringAssist.isNullOrEmpty(o))
										.map(o -> ConvertAssist.stringToLong(o, 0))
										.filter(o -> o > 0).collect(Collectors.toList());

			RoleCodeToolsService roleCodeToolsService = this.getRoleCodeToolsService();

			return roleCodeToolsService.findByIdCollection(roleIdList);
		}

		return new ArrayList<>();
	}

	public List<SerializableData> getAuthorityCollection(long accountId) {
		List<RoleUniversal> roleUniversalList = getRoleUniversalCollection(accountId);
		List<RoleCodeTools> roleCodeToolsList = getRoleCodeToolsCollection(accountId);

		List<SerializableData> result = roleUniversalList.stream().map(role -> {
			SerializableData data = new SerializableData();

			data.append("key", role.getId());
			data.append("name", role.getName());
			data.append("createMode", AccountRoleCreateMode.FromUniversal.getFlag());

			return data;
		}).collect(Collectors.toList());

		result.addAll(roleCodeToolsList.stream().map(role -> {
			SerializableData data = new SerializableData();

			data.append("key", role.getId());
			data.append("name", role.getName());
			data.append("createMode", AccountRoleCreateMode.IndependentEstablishment.getFlag());

			return data;
		}).collect(Collectors.toList()));

		return result;
	}

	public List<String> getCompetenceTagCollection(long accountId) {
		List<Competence> listResult = getCompetenceCollection(accountId);

		List<String> result = listResult.stream().map(Competence::getTag).collect(Collectors.toList());

		result.add("currentCustomer");

		return result;
	}

	private List<Competence> getCompetenceCollection(long accountId) {
		RoleUniversalService resultRoleUniversalService = this.getRoleUniversalService();
		RoleCodeToolsService resultRoleCodeToolsService = this.getRoleCodeToolsService();

		List<Competence> ceList = new ArrayList<>();

		List<BaseRole> baseRoleList = new ArrayList<>();

		List<RoleUniversal> roleUniversalList = getRoleUniversalCollection(accountId);

		if (!roleUniversalList.isEmpty()) {
			baseRoleList.addAll(roleUniversalList);
		}

		List<RoleCodeTools> roleCodeToolsList = getRoleCodeToolsCollection(accountId);

		if (!roleCodeToolsList.isEmpty()) {
			baseRoleList.addAll(roleCodeToolsList);
		}

		for (BaseRole role : baseRoleList) {
			if (role.getWhetherSuper().equals(WhetherSuper.Yes.getFlag())) {
				Competence c = new Competence();

				c.setName(ConstantCollection.SUPER_ROLE_NAME);
				c.setTag(ConstantCollection.SUPER_ROLE_TAG);

				ceList.add(c);
			} else {
				List<Competence> competenceList = new ArrayList<>();

				if (role instanceof RoleUniversal) {
					competenceList = resultRoleUniversalService
							.getCompetenceEntityCollection((RoleUniversal) role);
				}

				if (role instanceof RoleCodeTools) {
					competenceList = resultRoleCodeToolsService
							.getCompetenceEntityCollection((RoleCodeTools) role);
				}

				for (Competence c : competenceList) {
					boolean exist = false;
					int ceListSize = ceList.size();

					for (int index = 0; index < ceListSize; index++) {
						Competence ce = ceList.get(index);
						if (ce.getTag().equals(c.getTag())) {
							exist = true;

							try {
								ce = Competence.plus(ce, c);

								ceList.set(index, ce);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					if (!exist) {
						ceList.add(c);
					}
				}
			}
		}

		return ceList;
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId                 accountId
	 * @param roleUniversalIdCollection roleUniversalIdList
	 * @param roleCodeToolsIdCollection roleCodeToolsIdList
	 * @return ExecutiveSimpleResult
	 */
	public ExecutiveSimpleResult changeRole(long accountId, String roleUniversalIdCollection, String roleCodeToolsIdCollection) {
		if (accountId <= 0) {
			ExecutiveSimpleResult result = new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage());

			result.setMessage("指定的账户标识错误");

			return result;
		}

		List<Long> roleUniversalIdList = StringAssist.isNullOrEmpty(roleUniversalIdCollection) ? new ArrayList<>() : Stream
				.of(StringAssist
						.split(roleUniversalIdCollection, ",").toArray())
				.filter(o -> !StringAssist.isNullOrEmpty(o.toString()))
				.map(o -> ConvertAssist.stringToLong(o.toString()))
				.collect(Collectors.toList());

		List<Long> roleCodeToolsIdList = StringAssist.isNullOrEmpty(roleCodeToolsIdCollection) ? new ArrayList<>() : Stream
				.of(StringAssist
						.split(roleCodeToolsIdCollection, ",").toArray())
				.filter(o -> !StringAssist.isNullOrEmpty(o.toString()))
				.map(o -> ConvertAssist.stringToLong(o.toString()))
				.collect(Collectors.toList());

		return changeRole(accountId, roleUniversalIdList, roleCodeToolsIdList);
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId           accountId
	 * @param roleUniversalIdList roleUniversalIdList
	 * @param roleCodeToolsIdList roleCodeToolsIdList
	 * @return ExecutiveSimpleResult
	 */
	public ExecutiveSimpleResult changeRole(long accountId, List<Long> roleUniversalIdList, List<Long> roleCodeToolsIdList) {
		ExecutiveSimpleResult result;

		if (accountId <= 0) {
			result = new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage());

			result.setMessage("指定的账户标识错误");

			return result;
		}

		ExecutiveSimpleResult changeRoleUniversalResult = changeRoleUniversal(accountId, roleUniversalIdList);
		ExecutiveSimpleResult changeRoleCodeToolsResult = changeRoleCodeTools(accountId, roleCodeToolsIdList);

		if (changeRoleUniversalResult.getSuccess() && changeRoleCodeToolsResult.getSuccess()) {
			return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
		}

		if (!changeRoleUniversalResult.getSuccess()) {
			return changeRoleUniversalResult;
		}

		if (!changeRoleCodeToolsResult.getSuccess()) {
			return changeRoleCodeToolsResult;
		}

		return new ExecutiveSimpleResult(ReturnDataCode.Unknown.toMessage());
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId     accountId
	 * @param roleUniversal roleUniversal
	 * @return ExecutiveSimpleResult
	 */
	public ExecutiveSimpleResult changeRoleUniversal(long accountId, RoleUniversal roleUniversal) {
		return changeRoleUniversal(accountId, roleUniversal.getId());
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId       accountId
	 * @param roleUniversalId roleUniversalId
	 * @return ExecutiveSimpleResult
	 */
	private ExecutiveSimpleResult changeRoleUniversal(long accountId, Long roleUniversalId) {
		return changeRoleUniversal(accountId, Collections.singletonList(roleUniversalId));
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId           accountId
	 * @param roleUniversalIdList roleUniversalIdList
	 * @return ExecutiveSimpleResult
	 */
	private ExecutiveSimpleResult changeRoleUniversal(long accountId, List<Long> roleUniversalIdList) {
		RoleUniversalService roleUniversalService = this.getRoleUniversalService();

		AccountRoleService accountRoleService = this.getAccountRoleService();

		ExecutiveSimpleResult result;

		if (accountId <= 0) {
			result = new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage());

			result.setMessage("指定的账户标识错误");

			return result;
		}

		Optional<Account> resultGet = accountService.get(accountId);

		if (resultGet.isPresent()) {
			List<RoleUniversal> roleUniversalListResult = roleUniversalService
					.findByIdCollection(roleUniversalIdList);

			Optional<AccountRole> optionalAccountRole = accountRoleService
					.findByAccountId(accountId);

			AccountRole accountRole;

			if (optionalAccountRole.isPresent()) {
				accountRole = optionalAccountRole.get();
			} else {
				accountRole = new AccountRole();

				accountRole.setAccountId(accountId);
			}

			accountRole.setRoleUniversalCollection(StringAssist.join(roleUniversalListResult.stream()
																							.map(o -> Long.toString(o.getId()))
																							.collect(Collectors.toList())));

			accountRoleService.save(accountRole);

			result = new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
		} else {
			result = new ExecutiveSimpleResult(ReturnDataCode.NoData.toMessage());

			result.setMessage("指定的账户不存在");
		}

		return result;
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId     accountId
	 * @param roleCodeTools roleCodeTools
	 * @return ExecutiveSimpleResult
	 */
	public ExecutiveSimpleResult changeRoleCodeTools(long accountId, RoleCodeTools roleCodeTools) {
		return changeRoleCodeTools(accountId, roleCodeTools.getId());
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId       accountId
	 * @param roleCodeToolsId roleCodeToolsId
	 * @return ExecutiveSimpleResult
	 */
	private ExecutiveSimpleResult changeRoleCodeTools(long accountId, Long roleCodeToolsId) {
		return changeRoleCodeTools(accountId, Collections.singletonList(roleCodeToolsId));
	}

	/**
	 * 更改指定账户拥有的角色
	 *
	 * @param accountId           accountId
	 * @param roleCodeToolsIdList roleCodeToolsIdList
	 * @return ExecutiveSimpleResult
	 */
	private ExecutiveSimpleResult changeRoleCodeTools(long accountId, List<Long> roleCodeToolsIdList) {
		RoleCodeToolsService roleCodeToolsService = this.getRoleCodeToolsService();

		AccountRoleService accountRoleService = this.getAccountRoleService();

		ExecutiveSimpleResult result;

		if (accountId <= 0) {
			result = new ExecutiveSimpleResult(ReturnDataCode.ParamError.toMessage());

			result.setMessage("指定的账户标识错误");

			return result;
		}

		Optional<Account> resultGet = accountService.get(accountId);

		if (resultGet.isPresent()) {
			List<RoleCodeTools> roleCodeToolsList = roleCodeToolsService
					.findByIdCollection(roleCodeToolsIdList);

			Optional<AccountRole> optionalAccountRole = accountRoleService
					.findByAccountId(accountId);

			AccountRole accountRole;

			if (optionalAccountRole.isPresent()) {
				accountRole = optionalAccountRole.get();
			} else {
				accountRole = new AccountRole();

				accountRole.setAccountId(accountId);
			}

			accountRole.setRoleCodeToolsCollection(StringAssist.join(roleCodeToolsList.stream()
																					  .map(o -> Long.toString(o.getId()))
																					  .collect(Collectors.toList())));
			accountRoleService.save(accountRole);

			result = new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
		} else {
			result = new ExecutiveSimpleResult(ReturnDataCode.NoData.toMessage());

			result.setMessage("指定的账户不存在");
		}

		return result;
	}

	public boolean checkAccessPermission(long accountId, String tag) {
		Optional<List<Competence>> optionalCompetenceList = ListCompetenceCache.getCache(accountId, this.getLoadingCache());

		List<Competence> competenceList;

		if (!optionalCompetenceList.isPresent()) {
			competenceList = getCompetenceCollection(accountId);

			ListCompetenceCache.setCache(accountId, competenceList, this.getLoadingCache());
		} else {
			competenceList = optionalCompetenceList.get();
		}

		for (Competence c : competenceList) {
			if (c.getTag().equals(tag) || c.getTag().equals(ConstantCollection.SUPER_ROLE_TAG)) {
				return true;
			}
		}

		return false;
	}

}
