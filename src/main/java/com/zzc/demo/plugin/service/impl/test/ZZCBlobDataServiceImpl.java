package com.zzc.demo.plugin.service.impl.test;

import java.io.IOException;
import java.sql.Blob;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.hibernate.LobHelper;

import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.demo.plugin.entity.test.BlobDataEntity;
import com.zzc.demo.plugin.service.test.ZZCBlobDataServiceI;

@Service("jeecgBlobDataService")
@Transactional
public class ZZCBlobDataServiceImpl extends CommonServiceImpl implements ZZCBlobDataServiceI {
	
	public void saveObj(String documentTitle, MultipartFile file) {
		BlobDataEntity obj = new BlobDataEntity();
		LobHelper lobHelper = commonDao.getSession().getLobHelper();
		Blob data;
		try {
			data = lobHelper.createBlob(file.getInputStream(), 0);
			obj.setAttachmentcontent(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		obj.setAttachmenttitle(documentTitle);
		String sFileName = file.getOriginalFilename();
		int iPos = sFileName.lastIndexOf('.');
		if (iPos >= 0) {
			obj.setExtend(sFileName.substring(iPos+1));
		}
		super.save(obj);
	}
}