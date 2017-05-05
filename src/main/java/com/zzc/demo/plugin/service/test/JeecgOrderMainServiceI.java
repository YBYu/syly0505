package com.zzc.demo.plugin.service.test;

import java.util.List;

import com.zzc.core.common.service.CommonService;
import com.zzc.demo.plugin.entity.test.ZZCOrderCustomEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderMainEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderProductEntity;


public interface JeecgOrderMainServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZZCOrderMainEntity jeecgOrderMain,List<ZZCOrderProductEntity> jeecgOrderProducList,List<ZZCOrderCustomEntity> jeecgOrderCustomList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZZCOrderMainEntity jeecgOrderMain,List<ZZCOrderProductEntity> jeecgOrderProducList,List<ZZCOrderCustomEntity> jeecgOrderCustomList,boolean jeecgOrderCustomShow) ;
	public void delMain (ZZCOrderMainEntity jeecgOrderMain);
}
