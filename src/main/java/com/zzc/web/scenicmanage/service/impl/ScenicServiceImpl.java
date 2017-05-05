package com.zzc.web.scenicmanage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.core.common.service.impl.CommonServiceImpl;

import com.zzc.web.scenicmanage.entity.Scenic;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.service.ScenicService;

@Service("scenicService")
@Transactional
public class ScenicServiceImpl extends CommonServiceImpl implements ScenicService {




	public void saveScenic(Scenic scenic){
	//	Scenic sc = new Scenic();
		commonDao.save(scenic);
		//this.save(scenic);
		
	}

	@Override
	public void deleteScenic(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveScenicData(ScenicData scenicData) {
		// TODO Auto-generated method stub
		commonDao.save(scenicData);
	}

 	
}
