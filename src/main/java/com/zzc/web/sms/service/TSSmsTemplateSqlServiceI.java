package com.zzc.web.sms.service;
import com.zzc.core.common.service.CommonService;
import com.zzc.web.sms.entity.TSSmsTemplateSqlEntity;

import java.io.Serializable;

public interface TSSmsTemplateSqlServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSSmsTemplateSqlEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSSmsTemplateSqlEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSSmsTemplateSqlEntity t);
}
