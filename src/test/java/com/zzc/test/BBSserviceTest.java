package com.zzc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzc.demo.crud.entity.BBStopicEntity;
import com.zzc.demo.crud.service.IBBSTopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml",
		"classpath:spring-mvc-hibernate.xml",
		})
public class BBSserviceTest {

	
	@Autowired
	IBBSTopicService bbsTopicService;
	
	@Test
	public void saveAndUpdate()
	{
		BBStopicEntity bbsTopicEntity = new BBStopicEntity();
		bbsTopicEntity.setId("223");
		bbsTopicEntity.setContent("23123");
		bbsTopicEntity.setPublishDatetime("2015-12-11 00:00:12");
		bbsTopicService.saveOrUpdate(bbsTopicEntity);
	}
	
}
