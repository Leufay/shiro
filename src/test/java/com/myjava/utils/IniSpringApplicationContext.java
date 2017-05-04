package com.myjava.utils;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IniSpringApplicationContext {
	@Test
	public void IniSpring() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml") ;
		ac.getBean("userService") ;
	}
}
