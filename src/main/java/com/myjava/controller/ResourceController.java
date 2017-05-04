package com.myjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myjava.entity.Module;
import com.myjava.service.ModuleService;


/**
 * 资源管理的Controller
 * @author Mrlxf
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ModuleService moduleService ;
	
	@RequestMapping("/listUI")
	public String listUI(){
		return "resources/resourceList";
	}
	
	@RequestMapping("/listModules")
	@ResponseBody
	public List<Module> listModules(ModelMap mm){
		return (List<Module>) moduleService.findAll() ;
	}
}
