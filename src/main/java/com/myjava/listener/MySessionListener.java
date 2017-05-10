package com.myjava.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.SessionListenerAdapter;

public class MySessionListener extends SessionListenerAdapter{

	
	/**
	 * session过期时触发
	 * 删除之前保存的用户信息
	 */
	@Override
	public void onExpiration(Session session) {
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user") ;
		}
	}

}
