package com.zzc.web.scenicmanage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.core.util.LogUtil;
import com.zzc.web.htoal.entity.HotelMonthly;
import com.zzc.web.htoal.entity.Hotelmanage;
import com.zzc.web.htoal.entity.HotelmanageSta;
import com.zzc.web.scenicmanage.entity.ScenicData;
import com.zzc.web.scenicmanage.entity.ScenicDataSta;
import com.zzc.web.scenicmanage.entity.ScenicSpotAnnual;
import com.zzc.web.scenicmanage.entity.ScenicSpotQuarter;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.service.SystemService;
import com.zzc.webservice.ReadJson;
@Component
public class SynchroScenicController {
	private static SystemService systemService;
	@Autowired
	public SynchroScenicController(SystemService systemService){
		SynchroScenicController.systemService = systemService;
	}
	//同步年报到国家系统
	public static void saveScenicInfo(){
		//获取所有的景区
		List<ScenicDataSta> list=systemService.getList(ScenicDataSta.class);
		// 需要同步到国家系统的集合
				List<ScenicDataSta> countryList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					ScenicDataSta scenicDataSta = list.get(i);
					// 过滤市级审核通过的数据且为A级景区的数据
					if(scenicDataSta.getStatus().equals(Status.cityAudit)){
						if(scenicDataSta.getLevel().equals("1")||scenicDataSta.getLevel().equals("2")||scenicDataSta.getLevel().equals("3")||scenicDataSta.getLevel().equals("4")||scenicDataSta.getLevel().equals("5")){
							countryList.add(scenicDataSta);
						}
					
					}
				}
				
