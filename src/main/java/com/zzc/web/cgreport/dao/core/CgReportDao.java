package com.zzc.web.cgreport.dao.core;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zzc.dao.annotation.Arguments;
import com.zzc.dao.annotation.MiniDao;

/**
 * 
 * @author zhangdaihao
 *
 */
@Repository("cgReportDao")
public interface CgReportDao{

	@Arguments("configId")
	List<Map<String,Object>> queryCgReportItems(String configId);
	
	@Arguments("id")
	Map queryCgReportMainConfig(String id);
}
