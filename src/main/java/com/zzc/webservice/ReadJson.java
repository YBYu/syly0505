package com.zzc.webservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpRequest;

import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicDataSta;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.scenicmanage.entity.ScenicSpotTicket;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 景区接口
 * @author pan
 *
 */

public class ReadJson {
	public static String loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return json.toString();
    }
	//查看当前key和IP可以管理的行政区划
	public String findProvince(){
		 	String url = "http://test.cnta.gov.cn/apis/find_province?key=0.675221457437362";
	        String json = loadJson(url);
	        String code=JSONObject.fromObject(json).getJSONObject("province").getString("code");
	        return code;
	}
	//查看特定行政区划
	public String findProvinceByCode(){
		String code1=findProvince();
	 	String url = "http://test.cnta.gov.cn/apis/find_province_by_code?key=0.675221457437362&code="+code1;
        String json = loadJson(url);
        String code=JSONObject.fromObject(json).getJSONObject("province").getString("code");
        return code;
}//查看某行政区划的下属行政区划
	public List<String> subProvinces(){
		String code1=findProvinceByCode();
	 	String url = "http://test.cnta.gov.cn/apis/sub_provinces?key=0.675221457437362&code="+code1;
        String json = loadJson(url);
        List<String> list=new ArrayList<String>();
        JSONObject jsonArray=JSONObject.fromObject(json);
        JSONArray j=jsonArray.getJSONArray("codes");
        for(int i=0;i<j.size();i++){
        	list.add(j.getString(i));
        }
        return list;
}//查看行政区划下属景区
	public static List<String> instsOfProvince(){
	 	String url = "http://test.cnta.gov.cn/apis/insts_of_province?key=0.675221457437362&province_code=460200";
        String json = loadJson(url);
        List<String> list=new ArrayList<String>();
        JSONObject jsonArray=JSONObject.fromObject(json);
        JSONArray j=jsonArray.getJSONArray("code");
        for(int i=0;i<j.size();i++){
        	list.add(j.getString(i));
        }
        return list;
}
	//根据名称查找景区
	public static String  findInstsByName(String name){
		String res=null;
	 	String url = "http://test.cnta.gov.cn/apis/find_insts_by_name?key=0.675221457437362&name="+name;
        String json = loadJson(url);
        List<String> list=new ArrayList<String>();
        JSONObject jsonArray=JSONObject.fromObject(json);
        JSONArray j=jsonArray.getJSONArray("code");
        for(int i=0;i<j.size();i++){
        	list.add(j.getString(i));
        }
        if(list!=null){
        	res=list.get(0);
        } 
        return res;
}
//查看景区详情
public static ScenicDataSta findInstByCode(String code) {
        String url = "http://test.cnta.gov.cn/apis/find_inst_by_code?key=0.675221457437362&code="+code;
        String json = loadJson(url);
        JSONObject node = JSONObject.fromObject(json);
        JSONObject nodes=node.getJSONObject("institution");
//        province 省
        String province= nodes.getJSONObject("province").getString("code");
//        city 市
        String city=  nodes.getJSONObject("city").getString("code");
//        county 县
        String county=  nodes.getJSONObject("county").getString("code");
//        name 景区名称
        String name= nodes.getString("name");
//        jq_code 景区代码
        String jqCode= nodes.getString("jq_code");
//        address 地址
        String address= nodes.getString("address");
//        post 邮编
        String post= nodes.getString("post");
//        nature_id 机构性质代码，参见一、e)i机构性质
        String naturId= nodes.getString("nature_id");
//        website 网址
        String website= nodes.getString("website");
//        consult_phone 咨询电话
        String consultPhone= nodes.getString("consult_phone");
//        type_id 景区类型代码，参见一、e)ii景区类型
        String typeId= nodes.getString("type_id");
//        establish 开业时间
        String establish= nodes.getString("establish");
//        invest 投资主体
        String invest= nodes.getString("invest");
//        higher_authority 上级主管单位
        String higherAuthority= nodes.getString("higher_authority");
//        level_id 等级代码，参见一、e)iii景区等级
        String levelId= nodes.getString("level_id");  
//        level_date 等级公告时间
        String levelDate= nodes.getString("level_date");
//        capacity 日接待最大容量（万人）
        String capacity= nodes.getString("capacity");
//        area 面积（公顷）
        String area= nodes.getString("area");
                
        String ticketTerm= nodes.getString("ticket_term");    
//     ticket_id 门票形式代码，参见一、e)iv门票类型（淡旺季）
        String ticketId= nodes.getString("ticket_id");
        //note 备注
        String note= nodes.getString("note");
       // status 审核状态代码，参见一、e)viii审查状态
        String status=  nodes.getJSONObject("status").getString("id");      
        //one_ticket 是否一票制（1代表是，0代表否）
        String oneTicket= nodes.getString("one_ticket");
        String termType1=nodes.getString("term_type1");
        String price1 =nodes.getString("price1");
        String beginMonth1=nodes.getString("begin_month1");
        String beginDate1=nodes.getString("begin_date1");
        String endMonth1=nodes.getString("end_month1");
        String endDate1=nodes.getString("end_date1");
        String openHour1=nodes.getString("open_hour1");
        String openMinute1=nodes.getString("open_minute1");
        String closeHour1=nodes.getString("close_hour1");
        String closeMinute1=nodes.getString("close_minute1");
        String termType2=nodes.getString("term_type2");
        String price2 =nodes.getString("price2");
        String beginMonth2=nodes.getString("begin_month2");
        String beginDate2=nodes.getString("begin_date2");
        String endMonth2=nodes.getString("end_month2");
        String endDate2=nodes.getString("end_date2");
        String openHour2=nodes.getString("open_hour2");
        String openMinute2=nodes.getString("open_minute2");
        String closeHour2=nodes.getString("close_hour2");
        String closeMinute2=nodes.getString("close_minute2");
        String termType3=nodes.getString("term_type3");
        String price3 =nodes.getString("price3");
        String beginMonth3=nodes.getString("begin_month3");
        String beginDate3=nodes.getString("begin_date3");
        String endMonth3=nodes.getString("end_month3");
        String endDate3=nodes.getString("end_date3");
        String openHour3=nodes.getString("open_hour3");
        String openMinute3=nodes.getString("open_minute3");
        String closeHour3=nodes.getString("close_hour3");
        String closeMinute3=nodes.getString("close_minute3");
        String termType4=nodes.getString("term_type4");
        String price4 =nodes.getString("price4");
        String beginMonth4=nodes.getString("begin_month4");
        String beginDate4=nodes.getString("begin_date4");
        String endMonth4=nodes.getString("end_month4");
        String endDate4=nodes.getString("end_date4");
        String openHour4=nodes.getString("open_hour4");
        String openMinute4=nodes.getString("open_minute4");
        String closeHour4=nodes.getString("close_hour4");
        String closeMinute4=nodes.getString("close_minute4");
        String termType5=nodes.getString("term_type5");
        String price5 =nodes.getString("price5");
        String beginMonth5=nodes.getString("begin_month5");
        String beginDate5=nodes.getString("begin_date5");
        String endMonth5=nodes.getString("end_month5");
        String endDate5=nodes.getString("end_date5");
        String openHour5=nodes.getString("open_hour5");
        String openMinute5=nodes.getString("open_minute5");
        String closeHour5=nodes.getString("close_hour5");
        String closeMinute5=nodes.getString("close_minute5");

        JSONArray a=nodes.getJSONArray("titles");
        StringBuffer titles=new StringBuffer();
        for(int i=0;i<a.size();i++){
        	
        	if(i==a.size()-1){
        		titles.append(a.getJSONObject(i).getString("id"));
        		break;
        	}
        	titles.append(a.getJSONObject(i).getString("id")+",");
        }   
        String title=titles.toString();
        ScenicDataSta scenicData=new ScenicDataSta();
        scenicData.setName(name);
        scenicData.setScenictitle(title);
        scenicData.setAddress(address);
        scenicData.setConsultphone(consultPhone);
        scenicData.setCity(city);
        scenicData.setLevel(levelId);
        scenicData.setLeveldate(levelDate);
        scenicData.setOrgproperty(naturId.equals("null")?0:Integer.valueOf(naturId));
        scenicData.setProvince(province);
        scenicData.setArea(county);
        scenicData.setTicketisOne(Byte.valueOf(oneTicket));
        scenicData.setZipcode(post);
        scenicData.setUrl(website);
        scenicData.setScenictype(typeId);
        scenicData.setOpentime(establish.equals("null")?"":establish.substring(0,10));
        scenicData.setInvestunit(invest);
        scenicData.setSuperiorunit(higherAuthority);
        scenicData.setLeveldate(levelDate);
        scenicData.setDaylimit(capacity.equals("null")?0.0:Double.valueOf(capacity));
        scenicData.setAcreage(area.equals("null")?0.0:Double.valueOf(area));
        scenicData.setStatus(status);
        scenicData.setRemarks(note);
        scenicData.setTickettype(ticketId);
        scenicData.setCode(jqCode);
        scenicData.setTickettype(ticketTerm);
        scenicData.setTermType1(termType1);
        scenicData.setPrice1(price1);
        scenicData.setBeginDate1(beginDate1);
        scenicData.setBeginMonth1(beginMonth1);
        scenicData.setEndDate1(endDate1);
        scenicData.setEndMonth1(endMonth1);
        scenicData.setOpenHour1(openHour1);
        scenicData.setOpenMinute1(openMinute1);
        scenicData.setCloseHour1(closeHour1);
        scenicData.setCloseMinute1(closeMinute1);
        
        scenicData.setTermType2(termType2);
        scenicData.setPrice2(price2);
        scenicData.setBeginDate2(beginDate2);
        scenicData.setBeginMonth2(beginMonth2);
        scenicData.setEndDate2(endDate2);
        scenicData.setEndMonth2(endMonth2);
        scenicData.setOpenHour2(openHour2);
        scenicData.setOpenMinute2(openMinute2);
        scenicData.setCloseHour2(closeHour2);
        scenicData.setCloseMinute2(closeMinute2);
        
        scenicData.setTermType3(termType3);
        scenicData.setPrice3(price3);
        scenicData.setBeginDate3(beginDate3);
        scenicData.setBeginMonth3(beginMonth3);
        scenicData.setEndDate3(endDate3);
        scenicData.setEndMonth3(endMonth3);
        scenicData.setOpenHour3(openHour3);
        scenicData.setOpenMinute3(openMinute3);
        scenicData.setCloseHour3(closeHour3);
        scenicData.setCloseMinute3(closeMinute3);
        
        scenicData.setTermType4(termType4);
        scenicData.setPrice4(price4);
        scenicData.setBeginDate4(beginDate4);
        scenicData.setBeginMonth4(beginMonth4);
        scenicData.setEndDate4(endDate4);
        scenicData.setEndMonth4(endMonth4);
        scenicData.setOpenHour4(openHour4);
        scenicData.setOpenMinute4(openMinute4);
        scenicData.setCloseHour4(closeHour4);
        scenicData.setCloseMinute4(closeMinute4);
        
        scenicData.setTermType5(termType5);
        scenicData.setPrice5(price5);
        scenicData.setBeginDate5(beginDate5);
        scenicData.setBeginMonth5(beginMonth5);
        scenicData.setEndDate5(endDate5);
        scenicData.setEndMonth5(endMonth5);
        scenicData.setOpenHour5(openHour5);
        scenicData.setOpenMinute5(openMinute5);
        scenicData.setCloseHour5(closeHour5);
        scenicData.setCloseMinute5(closeMinute5);
        /*ScenicSpotTicket ticket1=new ScenicSpotTicket();
        ticket1.setBeginDate(beginDate1);
        ticket1.setBeginMonth(beginMonth1);
        ticket1.setCloseHour(closeHour1);
        ticket1.setCloseMinute(closeMinute1);
        ticket1.setEndDate(endDate1);
        ticket1.setEndMonth(endMonth1);
        ticket1.setOpenHour(openHour1);
        ticket1.setOpenMinute(openMinute1);
        ticket1.setPrice(price1);
        ticket1.setType(termType1);
        ScenicSpotTicket ticket2=new ScenicSpotTicket();
        ticket2.setBeginDate(beginDate2);
        ticket2.setBeginMonth(beginMonth2);
        ticket2.setCloseHour(closeHour2);
        ticket2.setCloseMinute(closeMinute2);
        ticket2.setEndDate(endDate2);
        ticket2.setEndMonth(endMonth2);
        ticket2.setOpenHour(openHour2);
        ticket2.setOpenMinute(openMinute2);
        ticket2.setPrice(price2);
        ticket2.setType(termType2);
        ScenicSpotTicket ticket3=new ScenicSpotTicket();
        ticket3.setBeginDate(beginDate3);
        ticket3.setBeginMonth(beginMonth3);
        ticket3.setCloseHour(closeHour3);
        ticket3.setCloseMinute(closeMinute3);
        ticket3.setEndDate(endDate3);
        ticket3.setEndMonth(endMonth3);
        ticket3.setOpenHour(openHour3);
        ticket3.setOpenMinute(openMinute3);
        ticket3.setPrice(price3);
        ticket3.setType(termType3);
        ScenicSpotTicket ticket4=new ScenicSpotTicket();
        ticket4.setBeginDate(beginDate4);
        ticket4.setBeginMonth(beginMonth4);
        ticket4.setCloseHour(closeHour4);
        ticket4.setCloseMinute(closeMinute4);
        ticket4.setEndDate(endDate4);
        ticket4.setEndMonth(endMonth4);
        ticket4.setOpenHour(openHour4);
        ticket4.setOpenMinute(openMinute4);
        ticket4.setPrice(price4);
        ticket4.setType(termType4);
        ScenicSpotTicket ticket5=new ScenicSpotTicket();
        ticket5.setBeginDate(beginDate5);
        ticket5.setBeginMonth(beginMonth5);
        ticket5.setCloseHour(closeHour5);
        ticket5.setCloseMinute(closeMinute5);
        ticket5.setEndDate(endDate5);
        ticket5.setEndMonth(endMonth5);
        ticket5.setOpenHour(openHour5);
        ticket5.setOpenMinute(openMinute5);
        ticket5.setPrice(price5);
        ticket5.setType(termType5);
        List<ScenicSpotTicket> list=new ArrayList<ScenicSpotTicket>();
        list.add(ticket1);
        list.add(ticket2);
        list.add(ticket3);
        list.add(ticket4);
        list.add(ticket5);      */
      return scenicData;  
	}
