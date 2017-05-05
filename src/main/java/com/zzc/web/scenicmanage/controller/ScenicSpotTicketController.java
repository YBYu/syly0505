package com.zzc.web.scenicmanage.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicSpotTicket;
import com.zzc.web.system.service.SystemService;

@Scope("prototype")
@Controller
@RequestMapping("/scenicSpotTicketController")
public class ScenicSpotTicketController {
	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "delete")
	@ResponseBody
	public void delete(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id").toString();
		systemService.deleteEntityById(ScenicSpotTicket.class, id);
	}

	@RequestMapping(params = "add")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res) {
		String sid = (String)req.getParameter("sid");
		return new ModelAndView("scenic/scenicTicketAdd").addObject("sid", sid);
	}

	@RequestMapping(params = "saveOrUpdate")
	@ResponseBody
	public AjaxJson saveOrUpdateScenic(HttpServletRequest request,
			ScenicSpotTicket scenicSpotTicket) {
		AjaxJson j = new AjaxJson();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		scenicSpotTicket.setId(id);
		String sid=(String)request.getParameter("sid");
		ScenicData scenicData=systemService.getEntity(ScenicData.class, sid);
		scenicSpotTicket.setScenicData(scenicData);
		systemService.save(scenicSpotTicket);
		/*
		 * systemService.saveOrUpdate(scenicSpotTicket); ScenicSpotTicket
		 * scenicTicket = systemService.get(ScenicSpotTicket.class, id);
		 * 
		 * systemService.saveOrUpdate(scenicSpotTicket);
		 */

		return j;
	}
	

}
