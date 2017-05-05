package com.zzc.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.StarredHotel;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.travel.entity.Traveldata;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.travelagency.GetBasicInfoResponseGetBasicInfoResult;
import com.zzc.webservice.travelagency.GetBasicStateResponseGetBasicStateResult;
import com.zzc.webservice.travelagency.GetFinanceResponseGetFinanceResult;
import com.zzc.webservice.travelagency.GetInboundTravelResponseGetInboundTravelResult;
import com.zzc.webservice.travelagency.GetInternalTourismResponseGetInternalTourismResult;
import com.zzc.webservice.travelagency.GetOutboundTravelResponseGetOutboundTravelResult;
import com.zzc.webservice.travelagency.GetQuarterlyStateResponseGetQuarterlyStateResult;
import com.zzc.webservice.travelagency.PublicInterfaceSoapProxy;

public class Test {
	
	public static void main(String[] args) {}
	
	public void getFinance() throws Exception {
		String areaID = "002008013";
		String code = "L-HAN00132";
		String year = "2015";
		String verificationCode = "sql*lyj_baiwang@2017";
		GetFinanceResponseGetFinanceResult rs = null;
		try {
			rs = new PublicInterfaceSoapProxy().getFinance(areaID, code, year, verificationCode);
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
		System.out.println(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			System.out.println("填报时间:"+element.element("TableDate").getTextTrim().substring(0, 10));
			System.out.println("单位名称:"+element.element("UnitName").getTextTrim());
			System.out.println("单位负责人:"+element.element("UnitMaster").getTextTrim());
			System.out.println("填表人:"+element.element("Filler").getTextTrim());
			System.out.println("填表人电话:"+element.element("FillerTel").getTextTrim());
			System.out.println("流动资产小计:"+element.element("CurrentAssetsTotal").getTextTrim());
			System.out.println("长期投资:"+element.element("LongTermInvest").getTextTrim());
			System.out.println("固定资产小计:"+element.element("FixAssetsTotal").getTextTrim());
			System.out.println("固定资产原价:"+element.element("FixAssetsInitialPrice").getTextTrim());
			System.out.println("资产合计:"+element.element("CapitalTotal").getTextTrim());
			System.out.println("负债合计  :"+element.element("TotalLiabilities").getTextTrim());
			System.out.println("所有者权益合计:"+element.element("OwnerBenefitTotal").getTextTrim());
			System.out.println("实收资本:"+element.element("RealCapital").getTextTrim());
			System.out.println("营业收入:"+element.element("OperationIncome").getTextTrim());
			System.out.println("入境旅游营业收入:"+element.element("InboundRevenue").getTextTrim());
			System.out.println("出境旅游营业收入:"+element.element("OutboundRevenues").getTextTrim());
			System.out.println("国内旅游营业收入:"+element.element("DomesticRevenue").getTextTrim());
			System.out.println("营业成本:"+element.element("OperationCost").getTextTrim());
			System.out.println("营业费用  :"+element.element("OperationFee").getTextTrim());
			System.out.println("营业税金及附加:"+element.element("OperationTaxAddition").getTextTrim());
			System.out.println("主营业务利润:"+element.element("BusinessProfit").getTextTrim());
			System.out.println("入境旅游业务利润:"+element.element("InbundProfit").getTextTrim());
			System.out.println("出境旅游业务利润:"+element.element("OutboundProfit").getTextTrim());
			System.out.println("国内旅游业务利润:"+element.element("DomesticProfit").getTextTrim());
			System.out.println("管理费用:"+element.element("ManagementFee").getTextTrim());
			System.out.println("税金:"+element.element("Tax").getTextTrim());
			System.out.println("财务费用:"+element.element("FinanceFee").getTextTrim());
			System.out.println("利息支出:"+element.element("InterestPay").getTextTrim());
			System.out.println("营业利润:"+element.element("OperationProfit").getTextTrim());
			System.out.println("利润总额:"+element.element("ProfitTotal").getTextTrim());
			System.out.println("所得税:"+element.element("IncomeTax").getTextTrim());
			System.out.println("应付职工酬薪:"+element.element("AnnualPayTotal").getTextTrim());
			System.out.println("全部从业人员年平均人数:"+element.element("YearendEmployment").getTextTrim());
			System.out.println("其中大专以上学历人数:"+element.element("JuniorEmployment").getTextTrim());
			System.out.println("累计折旧:"+element.element("AccumulatedDepreciation").getTextTrim());
			System.out.println("其中：本年折旧:"+element.element("YearDepreciation").getTextTrim());
			System.out.println("差旅费:"+element.element("TravelExpenses").getTextTrim());
			System.out.println("公允价值变动收益:"+element.element("Gains").getTextTrim());
			System.out.println("投资收益:"+element.element("ReturnInvestment").getTextTrim());
			System.out.println("是否执行2006年《企业会计准则》:"+element.element("WhetherPerform").getTextTrim());
			System.out.println("营业外收入:"+element.element("NonIncome").getTextTrim());
			System.out.println("其中：政府补贴:"+element.element("Subsidies").getTextTrim());
			System.out.println("销售费用:"+element.element("CostSales").getTextTrim());
			System.out.println("毛利润:"+element.element("GrossProfit").getTextTrim());
			System.out.println("本年应交增值税:"+element.element("TheVAT").getTextTrim());
			System.out.println("签订劳动合同的导游人数:"+element.element("LaborContract").getTextTrim());
			System.out.println("领队人数:"+element.element("LeaderNumber").getTextTrim());
			System.out.println("门店数量:"+element.element("StoresNumber").getTextTrim());
			System.out.println("分社数量:"+element.element("BranchNumber").getTextTrim());
			System.out.println("子公司数量:"+element.element("SubsidiariesNumber").getTextTrim());
			System.out.println("由执行2006年《企业会计准则》企业填报：资产减值损失:"+element.element("Loss").getTextTrim());
			System.out.println("旅行社所属地区编码:"+element.element("City").getTextTrim());
		}
		
		
//		GetBasicInfoResponseGetBasicInfoResult rs = new PublicInterfaceSoapProxy().getBasicInfo("002008013", "sql*lyj_baiwang@2017");
//		Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
//		Element root = doc.getRootElement();
//		List<Element> nodes = root.element("NewDataSet").elements();
//		for (int i = 0; i < nodes.size(); i++) {
//			System.out.println(nodes.get(i).element("UserName").getTextTrim());
//			String areaID = "002008013";
//			String code = nodes.get(i).element("UserName").getTextTrim();
//			String year = "2015";
//			String verificationCode = "sql*lyj_baiwang@2017";
//			
//			GetFinanceResponseGetFinanceResult rs1 = null;
//			try {
//				rs1 = new PublicInterfaceSoapProxy().getFinance(areaID, code, year, verificationCode);
//			} catch (RemoteException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//			System.out.println(rs1.get_any()[1].toString());
//		}
	}
	
	public static void getOutboundTravel() throws Exception {
		String areaID = "002008013";
		String code = "L-HAN00214";
		String year = "2016";
		String quarter = "1";
		String sortID = "001001";
		String verificationCode = "sql*lyj_baiwang@2017";
		
		GetOutboundTravelResponseGetOutboundTravelResult rs = null;
		try {
			rs = new PublicInterfaceSoapProxy().getOutboundTravel(areaID, code, year, quarter, sortID, verificationCode);
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
		System.out.println(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			System.out.println("人次:"+element.element("season").getTextTrim());
			System.out.println("人天:"+element.element("SeasonDay").getTextTrim());
		}
		
		
//		GetBasicInfoResponseGetBasicInfoResult rs = new PublicInterfaceSoapProxy().getBasicInfo("002008013", "sql*lyj_baiwang@2017");
//		Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
//		Element root = doc.getRootElement();
//		List<Element> nodes = root.element("NewDataSet").elements();
//		for (int i = 0; i < nodes.size(); i++) {
//			System.out.println(nodes.get(i).element("UserName").getTextTrim());
//			String areaID = "002008013";
//			String code = nodes.get(i).element("UserName").getTextTrim();
//			String year = "2015";
//			String quarter = "4";
//			String sortID = "001001";
//			String verificationCode = "sql*lyj_baiwang@2017";
//			
//			GetOutboundTravelResponseGetOutboundTravelResult rs1 = null;
//			try {
//				rs1 = new PublicInterfaceSoapProxy().getOutboundTravel(areaID, code, year, quarter, sortID, verificationCode);
//			} catch (RemoteException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//			System.out.println(rs1.get_any()[1].toString());
//		}
	}
	
	public static void getInboundTravel() throws Exception {
		String areaID = "002008013";
		String code = "L-HAN00214";
		String year = "2016";
		String quarter = "1";
		String sortID = "001001001001";
		String verificationCode = "sql*lyj_baiwang@2017";
		
		GetInboundTravelResponseGetInboundTravelResult rs = null;
		try {
			rs = new PublicInterfaceSoapProxy().getInboundTravel(areaID, code, year, quarter, sortID, verificationCode);
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
		System.out.println(rs.get_any()[1].toString());
		Element root = doc.getRootElement();
		List<Element> nodes = root.element("NewDataSet").elements();
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			System.out.println("外联人次:"+element.element("season").getTextTrim());
			System.out.println("接待人次:"+element.element("SeasonDay").getTextTrim());
			System.out.println("外联人天:"+element.element("SeasonAdmit").getTextTrim());
			System.out.println("接待人天:"+element.element("SeasonAdmitDay").getTextTrim());
		}
		
//		GetBasicInfoResponseGetBasicInfoResult rs = new PublicInterfaceSoapProxy().getBasicInfo("002008013", "sql*lyj_baiwang@2017");
//		Document doc = DocumentHelper.parseText(rs.get_any()[1].toString());
//		Element root = doc.getRootElement();
//		List<Element> nodes = root.element("NewDataSet").elements();
//		for (int i = 0; i < nodes.size(); i++) {
//			System.out.println(nodes.get(i).element("UserName").getTextTrim());
//			String areaID = "002008013";
//			String code = nodes.get(i).element("UserName").getTextTrim();
//			String year = "2016";
//			String quarter = "1";
//			String sortID = "001001001001";
//			String verificationCode = "sql*lyj_baiwang@2017";
//			
//			GetInboundTravelResponseGetInboundTravelResult rs1 = null;
//			try {
//				rs1 = new PublicInterfaceSoapProxy().getInboundTravel(areaID, code, year, quarter, sortID, verificationCode);
//			} catch (RemoteException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//			System.out.println(rs1.get_any()[1].toString());
//		}
	}
	
	public static void getInternalTourism() {
		String areaID = "002008013";
		String code = "L-HAN003741";
		String year = "2016";
		String quarter = "1";
		String sortID = "002002";
		String verificationCode = "sql*lyj_baiwang@2017";
		GetInternalTourismResponseGetInternalTourismResult rs = null;
		// webservice获取所有国内报表数据
		try {
			rs = new PublicInterfaceSoapProxy().getInternalTourism(areaID, code, year, quarter, sortID, verificationCode);
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
		for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			System.out.println("本季组织人次:"+element.element("season").getTextTrim());
			System.out.println("本季接待人次:"+element.element("Season_boy").getTextTrim());
			System.out.println("本季组织人天:"+element.element("SeasonDay").getTextTrim());
			System.out.println("本季接待人天:"+element.element("SeasonDay_boy").getTextTrim());
		}
	}
	
	/**
	 * 获取旅行社基本信息
	 */
	public static void getBasicInfo() {
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
			traveldata.setPhone(element.element("mobile").getTextTrim());
			traveldata.setPhoneextension(element.element("Tel").getTextTrim());
			traveldata.setMobile(element.element("TelNo").getTextTrim());
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
			list.add(traveldata);
			// 输出值
			System.out.println("组织机构代码:"+element.element("Code").getTextTrim());
			System.out.println("工商登记注册时间:"+element.element("TableDate").getTextTrim());
			System.out.println("单位名称:"+element.element("UnitName").getTextTrim());
			System.out.println("法定代表人:"+element.element("Delegate").getTextTrim());
			System.out.println("用户名:"+element.element("UserName").getTextTrim());
			System.out.println("单位负责人:"+element.element("UnitMaster").getTextTrim());
			System.out.println("填表人:"+element.element("Filler").getTextTrim());
			System.out.println("填表人电话:"+element.element("FillerTel").getTextTrim());
			System.out.println("单位所在地:"+element.element("Adessr").getTextTrim());
			System.out.println("统一社会信用代码:"+element.element("Province").getTextTrim());
			System.out.println("所属地区:"+element.element("City").getTextTrim());
			System.out.println("邮政编码:"+element.element("Postcode").getTextTrim());
			System.out.println("行政区划码:"+element.element("RegionalismCode").getTextTrim());
			System.out.println("电话号码:"+element.element("mobile").getTextTrim());
			System.out.println("电话分机号码:"+element.element("Tel").getTextTrim());
			System.out.println("移动电话:"+element.element("TelNo").getTextTrim());
			System.out.println("传真号码:"+element.element("Fax").getTextTrim());
			System.out.println("传真分机号码:"+element.element("FaxNo").getTextTrim());
			System.out.println("互联网网址:"+element.element("WebSite").getTextTrim());
			System.out.println("电子邮件信箱:"+element.element("Email").getTextTrim());
			System.out.println("企业登记注册类型:"+element.element("RegistrationType").getTextTrim());
			System.out.println("是否旅行社协会会员:"+element.element("UnitType").getTextTrim());
			System.out.println("批准文号:"+element.element("AccommodationStar").getTextTrim());
			System.out.println("旅行社经营场所:"+element.element("TravelAgencyType").getTextTrim());
			System.out.println("旅行社等级:"+element.element("SceneSpotLevel").getTextTrim());
			System.out.println("是否经营出境游:"+element.element("OutBoundTourism").getTextTrim());
			System.out.println("是否经营边境游:"+element.element("BorderTour").getTextTrim());
			System.out.println("英文名称:"+element.element("EnglishName").getTextTrim());
			System.out.println("QQ号:"+element.element("QQ").getTextTrim());
		}
	}
}
