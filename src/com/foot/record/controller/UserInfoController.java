package com.foot.record.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foot.record.base.BaseController;
import com.foot.record.entity.Role;
import com.foot.record.entity.User;
import com.foot.record.form.UserForm;
import com.foot.record.page.PageBean;
import com.foot.record.service.RoleInfoService;
import com.foot.record.service.UserInfoService;
import com.foot.record.utils.CheckMobile;
import com.foot.record.utils.FrameworkBeansName;


@Controller
@RequestMapping(FrameworkBeansName.CONTROLLER_USERINFO)
public class UserInfoController extends BaseController {

	private UserInfoService userInfoService;
	private RoleInfoService roleInfoService;

	@Autowired
	public void setUserService(@Qualifier(FrameworkBeansName.USER_INFO_SERVICE_BEAN)UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@Autowired
	public void setRoleInfoService(@Qualifier(FrameworkBeansName.ROLE_INFO_SERVICE_BEAN)RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=logincheck")
	public String checkLoginAction(@RequestParam(value="username", required=false) String username, @RequestParam(value="password", required=false) String password,
			ModelMap model, HttpServletRequest request) {
		boolean isFromMobile = false;
		HttpSession session = request.getSession();
		User user = null;
		   //检查是否已经记录访问方式（移动端或pc端）  
	    if(null==session.getAttribute("ua")){  
	        try{  
	            //获取ua，用来判断是否为移动端访问  
	            String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();    
	            if(null == userAgent){    
	                userAgent = "";    
	            }  
	            isFromMobile=CheckMobile.check(userAgent);  
	            //判断是否为移动端访问  
	            if(isFromMobile){  
	                session.setAttribute("ua","mobile");  
	            } else {  
	                session.setAttribute("ua","pc");  
	            }  
	        }catch(Exception e){}  
	    }else{  
	        isFromMobile=session.getAttribute("ua").equals("mobile");  
	    }
	    if(username == null || password == null){
	    	User currentuser = (User) session.getAttribute("currentuser");
	    	if(currentuser ==null ){
	    		if(isFromMobile){
					return "mLogin.jsp";
				}
				return "index.jsp";
	    	}else {
	    		user = currentuser;
	    	}
		}
	    
		if (username != null && password != null) {
			user = userInfoService.checkUser(username, password);
		}
		
			if (user == null) {
				session.setAttribute("loginIsErro", "1");
				if(isFromMobile){
					return "mLogin.jsp";
				}
				return "index.jsp";
			}
			String result = "";
//			Role role = roleInfoService.getRole(userToRole.getRoleId());
			//根据角色权限跳转地址
			if(isFromMobile){
				result ="mlist.jsp";
			}else {
//				if(role.getPermission().contains("update")){
					result = "recordinfo.ak?method=search_recordInfo";
//				}else {
//					result ="orderinfo.ak?method=condition_orderInfo_action";	
//				}
			}
//			user.setRole(role);
			// 机构
			user.setLastLoginTime(new Date());
			userInfoService.updataUser(user);
			session.setAttribute("currentuser", user);
			session.setAttribute("loginIsErro", "0");
			return result;
	}
	
	/**
	 * 添加用户页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=add_user_form")
	public String createUser(ModelMap model, HttpServletRequest request){
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		User user = new User();
		//加载权限
		List<Role> roles = this.roleInfoService.queryRoles();
		model.addAttribute("userInfoBean", user);
		model.addAttribute("roles", roles);
		return "addUser.jsp";
	}
	
	@RequestMapping(params = "method=add_userinfo_action")
	public String addUser(@ModelAttribute("userInfoBean") User from,ModelMap model,HttpServletRequest request){
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		return null;
	}
	
	
	/**
	 * 用户管理
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=search_userInfo")
	public String searchUser(@ModelAttribute("userInfoForm") UserForm form,ModelMap model, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		User currentuser = (User)session.getAttribute("currentuser");
		if(currentuser == null){
			return "index.jsp";
		}
		PageBean page = new PageBean();
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		List<User> users = this.userInfoService.getUsers(form, page);
		model.addAttribute("page", page);
		model.addAttribute("userLists", users);
		model.addAttribute("userInfoForm", form);
		return "userManage.jsp";
	}
	
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("currentuser", null);
		return "index.jsp";
	}

	

}
