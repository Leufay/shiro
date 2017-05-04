package com.myjava.coder;

import org.junit.Before;
import org.junit.Test;

import com.myjava.entity.User;
import com.myjava.service.UserService;
import com.myjava.service.impl.UserServiceImpl;

/**
 * 测试加密
 * @author Mrlxf
 *
 */
public class TestEncipher {
	private UserService userService ;
	@Test
	public void testEncipher() throws Exception {
		User user = new User() ;
		user.setUsername("admin");
		user.setPassword("admin");
		user.setId(2L);
		
	}
	@Before
	public void initService(){
		userService = new UserServiceImpl() ;
	}
}
