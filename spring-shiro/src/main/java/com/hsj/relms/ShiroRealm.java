package com.hsj.relms;


import java.util.Set;

import com.hsj.dao.PermissionMapper;
import com.hsj.dao.RoleMapper;
import com.hsj.dao.UserMapper;
import com.hsj.model.User;
import com.hsj.service.PermissionService;
import com.hsj.service.RoleService;
import com.hsj.service.UserService;
import com.hsj.service.impl.PermissionServiceImpl;
import com.hsj.service.impl.RoleServiceImpl;
import com.hsj.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
	UserServiceImpl userService;
//    @Resource
//	RoleServiceImpl roleService;
//    @Resource
//	PermissionServiceImpl permissionService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstRealm] doGetAuthenticationInfo");
		UsernamePasswordToken user = (UsernamePasswordToken) token;

		String credentials = userService.selectByUsername(user.getUsername());

//		if("admin".equals(user.getUsername())){
////			credentials = "098d2c478e9c11555ce2823231e02ec1";
////		}
		//SecurityUtils.getSubject().login(token1);
		Object principal = user.getUsername();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());

		return new SimpleAuthenticationInfo(user.getUsername(),credentials,credentialsSalt,getName());
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User token = (User) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		Set<String> roles = roleService.findRoleByUserId(token.getId());
//		info.setRoles(roles);
//		Set<String> permissions = permissionService.findPermissionByUserId(token.getId());
//		info.setStringPermissions(permissions);

//		SimpleAuthorizationInfo in = new SimpleAuthorizationInfo();
//		Set<String> s = new HashSet<String>();
//		s.add("admin");
//		in.setRoles(s);
		return info;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

//	public RoleServiceImpl getRoleService() {
//		return roleService;
//	}
//
//	public void setRoleService(RoleServiceImpl roleService) {
//		this.roleService = roleService;
//	}
//
//	public PermissionServiceImpl getPermissionService() {
//		return permissionService;
//	}
//
//	public void setPermissionService(PermissionServiceImpl permissionService) {
//		this.permissionService = permissionService;
//	}
}
