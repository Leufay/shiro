package com.myjava.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager ;
/**
 * 初始化Subject
 * @author Mrlxf
 *
 */
public class IniSecurity {
	/**
	 * 利用ini文件初始化一个subject
	 * @param iniFile 配置文件路径
	 * @return subject
	 */
	public static Subject initSecurity(String iniFile){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
		SecurityManager securityManager = (SecurityManager) factory.getInstance() ;
		SecurityUtils.setSecurityManager(securityManager);
		return SecurityUtils.getSubject() ;
	}
}	
