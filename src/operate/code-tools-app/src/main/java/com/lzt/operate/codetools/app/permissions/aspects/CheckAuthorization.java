package com.lzt.operate.codetools.app.permissions.aspects;

import com.lzt.operate.codetools.app.assists.AccountAssist;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.app.ehcache.CustomEhcacheManager;
import com.lzt.operate.codetools.dao.service.impl.AccountRoleServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.AccountServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleCodeToolsServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.codetools.entities.AccessWay;
import com.lzt.operate.custommessagequeue.custommessagequeue.accessway.AccessWayProducer;
import com.lzt.operate.custommessagequeue.custommessagequeue.accessway.AccessWayProducerFactory;
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

	private CustomEhcacheManager customEhcacheManager;

	private AccountAssist accountAssist;

	@Autowired
	public CheckAuthorization(
			CustomJsonWebTokenConfig customJsonWebTokenConfig,
			CustomEhcacheManager customEhcacheManager,
			AccountServiceImpl accountService,
			AccountRoleServiceImpl accountRoleService,
			RoleUniversalServiceImpl roleUniversalService,
			RoleCodeToolsServiceImpl roleCodeToolsService) {
		this.accountAssist = new AccountAssist(
				customJsonWebTokenConfig,
				customEhcacheManager,
				accountService,
				accountRoleService,
				roleUniversalService,
				roleCodeToolsService
		);
	}

	private AccountAssist getAccountAssist() {
		return this.accountAssist;
	}

	@Override
	protected BaseCustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		return this.accountAssist.getCustomJsonWebTokenConfig();
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

			return resultBaseOperator.filter(baseOperator -> this.getAccountAssist()
																 .checkAccessPermission(baseOperator.getOperatorId(), tag))
									 .isPresent();
		}

		return false;
	}
}
