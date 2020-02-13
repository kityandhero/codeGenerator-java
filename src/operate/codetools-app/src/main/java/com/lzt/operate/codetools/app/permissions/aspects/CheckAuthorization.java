package com.lzt.operate.codetools.app.permissions.aspects;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.impl.AccessWayServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.OperatorServiceImpl;
import com.lzt.operate.codetools.entities.AccessWay;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.exceptions.AuthorizationException;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.permissions.aspects.BaseCheckAuthorization;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 检验权限
 *
 * @author luzhitao
 */
@Aspect
@Component
public class CheckAuthorization extends BaseCheckAuthorization {

	private final OperatorServiceImpl operatorService;
	private AccessWayServiceImpl accessWayService;

	@Autowired
	public CheckAuthorization(CustomJsonWebTokenConfig customJsonWebTokenConfig, AccessWayServiceImpl accessWayService, OperatorServiceImpl operatorService) {
		this.setBaseCustomJsonWebTokenConfig(customJsonWebTokenConfig);
		this.accessWayService = accessWayService;
		this.operatorService = operatorService;
	}

	@Override
	protected boolean checkAuthorization(JoinPoint joinPoint, NeedAuthorization needAuthorization, CustomJsonWebToken customJsonWebToken) {
		String tag = Optional.of(Optional.of(needAuthorization).orElse(null).tag()).orElse("");

		if (!StringAssist.isNullOrEmpty(tag)) {
			Optional<AccessWay> optional = this.accessWayService.findByTag(tag);

			String name = needAuthorization.name();

			String relativePath = RequestAssist.getHttpServletRequest().getContextPath();

			String expand = StringAssist.join(needAuthorization.config(), "|", true, true);

			if (optional.isPresent()) {

				AccessWay accessWay = optional.get();

				String accessWayName = accessWay.getName();
				String accessWayRelativePath = accessWay.getRelativePath();
				String accessWayExpand = accessWay.getExpand();

				if (!accessWayName.equals(name) || !accessWayRelativePath.equals(relativePath) || !accessWayExpand.equals(expand)) {

					accessWay.setName(name);
					accessWay.setTag(tag);
					accessWay.setRelativePath(relativePath);
					accessWay.setExpand(expand);

					this.accessWayService.save(accessWay);
				}

			} else {
				String[] config = needAuthorization.config();

				for (String c : config) {
					int index = c.indexOf("|");

					if (index >= 0) {
						throw new AuthorizationException("扩展权限中不能含有|");
					}
				}

				AccessWay accessWay = new AccessWay();

				accessWay.setName(name);
				accessWay.setTag(tag);
				accessWay.setRelativePath(relativePath);
				accessWay.setExpand(expand);

				this.accessWayService.save(accessWay);

				return false;
			}
		}

		return false;
	}
}
