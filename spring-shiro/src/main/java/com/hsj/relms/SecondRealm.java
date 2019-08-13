package com.hsj.relms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SecondReaml] doGetAuthenticationInfo");

		UsernamePasswordToken token1 = (UsernamePasswordToken) token;
		Object credentials = null;
		if("admin".equals(token1.getUsername())){
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}
		//SecurityUtils.getSubject().login(token1);
		Object principal = token1.getUsername();

		ByteSource credentialsSalt = ByteSource.Util.bytes(token1.getUsername());
		return new SimpleAuthenticationInfo(token.getPrincipal(), credentials, credentialsSalt, getName());
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}
