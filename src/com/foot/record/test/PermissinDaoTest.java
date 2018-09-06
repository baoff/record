package com.foot.record.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foot.record.dao.PermissionDao;
import com.foot.record.entity.Permission;
import com.foot.record.entity.Role;

public class PermissinDaoTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		PermissionDao dao = (PermissionDao) ctx.getBean("permissionInfoDaoBean");
		Permission p = new Permission();
		p.setPage("search");
		p.setOperate("search");
		
	}
}	