/**
 * 删除景区
 */
public static boolean delScenic(String code){
	String url = "http://test.cnta.gov.cn/apis/del_inst?key=0.675221457437362&code="+code;
	String json = loadJson(url);
	boolean flag=false;
	if(json!=null&&!json.equals("")){
		JSONObject node = JSONObject.fromObject(json);
		String msg=node.getString("message");
		if(msg.equals("deleted")){
			flag=true;
		}
	}
	
	return flag;
	
}

/**
 * 读取年报
 * @param year
 * @param code
 * @return
 */
public static ScenicSpotAnnual  findAnnual(int year ,String code ){
		String url = "http://test.cnta.gov.cn/apis/find_annual?key=0.675221457437362&code="
				+ code + "&year=" + year;
		String json = loadJson(url);
		JSONObject node = JSONObject.fromObject(json);
		JSONObject nodes = node.getJSONObject("annual");
		ScenicSpotAnnual scenicSpotAnnual =null;
		if(!nodes.toString().equals("null")){
			// wealth 资产总额（万元）
			String wealth = nodes.getString("wealth");
			// total 年度建设投资（万元）
			String total = nodes.getString("total");
			// construct_part 景区内部建设投资（万元）
			String constructPart = nodes.getString("construct_part");
			// out_part 景区外部建设投资（万元）
			String outPart = nodes.getString("out_part");
			// source_appr 拨款（万元）
			String sourceAppr = nodes.getString("source_appr");
			// source_debt 贷款（万元）
			String sourceDebt = nodes.getString("source_debt");
			// source_self 自筹（万元）
			String sourceSelf = nodes.getString("source_self");
			// income 景区经营总收入（万元）
			String income = nodes.getString("income");
			// ticket_income 门票收入（万元）
			String ticketIncome = nodes.getString("ticket_income");
			// product_income 商品收入（万元）
			String productIncome = nodes.getString("product_income");
			// catering_income 餐饮收入（万元）
			String cateringIncome = nodes.getString("catering_income");
			// traffic_income 交通收入（万元）
			String trafficIncome = nodes.getString("traffic_income");
			// lodging_income 住宿收入（万元）
			String lodgingIncome = nodes.getString("lodging_income");
			// show_income 演艺收入（万元）
			String showIncome = nodes.getString("show_income");
			// other_income 其他收入（万元）
			String otherIncome = nodes.getString("other_income");
			// tourists 接待人次（万人次）
			String tourists = nodes.getString("tourists");
			// free_tourists 其中免票人次（万人次）
			String freeTourists = nodes.getString("free_tourists");
			// total_number 就业人数（人）
			String totalNumber = nodes.getString("total_number");
			// static_staff 固定用工（人）
			String staticStaff = nodes.getString("static_staff");
			// part_staff 临时（季节性）用工（人次）
			String partStaff = nodes.getString("part_staff");
			// guide 专职导游人数（人）
			String guide = nodes.getString("guide");
			// note 备注
			String note = nodes.getString("note");
			// status 审核状态代码，参见一、e)viii审查状态
			String status = nodes.getJSONObject("status").getString("id");
			 scenicSpotAnnual = new ScenicSpotAnnual();
			scenicSpotAnnual.setAssetstotal(Double.valueOf(wealth.equals("null")||wealth.equals("")?"0.0":wealth));
			scenicSpotAnnual.setBuildinvest(Double.valueOf(total.equals("null")||total.equals("")?"0.0":total));
			scenicSpotAnnual.setInbuild(Double.valueOf(constructPart.equals("null")||constructPart.equals("")?"0.0":constructPart));
			scenicSpotAnnual.setOutbuild(Double.valueOf(outPart.equals("null")||outPart.equals("")?"0.0":outPart));
			scenicSpotAnnual.setAppropriation(Double.valueOf(sourceAppr.equals("null")||sourceAppr.equals("")?"0.0":sourceAppr));
			scenicSpotAnnual.setLoan(Double.valueOf(sourceDebt.equals("null")||sourceDebt.equals("")?"0.0":sourceDebt));
			scenicSpotAnnual.setFundsself(Double.valueOf(sourceSelf.equals("null")||sourceSelf.equals("")?"0.0":sourceSelf));
			scenicSpotAnnual.setTotalincome(Double.valueOf(income.equals("null")||income.equals("")?"0.0":income));
			scenicSpotAnnual.setShopincome(Double.valueOf(productIncome.equals("null")||productIncome.equals("")?"0.0":productIncome));
			scenicSpotAnnual.setTicketincome(Double.valueOf(ticketIncome.equals("null")||ticketIncome.equals("")?"0.0":ticketIncome));
			scenicSpotAnnual.setFoodincome(Double.valueOf(cateringIncome.equals("null")||cateringIncome.equals("")?"0.0":cateringIncome));
			scenicSpotAnnual.setTrafficincome(Double.valueOf(trafficIncome.equals("null")||trafficIncome.equals("")?"0.0":trafficIncome));
			scenicSpotAnnual.setPutUpincome(Double.valueOf(lodgingIncome.equals("null")||lodgingIncome.equals("")?"0.0":lodgingIncome));
			scenicSpotAnnual.setShowincome(Double.valueOf(showIncome.equals("null")||showIncome.equals("")?"0.0":showIncome));
			scenicSpotAnnual.setOtherincome(Double.valueOf(otherIncome.equals("null")||otherIncome.equals("")?"0.0":otherIncome));
			scenicSpotAnnual.setReceptionnum(Double.valueOf(tourists.equals("null")||tourists.equals("")?"0.0":tourists));
			scenicSpotAnnual.setExemptTicketnum(Double.valueOf(freeTourists.equals("null")||freeTourists.equals("")?"0.0":freeTourists));
			scenicSpotAnnual.setJobnum(Integer.valueOf(totalNumber.equals("null")||totalNumber.equals("")?"0":totalNumber));
			scenicSpotAnnual.setFixedworker(Integer.valueOf(staticStaff.equals("null")||staticStaff.equals("")?"0":staticStaff));
			scenicSpotAnnual.setTempworker(Integer.valueOf(partStaff.equals("null")||partStaff.equals("")?"0":partStaff));
			scenicSpotAnnual.setGuidenum(Integer.valueOf(guide.equals("null")||guide.equals("")?"0":guide));
			scenicSpotAnnual.setRemarks(note);
			scenicSpotAnnual.setStatus(status);
		
		}
			
		return scenicSpotAnnual;
		
}
/**
 * 读取季度经营信息
 * @param args
 * @throws Exception
 */
