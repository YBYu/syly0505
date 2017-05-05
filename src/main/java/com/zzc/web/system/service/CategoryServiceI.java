package com.zzc.web.system.service;

import com.zzc.core.common.service.CommonService;
import com.zzc.web.system.pojo.base.TSCategoryEntity;

public interface CategoryServiceI extends CommonService{
	/**
	 * 保存分类管理
	 * @param category
	 */
	void saveCategory(TSCategoryEntity category);

}
