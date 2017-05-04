package com.myjava.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.myjava.entity.User;
/**
 * 加密
 * @author Mrlxf
 *
 */
public class PasswordHelper {
	//指定算法名称
	private String algorithName = "md5"; 							
	//产生随机数的工具
	RandomNumberGenerator randomNumber = new SecureRandomNumberGenerator() ;
	//hash值的迭代次数
	private final int hashIterations = 2 ;	
	
	public void encryptPassword(User user){
		//user.setSalt(randomNumber.nextBytes().toHex());				//设置盐
		String newPassword = new SimpleHash(this.algorithName,user.getPassword() , ByteSource.Util.bytes(user.getSalt()),this.hashIterations).toHex();
		user.setPassword(newPassword) ;
		
	}
	
}
