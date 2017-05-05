package com.zzc.web.sms.util.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzc.web.sms.service.TSSmsServiceI;



/**
 * 
 * @ClassName:SmsSendTask 所有信息的发送定时任务类
 * @Description: TODO
 * @author Comsys-skyCc cmzcheng@gmail.com
 * @date 2014-11-13 下午5:06:34
 * 
 */
@Service("smsSendTask")
public class SmsSendTask {
	
	@Autowired
	private TSSmsServiceI tSSmsService;
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public void run() {
		long start = System.currentTimeMillis();
		com.zzc.core.util.LogUtil.info("===================消息中间件定时任务开始===================");
		try {
			tSSmsService.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		com.zzc.core.util.LogUtil.info("===================消息中间件定时任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		com.zzc.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}
}
