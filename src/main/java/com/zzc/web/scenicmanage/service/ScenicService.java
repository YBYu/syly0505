package com.zzc.web.scenicmanage.service;



import com.zzc.core.common.service.CommonService;
import com.zzc.web.scenicmanage.entity.Scenic;
import com.zzc.web.scenicmanage.entity.ScenicData;



public interface ScenicService extends CommonService{
	public void saveScenic(Scenic scenic);
	public void deleteScenic(String id);
	public void saveScenicData(ScenicData scenicData);

}