public static ScenicSpotQuarter findSeason(int year ,String code,int season ){
	String url = "http://test.cnta.gov.cn/apis/find_season?key=0.675221457437362&code="+code+"&year="+year+"&quarter_id="+season;
	String json = loadJson(url);
	JSONObject node = JSONObject.fromObject(json);
	JSONObject nodes = node.getJSONObject("season");
	ScenicSpotQuarter scenicSpotQuarter =null;
	if(!nodes.toString().equals("null")){
		//income 景区经营总收入（万元）
		 String income =nodes.getString("income");
		 //ticket_income 门票收入（万元）
		 String ticketIncome =nodes.getString("ticket_income");
		 //tourists 接待人次（万人次）
		 String tourists  =nodes.getString("tourists");
		 //free_tourists 其中免票人次（万人次）
		 String freeTourists =nodes.getString("free_tourists");
		 //note 备注
		 String note  =nodes.getString("note");
//			status 审核状态代码
		 String status =nodes.getJSONObject("status").getString("id");
		 
		 
		 	scenicSpotQuarter=new ScenicSpotQuarter();
		 	scenicSpotQuarter.setTotalincome(Double.valueOf(income.equals("")?"0.0":income));
		 	scenicSpotQuarter.setTicketincome(Double.valueOf(ticketIncome.equals("")?"0.0":ticketIncome));
		 	scenicSpotQuarter.setReceptionnum(Double.valueOf(tourists.equals("")?"0.0":tourists));
		 	scenicSpotQuarter.setExemptticketnum(Double.valueOf(freeTourists.equals("")?"0.0":freeTourists));
		 	scenicSpotQuarter.setRemarks(note);
		 	scenicSpotQuarter.setStatus(status);
	}
		
	return scenicSpotQuarter;
	
}
/**
 * 读取季报审核意见
 * @param args
 * @throws Exception
 */
