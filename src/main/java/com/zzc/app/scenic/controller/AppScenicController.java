package com.zzc.app.scenic.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzc.app.scenic.entity.EnterpriseBaseInfoEntity;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicDataSta;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appscenicController")
public class AppScenicController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 基本信息
	 */

	@RequestMapping(params = "scenic", method = RequestMethod.GET)
	@ResponseBody
	public String scenic(HttpServletRequest request, HttpServletResponse res,
			String userId) throws ParseException {

		/** 设置响应头允许ajax跨域访问 **/
		res.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		res.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String response = null;
		List<ScenicDataSta> scenicDataStalist = systemService.findHql(
				"from ScenicDataSta s  where s.userId=?", userId);
		if (scenicDataStalist.size() > 0) {
			ScenicDataSta scenicDataSta = scenicDataStalist.get(0);
			EnterpriseBaseInfoEntity enterpriseBaseInfoEntity = new EnterpriseBaseInfoEntity();
			enterpriseBaseInfoEntity
					.setEnterpriseName(scenicDataSta.getName() == null ? ""
							: scenicDataSta.getName());
			enterpriseBaseInfoEntity.setEnterpriseAddress(scenicDataSta
					.getAddress() == null ? "" : scenicDataSta.getAddress());
			enterpriseBaseInfoEntity
					.setEnterpriseArea(scenicDataSta.getArea() == null ? ""
							: scenicDataSta.getArea());
			enterpriseBaseInfoEntity
					.setEnterpriseCode(scenicDataSta.getCode() == null ? ""
							: scenicDataSta.getCode());
			enterpriseBaseInfoEntity.setEnterpriseType(scenicDataSta
					.getScenictype() == null ? "" : scenicDataSta
					.getScenictype());
			enterpriseBaseInfoEntity
					.setProvince(scenicDataSta.getProvince() == null ? ""
							: scenicDataSta.getProvince());
			enterpriseBaseInfoEntity
					.setCity(scenicDataSta.getCity() == null ? ""
							: scenicDataSta.getCity());
			enterpriseBaseInfoEntity
					.setZipcode(scenicDataSta.getZipcode() == null ? ""
							: scenicDataSta.getZipcode());
			enterpriseBaseInfoEntity.setOrgproperty(getOrgproperty(String
					.valueOf(scenicDataSta.getOrgproperty() == null ? ""
							: scenicDataSta.getOrgproperty())));
			enterpriseBaseInfoEntity.setUrl(scenicDataSta.getUrl() == null ? ""
					: scenicDataSta.getUrl());
			enterpriseBaseInfoEntity.setConsultphone(scenicDataSta
					.getConsultphone() == null ? "" : scenicDataSta
					.getConsultphone());
			enterpriseBaseInfoEntity.setScenictype(scenicDataSta
					.getScenictype() == null ? "" : scenicDataSta
					.getScenictype());
			enterpriseBaseInfoEntity
					.setOpentime(scenicDataSta.getOpentime() == null ? ""
							: scenicDataSta.getOpentime().replace("-", ""));
			enterpriseBaseInfoEntity.setDaylimit(String.valueOf(scenicDataSta
					.getDaylimit() == null ? "" : scenicDataSta.getDaylimit()));
			enterpriseBaseInfoEntity.setAcreage(String.valueOf(scenicDataSta
					.getAcreage() == null ? "" : scenicDataSta.getAcreage()));
			enterpriseBaseInfoEntity.setInvestunit(scenicDataSta
					.getInvestunit() == null ? "" : scenicDataSta
					.getInvestunit());
			enterpriseBaseInfoEntity.setSuperiorunit(scenicDataSta
					.getSuperiorunit() == null ? "" : scenicDataSta
					.getSuperiorunit());
			enterpriseBaseInfoEntity
					.setLevel(scenicDataSta.getLevel() == null ? ""
							: scenicDataSta.getLevel());
			enterpriseBaseInfoEntity
					.setLeveldate(scenicDataSta.getLeveldate() == null ? ""
							: scenicDataSta.getLeveldate().replace("-", ""));
			enterpriseBaseInfoEntity.setChargename(scenicDataSta
					.getChargename() == null ? "" : scenicDataSta
					.getChargename());
			enterpriseBaseInfoEntity.setChargeemail(scenicDataSta
					.getChargeemail() == null ? "" : scenicDataSta
					.getChargeemail());
			enterpriseBaseInfoEntity.setChargephone(scenicDataSta
					.getChargephone() == null ? "" : scenicDataSta
					.getChargephone());
			enterpriseBaseInfoEntity.setInformantname(scenicDataSta
					.getInformantname() == null ? "" : scenicDataSta
					.getInformantname());
			enterpriseBaseInfoEntity.setInformantemail(scenicDataSta
					.getInformantemail() == null ? "" : scenicDataSta
					.getInformantemail());
			enterpriseBaseInfoEntity.setInformantphone(scenicDataSta
					.getInformantphone() == null ? "" : scenicDataSta
					.getInformantphone());
			enterpriseBaseInfoEntity.setInformantqq(scenicDataSta
					.getInformantqq() == null ? "" : scenicDataSta
					.getInformantqq());
			enterpriseBaseInfoEntity.setScenictitle(scenicDataSta
					.getScenictitle() == null ? "" : scenicDataSta
					.getScenictitle());
			enterpriseBaseInfoEntity
					.setBayType(scenicDataSta.getBayType() == null ? ""
							: scenicDataSta.getBayType());
			enterpriseBaseInfoEntity
					.setRemarks(scenicDataSta.getRemarks() == null ? ""
							: scenicDataSta.getRemarks());
			String status = ScenicWeekController.getstatus(scenicDataSta
					.getStatus());
			enterpriseBaseInfoEntity.setStatus(status);
			response = JSON.toJSONString(enterpriseBaseInfoEntity);
		}
		return response;

	}

	/**
	 * 正式基本信息
	 */

	@RequestMapping(params = "newscenic", method = RequestMethod.GET)
	@ResponseBody
	public EnterpriseBaseInfoEntity newscenic(HttpServletRequest request,
			HttpServletResponse res, String userId) throws ParseException {

		/** 设置响应头允许ajax跨域访问 **/
		res.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		res.setHeader("Access-Control-Allow-Methods", "GET,POST");
		EnterpriseBaseInfoEntity enterpriseBaseInfoEntity = new EnterpriseBaseInfoEntity();
		// String response = null;
		List<ScenicData> scenicDatalist = systemService.findHql(
				"from ScenicData s  where s.userId=?", userId);
		if (scenicDatalist.size() > 0) {
			ScenicData scenicData = scenicDatalist.get(0);

			enterpriseBaseInfoEntity
					.setEnterpriseName(scenicData.getName() == null ? ""
							: scenicData.getName());
			enterpriseBaseInfoEntity.setEnterpriseAddress(scenicData
					.getAddress() == null ? "" : scenicData.getAddress());
			enterpriseBaseInfoEntity
					.setEnterpriseArea(scenicData.getArea() == null ? ""
							: scenicData.getArea());
			enterpriseBaseInfoEntity
					.setEnterpriseCode(scenicData.getCode() == null ? ""
							: scenicData.getCode());
			enterpriseBaseInfoEntity.setEnterpriseType(scenicData
					.getScenictype() == null ? "" : scenicData.getScenictype());
			enterpriseBaseInfoEntity
					.setProvince(scenicData.getProvince() == null ? ""
							: scenicData.getProvince());
			enterpriseBaseInfoEntity.setCity(scenicData.getCity() == null ? ""
					: scenicData.getCity());
			enterpriseBaseInfoEntity
					.setZipcode(scenicData.getZipcode() == null ? ""
							: scenicData.getZipcode());
			enterpriseBaseInfoEntity.setOrgproperty(getOrgproperty(String
					.valueOf(scenicData.getOrgproperty() == null ? ""
							: scenicData.getOrgproperty())));
			enterpriseBaseInfoEntity.setUrl(scenicData.getUrl() == null ? ""
					: scenicData.getUrl());
			enterpriseBaseInfoEntity.setConsultphone(scenicData
					.getConsultphone() == null ? "" : scenicData
					.getConsultphone());
			enterpriseBaseInfoEntity
					.setScenictype(scenicData.getScenictype() == null ? ""
							: scenicData.getScenictype());
			enterpriseBaseInfoEntity
					.setOpentime(scenicData.getOpentime() == null ? ""
							: scenicData.getOpentime().replace("-", ""));
			enterpriseBaseInfoEntity.setDaylimit(String.valueOf(scenicData
					.getDaylimit() == null ? "" : scenicData.getDaylimit()));
			enterpriseBaseInfoEntity.setAcreage(String.valueOf(scenicData
					.getAcreage() == null ? "" : scenicData.getAcreage()));
			enterpriseBaseInfoEntity
					.setInvestunit(scenicData.getInvestunit() == null ? ""
							: scenicData.getInvestunit());
			enterpriseBaseInfoEntity.setSuperiorunit(scenicData
					.getSuperiorunit() == null ? "" : scenicData
					.getSuperiorunit());
			enterpriseBaseInfoEntity
					.setLevel(scenicData.getLevel() == null ? "" : scenicData
							.getLevel());
			enterpriseBaseInfoEntity
					.setLeveldate(scenicData.getLeveldate() == null ? ""
							: scenicData.getLeveldate().replace("-", ""));
			enterpriseBaseInfoEntity
					.setChargename(scenicData.getChargename() == null ? ""
							: scenicData.getChargename());
			enterpriseBaseInfoEntity
					.setChargeemail(scenicData.getChargeemail() == null ? ""
							: scenicData.getChargeemail());
			enterpriseBaseInfoEntity
					.setChargephone(scenicData.getChargephone() == null ? ""
							: scenicData.getChargephone());
			enterpriseBaseInfoEntity.setInformantname(scenicData
					.getInformantname() == null ? "" : scenicData
					.getInformantname());
			enterpriseBaseInfoEntity.setInformantemail(scenicData
					.getInformantemail() == null ? "" : scenicData
					.getInformantemail());
			enterpriseBaseInfoEntity.setInformantphone(scenicData
					.getInformantphone() == null ? "" : scenicData
					.getInformantphone());
			enterpriseBaseInfoEntity
					.setInformantqq(scenicData.getInformantqq() == null ? ""
							: scenicData.getInformantqq());
			enterpriseBaseInfoEntity
					.setScenictitle(scenicData.getScenictitle() == null ? ""
							: scenicData.getScenictitle());
			enterpriseBaseInfoEntity
					.setBayType(scenicData.getBayType() == null ? ""
							: scenicData.getBayType());
			enterpriseBaseInfoEntity
					.setRemarks(scenicData.getRemarks() == null ? ""
							: scenicData.getRemarks());
			enterpriseBaseInfoEntity
					.setStatus(scenicData.getStatus() == null ? "" : scenicData
							.getStatus());
			/* JSON.toJSONString(enterpriseBaseInfoEntity); */
		}
		return enterpriseBaseInfoEntity;

	}

	/**
	 * 基本信息修改
	 * 
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "editscenic", method = RequestMethod.POST)
	@ResponseBody
	public String editscenic(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		String json = request.getParameter("param");
		JSONObject node = JSONObject.fromObject(json);
		String repsonse = "error";
		String userId = node.getString("id");
		List<ScenicDataSta> scenicDataStalist = systemService.findHql(
				"from ScenicDataSta s  where s.userId=?", userId);
		if (scenicDataStalist.size() > 0) {
			ScenicDataSta scenicDataSta = scenicDataStalist.get(0);

			scenicDataSta.setName(node.getString("enterpriseName"));
			scenicDataSta.setArea(node.getString("enterpriseArea"));
			scenicDataSta.setAddress(node.getString("enterpriseAddress"));
			scenicDataSta.setZipcode(node.getString("zipcode"));
			scenicDataSta.setOrgproperty(node.getString("orgproperty").equals(
					"") ? 0 : Integer.valueOf(setOrgproperty(node
					.getString("orgproperty"))));
			scenicDataSta.setUrl(node.getString("url"));
			scenicDataSta.setConsultphone(node.getString("consultphone"));
			scenicDataSta.setScenictype(node.getString("scenictype"));
			StringBuffer sb = new StringBuffer(node.getString("opentime"));
			if (sb.length() > 0) {
				sb.insert(4, "-");
				sb.insert(7, "-");
			}

			scenicDataSta.setOpentime(sb.toString());
			scenicDataSta
					.setDaylimit(node.getString("acreage").equals("") ? 0.0
							: Double.valueOf(node.getString("daylimit")));
			scenicDataSta.setAcreage(node.getString("acreage").equals("") ? 0.0
					: Double.valueOf(node.getString("acreage")));
			scenicDataSta.setInvestunit(node.getString("investunit"));
			scenicDataSta.setSuperiorunit(node.getString("superiorunit"));
			scenicDataSta.setLevel(node.getString("level"));
			StringBuffer sb1 = new StringBuffer(node.getString("leveldate"));
			if (sb1.length() > 0) {
				sb1.insert(4, "-");
				sb1.insert(7, "-");
			}

			scenicDataSta.setLeveldate(sb1.toString());
			scenicDataSta.setChargename(node.getString("chargename"));
			scenicDataSta.setChargephone(node.getString("chargephone"));
			scenicDataSta.setChargeemail(node.getString("chargeemail"));
			scenicDataSta.setInformantname(node.getString("informantname"));
			scenicDataSta.setInformantphone(node.getString("informantphone"));
			scenicDataSta.setInformantemail(node.getString("informantemail"));
			scenicDataSta.setInformantqq(node.getString("informantqq"));
			scenicDataSta.setScenictitle(node.getString("scenictitle"));
			scenicDataSta.setBayType(node.getString("bayType"));
			scenicDataSta.setRemarks(node.getString("remarks"));
			/* scenicDataSta.setReportDate(date); */
			scenicDataSta.setStatus(Status.notEdit);
			systemService.saveOrUpdate(scenicDataSta);
			// ScenicData scenicData= ScenicDataSta.copy(scenicDataSta);
			// systemService.saveOrUpdate(scenicData);
			repsonse = "success";

		}
		return repsonse;

	}

	// 机构性质转换
	public static String getOrgproperty(String orgproperty) {
		if (orgproperty.equals("2")) {
			orgproperty = "行政单位";
		} else if (orgproperty.equals("4")) {
			orgproperty = "事业单位";
		} else if (orgproperty.equals("7")) {
			orgproperty = "国有";
		} else if (orgproperty.equals("8")) {
			orgproperty = "集体";
		} else if (orgproperty.equals("9")) {
			orgproperty = "股份合作";
		} else if (orgproperty.equals("10")) {
			orgproperty = "国有联营";
		} else if (orgproperty.equals("11")) {
			orgproperty = "集体联营";
		} else if (orgproperty.equals("12")) {
			orgproperty = "国有与集体联营";
		} else if (orgproperty.equals("13")) {
			orgproperty = "其他联营";
		} else if (orgproperty.equals("14")) {
			orgproperty = "国有独资公司";
		} else if (orgproperty.equals("15")) {
			orgproperty = "其他有限责任公司";
		} else if (orgproperty.equals("16")) {
			orgproperty = "股份有限公司";
		} else if (orgproperty.equals("17")) {
			orgproperty = "私营独资";
		} else if (orgproperty.equals("18")) {
			orgproperty = "私营合伙";
		} else if (orgproperty.equals("19")) {
			orgproperty = "私营有限责任公司";
		} else if (orgproperty.equals("20")) {
			orgproperty = "私营股份有限公司";
		} else if (orgproperty.equals("23")) {
			orgproperty = "与港澳台商合资经营";
		} else if (orgproperty.equals("24")) {
			orgproperty = "与港澳台商合作经营";
		} else if (orgproperty.equals("25")) {
			orgproperty = "港澳台商独资";
		} else if (orgproperty.equals("26")) {
			orgproperty = "港澳台商投资股份有限公司";
		} else if (orgproperty.equals("28")) {
			orgproperty = "中外合资经营";
		} else if (orgproperty.equals("29")) {
			orgproperty = "中外合作经营";
		} else if (orgproperty.equals("30")) {
			orgproperty = "外资企业";
		} else if (orgproperty.equals("31")) {
			orgproperty = "外商投资股份有限公司";
		} else if (orgproperty.equals("33")) {
			orgproperty = "部队";
		} else if (orgproperty.equals("35")) {
			orgproperty = "其他";
		} else if (orgproperty.equals("")) {
			orgproperty = "";
		} else {
			orgproperty = "无";
		}
		return orgproperty;
	}

	//
	public static String setOrgproperty(String orgproperty) {
		if (orgproperty.equals("行政单位")) {
			orgproperty = "2";
		} else if (orgproperty.equals("事业单位")) {
			orgproperty = "4";
		} else if (orgproperty.equals("国有")) {
			orgproperty = "7";
		} else if (orgproperty.equals("集体")) {
			orgproperty = "8";
		} else if (orgproperty.equals("股份合作")) {
			orgproperty = "9";
		} else if (orgproperty.equals("国有联营")) {
			orgproperty = "10";
		} else if (orgproperty.equals("集体联营")) {
			orgproperty = "11";
		} else if (orgproperty.equals("国有与集体联营")) {
			orgproperty = "12";
		} else if (orgproperty.equals("其他联营")) {
			orgproperty = "13";
		} else if (orgproperty.equals("国有独资公司")) {
			orgproperty = "14";
		} else if (orgproperty.equals("其他有限责任公司")) {
			orgproperty = "15";
		} else if (orgproperty.equals("股份有限公司")) {
			orgproperty = "16";
		} else if (orgproperty.equals("私营独资")) {
			orgproperty = "17";
		} else if (orgproperty.equals("私营合伙")) {
			orgproperty = "18";
		} else if (orgproperty.equals("私营有限责任公司")) {
			orgproperty = "19";
		} else if (orgproperty.equals("私营股份有限公司")) {
			orgproperty = "20";
		} else if (orgproperty.equals("与港澳台商合资经营")) {
			orgproperty = "23";
		} else if (orgproperty.equals("与港澳台商合作经营")) {
			orgproperty = "24";
		} else if (orgproperty.equals("港澳台商独资")) {
			orgproperty = "25";
		} else if (orgproperty.equals("港澳台商投资股份有限公司")) {
			orgproperty = "26";
		} else if (orgproperty.equals("中外合资经营")) {
			orgproperty = "28";
		} else if (orgproperty.equals("中外合作经营")) {
			orgproperty = "29";
		} else if (orgproperty.equals("外资企业")) {
			orgproperty = "30";
		} else if (orgproperty.equals("外商投资股份有限公司")) {
			orgproperty = "31";
		} else if (orgproperty.equals("部队")) {
			orgproperty = "33";
		} else if (orgproperty.equals("其他")) {
			orgproperty = "35";
		} else if (orgproperty.equals("")) {
			orgproperty = "0";
		}
		return orgproperty;
	}

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("20160203");
		sb.insert(4, "-");
		sb.insert(7, "-");
		System.out.println(sb.toString());
	}
}
