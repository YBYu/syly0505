package com.zzc.demo.plugin.service.test;


import com.zzc.core.common.service.CommonService;
import com.zzc.demo.plugin.entity.test.TFinanceEntity;
import com.zzc.demo.plugin.entity.test.TFinanceFilesEntity;

public interface TFinanceServiceI extends CommonService{

	void deleteFile(TFinanceFilesEntity file);

	void deleteFinance(TFinanceEntity finance);

}