				// 国家系统同步
				for (int i = 0; i < countryList.size(); i++) {
					ScenicDataSta scenicDatasta = countryList.get(i);
					String area=null;
					 
					if(scenicDatasta.getArea().equals("0")){
						area="460201";
					}else if(scenicDatasta.getArea().equals("1")){
						area="460205";
					}
					else if(scenicDatasta.getArea().equals("2")){
						area="460202";
					}
					else if(scenicDatasta.getArea().equals("3")){
						area="460204";
					}else if(scenicDatasta.getArea().equals("4")){
						area="460203";
					}else if(scenicDatasta.getArea().equals("5")){
						area="460200";
					}
					 if(scenicDatasta.getCode()==null||scenicDatasta.getCode().equals("")){//如果code景区编码为空  添加新景区的接口
						 String url="http://test.cnta.gov.cn/apis/add_inst";
							String param;
							try {
								param = "key=0.675221457437362&county_code="+area+"&institution[name]="+URLEncoder.encode(scenicDatasta.getName(), "UTF-8")+"&institution[address]="+URLEncoder.encode(scenicDatasta.getAddress(), "UTF-8")+"&institution[post]="+scenicDatasta.getZipcode()+"&institution[nature_id]="+scenicDatasta.getOrgproperty()+"&institution[website]="+scenicDatasta.getUrl()+"&institution[consult_phone]="+scenicDatasta.getConsultphone()+"&institution[type_id]="+scenicDatasta.getScenictype()+"&institution[establish]="+scenicDatasta.getOpentime()+"&institution[invest]="+URLEncoder.encode(scenicDatasta.getInvestunit(), "UTF-8")+"&institution[higher_authority]="+URLEncoder.encode(scenicDatasta.getSuperiorunit(), "UTF-8")+"&institution[level_id]="+scenicDatasta.getLevel()+"&institution[level_date]="+scenicDatasta.getLeveldate()+"&institution[capacity]="+scenicDatasta.getDaylimit()+"&institution[area]="+scenicDatasta.getAcreage()+"&institution[duty]="+URLEncoder.encode(scenicDatasta.getChargename(), "UTF-8")+"&institution[duty_phone]="+scenicDatasta.getChargephone()+"&institution[duty_email]="+scenicDatasta.getChargeemail()+"&institution[contact]="+URLEncoder.encode(scenicDatasta.getInformantname(), "UTF-8")+"&institution[phone]="+scenicDatasta.getInformantphone()+"&institution[email]="+scenicDatasta.getInformantemail()+"&institution[qq]="+scenicDatasta.getInformantqq()+"&institution[term_type1]="+scenicDatasta.getTermType1()+"&institution[price1]="+scenicDatasta.getPrice1()+"&institution[begin_month1]="+scenicDatasta.getBeginMonth1()+"&institution[begin_date1]="+scenicDatasta.getBeginDate1()+"&institution[end_month1]="+scenicDatasta.getEndMonth1()+"&institution[end_date1]="+scenicDatasta.getEndDate1()+"&institution[open_hour1]="+scenicDatasta.getOpenHour1()+"&institution[open_minute1]="+scenicDatasta.getOpenMinute1()+"&institution[close_hour1]="+scenicDatasta.getCloseHour1()+"&institution[close_minute1]="+scenicDatasta.getCloseMinute1()+"&institution[ticket_id]="+scenicDatasta.getTickettype()+"&institution[one_ticket]="+scenicDatasta.getTicketisOne()+"&institution[note]="+URLEncoder.encode(scenicDatasta.getRemarks(), "UTF-8")+"&institution[term_type2]="+scenicDatasta.getTermType2()+"&institution[price2]="+scenicDatasta.getPrice2()+"&institution[begin_month2]="+scenicDatasta.getBeginMonth2()+"&institution[begin_date2]="+scenicDatasta.getBeginDate2()+"&institution[end_month2]="+scenicDatasta.getBeginMonth2()+"&institution[begin_date2]="+scenicDatasta.getBeginDate2()+"&institution[end_month2]="+scenicDatasta.getEndMonth2()+"&institution[end_date2]="+scenicDatasta.getEndDate2()+"&institution[open_hour2]="+scenicDatasta.getOpenHour2()+"&institution[open_minute2]="+scenicDatasta.getOpenMinute2()+"&institution[close_hour2]="+scenicDatasta.getCloseHour2()+"&institution[close_minute2]="+scenicDatasta.getCloseMinute2()
										+"&institution[term_type3]="+scenicDatasta.getTermType3()+"&institution[price3]="+scenicDatasta.getPrice3()+"&institution[begin_month3]="+scenicDatasta.getBeginMonth3()+"&institution[begin_date3]="+scenicDatasta.getBeginDate3()+"&institution[end_month3]="+scenicDatasta.getBeginMonth3()+"&institution[begin_date3]="+scenicDatasta.getBeginDate3()+"&institution[end_month3]="+scenicDatasta.getEndMonth3()+"&institution[end_date3]="+scenicDatasta.getEndDate3()+"&institution[open_hour3]="+scenicDatasta.getOpenHour3()+"&institution[open_minute3]="+scenicDatasta.getOpenMinute3()+"&institution[close_hour3]="+scenicDatasta.getCloseHour3()+"&institution[close_minute3]="+scenicDatasta.getCloseMinute3()
										+"&institution[term_type4]="+scenicDatasta.getTermType4()+"&institution[price4]="+scenicDatasta.getPrice4()+"&institution[begin_month4]="+scenicDatasta.getBeginMonth4()+"&institution[begin_date4]="+scenicDatasta.getBeginDate4()+"&institution[end_month4]="+scenicDatasta.getBeginMonth4()+"&institution[begin_date4]="+scenicDatasta.getBeginDate4()+"&institution[end_month4]="+scenicDatasta.getEndMonth4()+"&institution[end_date4]="+scenicDatasta.getEndDate4()+"&institution[open_hour4]="+scenicDatasta.getOpenHour4()+"&institution[open_minute4]="+scenicDatasta.getOpenMinute4()+"&institution[close_hour4]="+scenicDatasta.getCloseHour4()+"&institution[close_minute4]="+scenicDatasta.getCloseMinute4()
										+"&institution[term_type5]="+scenicDatasta.getTermType5()+"&institution[price5]="+scenicDatasta.getPrice5()+"&institution[begin_month5]="+scenicDatasta.getBeginMonth5()+"&institution[begin_date5]="+scenicDatasta.getBeginDate5()+"&institution[end_month5]="+scenicDatasta.getBeginMonth5()+"&institution[begin_date5]="+scenicDatasta.getBeginDate5()+"&institution[end_month5]="+scenicDatasta.getEndMonth5()+"&institution[end_date5]="+scenicDatasta.getEndDate5()+"&institution[open_hour5]="+scenicDatasta.getOpenHour5()+"&institution[open_minute5]="+scenicDatasta.getOpenMinute5()+"&institution[close_hour5]="+scenicDatasta.getCloseHour5()+"&institution[close_minute5]="+scenicDatasta.getCloseMinute5();
								if(scenicDatasta.getScenictitle()!=null){
									String [] scenicType=scenicDatasta.getScenictitle().split(",");
									for(int j=0;j<scenicType.length;j++){
										param+="&institution[title_ids[]="+scenicType[j];
									}
								}
						
							
							
							try {
								String msg=ReadJson.sendPost(url,param);
								JSONObject node = JSONObject.fromObject(msg);
								String code=node.getString("code"); 			
								scenicDatasta.setCode(code);
								scenicDatasta.setStatus(Status.waitProAudit);
								systemService.saveOrUpdate(scenicDatasta);
								ScenicData scenicData=ScenicDataSta.copy(scenicDatasta);
								systemService.saveOrUpdate(scenicData);
							} catch (Exception e) {
								 com.zzc.core.util.LogUtil.info("添加景区"+scenicDatasta.getName()+"出错");
								e.printStackTrace();
							}
							
							} catch (UnsupportedEncodingException e1) {
								 com.zzc.core.util.LogUtil.info("参数转化出错");
								e1.printStackTrace();
							}	
					 }else{//如果不为空 更新景区的接口
							 String url="http://test.cnta.gov.cn/apis/update_inst";
								if(scenicDatasta.getArea().equals("0")){
									area="460201";
								}else if(scenicDatasta.getArea().equals("1")){
									area="460205";
								}
								else if(scenicDatasta.getArea().equals("2")){
									area="460202";
								}
								else if(scenicDatasta.getArea().equals("3")){
									area="460204";
								}else if(scenicDatasta.getArea().equals("4")){
									area="460203";
								}else if(scenicDatasta.getArea().equals("5")){
									area="460200";
								}
								String param;
								try {
									param = "key=0.675221457437362&code="+scenicDatasta.getCode()+"&county_code="+area+"&institution[name]="+URLEncoder.encode(scenicDatasta.getName(), "UTF-8")+"&institution[address]="+URLEncoder.encode(scenicDatasta.getAddress(), "UTF-8")+"&institution[post]="+scenicDatasta.getZipcode()+"&institution[nature_id]="+scenicDatasta.getOrgproperty()+"&institution[website]="+scenicDatasta.getUrl()+"&institution[consult_phone]="+scenicDatasta.getConsultphone()+"&institution[type_id]="+scenicDatasta.getScenictype()+"&institution[establish]="+scenicDatasta.getOpentime()+"&institution[invest]="+URLEncoder.encode(scenicDatasta.getInvestunit(), "UTF-8")+"&institution[higher_authority]="+URLEncoder.encode(scenicDatasta.getSuperiorunit(), "UTF-8")+"&institution[level_id]="+scenicDatasta.getLevel()+"&institution[level_date]="+scenicDatasta.getLeveldate()+"&institution[capacity]="+scenicDatasta.getDaylimit()+"&institution[area]="+scenicDatasta.getAcreage()+"&institution[duty]="+URLEncoder.encode(scenicDatasta.getChargename(), "UTF-8")+"&institution[duty_phone]="+scenicDatasta.getChargephone()+"&institution[duty_email]="+scenicDatasta.getChargeemail()+"&institution[contact]="+URLEncoder.encode(scenicDatasta.getInformantname(), "UTF-8")+"&institution[phone]="+scenicDatasta.getInformantphone()+"&institution[email]="+scenicDatasta.getInformantemail()+"&institution[qq]="+scenicDatasta.getInformantqq()+"&institution[term_type1]="+scenicDatasta.getTermType1()+"&institution[price1]="+scenicDatasta.getPrice1()+"&institution[begin_month1]="+scenicDatasta.getBeginMonth1()+"&institution[begin_date1]="+scenicDatasta.getBeginDate1()+"&institution[end_month1]="+scenicDatasta.getEndMonth1()+"&institution[end_date1]="+scenicDatasta.getEndDate1()+"&institution[open_hour1]="+scenicDatasta.getOpenHour1()+"&institution[open_minute1]="+scenicDatasta.getOpenMinute1()+"&institution[close_hour1]="+scenicDatasta.getCloseHour1()+"&institution[close_minute1]="+scenicDatasta.getCloseMinute1()+"&institution[ticket_id]="+scenicDatasta.getTickettype()+"&institution[one_ticket]="+scenicDatasta.getTicketisOne()+"&institution[note]="+URLEncoder.encode(scenicDatasta.getRemarks(), "UTF-8")+"&institution[term_type2]="+scenicDatasta.getTermType2()+"&institution[price2]="+scenicDatasta.getPrice2()+"&institution[begin_month2]="+scenicDatasta.getBeginMonth2()+"&institution[begin_date2]="+scenicDatasta.getBeginDate2()+"&institution[end_month2]="+scenicDatasta.getBeginMonth2()+"&institution[begin_date2]="+scenicDatasta.getBeginDate2()+"&institution[end_month2]="+scenicDatasta.getEndMonth2()+"&institution[end_date2]="+scenicDatasta.getEndDate2()+"&institution[open_hour2]="+scenicDatasta.getOpenHour2()+"&institution[open_minute2]="+scenicDatasta.getOpenMinute2()+"&institution[close_hour2]="+scenicDatasta.getCloseHour2()+"&institution[close_minute2]="+scenicDatasta.getCloseMinute2()
											+"&institution[term_type3]="+scenicDatasta.getTermType3()+"&institution[price3]="+scenicDatasta.getPrice3()+"&institution[begin_month3]="+scenicDatasta.getBeginMonth3()+"&institution[begin_date3]="+scenicDatasta.getBeginDate3()+"&institution[end_month3]="+scenicDatasta.getBeginMonth3()+"&institution[begin_date3]="+scenicDatasta.getBeginDate3()+"&institution[end_month3]="+scenicDatasta.getEndMonth3()+"&institution[end_date3]="+scenicDatasta.getEndDate3()+"&institution[open_hour3]="+scenicDatasta.getOpenHour3()+"&institution[open_minute3]="+scenicDatasta.getOpenMinute3()+"&institution[close_hour3]="+scenicDatasta.getCloseHour3()+"&institution[close_minute3]="+scenicDatasta.getCloseMinute3()
											+"&institution[term_type4]="+scenicDatasta.getTermType4()+"&institution[price4]="+scenicDatasta.getPrice4()+"&institution[begin_month4]="+scenicDatasta.getBeginMonth4()+"&institution[begin_date4]="+scenicDatasta.getBeginDate4()+"&institution[end_month4]="+scenicDatasta.getBeginMonth4()+"&institution[begin_date4]="+scenicDatasta.getBeginDate4()+"&institution[end_month4]="+scenicDatasta.getEndMonth4()+"&institution[end_date4]="+scenicDatasta.getEndDate4()+"&institution[open_hour4]="+scenicDatasta.getOpenHour4()+"&institution[open_minute4]="+scenicDatasta.getOpenMinute4()+"&institution[close_hour4]="+scenicDatasta.getCloseHour4()+"&institution[close_minute4]="+scenicDatasta.getCloseMinute4()
											+"&institution[term_type5]="+scenicDatasta.getTermType5()+"&institution[price5]="+scenicDatasta.getPrice5()+"&institution[begin_month5]="+scenicDatasta.getBeginMonth5()+"&institution[begin_date5]="+scenicDatasta.getBeginDate5()+"&institution[end_month5]="+scenicDatasta.getBeginMonth5()+"&institution[begin_date5]="+scenicDatasta.getBeginDate5()+"&institution[end_month5]="+scenicDatasta.getEndMonth5()+"&institution[end_date5]="+scenicDatasta.getEndDate5()+"&institution[open_hour5]="+scenicDatasta.getOpenHour5()+"&institution[open_minute5]="+scenicDatasta.getOpenMinute5()+"&institution[close_hour5]="+scenicDatasta.getCloseHour5()+"&institution[close_minute5]="+scenicDatasta.getCloseMinute5();
								
							if(scenicDatasta.getScenictitle()!=null){
								String [] scenicType=scenicDatasta.getScenictitle().split(",");
								for(int j=0;j<scenicType.length;j++){
									param+="&institution[title_ids[]="+scenicType[j];
								}
							}
								
								try {
									String msg=ReadJson.sendPost(url,param);
									JSONObject node = JSONObject.fromObject(msg);
									if(!node.equals("modification not allowed")){
										String code=node.getString("code");
										scenicDatasta.setCode(code);
										scenicDatasta.setStatus(Status.waitProAudit);
										//scenicDatasta.setProvinceSubmit("2");
										systemService.saveOrUpdate(scenicDatasta);
										ScenicData scenicData=ScenicDataSta.copy(scenicDatasta);
										systemService.saveOrUpdate(scenicData);
									}
								
								} catch (Exception e) {
									 com.zzc.core.util.LogUtil.info("更新景区"+scenicDatasta.getName()+"出错");
									e.printStackTrace();
								}
								} catch (UnsupportedEncodingException e1) {
									 com.zzc.core.util.LogUtil.info("参数转化出错");
									e1.printStackTrace();
								}	
					 }
					
				}		
				
	}

	
	//同步季报到国家系统
	public static void sendScenicQuarterly() throws UnsupportedEncodingException{
		// 获取所有季报
		List<ScenicSpotQuarter> list = systemService.getList(ScenicSpotQuarter.class);
		System.out.println("-----------开始同步季报到国家系统----------");
		System.out.println("获取所有季报:"+list.size());
		// 待提交的季报
		List<ScenicSpotQuarter> sendList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			ScenicSpotQuarter scenicSpotQuarter = list.get(i);
			// 过滤未通过审核的季报
			if(scenicSpotQuarter.getStatus().equals(Status.cityAudit)){
				sendList.add(scenicSpotQuarter);
			}
		}
		System.out.println("获取待提交的季报:"+sendList.size());
		for (int i = 0; i < sendList.size(); i++) {
			ScenicSpotQuarter scenicSpotQuarter = sendList.get(i);
			Map<String,Object> m=systemService.findOneForJdbc("SELECT t.quarter_id scenicId from t_scenicspot_quarterly t where t.id=?", scenicSpotQuarter.getId());
			String scenicId=m.get("scenicId").toString();
			List<ScenicData> sceniclist=systemService.findHql("from ScenicData s where s.id=?", scenicId);
			if(sceniclist.size()>0){
				ScenicData scenicdata=sceniclist.get(0);
			// 提交成功后改变状态
			 String url="http://test.cnta.gov.cn/apis/add_season";
			String param="key=0.675221457437362&code="+scenicdata.getCode()+"&season[year]="+scenicSpotQuarter.getYear()+"&season[quarter_id]="+scenicSpotQuarter.getQuarter()+"&season[income]="+scenicSpotQuarter.getTotalincome()+"&season[ticket_income]="+scenicSpotQuarter.getTicketincome()+"&season[tourists]="+scenicSpotQuarter.getReceptionnum()+"&season[free_tourists]="+scenicSpotQuarter.getExemptticketnum()+"&season[note]="+URLEncoder.encode(scenicSpotQuarter.getRemarks(), "UTF-8");	
			try {
				String msg=ReadJson.sendPost(url,param);
				JSONObject node = JSONObject.fromObject(msg);
				String message=node.getString("message");
				if(message.equals("season saved")){ 
					scenicSpotQuarter.setStatus(Status.waitProAudit);
					//scenicSpotQuarter.setProvinceSubmit("2");
					systemService.saveOrUpdate(scenicSpotQuarter);
					 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotQuarter.getYear()+"年第"+scenicSpotQuarter.getQuarter()+"季度季报提交成功");
				}
			}catch (Exception e) {
				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotQuarter.getYear()+"年第"+scenicSpotQuarter.getQuarter()+"季度季报提交出错");
				e.printStackTrace();
			}
	}
		}
}
	
	//同步年报到国家系统
	public static void sendScenicAnnual() throws UnsupportedEncodingException{
		// 获取所有年报
		List<ScenicSpotAnnual> list = systemService.getList(ScenicSpotAnnual.class);
		// 待提交的年报
		List<ScenicSpotAnnual> sendList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			ScenicSpotAnnual scenicSpotAnnual = list.get(i);
			// 过滤未通过审核的年报
			if(scenicSpotAnnual.getStatus().equals(Status.cityAudit)){
				sendList.add(scenicSpotAnnual);
			}
		}
		for (int i = 0; i < sendList.size(); i++) {
			ScenicSpotAnnual scenicSpotAnnual = sendList.get(i);
			Map<String,Object> m=systemService.findOneForJdbc("SELECT t.annual_id AS scenicId from t_scenicspot_annual t where t.id=?", scenicSpotAnnual.getId());
			String scenicId=m.get("scenicId").toString();
			List<ScenicData> sceniclist=systemService.findHql("from ScenicData s where s.id=?", scenicId);
			if(sceniclist.size()>0){
				ScenicData scenicdata=sceniclist.get(0);
			// 提交成功后改变状态
			String url="http://test.cnta.gov.cn/apis/add_annual";
			String param="key=0.675221457437362&code="+scenicdata.getCode()+"&annual[year]="+scenicSpotAnnual.getYear()+"&annual[wealth]="+scenicSpotAnnual.getAssetstotal()+"&annual[total]="+scenicSpotAnnual.getBuildinvest()+"&annual[construct_part]="+scenicSpotAnnual.getInbuild()+"&annual[out_part]="+scenicSpotAnnual.getOutbuild()+"&annual[source_appr]="+scenicSpotAnnual.getAppropriation()+"&annual[source_debt]="+scenicSpotAnnual.getLoan()+"&annual[source_self]="+scenicSpotAnnual.getFundsself()+"&annual[income]="+scenicSpotAnnual.getTotalincome()+"&annual[ticket_income]="+scenicSpotAnnual.getTicketincome()+"&annual[product_income]="+scenicSpotAnnual.getShopincome()+"&annual[catering_income]="+scenicSpotAnnual.getFoodincome()+"&annual[traffic_income]="+scenicSpotAnnual.getTrafficincome()+"&annual[lodging_income]="+scenicSpotAnnual.getPutUpincome()+"&annual[show_income]="+scenicSpotAnnual.getShowincome()+"&annual[other_income]="+scenicSpotAnnual.getOtherincome()+"&annual[tourists]="+scenicSpotAnnual.getReceptionnum()+"&annual[free_tourists]="+scenicSpotAnnual.getExemptTicketnum()+"&annual[total_number]="+scenicSpotAnnual.getJobnum()+"&annual[static_staff]="+scenicSpotAnnual.getFixedworker()+"&annual[part_staff]="+scenicSpotAnnual.getTempworker()+"&annual[guide]="+scenicSpotAnnual.getGuidenum()+"&annual[note]="+URLEncoder.encode(scenicSpotAnnual.getRemarks(), "UTF-8");	
			try {
				String msg=ReadJson.sendPost(url,param);
				JSONObject node = JSONObject.fromObject(msg);
				String message=node.getString("message");
				if(message.equals("annual saved")){
					scenicSpotAnnual.setStatus(Status.waitProAudit);
					//scenicSpotAnnual.setProvinceSubmit("2");
					systemService.saveOrUpdate(scenicSpotAnnual);
					 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotAnnual.getYear()+"年年报提交成功");
				}
			} catch (Exception e) {
				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotAnnual.getYear()+"年年报提交出错");
				e.printStackTrace();
			} 
	}
		}
}
	/*
	 * 同步景区年报审核状态到本系统
	 */
	public static void scenicAudit(){
       List<ScenicDataSta> list=systemService.findHql("from ScenicDataSta sd where sd.status=?", Status.waitProAudit);
       if(list.size()>0){
       	for(int i=0;i<list.size();i++){
       		 ScenicDataSta scenicData =list.get(i);
                ScenicDataSta scenicData1=ReadJson.findInstByCode(scenicData.getCode());
                if(scenicData1.getStatus().equals(Status.notPass)||scenicData1.getStatus().equals(Status.provinceAudit)||scenicData1.getStatus().equals(Status.countryAudit)){
                	String comments=ReadJson.getScenicComments(scenicData.getCode());
                	scenicData.setStatus(scenicData1.getStatus());//将status设置为获取接口的景区的status;
                	scenicData.setComments(comments);
                	systemService.saveOrUpdate(scenicData);
                	ScenicData scenicData2=ScenicDataSta.copy(scenicData);
                	systemService.saveOrUpdate(scenicData2);
   				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicData2.getCode()+"的景区年报审核状态同步成功");
                }
       	}
       	
       }
      
       
       
   }
	/**
	 * 同步年报审核状态到本系统
	 */
	public static void scenicAnnualAudit(){
        List<ScenicSpotAnnual> list=systemService.findHql("from ScenicSpotAnnual ssa where ssa.status=?",  Status.waitProAudit);
        if(list.size()>0){
        	for(int i=0;i<list.size();i++){
        		ScenicSpotAnnual scenicSpotAnnual = list.get(i);
    			Map<String,Object> m=systemService.findOneForJdbc("SELECT t.annual_id AS scenicId from t_scenicspot_annual t where t.id=?", scenicSpotAnnual.getId());
    			String scenicId=m.get("scenicId").toString();
    			List<ScenicData> sceniclist=systemService.findHql("from ScenicData s where s.id=?", scenicId);
    			if(sceniclist.size()>0){
        		ScenicData scenicdata=sceniclist.get(0);
            	ScenicSpotAnnual scenicSpotAnnual1=ReadJson.findAnnual(Integer.valueOf(scenicSpotAnnual.getYear()),scenicdata.getCode());
                 if(scenicSpotAnnual1.getStatus().equals(Status.provinceAudit)||scenicSpotAnnual1.getStatus().equals(Status.countryAudit)||scenicSpotAnnual1.getStatus().equals(Status.notPass)){
                	String comments=ReadJson.getAnnualComments(Integer.valueOf(scenicSpotAnnual.getYear()),scenicdata.getCode());
                	 scenicSpotAnnual.setStatus(scenicSpotAnnual1.getStatus());//将status设置为获取接口的景区的status;
                	 scenicSpotAnnual.setComments(comments);//获取审查意见
                	 systemService.saveOrUpdate(scenicSpotAnnual);
    				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotAnnual.getYear()+"财务年报审核状态同步成功");

                 }
        	}
        	}
        }
       
        
        
    }
	/**
	 * 同步季报审核状态到本系统
	 */
    public static void scenicQuarterAudit(){
        List<ScenicSpotQuarter> list=systemService.findHql("from ScenicSpotQuarter ssa where ssa.status=?",  Status.waitProAudit);
        if(list.size()>0){
        	for(int i=0;i<list.size();i++){
        			
        		ScenicSpotQuarter scenicSpotQuarter =list.get(i);
        		Map<String,Object> m=systemService.findOneForJdbc("SELECT t.quarter_id scenicId from t_scenicspot_quarterly t where t.id=?", scenicSpotQuarter.getId());
    			String scenicId=m.get("scenicId").toString();
    			List<ScenicData> sceniclist=systemService.findHql("from ScenicData s where s.id=?", scenicId);
    			if(sceniclist.size()>0){
    				ScenicData scenicdata=sceniclist.get(0);
            	ScenicSpotQuarter scenicSpotQuarter1=ReadJson.findSeason(scenicSpotQuarter.getYear(),scenicdata.getCode(),scenicSpotQuarter.getQuarter());
            	if(scenicSpotQuarter1.getStatus().equals(Status.notPass)||scenicSpotQuarter1.getStatus().equals(Status.provinceAudit)||scenicSpotQuarter1.getStatus().equals(Status.countryAudit)){
                    String comments=ReadJson.getQuarterComments(scenicSpotQuarter.getYear(),scenicdata.getCode(),scenicSpotQuarter.getQuarter()); 
            		scenicSpotQuarter.setStatus(scenicSpotQuarter1.getStatus());//将status设置为获取接口的景区的status;
                	 scenicSpotQuarter.setComments(comments);//设置审核意见
                	 systemService.saveOrUpdate(scenicSpotQuarter);
    				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotQuarter.getYear()+scenicSpotQuarter.getQuarter()+"季度季报审核状态同步成功");

                 }
        	}
        	}
        }
       
        
    }
    
    /*
	 * 同步景区等级发生变化的到本系统
	 */
	public static void scenicLevel(){
       List<ScenicDataSta> list=systemService.findHql("from ScenicDataSta sd where sd.level=6");
       if(list.size()>0){
       	for(int i=0;i<list.size();i++){
       			ScenicDataSta scenicData =list.get(i);
       			String code=ReadJson.findInstsByName(scenicData.getName());
                ScenicDataSta scenicData1=ReadJson.findInstByCode(code);
                if(scenicData1.getLevel()!=scenicData.getLevel()){
                	scenicData1.setId(scenicData.getId());
                	systemService.saveOrUpdate(scenicData1);
                	ScenicData scenicData2=ScenicDataSta.copy(scenicData1);
                	systemService.saveOrUpdate(scenicData2);
   				 com.zzc.core.util.LogUtil.info("景区名字为"+scenicData.getName()+"基本信息同步成功");
                }
       	}
       	
       }
      
       
       
   }
	
