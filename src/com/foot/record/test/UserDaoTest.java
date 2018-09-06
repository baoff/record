package com.foot.record.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foot.record.dao.UserDao;
import com.foot.record.entity.Role;
import com.foot.record.entity.User;
import com.foot.record.utils.RecordMessageDi;

public class UserDaoTest {
	
	
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDao dao =  (UserDao) ctx.getBean("userInfoDaoBean");
//		User user = new User();
//		user.setLoginName("bff");
//		user.setNickname("±«·²·²");
//		String password = "baoff";
//		password = RecordMessageDi.MD5Encoder(password);
//		user.setPassword(password);
//		user.setOrgName("¿ª·¢²¿");
//		dao.save(user);
		User user = dao.getBy("loginName", "bff");
		System.out.println(user.getId());
		System.out.println(user.getRole().getId());
	}
}
