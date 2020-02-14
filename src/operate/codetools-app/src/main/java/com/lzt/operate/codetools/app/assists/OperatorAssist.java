package com.lzt.operate.codetools.app.assists;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.OperatorRoleCreateMode;
import com.lzt.operate.codetools.common.enums.WhetherSuper;
import com.lzt.operate.codetools.common.utils.Constants;
import com.lzt.operate.codetools.dao.service.OperatorRoleService;
import com.lzt.operate.codetools.dao.service.OperatorService;
import com.lzt.operate.codetools.dao.service.RoleCodeToolsService;
import com.lzt.operate.codetools.dao.service.RoleUniversalService;
import com.lzt.operate.codetools.entities.Operator;
import com.lzt.operate.codetools.entities.OperatorRole;
import com.lzt.operate.codetools.entities.RoleCodeTools;
import com.lzt.operate.codetools.entities.RoleUniversal;
import com.lzt.operate.codetools.entities.bases.BaseRole;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.Competence;
import com.lzt.operate.utility.pojo.SerializableData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Operator辅助方法集合
 *
 * @author luzhitao
 */
public class OperatorAssist {

	private CustomJsonWebTokenConfig jwtConfig;

	private OperatorService operatorService;

	private OperatorRoleService operatorRoleService;

	private RoleUniversalService roleUniversalService;

	private RoleCodeToolsService roleCodeToolsService;

	public OperatorAssist(
			CustomJsonWebTokenConfig jwtConfig,
			OperatorService operatorRepository,
			OperatorRoleService operatorRoleService,
			RoleUniversalService roleUniversalService,
			RoleCodeToolsService roleCodeToolsService) {
		this.jwtConfig = jwtConfig;
		this.operatorService = operatorRepository;
		this.operatorRoleService = operatorRoleService;
		this.roleUniversalService = roleUniversalService;
		this.roleCodeToolsService = roleCodeToolsService;
	}

	private OperatorService getOperatorService() {
		Optional<OperatorService> optional = Optional.ofNullable(this.operatorService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("OperatorService获取失败");
	}

	private OperatorRoleService getOperatorRoleService() {
		Optional<OperatorRoleService> optional = Optional.ofNullable(this.operatorRoleService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("OperatorRoleService获取失败");
	}

	private RoleUniversalService getRoleUniversalService() {
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

		throw new RuntimeException("RoleCodeToolsService");
	}

	public Optional<Operator> getCurrent() {
		Optional<CustomJsonWebToken> op = CustomJsonWebToken.getFromCurrentHttpToken(jwtConfig);

		return op.flatMap(customIdentificationToken -> {
			try {
				return operatorService.get(customIdentificationToken.getId());
			} catch (Exception e) {
				e.printStackTrace();

				return Optional.empty();
			}
		});
	}

	public List<RoleUniversal> getRoleUniversalCollection(long operatorId) {
		OperatorRoleService operatorRoleService = this.getOperatorRoleService();

		Optional<OperatorRole> optional = operatorRoleService.findByOperatorId(operatorId);

		if (optional.isPresent()) {
			List<String> list = Arrays.asList(Optional.of(optional.get().getRoleUniversalCollection())
													  .orElse("")
													  .split(","));

			List<Long> roleIdList = list.stream()
										.filter(o -> !StringAssist.isNullOrEmpty(o))
										.map(o -> ConvertAssist.stringToLong(o, (long) 0))
										.filter(o -> o > 0).collect(Collectors.toList());

			return this.getRoleUniversalService().findByIdCollection(roleIdList);
		} else {
			return new ArrayList<>();
		}
	}

	public List<RoleCodeTools> getRoleCodeToolsCollection(long operatorId) {
		OperatorRoleService operatorRoleService = this.getOperatorRoleService();

		Optional<OperatorRole> optional = operatorRoleService.findByOperatorId(operatorId);

		if (optional.isPresent()) {
			List<String> list = Arrays.asList(Optional.of(optional.get().getRoleCodeToolsCollection())
													  .orElse("")
													  .split(","));

			List<Long> roleIdList = list.stream()
										.filter(o -> !StringAssist.isNullOrEmpty(o))
										.map(o -> ConvertAssist.stringToLong(o, (long) 0))
										.filter(o -> o > 0).collect(Collectors.toList());

			return this.getRoleCodeToolsService().findByIdCollection(roleIdList);
		} else {
			return new ArrayList<>();
		}
	}

	public List<SerializableData> getAuthorityCollection(long operatorId) {
		List<RoleUniversal> roleUniversalList = getRoleUniversalCollection(operatorId);
		List<RoleCodeTools> roleCodeToolsList = getRoleCodeToolsCollection(operatorId);

		List<SerializableData> result = roleUniversalList.stream().map(role -> {
			SerializableData data = new SerializableData();

			data.append("key", role.getId());
			data.append("name", role.getName());
			data.append("createMode", OperatorRoleCreateMode.FromUniversal.getValue());

			return data;
		}).collect(Collectors.toList());

		result.addAll(roleCodeToolsList.stream().map(role -> {
			SerializableData data = new SerializableData();

			data.append("key", role.getId());
			data.append("name", role.getName());
			data.append("createMode", OperatorRoleCreateMode.IndependentEstablishment.getValue());

			return data;
		}).collect(Collectors.toList()));

		return result;
	}

	public List<String> getCompetenceTagCollection(long operatorId) {
		List<Competence> list = getCompetenceCollection(operatorId);

		List<String> result = list.stream().map(Competence::getTag).collect(Collectors.toList());

		result.add("self");

		return result;
	}

	private List<Competence> getCompetenceCollection(long operatorId) {
		List<Competence> ceList = new ArrayList<>();

		List<BaseRole> baseRoleList = new ArrayList<>();

		List<RoleUniversal> roleUniversalList = getRoleUniversalCollection(operatorId);

		if (!roleUniversalList.isEmpty()) {
			baseRoleList.addAll(roleUniversalList);
		}

		List<RoleCodeTools> roleCodeToolsList = getRoleCodeToolsCollection(operatorId);

		if (!roleCodeToolsList.isEmpty()) {
			baseRoleList.addAll(roleCodeToolsList);
		}

		for (BaseRole role : baseRoleList) {
			if (role.getWhetherSuper().equals(WhetherSuper.Yes.getValue())) {
				Competence c = new Competence();

				c.setName(Constants.SUPER_ROLE_NAME);
				c.setTag(Constants.SUPER_ROLE_TAG);

				ceList.add(c);
			} else {
				List<Competence> competenceList = new ArrayList<>();

				if (role instanceof RoleUniversal) {
					competenceList = this.getRoleUniversalService().getCompetenceEntityCollection((RoleUniversal) role);
				}

				if (role instanceof RoleCodeTools) {
					competenceList = this.getRoleCodeToolsService().getCompetenceEntityCollection((RoleCodeTools) role);
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

}