/**
 * 同步审核意见
 */
	public static void GetComments(){
		 List<ScenicSpotAnnual> list=systemService.findHql("from ScenicSpotAnnual ");
	        if(list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		ScenicSpotAnnual scenicSpotAnnual = list.get(i);
	    			Map<String,Object> m=systemService.findOneForJdbc("SELECT t.annual_id AS scenicId from t_scenicspot_annual t where t.id=?", scenicSpotAnnual.getId());
	    			String scenicId=m.get("scenicId").toString();
	    			List<ScenicData> sceniclist=systemService.findHql("from ScenicData s where s.id=?", scenicId);
	    			if(sceniclist.size()>0){
	        		ScenicData scenicdata=sceniclist.get(0);
	            	ScenicSpotAnnual scenicSpotAnnual1=ReadJson.findAnnual(Integer.valueOf(scenicSpotAnnual.getYear()),scenicdata.getCode());
	                 if(scenicSpotAnnual1.getStatus().equals(Status.provinceAudit)||scenicSpotAnnual1.getStatus().equals(Status.countryAudit)||scenicSpotAnnual1.getStatus().equals(Status.notPass)){
	                	 scenicSpotAnnual.setStatus(scenicSpotAnnual1.getStatus());//将status设置为获取接口的景区的status;
	                	 systemService.saveOrUpdate(scenicSpotAnnual);
	    				 com.zzc.core.util.LogUtil.info("景区编码为"+scenicdata.getCode()+"的景区"+scenicSpotAnnual.getYear()+"财务年报审核状态同步成功");

	                 	}
	    			}
	        	}
	        }
	}
}