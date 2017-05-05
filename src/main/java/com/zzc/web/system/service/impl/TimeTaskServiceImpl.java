package com.zzc.web.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.web.system.service.TimeTaskServiceI;

@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}