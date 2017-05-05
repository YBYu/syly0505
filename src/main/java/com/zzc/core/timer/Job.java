package com.zzc.core.timer;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicDataSta;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.service.SystemService;
import com.zzc.webservice.ReadJson;

@Component
public class Job {
	@Autowired
	private SystemService systemService;
// 
//    @Scheduled(cron="*/10 * * * * *") 
//    public void s10(){
//        com.zzc.core.util.LogUtil.info("==== 十秒执行一次=======10s");
//    		 
//    	
//    }
//    
//    @Scheduled(cron="0 */1 * * * *") 
//    public void m1(){
//        org.jeecgframework.core.util.LogUtil.info("1m");
//    }
//    
//    /**
//     * 每天1点执行一次
//     * */
//   @Scheduled(cron="0 41 17 * * ?") 
//    public void oneOClockPerDay(){
//     System.out.println("test");
//     
//   }
   

    
    
}