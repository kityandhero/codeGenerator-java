package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.service.impl.OperatorServiceImpl;
import com.lzt.operate.utility.StringAssist;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author lzt
 */
public class CustomShiroRealm extends AuthorizingRealm {

	@Autowired
	private OperatorServiceImpl operatorServiceImpl;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限认证");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Operator user = (Operator) principals.getPrimaryPrincipal();
		System.out.println("**********" + user.toString());

		// try {
		// 	List<SysRole> roles = sysRoleService.selectByUid(user.getUid());
		// 	for (SysRole role : roles) {
		// 		authorizationInfo.addRole(role.getRolename());
		// 	}
		// 	List<SysPermission> permissions = sysPermissionService.selectByUid(user.getUid());
		// 	for (SysPermission permission : permissions) {
		// 		authorizationInfo.addStringPermission(permission.getPername());
		// 	}
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

		return authorizationInfo;

	}

	/**
	 * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
	 *
	 * @param authcToken authcToken
	 * @return AuthenticationInfo
	 * @throws AuthenticationException AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		//获取用户账号和密码
		System.out.println("用户认证");

		Object credentials = authcToken.getCredentials();

		String token = (String) credentials;

		Optional<CustomJsonWebToken> opCustomJsonWebToken = CustomJsonWebToken.getFromHttpToken(token);

		if (!opCustomJsonWebToken.isPresent()) {
			return null;
		}

		CustomJsonWebToken customJsonWebToken = opCustomJsonWebToken.get();

		String userName;

		try {
			userName = customJsonWebToken.getUserName();
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

		if (StringAssist.isNullOrEmpty(userName)) {
			return null;
		}

		Optional<Operator> op = operatorServiceImpl.findByUserName(userName);

		if (!op.isPresent()) {
			throw new UnknownAccountException("未知用户");
		}

		Operator operator = op.get();

		// if (user.getStatus() == 1) { //账户冻结
		// 	throw new LockedAccountException();
		// }

		// 密码验证
		if (!CustomJsonWebToken.verify(token, operator.getId(), userName, operator.getPassword())) {
			throw new UnknownAccountException("账户验证失败");
		}

		return new SimpleAuthenticationInfo(token, token, "CUSTOMREALM_NAME");
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof CustomJsonWebToken;
	}
}
