package com.zzc.web.system.service;

import java.util.List;

import com.zzc.core.common.service.CommonService;
import com.zzc.web.system.pojo.base.DynamicDataSourceEntity;

public interface DynamicDataSourceServiceI extends CommonService{
	
	public List<DynamicDataSourceEntity> initDynamicDataSource();
	
	public void refleshCache();
}
