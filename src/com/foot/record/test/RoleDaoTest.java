package com.foot.record.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foot.record.dao.RoleDao;
import com.foot.record.entity.Role;

public class RoleDaoTest {
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		RoleDao dao = (RoleDao) ctx.getBean("roleInfoDaoBean");
		Role role = new Role();
		role.setName("manage");
		role.setDescription("π‹¿Ì‘±");
		dao.save(role);
		System.out.println("------");
	}
}
