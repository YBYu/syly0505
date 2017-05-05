package com.zzc.app.notice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zzc.app.notice.entity.InformEntity;
import com.zzc.web.notice.entity.Notice;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appnoticeController")
public class AppNoticeController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * 获取通知详情
	 * 
	 * @param request
	 * @param userId
	 * @return 通知json
	 */
	@RequestMapping(params = "notice", method = RequestMethod.GET)
	@ResponseBody
	public String getNotice(HttpServletRequest request, String userId) {
		Map<String, Object> roleMap = systemService
				.findOneForJdbc(
						"select * from t_s_role t where t.id=(select tr.roleid from t_s_role_user tr where tr.userid =?)",
						userId);
		String role = roleMap.get("rolename").toString();
		String response = null;
		String noticerange = null;
		if (role.contains("景区")) {
			noticerange = "1";
		} else if (role.contains("旅行社")) {
			noticerange = "2";
		} else if (role.equals("星级酒店数据上报员")) {
			noticerange = "3";
		} else if (role.contains("星级酒店")) {
			noticerange = "4";
		} else if (role.contains("机场") || role.contains("动车")
				|| role.contains("高尔夫") || role.contains("游艇")
				|| role.contains("空中飞行")) {
			noticerange = "5";
		}
		List<Notice> noticelist = new ArrayList<Notice>();
		List<InformEntity> appnoticelist = new ArrayList<InformEntity>();
		if (noticerange != null) {
			noticelist = systemService
					.findHql("from Notice n where n.range='0' or n.range=?",
							noticerange);
		} else {
			noticelist = systemService.findHql("from Notice");
		}
		Notice notice[] = new Notice[noticelist.size()];
		InformEntity informEntity[] = new InformEntity[noticelist.size()];
		for (int i = 0; i < noticelist.size(); i++) {
			notice[i] = noticelist.get(i);
			informEntity[i] = new InformEntity();
			informEntity[i].setId(notice[i].getId());
			informEntity[i].setInformContent(notice[i].getAppContent());
			informEntity[i].setInformFrom(notice[i].getSource());
			informEntity[i].setInformName(notice[i].getTitle());
			informEntity[i].setInformTime(String.valueOf(notice[i]
					.getCreateDate() == null ? "" : notice[i].getCreateDate()));
			if (notice[i].getRange().equals("0")) {
				notice[i].setRange("全部企业");
			} else if (notice[i].getRange().equals("1")) {
				notice[i].setRange("景区");
			} else if (notice[i].getRange().equals("2")) {
				notice[i].setRange("旅行社");
			} else if (notice[i].getRange().equals("3")) {
				notice[i].setRange("星级酒店");
			} else if (notice[i].getRange().equals("4")) {
				notice[i].setRange("所有酒店");
			} else if (notice[i].getRange().equals("5")) {
				notice[i].setRange("其他涉旅企业");
			}
			informEntity[i].setRange(notice[i].getRange());
			informEntity[i].setReleaser(notice[i].getAnnouncer());
			appnoticelist.add(informEntity[i]);
		}
		String giveapp = "|success";
		response = JSON.toJSONString(appnoticelist) + giveapp;
		return response;
	}

	/**
	 * 返回通知视图
	 * 
	 * @param request
	 * @return 通知视图
	 */
	@RequestMapping(params = "detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request) {
		String id = request.getParameter("id");
		Notice notice = systemService.getEntity(Notice.class, id);
		request.setAttribute("notice", notice);
		return new ModelAndView("notice/appnotice").addObject("notice", notice);
		/*
		 * return
		 * "http://192.168.0.106:8080/syly/webpage/notice/appnotice.jsp?id="+id;
		 */
	}
}
