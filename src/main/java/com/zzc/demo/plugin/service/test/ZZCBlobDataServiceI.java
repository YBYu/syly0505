package com.zzc.demo.plugin.service.test;

import org.springframework.web.multipart.MultipartFile;

import com.zzc.core.common.service.CommonService;

public interface ZZCBlobDataServiceI extends CommonService{
	public void saveObj(String documentTitle, MultipartFile file);

}
