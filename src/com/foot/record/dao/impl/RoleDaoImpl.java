package com.foot.record.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.RoleDao;
import com.foot.record.entity.Role;
import com.foot.record.utils.FrameworkBeansName;

@Transactional
@Repository(FrameworkBeansName.ROLE_INFO_DAO_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
		// TODO Auto-generated constructor stub
	}

}
