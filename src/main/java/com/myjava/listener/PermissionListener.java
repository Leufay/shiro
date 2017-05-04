package com.myjava.listener;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.myjava.entity.Module;
import com.myjava.service.ModuleService;
/**
 * 
 * @author Mrlxf
 *
 */
public class PermissionListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext() ;
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext) ;
		ModuleService moduleService = (ModuleService) applicationContext.getBean("moduleService") ;
		//获取所有的模块
		Collection<Module> allModules = moduleService.findAll() ;
		servletContext.setAttribute("allModules", allModules);
//		//获取所有的功能
//		FunctionService funService = (FunctionService) applicationContext.getBean("functionService") ;
//		Collection<Function> allFunctions = funService.findAll() ;
//		servletContext.setAttribute("allFunctions", allFunctions);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
