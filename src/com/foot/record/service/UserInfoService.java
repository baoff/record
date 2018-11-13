package com.foot.record.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.foot.record.dao.UserDao;
import com.foot.record.entity.User;
import com.foot.record.form.UserForm;
import com.foot.record.page.PageBean;
import com.foot.record.utils.FrameworkBeansName;
import com.foot.record.utils.RecordMessageDi;

@Service(FrameworkBeansName.USER_INFO_SERVICE_BEAN)
@Scope("prototype")
public class UserInfoService {
	
	private UserDao userDao ;

	public UserDao getUserDao() {
		return userDao;
	}
	
	@Autowired
	public void setUserDao(@Qualifier(FrameworkBeansName.USER_INFO_DAO_BEAN)UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void addUser(User user){
		this.userDao.save(user);
	}
	
	public void deleteUser(long id){
		this.userDao.delete(id);
	}
	
	public void updataUser(User user){
		this.userDao.update(user);
	}
	
	public User findUser(long id){
		return this.userDao.find(id);
	}
	
	public User checkUser(String username,String password){
		String propertyName="loginName";
		Object value=username;
		User user=userDao.getBy(propertyName, value);
		if(user != null && (user.getPassword().equals(RecordMessageDi.MD5Encoder(password)))){
			return user;
		}
		return null;
	}
	
	public List<User> getUsers(UserForm form ,PageBean pagebean){
		String loginName = form.getLoginName();
		String nickName = form.getNickName();
		String email = form.getEmail();
		String phone = form.getPhone();
		String querycount = "select count(*) from User where ";
		String condition = "1=1";
		List<String> list = new ArrayList<String>();
		if(loginName != null && loginName.trim().length() >0){
			condition +=  " and loginName like ?";
			list.add(loginName);
		}
		if(nickName != null && nickName.trim().length() >0){
			condition += " and nickName like ?";
			list.add(nickName);
		}
		if(email != null && email.trim().length() >0){
			condition += " and email like ?";
			list.add(email);
		}
		if(phone != null && phone.trim().length()>0){
			condition += " and phoneNum like ?";
			list.add(phone);
		}
		
		List<User> users = this.userDao.queryList(querycount+ condition, condition, list.toArray(), pagebean);
		return users;
	}
	
	
	

}
