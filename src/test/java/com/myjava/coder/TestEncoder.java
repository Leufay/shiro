package com.myjava.coder;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestEncoder {
	@Test
	public void testBse64() throws Exception {
		String str = "admin" ;
		String base64Encode = Base64.encodeToString(str.getBytes()) ;
		System.out.println(base64Encode);
	}
	/**
	 * md5加密
	 * new Md5Hash(Obeject source , Object salt)
	 * @throws Exception
	 */
	@Test
	public void testMD5() throws Exception {
		String username = "admin";	//用户名
		String password = "lxf123";	//密码
		String userId = "12";		//ID
		String salt = username+userId ;		//加盐,因为用户名和ID只有系统知道所以更难破解
		String md5 = new Md5Hash(password, salt).toString() ;
		System.out.println("MD5加密后的结果为:"+md5);
		
		//shiro提供了通用的散列支持
		String newPassword = new SimpleHash("MD5", password, salt).toString() ;
		System.out.println("MD5加密"+newPassword);
		String newPassword2 = new SimpleHash("SHA-512",password,salt).toString();
		System.out.println(newPassword2);
	}
}