public static String getQuarterComments(int year ,String code,int season ){
	String url1 = "http://test.cnta.gov.cn/apis/season_comments?key=0.675221457437362&code="+code+"&year="+year+"&quarter_id="+season;
	String json1=loadJson(url1);
	JSONObject node1= JSONObject.fromObject(json1);
	JSONArray comment=node1 .getJSONArray("comments");
    String comments=comment.getString(comment.size()-1);
    String fincom=comments.substring(1, comments.length()-1).substring(0, comments.indexOf(",")-1);
	return fincom;
	 
	
}
/**
 * 读取年报审核意见
 * @param args
 * @throws Exception
 */
public static String getAnnualComments(int year ,String code  ){
	String url1="http://test.cnta.gov.cn/apis/annual_comments?key=0.675221457437362&code="
			+ code + "&year=" + year;
	String json1=loadJson(url1);
	JSONObject node1= JSONObject.fromObject(json1);
	JSONArray comment=node1 .getJSONArray("comments");
    String comments=comment.getString(comment.size()-1);
    String fincom=comments.substring(1, comments.length()-1).substring(0, comments.indexOf(",")-1);
	return fincom;
	 
	
}
/**
 * 读取景区基本信息审核意见
 
 */
public static String getScenicComments(String code  ){
	String url1="http://test.cnta.gov.cn/apis/institution_comments?key=0.675221457437362&code="+code;
	String json1=loadJson(url1);
	JSONObject node1= JSONObject.fromObject(json1);
	JSONArray comment=node1 .getJSONArray("comments");
    String comments=comment.getString(comment.size()-1);
    String fincom=comments.substring(1, comments.length()-1).substring(0, comments.indexOf(",")-1);
	return fincom;
	 
	
}
 //post 请求发送
public static String sendPost(String urlPath,String param) throws Exception{
    //建立连接
    URL url=new URL(urlPath);
    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
    //设置参数
    httpConn.setDoOutput(true);   //需要输出
    httpConn.setDoInput(true);   //需要输入
    httpConn.setUseCaches(false);  //不允许缓存
    httpConn.setRequestMethod("POST");   //设置POST方式连接
    //设置请求属性
    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
    httpConn.setRequestProperty("Charset", "UTF-8");
    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
    httpConn.connect();
    //建立输入流，向指向的URL传入参数
    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
    byte[] bytes=param.getBytes();
    dos.writeBytes(new String(bytes,"UTF-8"));
    dos.flush();
    dos.close();
    //获得响应状态
    int resultCode=httpConn.getResponseCode();
    if(HttpURLConnection.HTTP_OK==resultCode){
      StringBuffer sb=new StringBuffer();
      String readLine=new String();
      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
      while((readLine=responseReader.readLine())!=null){
        sb.append(readLine).append("\n");
      }
      responseReader.close();
      System.out.println(sb.toString());
      return sb.toString();
    } else{
    	return "请求错误:"+resultCode;
    }
   
  } 
 
}
