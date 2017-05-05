package com.zzc.web.travel.service.impl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.system.controller.core.UserController;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.TravelAnnualFinance;
import com.zzc.web.travel.entity.TravelQuarterIn;
import com.zzc.web.travel.entity.TravelQuarterInRecord;
import com.zzc.web.travel.entity.TravelQuarterInland;
import com.zzc.web.travel.entity.TravelQuarterInlandRecord;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.TravelQuarterOutRecord;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.web.travel.entity.TraveldataAnnual;
import com.zzc.web.travel.service.TravelService;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.travelagency.GetBasicStateResponseGetBasicStateResult;
import com.zzc.webservice.travelagency.GetFinanceStateResponseGetFinanceStateResult;
import com.zzc.webservice.travelagency.GetQuarterlyStateResponseGetQuarterlyStateResult;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

@Service("travelservice")
@Transactional
public class TravelServiceImpl implements TravelService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TravelServiceImpl.class);
	
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 旅行社添加或编辑信息
	 * @author Fp
	 */
	public String addOrUpdateTravelInfo(TraveldataAnnual traveldataAnnual){
		
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = ServiceInstance.getTravelAgencyService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tip = "200";
		try {
			Thread.sleep(1000);
		String code = traveldataAnnual.getOrganizationnum();
		String tableDate = sdf.format(new Date());
		String unitName = traveldataAnnual.getName();
		String delegate = traveldataAnnual.getCorporate();
		String userName = traveldataAnnual.getLicensenum();
		String unitMaster = traveldataAnnual.getPrincipal();
		String filler = traveldataAnnual.getFiller();
		String fillerTel = traveldataAnnual.getFillerTel();
		String adessr = traveldataAnnual.getAddress();
		String province = traveldataAnnual.getProvince();
		String city = GlobalParams.areaID;
		String postcode = traveldataAnnual.getZipcode();
		String regionalismCode = traveldataAnnual.getAdminnum();
		String mobile = traveldataAnnual.getMobile();
		String tel = traveldataAnnual.getPhone();
		String telNo = traveldataAnnual.getPhoneextension();
		String fax = traveldataAnnual.getFax();
		String faxNo = traveldataAnnual.getFaxextension();
		String webSite = traveldataAnnual.getWeburl();
		String email = traveldataAnnual.getEmail();
		String registrationType = traveldataAnnual.getRegisterstyle();
		String unitType = traveldataAnnual.getIsmember();
		String accommodationStar = traveldataAnnual.getAccommodationStar();
		String travelAgencyType = traveldataAnnual.getTravelAgencyType();
		String sceneSpotLevel = traveldataAnnual.getSceneSpotlevel();
		String outBoundTourism = traveldataAnnual.getBusinessexit();
		String borderTour = traveldataAnnual.getBusinessexit();
		String englishName = traveldataAnnual.getEnglishname();
		String QQ = traveldataAnnual.getQq();
		String areaID = GlobalParams.areaID;
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
		logger.info("调用旅行社添加或编辑信息接口："+traveldataAnnual.getId());
		tip =  travelAgencyService.insertIntoBasic(areaID, code, tableDate, unitName, delegate, userName, unitMaster, filler, fillerTel, adessr, province, city, postcode, regionalismCode, mobile, tel, telNo, fax, faxNo, webSite, email, registrationType, unitType, accommodationStar, travelAgencyType, sceneSpotLevel, outBoundTourism, borderTour, englishName, QQ, verificationCode);
		if("添加成功".equals(tip)) tip = "200";
		logger.info("调用旅行社添加或编辑信息接口结果："+tip);
		
		} catch (Exception e) {
			logger.info("旅行社添加或编辑信息异常："+e.toString()+" id:"+traveldataAnnual.getId());
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}
	
	/**
	 * 
	 * 同步旅行社审核状态
	 * @author Fp
	 * @return 2退回，1通过，0未审核
	 */
	public String[] syncTavelInfo(TraveldataAnnual traveldataAnnual){
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = ServiceInstance.getTravelAgencyService();
		GetBasicStateResponseGetBasicStateResult rs;
		try {
			Thread.sleep(1000);
			logger.info("同步旅行社审核状态接口："+traveldataAnnual.getId());
			rs = travelAgencyService.getBasicState(GlobalParams.areaID, traveldataAnnual.getLicensenum(), GlobalParams.travelAgencyVerificationCode);
			logger.info("同步旅行社审核状态接口结果："+rs.get_any()[1].toString());
			Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
			Element root = doc.getRootElement();
			List<Element> nodes = root.element("DocumentElement").elements();
			String provinceState = nodes.get(0).elementText("provinceState") == null ? "" : nodes.get(0).elementText("provinceState");
			String provinceContent = nodes.get(0).elementText("provinceContent") == null ? "" : nodes.get(0).elementText("provinceContent");
			return new String[]{provinceState, provinceContent};
		} catch (Exception e) {
			logger.info("同步旅行社审核状态接口异常："+e.toString()+" id:"+traveldataAnnual.getId());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 年报添加或编辑信息
	 */
	public String addOrUpdateTravelFinance(TravelAnnualFinance travelannualfinance){
		String tip = "200";
		try {
			Thread.sleep(1000);
		String travelid = travelannualfinance.getTid();
		
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();
		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);
		
//		AreID	地区编码
		String areaID = GlobalParams.areaID;
//		Code	许可证号
		String code = traveldata.getLicensenum();
//		TableDate	填报时间
		String tableDate = travelannualfinance.getReportTime();
//		UnitName	单位名称
		String unitName = traveldata.getName();
//		UnitMaster	单位负责人
		String unitMaster = traveldata.getPrincipal();
//		Filler	填表人
		String filler = travelannualfinance.getReportPerson();
//		FillerTel	填表人电话
		String fillerTel = travelannualfinance.getTelephone();
//		CurrentAssetsTotal	流动资产小计
		String currentAssetsTotal = String.valueOf(travelannualfinance.getStreamTotal());
//		LongTermInvest	长期投资
		String longTermInvest = String.valueOf(travelannualfinance.getLongInvest());
//		FixAssetsTotal	固定资产小计
		String fixAssetsTotal = String.valueOf(travelannualfinance.getFixedAssets());
//		FixAssetsInitialPrice	固定资产原价
		String fixAssetsInitialPrice = String.valueOf(travelannualfinance.getFixedPrice());
//		CapitalTotal	资产合计
		String capitalTotal = String.valueOf(travelannualfinance.getAssetTotal());
//		TotalLiabilities  	负债合计  
		String totalLiabilities = String.valueOf(travelannualfinance.getLiabilitiesTotal());
//		OwnerBenefitTotal	所有者权益合计
		String ownerBenefitTotal = String.valueOf(travelannualfinance.getPossessor());
//		RealCapital	实收资本
		String realCapital = String.valueOf(travelannualfinance.getRealIncome());
//		OperationIncome	营业收入
		String operationIncome = String.valueOf(travelannualfinance.getBusinessIncome());
//		InboundRevenue	入境旅游营业收入
		String inboundRevenue = String.valueOf(travelannualfinance.getIntoIncome());
//		OutboundRevenues	出境旅游营业收入
		String outboundRevenues = String.valueOf(travelannualfinance.getOutIncome());
//		DomesticRevenue	国内旅游营业收入
		String domesticRevenue = String.valueOf(travelannualfinance.getInTravelIncome());
//		OperationCost	营业成本
		String operationCost = String.valueOf(travelannualfinance.getCost());
//		OperationFee	营业费用  
		String operationFee = String.valueOf(travelannualfinance.getBusinessExpenses());
//		OperationTaxAddition	营业税金及附加
		String operationTaxAddition = String.valueOf(travelannualfinance.getBusinessTexAdd());
//		BusinessProfit	主营业务利润
		String businessProfit = String.valueOf(travelannualfinance.getMainOperation());
//		InbundProfit	入境旅游业务利润
		String inbundProfit = String.valueOf(travelannualfinance.getProfitInTravel());
//		OutboundProfit	出境旅游业务利润
		String outboundProfit = String.valueOf(travelannualfinance.getOutTravelBusiness());
//		DomesticProfit	国内旅游业务利润
		String domesticProfit = String.valueOf(travelannualfinance.getInTravelReturn());
//		ManagementFee	管理费用
		String managementFee = String.valueOf(travelannualfinance.getManageCost());
//		Tax	税金
		String tax = String.valueOf(travelannualfinance.getTaxes());
//		FinanceFee	财务费用
		String financeFee = String.valueOf(travelannualfinance.getFinancialCost());
//		InterestPay	利息支出
		String interestPay = String.valueOf(travelannualfinance.getInterestCost());
//		OperationProfit	营业利润
		String operationProfit = String.valueOf(travelannualfinance.getOperatingIncome());
//		ProfitTotal	利润总额
		String profitTotal = String.valueOf(travelannualfinance.getTotalReturn());
//		IncomeTax 	所得税
		String incomeTax = String.valueOf(travelannualfinance.getIncomeTax());
//		AnnualPayTotal	应付职工酬薪
		String annualPayTotal = String.valueOf(travelannualfinance.getEmployeeSalary());
//		YearendEmployment	全部从业人员年平均人数
		String yearendEmployment = String.valueOf(travelannualfinance.getNumAverage());
//		JuniorEmployment	其中大专以上学历人数
		String juniorEmployment = String.valueOf(travelannualfinance.getCollege());
//		AccumulatedDepreciation	累计折旧
		String accumulatedDepreciation = String.valueOf(travelannualfinance.getDepreciation());
//		YearDepreciation	其中：本年折旧
		String yearDepreciation = String.valueOf(travelannualfinance.getYearDepreciation());
//		TravelExpenses	差旅费
		String travelExpenses = String.valueOf(travelannualfinance.getTravelExpense());
//		Gains	公允价值变动收益
		String gains = String.valueOf(travelannualfinance.getValueVariation());
//		ReturnInvestment	投资收益
		String returnInvestment = String.valueOf(travelannualfinance.getInvest());
//		WhetherPerform	是否执行2006年《企业会计准则》
		String whetherPerform = travelannualfinance.getWhetherPerform();
//		NonIncome	营业外收入
		String nonIncome = String.valueOf(travelannualfinance.getExtraGains());
//		Subsidies	其中：政府补贴
		String subsidies = String.valueOf(travelannualfinance.getGovernmentSubsidies());
//		CostSales	销售费用
		String costSales = String.valueOf(travelannualfinance.getSellingExpense());
//		GrossProfit	毛利润
		String grossProfit = String.valueOf(travelannualfinance.getGross());
//		TheVAT	本年应交增值税
		String theVAT = String.valueOf(travelannualfinance.getAddTex());
//		LaborContract	签订劳动合同的导游人数
		String laborContract = String.valueOf(travelannualfinance.getTourNum());
//		LeaderNumber	领队人数
		String leaderNumber = String.valueOf(travelannualfinance.getLeadGroup());
//		StoresNumber	门店数量
		String storesNumber = String.valueOf(travelannualfinance.getStoreNum());
//		BranchNumber	分社数量
		String branchNumber = String.valueOf(travelannualfinance.getBranchOffice());
//		SubsidiariesNumber	子公司数量
		String subsidiariesNumber = String.valueOf(travelannualfinance.getSubsidiary());
//		Loss	由执行2006年《企业会计准则》企业填报：资产减值损失
		String loss = String.valueOf(travelannualfinance.getLoss());
//		City	旅行社所属地区编码
		String city = GlobalParams.areaID;
//		VerificationCode	接口密码
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
		logger.info("调用添加旅行社财务年报接口："+travelannualfinance.getId());
			String rs = travelAgencyService.insertIntoFinance(areaID, code, tableDate, unitName, unitMaster, filler, fillerTel, currentAssetsTotal, longTermInvest, fixAssetsTotal, fixAssetsInitialPrice, capitalTotal, totalLiabilities, ownerBenefitTotal, realCapital, operationIncome, inboundRevenue, outboundRevenues, domesticRevenue, operationCost, operationFee, operationTaxAddition, businessProfit, inbundProfit, outboundProfit, domesticProfit, managementFee, tax, financeFee, interestPay, operationProfit, profitTotal, incomeTax, annualPayTotal, yearendEmployment, juniorEmployment, accumulatedDepreciation, yearDepreciation, travelExpenses, gains, returnInvestment, whetherPerform, nonIncome, subsidies, costSales, grossProfit, theVAT, laborContract, leaderNumber, storesNumber, branchNumber, subsidiariesNumber, loss, city, verificationCode);
			if(!"添加成功".equals(rs)) tip = "添加失败";
		logger.info("调用添加旅行社财务年报接口："+rs);	
		} catch (Exception e) {
			e.printStackTrace();
			tip = "通信异常";
			logger.info("调用添加旅行社财务年报接口异常："+e.toString()+" id:"+travelannualfinance.getId());	
		}
		return tip;
	}
	
	/**
	 * 获取年报审核状态
	 */
	public String[] getFinance(TravelAnnualFinance travelannualfinance){
		String status = "";
		try {
			Thread.sleep(1000);
			String year = travelannualfinance.getYear();
			String areaID = GlobalParams.areaID;
			Traveldata traveldata = systemService.get(Traveldata.class, travelannualfinance.getTid());
			String license = traveldata.getLicensenum();
			String verificationCode = GlobalParams.travelAgencyVerificationCode;
			GetFinanceStateResponseGetFinanceStateResult rs = null;
			try {
				logger.info("调用获取年报审核状态审核状态接口："+travelannualfinance.getId());
				rs = new PublicInterfaceSoapProxy().getFinanceState(areaID,
						license, year, verificationCode);
			} catch (RemoteException e) {
				e.printStackTrace();
				status = "-1";
			}
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(rs.get_any()[1].toString());
				logger.info("调用获取年报审核状态审核状态结果："+rs.get_any()[1].toString());
			} catch (DocumentException e) {
				e.printStackTrace();
				status = "-1";
				logger.info("调用获取年报审核状态审核状态异常："+e.toString()+" id:"+travelannualfinance.getId());
			}
			Element root = doc.getRootElement();
			// 非空判断
			if (root.element("DocumentElement") == null)
				status = "-1";
			
			String provinceState = root.element("DocumentElement").element("旅行社年报财务数据审核状态").element("provinceState").getText();
			String provinceContent = root.element("DocumentElement").element("旅行社年报财务数据审核状态").element("provinceContent").getText();
			return new String[]{provinceState, provinceContent};
		} catch (Exception e) {
			e.printStackTrace();
			status = "-1";
			logger.info("调用获取年报审核状态审核状态异常："+e.toString()+" id:"+travelannualfinance.getId());
			return null;
		}
	}
	
	/**
	 * 新增或编辑月报
	 */
	public String addOrUpdateEntryTravelQuarter(TravelQuarterIn travelQuarterIn){
		String tip = "200";
		// 旅行社ID
		String travelid = travelQuarterIn.getTid();
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();

		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterIn.getReportPerson();
		String fillerTel = travelQuarterIn.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
//		香港（人天，人次）	001001001001
		map = new HashMap<>();
		map.put("sortID", "001001001001");
		map.put("Season", String.valueOf(travelQuarterIn.getHkComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getHkComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getHkComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getHkComFour()));
		list.add(map);
//		澳门（人天，人次）	001001001002
		map = new HashMap<>();
		map.put("sortID", "001001001002");
		map.put("Season", String.valueOf(travelQuarterIn.getMcComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMcComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMcComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMcComFour()));
		list.add(map);
//		台湾（人天，人次）	001001001003
		map = new HashMap<>();
		map.put("sortID", "001001001003");
		map.put("Season", String.valueOf(travelQuarterIn.getTwComOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getTwComTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getTwComThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getTwComFour()));
		list.add(map);
//		外国人（人天总数）	001001007
		map = new HashMap<>();
		map.put("sortID", "001001007");
		map.put("Season", String.valueOf(travelQuarterIn.getForeignersOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getForeignersTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getForeignersThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getForeignersFour()));
		list.add(map);
//		日本（人次）	001001001004
		map = new HashMap<>();
		map.put("sortID", "001001001004");
		map.put("Season", String.valueOf(travelQuarterIn.getJapanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getJapanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getJapanThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getJapanFour()));
		list.add(map);
//		韩国（人次）	001001001005
		map = new HashMap<>();
		map.put("sortID", "001001001005");
		map.put("Season", String.valueOf(travelQuarterIn.getKoreaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getKoreaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getKoreaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getKoreaFour()));
		list.add(map);
//		蒙古（人次）	001001001006
		map = new HashMap<>();
		map.put("sortID", "001001001006");
		map.put("Season", String.valueOf(travelQuarterIn.getMongoOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMongoTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMongoThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMongoFour()));
		list.add(map);
//		印度尼西亚（人次）	001001001007
		map = new HashMap<>();
		map.put("sortID", "001001001007");
		map.put("Season", String.valueOf(travelQuarterIn.getIndonxyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getIndonxyTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getIndonxyThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getIndonxyFour()));
		list.add(map);
//		马来西亚（人次）	001001001008
		map = new HashMap<>();
		map.put("sortID", "001001001008");
		map.put("Season", String.valueOf(travelQuarterIn.getMalaxyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMalaxyTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMalaxyThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMalaxyFour()));
		list.add(map);
//		菲律宾（人次）	001001001009
		map = new HashMap<>();
		map.put("sortID", "001001001009");
		map.put("Season", String.valueOf(travelQuarterIn.getPhilipnOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getPhilipnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getPhilipnThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getPhilipnFour()));
		list.add(map);
//		新加坡（人次）	001001001010
		map = new HashMap<>();
		map.put("sortID", "001001001010");
		map.put("Season", String.valueOf(travelQuarterIn.getSingaporeOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSingaporeTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getSingaporeThree()));
		map.put("SeasonAdmitDay", String.valueOf(+travelQuarterIn.getSingaporeFour()));
		list.add(map);
//		泰国（人次）	001001001011
		map = new HashMap<>();
		map.put("sortID", "001001001011");
		map.put("Season", String.valueOf(travelQuarterIn.getTailandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getTailandTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getTailandThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getTailandFour()));
		list.add(map);
//		印度（人次）	001001001012
		map = new HashMap<>();
		map.put("sortID", "001001001012");
		map.put("Season", String.valueOf(travelQuarterIn.getIndiaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getIndiaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getIndiaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getIndiaFour()));
		list.add(map);
//		越南（人次）	001001001013
		map = new HashMap<>();
		map.put("sortID", "001001001013");
		map.put("Season", String.valueOf(travelQuarterIn.getVietnamOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getVietnamTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getVietnamThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getVietnamFour()));
		list.add(map);
//		缅甸（人次）	001001001014
		map = new HashMap<>();
		map.put("sortID", "001001001014");
		map.put("Season", String.valueOf(travelQuarterIn.getMyanamarOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getMyanamarTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getMyanamarThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getMyanamarFour()));
		list.add(map);
//		亚洲其他（人次）	001001001015
		map = new HashMap<>();
		map.put("sortID", "001001001015");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherAsianOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherAsianTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getOtherAsianThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getOtherAsianFour()));
		list.add(map);
