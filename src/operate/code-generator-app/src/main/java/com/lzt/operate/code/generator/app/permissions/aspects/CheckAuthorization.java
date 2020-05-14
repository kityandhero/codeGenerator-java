package com.lzt.operate.code.generator.app.permissions.aspects;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.assists.AccountAssist;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.custommessagequeue.accessway.AccessWayProducer;
import com.lzt.operate.code.generator.custommessagequeue.accessway.AccessWayProducerFactory;
import com.lzt.operate.code.generator.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.code.generator.entities.AccessWay;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.components.bases.JsonWebToken.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.exceptions.AuthorizationException;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.permissions.aspects.BaseCheckAuthorization;
import com.lzt.operate.utility.pojo.BaseOperator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 检验权限
 *
 * @author luzhitao
 */
@Aspect
@Component
public class CheckAuthorization extends BaseCheckAuthorization {

	private final LoadingCache<String, Object> loadingCache;

	private final AccountAssist accountAssist;

	@Autowired
	public CheckAuthorization(
			LoadingCache<String, Object> loadingCache,
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.loadingCache = loadingCache;
		accountAssist = new AccountAssist(
				loadingCache,
				customJsonWebTokenConfig,
				accountService,
				accountRoleService,
				roleUniversalService,
				roleCodeToolsService
		);
	}

	private AccountAssist getAccountAssist() {
		return accountAssist;
	}

	@Override
	protected BaseCustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		return accountAssist.getCustomJsonWebTokenConfig();
	}

	@Override
	protected boolean checkAuthorization(JoinPoint joinPoint, NeedAuthorization needAuthorization, CustomJsonWebToken customJsonWebToken) {
		String tag = Optional.of(Optional.of(needAuthorization).orElse(null).tag()).orElse("");

		if (!StringAssist.isNullOrEmpty(tag)) {
			String name = needAuthorization.name();
			String description = needAuthorization.description();

			Optional<HttpServletRequest> optional1 = RequestAssist.getCurrentHttpServletRequest();

			String relativePath;

			if (optional1.isPresent()) {
				relativePath = optional1.get().getRequestURI();
			} else {
				throw new AuthorizationException("非有效的Http请求");
			}

			String expand = StringAssist.join(needAuthorization.config(), "|", true, true);

			String[] config = needAuthorization.config();

			for (String c : config) {
				int index = c.indexOf("|");

				if (index >= 0) {
					throw new AuthorizationException("扩展权限中不能含有|");
				}
			}

			AccessWay accessWay = new AccessWay();

			accessWay.setName(name);
			accessWay.setDescription(description);
			accessWay.setTag(tag);
			accessWay.setRelativePath(relativePath);
			accessWay.setExpand(expand);

			AccessWayProducer producer = AccessWayProducerFactory.getInstance().getProducer();

			producer.push(accessWay);

			Optional<BaseOperator> resultBaseOperator = customJsonWebToken.getOperator();

			return resultBaseOperator.filter(baseOperator -> getAccountAssist()
																 .checkAccessPermission(baseOperator.getOperatorId(), tag))
									 .isPresent();
		}

		return false;
	}
}
