package com.zzc.web.cgform.dao.config;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zzc.dao.annotation.Arguments;
import com.zzc.dao.annotation.MiniDao;

/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 * @author 张代浩
 * @date Aug 24, 2013 11:33:33 AM
 * @version V1.0
 */
@Repository("cgFormFieldDao")
public interface CgFormFieldDao {
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormFieldByTableName(String tableName);
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormHiddenFieldByTableName(String tableName);
	
}
