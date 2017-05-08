package com.myjava.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myjava.entity.Permission;
import com.myjava.service.PermissionService;

@Controller
@RequestMapping("/perm")
public class PermissionController {
	@Autowired
	private PermissionService permService ;
	
	/**
	 * 只有system:perm:view权限的人才能访问
	 * 权限列表		
	 * @param mm
	 * @return
	 */
	@RequiresPermissions("system:perm:view")							
	@RequestMapping("/list")
	public String list(ModelMap mm){
		List<Permission> permissions = permService.findAll() ;
		mm.put("permList", permissions) ;
		return "perm/permList";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "perm/addUI";
	}
	
	@RequestMapping("/add")
	public String add(Permission perm){
		permService.insert(perm);
		return "redirect:list.action";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Long id ,ModelMap mm){
		mm.put("perm", permService.get(id)) ;
		return "perm/updateUI";
	}
	
	@RequestMapping("/update")
	public String update(Permission perm){
		permService.update(perm);
		return "redirect:list.action";
	}
	
	@RequestMapping("/listJson")
	@ResponseBody
	public List<Permission> listJson(){
		return permService.findAll() ;
	}
	@RequestMapping("/delete")
	public String delete(Long id){
		permService.delete(id);
		return "redirect:list.action";
	}
	
	/**
	 * 
	 * @param permUrls 权限标识，如system:user:add
	 * @param permId
	 * @return
	 */
	@RequestMapping("/setResources")
	@ResponseBody
	public String setResources(@RequestParam("permUrls")String[] permUrls, Long permId){
		permService.setResources(permUrls, permId);
		return "" ;
	}
}
