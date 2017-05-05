package com.zzc.web.otherTravel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.ResourceUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.otherTravel.entity.OtherTravelInfo;
import com.zzc.web.sylyUtils.AutoAddUser;
import com.zzc.web.sylyUtils.GlobalParams;
import com.zzc.web.sylyUtils.Status;
import com.zzc.web.system.pojo.base.TSRoleUser;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;
/**
 * 
 *                  
 * @date: 2016年11月28日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: OtherTravelController.java
 * @Version: 1.0
 * @About:其他涉旅机构的表现层 
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/otherTravelController")
public class OtherTravelController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OtherTravelController.class);
	private String message = null;
	private SystemService systemService;
	/*private OtherTravelService otherTravelService;*/
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	/**
	 * 
	 * @date: 2016年12月23日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:跳转到添加涉旅机构页面
	 */
	@RequestMapping(params = "addUser")
	public String addUser(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		
		return "otherTravel/addOtUser";
	}
/*	@Autowired
	public void setOtherTravelService(OtherTravelService otherTravelService) {
		this.otherTravelService = otherTravelService;
	}*/
	//涉旅机构管理跳转页面
	@RequestMapping(params = "otherTravel")
	public ModelAndView role() {
		return new ModelAndView("otherTravel/otherTravelList");
	}
	//跳转到save保存页面
	@RequestMapping(params="add")
	public String otravelInfo(HttpServletRequest req){
		String otid = ResourceUtil.getSessionUserName().getId();
		OtherTravelInfo  ot=this.systemService.getEntity(OtherTravelInfo .class, otid);
		req.setAttribute("otherTravelInfo", ot);
		return "otherTravel/saveOtherTravel";
	}
	/**
	 * 
	 * @date: 2016年12月23日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:
	 */
	@RequestMapping(params="addOtUser")
	@ResponseBody
	public AjaxJson addOtUser(OtherTravelInfo otherTravelInfo,HttpServletRequest req){
		
		AjaxJson j = new AjaxJson();
		String username = otherTravelInfo.getName();
		String realname =otherTravelInfo.getName();
		String roleId = null;
		switch (otherTravelInfo.getType()) {
		case Status.golf:
			roleId=GlobalParams.GAOERFU;
			break;
		case Status.boat:
			roleId=GlobalParams.YOUTING;
			break;
		case Status.airport:
			roleId=GlobalParams.JICHANGSHANGBAOYUAN;
			break;
	/*	case Status.golf:
			roleId=GlobalParams.GAOERFU;
			break;*/
		case Status.motorcar:
			roleId=GlobalParams.DONGCHESHANGBAOYUAN;
			break;
		case Status.flight:
			roleId=GlobalParams.KONGZHONGFEIXING;
			break;
		

		default:
			break;
		}
		TSUser otuser = AutoAddUser.add(username, roleId, realname);
		String otlId = otuser.getId();
		otherTravelInfo.setStatus(Status.noSumbit);
		otherTravelInfo.setCity("三亚市");
		otherTravelInfo.setId(otlId);
		//otherTravelInfo.setHuserId(hotelId);
		systemService.save(otherTravelInfo);
		//System.out.println("涉旅用户添加成功");
		
		j.setMsg("涉旅用户:"+otuser.getUserName()+"添加成功");
		
		systemService.addLog("新增涉旅企业成功:"+otuser.getUserName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		
		return j;
	}
	
	/**
	 * 
	 * @date: 2016年11月28日
	 * @Author: 龙亚辉
	 * @Email:
	 * @param: 
	 * @return:
	 * 展示涉旅单位信息列表方法
	 */
	@ResponseBody
	@RequestMapping(params = "datagrid")
	public void datagrid(OtherTravelInfo otherTravelInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(OtherTravelInfo.class, dataGrid);
        //查询条件组装器
        com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, otherTravelInfo);      
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        //List<OtherTravelInfo> cfeList = new ArrayList<OtherTravelInfo>();
        TagUtil.datagrid(response, dataGrid);
    }
	//基本信息展示
	@RequestMapping(params = "details")
	public ModelAndView detials(HttpServletRequest request) {
		String otherTravelId = request.getParameter("otherTravelId");
		//月报id
		//String monthId = request.getParameter("monthId");
		//存到请求域中
		//request.setAttribute("otherTravelId", otherTravelId);  
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otherTravelId);
		 //hotelMonthly = this.systemService.get(HotelMonthly.class, monthId);
    
		  request.setAttribute("otherTravel",h);
		
		return new ModelAndView("otherTravel/editOtherTravel");
	}
	@RequestMapping(params = "detail")
	public ModelAndView detial(HttpServletRequest request) {
		String otherTravelId = request.getParameter("otherTravelId");
		//月报id
		//String monthId = request.getParameter("monthId");
		//存到请求域中
		//request.setAttribute("otherTravelId", otherTravelId);  
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otherTravelId);
		 //hotelMonthly = this.systemService.get(HotelMonthly.class, monthId);
    
		  request.setAttribute("otherTravel",h);
		
		return new ModelAndView("otherTravel/showOtherTravelDetail");
	}
	/**
	 * 
	 * @date: 2016年12月26日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:涉旅信息编辑
	 */
	/*@RequestMapping(params = "edit")
	public ModelAndView edit(HttpServletRequest request) {
		String otherTravelId = request.getParameter("otherTravelId");
		//月报id
		//String monthId = request.getParameter("monthId");
		//存到请求域中
		//request.setAttribute("otherTravelId", otherTravelId);  
		OtherTravelInfo h = this.systemService.get(OtherTravelInfo.class, otherTravelId);
		 //hotelMonthly = this.systemService.get(HotelMonthly.class, monthId);
    
		  request.setAttribute("otherTravel",h);
		
		return new ModelAndView("otherTravel/editOtherTravel");
	}*/
	/**
	 * 
	 * @date: 2016年12月14日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson add(OtherTravelInfo otherTravelInfo,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		//补全月报信息 由
		if(null==otherTravelInfo.getId()||otherTravelInfo.getId().length()==0){
		TSUser user = ResourceUtil.getSessionUserName();
		otherTravelInfo.setStatus(Status.noAudit);
		otherTravelInfo.setId(user.getId());
		systemService.saveOrUpdate(otherTravelInfo);
		}
	
		OtherTravelInfo aimotherTravelInfo = systemService.getEntity(OtherTravelInfo.class, otherTravelInfo.getId());
		aimotherTravelInfo.setArea(otherTravelInfo.getArea());
		aimotherTravelInfo.setCity("三亚市");
		aimotherTravelInfo.setAddress(otherTravelInfo.getAddress());
		aimotherTravelInfo.setPrincipal(otherTravelInfo.getPrincipal());
		aimotherTravelInfo.setPostalcode(otherTravelInfo.getPostalcode());
		aimotherTravelInfo.setPhone(otherTravelInfo.getPhone());
		aimotherTravelInfo.setPhone(otherTravelInfo.getMobile());
		this.systemService.saveOrUpdate(aimotherTravelInfo);
		j.setMsg("操作成功");
		systemService.addLog("填报涉旅企业信息成功:"+aimotherTravelInfo.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		return j;
	}
	//基本信息编辑
	@RequestMapping(params = "edits")
	@ResponseBody
	public AjaxJson edits(OtherTravelInfo otherTravelInfo, HttpServletRequest req) {
		String id = otherTravelInfo.getId();
		AjaxJson j = new AjaxJson();
		try {
			OtherTravelInfo aimotherTravelInfo = systemService.getEntity(OtherTravelInfo.class, id);
			aimotherTravelInfo.setArea(otherTravelInfo.getArea());
			aimotherTravelInfo.setCity("三亚市");
			aimotherTravelInfo.setAddress(otherTravelInfo.getAddress());
			aimotherTravelInfo.setPrincipal(otherTravelInfo.getPrincipal());
			aimotherTravelInfo.setPostalcode(otherTravelInfo.getPostalcode());
			aimotherTravelInfo.setPhone(otherTravelInfo.getPhone());
			this.systemService.saveOrUpdate(aimotherTravelInfo);
			systemService.addLog("填报涉旅企业信息成功:"+aimotherTravelInfo.getName(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			message="编辑成功";
		} catch (Exception e) {
			message="编辑失败";
			systemService.addLog("填报涉旅企业信息失败:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		}
		
		j.setMsg(message);
		return j;
	}
	//删除
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(OtherTravelInfo otherTravelInfo, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			otherTravelInfo = systemService.getEntity(OtherTravelInfo.class, id);
			//删除对应的用户
			TSUser user = systemService.getEntity(TSUser.class, id);
			List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			systemService.delete(otherTravelInfo);
			//删除对应的角色
			delRoleUser(user);
			systemService.executeSql("delete from t_s_user_org where user_id=?", user.getId()); // 删除 用户-机构 数据
			systemService.delete(user);
			message="涉旅机构删除成功";
			systemService.addLog("删除涉旅企业信息成功:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			message="涉旅机构删除失败";
			systemService.addLog("删除涉旅企业信息失败:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		return j;
	}
	public void delRoleUser(TSUser user) {
		// 同步删除用户角色关联表
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
	} 
	
	

}
