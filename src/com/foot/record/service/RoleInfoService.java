package com.foot.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.foot.record.dao.RoleDao;
import com.foot.record.entity.Role;
import com.foot.record.utils.FrameworkBeansName;

@Service(FrameworkBeansName.ROLE_INFO_SERVICE_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RoleInfoService {
	private RoleDao roleDao;
	
	@Autowired
	public void setRoleDao(@Qualifier(FrameworkBeansName.ROLE_INFO_DAO_BEAN)RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public Role getRole(long id){
		return roleDao.find(id);
	}
	
	public List<Role> queryRoles(){
		return this.roleDao.queryList(null, "1=1", null, null);
	}
	
	
}
