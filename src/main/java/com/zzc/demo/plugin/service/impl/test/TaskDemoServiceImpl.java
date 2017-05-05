package com.zzc.demo.plugin.service.impl.test;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.zzc.demo.plugin.service.test.TaskDemoServiceI;
@Service("taskDemoService")
public class TaskDemoServiceImpl implements TaskDemoServiceI {

	
	public void work() {
		com.zzc.core.util.LogUtil.info(new Date().getTime());
		com.zzc.core.util.LogUtil.info("----------任务测试-------");
	}

}
