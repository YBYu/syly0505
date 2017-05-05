package com.zzc.demo.plugin.service.test;


import org.springframework.web.multipart.MultipartFile;

import com.zzc.core.common.service.CommonService;
import com.zzc.demo.plugin.entity.test.WebOfficeEntity;

public interface WebOfficeServiceI extends CommonService{
	public void saveObj(WebOfficeEntity docObj, MultipartFile file);
}
