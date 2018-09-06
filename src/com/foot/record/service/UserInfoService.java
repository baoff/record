package com.foot.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.foot.record.dao.UserDao;
import com.foot.record.entity.User;
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
	
	
	

}
