package com.foot.record.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.RecordDao;
import com.foot.record.entity.Record;
import com.foot.record.utils.FrameworkBeansName;

@Transactional
@Repository(FrameworkBeansName.RECORD_INFO_DAO_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RecordDaoImpl extends GenericDaoImpl<Record> implements RecordDao{

	public RecordDaoImpl() {
		super(Record.class);
	}

}
