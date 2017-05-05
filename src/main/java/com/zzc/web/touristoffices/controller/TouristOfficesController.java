package com.zzc.web.touristoffices.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.web.scenicmanage.controller.ScenicController;
import com.zzc.web.system.service.LogService;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.touristoffices.entity.TouristOffices;





/**
 * @Title: Controller
 * @Description: 三亚旅游局资料管理
 * @author 冯勇齐
 * @date 2016-12-25 11:50:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/touristOfficesController")
public class TouristOfficesController extends BaseController  {

	
	
	
	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ScenicController.class);
    @Autowired
	private SystemService systemService;
	
    public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
//	@Autowired
//	private TouristOfficesService touristOfficesService;
//		
//	public TouristOfficesService getTouristOfficesService() {
//		return touristOfficesService;
//	}
//	public void setTouristOfficesService(TouristOfficesService touristOfficesService) {
//		this.touristOfficesService = touristOfficesService;
//	}

	@Autowired
	private LogService logservice;
	public void setLogservice(LogService logservice) {
		this.logservice = logservice;
	}
	/**
	 * 编辑
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params="audit")
	public String touristOfficesInfo(HttpServletRequest request) {
		String touristofficesid = request.getParameter("touristofficesid");

		request.setAttribute("touristofficesid", touristofficesid);  

		//System.out.println(0);

		return "touristoffices/savetourist";
	}
	
	 
	/**
	 * 审核页面
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "audittourist")
	public ModelAndView audit(HttpServletRequest request,TouristOffices touristOffices) {
		// 获取当前登录用户ID
		String touristofficesid = ResourceUtil.getSessionUserName().getId();
		TouristOffices t = this.systemService.get(TouristOffices.class, touristofficesid);

		 request.setAttribute("touristofficesdata", t);     
		
		
		return new ModelAndView("touristoffices/savetourist");
	}
	
	
	/**
	 * 提交
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveScenic(HttpServletRequest request,
			TouristOffices touristOffices) {
		AjaxJson j = new AjaxJson();
		try {
			// 获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			touristOffices.setId(userId);
			String sql = "select need_add from t_tourist_offices t where t.id = ?";
			List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[]{touristOffices.getId()});
			// 非空则新建
			if(list.size() == 0) {
				touristOffices.setNeedAdd("1");
			}else{
				touristOffices.setNeedAdd(list.get(0).get("need_add") == null ? null : list.get(0).get("need_add").toString());
				touristOffices.setNeedEdit("1");
			}
			systemService.saveOrUpdate(touristOffices);
			systemService.addLog("填报旅游局信息成功:"+touristOffices.getId(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			message="提交信息成功";
		} catch (Exception e) {
			e.printStackTrace();
			message="提交信息失败";
			systemService.addLog("填报旅游局信息失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		               
		return j;   
	}
	
	/**
	 * 改变状态，状态值：同步
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "saveStatus",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson saveStatus(HttpServletRequest request,
			TouristOffices touristOffices) {

		AjaxJson j = new AjaxJson();
		
		
		touristOffices.setStatus("1");
		systemService.saveOrUpdate(touristOffices);   
                
		return j;   
	}
}
