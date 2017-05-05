package com.zzc.web.htoal.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzc.web.htoal.entity.Holiday;
import com.zzc.web.htoal.entity.HotelAnnual;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.HotelQuarter;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.notice.entity.Notice;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.touristoffices.entity.TouristOffices;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.provinceHotel.Audit;
import com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo;
import com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult;
import com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult;
import com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult;
import com.zzc.webservice.provinceHotel.MoonFestivalInfo;
import com.zzc.webservice.provinceHotel.TraveAgenciesBasic;
import com.zzc.webservice.provinceHotel.WebService_SanYaSoapProxy;

@Scope("prototype")
@Controller
@RequestMapping("/hoteldataupdatecontroller")
public class HoteldataUpdateController {
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * 同步星级酒店信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getUserInfo")
	public void getUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		System.out
				.println("-----------------------------getUserInfo开始同步-----------------------------");
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/getUserInfo";
		for (int page = 1; page <= 50; page++) {
			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "start_page=" + URLEncoder.encode(String.valueOf(page), "UTF-8");
				content += "&page_nums=" + URLEncoder.encode("100", "UTF-8");
				// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
				out.writeBytes(content);
				out.flush();
				out.close();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
				reader.readLine();
				String line = reader.readLine()
						.replace("<string xmlns=\"http://tempuri.org/\">", "")
						.replace("</string>", "");

				System.out.println(line);
				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String data = jsonObject.get("data").toString();
				JSONArray list = JSON.parseArray(data);

				// 格式化时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:dd:mm");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				
				List<Hotelmanage> hotelList = new ArrayList<>();
				List<HotelmanageSta> hotelStaList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					JSONObject obj = JSON.parseObject(list.get(i).toString());
					
					if(!"审核通过".equals(obj.getString("Sheng"))) continue;
					if(!"0".equals(obj.getString("IsDelete"))) continue;
					
					Hotelmanage hotelManage = new Hotelmanage();
					
					HotelmanageSta hotelManageSta = new HotelmanageSta();
					hotelManage.setOnBuinessSeason("1");
					hotelManageSta.setOnBuinessSeason("1");
					
					hotelManage.setOnBuinessYear("1");
					hotelManageSta.setOnBuinessYear("1");
					// 1,省级系统；2,国家系统
					hotelManage.setSourceType("2");
					hotelManageSta.setSourceType("2");
					// Code Var50 不允许 标牌编码
					hotelManage.setCode(obj.getString("Code"));
					hotelManageSta.setCode(obj.getString("Code"));
					// TableDate DateTime 不允许 报表时间（页面左上角）
					try {
						hotelManage.setW_time(sdf1.parse(obj.getString("TableDate").substring(0, 10)));
						hotelManageSta.setW_time(sdf1.parse(obj.getString("TableDate").substring(0, 10)));
					} catch (ParseException e) {
						e.printStackTrace();
						hotelManage.setW_time(null);
						hotelManageSta.setW_time(null);
					}
					hotelManage.setVerifyTime(new Date());
					hotelManageSta.setVerifyTime(new Date());
					hotelManage.setVerifyTime1(new Date());
					hotelManageSta.setVerifyTime1(new Date());
					// UnitName varchar(100) 不允许 单位名称
					hotelManage.setUnitname(obj.getString("UnitName"));
					hotelManageSta.setUnitname(obj.getString("UnitName"));
					// Delegate Var50 不允许 法定代表人
					hotelManage.setLegalPerson(obj.getString("Delegate"));
					hotelManageSta.setLegalPerson(obj.getString("Delegate"));
					// UserName Var50 不允许 组织机构代码
					hotelManage.setOrganizationNum(obj.getString("UserName"));
					hotelManageSta.setOrganizationNum(obj.getString("UserName"));
					hotelManage.setUsername(obj.getString("UserName"));
					hotelManageSta.setUsername(obj.getString("UserName"));
					// Password Var50 允许 密码
					hotelManage.setPassword(obj.getString("Password"));
					hotelManageSta.setPassword(obj.getString("Password"));
					// FillDate DateTime 不允许 报出日期
					hotelManage.setFillDate(obj.getString("FillDate").substring(0,
							10));
					hotelManageSta.setFillDate(obj.getString("FillDate").substring(
							0, 10));
					// UnitMaster Var50 允许 单位负责人
					hotelManage.setManager(obj.getString("UnitMaster"));
					hotelManageSta.setManager(obj.getString("UnitMaster"));
					// Filler Var50 允许 填表人
					hotelManage.setWriter(obj.getString("Filler"));
					hotelManageSta.setWriter(obj.getString("Filler"));
					// FillerTel Var50 允许 填表人电话
					hotelManage.setFillerTel(obj.getString("FillerTel"));
					hotelManageSta.setFillerTel(obj.getString("FillerTel"));
					// Adessr varchar(100) 不允许 单位所在地
					hotelManage.setAddress(obj.getString("Adessr"));
					hotelManageSta.setAddress(obj.getString("Adessr"));
					// Province Var50 允许 单位省
					// City Var50 允许 单位市
					// Postcode Var50 不允许 邮政编码
					hotelManage.setZipcode(obj.getString("Postcode"));
					hotelManageSta.setZipcode(obj.getString("Postcode"));
					// RegionalismCode Var50 允许 行政区划码
					hotelManage
							.setRegionalismCode(obj.getString("RegionalismCode"));
					hotelManageSta.setRegionalismCode(obj
							.getString("RegionalismCode"));
					// Tel Var50 不允许 电话号码
					hotelManage.setTelephone(obj.getString("Tel"));
					hotelManageSta.setTelephone(obj.getString("Tel"));
					// TelNo Var50 允许 电话分机号码
					hotelManage.setTelNo(obj.getString("TelNo"));
					hotelManageSta.setTelNo(obj.getString("TelNo"));
					// Mobile Var50 允许 移动电话
					hotelManage.setMobile(obj.getString("Mobile"));
					hotelManageSta.setMobile(obj.getString("Mobile"));
					// Fax Var50 不允许 传真号码
					hotelManage.setFax(obj.getString("Fax"));
					hotelManageSta.setFax(obj.getString("Fax"));
					// FaxNo Var50 允许 传真分机号码
					hotelManage.setFaxNo(obj.getString("FaxNo"));
					hotelManageSta.setFaxNo(obj.getString("FaxNo"));
					// WebSite varchar(200) 允许 互联网网址
					hotelManage.setWeburl(obj.getString("WebSite"));
					hotelManageSta.setWeburl(obj.getString("WebSite"));
					// Email varchar(200) 允许 电子邮件信箱
					hotelManage.setEmail(obj.getString("Email"));
					hotelManageSta.setEmail(obj.getString("Email"));
					// RegistrationType Var50 不允许 旅行社类别
					hotelManage.setRegistrationType(obj
							.getString("RegistrationType"));
					hotelManageSta.setRegistrationType(obj
							.getString("RegistrationType"));
					// UnitType Var50 不允许 单位类别
					hotelManage.setUnitType(obj.getString("UnitType"));
					hotelManageSta.setUnitType(obj.getString("UnitType"));
					// AccommodationStar Var50 不允许 住宿设施星级
					try {
						hotelManage.setHousegrade(String.valueOf((Integer.parseInt(obj
								.getString("Code").substring(2, 3)) + 3)));
						hotelManageSta.setHousegrade(String.valueOf((Integer.parseInt(obj
								.getString("Code").substring(2, 3)) + 3)));
					} catch (Exception e) {
						hotelManage.setHousegrade("0");
						hotelManageSta.setHousegrade("0");
					}
					// TravelAgencyType Var50 不允许 企业登记注册类型
					hotelManage.setRegisterstyle(obj.getString("TravelAgencyType"));
					hotelManageSta.setRegisterstyle(obj
							.getString("TravelAgencyType"));
					// SceneSpotLevel Var50 不允许 旅游区（点）等级
					hotelManage.setSceneSpotLevel(obj.getString("SceneSpotLevel"));
					hotelManageSta.setSceneSpotLevel(obj
							.getString("SceneSpotLevel"));
					// IsCheck Var50 是否审核
					// IsDelete Var50 是否删除
					// Demo Var200 年份
					// Kuo Var50 用户填报添加随机数，证明已经填报过
					// Ip Var50 最后一次的ip
					// Sheng Var50 省审核
					hotelManage.setSheng(obj.getString("Sheng"));
					hotelManageSta.setSheng(obj.getString("Sheng"));
					// Shi Var50 市审核
					hotelManage.setShi(obj.getString("Shi"));
					hotelManageSta.setShi(obj.getString("Shi"));
					// Xian Var50 县审核
					hotelManage.setXian(obj.getString("Xian"));
					hotelManageSta.setXian(obj.getString("Xian"));
					hotelManage.setStatus(Status.passAudit);
					hotelManageSta.setStatus(Status.passAudit);
					
					hotelManage.setDistrictStatus(Status.noAudit);
					hotelManageSta.setDistrictStatus(Status.noAudit);
					
					hotelManage.setProvinceStatus(Status.passAudit);
					hotelManageSta.setProvinceStatus(Status.passAudit);
					
					hotelManage.setCountryStatus(Status.passAudit);
					hotelManageSta.setCountryStatus(Status.passAudit);
					
					hotelManage.setOnBuinessSeason("1");
					hotelManageSta.setOnBuinessYear("1");
					
					hotelManage.setCounty("0");
					hotelManageSta.setCounty("0");
					
					hotelManage.setBay("0");
					hotelManageSta.setBay("0");
					
					// 分数
					hotelManage.setScore(40);
					hotelManageSta.setScore(40);
					
					hotelList.add(hotelManage);
					hotelStaList.add(hotelManageSta);
				}
				// 关闭连接
				connection.disconnect();
				reader.close();

