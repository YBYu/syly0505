package com.zzc.app.otherTravel.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzc.app.login.entity.APPOtherTravel;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/basicinfocontroller")
public class BasicInfoController {

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 基本信息填报
	 * 
	 * @param request
	 * @param response
	 * @return 成功标志
	 */
	@RequestMapping(params = "fillBasicInfo")
	@ResponseBody
	public String fillBasicInfo(HttpServletRequest request,
			HttpServletResponse response) {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String tip = "success";

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String msg = request.getParameter("param");
		String userId = request.getParameter("userId");

		APPOtherTravel appTravelInfo = null;

		try {
			appTravelInfo = JSON.parseObject(msg, APPOtherTravel.class);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "数据转化失败!";
			return tip;
		}

		try {
			OtherTravelInfo otherTravelInfo = systemService.get(
					OtherTravelInfo.class, userId);
			// 企业名称
			otherTravelInfo.setName(appTravelInfo.getName());
			// 负责人
			otherTravelInfo.setPrincipal(appTravelInfo.getPrincipal());
			// 联系电话
			otherTravelInfo.setPhone(appTravelInfo.getPhone());
			// 详细地址
			otherTravelInfo.setAddress(appTravelInfo.getAddress());
			// 邮编
			otherTravelInfo.setPostalcode(appTravelInfo.getPostalcode());
			otherTravelInfo.setMobile(appTravelInfo.getTel());
			/*
			 * String county = appTravelInfo.getArea(); switch (county) { case
			 * "市辖区": county = "0"; break; case "崖州区": county = "1"; break; case
			 * "海棠区": county = "2"; break; case "天涯区": county = "3"; break; case
			 * "吉阳区": county = "4"; break; } otherTravelInfo.setArea(county)
			 */
			systemService.saveOrUpdate(otherTravelInfo);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "操作失败";
		}
		return tip;
	}

}
