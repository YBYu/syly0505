package com.zzc.web.system.service;

import java.util.List;


import com.zzc.core.common.service.CommonService;
import com.zzc.web.system.pojo.base.TSAttachment;

/**
 * 
 * @author  张代浩
 *
 */
public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