//		英国（人次）	001001002001
		map = new HashMap<>();
		map.put("sortID", "001001002001");
		map.put("Season", String.valueOf(travelQuarterIn.getEnglandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getEnglandTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		法国（人次）	001001002002
		map = new HashMap<>();
		map.put("sortID", "001001002002");
		map.put("Season", String.valueOf(travelQuarterIn.getFrenchOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getFrenchTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		德国（人次）	001001002003
		map = new HashMap<>();
		map.put("sortID", "001001002003");
		map.put("Season", String.valueOf(travelQuarterIn.getGermanyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getGermanyTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		意大利（人次）	001001002004
		map = new HashMap<>();
		map.put("sortID", "001001002004");
		map.put("Season", String.valueOf(travelQuarterIn.getItalyOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getItalyTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		瑞士（人次）	001001002005
		map = new HashMap<>();
		map.put("sortID", "001001002005");
		map.put("Season", String.valueOf(travelQuarterIn.getSwissOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSwissTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		瑞典（人次）	001001002006
		map = new HashMap<>();
		map.put("sortID", "001001002006");
		map.put("Season", String.valueOf(travelQuarterIn.getSwedishOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSwedishTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		俄罗斯（人次）	001001002007
		map = new HashMap<>();
		map.put("sortID", "001001002007");
		map.put("Season", String.valueOf(travelQuarterIn.getRussiaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getRussiaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		西班牙（人次）	001001002008
		map = new HashMap<>();
		map.put("sortID", "001001002008");
		map.put("Season", String.valueOf(travelQuarterIn.getSpainOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSpainTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		荷兰（人次）	001001002009
		map = new HashMap<>();
		map.put("sortID", "001001002009");
		map.put("Season", String.valueOf(travelQuarterIn.getHollandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getHollandTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getHollandThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getHollandFour()));
		list.add(map);
////		丹麦（人次）	001001002010
		map = new HashMap<>();
		map.put("sortID", "001001002010");
		map.put("Season", String.valueOf(travelQuarterIn.getDenmarkOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getDenmarkTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		欧洲其他（人次）	001001002011
		map = new HashMap<>();
		map.put("sortID", "001001002011");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherEuropeanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherEuropeanTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		美国（人次）	001001003001
		map = new HashMap<>();
		map.put("sortID", "001001003001");
		map.put("Season", String.valueOf(travelQuarterIn.getUsOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getUsTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		加拿大（人次）	001001003002
		map = new HashMap<>();
		map.put("sortID", "001001003002");
		map.put("Season", String.valueOf(travelQuarterIn.getCanadaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getCanadaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		美洲其他（人次）	001001003003
		map = new HashMap<>();
		map.put("sortID", "001001003003");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherAmericanOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherAmericanTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		澳大利亚（人次）	001001004001
		map = new HashMap<>();
		map.put("sortID", "001001004001");
		map.put("Season", String.valueOf(travelQuarterIn.getAustraliaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getAustraliaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		新西兰（人次）	001001004002
		map = new HashMap<>();
		map.put("sortID", "001001004002");
		map.put("Season", String.valueOf(travelQuarterIn.getNewlandOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getNewlandOne()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		大洋洲其他（人次）	001001004003
		map = new HashMap<>();
		map.put("sortID", "001001004003");
		map.put("Season", String.valueOf(travelQuarterIn.getOtherOceaniaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getOtherOceaniaTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		南非（人次）	001001005001
		map = new HashMap<>();
		map.put("sortID", "001001005001");
		map.put("Season", String.valueOf(travelQuarterIn.getSouthafricaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getSouthafricaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getSouthafricaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getSouthafricaFour()));
		list.add(map);
//		埃及（人次）	001001005002
		map = new HashMap<>();
		map.put("sortID", "001001005002");
		map.put("Season", String.valueOf(travelQuarterIn.getEgyptOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getEgyptTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		肯尼亚（人次）	001001005003
		map = new HashMap<>();
		map.put("sortID", "001001005003");
		map.put("Season", String.valueOf(travelQuarterIn.getKenyaOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getKenyaTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterIn.getKenyaThree()));
		map.put("SeasonAdmitDay", String.valueOf(travelQuarterIn.getKenyaFour()));
		list.add(map);
//		非洲其他（人次）	001001005004
		map = new HashMap<>();
		map.put("sortID", "001001005004");
		map.put("Season", String.valueOf(travelQuarterIn.getAfricaotherOne()));
		map.put("SeasonAdmit", String.valueOf(travelQuarterIn.getAfricaotherTwo()));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
//		其他小计（人次）	001001006
		map = new HashMap<>();
		map.put("sortID", "001001006");
		Integer countSeason = travelQuarterIn.getTotalOtherOne()+travelQuarterIn.getKenyaOne()+travelQuarterIn.getSouthafricaOne()+travelQuarterIn.getEgyptOne()+travelQuarterIn.getAfricaotherOne();
		Integer countSeasonAdmit = travelQuarterIn.getTotalOtherTwo()+travelQuarterIn.getKenyaTwo()+travelQuarterIn.getSouthafricaTwo()+travelQuarterIn.getEgyptTwo()+travelQuarterIn.getAfricaotherTwo();
		map.put("Season", String.valueOf(countSeason));
		map.put("SeasonAdmit", String.valueOf(countSeasonAdmit));
		map.put("SeasonDay", "0");
		map.put("SeasonAdmitDay", "0");
		list.add(map);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map2 = (Map<String, String>) iterator.next();
			String flag = "200";
			
			String sortID = map2.get("sortID");
			String season = map2.get("Season");
			String seasonAdmit = map2.get("SeasonAdmit");
			String seasonDay = map2.get("SeasonDay");
			String seasonAdmitDay = map2.get("SeasonAdmitDay");
			try {
				Thread.sleep(1000);
				logger.info("调用旅行社入境季报添加或编辑信息接口："+" 季报id:"+travelQuarterIn.getId()+" 地区编码:"+sortID);
				String rs = travelAgencyService.insertIntoInboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, seasonAdmitDay, season, seasonAdmit, verificationCode);
				System.out.println(sortID+" : "+rs);
				if(!"添加成功".equals(flag)){
					tip = "接口返回失败";
				}
				logger.info("调用旅行社入境季报添加或编辑信息接口结果："+rs);
			} catch (Exception e) {
				e.printStackTrace();
				flag = "通信异常";
				logger.info("调用旅行社入境季报添加或编辑信息接口异常："+e.toString()+" 季报id:"+travelQuarterIn.getId()+" 地区编码:"+sortID);
				System.out.println("通信异常:"+sortID);
			}
			// 添加失败，进行记录
			if("200".equals(flag)){
				tip = "通信异常";
				TravelQuarterInRecord record = new TravelQuarterInRecord();
				record.setCode(code);
				record.setYear(travelQuarterIn.getYear());
				record.setQuarter(travelQuarterIn.getQuarter());
				record.setUnitMaster(unitMaster);
				record.setFiller(filler);
				record.setFillerTel(fillerTel);
				record.setSortID(sortID);
				record.setSeasonDay(seasonDay);
				record.setSeasonAdmitDay(seasonAdmitDay);
				record.setSeason(season);
				record.setSeasonAdmit(seasonAdmitDay);
				record.setRemark("insertIntoInboundTravel");
				systemService.saveOrUpdate(record);
			}
		}
		return tip;
	}
	
	/**
	 * 新增或编辑出境季报
	 */
	public String addOrUpdateLeaveTravelQuarter(TravelQuarterOut travelQuarterOut){
		String tip = "200";
		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();

		String travelid = travelQuarterOut.getTid();
		
		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterOut.getReportPerson();
		String fillerTel = travelQuarterOut.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
//		出境游客（人天总数）	001001
		map = new HashMap<>();
		map.put("sortID", "001001");
		map.put("Season", String.valueOf(travelQuarterOut.getOutOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getOutTwo()));
		list.add(map);
//		香港地区（人次）	001001001001
		map = new HashMap<>();
		map.put("sortID", "001001001001");
		map.put("Season", String.valueOf(travelQuarterOut.getHongKongOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getHongKongTwo()));
		list.add(map);
//		澳门地区（人次）	001001001002
		map = new HashMap<>();
		map.put("sortID", "001001001002");
		map.put("Season", String.valueOf(travelQuarterOut.getMacauOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMacauTwo()));
		list.add(map);
//		台湾地区（人次）	001001001003
		map = new HashMap<>();
		map.put("sortID", "001001001003");
		map.put("Season", String.valueOf(travelQuarterOut.getTaiWanOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getTaiWanTwo()));
		list.add(map);
//		日本（人次）	001001001004
		map = new HashMap<>();
		map.put("sortID", "001001001004");
		map.put("Season", String.valueOf(travelQuarterOut.getJapanOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getJapanTwo()));
		list.add(map);
//		韩国（人次）	001001001005
		map = new HashMap<>();
		map.put("sortID", "001001001005");
		map.put("Season", String.valueOf(travelQuarterOut.getKoreaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getKoreaTwo()));
		list.add(map);
//		蒙古（人次）	001001001006
		map = new HashMap<>();
		map.put("sortID", "001001001006");
		map.put("Season", String.valueOf(travelQuarterOut.getMongoliaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMongoliaTwo()));
		list.add(map);
//		印度尼西亚（人次）	001001001007
		map = new HashMap<>();
		map.put("sortID", "001001001007");
		map.put("Season", String.valueOf(travelQuarterOut.getIndonesiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getIndonesiaTwo()));
		list.add(map);
//		马来西亚（人次）	001001001008
		map = new HashMap<>();
		map.put("sortID", "001001001008");
		map.put("Season", String.valueOf(travelQuarterOut.getMalaysiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getMalaysiaTwo()));
		list.add(map);
//		菲律宾（人次）	001001001009
		map = new HashMap<>();
		map.put("sortID", "001001001009");
		map.put("Season", String.valueOf(travelQuarterOut.getPhilippinesOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getPhilippinesTwo()));
		list.add(map);
//		新加坡（人次）	001001001010
		map = new HashMap<>();
		map.put("sortID", "001001001010");
		map.put("Season", String.valueOf(travelQuarterOut.getSingaporeOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getSingaporeTwo()));
		list.add(map);
//		泰国（人次）	001001001011
		map = new HashMap<>();
		map.put("sortID", "001001001011");
		map.put("Season", String.valueOf(travelQuarterOut.getThailandOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getThailandTwo()));
		list.add(map);
//		印度（人次）	001001001012
		map = new HashMap<>();
		map.put("sortID", "001001001012");
		map.put("Season", String.valueOf(travelQuarterOut.getIndiaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getIndiaTwo()));
		list.add(map);
//		越南（人次）	001001001013
		map = new HashMap<>();
		map.put("sortID", "001001001013");
		map.put("Season", String.valueOf(travelQuarterOut.getVietnamOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getVietnamTwo()));
		list.add(map);
//		缅甸（人次）	001001001014
		map = new HashMap<>();
		map.put("sortID", "001001001014");
		map.put("Season", String.valueOf(travelQuarterOut.getBurmaOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getBurmaTwo()));
		list.add(map);
//		亚洲其他（人次）	001001001015
		map = new HashMap<>();
		map.put("sortID", "001001001015");
		map.put("Season", String.valueOf(travelQuarterOut.getAsianOtherOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getAsianOtherTwo()));
		list.add(map);
//		英国（人次）	001001002001
		map = new HashMap<>();
		map.put("sortID", "001001002001");
		map.put("Season", String.valueOf(travelQuarterOut.getEnglishOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getEnglishTwo()));
		list.add(map);
//		法国（人次）	001001002002
		map = new HashMap<>();
		map.put("sortID", "001001002002");
		map.put("Season", String.valueOf(travelQuarterOut.getFranchOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getFranchTwo()));
		list.add(map);
//		德国（人次）	001001002003
		map = new HashMap<>();
		map.put("sortID", "001001002003");
		map.put("Season", String.valueOf(travelQuarterOut.getGermanyOne()));
		map.put("SeasonDay", String.valueOf(travelQuarterOut.getGermanyTwo()));
		list.add(map);
//		意大利（人次）	001001002004
		map = new HashMap<>();
		map.put("sortID", "001001002004");
		map.put("Season", String.valueOf(travelQuarterOut.getItalyOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		瑞士（人次）	001001002005
		map = new HashMap<>();
		map.put("sortID", "001001002005");
		map.put("Season", String.valueOf(travelQuarterOut.getSwitzerLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		瑞典（人次）	001001002006
		map = new HashMap<>();
		map.put("sortID", "001001002006");
		map.put("Season", String.valueOf(travelQuarterOut.getSwedenOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		俄罗斯（人次）	001001002007
		map = new HashMap<>();
		map.put("sortID", "001001002007");
		map.put("Season", String.valueOf(travelQuarterOut.getRussiaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		西班牙（人次）	001001002008
		map = new HashMap<>();
		map.put("sortID", "001001002008");
		map.put("Season", String.valueOf(travelQuarterOut.getSpainOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		荷兰（人次）	001001002009
		map = new HashMap<>();
		map.put("sortID", "001001002009");
		map.put("Season", String.valueOf(travelQuarterOut.getHolLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		丹麦（人次）	001001002010
		map = new HashMap<>();
		map.put("sortID", "001001002010");
		map.put("Season", String.valueOf(travelQuarterOut.getDenMarkOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		欧洲其他（人次）	001001002011
		map = new HashMap<>();
		map.put("sortID", "001001002011");
		map.put("Season", String.valueOf(travelQuarterOut.getEuropeOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		美国（人次）	001001003001
		map = new HashMap<>();
		map.put("sortID", "001001003001");
		map.put("Season", String.valueOf(travelQuarterOut.getUsOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		加拿大（人次）	001001003002
		map = new HashMap<>();
		map.put("sortID", "001001003002");
		map.put("Season", String.valueOf(travelQuarterOut.getCanadaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		美洲其他（人次）	001001003003
		map = new HashMap<>();
		map.put("sortID", "001001003003");
		map.put("Season", String.valueOf(travelQuarterOut.getUsOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		澳大利亚（人次）	001001004001
		map = new HashMap<>();
		map.put("sortID", "001001004001");
		map.put("Season", String.valueOf(travelQuarterOut.getAustralianOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		新西兰（人次）	001001004002
		map = new HashMap<>();
		map.put("sortID", "001001004002");
		map.put("Season", String.valueOf(travelQuarterOut.getZeaLandOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		大洋洲其他（人次）	001001004003
		map = new HashMap<>();
		map.put("sortID", "001001004003");
		map.put("Season", String.valueOf(travelQuarterOut.getOceaniaOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		南非（人次）	001001005001
		map = new HashMap<>();
		map.put("sortID", "001001005001");
		map.put("Season", String.valueOf(travelQuarterOut.getSouthAfricaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		埃及（人次）	001001005002
		map = new HashMap<>();
		map.put("sortID", "001001005002");
		map.put("Season", String.valueOf(travelQuarterOut.getEgyptOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		肯尼亚（人次）	001001005003
		map = new HashMap<>();
		map.put("sortID", "001001005003");
		map.put("Season", String.valueOf(travelQuarterOut.getKenyaOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		非洲其他（人次）	001001005004
		map = new HashMap<>();
		map.put("sortID", "001001005004");
		map.put("Season", String.valueOf(travelQuarterOut.getAfricaOtherOne()));
		map.put("SeasonDay", "0");
		list.add(map);
//		其他小计（人次）	001001006
		map = new HashMap<>();
		map.put("sortID", "001001006");
		map.put("Season", String.valueOf(travelQuarterOut.getOtherTotalOne()));
		map.put("SeasonDay", "0");
		list.add(map);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map2 = (Map<String, String>) iterator.next();
			String sortID = map2.get("sortID");
			String season = map2.get("Season");
			String seasonDay = map2.get("SeasonDay");
			String flag = "200";
			try {
				Thread.sleep(1000);
				logger.info("调用旅行社出境季报添加或编辑信息接口："+" 季报id:"+travelQuarterOut.getId()+" 地区编码:"+sortID);
				String rs = travelAgencyService.insertIntoOutboundTravel(areaID, code, unitMaster, filler, fillerTel, sortID, seasonDay, season, verificationCode);
				System.out.println(sortID+" : "+rs);
				if(!"添加成功".equals(rs)){
					flag = "接口返回失败";
				}
				logger.info("调用旅行社出境季报添加或编辑信息接口结果："+rs);
			} catch (Exception e) {
				e.printStackTrace();
				flag = "通信异常";
				logger.info("调用旅行社出境季报添加或编辑信息异常："+e.toString()+" 季报id:"+travelQuarterOut.getId()+" 地区编码:"+sortID);
			}
			// 添加失败
			if(!"200".equals(flag)){
				tip = "通信异常";
				TravelQuarterOutRecord record = new TravelQuarterOutRecord();
				record.setCode(code);
				record.setYear(travelQuarterOut.getYear());
				record.setQuarter(travelQuarterOut.getQuarter());
				record.setUnitMaster(unitMaster);
				record.setFiller(filler);
				record.setFillerTel(fillerTel);
				record.setSortID(sortID);
				record.setSeasonDay(seasonDay);
				record.setSeason(season);
				record.setRemark("insertIntoOutboundTravel");
				systemService.saveOrUpdate(record);
			}
		}
		return tip;
	}
	
	/**
	 * 新增或编辑国内季报
	 */
	public String addOrUpdateInlandTravelQuarter(TravelQuarterInland travelQuarterInland){

		// 获取旅行社服务接口
		PublicInterfaceSoapProxy travelAgencyService = new PublicInterfaceSoapProxy();
		String travelid = travelQuarterInland.getTid();
		// 获取旅行社信息
		Traveldata traveldata = systemService.get(Traveldata.class, travelid);
		
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = null;
		String tip = "200";
		String areaID = GlobalParams.areaID;
		String code = traveldata.getLicensenum();
		String unitMaster = traveldata.getPrincipal();
		String filler = travelQuarterInland.getReportPerson();
		String fillerTel = travelQuarterInland.getReportTelephone();
		String verificationCode = GlobalParams.travelAgencyVerificationCode;
		
		//安徽（人天，人次）	002001
		map = new HashMap<>();
		map.put("sortID", "002001");
		map.put("Season", String.valueOf(travelQuarterInland.getAhOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getAhTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getAhThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getAhFour()));
		list.add(map);
//		北京（人天，人次）	002002
		map = new HashMap<>();
		map.put("sortID", "002002");
		map.put("Season", String.valueOf(travelQuarterInland.getBjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getBjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getBjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getBjFour()));
		list.add(map);
//		福建（人天，人次）	002003
		map = new HashMap<>();
		map.put("sortID", "002003");
		map.put("Season", String.valueOf(travelQuarterInland.getFjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getFjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getFjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getFjFour()));
		list.add(map);
//		甘肃（人天，人次）	002004
		map = new HashMap<>();
		map.put("sortID", "002004");
		map.put("Season", String.valueOf(travelQuarterInland.getGansuOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGansuTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGansuThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGansuFour()));
		list.add(map);
//		广东（人天，人次）	002005
		map = new HashMap<>();
		map.put("sortID", "002005");
		map.put("Season", String.valueOf(travelQuarterInland.getGdOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGdTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGdThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGdFour()));
		list.add(map);
//		广西（人天，人次）	002006
		map = new HashMap<>();
		map.put("sortID", "002006");
		map.put("Season", String.valueOf(travelQuarterInland.getGxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGxFour()));
		list.add(map);
//		贵州（人天，人次）	002007
		map = new HashMap<>();
		map.put("sortID", "002007");
		map.put("Season", String.valueOf(travelQuarterInland.getGzOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getGzTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getGzThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getGzFour()));
		list.add(map);
//		海南（人天，人次）	002008
		map = new HashMap<>();
		map.put("sortID", "002008");
		map.put("Season", String.valueOf(travelQuarterInland.getHainanOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHainanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHainanThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHainanFour()));
		list.add(map);
//		河北（人天，人次）	002009
		map = new HashMap<>();
		map.put("sortID", "002009");
		map.put("Season", String.valueOf(travelQuarterInland.getHbOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHbTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHbThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHbFour()));
		list.add(map);
//		河南（人天，人次）	002010
		map = new HashMap<>();
		map.put("sortID", "002010");
		map.put("Season", String.valueOf(travelQuarterInland.getHenanOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHenanTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHenanThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHenanFour()));
		list.add(map);
//		黑龙江（人天，人次）	002011
		map = new HashMap<>();
		map.put("sortID", "002011");
		map.put("Season", String.valueOf(travelQuarterInland.getHljOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHljTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHljThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHljFour()));
		list.add(map);
//		湖北（人天，人次）	002012
		map = new HashMap<>();
		map.put("sortID", "002012");
		map.put("Season", String.valueOf(travelQuarterInland.getHubeiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHubeiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHubeiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHubeiFour()));
		list.add(map);
//		湖南（人天，人次）	002013
		map = new HashMap<>();
		map.put("sortID", "002013");
		map.put("Season", String.valueOf(travelQuarterInland.getHlOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getHlTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getHlThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getHlFour()));
		list.add(map);
//		吉林（人天，人次）	002014
		map = new HashMap<>();
		map.put("sortID", "002014");
		map.put("Season", String.valueOf(travelQuarterInland.getJlOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJlTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJlThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJlFour()));
		list.add(map);
//		江苏（人天，人次）	002015
		map = new HashMap<>();
		map.put("sortID", "002015");
		map.put("Season", String.valueOf(travelQuarterInland.getJsOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJsTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJsThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJsFour()));
		list.add(map);
//		江西（人天，人次）	002016
		map = new HashMap<>();
		map.put("sortID", "002016");
		map.put("Season", String.valueOf(travelQuarterInland.getJxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJxFour()));
		list.add(map);
//		辽宁（人天，人次）	002017
		map = new HashMap<>();
		map.put("sortID", "002017");
		map.put("Season", String.valueOf(travelQuarterInland.getLnOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getLnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getLnThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getLnFour()));
		list.add(map);
//		内蒙古（人天，人次）	002018
		map = new HashMap<>();
		map.put("sortID", "002018");
		map.put("Season", String.valueOf(travelQuarterInland.getNmgOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getNmgTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getNmgThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getNmgFour()));
		list.add(map);
//		宁夏（人天，人次）	002019
		map = new HashMap<>();
		map.put("sortID", "002019");
		map.put("Season", String.valueOf(travelQuarterInland.getNxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getNxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getNxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getNxFour()));
		list.add(map);
//		青海（人天，人次）	002020
		map = new HashMap<>();
		map.put("sortID", "002020");
		map.put("Season", String.valueOf(travelQuarterInland.getQinghaiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getQinghaiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getQinghaiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getQinghaiFour()));
		list.add(map);
//		山东（人天，人次）	002021
		map = new HashMap<>();
		map.put("sortID", "002021");
		map.put("Season", String.valueOf(travelQuarterInland.getSdOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getSdTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getSdThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getSdFour()));
		list.add(map);
//		山西（人天，人次）	002022
		map = new HashMap<>();
		map.put("sortID", "002022");
		map.put("Season", String.valueOf(travelQuarterInland.getJxOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getJxTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getJxThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getJxFour()));
		list.add(map);
//		陕西（人天，人次）	002023
		map = new HashMap<>();
		map.put("sortID", "002023");
		map.put("Season", String.valueOf(travelQuarterInland.getShanxiOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getShanxiTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getShanxiThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getShanxiFour()));
		list.add(map);
//		上海（人天，人次）	002024
		map = new HashMap<>();
		map.put("sortID", "002024");
		map.put("Season", String.valueOf(travelQuarterInland.getShOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getShTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getShThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getShFour()));
		list.add(map);
//		四川（人天，人次）	002025
		map = new HashMap<>();
		map.put("sortID", "002025");
		map.put("Season", String.valueOf(travelQuarterInland.getScOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getScTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getScThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getScFour()));
		list.add(map);
//		天津（人天，人次）	002026
		map = new HashMap<>();
		map.put("sortID", "002026");
		map.put("Season", String.valueOf(travelQuarterInland.getTjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getTjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getTjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getTjFour()));
		list.add(map);
//		西藏（人天，人次）	002027
		map = new HashMap<>();
		map.put("sortID", "002027");
		map.put("Season", String.valueOf(travelQuarterInland.getXzOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getXzTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getXzThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getXzFour()));
		list.add(map);
//		新疆（人天，人次）	002028
		map = new HashMap<>();
		map.put("sortID", "002028");
		map.put("Season", String.valueOf(travelQuarterInland.getXjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getXjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getXjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getXjFour()));
		list.add(map);
//		云南（人天，人次）	002029
		map = new HashMap<>();
		map.put("sortID", "002029");
		map.put("Season", String.valueOf(travelQuarterInland.getYnOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getYnTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getYnThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getYnFour()));
		list.add(map);
//		浙江（人天，人次）	002030
		map = new HashMap<>();
		map.put("sortID", "002030");
		map.put("Season", String.valueOf(travelQuarterInland.getZjOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getZjTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getZjThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getZjFour()));
		list.add(map);
//		重庆（人天，人次）	002031
		map = new HashMap<>();
		map.put("sortID", "002031");
		map.put("Season", String.valueOf(travelQuarterInland.getCqOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getCqTwo()));
		map.put("SeasonDay", String.valueOf(travelQuarterInland.getCqThree()));
		map.put("SeasonDay_boy",String.valueOf(travelQuarterInland.getCqFour()));
		list.add(map);
		// 非地区编号 001001009
		map.put("sortID", "001001009");
		map.put("Season", String.valueOf(travelQuarterInland.getDayOrgOne()));
		map.put("Season_boy", String.valueOf(travelQuarterInland.getNightTimeOne()));
		map.put("SeasonDay", "0");
		map.put("SeasonDay_boy", String.valueOf(travelQuarterInland.getNightOrgOne()));
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String flag = "200";
				Map<String, String> map2 = (Map<String, String>) iterator.next();
				String sortID = map2.get("sortID");
				String seasonDay = map2.get("SeasonDay");
				String seasonDay_boy = map2.get("SeasonDay_boy");
				String season = map2.get("Season");
				String season_boy = map2.get("Season_boy");
		   try {
			   Thread.sleep(1000);
			   logger.info("调用旅行社国内季报添加或编辑信息接口："+" 季报id:"+travelQuarterInland.getId()+" 地区编码:"+sortID);
				String rs = travelAgencyService.insertIntoInternalTourism(
						areaID, code, unitMaster, filler, fillerTel, sortID,
						seasonDay, seasonDay_boy, season, season_boy,
						verificationCode);
				logger.info("调用旅行社国内季报添加或编辑信息接口结果：" + rs);
				if (!"添加成功".equals(rs)) {
					flag = "接口返回失败";
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = "通信异常";
				logger.info("调用旅行社国内季报添加或编辑信息接口异常："+e.toString()+" 季报id:"+travelQuarterInland.getId()+" 地区编码:"+sortID);
			}
			// 添加失败
			if ("200".equals(flag)) {
				tip = "通信异常";
				TravelQuarterInlandRecord record = new TravelQuarterInlandRecord();
				record.setCode(code);
				record.setYear(travelQuarterInland.getYear());
				record.setQuarter(travelQuarterInland.getQuarter());
				record.setUnitMaster(unitMaster);
				record.setFiller(filler);
				record.setFillerTel(fillerTel);
				record.setSortID(sortID);
				record.setSeasonDay(seasonDay);
				record.setSeasonDayboy(seasonDay_boy);
				record.setSeason(season);
				record.setSeasonBoy(season_boy);
				record.setRemark("insertIntoInternalTourism");
				systemService.saveOrUpdate(record);
			}
		}
		return tip;
	
	}
	
	/**
	 * 季报审核状态
	 */
	public String[] getQuarterStatus(String year, String quarter, String license){
		GetQuarterlyStateResponseGetQuarterlyStateResult rs = null;
		try {
			Thread.sleep(1000);
			logger.info("调用获取旅行社季报审核状态接口："+" 年份:"+year+" 季度:"+quarter+" 旅行社许可证号:"+license);
			rs = new PublicInterfaceSoapProxy().getQuarterlyState(GlobalParams.areaID, license, year, quarter, GlobalParams.travelAgencyVerificationCode);
			logger.info("调用获取旅行社季报审核状态接口结果："+rs.get_any()[1].toString());
			Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		// 非空判断
		if (root.element("DocumentElement") == null) return null;
		String provinceState = root.element("DocumentElement").element("旅行社季报数据审核状态").element("provinceState").getText();
		String provinceContent = root.element("DocumentElement").element("旅行社季报数据审核状态").element("provinceContent").getText();
		return new String[]{provinceState, provinceContent};
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用获取旅行社季报审核状态接口异常："+e.toString()+" 年份:"+year+" 季度:"+quarter+" 旅行社许可证号:"+license);
			return null;
		}
	}
	
}
