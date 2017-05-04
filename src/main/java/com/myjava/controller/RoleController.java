package com.myjava.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myjava.entity.Role;
import com.myjava.service.RoleService;
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService ;
	
	@RequiresPermissions(value={"system:role:view"})
	@RequestMapping("/list")
	public String list(ModelMap mm){
		List<Role> roleList = (List<Role>) roleService.findAll() ;
		mm.put("roleList", roleList) ;
		return "role/roleList";
	}
	@RequestMapping("/listJson")
	@ResponseBody
	public List<Role> listJson(){
		return (List<Role>) roleService.findAll() ;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap mm){
		return "role/addUI" ;
	}
	@RequestMapping("/add")
	public String add(Role role){
		roleService.insert(role);
		return "redirect:list.action";
	}
	/**
	 * 修改页面
	 * @param id
	 * @param mm
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Long id , ModelMap mm){
		Role role = roleService.getById(id) ;
		mm.put("role", role) ;
		return "role/updateUI";
	}
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Role role){
		roleService.update(role);
		return "redirect:list.action";
	}
	@RequestMapping("/delete")
	public String delete(Long id){
		roleService.delete(id);
		return "redirect:list.action";
	}
	/**
	 * 设置角色的权限
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/setPerms")
	@ResponseBody
	public boolean setPerms(String ids , Long roleId){
		String[] permIds = ids.split(",") ;
		try {
			roleService.correlationPermissions(roleId, permIds);
		} catch (Exception e) {
			return false ;
		}
		return true;
	}
}	
