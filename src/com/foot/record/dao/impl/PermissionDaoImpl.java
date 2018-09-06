package com.foot.record.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.PermissionDao;
import com.foot.record.entity.Permission;
import com.foot.record.page.PageBean;
import com.foot.record.utils.FrameworkBeansName;

@Transactional
@Repository(FrameworkBeansName.PERMISSION_INFO_DAO_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao{
	public PermissionDaoImpl(){
		super(Permission.class);
	}

}
