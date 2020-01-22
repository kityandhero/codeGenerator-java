package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.service.impl.OperatorServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
	 * @param token token
	 * @return AuthenticationInfo
	 * @throws AuthenticationException AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户账号和密码
		System.out.println("用户认证");
		String username = (String) token.getPrincipal();

		Optional<Operator> op = operatorServiceImpl.findByUserName(username);
		if (!op.isPresent()) {
			return null;
		}

		Operator operator = op.get();

		// if (user.getStatus() == 1) { //账户冻结
		// 	throw new LockedAccountException();
		// }

		return new SimpleAuthenticationInfo(
				operator,//安全数据
				operator.getPassword(),//
				getName()
		);
	}
}
