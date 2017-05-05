package com.zzc.web.otherTravel.service;

import com.zzc.core.common.service.CommonService;
import com.zzc.web.system.pojo.base.TSUser;
/**
 * 
 *                  
 * @date: 2016年11月28日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: OtherTravelService.java
 * @Version: 1.0
 * @About: 其他涉旅单位服务类接口
 *
 */
public interface OtherTravelService extends CommonService {
	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);

}
