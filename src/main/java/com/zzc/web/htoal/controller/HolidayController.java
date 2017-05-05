package com.zzc.web.htoal.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.htoal.entity.Holiday;
import com.zzc.web.htoal.service.Hotelservice;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
import com.zzc.webservice.ServiceInstance;
import com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult;
import com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult;
import com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo;
import com.zzc.webservice.provinceHotel.MoonFestivalInfo;
import com.zzc.webservice.provinceHotel.WebService_SanYaSoapProxy;
/**
 * 
 *                  
 * @date: 2016年11月30日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: HolidayController.java
 * @Version: 1.0
 * @About: 节假日模块信息 由管理员操作
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/holidayController")
public class HolidayController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HolidayController.class);
	private String message = null;
	private SystemService systemService;
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	private Hotelservice hotelservice;
	@Autowired
	public void setHotelservice(Hotelservice hotelservice) {
		this.hotelservice = hotelservice;
	}
	
	//节假日信息跳转页面
	@RequestMapping(params = "holiday")
	public String user(HttpServletRequest request) {
		return "holiday/holidayList";
	}
	
	/**
	 * 跳转到录入信息界面
	 * 
	 */
	//跳转到save页面
	@RequestMapping(params="addorupdate")
	public String holidaylInfo(HttpServletRequest request){
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("year", calendar.get(Calendar.YEAR));
		return "holiday/saveHoliday";
		}
	

	public boolean checkSame(Holiday holiday){
		boolean tip = false;
		String sql = "select * from t_holidaymodel t where t.year=? and t.type=?";
		List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[]{holiday.getYear(),holiday.getType()});
		if(list.size() != 0) tip = true;
		return tip;
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson add(Holiday holiday,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		if(this.checkSame(holiday)){
			j.setMsg("今年已填报该节假日的数据!");
			return j;
		}
		// TODO 定时器
//		String rs = "";
//		if("3".equals(holiday.getType())) rs = this.addDragonBoatFestival(holiday);
//		if("4".equals(holiday.getType())) rs = this.addMoonFestival(holiday);
//		if("200".equals(rs)){
//			j.setMsg(rs);
//			return j;
//		}
		holiday.setSourceStatus("0");
		//补全酒店信息
		this.systemService.saveOrUpdate(holiday);
		j.setMsg("添加成功");
		systemService.addLog("填报节假日数据成功:"+holiday.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		
		return j;
	}
	/**
	 * 展示节假日详情列表
	 */
	@ResponseBody
	@RequestMapping(params = "datagrid")
	public void datagrid(Holiday holiday,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		 CriteriaQuery cq = new CriteriaQuery(Holiday.class, dataGrid);
	        //查询条件组装器
	        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, holiday);

	        cq.add();
	        this.systemService.getDataGridReturn(cq, true);
	       
	    
	        TagUtil.datagrid(response, dataGrid);
	 
    }
	/**
	 * 
	 * @date: 2016年12月1日
	 * @Author:龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Holiday holiday, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			hotelservice.delete(holiday);
			message="删除酒店信息成功";
		} catch (Exception e) {
			message="删除酒店信息失败";
		}
		
		j.setMsg(message);
		return j;
	}
	/**
	 * 月报详情展示
	 * @date: 2016年12月20日
	 * @Author:龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:
	 */
	@RequestMapping(params = "detail")
	public String detial(HttpServletRequest request) {
		String holidayId = request.getParameter("holidayId");
		Holiday holiday = this.systemService.getEntity(Holiday.class, holidayId);
		  request.setAttribute("holiday",holiday);
		
		return "holiday/showHolidayDetail";
	}
	/**
	 * 
	 * @date: 2016年12月26日
	 * @Author:龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:节假日信息导出
	 */
	@RequestMapping(params = "exportsxls")
	public String exportsXls(Holiday holiday,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap map) {
	
	    CriteriaQuery cq = new CriteriaQuery(Holiday.class, dataGrid);
	    com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, holiday, request.getParameterMap());
	    List<Holiday> holidays = this.systemService.getListByCriteriaQuery(cq,false);
	    map.put(NormalExcelConstants.FILE_NAME,"节假日信息");
	    map.put(NormalExcelConstants.CLASS,Holiday.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("节假日列表", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,holidays);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	
		//////////////////////////////////////数据更新接口调用//////////////////////////////////////

	/**
	 * 
	 * 新增端午节接待数据
	 * @param holiday
	 * @return
	 */
	public String addDragonBoatFestival(Holiday holiday){
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		String tip = "200";
		DragonBoatFestivalInfo dragonBoatFestivalInfo = new DragonBoatFestivalInfo();
		
//		            HotelCount 酒店人数
		dragonBoatFestivalInfo.setHotelCount(new BigDecimal(holiday.getHotelNum()));
//		            HotelEarning 酒店收入
		dragonBoatFestivalInfo.setHotelEarning(new BigDecimal(holiday.getHotelIncome()));
//		            PlaceCount 景区人数
		dragonBoatFestivalInfo.setPlaceCount(new BigDecimal(holiday.getScenicNum()));
//		            PlaceEarning 景区收入
		dragonBoatFestivalInfo.setPlaceEarning(new BigDecimal(holiday.getScenicIncome()));
//		            VillageCount 乡村人数
		dragonBoatFestivalInfo.setVillageCount(new BigDecimal(holiday.getTownTravelNum()));
//		            VillageEarning 乡村收入
		dragonBoatFestivalInfo.setVillageEarning(new BigDecimal(holiday.getTownTravelIncome()));
//		ExemptionCount  免税店人数
		dragonBoatFestivalInfo.setExemptionCount(new BigDecimal(holiday.getTaxFreeNum()));
//		ExemptionEarning 免税店收入
		dragonBoatFestivalInfo.setExemptionEarning(new BigDecimal(holiday.getTaxFreeIncome()));
//		UserAccount 当前登录用户名
		String userName = ResourceUtil.getSessionUserName().getUserName();
		AddDragonBoatFestivalResponseAddDragonBoatFestivalResult rs = null;
		try {
			rs = hotelProvinceService.addDragonBoatFestival(GlobalParams.hotelProvinceCode, dragonBoatFestivalInfo, userName);
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}
	
	/**
	 * 
	 * 新增中秋节接待数据
	 * @param holiday
	 * @return
	 */
	public String addMoonFestival(Holiday holiday){
		WebService_SanYaSoapProxy hotelProvinceService = ServiceInstance.getHotelProvinceService();
		String tip = "200";
		MoonFestivalInfo moonFestivalInfo = new MoonFestivalInfo();
		
//		            HotelCount 酒店人数
		moonFestivalInfo.setHotelCount(new BigDecimal(holiday.getHotelNum()));
//		            HotelEarning 酒店收入
		moonFestivalInfo.setHotelEarning(new BigDecimal(holiday.getHotelIncome()));
//		            PlaceCount 景区人数
		moonFestivalInfo.setPlaceCount(new BigDecimal(holiday.getScenicNum()));
//		            PlaceEarning 景区收入
		moonFestivalInfo.setPlaceEarning(new BigDecimal(holiday.getScenicIncome()));
//		            VillageCount 乡村人数
		moonFestivalInfo.setVillageCount(new BigDecimal(holiday.getTownTravelNum()));
//		            VillageEarning 乡村收入
		moonFestivalInfo.setVillageEarning(new BigDecimal(holiday.getTownTravelIncome()));
//		ExemptionCount  免税店人数
		moonFestivalInfo.setExemptionCount(new BigDecimal(holiday.getTaxFreeNum()));
//		ExemptionEarning 免税店收入
		moonFestivalInfo.setExemptionEarning(new BigDecimal(holiday.getTaxFreeIncome()));
//		UserAccount 当前登录用户名
		String userName = ResourceUtil.getSessionUserName().getUserName();
		AddMoonFestivalResponseAddMoonFestivalResult rs = null;
		try {
			rs = hotelProvinceService.addMoonFestival(GlobalParams.hotelProvinceCode, moonFestivalInfo, userName);
		} catch (RemoteException e) {
			e.printStackTrace();
			tip = "通信异常!";
		}
		return tip;
	}
}
