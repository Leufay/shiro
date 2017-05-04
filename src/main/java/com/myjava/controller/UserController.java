package com.myjava.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myjava.entity.User;
import com.myjava.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService ;
	@RequiresPermissions(value={"system:user:view"})
	@RequestMapping("/list")
	public String list(ModelMap mm){
		List<User> userList = (List<User>) userService.findAll() ;
		mm.put("userList", userList) ;
		return "user/userList";
	}
	/**
	 * 修改页面
	 * @param id
	 * @param mm
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Long id , ModelMap mm){
		User user = userService.getById(id) ;
		mm.put("user", user) ;
		return "user/updateUI";
	}
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public String update(User user){
		userService.update(user);
		return "redirect:list.action";
	}
	/**
	 * 增加页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "user/addUI";
	}
	/**
	 * 增加
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	public String add(User user){
		userService.insert(user);
		return "redirect:list.action";
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Long id){
		userService.deleteById(id);
		return "redirect:list.action";
	}
	@RequestMapping("/setRoles")
	@ResponseBody
	public boolean setRoles(String ids,Long userId){
		String[] roleIds = ids.split(",") ;
		try{
			userService.correlationRoles(userId, roleIds);
		}catch(Exception e){
			e.printStackTrace();
			return false ;
		}
		return true;
	}
}
