package com.zzc.demo.plugin.service.impl.test;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.demo.plugin.service.test.ZZCDemoServiceI;


@Service("jeecgDemoService")
@Transactional
public class ZZCDemoServiceImpl extends CommonServiceImpl implements ZZCDemoServiceI {
	
}
