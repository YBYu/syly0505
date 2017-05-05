package com.zzc.web.travel.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzc.web.notice.entity.Notice;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.TravelQuarterIn;
import com.zzc.web.travel.entity.TravelQuarterInland;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.travelagency.GetBasicInfoResponseGetBasicInfoResult;
import com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult;
import com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult;
import com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult;
import com.zzc.webservice.travelagency.GetNoticeResponseGetNoticeResult;
import com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

@Scope("prototype")
@Controller
@RequestMapping("/traveldataupdatecontroller")
public class TraveldataUpdateController {
	
	private static final Logger logger = Logger.getLogger(TraveldataUpdateController.class);
	
	@Autowired
	private SystemService systemService;
	
	public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 同步旅行社
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getBasicInfo")
	public void getBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------getBasicInfo开始同步-----------------------------");
		// webservice获取所有旅行社数据
		GetBasicInfoResponseGetBasicInfoResult rs = null;
		try {
			rs = new PublicInterfaceSoapProxy().getBasicInfo("002008013", "sql*lyj_baiwang@2017");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(rs.get_any()[1].toString());
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		List<Element> nodes = root.element("NewDataSet").elements();
		//数据集合
		List<Traveldata> list = new ArrayList<Traveldata>();
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			Traveldata traveldata = new Traveldata();
			traveldata.setOrganizationnum(element.element("Code").getTextTrim());
			traveldata.setTableDate(element.element("TableDate").getTextTrim());
			traveldata.setName(element.element("UnitName").getTextTrim());
			traveldata.setCorporate(element.element("Delegate").getTextTrim());
			traveldata.setLicensenum(element.element("UserName").getTextTrim());
			traveldata.setPrincipal(element.element("UnitMaster").getTextTrim());
			traveldata.setFiller(element.element("Filler").getTextTrim());
			traveldata.setFillerTel(element.element("FillerTel").getTextTrim());
			traveldata.setAddress(element.element("Adessr").getTextTrim());
			traveldata.setProvince(element.element("Province").getTextTrim());
			traveldata.setArea(element.element("City").getTextTrim());
			traveldata.setZipcode(element.element("Postcode").getTextTrim());
			traveldata.setPhone(element.element("Tel").getTextTrim());
			traveldata.setPhoneextension(element.element("TelNo").getTextTrim());
			traveldata.setMobile(element.element("mobile").getTextTrim());
			traveldata.setFax(element.element("Fax").getTextTrim());
			traveldata.setFaxextension(element.element("FaxNo").getTextTrim());
			traveldata.setWeburl(element.element("WebSite").getTextTrim());
			traveldata.setEmail(element.element("Email").getTextTrim());
			traveldata.setRegisterstyle(element.element("RegistrationType").getTextTrim());
			traveldata.setIsmember(element.element("UnitType").getTextTrim());
			traveldata.setAccommodationStar(element.element("AccommodationStar").getTextTrim());
			traveldata.setTravelAgencyType(element.element("TravelAgencyType").getTextTrim());
			traveldata.setSceneSpotlevel(element.element("SceneSpotLevel").getTextTrim());
			traveldata.setBusinessexit(element.element("OutBoundTourism").getTextTrim());
			traveldata.setBusinessborder(element.element("BorderTour").getTextTrim());
			traveldata.setEnglishname(element.element("EnglishName").getTextTrim());
			traveldata.setQq(element.element("QQ").getTextTrim());
			traveldata.setScore(40);
			list.add(traveldata);
		}
		//数据集合
		List<Traveldata> listNew = new ArrayList<Traveldata>();
		for (int i = 0; i < list.size(); i++) {
			Traveldata traveldata = list.get(i);
			TSUser user = null;
			if(traveldata.getLicensenum() == null || traveldata.getLicensenum().length() == 0){
				user = AutoAddUser.add(traveldata.getLicensenum(), GlobalParams.LVXINGSHE, traveldata.getName());
			}else{
				user = AutoAddUser.interfaceAdd(traveldata.getLicensenum(), GlobalParams.LVXINGSHE, traveldata.getName());
			}
			traveldata.setUserId(user.getId());
			listNew.add(traveldata);
		}
		// 批量保存
		systemService.batchSave(listNew);
		System.out.println("-----------------------------getBasicInfo结束同步-----------------------------");
    }
	
	/**
	 * 
	 * 同步国内季报
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getInternalTourism")
	public void getInternalTourism(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------getInternalTourism开始同步-----------------------------");
		String year = request.getParameter("year");
		for (int quarterNum = 1; quarterNum < 5; quarterNum++) {
			String quarter = String.valueOf(quarterNum);
			List<TravelQuarterInland> list = new ArrayList<TravelQuarterInland>();
			
			// 获取所有旅行社
			List<Traveldata> travelList = systemService.getList(Traveldata.class);
			for (int i = 0; i < travelList.size(); i++) {
				// 暂停0.2s
				try {
				// 获取旅行社信息
				Traveldata traveldata = travelList.get(i);
				System.out.println(traveldata.getName());
				TravelQuarterInland travelQuarterInland = new TravelQuarterInland();
				// 设定基本信息
				travelQuarterInland.setYear(year);
				travelQuarterInland.setQuarter(quarter);
				travelQuarterInland.setTid(traveldata.getId());
				travelQuarterInland.setStatus("4");
				
				String license = traveldata.getLicensenum();
//				安徽（人天，人次）	002001
				Integer[] data = this.getAreaInternalTourism("002001", year, quarter, license);
				travelQuarterInland.setAhOne(data[0]);
				travelQuarterInland.setAhTwo(data[1]);
				travelQuarterInland.setAhThree(data[2]);
				travelQuarterInland.setAhFour(data[3]);
//				北京（人天，人次）	002002
				data = this.getAreaInternalTourism("002002", year, quarter, license);
				travelQuarterInland.setBjOne(data[0]);
				travelQuarterInland.setBjTwo(data[1]);
				travelQuarterInland.setBjThree(data[2]);
				travelQuarterInland.setBjFour(data[3]);
//				福建（人天，人次）	002003
				data = this.getAreaInternalTourism("002003", year, quarter, license);
				travelQuarterInland.setFjOne(data[0]);
				travelQuarterInland.setFjTwo(data[1]);
				travelQuarterInland.setFjThree(data[2]);
				travelQuarterInland.setFjFour(data[3]);
//				甘肃（人天，人次）	002004
				data = this.getAreaInternalTourism("002004", year, quarter, license);
				travelQuarterInland.setGansuOne(data[0]);
				travelQuarterInland.setGansuTwo(data[1]);
				travelQuarterInland.setGansuThree(data[2]);
				travelQuarterInland.setGansuFour(data[3]);
//				广东（人天，人次）	002005
				data = this.getAreaInternalTourism("002005", year, quarter, license);
				travelQuarterInland.setGdOne(data[0]);
				travelQuarterInland.setGdTwo(data[1]);
				travelQuarterInland.setGdThree(data[2]);
				travelQuarterInland.setGdFour(data[3]);
//				广西（人天，人次）	002006
				data = this.getAreaInternalTourism("002006", year, quarter, license);
				travelQuarterInland.setGxOne(data[0]);
				travelQuarterInland.setGxTwo(data[1]);
				travelQuarterInland.setGxThree(data[2]);
				travelQuarterInland.setGxFour(data[3]);
//				贵州（人天，人次）	002007
				data = this.getAreaInternalTourism("002007", year, quarter, license);
				travelQuarterInland.setGzOne(data[0]);
				travelQuarterInland.setGzTwo(data[1]);
				travelQuarterInland.setGzThree(data[2]);
				travelQuarterInland.setGzFour(data[3]);
//				海南（人天，人次）	002008
				data = this.getAreaInternalTourism("002008", year, quarter, license);
				travelQuarterInland.setHainanOne(data[0]);
				travelQuarterInland.setHainanTwo(data[1]);
				travelQuarterInland.setHainanThree(data[2]);
				travelQuarterInland.setHainanFour(data[3]);
//				河北（人天，人次）	002009
				data = this.getAreaInternalTourism("002009", year, quarter, license);
				travelQuarterInland.setHbOne(data[0]);
				travelQuarterInland.setHbTwo(data[1]);
				travelQuarterInland.setHbThree(data[2]);
				travelQuarterInland.setHbFour(data[3]);
//				河南（人天，人次）	002010
				data = this.getAreaInternalTourism("002010", year, quarter, license);
				travelQuarterInland.setHenanOne(data[0]);
				travelQuarterInland.setHenanTwo(data[1]);
				travelQuarterInland.setHenanThree(data[2]);
				travelQuarterInland.setHenanFour(data[3]);
//				黑龙江（人天，人次）	002011
				data = this.getAreaInternalTourism("002011", year, quarter, license);
				travelQuarterInland.setHljOne(data[0]);
				travelQuarterInland.setHljTwo(data[1]);
				travelQuarterInland.setHljThree(data[2]);
				travelQuarterInland.setHljFour(data[3]);
//				湖北（人天，人次）	002012
				data = this.getAreaInternalTourism("002012", year, quarter, license);
				travelQuarterInland.setHubeiOne(data[0]);
				travelQuarterInland.setHubeiTwo(data[1]);
				travelQuarterInland.setHubeiThree(data[2]);
				travelQuarterInland.setHubeiFour(data[3]);
//				湖南（人天，人次）	002013
				data = this.getAreaInternalTourism("002013", year, quarter, license);
				travelQuarterInland.setHlOne(data[0]);
				travelQuarterInland.setHlTwo(data[1]);
				travelQuarterInland.setHlThree(data[2]);
				travelQuarterInland.setHlFour(data[3]);
//				吉林（人天，人次）	002014
				data = this.getAreaInternalTourism("002014", year, quarter, license);
				travelQuarterInland.setJlOne(data[0]);
				travelQuarterInland.setJlTwo(data[1]);
				travelQuarterInland.setJlThree(data[2]);
				travelQuarterInland.setJlFour(data[3]);
//				江苏（人天，人次）	002015
				data = this.getAreaInternalTourism("002015", year, quarter, license);
				travelQuarterInland.setJsOne(data[0]);
				travelQuarterInland.setJsTwo(data[1]);
				travelQuarterInland.setJsThree(data[2]);
				travelQuarterInland.setJsFour(data[3]);
//				江西（人天，人次）	002016
				data = this.getAreaInternalTourism("002016", year, quarter, license);
				travelQuarterInland.setJxOne(data[0]);
				travelQuarterInland.setJxTwo(data[1]);
				travelQuarterInland.setJxThree(data[2]);
				travelQuarterInland.setJxFour(data[3]);
//				辽宁（人天，人次）	002017
				data = this.getAreaInternalTourism("002017", year, quarter, license);
				travelQuarterInland.setLnOne(data[0]);
				travelQuarterInland.setLnTwo(data[1]);
				travelQuarterInland.setLnThree(data[2]);
				travelQuarterInland.setLnFour(data[3]);
//				内蒙古（人天，人次）	002018
				data = this.getAreaInternalTourism("002018", year, quarter, license);
				travelQuarterInland.setNmgOne(data[0]);
				travelQuarterInland.setNmgTwo(data[1]);
				travelQuarterInland.setNmgThree(data[2]);
				travelQuarterInland.setNmgFour(data[3]);
//				宁夏（人天，人次）	002019
				data = this.getAreaInternalTourism("002019", year, quarter, license);
				travelQuarterInland.setNxOne(data[0]);
				travelQuarterInland.setNxTwo(data[1]);
				travelQuarterInland.setNxThree(data[2]);
				travelQuarterInland.setNxFour(data[3]);
//				青海（人天，人次）	002020
				data = this.getAreaInternalTourism("002020", year, quarter, license);
				travelQuarterInland.setQinghaiOne(data[0]);
				travelQuarterInland.setQinghaiTwo(data[1]);
				travelQuarterInland.setQinghaiThree(data[2]);
				travelQuarterInland.setQinghaiFour(data[3]);
//				山东（人天，人次）	002021
				data = this.getAreaInternalTourism("002021", year, quarter, license);
				travelQuarterInland.setSdOne(data[0]);
				travelQuarterInland.setSdTwo(data[1]);
				travelQuarterInland.setSdThree(data[2]);
				travelQuarterInland.setSdFour(data[3]);
//				山西（人天，人次）	002022
				data = this.getAreaInternalTourism("002022", year, quarter, license);
				travelQuarterInland.setSxOne(data[0]);
				travelQuarterInland.setSxTwo(data[1]);
				travelQuarterInland.setSxThree(data[2]);
				travelQuarterInland.setSxFour(data[3]);
//				陕西（人天，人次）	002023
				data = this.getAreaInternalTourism("002023", year, quarter, license);
				travelQuarterInland.setShanxiOne(data[0]);
				travelQuarterInland.setShanxiTwo(data[1]);
				travelQuarterInland.setShanxiThree(data[2]);
				travelQuarterInland.setShanxiFour(data[3]);
//				上海（人天，人次）	002024
				data = this.getAreaInternalTourism("002024", year, quarter, license);
				travelQuarterInland.setShOne(data[0]);
				travelQuarterInland.setShTwo(data[1]);
				travelQuarterInland.setShThree(data[2]);
				travelQuarterInland.setShFour(data[3]);
//				四川（人天，人次）	002025
				data = this.getAreaInternalTourism("002025", year, quarter, license);
				travelQuarterInland.setScOne(data[0]);
				travelQuarterInland.setScTwo(data[1]);
				travelQuarterInland.setScThree(data[2]);
				travelQuarterInland.setScFour(data[3]);
//				天津（人天，人次）	002026
				data = this.getAreaInternalTourism("002026", year, quarter, license);
				travelQuarterInland.setTjOne(data[0]);
				travelQuarterInland.setTjTwo(data[1]);
				travelQuarterInland.setTjThree(data[2]);
				travelQuarterInland.setTjFour(data[3]);
//				西藏（人天，人次）	002027
				data = this.getAreaInternalTourism("002027", year, quarter, license);
				travelQuarterInland.setXzOne(data[0]);
				travelQuarterInland.setXzTwo(data[1]);
				travelQuarterInland.setXzThree(data[2]);
				travelQuarterInland.setXzFour(data[3]);
//				新疆（人天，人次）	002028
				data = this.getAreaInternalTourism("002028", year, quarter, license);
				travelQuarterInland.setXjOne(data[0]);
				travelQuarterInland.setXjTwo(data[1]);
				travelQuarterInland.setXjThree(data[2]);
				travelQuarterInland.setXjFour(data[3]);
//				云南（人天，人次）	002029
				data = this.getAreaInternalTourism("002029", year, quarter, license);
				travelQuarterInland.setYnOne(data[0]);
				travelQuarterInland.setYnTwo(data[1]);
				travelQuarterInland.setYnThree(data[2]);
				travelQuarterInland.setYnFour(data[3]);
//				浙江（人天，人次）	002030
				data = this.getAreaInternalTourism("002030", year, quarter, license);
				travelQuarterInland.setZjOne(data[0]);
				travelQuarterInland.setZjTwo(data[1]);
				travelQuarterInland.setZjThree(data[2]);
				travelQuarterInland.setZjFour(data[3]);
//				重庆（人天，人次）	002031
				data = this.getAreaInternalTourism("002031", year, quarter, license);
				travelQuarterInland.setCqOne(data[0]);
				travelQuarterInland.setCqTwo(data[1]);
				travelQuarterInland.setCqThree(data[2]);
				travelQuarterInland.setCqFour(data[3]);
//				非地区	 001001009
				data = this.getAreaInternalTourism("001001009", year, quarter, license);
				travelQuarterInland.setDayOrgOne(data[0]);
				travelQuarterInland.setNightTimeOne(data[1]);
				travelQuarterInland.setNightOrgOne(data[3]);
				list.add(travelQuarterInland);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			systemService.batchSave(list);
		}

		System.out.println("-----------------------------getInternalTourism结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 根据地区id获取地区国内季报数据
	 * @param sortID
	 * @param year
	 * @param quarter
	 * @param license
	 * @return 地区国内季报数据
	 */
	public Integer[] getAreaInternalTourism(String sortID, String year, String quarter, String license){
		Integer[] data = new Integer[]{null,null,null,null};
		String areaID = "002008013";
		String verificationCode = "sql*lyj_baiwang@2017";
		GetInternalTourismResponseGetInternalTourismResult rs = null;
		// webservice获取所有国内报表数据
		try {
			// 暂停0.2s
			Thread.sleep(1000);
			rs = new PublicInterfaceSoapProxy().getInternalTourism(areaID, license, year, quarter, sortID, verificationCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return data;
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(rs.get_any()[1].toString());
		
		Element root = doc.getRootElement();
		// 没有查询到数据
		if(root.element("NewDataSet") == null) return data;
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			data[0] = Integer.parseInt(element.element("season") == null ? "0" : element.element("season").getTextTrim());
			data[1] = Integer.parseInt(element.element("Season_boy") == null ? "0" : element.element("Season_boy").getTextTrim());
			data[2] = Integer.parseInt(element.element("SeasonDay") == null ? "0" : element.element("SeasonDay").getTextTrim());
			data[3] = Integer.parseInt(element.element("SeasonDay_boy") == null ? "0" : element.element("SeasonDay_boy").getTextTrim());
//			System.out.println("本季组织人次:"+element.element("season").getTextTrim());
//			System.out.println("本季接待人次:"+element.element("Season_boy").getTextTrim());
//			System.out.println("本季组织人天:"+element.element("SeasonDay").getTextTrim());
//			System.out.println("本季接待人天:"+element.element("SeasonDay_boy").getTextTrim());
		}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			logger.info("getAreaInternalTourism失败,sortID:"+sortID+" year:"+year+" quarter:"+quarter+" license:"+license);
			return data;
		}
		return data;
	}
	
	/**
	 * 
	 * 同步入境季报
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getInboundTravel")
	public void getInboundTravel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------getInboundTravel开始同步-----------------------------");
		String year = request.getParameter("year");
		for (int quarterNum = 1; quarterNum < 5; quarterNum++) {
			String quarter = String.valueOf(quarterNum);
			List<TravelQuarterIn> list = new ArrayList<TravelQuarterIn>();
			
			// 获取所有旅行社
			List<Traveldata> travelList = systemService.getList(Traveldata.class);
			for (int i = 0; i < travelList.size(); i++) {
				try {
				// 获取旅行社信息
				Traveldata traveldata = travelList.get(i);
				System.out.println(traveldata.getName());
				TravelQuarterIn travelQuarterIn = new TravelQuarterIn();
				// 设定基本信息
				travelQuarterIn.setYear(year);
				travelQuarterIn.setQuarter(quarter);
				travelQuarterIn.setTid(traveldata.getId());
				travelQuarterIn.setStatus("4");
				
				String license = traveldata.getLicensenum();
//				香港（人天，人次）	001001001001
				Integer[] data = this.getAreaInboundTourism("001001001001", year, quarter, license);
				travelQuarterIn.setHkComOne(data[0]);
				travelQuarterIn.setHkComTwo(data[1]);
				travelQuarterIn.setHkComThree(data[2]);
				travelQuarterIn.setHkComFour(data[3]);
//				澳门（人天，人次）	001001001002
				data = this.getAreaInboundTourism("001001001002", year, quarter, license);
				travelQuarterIn.setMcComOne(data[0]);
				travelQuarterIn.setMcComTwo(data[1]);
				travelQuarterIn.setMcComThree(data[2]);
				travelQuarterIn.setMcComFour(data[3]);
//				台湾（人天，人次）	001001001003
				data = this.getAreaInboundTourism("001001001003", year, quarter, license);
				travelQuarterIn.setTwComOne(data[0]);
				travelQuarterIn.setTwComTwo(data[1]);
				travelQuarterIn.setTwComThree(data[2]);
				travelQuarterIn.setTwComFour(data[3]);
//				外国人（人天总数）	001001007
				data = this.getAreaInboundTourism("001001007", year, quarter, license);
				travelQuarterIn.setForeignersOne(data[0]);
				travelQuarterIn.setForeignersTwo(data[1]);
				travelQuarterIn.setForeignersThree(data[2]);
				travelQuarterIn.setForeignersFour(data[3]);
//				日本（人次）	001001001004
				data = this.getAreaInboundTourism("001001001004", year, quarter, license);
				travelQuarterIn.setJapanOne(data[0]);
				travelQuarterIn.setJapanTwo(data[1]);
				travelQuarterIn.setJapanThree(data[2]);
				travelQuarterIn.setJapanFour(data[3]);
//				韩国（人次）	001001001005
				data = this.getAreaInboundTourism("001001001005", year, quarter, license);
				travelQuarterIn.setKoreaOne(data[0]);
				travelQuarterIn.setKoreaTwo(data[1]);
				travelQuarterIn.setKoreaThree(data[2]);
				travelQuarterIn.setKoreaFour(data[3]);
//				蒙古（人次）	001001001006
				data = this.getAreaInboundTourism("001001001006", year, quarter, license);
				travelQuarterIn.setMongoOne(data[0]);
				travelQuarterIn.setMongoTwo(data[1]);
				travelQuarterIn.setMongoThree(data[2]);
				travelQuarterIn.setMongoFour(data[3]);
//				印度尼西亚（人次）	001001001007
				data = this.getAreaInboundTourism("001001001007", year, quarter, license);
				travelQuarterIn.setIndonxyOne(data[0]);
				travelQuarterIn.setIndonxyTwo(data[1]);
				travelQuarterIn.setIndonxyThree(data[2]);
				travelQuarterIn.setIndonxyFour(data[3]);
//				马来西亚（人次）	001001001008
				data = this.getAreaInboundTourism("001001001008", year, quarter, license);
				travelQuarterIn.setMalaxyOne(data[0]);
				travelQuarterIn.setMalaxyTwo(data[1]);
				travelQuarterIn.setMalaxyThree(data[2]);
				travelQuarterIn.setMalaxyFour(data[3]);
//				菲律宾（人次）	001001001009
				data = this.getAreaInboundTourism("001001001009", year, quarter, license);
				travelQuarterIn.setPhilipnOne(data[0]);
				travelQuarterIn.setPhilipnTwo(data[1]);
				travelQuarterIn.setPhilipnThree(data[2]);
				travelQuarterIn.setPhilipnFour(data[3]);
//				新加坡（人次）	001001001010
				data = this.getAreaInboundTourism("001001001010", year, quarter, license);
				travelQuarterIn.setSingaporeOne(data[0]);
				travelQuarterIn.setSingaporeTwo(data[1]);
				travelQuarterIn.setSingaporeThree(data[2]);
				travelQuarterIn.setSingaporeFour(data[3]);
//				泰国（人次）	001001001011
				data = this.getAreaInboundTourism("001001001011", year, quarter, license);
				travelQuarterIn.setTailandOne(data[0]);
				travelQuarterIn.setTailandTwo(data[1]);
				travelQuarterIn.setTailandThree(data[2]);
				travelQuarterIn.setTailandFour(data[3]);
//				印度（人次）	001001001012
				data = this.getAreaInboundTourism("001001001012", year, quarter, license);
				travelQuarterIn.setIndiaOne(data[0]);
				travelQuarterIn.setIndiaTwo(data[1]);
				travelQuarterIn.setIndiaThree(data[2]);
				travelQuarterIn.setIndiaFour(data[3]);
//				越南（人次）	001001001013
				data = this.getAreaInboundTourism("001001001013", year, quarter, license);
				travelQuarterIn.setVietnamOne(data[0]);
				travelQuarterIn.setVietnamTwo(data[1]);
				travelQuarterIn.setVietnamThree(data[2]);
				travelQuarterIn.setVietnamFour(data[3]);
//				缅甸（人次）	001001001014
				data = this.getAreaInboundTourism("001001001014", year, quarter, license);
				travelQuarterIn.setMyanamarOne(data[0]);
				travelQuarterIn.setMyanamarTwo(data[1]);
				travelQuarterIn.setMyanamarThree(data[2]);
				travelQuarterIn.setMyanamarFour(data[3]);
//				亚洲其他（人次）	001001001015
				data = this.getAreaInboundTourism("001001001015", year, quarter, license);
				travelQuarterIn.setOtherAsianOne(data[0]);
				travelQuarterIn.setOtherAsianTwo(data[1]);
				travelQuarterIn.setOtherAsianThree(data[2]);
				travelQuarterIn.setOtherAsianFour(data[3]);
//				英国（人次）	001001002001
				data = this.getAreaInboundTourism("001001002001", year, quarter, license);
				travelQuarterIn.setEnglandOne(data[0]);
				travelQuarterIn.setEnglandTwo(data[1]);
//				法国（人次）	001001002002
				data = this.getAreaInboundTourism("001001002002", year, quarter, license);
				travelQuarterIn.setFrenchOne(data[0]);
				travelQuarterIn.setFrenchTwo(data[1]);
//				德国（人次）	001001002003
				data = this.getAreaInboundTourism("001001002003", year, quarter, license);
				travelQuarterIn.setGermanyOne(data[0]);
				travelQuarterIn.setGermanyTwo(data[1]);
//				意大利（人次）	001001002004
				data = this.getAreaInboundTourism("001001002004", year, quarter, license);
				travelQuarterIn.setItalyOne(data[0]);
				travelQuarterIn.setItalyTwo(data[1]);
//				瑞士（人次）	001001002005
				data = this.getAreaInboundTourism("001001002005", year, quarter, license);
				travelQuarterIn.setSwissOne(data[0]);
				travelQuarterIn.setSwissTwo(data[1]);
//				瑞典（人次）	001001002006
				data = this.getAreaInboundTourism("001001002006", year, quarter, license);
				travelQuarterIn.setSwedishOne(data[0]);
				travelQuarterIn.setSwedishTwo(data[1]);
//				俄罗斯（人次）	001001002007
				data = this.getAreaInboundTourism("001001002007", year, quarter, license);
				travelQuarterIn.setRussiaOne(data[0]);
				travelQuarterIn.setRussiaTwo(data[1]);
//				西班牙（人次）	001001002008
				data = this.getAreaInboundTourism("001001002008", year, quarter, license);
				travelQuarterIn.setSpainOne(data[0]);
				travelQuarterIn.setSpainTwo(data[1]);
//				荷兰（人次）	001001002009
				data = this.getAreaInboundTourism("001001002009", year, quarter, license);
				travelQuarterIn.setHollandOne(data[0]);
				travelQuarterIn.setHollandTwo(data[1]);
				travelQuarterIn.setHollandThree(data[2]);
				travelQuarterIn.setHollandFour(data[3]);
//				丹麦（人次）	001001002010
				data = this.getAreaInboundTourism("001001002010", year, quarter, license);
				travelQuarterIn.setDenmarkOne(data[0]);
				travelQuarterIn.setDenmarkTwo(data[1]);
//				欧洲其他（人次）	001001002011
				data = this.getAreaInboundTourism("001001002011", year, quarter, license);
				travelQuarterIn.setOtherEuropeanOne(data[0]);
				travelQuarterIn.setOtherEuropeanTwo(data[1]);
//				美国（人次）	001001003001
				data = this.getAreaInboundTourism("001001003001", year, quarter, license);
				travelQuarterIn.setUsOne(data[0]);
				travelQuarterIn.setUsTwo(data[1]);
//				加拿大（人次）	001001003002
				data = this.getAreaInboundTourism("001001003002", year, quarter, license);
				travelQuarterIn.setCanadaOne(data[0]);
				travelQuarterIn.setCanadaTwo(data[1]);
//				美洲其他（人次）	001001003003
				data = this.getAreaInboundTourism("001001003003", year, quarter, license);
				travelQuarterIn.setOtherAmericanOne(data[0]);
				travelQuarterIn.setOtherAmericanTwo(data[1]);
//				澳大利亚（人次）	001001004001
				data = this.getAreaInboundTourism("001001004001", year, quarter, license);
				travelQuarterIn.setAustraliaOne(data[0]);
				travelQuarterIn.setAustraliaTwo(data[1]);
//				新西兰（人次）	001001004002
				data = this.getAreaInboundTourism("001001004002", year, quarter, license);
				travelQuarterIn.setNewlandOne(data[0]);
				travelQuarterIn.setNewlandTwo(data[1]);
//				大洋洲其他（人次）	001001004003
				data = this.getAreaInboundTourism("001001004003", year, quarter, license);
				travelQuarterIn.setOtherOceaniaOne(data[0]);
				travelQuarterIn.setOtherOceaniaTwo(data[1]);
//				南非（人次）	001001005001
				data = this.getAreaInboundTourism("001001005001", year, quarter, license);
				travelQuarterIn.setSouthafricaOne(data[0]);
				travelQuarterIn.setSouthafricaTwo(data[1]);
//				埃及（人次）	001001005002
				data = this.getAreaInboundTourism("001001005002", year, quarter, license);
				travelQuarterIn.setEgyptOne(data[0]);
				travelQuarterIn.setEgyptTwo(data[1]);
//				肯尼亚（人次）	001001005003
				data = this.getAreaInboundTourism("001001005003", year, quarter, license);
				travelQuarterIn.setKenyaOne(data[0]);
				travelQuarterIn.setKenyaTwo(data[1]);
				travelQuarterIn.setKenyaThree(data[2]);
				travelQuarterIn.setKenyaFour(data[3]);
//				非洲其他（人次）	001001005004
				data = this.getAreaInboundTourism("001001005004", year, quarter, license);
				travelQuarterIn.setAfricaotherOne(data[0]);
				travelQuarterIn.setAfricaotherTwo(data[1]);
//				其他小计（人次）	001001006
				data = this.getAreaInboundTourism("001001006", year, quarter, license);
				travelQuarterIn.setTotalOtherOne(data[0]);
				travelQuarterIn.setTotalOtherTwo(data[1]);
				list.add(travelQuarterIn);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			systemService.batchSave(list);
		}
		System.out.println("-----------------------------getInboundTravel结束同步-----------------------------");
	}
	
	/**
	 * 
	 * @param sortID
	 * @param year
	 * @param quarter
	 * @param license
	 * @return 地区入境季报数据
	 */
	public Integer[] getAreaInboundTourism(String sortID, String year, String quarter, String license){
		Integer[] data = new Integer[]{null,null,null,null};
		String areaID = "002008013";
		String verificationCode = "sql*lyj_baiwang@2017";
		GetInboundTravelResponseGetInboundTravelResult rs = null;
		// webservice获取所有国内报表数据
		try {
			// 暂停0.2s
			Thread.sleep(1000);
			rs = new PublicInterfaceSoapProxy().getInboundTravel(areaID, license, year, quarter, sortID, verificationCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return data;
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		// 没有查询到数据
		if(root.element("NewDataSet") == null) return data;
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			data[0] = Integer.parseInt(element.element("season") == null ? "0" : element.element("season").getTextTrim());
			data[1] = Integer.parseInt(element.element("SeasonDay") == null ? "0" : element.element("SeasonDay").getTextTrim());
			data[2] = Integer.parseInt(element.element("SeasonAdmit") == null ? "0" : element.element("SeasonAdmit").getTextTrim());
			data[3] = Integer.parseInt(element.element("SeasonAdmitDay") == null ? "0" : element.element("SeasonAdmitDay").getTextTrim());
//			System.out.println("外联人次:"+element.element("season").getTextTrim());
//			System.out.println("接待人次:"+element.element("SeasonDay").getTextTrim());
//			System.out.println("外联人天:"+element.element("SeasonAdmit").getTextTrim());
//			System.out.println("接待人天:"+element.element("SeasonAdmitDay").getTextTrim());
		}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			logger.info("getAreaInboundTourism失败,sortID:"+sortID+" year:"+year+" quarter:"+quarter+" license:"+license);
			return data;
		}
		return data;
	}
	
	/**
	 * 
	 * 同步出境季报
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getOutboundTravel")
	public void getOutboundTravel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------getOutboundTravel开始同步-----------------------------");
		String year = request.getParameter("year");
		for (int quarterNum = 1; quarterNum < 5; quarterNum++) {
			String quarter = String.valueOf(quarterNum);
			List<TravelQuarterOut> list = new ArrayList<TravelQuarterOut>();
			
			// 获取所有旅行社
			List<Traveldata> travelList = systemService.getList(Traveldata.class);
			for (int i = 0; i < travelList.size(); i++) {
				try {
				// 获取旅行社信息
				Traveldata traveldata = travelList.get(i);
				System.out.println(traveldata.getName());
				TravelQuarterOut travelQuarterOut = new TravelQuarterOut();
				// 设定基本信息
				travelQuarterOut.setYear(year);
				travelQuarterOut.setQuarter(quarter);
				travelQuarterOut.setTid(traveldata.getId());
				travelQuarterOut.setStatus("4");
				
				String license = traveldata.getLicensenum();
//				出境游客（人天总数）	001001
				Integer[] data = this.getAreaOutboundTourism("001001", year, quarter, license);
				travelQuarterOut.setOutOne(data[0]);
				travelQuarterOut.setOutTwo(data[1]);
//				香港地区（人次）	001001001001
				data = this.getAreaOutboundTourism("001001001001", year, quarter, license);
				travelQuarterOut.setHongKongOne(data[0]);
				travelQuarterOut.setHongKongTwo(data[1]);
//				澳门地区（人次）	001001001002
				data = this.getAreaOutboundTourism("001001001002", year, quarter, license);
				travelQuarterOut.setMacauOne(data[0]);
				travelQuarterOut.setMacauTwo(data[1]);
//				台湾地区（人次）	001001001003
				data = this.getAreaOutboundTourism("001001001003", year, quarter, license);
				travelQuarterOut.setTaiWanOne(data[0]);
				travelQuarterOut.setTaiWanTwo(data[1]);
//				日本（人次）	001001001004
				data = this.getAreaOutboundTourism("001001001004", year, quarter, license);
				travelQuarterOut.setJapanOne(data[0]);
				travelQuarterOut.setJapanTwo(data[1]);
//				韩国（人次）	001001001005
				data = this.getAreaOutboundTourism("001001001005", year, quarter, license);
				travelQuarterOut.setKoreaOne(data[0]);
				travelQuarterOut.setKoreaTwo(data[1]);
//				蒙古（人次）	001001001006
				data = this.getAreaOutboundTourism("001001001006", year, quarter, license);
				travelQuarterOut.setMongoliaOne(data[0]);
				travelQuarterOut.setMongoliaTwo(data[1]);
//				印度尼西亚（人次）	001001001007
				data = this.getAreaOutboundTourism("001001001007", year, quarter, license);
				travelQuarterOut.setIndonesiaOne(data[0]);
				travelQuarterOut.setIndonesiaTwo(data[1]);
//				马来西亚（人次）	001001001008
				data = this.getAreaOutboundTourism("001001001008", year, quarter, license);
				travelQuarterOut.setMalaysiaOne(data[0]);
				travelQuarterOut.setMalaysiaTwo(data[1]);
//				菲律宾（人次）	001001001009
				data = this.getAreaOutboundTourism("001001001009", year, quarter, license);
				travelQuarterOut.setPhilippinesOne(data[0]);
				travelQuarterOut.setPhilippinesTwo(data[1]);
//				新加坡（人次）	001001001010
				data = this.getAreaOutboundTourism("001001001010", year, quarter, license);
				travelQuarterOut.setSingaporeOne(data[0]);
				travelQuarterOut.setSingaporeTwo(data[1]);
//				泰国（人次）	001001001011
				data = this.getAreaOutboundTourism("001001001011", year, quarter, license);
				travelQuarterOut.setThailandOne(data[0]);
				travelQuarterOut.setThailandTwo(data[1]);
//				印度（人次）	001001001012
				data = this.getAreaOutboundTourism("001001001012", year, quarter, license);
				travelQuarterOut.setIndiaOne(data[0]);
				travelQuarterOut.setIndiaTwo(data[1]);
//				越南（人次）	001001001013
				data = this.getAreaOutboundTourism("001001001013", year, quarter, license);
				travelQuarterOut.setVietnamOne(data[0]);
				travelQuarterOut.setVietnamTwo(data[1]);
//				缅甸（人次）	001001001014
				data = this.getAreaOutboundTourism("001001001014", year, quarter, license);
				travelQuarterOut.setBurmaOne(data[0]);
				travelQuarterOut.setBurmaTwo(data[1]);
//				亚洲其他（人次）	001001001015
				data = this.getAreaOutboundTourism("001001001015", year, quarter, license);
				travelQuarterOut.setAsianOtherOne(data[0]);
				travelQuarterOut.setAsianOtherTwo(data[1]);
//				英国（人次）	001001002001
				data = this.getAreaOutboundTourism("001001002001", year, quarter, license);
				travelQuarterOut.setEnglishOne(data[0]);
				travelQuarterOut.setEnglishTwo(data[1]);
//				法国（人次）	001001002002
				data = this.getAreaOutboundTourism("001001002002", year, quarter, license);
				travelQuarterOut.setFranchOne(data[0]);
				travelQuarterOut.setFranchTwo(data[1]);
//				德国（人次）	001001002003
				data = this.getAreaOutboundTourism("001001002003", year, quarter, license);
				travelQuarterOut.setGermanyOne(data[0]);
				travelQuarterOut.setGermanyTwo(data[1]);
//				意大利（人次）	001001002004
				data = this.getAreaOutboundTourism("001001002004", year, quarter, license);
				travelQuarterOut.setItalyOne(data[0]);
//				瑞士（人次）	001001002005
				data = this.getAreaOutboundTourism("001001002005", year, quarter, license);
				travelQuarterOut.setSwitzerLandOne(data[0]);
//				瑞典（人次）	001001002006
				data = this.getAreaOutboundTourism("001001002006", year, quarter, license);
				travelQuarterOut.setSwedenOne(data[0]);
//				俄罗斯（人次）	001001002007
				data = this.getAreaOutboundTourism("001001002007", year, quarter, license);
				travelQuarterOut.setRussiaOne(data[0]);
//				西班牙（人次）	001001002008
				data = this.getAreaOutboundTourism("001001002008", year, quarter, license);
				travelQuarterOut.setSpainOne(data[0]);
//				荷兰（人次）	001001002009
				data = this.getAreaOutboundTourism("001001002009", year, quarter, license);
				travelQuarterOut.setHolLandOne(data[0]);
//				丹麦（人次）	001001002010
				data = this.getAreaOutboundTourism("001001002010", year, quarter, license);
				travelQuarterOut.setDenMarkOne(data[0]);
//				欧洲其他（人次）	001001002011
				data = this.getAreaOutboundTourism("001001002011", year, quarter, license);
				travelQuarterOut.setEuropeOtherOne(data[0]);
//				美国（人次）	001001003001
				data = this.getAreaOutboundTourism("001001003001", year, quarter, license);
				travelQuarterOut.setUsOne(data[0]);
//				加拿大（人次）	001001003002
				data = this.getAreaOutboundTourism("001001003002", year, quarter, license);
				travelQuarterOut.setCanadaOne(data[0]);
//				美洲其他（人次）	001001003003
				data = this.getAreaOutboundTourism("001001003003", year, quarter, license);
				travelQuarterOut.setUsOtherOne(data[0]);
//				澳大利亚（人次）	001001004001
				data = this.getAreaOutboundTourism("001001004001", year, quarter, license);
				travelQuarterOut.setAustralianOne(data[0]);
//				新西兰（人次）	001001004002
				data = this.getAreaOutboundTourism("001001004002", year, quarter, license);
				travelQuarterOut.setZeaLandOne(data[0]);
//				大洋洲其他（人次）	001001004003
				data = this.getAreaOutboundTourism("001001004003", year, quarter, license);
				travelQuarterOut.setOceaniaOtherOne(data[0]);
//				南非（人次）	001001005001
				data = this.getAreaOutboundTourism("001001005001", year, quarter, license);
				travelQuarterOut.setSouthAfricaOne(data[0]);
//				埃及（人次）	001001005002
				data = this.getAreaOutboundTourism("001001005002", year, quarter, license);
				travelQuarterOut.setEgyptOne(data[0]);
//				肯尼亚（人次）	001001005003
				data = this.getAreaOutboundTourism("001001005003", year, quarter, license);
				travelQuarterOut.setKenyaOne(data[0]);
//				非洲其他（人次）	001001005004
				data = this.getAreaOutboundTourism("001001005004", year, quarter, license);
				travelQuarterOut.setAfricaOtherOne(data[0]);
//				其他小计（人次）	001001006
				data = this.getAreaOutboundTourism("001001006", year, quarter, license);
				travelQuarterOut.setOtherTotalOne(data[0]);
				list.add(travelQuarterOut);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			systemService.batchSave(list);
		}
		
		System.out.println("-----------------------------getOutboundTravel结束同步-----------------------------");
	}
	
	/**
	 * 
	 * @param sortID
	 * @param year
	 * @param quarter
	 * @param license
	 * @return 地区出境季报数据
	 */
	public Integer[] getAreaOutboundTourism(String sortID, String year, String quarter, String license){
		Integer[] data = new Integer[]{null,null,null,null};
		String areaID = "002008013";
		String verificationCode = "sql*lyj_baiwang@2017";
		GetOutboundTravelResponseGetOutboundTravelResult rs = null;
		// webservice获取所有国内报表数据
		try {
			// 暂停0.2s
			Thread.sleep(1000);
			rs = new PublicInterfaceSoapProxy().getOutboundTravel(areaID, license, year, quarter, sortID, verificationCode);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return data;
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		// 没有查询到数据
		if(root.element("NewDataSet") == null) return data;
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			data[0] = Integer.parseInt(element.element("season") == null ? "0" : element.element("season").getTextTrim());
			data[1] = Integer.parseInt(element.element("SeasonDay") == null ? "0" : element.element("SeasonDay").getTextTrim());
//			System.out.println("人次:"+element.element("season").getTextTrim());
//			System.out.println("人天:"+element.element("SeasonDay").getTextTrim());
		}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			logger.info("getAreaOutboundTourism失败,sortID:"+sortID+" year:"+year+" quarter:"+quarter+" license:"+license);
			return data;
		}
		return data;
	}
	
	/**
	 * 
	 * 同步财务年报
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getFinance")
	public void getFinance(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------getFinance开始同步-----------------------------");
		String year = request.getParameter("year");
		String areaID = "002008013";
		String verificationCode = "sql*lyj_baiwang@2017";

		List<TravelAnnualFinance> list = new ArrayList<TravelAnnualFinance>();
		
		// 获取所有旅行社
		List<Traveldata> travelList = systemService.getList(Traveldata.class);
		for (int i = 0; i < travelList.size(); i++) {
			try {
				Thread.sleep(500);
			// 获取旅行社信息
			Traveldata traveldata = travelList.get(i);
			TravelAnnualFinance travelAnnualFinance = new TravelAnnualFinance();
			// 设定基本信息
			travelAnnualFinance.setYear(year);
			travelAnnualFinance.setTid(traveldata.getId());
			travelAnnualFinance.setStatus("4");
			
			String license = traveldata.getLicensenum();
			
			GetFinanceResponseGetFinanceResult rs = null;
			try {
				System.out.println(license);
				rs = new PublicInterfaceSoapProxy().getFinance(areaID, license, year, verificationCode);
			} catch (RemoteException e) {
				e.printStackTrace();
				continue;
			}
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(rs.get_any()[1].toString());
			} catch (DocumentException e) {
				e.printStackTrace();
				continue;
			}
			Element root = doc.getRootElement();
			// 非空判断
			if(root.element("NewDataSet") == null) continue;
			List<Element> nodes = root.element("NewDataSet").elements();
			for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
				Element element = iterator.next();
				travelAnnualFinance.setReportTime(element.element("TableDate").getTextTrim().substring(0, 10));
				travelAnnualFinance.setReportPerson(element.element("Filler").getTextTrim());
				travelAnnualFinance.setTelephone(element.element("Filler").getTextTrim());
				travelAnnualFinance.setStreamTotal(Double.parseDouble(element.element("CurrentAssetsTotal").getTextTrim()));
				travelAnnualFinance.setLongInvest(Double.parseDouble(element.element("LongTermInvest").getTextTrim()));
				travelAnnualFinance.setFixedAssets(Double.parseDouble(element.element("FixAssetsTotal").getTextTrim()));
				travelAnnualFinance.setFixedPrice(Double.parseDouble(element.element("FixAssetsInitialPrice").getTextTrim()));
				travelAnnualFinance.setAssetTotal(Double.parseDouble(element.element("CapitalTotal").getTextTrim()));
				travelAnnualFinance.setLiabilitiesTotal(Double.parseDouble(element.element("TotalLiabilities").getTextTrim()));
				travelAnnualFinance.setPossessor(Double.parseDouble(element.element("OwnerBenefitTotal").getTextTrim()));
				travelAnnualFinance.setRealIncome(Double.parseDouble(element.element("RealCapital").getTextTrim()));
				travelAnnualFinance.setBusinessIncome(Double.parseDouble(element.element("OperationIncome").getTextTrim()));
				travelAnnualFinance.setIntoIncome(Double.parseDouble(element.element("InboundRevenue").getTextTrim()));
				travelAnnualFinance.setOutIncome(Double.parseDouble(element.element("OutboundRevenues").getTextTrim()));
				travelAnnualFinance.setInTravelIncome(Double.parseDouble(element.element("DomesticRevenue").getTextTrim()));
				travelAnnualFinance.setCost(Double.parseDouble(element.element("OperationCost").getTextTrim()));
				travelAnnualFinance.setBusinessExpenses(Double.parseDouble(element.element("OperationFee").getTextTrim()));
				travelAnnualFinance.setBusinessTexAdd(Double.parseDouble(element.element("OperationTaxAddition").getTextTrim()));
				travelAnnualFinance.setMainOperation(Double.parseDouble(element.element("BusinessProfit").getTextTrim()));
				travelAnnualFinance.setProfitInTravel(Double.parseDouble(element.element("InbundProfit").getTextTrim()));
				travelAnnualFinance.setOutTravelBusiness(Double.parseDouble(element.element("OutboundProfit").getTextTrim()));
				travelAnnualFinance.setInTravelReturn(Double.parseDouble(element.element("DomesticProfit").getTextTrim()));
				travelAnnualFinance.setManageCost(Double.parseDouble(element.element("ManagementFee").getTextTrim()));
				travelAnnualFinance.setTaxes(Double.parseDouble(element.element("Tax").getTextTrim()));
				travelAnnualFinance.setFinancialCost(Double.parseDouble(element.element("FinanceFee").getTextTrim()));
				travelAnnualFinance.setInterestCost(Double.parseDouble(element.element("InterestPay").getTextTrim()));
				travelAnnualFinance.setOperatingIncome(Double.parseDouble(element.element("OperationProfit").getTextTrim()));
				travelAnnualFinance.setTotalReturn(Double.parseDouble(element.element("ProfitTotal").getTextTrim()));
				travelAnnualFinance.setIncomeTax(Double.parseDouble(element.element("IncomeTax").getTextTrim()));
				travelAnnualFinance.setEmployeeSalary(Double.parseDouble(element.element("AnnualPayTotal").getTextTrim()));
				travelAnnualFinance.setNumAverage(Integer.parseInt(element.element("YearendEmployment").getTextTrim()));
				travelAnnualFinance.setCollege(Integer.parseInt(element.element("JuniorEmployment").getTextTrim()));
				travelAnnualFinance.setDepreciation(Double.parseDouble(element.element("AccumulatedDepreciation").getTextTrim()));
				travelAnnualFinance.setYearDepreciation(Double.parseDouble(element.element("YearDepreciation").getTextTrim()));
				travelAnnualFinance.setTravelExpense(Double.parseDouble(element.element("TravelExpenses").getTextTrim()));
				travelAnnualFinance.setValueVariation(Double.parseDouble(element.element("Gains").getTextTrim()));
				travelAnnualFinance.setInvest(Double.parseDouble(element.element("ReturnInvestment").getTextTrim()));
				travelAnnualFinance.setWhetherPerform((element.element("WhetherPerform").getTextTrim()));
				travelAnnualFinance.setLoss((element.element("Loss").getTextTrim()));
				travelAnnualFinance.setExtraGains(Double.parseDouble(element.element("NonIncome").getTextTrim()));
				travelAnnualFinance.setGovernmentSubsidies(Double.parseDouble(element.element("Subsidies").getTextTrim()));
				travelAnnualFinance.setSellingExpense(Double.parseDouble(element.element("CostSales").getTextTrim()));
				travelAnnualFinance.setGross(Double.parseDouble(element.element("GrossProfit").getTextTrim()));
				travelAnnualFinance.setAddTex(Double.parseDouble(element.element("TheVAT").getTextTrim()));
				travelAnnualFinance.setTourNum(Integer.parseInt(element.element("LaborContract").getTextTrim()));
				travelAnnualFinance.setLeadGroup(Integer.parseInt(element.element("LeaderNumber").getTextTrim()));
				travelAnnualFinance.setStoreNum(Integer.parseInt(element.element("StoresNumber").getTextTrim()));
				travelAnnualFinance.setBranchOffice(Integer.parseInt(element.element("BranchNumber").getTextTrim()));
				travelAnnualFinance.setSubsidiary(Integer.parseInt(element.element("SubsidiariesNumber").getTextTrim()));
//				travelAnnualFinance.setReportTime(element.element("Loss").getTextTrim());
				list.add(travelAnnualFinance);
			}
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		systemService.batchSave(list);
		System.out.println("-----------------------------getFinance结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 旅行社通知同步
	 * @param request
	 */
	@RequestMapping(params = "getTravelNotice")
	public void getTravelNotice(HttpServletRequest request) {
		System.out.println("-----------------------------getTravelNotice开始同步-----------------------------");
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = ServiceInstance.getTravelAgencyService();
		GetNoticeResponseGetNoticeResult rs = null;
		List<Notice> noticeList = new ArrayList<Notice>();
		try {
			rs = travelAgencyService.getNotice("002008013", "sql*lyj_baiwang@2017");
			Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
			Element root = doc.getRootElement();
			List<Element> nodes = root.element("NewDataSet").elements();
			for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
				Element element = iterator.next();
				Notice notice = new Notice();
				notice.setTitle(element.element("Title").getTextTrim());
				notice.setContent(element.element("Content").getTextTrim());
				notice.setPublishDate(element.element("InputDate").getTextTrim().substring(0,10));
				notice.setRange("2");
				notice.setStatus("1");
				notice.setSource("0");
				noticeList.add(notice);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		systemService.batchSave(noticeList);
		System.out.println("-----------------------------getTravelNotice结束同步-----------------------------");
	}
	
}
