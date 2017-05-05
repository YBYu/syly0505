package com.zzc.web.cgform.dao.config;


import org.springframework.stereotype.Repository;

import com.zzc.dao.annotation.Arguments;
import com.zzc.dao.annotation.MiniDao;
import com.zzc.web.cgform.entity.config.CgFormHeadEntity;


/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 * @author 赵俊夫
 * @date Aug 24, 2013 11:33:33 AM
 * @version V1.0
 */
@Repository("cgFormVersionDao")
public interface CgFormVersionDao {
	@Arguments("tableName")
	public String  getCgFormVersionByTableName(String tableName);
	@Arguments("id")
	public String  getCgFormVersionById(String id);
	@Arguments({"newVersion","formId"})
	public void  updateVersion(String newVersion,String formId);
	
	@Arguments({"id"})
	public CgFormHeadEntity  getCgFormById(String id);
}
