package com.foot.record.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.UserDao;
import com.foot.record.entity.User;
import com.foot.record.utils.FrameworkBeansName;

@Transactional
@Repository(FrameworkBeansName.USER_INFO_DAO_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	public UserDaoImpl(){
		super(User.class);
	}
}
