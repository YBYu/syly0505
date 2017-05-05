package com.zzc.demo.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.demo.crud.entity.BBStopicEntity;
import com.zzc.demo.crud.service.IBBSTopicService;

@Controller
@RequestMapping("/bbsTopic")
public class BBSTopicController extends BaseController{
	
	@Autowired
	IBBSTopicService bbsTopicService;
	
    @RequestMapping(params= "add" )
    public ModelAndView saveBbsTopic(ModelAndView model)
    {
    	BBStopicEntity bbsTopicEntity = new BBStopicEntity();
    	bbsTopicEntity.setId("123");
    	bbsTopicService.saveOrUpdate(bbsTopicEntity);
    	model.setViewName("system/user/userList2");
		return model;
    }
}
