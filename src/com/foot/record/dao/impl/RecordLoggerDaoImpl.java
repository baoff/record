package com.foot.record.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.RecordLoggerDao;
import com.foot.record.entity.RecordLogger;
import com.foot.record.utils.FrameworkBeansName;

@Transactional
@Repository(FrameworkBeansName.LOGGER_INFO_DAO_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RecordLoggerDaoImpl extends GenericDaoImpl<RecordLogger> implements RecordLoggerDao{

	public RecordLoggerDaoImpl() {
		super(RecordLogger.class);
		// TODO Auto-generated constructor stub
	}

}
