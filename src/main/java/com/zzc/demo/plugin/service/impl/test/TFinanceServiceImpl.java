package com.zzc.demo.plugin.service.impl.test;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.core.util.ContextHolderUtils;
import com.zzc.core.util.FileUtils;
import com.zzc.core.util.ResourceUtil;
import com.zzc.demo.plugin.entity.test.TFinanceEntity;
import com.zzc.demo.plugin.entity.test.TFinanceFilesEntity;
import com.zzc.demo.plugin.service.test.TFinanceServiceI;
import com.zzc.web.system.pojo.base.TSAttachment;

@Service("tFinanceService")
@Transactional
public class TFinanceServiceImpl extends CommonServiceImpl implements TFinanceServiceI {

	/**
	 * 附件删除
	 */
	public void deleteFile(TFinanceFilesEntity file) {
		
		//[1].删除附件
		String sql = "select * from t_s_attachment where id = ?";
		Map<String, Object> attachmentMap = commonDao.findOneForJdbc(sql, file.getId());
		//附件相对路径
		String realpath = (String) attachmentMap.get("realpath");
		String fileName = FileUtils.getFilePrefix2(realpath);
		
		//获取部署项目绝对地址
		String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
		FileUtils.delete(realPath+realpath);
		FileUtils.delete(realPath+fileName+".pdf");
		FileUtils.delete(realPath+fileName+".swf");
		//[2].删除数据
		commonDao.delete(file);
	}

	/**
	 * 资金管理删除
	 */
	public void deleteFinance(TFinanceEntity finance) {
		//[1].上传附件删除
		String attach_sql = "select * from t_s_attachment where id in (select id from t_finance_files where financeId = ?)";
		List<Map<String, Object>> attachmentList = commonDao.findForJdbc(attach_sql, finance.getId());
		for(Map<String, Object> map:attachmentList){
			//附件相对路径
			String realpath = (String) map.get("realpath");
			String fileName = FileUtils.getFilePrefix2(realpath);
			
			//获取部署项目绝对地址
			String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
			FileUtils.delete(realPath+realpath);
			FileUtils.delete(realPath+fileName+".pdf");
			FileUtils.delete(realPath+fileName+".swf");
		}
		//[2].删除资金管理
		commonDao.delete(finance);
	}
	
}