				// 添加用户
				List<Hotelmanage> hotelNewList = new ArrayList<>();
				List<HotelmanageSta> hotelStaNewList = new ArrayList<>();
				for (int i = 0; i < hotelList.size(); i++) {
					Hotelmanage hotelManage = hotelList.get(i);
					HotelmanageSta hotelManageSta = hotelStaList.get(i);
					TSUser user = null;
					if(hotelManage.getUsername() == null || hotelManage.getUsername().length() == 0){
						user = AutoAddUser.add(
								hotelManage.getUsername(),
								GlobalParams.XINGJIJIUDIAN, hotelManage.getUnitname());
					}else{
						user = AutoAddUser.interfaceAdd(
								hotelManage.getUsername(),
								GlobalParams.XINGJIJIUDIAN, hotelManage.getUnitname());
					}
					hotelManage.setId(user.getId());
					hotelManage.setHuserId(user.getId());
					hotelManageSta.setId(user.getId());
					hotelManageSta.setHuserId(user.getId());
					hotelNewList.add(hotelManage);
					hotelStaNewList.add(hotelManageSta);
				}
				// 保存酒店基本信息
				systemService.batchSave(hotelNewList);
				systemService.batchSave(hotelStaNewList);
				System.out
						.println("-----------------------------getUserInfo结束同步-----------------------------");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 同步星级酒店年报信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getHotelYearInfo")
	public void getHotelYearInfo(HttpServletRequest request,
			HttpServletResponse response) {
		System.out
				.println("-----------------------------getHotelYearInfo开始同步-----------------------------");
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_year_info";
		String year = request.getParameter("year");
		try {
			// 获取所有酒店
			List<Hotelmanage> hotelList = systemService
					.getList(Hotelmanage.class);
			List<HotelAnnual> annualList = new ArrayList<>();
			for (Iterator iterator = hotelList.iterator(); iterator.hasNext();) {
				Hotelmanage hotelmanage = (Hotelmanage) iterator.next();
				// 是否为国家系统数据
				if(!"2".equals(hotelmanage.getSourceType())) continue;
				// 暂停0.2s
				Thread.sleep(500);
				
				System.out.println(hotelmanage.getUsername());
				try {
				HttpURLConnection connection = this.getConnection(url);
				DataOutputStream out = null;
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = "code="
						+ URLEncoder.encode(hotelmanage.getCode(),
								"UTF-8");
				content += "&year=" + URLEncoder.encode(year, "UTF-8");
				// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
				out.writeBytes(content);
				out.flush();
				out.close();
				BufferedReader reader = null;
				
					reader = new BufferedReader(new InputStreamReader(
							connection.getInputStream(), "UTF-8"));
				reader.readLine();
				String line = reader.readLine()
						.replace("<string xmlns=\"http://tempuri.org/\">", "")
						.replace("</string>", "");

				System.out.println(line);

				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String data = jsonObject.get("data").toString();
				JSONArray list = JSON.parseArray(data);

				// 格式化时间
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:dd:mm");
				for (int i = 0; i < list.size(); i++) {
					JSONObject obj = JSON.parseObject(list.get(i).toString());
					
					if(!"审核通过".equals(obj.getString("Sheng"))) continue;
					if(!"0".equals(obj.getString("IsDelete"))) continue;
					
					HotelAnnual hotelAnnual = new HotelAnnual();
					hotelAnnual.setCode(hotelmanage.getCode());
					try {
						hotelAnnual.setReportTime(obj.getString("FillDate").substring(0,10));
					} catch (Exception e) {
						e.printStackTrace();
						hotelAnnual.setReportTime(null);
					}
					hotelAnnual.setHotelmanage(hotelmanage);
					hotelAnnual.setStatus("4");
					hotelAnnual.setCountryState("4");
					hotelAnnual.setHid(hotelmanage.getId());
					hotelAnnual.setName(hotelmanage.getUnitname());
					hotelAnnual.setPerson(hotelmanage.getManager());
					hotelAnnual.setFiller(obj.getString("Filler"));
					hotelAnnual.setFillerTel(obj.getString("FillerTel"));
					hotelAnnual.setStreamTotal(obj
							.getDouble("CurrentAssetsTotal"));
					hotelAnnual.setDeposit(obj.getDouble("Deposit"));
					hotelAnnual.setLongInvest(obj.getDouble("LongTermInvest"));
					hotelAnnual.setFixedAssets(obj.getDouble("FixAssetsTotal"));
					hotelAnnual.setFixedPrice(obj
							.getDouble("FixAssetsInitialPrice"));
					hotelAnnual.setDepreciation(obj
							.getDouble("AccumulatedDepreciation"));
					hotelAnnual.setYearDepreciation(obj
							.getDouble("CurrentYearDepreciation"));
					hotelAnnual.setAssetTotal(obj.getDouble("CapitalTotal"));
					hotelAnnual.setLiabilitiesTotal(obj
							.getDouble("TotalLiabilities"));
					hotelAnnual.setPossessorTotal(obj
							.getDouble("OwnerBenefitTotal"));
					hotelAnnual.setRealIncome(obj.getDouble("RealCapital"));
					hotelAnnual.setNationalCapital(obj
							.getString("NationalCapital"));
					hotelAnnual.setCollectiveCapital(obj
							.getString("Collectivecapital"));
					hotelAnnual.setPersonalCapital(obj
							.getString("PersonalCapital"));
					hotelAnnual.setCorporateCapital(obj
							.getString("CorporateCapital"));
					hotelAnnual.setHMTCapital(obj.getString("HMTCapital"));
					hotelAnnual.setForeignerCapital(obj
							.getString("ForeignerCapital"));
					hotelAnnual.setOperationIncome(obj
							.getDouble("OperationIncome"));
					hotelAnnual.setRoomIncome(obj.getDouble("RoomRevenue"));
					hotelAnnual
							.setCanteeIncome(obj.getDouble("CateringIncome"));
					hotelAnnual
							.setOperationCost(obj.getDouble("OperationCost"));
					hotelAnnual.setOperationFee(obj.getDouble("OperationFee"));
					hotelAnnual.setOprFeeAndTax(obj
							.getDouble("OperationTaxAddition"));
					hotelAnnual.setBusinessProfit(obj
							.getString("BusinessProfit"));
					hotelAnnual
							.setManageCost(obj.getDouble("ManagementFee"));
					hotelAnnual.setTaxes(obj.getDouble("Tax"));
					hotelAnnual.setFinancialCost(obj.getDouble("FinanceFee"));
					hotelAnnual.setInterestCost(obj.getDouble("InterestPay"));
					hotelAnnual.setOpreationIntrest(obj
							.getDouble("OperationProfit"));
					hotelAnnual.setProfitTotal(obj.getDouble("ProfitTotal"));
					hotelAnnual.setOwnTax(obj.getDouble("IncomeTax"));
					hotelAnnual.setAnnualPayTotal(obj
							.getString("AnnualPayTotal"));
					hotelAnnual.setPeople(obj.getInteger("YearendEmployment"));
					hotelAnnual.setCollege(obj.getInteger("JuniorEmployment"));
					hotelAnnual.setRoom(obj.getString("Room"));
					hotelAnnual.setBed(obj.getString("Bed"));
					hotelAnnual.setActualRent(obj.getString("ActualRent"));
					hotelAnnual.setForHire(obj.getString("ForHire"));
					hotelAnnual.setShenHeDateTime(obj.getString(
							"ShenHeDateTime").substring(0, 10));
					hotelAnnual.setOtherIncome(obj.getDouble("OtherIncome"));
					hotelAnnual.setSellFee(obj.getDouble("CostSales")); //销售费用
					hotelAnnual.setAddedValueTax(obj.getDouble("TheVAT")); // 本年应交增值税
					hotelAnnual.setExtraneousIncome(obj.getDouble("NonIncome")); // 营业外收入
					hotelAnnual.setSubsidies(obj.getDouble("Subsidies")); // 政府补贴
					hotelAnnual.setConsumption(obj.getDouble("EnergyCosts")); // 能耗成本
					hotelAnnual.setSheng(obj.getString("Sheng"));
					hotelAnnual.setShi(obj.getString("Shi"));
					hotelAnnual.setXian(obj.getString("Xian"));
					hotelAnnual.setYear(year);
					hotelAnnual.setDistrictStatus(Status.noAudit);
					hotelAnnual.setCountryState(Status.passAudit);
					hotelAnnual.setReportPerson(obj.getString("Filler"));
					annualList.add(hotelAnnual);
				}
				// 关闭连接
				connection.disconnect();
				reader.close();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			systemService.batchSave(annualList);
			System.out
					.println("-----------------------------getHotelYearInfo结束同步-----------------------------");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 同步星级酒店季报信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getHotelSeaInfo")
	public void getHotelSeaInfo(HttpServletRequest request,
			HttpServletResponse response) {
		System.out
				.println("-----------------------------getHotelSeaInfo开始同步-----------------------------");
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/hotel_sea_info";
		String year = request.getParameter("year");
		try {
			// 获取所有酒店
			List<Hotelmanage> hotelList = systemService
					.getList(Hotelmanage.class);
			List<HotelQuarter> quarterList = new ArrayList<>();
			for (int j = 0; j < hotelList.size(); j++) {
				Hotelmanage hotelmanage = hotelList.get(j);
				if("1".equals(hotelmanage.getSourceType())) continue;
				for (int quarter = 1; quarter < 5; quarter++) {
					// 暂停1s
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
					HttpURLConnection connection = this.getConnection(url);
					DataOutputStream out = null;
					out = new DataOutputStream(connection.getOutputStream());
					// The URL-encoded contend
					// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
					String content = "code="
							+ URLEncoder.encode(hotelmanage.getCode(), "UTF-8");
					content += "&year=" + URLEncoder.encode(year, "UTF-8");
					content += "&sea="
							+ URLEncoder.encode(String.valueOf(quarter),
									"UTF-8");
					System.out.println(content);
					// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
					out.writeBytes(content);
					out.flush();
					out.close();
					BufferedReader reader = null;
						reader = new BufferedReader(new InputStreamReader(
								connection.getInputStream(), "UTF-8"));
					reader.readLine();
					String line = reader
							.readLine()
							.replace("<string xmlns=\"http://tempuri.org/\">",
									"").replace("</string>", "");

					//System.out.println(line);

					// 数据转为json对象
					JSONObject jsonObject = JSON.parseObject(line);
					String data = jsonObject.get("data").toString();
					JSONArray list = JSON.parseArray(data);

					// 格式化时间
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:dd:mm");
					for (int i = 0; i < list.size(); i++) {
						JSONObject obj = JSON.parseObject(list.get(i)
								.toString());
						
						if(!"审核通过".equals(obj.getString("Sheng"))) continue;
						if(!"0".equals(obj.getString("IsDelete"))) continue;
						
						HotelQuarter hotelQuarter = new HotelQuarter();
						hotelQuarter.setHid(hotelmanage.getId());
						hotelQuarter.setHotelmanage(hotelmanage);
						hotelQuarter.setCountryState("4");
						hotelQuarter.setOrganizationNum(hotelmanage.getOrganizationNum());
						hotelQuarter.setUnitname(hotelmanage.getUnitname());
						hotelQuarter.setWriter(obj.getString("Filler"));
						hotelQuarter.setMobile(obj.getString("FillerTerl"));
						try {
							hotelQuarter.setWriterDate(obj.getString("tanbleDate").substring(0, 10));
						} catch (Exception e) {
							e.printStackTrace();
							hotelQuarter.setWriterDate(null);
						}
						hotelQuarter.setManager(hotelmanage.getManager());
						hotelQuarter.setTotalIncome(Double.parseDouble(obj
								.getString("OperationCost")));
						hotelQuarter.setRoomIncome(Double.parseDouble(obj
								.getString("RoomRevenue")));
						hotelQuarter.setCanteenIncome(Double.parseDouble(obj
								.getString("CateringIncome")));
						hotelQuarter.setRoomnum(Integer.parseInt(obj
								.getString("Room")));
						hotelQuarter.setBednum(Integer.parseInt(obj
								.getString("Bed")));
						hotelQuarter.setRealNights(Integer.parseInt(obj
								.getString("ActualRent")));
						hotelQuarter.setCanNights(Integer.parseInt(obj
								.getString("ForHire")));
						hotelQuarter.setYear(year);
						hotelQuarter.setQuarter(quarter);
						hotelQuarter.setStatus("4");
						hotelQuarter.setDistrictStatus(Status.noAudit);
						hotelQuarter.setCountryState(Status.passAudit);
						quarterList.add(hotelQuarter);
					}
					// 关闭连接
					connection.disconnect();
					reader.close();
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
//				if(quarterList.size() > 10) break;
			}
			systemService.batchSave(quarterList);
			System.out
					.println("-----------------------------getHotelSeaInfo结束同步-----------------------------");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 获取HTTP请求
	 * 
	 * @param url
	 * @return HttpURLConnection
	 */
	private HttpURLConnection getConnection(String url) {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl;
		try {
			postUrl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		// 打开连接
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) postUrl.openConnection();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
			return null;
		}

		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 默认是 GET方式
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		// Post 请求不能使用缓存
		connection.setUseCaches(false);

		connection.setInstanceFollowRedirects(true);

		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("apikey",
				"b76a843c8e0b7ccc1f8c51302557ae75");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		return connection;
	}

	// //////////////////////////////////省系统接口//////////////////////////////////////
	/**
	 * 
	 * 同步省酒店数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getProvinceHotel")
	public void getProvinceHotel(HttpServletRequest request,
			HttpServletResponse response) {
		System.out
				.println("-----------------------------getProvinceHotel开始同步-----------------------------");
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance
				.getHotelProvinceService();
		TraveAgenciesBasic traveAgenciesBasic = new com.zzc.webservice.provinceHotel.TraveAgenciesBasic();
		String org = "0001-01-01 00:00:00";
		SimpleDateFormat sdforg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date orgDate = null;
		Calendar cal=Calendar.getInstance();
		try {
			orgDate = sdforg.parse(org);
		} catch (ParseException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		cal.setTime(orgDate);
		traveAgenciesBasic.setOrcanRegistertime(cal);
		traveAgenciesBasic.setRatifiedTime(cal);
		traveAgenciesBasic.setCreateTime(cal);
		traveAgenciesBasic.setUpdateTime(cal);
		traveAgenciesBasic.setUserRole(1);
		traveAgenciesBasic.setFlag(1);
//		traveAgenciesBasic.setStarHotel(0);
		com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult rs = null;
		try {
			rs = hotelProvinceService.getHotel(
					GlobalParams.hotelProvinceCode, traveAgenciesBasic);
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
//		System.out.println(rs.get_any()[0].toString());
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(rs.get_any()[0].toString());
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		// 没有查询到数据
		if (root.element("DataSet") == null) {
			System.out.println("null");
		}
		// 格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:dd:mm");
		List<Hotelmanage> hotelList = new ArrayList<>();
		List<HotelmanageSta> hotelStaList = new ArrayList<>();

		List<Hotelmanage> starHotelList = systemService.getList(Hotelmanage.class);
		
		// 遍历元素
		List<Element> nodes = root.element("DataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			
			boolean repeat = false;
			// 检查是否与国家星级酒店重复
			for (int i = 0; i < starHotelList.size(); i++) {
				if(starHotelList.get(i).getUnitname()!= null && starHotelList.get(i).getUnitname().length() != 0){
					if(starHotelList.get(i).getUnitname().equals(element.element("OrcanName"))) repeat = true;
				}
			}
			// 重复则不同步
			if(repeat) continue;
			
			Hotelmanage hotelmanage = new Hotelmanage();
			HotelmanageSta hotelmanageSta = new HotelmanageSta();
			try {
				// 信息来源
				hotelmanage.setSourceType("1");
				hotelmanageSta.setSourceType("1");
				
				hotelmanage.setOnBuinessSeason("1");
				hotelmanageSta.setOnBuinessSeason("1");
				
				hotelmanage.setOnBuinessYear("1");
				hotelmanageSta.setOnBuinessYear("1");
				
				// / 标牌编号
				hotelmanage.setUsername(element.element("UserAccount")
						.getTextTrim());
				hotelmanageSta.setUsername(element.element("UserAccount")
						.getTextTrim());
				// 控股情况
				hotelmanage.setShareHolding(element.element("ShareHolding")
						.getTextTrim());
				hotelmanageSta.setShareHolding(element.element("ShareHolding")
						.getTextTrim());
				// / 组织机构代码 *
				hotelmanage
						.setOrganizationNum(element.element("OrcanCode") == null ? ""
								: element.element("OrcanCode").getTextTrim());
				hotelmanageSta.setOrganizationNum(element
						.element("UserAccount") == null ? "" : element.element(
						"UserAccount").getTextTrim());
				// / 单位名称 *
				hotelmanage
						.setUnitname(element.element("OrcanName") == null ? ""
								: element.element("OrcanName").getTextTrim());
				hotelmanageSta
						.setUnitname(element.element("OrcanName") == null ? ""
								: element.element("OrcanName").getTextTrim());
				// / 法定代表人 *
				hotelmanage.setLegalPerson(element
						.element("LegalRepresentative") == null ? "" : element
						.element("LegalRepresentative").getTextTrim());
				hotelmanageSta.setLegalPerson(element
						.element("LegalRepresentative") == null ? "" : element
						.element("LegalRepresentative").getTextTrim());
				// / 工商注册登记时间 *(年/月/日)
				try {
					hotelmanage.setRegistertime(element
							.element("OrcanRegistertime") == null ? null : sdf
							.parse(element.element("OrcanRegistertime")
									.getTextTrim().split(" ")[0]));
					hotelmanageSta.setRegistertime(element
							.element("OrcanRegistertime") == null ? null : sdf
							.parse(element.element("OrcanRegistertime")
									.getTextTrim().split(" ")[0]));
				} catch (Exception e) {
					hotelmanage.setRegistertime(null);
					hotelmanageSta.setRegionalismCode(null);
				}
				// / 县（区、市、旗）
				String countyName = "";
				switch (element.element("CountyName") == null ? "" : element
						.element("CountyName").getTextTrim()) {
				case "市辖区":
					countyName = "0";
					break;
				case "崖州区":
					countyName = "1";
					break;
				case "海棠区":
					countyName = "2";
					break;
				case "天涯区":
					countyName = "3";
					break;
				case "吉阳区":
					countyName = "4";
					break;
				}
				hotelmanage.setCounty(countyName);
				hotelmanageSta.setCounty(countyName);

				hotelmanage
						.setAddress(element.element("StreetName") == null ? ""
								: element.element("StreetName").getTextTrim());
				hotelmanageSta
						.setAddress(element.element("StreetName") == null ? ""
								: element.element("StreetName").getTextTrim());

				hotelmanage
						.setTelephone(element.element("TelephoneNumber") == null ? ""
								: element.element("TelephoneNumber")
										.getTextTrim());
				hotelmanageSta
						.setTelephone(element.element("TelephoneNumber") == null ? ""
								: element.element("TelephoneNumber")
										.getTextTrim());

				hotelmanage
						.setTelNo(element.element("ExtensionNumber") == null ? ""
								: element.element("ExtensionNumber")
										.getTextTrim());
				hotelmanageSta
						.setTelNo(element.element("ExtensionNumber") == null ? ""
								: element.element("ExtensionNumber")
										.getTextTrim());

				hotelmanage
						.setFax(element.element("Phototelephony") == null ? ""
								: element.element("Phototelephony")
										.getTextTrim());
				hotelmanageSta
						.setFax(element.element("Phototelephony") == null ? ""
								: element.element("Phototelephony")
										.getTextTrim());

				hotelmanage.setFaxNo(element
						.element("PhototelephonyExtensionNumber") == null ? ""
						: element.element("PhototelephonyExtensionNumber")
								.getTextTrim());
				hotelmanageSta.setFaxNo(element
						.element("PhototelephonyExtensionNumber") == null ? ""
						: element.element("PhototelephonyExtensionNumber")
								.getTextTrim());

				hotelmanage
						.setMobile(element.element("MobileTelephone") == null ? ""
								: element.element("MobileTelephone")
										.getTextTrim());
				hotelmanageSta
						.setMobile(element.element("MobileTelephone") == null ? ""
								: element.element("MobileTelephone")
										.getTextTrim());

				hotelmanage
						.setZipcode(element.element("PostalCode") == null ? ""
								: element.element("PostalCode").getTextTrim());
				hotelmanageSta
						.setZipcode(element.element("PostalCode") == null ? ""
								: element.element("PostalCode").getTextTrim());

				hotelmanage.setEmail(element.element("EMail") == null ? ""
						: element.element("EMail").getTextTrim());
				hotelmanageSta.setEmail(element.element("EMail") == null ? ""
						: element.element("EMail").getTextTrim());

				hotelmanage
						.setWeburl(element.element("IntenetUrl") == null ? ""
								: element.element("IntenetUrl").getTextTrim());
				hotelmanageSta
						.setWeburl(element.element("IntenetUrl") == null ? ""
								: element.element("IntenetUrl").getTextTrim());

				// / 企业登记注册类型 *
				System.out.println("企业登记注册类型:"
						+ element.element("OrcanType").getTextTrim());
				// / 企业登记注册类型名称
				try {
					hotelmanage.setRegistrationType(element
							.element("OrcanTypeName") == null ? ""
							: element.element("OrcanTypeName").getTextTrim()
									.split(" ")[1]);
					hotelmanageSta.setRegistrationType(element
							.element("OrcanTypeName") == null ? ""
							: element.element("OrcanTypeName").getTextTrim()
									.split(" ")[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				// / 饭店星级 *
				if (element.element("StarHotel") != null
						&& !"0".equals(element.element("StarHotel")
								.getTextTrim())) {
					hotelmanage.setHousegrade(String.valueOf(Integer
							.parseInt(element.element("StarHotel")
									.getTextTrim()) + 3));
					hotelmanageSta.setHousegrade(String.valueOf(Integer
							.parseInt(element.element("StarHotel")
									.getTextTrim()) + 3));
				} else {
					hotelmanage.setHousegrade("0");
					hotelmanageSta.setHousegrade("0");
				}
				// / 客房数 *
				hotelmanage
						.setHousenum(element.element("RoomNumber") == null ? 0
								: Integer.parseInt(element
										.element("RoomNumber").getTextTrim()));
				hotelmanageSta
						.setHousenum(element.element("RoomNumber") == null ? 0
								: Integer.parseInt(element
										.element("RoomNumber").getTextTrim()));
				// / 床位数 *
				hotelmanage.setBednum(element.element("BedNumber") == null ? 0
						: Integer.parseInt(element.element("BedNumber")
								.getTextTrim()));
				hotelmanageSta
						.setBednum(element.element("BedNumber") == null ? 0
								: Integer.parseInt(element.element("BedNumber")
										.getTextTrim()));
				// / 单位负责人 *
				hotelmanage
						.setManager(element.element("UnitLeader") == null ? ""
								: element.element("UnitLeader").getTextTrim());
				hotelmanageSta
						.setManager(element.element("UnitLeader") == null ? ""
								: element.element("UnitLeader").getTextTrim());
				// / 填报人
				hotelmanage
						.setWriter(element.element("OperateUser") == null ? ""
								: element.element("OperateUser").getTextTrim());
				hotelmanageSta
						.setWriter(element.element("OperateUser") == null ? ""
								: element.element("OperateUser").getTextTrim());
				// / 填报人电话
				hotelmanage
						.setFillerTel(element.element("OperateTelephone") == null ? ""
								: element.element("OperateTelephone")
										.getTextTrim());
				hotelmanageSta
						.setFillerTel(element.element("OperateTelephone") == null ? ""
								: element.element("OperateTelephone")
										.getTextTrim());
				try {
					// / 填报时间
					hotelmanage
							.setFillDate(element.element("CreateTime") == null ? ""
									: element.element("CreateTime")
											.getTextTrim().substring(0, 10)
											.replaceAll("/", "-"));
					hotelmanageSta
							.setFillDate(element.element("CreateTime") == null ? ""
									: element.element("CreateTime")
											.getTextTrim().substring(0, 10)
											.replaceAll("/", "-"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				hotelmanage.setStatus(Status.passAudit);
				hotelmanageSta.setStatus(Status.passAudit);
				
				// TODO
				hotelmanage.setDistrictStatus(Status.noAudit);
				hotelmanageSta.setDistrictStatus(Status.noAudit);
				
				hotelmanage.setProvinceStatus(Status.passAudit);
				hotelmanageSta.setProvinceStatus(Status.passAudit);
				
				hotelmanage.setCountryStatus(Status.passAudit);
				hotelmanageSta.setCountryStatus(Status.passAudit);
				
				hotelmanage.setOnBuinessSeason("1");
				hotelmanageSta.setOnBuinessYear("1");
				
				hotelmanage.setBay("0");
				hotelmanageSta.setBay("0");
				
				hotelmanage.setVerifyTime(new Date());
				hotelmanageSta.setVerifyTime(new Date());
				hotelmanage.setVerifyTime1(new Date());
				hotelmanageSta.setVerifyTime1(new Date());
				
				// 分数
				hotelmanage.setScore(40);
				hotelmanageSta.setScore(40);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
			hotelList.add(hotelmanage);
			hotelStaList.add(hotelmanageSta);
		}
		List<Hotelmanage> newHotelList = new ArrayList<>();
		List<HotelmanageSta> newHotelStaList = new ArrayList<>();
		for (int i = 0; i < hotelList.size(); i++) {
			Hotelmanage hotelManage = hotelList.get(i);
			HotelmanageSta hotelManageSta = hotelStaList.get(i);
			// 添加用户
			TSUser user = null;
			if (hotelManage.getHousegrade() == null
					|| "0".equals(hotelManage.getHousegrade())) {
				if(hotelManage.getUsername() == null || hotelManage.getUsername().length() == 0){
					user = AutoAddUser
							.add(hotelManage.getUsername(),
									GlobalParams.FEIXINGJIJIUDIAN,
									hotelManage.getUnitname());
				}else{
					user = AutoAddUser
							.interfaceAdd(hotelManage.getUsername(),
									GlobalParams.FEIXINGJIJIUDIAN,
									hotelManage.getUnitname());
				}
			} else {
				if(hotelManage.getUsername() == null || hotelManage.getUsername().length() == 0){
					user = AutoAddUser.add(
							hotelManage.getUsername(),
							GlobalParams.XINGJIJIUDIAN, hotelManage.getUnitname());
				}else{
					user = AutoAddUser.interfaceAdd(
							hotelManage.getUsername(),
							GlobalParams.XINGJIJIUDIAN, hotelManage.getUnitname());
				}
			}

			hotelManage.setId(user.getId());
			hotelManage.setHuserId(user.getId());
			hotelManageSta.setId(user.getId());
			hotelManageSta.setHuserId(user.getId());
			newHotelList.add(hotelManage);
			newHotelStaList.add(hotelManageSta);
		}
		systemService.batchSave(newHotelList);
		systemService.batchSave(newHotelStaList);
		System.out
				.println("-----------------------------getProvinceHotel结束同步-----------------------------");
	}

	/**
	 * 
	 * 同步预计接待数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getProvinceHotelEstimate")
	public void getProvinceHotelEstimate(HttpServletRequest request,
			HttpServletResponse response) {
		System.out
				.println("-----------------------------getProvinceHotelEstimate开始同步-----------------------------");

		WebService_SanYaSoapProxy hotelService = new WebService_SanYaSoapProxy();
		com.zzc.webservice.provinceHotel.HotelEstimate he = new com.zzc.webservice.provinceHotel.HotelEstimate();
		// 请求“人天”类型参数
		he.setType(1);
		com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult rs = null;
		try {
			rs = hotelService.getHotelEstimate(
					GlobalParams.hotelProvinceCode, he);
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		System.out.println(rs.get_any()[0]);

		Document doc = null;
		try {
			System.out.println(rs.get_any()[0].toString());
			doc = DocumentHelper.parseText(rs.get_any()[0].toString());
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		// 没有查询到数据
		if (root.element("DataSet") == null) {
			System.out.println("null");
		}
		List<Element> nodes = root.element("DataSet").elements();

		// 请求“人次”类型参数
		he.setType(0);
		try {
			rs = hotelService.getHotelEstimate(
					GlobalParams.hotelProvinceCode, he);
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		System.out.println(rs.get_any()[0]);
		try {
			doc = DocumentHelper.parseText(rs.get_any()[0].toString());
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		root = doc.getRootElement();
		// 没有查询到数据
		if (root.element("DataSet") == null) {
			System.out.println("null");
		}
		List<Element> nodesTimes = root.element("DataSet").elements();
		
		List<com.zzc.web.htoal.entity.HotelEstimate> list = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			Element element = nodes.get(i);
			Element elementTimes = nodesTimes.get(i);
			com.zzc.web.htoal.entity.HotelEstimate hotelEstimate = new com.zzc.web.htoal.entity.HotelEstimate();
			// 已同步
			hotelEstimate.setSourceStatus("1");
			// / 年份
			hotelEstimate.setYear(element.element("Year").getTextTrim());
			// / 月份
			hotelEstimate.setMonth(element.element("Month").getTextTrim());
			// 地区编码
			hotelEstimate.setCountryCode(element.element("CountryCode").getTextTrim());
			// 填报人
			hotelEstimate.setBymanager(element.element("CreateUser") == null ? "" : element.element("CreateUser").getTextTrim());
			// / 国内
			hotelEstimate.setDomesticD(Integer.parseInt(element
					.element("Domestic") == null ? "0" : element.element(
					"Domestic").getTextTrim()));
			hotelEstimate.setDomestic(Integer.parseInt(element
					.element("Domestic") == null ? "0" : elementTimes.element(
					"Domestic").getTextTrim()));
			// / 外国人
			hotelEstimate.setForeignDays(Integer.parseInt(element
					.element("Foreigner") == null ? "0" : element.element(
					"Foreigner").getTextTrim()));
			hotelEstimate.setForeignTimes(Integer.parseInt(element
					.element("Foreigner") == null ? "0" : elementTimes.element(
					"Foreigner").getTextTrim()));
			// / 香港
			hotelEstimate.setHongkongD(Integer.parseInt(element
					.element("Hongkong") == null ? "0" : element.element(
					"Hongkong").getTextTrim()));
			hotelEstimate.setHongkong(Integer.parseInt(element
					.element("Hongkong") == null ? "0" : elementTimes.element(
					"Hongkong").getTextTrim()));
			// / 澳门
			hotelEstimate
					.setMacaoD(Integer
							.parseInt(element.element("macaoD") == null ? "0"
									: element.element("macaoD").getTextTrim()));
			hotelEstimate
					.setMacao(Integer
							.parseInt(element.element("macaoD") == null ? "0"
									: elementTimes.element("macaoD")
											.getTextTrim()));
			// / 台湾
			hotelEstimate
					.setTaiwanD(Integer
							.parseInt(element.element("Taiwan") == null ? "0"
									: element.element("Taiwan").getTextTrim()));
			hotelEstimate
					.setTaiwan(Integer
							.parseInt(element.element("Taiwan") == null ? "0"
									: elementTimes.element("Taiwan")
											.getTextTrim()));
			// / 入境（国外）
			hotelEstimate
					.setEntryD(Integer
							.parseInt(element.element("Abroad") == null ? "0"
									: element.element("Abroad").getTextTrim()));
			hotelEstimate
					.setEntry(Integer
							.parseInt(element.element("Abroad") == null ? "0"
									: elementTimes.element("Abroad")
											.getTextTrim()));
			// / 总计
			hotelEstimate
					.setSumD(Integer.parseInt(element.element("Total") == null ? "0"
							: element.element("Total").getTextTrim()));
			hotelEstimate
					.setSum(Integer.parseInt(element.element("Total") == null ? "0"
							: elementTimes.element("Total").getTextTrim()));
			// / 创建时间
			hotelEstimate
					.setCreateTime(element.element("CreateTime") == null ? ""
							: element.element("CreateTime").getTextTrim().substring(0, 10).replaceAll("/", "-"));
			hotelEstimate.setSourceStatus("1");
			list.add(hotelEstimate);
		}
		systemService.batchSave(list);
		System.out
				.println("-----------------------------getProvinceHotelEstimate结束同步-----------------------------");
	}

	/**
	 * 
	 * 同步省级月报数据
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getProvinceMonthly")
	public void getProvinceMonthly(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("-----------------------------getProvinceMonthly开始同步-----------------------------");
		// 获取所有酒店的信息
		List<Hotelmanage> hotelList = systemService.getList(Hotelmanage.class);
		// 保存的月报数据集合
		List<HotelMonthly> list = new ArrayList<>();
		
		WebService_SanYaSoapProxy hotelService = new WebService_SanYaSoapProxy();
		// 遍历酒店
		for (int j = 0; j < hotelList.size(); j++) {
			Hotelmanage hotelmanage = hotelList.get(j);
			if("2".equals(hotelmanage.getSourceType())) continue;
			try {
				// 暂停0.2s
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			GetMonthlyResponseGetMonthlyResult rs = null;
			try {
				rs =hotelService.getMonthly(GlobalParams.hotelProvinceCode, new Audit(), hotelmanage.getUsername());
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				continue;
			}
			System.out.println(hotelmanage.getUsername());
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			} catch (DocumentException e) {
				e.printStackTrace();
				continue;
			}
			Element root = doc.getRootElement();
			// 没有查询到数据
			if(root.element("DataSet") == null || Integer.parseInt(root.element("DataStructure").element("ReturnCode").getTextTrim()) <= 0){
				continue;
			}
			
			List<Element> nodes = root.element("DataSet").elements();
			for (int i = 0; i < nodes.size(); i++) {
				Element element = nodes.get(i);
				HotelMonthly hotelMonthly = new HotelMonthly();
				//申报主表：
				hotelMonthly.setTotalIncome("0");
				hotelMonthly.setRoomIncome("0");
				hotelMonthly.setCateringIncome("0");
				hotelMonthly.setOtherIncome("0");
				// 审核状态
				hotelMonthly.setStatus("4");
				hotelMonthly.setProvinceState("4");
				hotelMonthly.setDistrictStatus(Status.noAudit);
				// 酒店ID
				hotelMonthly.setHotelId(hotelmanage.getId());
				hotelMonthly.setHotelmanage(hotelmanage);
				/// 年
				hotelMonthly.setYear(element.element("Year") == null ? "" : element.element("Year").getTextTrim());
				/// 月
				hotelMonthly.setMonth(element.element("Month") == null ? "" : element.element("Month").getTextTrim());
				/// 填表人
				hotelMonthly.setFiller(element.element("CreateUser") == null ? "" : element.element("CreateUser").getTextTrim());
				/// 联系电话
				hotelMonthly.setFillerTel(element.element("Phone") == null ? "" : element.element("Phone").getTextTrim());
				/// 实际出租间夜数
				hotelMonthly.setActualRentNum(element.element("RealityCount") == null ? "" : element.element("RealityCount").getTextTrim());
				/// 可供出租间夜数
				hotelMonthly.setRentNum(element.element("MaxCount") == null ? "" : element.element("MaxCount").getTextTrim());
				/// 出租率
				hotelMonthly.setRentPercent(element.element("HireRate") == null ? "" : element.element("HireRate").getTextTrim());
				/// 填报时间
				hotelMonthly.setReportDate(element.element("CreateTime") == null ? "" : element.element("CreateTime").getTextTrim().split(" ")[0].replaceAll("/", "-"));
				// 从表数据
				List<Element> indexList = element.element("IndexList").elements();
				for (int k = 0; k < indexList.size(); k++) {
					Element areaElement = indexList.get(k);
					String areaCode = areaElement.elementTextTrim("IndexCode");
					switch (areaCode) {
//					总人数	01
					case "01" :
						hotelMonthly.setTotalMonthTime(areaElement.element("PeopleCount") == null ? "0" : areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setTotalMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setTotalMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setTotalMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					一、国内过夜游客	02
					case "02" :
						hotelMonthly.setInlandMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setInlandMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setInlandMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setInlandMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					二、入境过夜游客	03
					case "03" :
						hotelMonthly.setEntryMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setEntryMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setEntryMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setEntryMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					(一)香港同胞	04
					case "04" :
						hotelMonthly.setHongkongMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setHongkongMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setHongkongMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setHongkongMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					(二)澳门同胞	05
					case "05" :
						hotelMonthly.setMacauMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setMacauMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setMacauMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setMacauMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					(三)台湾同胞	06
					case "06" :
						hotelMonthly.setTaiwanMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setTaiwanMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setTaiwanMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setTaiwanMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					(四)外国人	07
					case "07" :
						hotelMonthly.setForeignMonthTime(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setForeignMonthDay(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						hotelMonthly.setForeignMonthYearTime(areaElement.element("SumPeopleCount") == null ? "0" :  areaElement.element("SumPeopleCount").getTextTrim());
						hotelMonthly.setForeignMonthYearDay(areaElement.element("SumDayCount") == null ? "0" :  areaElement.element("SumDayCount").getTextTrim());
					break;
//					1.亚洲小计	08
					case "08" :
						hotelMonthly.setAsianMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAsianMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					日本	09
					case "09" :
						hotelMonthly.setJapanMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setJapanMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					韩国	10
					case "10" :
						hotelMonthly.setKoreaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setKoreaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					蒙古	11
					case "11" :
						hotelMonthly.setMongoliaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setMongoliaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					印度尼西亚	12
					case "12" :
						hotelMonthly.setIndonesiaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setIndonesiaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					马来西亚	13
					case "13" :
						hotelMonthly.setMalaysiaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setMalaysiaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
					break;
//					菲律宾	14
					case "14" :
						hotelMonthly.setPhilippinesMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setPhilippinesMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					新加坡	15
					case "15" :
						hotelMonthly.setSingaporeMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setSingaporeMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					泰国	16
					case "16" :
						hotelMonthly.setThailandMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setThailandMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					印度	17
					case "17" :
						hotelMonthly.setIndiaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setIndiaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					越南	18
					case "18" :
						hotelMonthly.setVietnamMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setVietnamMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					缅甸	19
					case "19" :
						hotelMonthly.setBurmaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setBurmaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					朝鲜	20
					case "20" :
						hotelMonthly.setNorthkoreaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setNorthkoreaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					巴基斯坦	21
					case "21" :
						hotelMonthly.setPakistanMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setPakistanMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					亚洲其它	22
					case "22" :
						hotelMonthly.setAisaOtherMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAisaOtherMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					2.欧洲小计	23
					case "23" :
						hotelMonthly.setEuropeMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setEuropeMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					英国	24
					case "24" :
						hotelMonthly.setEnglandMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setEnglandMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					法国	25
					case "25" :
						hotelMonthly.setFranceMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setFranceMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					德国	26
					case "26" :
						hotelMonthly.setGermanyMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setGermanyMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					意大利	27
					case "27" :
						hotelMonthly.setItalyMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setItalyMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					瑞士	28
					case "28" :
						hotelMonthly.setSwitzerlandMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setSwitzerlandMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					瑞典	29
					case "29" :
						hotelMonthly.setSwedenMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setSwedenMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					俄罗斯	30
					case "30" :
						hotelMonthly.setRussiaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setRussiaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					西班牙	31
					case "31" :
						hotelMonthly.setSpainMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setSpainMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					欧洲其它	32
					case "32" :
						hotelMonthly.setEuropeOtherMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setEuropeOtherMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					3.美洲小计	33
					case "33" :
						hotelMonthly.setAmericaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAmericaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					美国	34
					case "34" :
						hotelMonthly.setUsaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setUsaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					加拿大	35
					case "35" :
						hotelMonthly.setCanadaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setCanadaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					美洲其它	36
					case "36" :
						hotelMonthly.setAmericaOtherMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAmericaOtherMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					4.大洋洲小计	37
					case "37" :
						hotelMonthly.setOceaniaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setOceaniaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					澳大利亚	38
					case "38" :
						hotelMonthly.setAustraliaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAustraliaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					新西兰	39
					case "39" :
						hotelMonthly.setNzMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setNzMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					大洋洲其它	40
					case "40" :
						hotelMonthly.setOceaniaOtherMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setOceaniaOtherMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					5.非洲小计	41
					case "41" :
						hotelMonthly.setAfricaMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setAfricaMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
//					6.其它小计	42
					case "42" :
						hotelMonthly.setOtherMonth(areaElement.element("PeopleCount") == null ? "0" :  areaElement.element("PeopleCount").getTextTrim());
						hotelMonthly.setOtherMonthYear(areaElement.element("DayCount") == null ? "0" :  areaElement.element("DayCount").getTextTrim());
						break;
					}
				}
				list.add(hotelMonthly);
			}
		}
		systemService.batchSave(list);
		System.out.println("-----------------------------getProvinceMonthly结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 端午节数据同步
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getProvinceDragonBoatFestival")
	public void getProvinceDragonBoatFestival(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("-----------------------------getProvinceDragonBoatFestival开始同步-----------------------------");
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		List<Holiday> list = new ArrayList<>();
		GetDragonBoatFestivalResponseGetDragonBoatFestivalResult rs = null;
		try {
			rs = hotelProvinceService.getDragonBoatFestival(GlobalParams.hotelProvinceCode, new DragonBoatFestivalInfo());
			Document doc = null;
			try {
				System.out.println(rs.get_any()[0].toString());
				doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			} catch (DocumentException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			Element root = doc.getRootElement();
			// 没有查询到数据
			if (root.element("DataSet") == null || Integer.parseInt(root.element("DataStructure").element("ReturnCode").getTextTrim()) <= 0) {
				System.out.println("null");
			}
			List<Element> nodes = root.element("DataSet").elements();
			for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
				Element element = iterator.next();
				Holiday holiday = new Holiday();
				holiday.setType("3");
				/// 年
				holiday.setYear(element.element("Year").getTextTrim());
				/// 美兰
				holiday.setMeilanAir(Double.parseDouble(element.element("MeiLan").getTextTrim()));
				/// 凤凰
				holiday.setFenghuangAir(Double.parseDouble(element.element("FengHuang").getTextTrim()));
				/// 博鳌
				holiday.setBoaoAir(Double.parseDouble(element.element("BoAo").getTextTrim()));
				/// 海峡办
				holiday.setStraitOffice(Double.parseDouble(element.element("Strait").getTextTrim()));
				/// 景区人数
				holiday.setScenicNum(Double.parseDouble(element.element("PlaceCount").getTextTrim()));
				/// 景区收入
				holiday.setScenicIncome(Double.parseDouble(element.element("PlaceEarning").getTextTrim()));
				/// 酒店人数
				holiday.setHotelNum(Double.parseDouble(element.element("HotelCount").getTextTrim()));
				/// 酒店收入
				holiday.setHotelIncome(Double.parseDouble(element.element("HotelEarning").getTextTrim()));
				/// 乡村人数
				holiday.setTownTravelNum(Double.parseDouble(element.element("VillageCount").getTextTrim()));
				/// 乡村收入
				holiday.setTownTravelIncome(Double.parseDouble(element.element("VillageEarning").getTextTrim()));
				/// 免税店人数
				holiday.setTaxFreeNum(Double.parseDouble(element.element("ExemptionCount").getTextTrim()));
				/// 免税店收入
				holiday.setTaxFreeIncome(Double.parseDouble(element.element("ExemptionEarning").getTextTrim()));
				holiday.setSourceStatus("1");
				list.add(holiday);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		systemService.batchSave(list);
		System.out.println("-----------------------------getProvinceDragonBoatFestival结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 端午节数据同步
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getProvinceMoonFestival")
	public void getProvinceMoonFestival(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("-----------------------------getProvinceMoonFestival开始同步-----------------------------");
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		List<Holiday> list = new ArrayList<>();
		GetMoonFestivalResponseGetMoonFestivalResult rs = null;
		try {
			rs = hotelProvinceService.getMoonFestival(GlobalParams.hotelProvinceCode, new MoonFestivalInfo());
			Document doc = null;
			try {
				System.out.println(rs.get_any()[0].toString());
				doc = DocumentHelper.parseText(rs.get_any()[0].toString());
			} catch (DocumentException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			Element root = doc.getRootElement();
			// 没有查询到数据
			if (root.element("DataSet") == null || Integer.parseInt(root.element("DataStructure").element("ReturnCode").getTextTrim()) <= 0) {
				System.out.println("null");
			}
			List<Element> nodes = root.element("DataSet").elements();
			for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
				Element element = iterator.next();
				Holiday holiday = new Holiday();
				holiday.setType("4");
				/// 年
				holiday.setYear(element.element("Year").getTextTrim());
				/// 美兰
				holiday.setMeilanAir(Double.parseDouble(element.element("MeiLan").getTextTrim()));
				/// 凤凰
				holiday.setFenghuangAir(Double.parseDouble(element.element("FengHuang").getTextTrim()));
				/// 博鳌
				holiday.setBoaoAir(Double.parseDouble(element.element("BoAo").getTextTrim()));
				/// 海峡办
				holiday.setStraitOffice(Double.parseDouble(element.element("Strait").getTextTrim()));
				/// 景区人数
				holiday.setScenicNum(Double.parseDouble(element.element("PlaceCount").getTextTrim()));
				/// 景区收入
				holiday.setScenicIncome(Double.parseDouble(element.element("PlaceEarning").getTextTrim()));
				/// 酒店人数
				holiday.setHotelNum(Double.parseDouble(element.element("HotelCount").getTextTrim()));
				/// 酒店收入
				holiday.setHotelIncome(Double.parseDouble(element.element("HotelEarning").getTextTrim()));
				/// 乡村人数
				holiday.setTownTravelNum(Double.parseDouble(element.element("VillageCount").getTextTrim()));
				/// 乡村收入
				holiday.setTownTravelIncome(Double.parseDouble(element.element("VillageEarning").getTextTrim()));
				/// 免税店人数
				holiday.setTaxFreeNum(Double.parseDouble(element.element("ExemptionCount").getTextTrim()));
				/// 免税店收入
				holiday.setTaxFreeIncome(Double.parseDouble(element.element("ExemptionEarning").getTextTrim()));
				holiday.setSourceStatus("1");
				list.add(holiday);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		systemService.batchSave(list);
		System.out.println("-----------------------------getProvinceMoonFestival结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 通知同步
	 * @param request
	 */
	@RequestMapping(params = "getNotice")
	public void getNotice(HttpServletRequest request) {
		System.out.println("-----------------------------getNotice开始同步-----------------------------");
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		List<Notice> noticeList = new ArrayList<Notice>();
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/notice_info";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
		HttpURLConnection connection = this.getConnection(url);
		DataOutputStream out = null;
		out = new DataOutputStream(connection.getOutputStream());
		out.flush();
		out.close();
		BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
		reader.readLine();
		String line = reader
				.readLine()
				.replace("<string xmlns=\"http://tempuri.org/\">",
						"").replace("</string>", "");

		System.out.println(line);

		// 数据转为json对象
		JSONObject jsonObject = JSON.parseObject(line);
		String data = jsonObject.get("data").toString();
		JSONArray list = JSON.parseArray(data);

		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = JSON.parseObject(list.get(i)
					.toString());
			Notice notice = new Notice();
			notice.setTitle(obj.getString("Title"));
			notice.setContent(obj.getString("Content"));
			notice.setPublishDate(obj.getString("InputDate") == null ? "" : obj.getString("InputDate").substring(0,10));
			notice.setRange("3");
			notice.setStatus("1");
			notice.setSource("3");
			noticeList.add(notice);
		}
		// 关闭连接
		connection.disconnect();
		reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		systemService.batchSave(noticeList);
		System.out.println("-----------------------------getNotice结束同步-----------------------------");
	}
	
	/**
	 * 
	 * 旅游局数据同步
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "getTourismInfo")
	public void getTourismInfo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("-----------------------------getTourismInfo开始同步-----------------------------");
		String url = "http://fdcs.100chengxin.com/hotel_interface.asmx/get_tourismInfo";
		for (int page = 1; page <= 20; page++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			HttpURLConnection connection = this.getConnection(url);
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(connection.getOutputStream());
				// The URL-encoded contend
				// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
				String content = null;
				content = "start_page=" + URLEncoder.encode(String.valueOf(page), "UTF-8");
				content += "&page_nums=" + URLEncoder.encode("100", "UTF-8");
				// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
				out.writeBytes(content);
				out.flush();
				out.close();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
				reader.readLine();
				String line = reader.readLine()
						.replace("<string xmlns=\"http://tempuri.org/\">", "")
						.replace("</string>", "");

				System.out.println(line);
				// 数据转为json对象
				JSONObject jsonObject = JSON.parseObject(line);
				String data = jsonObject.get("data").toString();
				JSONArray list = JSON.parseArray(data);

				for (int i = 0; i < list.size(); i++) {
					JSONObject obj = JSON.parseObject(list.get(i)
							.toString());
					TouristOffices touristOffice = new TouristOffices();
					touristOffice.setOriginID(obj.getString("ID"));
					touristOffice.setAccount(obj.getString("UserName"));
					touristOffice.setPassword("syly1111");
					touristOffice.setCity("三亚市");
					touristOffice.setArea(obj.getString("InArea"));
					touristOffice.setName(obj.getString("CNCoName"));
					touristOffice.setHead(obj.getString("Manager"));
					touristOffice.setTel(obj.getString("ManagerTel"));
					touristOffice.setQq(obj.getString("QQ"));
					touristOffice.setPostcode(obj.getString("PostCode"));
					touristOffice.setFax(obj.getString("Fax"));
					touristOffice.setAdd(obj.getString("Address"));
					touristOffice.setPhone(obj.getString("Tel"));
					touristOffice.setHttp(obj.getString("Url"));
					touristOffice.setEmail(obj.getString("Email"));
					touristOffice.setCreateTime(new Date());
					systemService.save(touristOffice);
					AutoAddUser.add(obj.getString("UserName"), GlobalParams.XITONGGUANLIYUAN, obj.getString("Manager"));
				}
				// 关闭连接
				connection.disconnect();
				reader.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		System.out.println("-----------------------------getTourismInfo结束同步-----------------------------");
	}
	
	
	
}
