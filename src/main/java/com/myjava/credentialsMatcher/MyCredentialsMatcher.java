package com.myjava.credentialsMatcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
/**
 * CredentialMatcher密码验证时会调用
 * 
 * <br>
 * 这里其实没必要，只要在配置文件中指定使用HashedCredentialMatcher就可以
 * @author Mrlxf
 *
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {
	/**
	 * 重写默认的SimpleCredentialsMatcher的doCredentialsMatcher
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		return super.doCredentialsMatch(token, info) ;
	}
}
