package com.zzc.web.travel.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.util.StringUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.scenicmanage.util.ExcelExportUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.STOutQuarterDay;
import com.zzc.web.travel.entity.TravelQuarterOut;
import com.zzc.web.travel.entity.TravelQuarterOut2;


/**
 * 
 *                  
 * @date: 2017年1月9日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: StatisticTravelquarterController.java
 * @Version: 1.0
 * @About: 
 *旅行社季报统计之出境统计
 */
@Scope("prototype")
@Controller
@RequestMapping("statisticTravelquarterController")
public class StatisticTravelquarterController extends BaseController {
	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(StatisticTravelquarterController.class);
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	//返回出境旅游大洲人次统计表（按旅行社:人次）列表
	@RequestMapping(params = "toexports")
	public ModelAndView statisticTabs(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct ta.`year` AS year FROM  t_travelAgency_quarterly2 ta");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("travelstatistic/outQuarter");
	}
	//返回出境旅游大洲人次统计表（按地区:人次）列表
	@RequestMapping(params = "toOutlistByArea")
	public ModelAndView toOutlistByArea(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outlistByArea");
	}
	//返回出境旅游目的地国家人次统计表（按地区:人次）
	@RequestMapping(params = "toOutAimlistByArea")
	public ModelAndView toOutAimlistByArea(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outAimbyarea");
	}
	//返回出境旅游目的地国家人次统计表（按旅行社:人次）
	@RequestMapping(params = "toOutlistByTravel")
	public ModelAndView toOutlistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outlistByTravel");
	}
	//出境旅游目的地国家人天统计表（按地区:人天）
	@RequestMapping(params = "toOutAimlistByAreaDay")
	public ModelAndView toOutAimlistByArea2(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outAimbyarea2");
	}
	//出境旅游目的地国家人天统计表（按旅行社:人天）
	@RequestMapping(params = "toOutAimlistByTravel")
	public ModelAndView toOutAimlistByTravel2(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outAimbyTravel2");
	}
	@RequestMapping(params = "toOutAimlistPage")
	public ModelAndView toOutAimlistPage(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outAimPage");
	}@RequestMapping(params = "toOutAimlistPage1")
	public ModelAndView toOutAimlistPage1(HttpServletRequest request) {
		return new ModelAndView("travelstatistic/outAimPage1");
	}
	
	@RequestMapping(params = "leaveCollection")
	public void leaveCollection(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'出境游客', ");
		sql.append(" 	SUM(g.`人次小计`) `人次小计`, ");
		sql.append(" 	cast(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			SUM(t.out_guest_two) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	) as char) `人天小计` ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			'亚洲小计', ");
		sql.append(" 			sum(g.`人次`) `人次小计` ");
		sql.append(" 		FROM ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					'日本', ");
		sql.append(" 					sum(t.Japan_one) `人次` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'韩国', ");
		sql.append(" 						sum(t.Korea_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'蒙古', ");
		sql.append(" 							sum(t.Mongolia_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'印度尼西亚', ");
		sql.append(" 								sum(t.Indonesia_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'马来西亚', ");
		sql.append(" 									sum(t.Malaysia_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'菲律宾', ");
		sql.append(" 										sum(t.Philippines_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'新加坡', ");
		sql.append(" 											sum(t.Singapore_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'泰国', ");
		sql.append(" 												sum(t.Thailand_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'印度', ");
		sql.append(" 													sum(t.India_one) `人次` ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'越南', ");
		sql.append(" 														sum(t.Vietnam_one) `人次` ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'缅甸', ");
		sql.append(" 															sum(t.Burma_one) `人次` ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 														UNION ALL ");
		sql.append(" 															SELECT ");
		sql.append(" 																'亚洲其他', ");
		sql.append(" 																sum(t.Africa_other_one) `人次` ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			) g ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'欧洲小计', ");
		sql.append(" 				sum(g.`人次`) ");
		sql.append(" 			FROM ");
		sql.append(" 				( ");
		sql.append(" 					SELECT ");
		sql.append(" 						'英国', ");
		sql.append(" 						sum(t.english_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'法国', ");
		sql.append(" 							sum(t.France_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'德国', ");
		sql.append(" 								sum(t.Germany_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'意大利', ");
		sql.append(" 									sum(t.Italy_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'瑞士', ");
		sql.append(" 										sum(t.Switzerland_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'瑞典', ");
		sql.append(" 											sum(t.Sweden_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'俄罗斯', ");
		sql.append(" 												sum(t.Russia_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'西班牙', ");
		sql.append(" 													sum(t.Spain_one) `人次` ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'荷兰', ");
		sql.append(" 														sum(t.Holland_one) `人次` ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'丹麦', ");
		sql.append(" 															sum(t.Denmark_one) `人次` ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 														UNION ALL ");
		sql.append(" 															SELECT ");
		sql.append(" 																'欧洲其他', ");
		sql.append(" 																sum(t.Europe_other_one) `人次` ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				) g ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'美洲小计', ");
		sql.append(" 					SUM(g.`人次`) ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							'美国', ");
		sql.append(" 							sum(t.us_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'加拿大', ");
		sql.append(" 								sum(t.canada_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'美洲其他', ");
		sql.append(" 									sum(t.us_other_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					) g ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'大洋洲小计', ");
		sql.append(" 						SUM(g.`人次`) ");
		sql.append(" 					FROM ");
		sql.append(" 						( ");
		sql.append(" 							SELECT ");
		sql.append(" 								'新西兰', ");
		sql.append(" 								sum(t.Zealand_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'澳大利亚', ");
		sql.append(" 									sum(t.Australian_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'大洋洲其他', ");
		sql.append(" 										sum(t.Oceania_other_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						) g ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'非洲小计', ");
		sql.append(" 							SUM(g.`人次`) ");
		sql.append(" 						FROM ");
		sql.append(" 							( ");
		sql.append(" 								SELECT ");
		sql.append(" 									'埃及', ");
		sql.append(" 									sum(t.Egypt_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'肯尼亚', ");
		sql.append(" 										sum(t.Kenya_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'南非', ");
		sql.append(" 											sum(t.SouthAfrica_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'非洲其他', ");
		sql.append(" 												sum(t.Africa_other_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							) g ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'其他小计', ");
		sql.append(" 								sum(t.other_total_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	) g ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'亚洲小计', ");
		sql.append(" 		sum(g.`人次`) `人次小计`,'---' ");
		sql.append(" 	FROM ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				'日本', ");
		sql.append(" 				sum(t.Japan_one) `人次` ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'韩国', ");
		sql.append(" 					sum(t.Korea_one) `人次` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'蒙古', ");
		sql.append(" 						sum(t.Mongolia_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'印度尼西亚', ");
		sql.append(" 							sum(t.Indonesia_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'马来西亚', ");
		sql.append(" 								sum(t.Malaysia_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'菲律宾', ");
		sql.append(" 									sum(t.Philippines_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'新加坡', ");
		sql.append(" 										sum(t.Singapore_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'泰国', ");
		sql.append(" 											sum(t.Thailand_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'印度', ");
		sql.append(" 												sum(t.India_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'越南', ");
		sql.append(" 													sum(t.Vietnam_one) `人次` ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'缅甸', ");
		sql.append(" 														sum(t.Burma_one) `人次` ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'亚洲其他', ");
		sql.append(" 															sum(t.Africa_other_one) `人次` ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		) g ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'日本', ");
		sql.append(" 			sum(t.Japan_one),'---' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'韩国', ");
		sql.append(" 				sum(t.Korea_one),'---' ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'蒙古', ");
		sql.append(" 					sum(t.Mongolia_one),'---' ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'印度尼西亚', ");
		sql.append(" 						sum(t.Indonesia_one),'---' ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'马来西亚', ");
		sql.append(" 							sum(t.Malaysia_one),'---' ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'菲律宾', ");
		sql.append(" 								sum(t.Philippines_one),'---' ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'新加坡', ");
		sql.append(" 									sum(t.Singapore_one),'---' ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'泰国', ");
		sql.append(" 										sum(t.Thailand_one),'---' ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'印度', ");
		sql.append(" 											sum(t.India_one),'---' ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'越南', ");
		sql.append(" 												sum(t.Vietnam_one),'---' ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'缅甸', ");
		sql.append(" 													sum(t.Burma_one),'---' ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'亚洲其他', ");
		sql.append(" 														sum(t.Africa_other_one),'---' ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'欧洲小计', ");
		sql.append(" 															sum(g.`人次`),'---' ");
		sql.append(" 														FROM ");
		sql.append(" 															( ");
		sql.append(" 																SELECT ");
		sql.append(" 																	'英国', ");
		sql.append(" 																	sum(t.english_one) `人次` ");
		sql.append(" 																FROM ");
		sql.append(" 																	t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																UNION ALL ");
		sql.append(" 																	SELECT ");
		sql.append(" 																		'法国', ");
		sql.append(" 																		sum(t.France_one) `人次` ");
		sql.append(" 																	FROM ");
		sql.append(" 																		t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																	UNION ALL ");
		sql.append(" 																		SELECT ");
		sql.append(" 																			'德国', ");
		sql.append(" 																			sum(t.Germany_one) `人次` ");
		sql.append(" 																		FROM ");
		sql.append(" 																			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																		UNION ALL ");
		sql.append(" 																			SELECT ");
		sql.append(" 																				'意大利', ");
		sql.append(" 																				sum(t.Italy_one) `人次` ");
		sql.append(" 																			FROM ");
		sql.append(" 																				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																			UNION ALL ");
		sql.append(" 																				SELECT ");
		sql.append(" 																					'瑞士', ");
		sql.append(" 																					sum(t.Switzerland_one) `人次` ");
		sql.append(" 																				FROM ");
		sql.append(" 																					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																				UNION ALL ");
		sql.append(" 																					SELECT ");
		sql.append(" 																						'瑞典', ");
		sql.append(" 																						sum(t.Sweden_one) `人次` ");
		sql.append(" 																					FROM ");
		sql.append(" 																						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																					UNION ALL ");
		sql.append(" 																						SELECT ");
		sql.append(" 																							'俄罗斯', ");
		sql.append(" 																							sum(t.Russia_one) `人次` ");
		sql.append(" 																						FROM ");
		sql.append(" 																							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																						UNION ALL ");
		sql.append(" 																							SELECT ");
		sql.append(" 																								'西班牙', ");
		sql.append(" 																								sum(t.Spain_one) `人次` ");
		sql.append(" 																							FROM ");
		sql.append(" 																								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																							UNION ALL ");
		sql.append(" 																								SELECT ");
		sql.append(" 																									'荷兰', ");
		sql.append(" 																									sum(t.Holland_one) `人次` ");
		sql.append(" 																								FROM ");
		sql.append(" 																									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																								UNION ALL ");
		sql.append(" 																									SELECT ");
		sql.append(" 																										'丹麦', ");
		sql.append(" 																										sum(t.Denmark_one) `人次` ");
		sql.append(" 																									FROM ");
		sql.append(" 																										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																									UNION ALL ");
		sql.append(" 																										SELECT ");
		sql.append(" 																											'欧洲其他', ");
		sql.append(" 																											sum(t.Europe_other_one) `人次` ");
		sql.append(" 																										FROM ");
		sql.append(" 																											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 															) g ");
		sql.append(" 														UNION ALL ");
		sql.append(" 															SELECT ");
		sql.append(" 																'英国', ");
		sql.append(" 																sum(t.english_one),'---' ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 															UNION ALL ");
		sql.append(" 																SELECT ");
		sql.append(" 																	'法国', ");
		sql.append(" 																	sum(t.France_one),'---' ");
		sql.append(" 																FROM ");
		sql.append(" 																	t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																UNION ALL ");
		sql.append(" 																	SELECT ");
		sql.append(" 																		'德国', ");
		sql.append(" 																		sum(t.Germany_one),'---' ");
		sql.append(" 																	FROM ");
		sql.append(" 																		t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																	UNION ALL ");
		sql.append(" 																		SELECT ");
		sql.append(" 																			'意大利', ");
		sql.append(" 																			sum(t.Italy_one),'---' ");
		sql.append(" 																		FROM ");
		sql.append(" 																			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																		UNION ALL ");
		sql.append(" 																			SELECT ");
		sql.append(" 																				'瑞士', ");
		sql.append(" 																				sum(t.Switzerland_one),'---' ");
		sql.append(" 																			FROM ");
		sql.append(" 																				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																			UNION ALL ");
		sql.append(" 																				SELECT ");
		sql.append(" 																					'瑞典', ");
		sql.append(" 																					sum(t.Sweden_one),'---' ");
		sql.append(" 																				FROM ");
		sql.append(" 																					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																				UNION ALL ");
		sql.append(" 																					SELECT ");
		sql.append(" 																						'俄罗斯', ");
		sql.append(" 																						sum(t.Russia_one),'---' ");
		sql.append(" 																					FROM ");
		sql.append(" 																						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																					UNION ALL ");
		sql.append(" 																						SELECT ");
		sql.append(" 																							'西班牙', ");
		sql.append(" 																							sum(t.Spain_one),'---' ");
		sql.append(" 																						FROM ");
		sql.append(" 																							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																						UNION ALL ");
		sql.append(" 																							SELECT ");
		sql.append(" 																								'荷兰', ");
		sql.append(" 																								sum(t.Holland_one),'---' ");
		sql.append(" 																							FROM ");
		sql.append(" 																								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																							UNION ALL ");
		sql.append(" 																								SELECT ");
		sql.append(" 																									'丹麦', ");
		sql.append(" 																									sum(t.Denmark_one),'---' ");
		sql.append(" 																								FROM ");
		sql.append(" 																									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																								UNION ALL ");
		sql.append(" 																									SELECT ");
		sql.append(" 																										'欧洲其他', ");
		sql.append(" 																										sum(t.Europe_other_one),'---' ");
		sql.append(" 																									FROM ");
		sql.append(" 																										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																									UNION ALL ");
		sql.append(" 																										SELECT ");
		sql.append(" 																											'美洲小计', ");
		sql.append(" 																											SUM(g.`人次`),'---' ");
		sql.append(" 																										FROM ");
		sql.append(" 																											( ");
		sql.append(" 																												SELECT ");
		sql.append(" 																													'美国', ");
		sql.append(" 																													sum(t.us_one) `人次` ");
		sql.append(" 																												FROM ");
		sql.append(" 																													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																												UNION ALL ");
		sql.append(" 																													SELECT ");
		sql.append(" 																														'加拿大', ");
		sql.append(" 																														sum(t.canada_one) `人次` ");
		sql.append(" 																													FROM ");
		sql.append(" 																														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																													UNION ALL ");
		sql.append(" 																														SELECT ");
		sql.append(" 																															'美洲其他', ");
		sql.append(" 																															sum(t.us_other_one) `人次` ");
		sql.append(" 																														FROM ");
		sql.append(" 																															t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																											) g ");
		sql.append(" 																										UNION ALL ");
		sql.append(" 																											SELECT ");
		sql.append(" 																												'美国', ");
		sql.append(" 																												sum(t.us_one),'---' ");
		sql.append(" 																											FROM ");
		sql.append(" 																												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																											UNION ALL ");
		sql.append(" 																												SELECT ");
		sql.append(" 																													'加拿大', ");
		sql.append(" 																													sum(t.canada_one),'---' ");
		sql.append(" 																												FROM ");
		sql.append(" 																													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																												UNION ALL ");
		sql.append(" 																													SELECT ");
		sql.append(" 																														'美洲其他', ");
		sql.append(" 																														sum(t.us_other_one),'---' ");
		sql.append(" 																													FROM ");
		sql.append(" 																														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																													UNION ALL ");
		sql.append(" 																														SELECT ");
		sql.append(" 																															'大洋洲小计', ");
		sql.append(" 																															SUM(g.`人次`),'---' ");
		sql.append(" 																														FROM ");
		sql.append(" 																															( ");
		sql.append(" 																																SELECT ");
		sql.append(" 																																	'新西兰', ");
		sql.append(" 																																	sum(t.Zealand_one) `人次` ");
		sql.append(" 																																FROM ");
		sql.append(" 																																	t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																UNION ALL ");
		sql.append(" 																																	SELECT ");
		sql.append(" 																																		'澳大利亚', ");
		sql.append(" 																																		sum(t.Australian_one) `人次` ");
		sql.append(" 																																	FROM ");
		sql.append(" 																																		t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																	UNION ALL ");
		sql.append(" 																																		SELECT ");
		sql.append(" 																																			'大洋洲其他', ");
		sql.append(" 																																			sum(t.Oceania_other_one) `人次` ");
		sql.append(" 																																		FROM ");
		sql.append(" 																																			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																															) g ");
		sql.append(" 																														UNION ALL ");
		sql.append(" 																															SELECT ");
		sql.append(" 																																'新西兰', ");
		sql.append(" 																																sum(t.Zealand_one),'---' ");
		sql.append(" 																															FROM ");
		sql.append(" 																																t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																															UNION ALL ");
		sql.append(" 																																SELECT ");
		sql.append(" 																																	'澳大利亚', ");
		sql.append(" 																																	sum(t.Australian_one),'---' ");
		sql.append(" 																																FROM ");
		sql.append(" 																																	t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																UNION ALL ");
		sql.append(" 																																	SELECT ");
		sql.append(" 																																		'大洋洲其他', ");
		sql.append(" 																																		sum(t.Oceania_other_one),'---' ");
		sql.append(" 																																	FROM ");
		sql.append(" 																																		t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																	UNION ALL ");
		sql.append(" 																																		SELECT ");
		sql.append(" 																																			'非洲小计', ");
		sql.append(" 																																			SUM(g.`人次`),'---' ");
		sql.append(" 																																		FROM ");
		sql.append(" 																																			( ");
		sql.append(" 																																				SELECT ");
		sql.append(" 																																					'埃及', ");
		sql.append(" 																																					sum(t.Egypt_one) `人次` ");
		sql.append(" 																																				FROM ");
		sql.append(" 																																					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																				UNION ALL ");
		sql.append(" 																																					SELECT ");
		sql.append(" 																																						'肯尼亚', ");
		sql.append(" 																																						sum(t.Kenya_one) `人次` ");
		sql.append(" 																																					FROM ");
		sql.append(" 																																						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																					UNION ALL ");
		sql.append(" 																																						SELECT ");
		sql.append(" 																																							'南非', ");
		sql.append(" 																																							sum(t.SouthAfrica_one) `人次` ");
		sql.append(" 																																						FROM ");
		sql.append(" 																																							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																						UNION ALL ");
		sql.append(" 																																							SELECT ");
		sql.append(" 																																								'非洲其他', ");
		sql.append(" 																																								sum(t.Africa_other_one) `人次` ");
		sql.append(" 																																							FROM ");
		sql.append(" 																																								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																			) g ");
		sql.append(" 																																		UNION ALL ");
		sql.append(" 																																			SELECT ");
		sql.append(" 																																				'埃及', ");
		sql.append(" 																																				sum(t.Egypt_one),'---' ");
		sql.append(" 																																			FROM ");
		sql.append(" 																																				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																			UNION ALL ");
		sql.append(" 																																				SELECT ");
		sql.append(" 																																					'肯尼亚', ");
		sql.append(" 																																					sum(t.Kenya_one),'---' ");
		sql.append(" 																																				FROM ");
		sql.append(" 																																					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																				UNION ALL ");
		sql.append(" 																																					SELECT ");
		sql.append(" 																																						'南非', ");
		sql.append(" 																																						sum(t.SouthAfrica_one),'---' ");
		sql.append(" 																																					FROM ");
		sql.append(" 																																						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																					UNION ALL ");
		sql.append(" 																																						SELECT ");
		sql.append(" 																																							'非洲其他', ");
		sql.append(" 																																							sum(t.Africa_other_one),'---' ");
		sql.append(" 																																						FROM ");
		sql.append(" 																																							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 																																						UNION ALL ");
		sql.append(" 																																							SELECT ");
		sql.append(" 																																								'其他小计', ");
		sql.append(" 																																								sum(t.other_total_one) `人次`,'---' ");
		sql.append(" 																																							FROM ");
		sql.append(" 																																								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		
		ExportService emds = new ExportService(3,3);
		
		String excelName = "旅行社组织出境旅游情况汇总统计.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	}
	
	/**
	 * 
	 * 出境旅游目的地国家人次统计表（按地区）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "destinationPeopleTimesByArea")
	public void destinationPeopleTimesByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'三亚市', ");
		sql.append(" 	sum(t.hongkong_one), ");
		sql.append(" 	sum(t.macau_one), ");
		sql.append(" 	sum(t.taiwan_one), ");
		sql.append(" 	sum(t.Japan_one), ");
		sql.append(" 	sum(t.Korea_one), ");
		sql.append(" 	sum(t.Mongolia_one), ");
		sql.append(" 	sum(t.Indonesia_one), ");
		sql.append(" 	sum(t.Malaysia_one), ");
		sql.append(" 	sum(t.Philippines_one), ");
		sql.append(" 	sum(t.Singapore_one), ");
		sql.append(" 	sum(t.Thailand_one), ");
		sql.append(" 	sum(t.India_one), ");
		sql.append(" 	sum(t.Vietnam_one), ");
		sql.append(" 	sum(t.Burma_one), ");
		sql.append(" 	sum(t.Asian_other_one), ");
		sql.append(" 	sum(t.english_one), ");
		sql.append(" 	sum(t.France_one), ");
		sql.append(" 	sum(t.Germany_one), ");
		sql.append(" 	sum(t.Italy_one), ");
		sql.append(" 	sum(t.Switzerland_one), ");
		sql.append(" 	sum(t.Sweden_one), ");
		sql.append(" 	sum(t.Russia_one), ");
		sql.append(" 	sum(t.Spain_one), ");
		sql.append(" 	sum(t.Holland_one), ");
		sql.append(" 	sum(t.Denmark_one), ");
		sql.append(" 	sum(t.Europe_other_one), ");
		sql.append(" 	sum(t.us_one), ");
		sql.append(" 	sum(t.canada_one), ");
		sql.append(" 	sum(t.us_other_one), ");
		sql.append(" 	sum(t.Zealand_one), ");
		sql.append(" 	sum(t.Australian_one), ");
		sql.append(" 	sum(t.Oceania_other_one), ");
		sql.append(" 	sum(t.Egypt_one), ");
		sql.append(" 	sum(t.Kenya_one), ");
		sql.append(" 	sum(t.SouthAfrica_one), ");
		sql.append(" 	sum(t.Africa_other_one), ");
		sql.append(" 	sum(t.other_total_one) ");
		sql.append(" FROM ");

		sql.append(" 	t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");

		ExportService emds = new ExportService(2,38);
		
		String excelName = "出境旅游目的地国家人次统计表（按地区）.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
		
	}
	
	/**
	 * 
	 * 出境旅游目的地国家人次统计表（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "destinationPeopleTimesByTravelagency")
	public void destinationPeopleTimesByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	a.licensenum, ");
		sql.append(" 	a.`name`, ");
		sql.append(" 	sum(t.hongkong_one), ");
		sql.append(" 	sum(t.macau_one), ");
		sql.append(" 	sum(t.taiwan_one), ");
		sql.append(" 	sum(t.Japan_one), ");
		sql.append(" 	sum(t.Korea_one), ");
		sql.append(" 	sum(t.Mongolia_one), ");
		sql.append(" 	sum(t.Indonesia_one), ");
		sql.append(" 	sum(t.Malaysia_one), ");
		sql.append(" 	sum(t.Philippines_one), ");
		sql.append(" 	sum(t.Singapore_one), ");
		sql.append(" 	sum(t.Thailand_one), ");
		sql.append(" 	sum(t.India_one), ");
		sql.append(" 	sum(t.Vietnam_one), ");
		sql.append(" 	sum(t.Burma_one), ");
		sql.append(" 	sum(t.Asian_other_one), ");
		sql.append(" 	sum(t.english_one), ");
		sql.append(" 	sum(t.France_one), ");
		sql.append(" 	sum(t.Germany_one), ");
		sql.append(" 	sum(t.Italy_one), ");
		sql.append(" 	sum(t.Switzerland_one), ");
		sql.append(" 	sum(t.Sweden_one), ");
		sql.append(" 	sum(t.Russia_one), ");
		sql.append(" 	sum(t.Spain_one), ");
		sql.append(" 	sum(t.Holland_one), ");
		sql.append(" 	sum(t.Denmark_one), ");
		sql.append(" 	sum(t.Europe_other_one), ");
		sql.append(" 	sum(t.us_one), ");
		sql.append(" 	sum(t.canada_one), ");
		sql.append(" 	sum(t.us_other_one), ");
		sql.append(" 	sum(t.Zealand_one), ");
		sql.append(" 	sum(t.Australian_one), ");
		sql.append(" 	sum(t.Oceania_other_one), ");
		sql.append(" 	sum(t.Egypt_one), ");
		sql.append(" 	sum(t.Kenya_one), ");
		sql.append(" 	sum(t.SouthAfrica_one), ");
		sql.append(" 	sum(t.Africa_other_one), ");
		sql.append(" 	sum(t.other_total_one) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly2 t ");
		sql.append(" LEFT JOIN t_travelagency_info a ON t.tid = a.id ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" GROUP BY ");
		sql.append(" 	t.tid ");
		
		ExportService emds = new ExportService(2,39);
		
		String excelName = "出境旅游目的地国家人次统计表（按旅行社）.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
		
	}
	
	/**
	 * 
	 * 出境旅游目的地国家人天统计表（按地区）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "destinationPeopleDayByArea")
	public void destinationPeopleDayByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'三亚市', ");
		sql.append(" 	sum(t.out_guest_two) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly2 t ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		
		ExportService emds = new ExportService(2,2);
		
		String excelName = "出境旅游目的地国家人天统计表（按地区）.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
		
	}
	
	/**
	 * 
	 * 出境旅游目的地国家人天统计表（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "destinationPeopleDayByTravelagency")
	public void destinationPeopleDayByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	a.licensenum, ");
		sql.append(" 	a.`name`, ");
		sql.append(" 	sum(t.out_guest_two) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly2 t ");
		sql.append(" LEFT JOIN t_travelagency_info a ON t.tid = a.id ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" GROUP BY ");
		sql.append(" 	t.tid ");
		
		ExportService emds = new ExportService(2,3);
		
		String excelName = "出境旅游目的地国家人天统计表（按旅行社）.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
		
	}
	
	/**
	 * 
	 * 旅行社出境游各洲人次统计
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "peopleTimesByRegion")
	public void peopleTimesByRegion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'亚洲小计', ");
		sql.append(" 	sum(g.`人次`) `人次小计` ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			'日本', ");
		sql.append(" 			sum(t.Japan_one) `人次` ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'韩国', ");
		sql.append(" 				sum(t.Korea_one) `人次` ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'蒙古', ");
		sql.append(" 					sum(t.Mongolia_one) `人次` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'印度尼西亚', ");
		sql.append(" 						sum(t.Indonesia_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'马来西亚', ");
		sql.append(" 							sum(t.Malaysia_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'菲律宾', ");
		sql.append(" 								sum(t.Philippines_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'新加坡', ");
		sql.append(" 									sum(t.Singapore_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'泰国', ");
		sql.append(" 										sum(t.Thailand_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'印度', ");
		sql.append(" 											sum(t.India_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'越南', ");
		sql.append(" 												sum(t.Vietnam_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'缅甸', ");
		sql.append(" 													sum(t.Burma_one) `人次` ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'亚洲其他', ");
		sql.append(" 														sum(t.Africa_other_one) `人次` ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	) g ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'欧洲小计', ");
		sql.append(" 		sum(g.`人次`) ");
		sql.append(" 	FROM ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				'英国', ");
		sql.append(" 				sum(t.english_one) `人次` ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'法国', ");
		sql.append(" 					sum(t.France_one) `人次` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'德国', ");
		sql.append(" 						sum(t.Germany_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'意大利', ");
		sql.append(" 							sum(t.Italy_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'瑞士', ");
		sql.append(" 								sum(t.Switzerland_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'瑞典', ");
		sql.append(" 									sum(t.Sweden_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'俄罗斯', ");
		sql.append(" 										sum(t.Russia_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'西班牙', ");
		sql.append(" 											sum(t.Spain_one) `人次` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'荷兰', ");
		sql.append(" 												sum(t.Holland_one) `人次` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'丹麦', ");
		sql.append(" 													sum(t.Denmark_one) `人次` ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'欧洲其他', ");
		sql.append(" 														sum(t.Europe_other_one) `人次` ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		) g ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'美洲小计', ");
		sql.append(" 			SUM(g.`人次`) ");
		sql.append(" 		FROM ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					'美国', ");
		sql.append(" 					sum(t.us_one) `人次` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'加拿大', ");
		sql.append(" 						sum(t.canada_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'美洲其他', ");
		sql.append(" 							sum(t.us_other_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			) g ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'大洋洲小计', ");
		sql.append(" 				SUM(g.`人次`) ");
		sql.append(" 			FROM ");
		sql.append(" 				( ");
		sql.append(" 					SELECT ");
		sql.append(" 						'新西兰', ");
		sql.append(" 						sum(t.Zealand_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'澳大利亚', ");
		sql.append(" 							sum(t.Australian_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'大洋洲其他', ");
		sql.append(" 								sum(t.Oceania_other_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				) g ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'非洲小计', ");
		sql.append(" 					SUM(g.`人次`) ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							'埃及', ");
		sql.append(" 							sum(t.Egypt_one) `人次` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'肯尼亚', ");
		sql.append(" 								sum(t.Kenya_one) `人次` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'南非', ");
		sql.append(" 									sum(t.SouthAfrica_one) `人次` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'非洲其他', ");
		sql.append(" 										sum(t.Africa_other_one) `人次` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 					) g ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'其他小计', ");
		sql.append(" 						sum(t.other_total_one) `人次` ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly2 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");

		
		ExportService emds = new ExportService(2,2);
		
		String excelName = "旅行社出境游各洲人次统计.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	}
	
	/**
	 * 
	 * 出境旅游大洲人次统计表（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "peopleTimesByTravelagency")
	public void peopleTimesByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	a.licensenum, ");
		sql.append(" 	a.`name`, ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.Japan_one) + sum(t.Korea_one) + sum(t.Mongolia_one) + sum(t.Indonesia_one) + sum(t.Malaysia_one) + sum(t.Philippines_one) + sum(t.Singapore_one) + sum(t.Thailand_one) + sum(t.India_one) + sum(t.Vietnam_one) + sum(t.Burma_one) + sum(t.Africa_other_one) ");
		sql.append(" 			) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0'), ");
		sql.append(" IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.english_one) + sum(t.France_one) + sum(t.Germany_one) + sum(t.Italy_one) + sum(t.Switzerland_one) + sum(t.Sweden_one) + sum(t.Russia_one) + sum(t.Spain_one) + sum(t.Holland_one) + sum(t.Denmark_one) + sum(t.Europe_other_one) ");
		sql.append(" 			) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0'), ");
		sql.append(" IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.us_one) + sum(t.canada_one) + sum(t.us_other_one) ");
		sql.append(" 			) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0'), ");
		sql.append(" IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.Zealand_one) + sum(t.Australian_one) + sum(t.Oceania_other_one) ");
		sql.append(" 			) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0'), ");
		sql.append(" IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.Egypt_one) + sum(t.Kenya_one) + sum(t.SouthAfrica_one) + sum(t.Africa_other_one) ");
		sql.append(" 			) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0'), ");
		sql.append(" CAST(IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			(sum(t.other_total_one)) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly2 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),'0') AS CHAR) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");
		
		ExportService emds = new ExportService(2,8);
		
		String excelName = "出境旅游大洲人次统计表（按旅行社）.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.reset();
		response.setContentType("text/html;charset=UTF-8");
		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
		
	}
	
	/**
	 *
	 * @date: 2017年1月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:出境旅游大洲人次统计表（按旅行社）列表
	 */
	@RequestMapping(params = "outGridbytravel")
	public void leaveGridOfAdmin(TravelQuarterOut travelQuarterOut,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class,
				dataGrid);		
		String licensenumQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getName();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		
		
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterOut);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	//导出出境旅游大洲人次统计表（按旅行社）
	@RequestMapping(params = "exportsxls1")
	public String exportXls1(TravelQuarterOut travelQuarterOut,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap map) {
		AjaxJson j= new AjaxJson();
	    //CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class, dataGrid);
	    //com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, travelQuarterOut, request.getParameterMap());
		String hql="from com.zzc.web.travel.entity.TravelQuarterOut tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<TravelQuarterOut> travelQuarterOuts = this.systemService.findHql(hql,null);
	   
	   // this.systemService.findListbySql(query)
	   // List<TravelQuarterOut> travelQuarterOuts = this.systemService.getListByCriteriaQuery(cq, false);
	    map.put(NormalExcelConstants.FILE_NAME,"出境旅游大洲人次统计表");
	    map.put(NormalExcelConstants.CLASS,TravelQuarterOut.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("出境旅游大洲人次统计表", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,travelQuarterOuts);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	    
	}
	/**
	 * 
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return: 按地区统计出境各大洲人次
	 */
	@RequestMapping(params = "outbyarea")
	public void datagrid( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,tq.quarter as quarter,SUM(tq.asian_total_one) as sumasian,SUM(tq.Europe_one) as sumeurope,SUM(tq.Africa_one) as sumafrica,SUM(tq.America_total_one) as sumamerica,"
       +"SUM(tq.Oceania_total_one) as sumocean FROM `t_travelagency_quarterly2` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
				+ " where  1=1 ";
		if (StringUtil.isNotEmpty(getParam(request,"area"))) {
			sql += "and tr.area like '%"+getParam(request,"area")+"%'  ";
		}
		
		if (StringUtil.isNotEmpty(getParam(request,"year"))){
			sql += "and tq.`year` ="+getParam(request,"year");
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter")))
			sql += "and tq.quarter in ('"+getParam(request,"quarter")+"') ";
/*		if (StringUtil.isNotEmpty(getParam(request,"org_property")))
			sql += "and tf.org_property ='"+getParam(request,"org_property")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"scenic_type")))
			sql += "and tf.scenic_type ='"+getParam(request,"scenic_type")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"order")))
			sql += "order by "+getParam(request,"scenic_type");*/
		;
		List list = systemService.findForJdbc(sql);
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	private String getParam(HttpServletRequest request, String string) {
		return (String)request.getParameter(string);
	}
	
	
	@RequestMapping(params = "export")
	public void testexport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "测试.xlsx";
        String modelPath = "/com/zzc/web/export/model/"+excelName;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT  tr.area as area,tq.year as year,tq.quarter as quarter,SUM(tq.asian_total_one) as sumasian,SUM(tq.Europe_one) as sumeurope,SUM(tq.Africa_one) as sumafrica,SUM(tq.America_total_one) as sumamerica,"
        	       +"SUM(tq.Oceania_total_one) as sumocean FROM `t_travelagency_quarterly2` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
   				+ " where  1=1 ");
        if (StringUtil.isNotEmpty(getParam(request,"area"))) {
			sql.append("and tr.area like '%"+getParam(request,"area")+"%'  ");
		}
        ExportService emds = new ExportService(2); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        // 根据数据表,页面查询条件, 抓取数据库中对应的数据
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
        } catch (Exception e)
        {
        	e.printStackTrace();

        }
        response.reset();
        response.setContentType("text/html;charset=UTF-8");

		if (workbook != null) {
			// 针对Firefox做处理
			if ("FF".equals(ExplorerHelper.getBrowser(request))) {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(excelName.getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(excelName, "UTF-8"));
	        }
			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
	}

	
	
	
	//测试导出功能
	@RequestMapping(params = "exportssss")
	public void excelExport(HttpServletRequest request, HttpServletResponse response)
	{
		String fileName = "";
		
		try {
			//fileName = java.net.URLEncoder.encode("报名统计表", "ISO8859_1");
			fileName=new String("111".getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		response.setContentType("application/vnd.ms-excel;charset=ISO8859_1"); 
		response.setHeader("content-disposition", "attachment;filename="+fileName+".xls");  
		OutputStream fOut = null;
		try {
			fOut = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT  tr.area as area,tq.year as year,tq.quarter as quarter,SUM(tq.asian_total_one) as sumasian,SUM(tq.Europe_one) as sumeurope,SUM(tq.Africa_one) as sumafrica,SUM(tq.America_total_one) as sumamerica,"
			       +"SUM(tq.Oceania_total_one) as sumocean FROM `t_travelagency_quarterly2` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
							+ " where  1=1 ";
					if (StringUtil.isNotEmpty(getParam(request,"area"))) {
						sql += "and tr.area like '%"+getParam(request,"area")+"%'  ";
					}
					
					if (StringUtil.isNotEmpty(getParam(request,"year"))){
						sql += "and tq.`year` ="+getParam(request,"year");
					}
					if (StringUtil.isNotEmpty(getParam(request,"quarter")))
						sql += "and tq.quarter in ('"+getParam(request,"quarter")+"') ";
		List list = systemService.findForJdbc(sql);			
		Map argMap = new HashMap();
		//List<UserAccount> list = this.userAccountService.listUserAccount(orgId, orgId, 0, 1000);
		/*List<UserAccount> list = this.orgAndUserService.listUsers(
					orgId, systemId, name, 0, 1000);*/
		String[] fields = {"area","year","quarter","sumasian","sumafrica","sumeurope","sumamerica","sumocean"};
		String[] names = {"地区","年份","季度","亚洲","非洲","欧洲","美洲","大洋洲"};
		Workbook workbook;
		try {
			workbook = ExcelExportUtils.getInstance().inExcel(list, fields, names);
			workbook.write(fOut);  
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return: 按地区统计出境至各个目的地(按地区)
	 */
	@RequestMapping(params = "outAimbyarea")
	public void outAimdatagrid( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hongkong_one) as sumhongkong,SUM(tq.macau_one) as summacau,SUM(tq.taiwan_one) as sumtaiwan,SUM(tq.Japan_one) as sumjapan,"
				+"SUM(tq.Korea_one) as sumkorea,SUM(tq.Mongolia_one) as sumMongolia,SUM(tq.Indonesia_one) as sumIndonesia,SUM(tq.Malaysia_one) as sumMalaysia,"
				+"SUM(tq.Philippines_one) as sumPhilippines,SUM(tq.Singapore_one) as sumSingapore,SUM(tq.Thailand_one) as sumThailand,SUM(tq.India_one) as sumIndia,SUM(tq.Vietnam_one) as sumVietnam,"
				+"SUM(tq.Burma_one) as sumBurma,SUM(tq.Asian_other_one) as sumAsianOther,SUM(tq.english_one) as sumenglish,SUM(tq.France_one) as sumFrance,"
				+"SUM(tq.Germany_one) as sumGermany,SUM(tq.Italy_one) as sumItaly,SUM(tq.Switzerland_one) as sumSwitzerland,SUM(tq.Sweden_one) as sumSweden,"
				+"SUM(tq.Russia_one) as sumRussia,SUM(tq.Spain_one) as sumSpain,SUM(tq.Holland_one) as sumHolland,SUM(tq.Denmark_one) as sumDenmark,"
				+"SUM(tq.Europe_other_one) as sumEuropeother,SUM(tq.us_one) as sumus,SUM(tq.canada_one) as sumcanada,SUM(tq.us_other) as sumUsOther,SUM(tq.Australian_one) as sumAustralian,"
				+"SUM(tq.Zealand_one) as sumZealand,SUM(tq.Oceania_other_one) as sumOceaniaother,SUM(tq.SouthAfrica_one) as sumSouthAfrica,SUM(tq.Egypt_one) as sumEgypt,"
				+"SUM(tq.Kenya_one) as sumKenya,SUM(tq.Africa_other_one) as sumAfricaother,SUM(tq.other_total_one) as sumOthertotal "
       +"FROM `t_travelagency_quarterly2` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
				+ " where  1=1 ";
		if (StringUtil.isNotEmpty(getParam(request,"area"))) {
			sql += "and tr.area like '%"+getParam(request,"area")+"%'  ";
		}
		
		if (StringUtil.isNotEmpty(getParam(request,"year"))){
			sql += "and tq.`year` ="+getParam(request,"year");
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter")))
			sql += "and tq.quarter in ('"+getParam(request,"quarter")+"') ";
/*		if (StringUtil.isNotEmpty(getParam(request,"org_property")))
			sql += "and tf.org_property ='"+getParam(request,"org_property")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"scenic_type")))
			sql += "and tf.scenic_type ='"+getParam(request,"scenic_type")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"order")))
			sql += "order by "+getParam(request,"scenic_type");*/
		;
		List list = systemService.findForJdbc(sql);
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 
	 * @date: 2017年1月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:返回出境旅游目的地国家人次统计表（按旅行社）
	 */
	@RequestMapping(params = "outlistByTravel")
	public void outlistByTravel(TravelQuarterOut travelQuarterOut,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class,
				dataGrid);		
		String licensenumQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getName();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterOut);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
//********************************人天*********************************************	
	@RequestMapping(params = "outAimbyareaDay")
	public void outAimdatagridDay( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hongkong_two) as sumhongkong,SUM(tq.macau_two) as summacau,SUM(tq.taiwan_two) as sumtaiwan,SUM(tq.Japan_two) as sumjapan,"
				+"SUM(tq.Korea_two) as sumkorea,SUM(tq.Mongolia_two) as sumMongolia,SUM(tq.Indonesia_two) as sumIndonesia,SUM(tq.Malaysia_two) as sumMalaysia,"
				+"SUM(tq.Philippines_two) as sumPhilippines,SUM(tq.Singapore_two) as sumSingapore,SUM(tq.Thailand_two) as sumThailand,SUM(tq.India_two) as sumIndia,SUM(tq.Vietnam_two) as sumVietnam,"
				+"SUM(tq.Burma_two) as sumBurma,SUM(tq.Asian_other_two) as sumAsianOther,SUM(tq.english_two) as sumenglish,SUM(tq.France_two) as sumFrance,"
				+"SUM(tq.Germany_two) as sumGermany,SUM(tq.Italy_two) as sumItaly,SUM(tq.Switzerland_two) as sumSwitzerland,SUM(tq.Sweden_two) as sumSweden,"
				+"SUM(tq.Russia_two) as sumRussia,SUM(tq.Spain_two) as sumSpain,SUM(tq.Holland_two) as sumHolland,SUM(tq.Denmark_two) as sumDenmark,"
				+"SUM(tq.Europe_other_two) as sumEuropeother,SUM(tq.us_two) as sumus,SUM(tq.canada_two) as sumcanada,SUM(tq.us_other) as sumUsOther,SUM(tq.Australian_two) as sumAustralian,"
				+"SUM(tq.Zealand_two) as sumZealand,SUM(tq.Oceania_other_two) as sumOceaniaother,SUM(tq.SouthAfrica_two) as sumSouthAfrica,SUM(tq.Egypt_two) as sumEgypt,"
				+"SUM(tq.Kenya_two) as sumKenya,SUM(tq.Africa_other_two) as sumAfricaother,SUM(tq.other_total_two) as sumOthertotal "
       +"FROM `t_travelagency_quarterly2` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
				+ " where  1=1 ";
		if (StringUtil.isNotEmpty(getParam(request,"area"))) {
			sql += "and tr.area like '%"+getParam(request,"area")+"%'  ";
		}
		
		if (StringUtil.isNotEmpty(getParam(request,"year"))){
			sql += "and tq.`year` ="+getParam(request,"year");
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter")))
			sql += "and tq.quarter in ('"+getParam(request,"quarter")+"') ";
/*		if (StringUtil.isNotEmpty(getParam(request,"org_property")))
			sql += "and tf.org_property ='"+getParam(request,"org_property")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"scenic_type")))
			sql += "and tf.scenic_type ='"+getParam(request,"scenic_type")+"' ";
		if (StringUtil.isNotEmpty(getParam(request,"order")))
			sql += "order by "+getParam(request,"scenic_type");*/
		;
		List list = systemService.findForJdbc(sql);
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	//导出出境旅游目的地国家人次统计表(按旅行社 单位人次)
	@RequestMapping(params = "exportsxls2")
	public String exportXls2(TravelQuarterOut travelQuarterOut,HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
	    //CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class, dataGrid);
	    //com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, travelQuarterOut, request.getParameterMap());
		String hql="from com.zzc.web.travel.entity.TravelQuarterOut2 tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<TravelQuarterOut2> travelQuarterOuts = this.systemService.findHql(hql,null);
	   // this.systemService.findListbySql(query)
	   // List<TravelQuarterOut> travelQuarterOuts = this.systemService.getListByCriteriaQuery(cq, false);
	    map.put(NormalExcelConstants.FILE_NAME,"出境旅游目的地国家人次统计表(按旅行社 单位人次)");
	    map.put(NormalExcelConstants.CLASS,TravelQuarterOut2.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("出境旅游目的地国家人次统计表(按旅行社 单位人次)", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,travelQuarterOuts);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	//导出出境旅游目的地国家人次统计表(按旅行社 单位人次)
		@RequestMapping(params = "exportsxls3")
		public String exportXls3(TravelQuarterOut travelQuarterOut,HttpServletRequest request,HttpServletResponse response
				, ModelMap map) {
			
		    //CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class, dataGrid);
		    //com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, travelQuarterOut, request.getParameterMap());
			String hql="from com.zzc.web.travel.entity.STOutQuarterDay tq ";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				hql += " where tq.year="+getParam(request,"year")+"  ";
			}
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
				hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
			}
		    List<STOutQuarterDay> travelQuarterOuts = this.systemService.findHql(hql,null);
		   // this.systemService.findListbySql(query)
		   // List<TravelQuarterOut> travelQuarterOuts = this.systemService.getListByCriteriaQuery(cq, false);
		    map.put(NormalExcelConstants.FILE_NAME,"出境旅游目的地国家人次统计表(按旅行社 单位人天)");
		    map.put(NormalExcelConstants.CLASS,STOutQuarterDay.class);
		    map.put(NormalExcelConstants.PARAMS,new ExportParams("出境旅游目的地国家人次统计表(按旅行社 单位人天)", "导出人:管理员",
		            "导出信息"));
		    map.put(NormalExcelConstants.DATA_LIST,travelQuarterOuts);
		    return NormalExcelConstants.JEECG_EXCEL_VIEW;
		
		}
		
	   //导出出境旅游目的地国家人次统计表(按旅行社 单位人次)
		
		@SuppressWarnings("unchecked")
		@RequestMapping(params = "exportsxls4")
		public String exportXls4(HttpServletRequest request,HttpServletResponse response
				, ModelMap map) throws Exception {
			
			String sql="SELECT "
	+"IFNULL(SUM(asian_total_one),0) AS asianTotalOne,"
	+"IFNULL(SUM(Europe_one),0) AS europeOne,"
	+"IFNULL(SUM(Africa_one),0) AS africaOne,"
	+"IFNULL(SUM(America_total_one),0) AS americaTotalOne,"
	+"IFNULL(SUM(Australian_one+Zealand_one+Oceania_other_one),0) AS oceaniaOne,"
	+"IFNULL(SUM(out_guest_one),0) AS zonghe "

+"FROM "
	+" t_travelagency_quarterly2 tq WHERE 1=1 	" ;
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				sql += " and  tq.`year`="+getParam(request,"year")+"  ";
			}else{
				sql += " and  tq.`year` = 2014 ";
			}	
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
				sql += "and tq.`quarter`="+getParam(request,"quarter")+"  ";
			}	
			OutputStream os = response.getOutputStream();
			String filename="年第某季度出境旅游各洲人次统计.xls";
			String newFileName="年第某季度出境旅游各洲人次统计.xls";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				filename =(getParam(request,"year")+filename);
				if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
					newFileName = filename.replace("某", getParam(request,"quarter"));
					System.out.println(newFileName);
				}
			}
			
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			
			response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(newFileName,"UTF-8"));// 设置文件名
			Map<String, Object> parmass = this.systemService.findOneForJdbc(sql, null);

			
			HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  "/com/zzc/web/export/model/出境旅游各洲人次统计.xls", parmass);
			wb.write(os);
		    return null;
		
		}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportsxls5")
	public String exportXls5(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) throws Exception {
			
			String sql="SELECT "
	+"tq. YEAR AS YEAR,"
	+"IFNULL(SUM(tq.hongkong_one),0) AS sumhongkong,"
	 +"IFNULL(SUM(tq.macau_one),0) AS summacau,"
	 +"IFNULL(SUM(tq.taiwan_one),0) AS sumtaiwan,"
	 +"IFNULL(SUM(tq.Japan_one),0) AS sumjapan,"
	 +"IFNULL(SUM(tq.Korea_one),0) AS sumkorea,"
	 +"IFNULL(SUM(tq.Mongolia_one),0) AS sumMongolia,"
	 +"IFNULL(SUM(tq.Indonesia_one),0) AS sumIndonesia,"
	 +"IFNULL(SUM(tq.Malaysia_one), 0) AS sumMalaysia,"
	 +"IFNULL(SUM(tq.Philippines_one),0) AS sumPhilippines,"
	 +"IFNULL(SUM(tq.Singapore_one),0) AS sumSingapore,"
	 +"IFNULL(SUM(tq.Thailand_one),0) AS sumThailand,"
	 +"IFNULL(SUM(tq.India_one), 0) AS sumIndia,"
	 +"IFNULL(SUM(tq.Vietnam_one),0) AS sumVietnam,"
	 +"IFNULL(SUM(tq.Burma_one),0) AS sumBurma,"
	 +"IFNULL(SUM(tq.Asian_other_one),0) AS sumAsianOther,"
	 +"IFNULL(SUM(tq.english_one),0) AS sumenglish,"
	 +"IFNULL(SUM(tq.France_one),0) AS sumFrance,"
	 +"IFNULL(SUM(tq.Germany_one),0) AS sumGermany,"
	 +"IFNULL(SUM(tq.Italy_one),0) AS sumItaly,"
	 +"IFNULL(SUM(tq.Switzerland_one),0) AS sumSwitzerland,"
	 +"IFNULL(SUM(tq.Sweden_one),0) AS sumSweden,"
	 +"IFNULL(SUM(tq.Russia_one),0) AS sumRussia,"
	 +"IFNULL(SUM(tq.Spain_one),0) AS sumSpain,"
	 +"IFNULL(SUM(tq.Holland_one),0) AS sumHolland,"
	 +"IFNULL(SUM(tq.Denmark_one),0) AS sumDenmark,"
	 +"IFNULL(SUM(tq.Europe_other_one), 0) AS sumEuropeother,"
	 +"IFNULL(SUM(tq.us_one),0) AS sumus,"
	 +"IFNULL(SUM(tq.canada_one),0) AS sumcanada,"
	 +"IFNULL(SUM(tq.us_other),0) AS sumUsOther,"
	 +"IFNULL(SUM(tq.Australian_one),0) AS sumAustralian,"
	 +"IFNULL(SUM(tq.Zealand_one),0) AS sumZealand,"
	 +"IFNULL(SUM(tq.Oceania_other_one),0) AS sumOceaniaother,"
	 +"IFNULL(SUM(tq.SouthAfrica_one),0) AS sumSouthAfrica,"
	 +"IFNULL(SUM(tq.Egypt_one),0) AS sumEgypt,"
	 +"IFNULL(SUM(tq.Kenya_one),0) AS sumKenya,"
	 +"IFNULL(SUM(tq.Africa_other_one),0) AS sumAfricaother,"
	 +"IFNULL(SUM(tq.other_total_one),0) AS sumOthertotal "
	+"FROM "
	+"`t_travelagency_quarterly2` tq "
	+"WHERE "
	+"1 = 1" ;
			
			Calendar calendar = Calendar.getInstance();
			
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				sql += " and  tq.`year`="+getParam(request,"year")+"  ";
			}else{
				sql += " and  tq.`year` = '"+calendar.get(Calendar.YEAR)+"' ";
			}	
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
				sql += " and tq.`quarter`="+getParam(request,"quarter")+"  ";
			}	
			OutputStream os = response.getOutputStream();
			String filename="年第某出境旅游人次倒序排列（按目的地国家）.xls";
			String newFileName="年第某出境旅游人次倒序排列（按目的地国家）.xls";
			String fileTime = "";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
//				filename =(getParam(request,"year")+filename);
//				if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
//					newFileName = filename.replace("某", getParam(request,"quarter"));
//					System.out.println(newFileName);
//				}
				fileTime = getParam(request,"year")+"年";
			}else{
				fileTime = calendar.get(Calendar.YEAR) +"年";
			}
			if(StringUtil.isNotEmpty(getParam(request,"quarter"))){
				fileTime = fileTime + "第"+getParam(request,"quarter")+"季度";
			}
			
			newFileName = fileTime + "出境旅游人次倒序排列（按目的地国家）";
			
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			
			response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(newFileName+".xls","UTF-8"));// 设置文件名
			Map<String, Object> parmass = this.systemService.findOneForJdbc(sql, null);

			
			HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  "/com/zzc/web/export/model/出境旅游人次倒序排列（按目的地国家）.xls", parmass);
			wb.getSheetAt(0).getRow(0).getCell(0).setCellValue(newFileName);
			
			wb.write(os);
		    return null;
		
		}	
	//出境旅游人天统计（按目的地国家）
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportsxls6")
	public String exportXls6(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) throws Exception {
			
			String sql="SELECT "
	+"tq. YEAR AS YEAR,"
	+"IFNULL(SUM(tq.hongkong_two),0) AS sumhongkong,"
	 +"IFNULL(SUM(tq.macau_two),0) AS summacau,"
	 +"IFNULL(SUM(tq.taiwan_two),0) AS sumtaiwan,"
	 +"IFNULL(SUM(tq.Japan_two),0) AS sumjapan,"
	 +"IFNULL(SUM(tq.Korea_two),0) AS sumkorea,"
	 +"IFNULL(SUM(tq.Mongolia_two),0) AS sumMongolia,"
	 +"IFNULL(SUM(tq.Indonesia_two),0) AS sumIndonesia,"
	 +"IFNULL(SUM(tq.Malaysia_two), 0) AS sumMalaysia,"
	 +"IFNULL(SUM(tq.Philippines_two),0) AS sumPhilippines,"
	 +"IFNULL(SUM(tq.Singapore_two),0) AS sumSingapore,"
	 +"IFNULL(SUM(tq.Thailand_two),0) AS sumThailand,"
	 +"IFNULL(SUM(tq.India_two), 0) AS sumIndia,"
	 +"IFNULL(SUM(tq.Vietnam_two),0) AS sumVietnam,"
	 +"IFNULL(SUM(tq.Burma_two),0) AS sumBurma,"
	 +"IFNULL(SUM(tq.Asian_other_two),0) AS sumAsianOther,"
	 +"IFNULL(SUM(tq.english_two),0) AS sumenglish,"
	 +"IFNULL(SUM(tq.France_two),0) AS sumFrance,"
	 +"IFNULL(SUM(tq.Germany_two),0) AS sumGermany,"
	 +"IFNULL(SUM(tq.Italy_two),0) AS sumItaly,"
	 +"IFNULL(SUM(tq.Switzerland_two),0) AS sumSwitzerland,"
	 +"IFNULL(SUM(tq.Sweden_two),0) AS sumSweden,"
	 +"IFNULL(SUM(tq.Russia_two),0) AS sumRussia,"
	 +"IFNULL(SUM(tq.Spain_two),0) AS sumSpain,"
	 +"IFNULL(SUM(tq.Holland_two),0) AS sumHolland,"
	 +"IFNULL(SUM(tq.Denmark_two),0) AS sumDenmark,"
	 +"IFNULL(SUM(tq.Europe_other_two), 0) AS sumEuropeother,"
	 +"IFNULL(SUM(tq.us_two),0) AS sumus,"
	 +"IFNULL(SUM(tq.canada_two),0) AS sumcanada,"
	 +"IFNULL(SUM(tq.us_other),0) AS sumUsOther,"
	 +"IFNULL(SUM(tq.Australian_two),0) AS sumAustralian,"
	 +"IFNULL(SUM(tq.Zealand_two),0) AS sumZealand,"
	 +"IFNULL(SUM(tq.Oceania_other_two),0) AS sumOceaniaother,"
	 +"IFNULL(SUM(tq.SouthAfrica_two),0) AS sumSouthAfrica,"
	 +"IFNULL(SUM(tq.Egypt_two),0) AS sumEgypt,"
	 +"IFNULL(SUM(tq.Kenya_two),0) AS sumKenya,"
	 +"IFNULL(SUM(tq.Africa_other_two),0) AS sumAfricaother,"
	 +"IFNULL(SUM(tq.other_total_two),0) AS sumOthertotal "
	+"FROM "
	+"`t_travelagency_quarterly2` tq "
	+"WHERE "
	+"1 = 1" ;
			Calendar calendar = Calendar.getInstance();
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				sql += " and  tq.`year`="+getParam(request,"year")+"  ";
			}else{
				sql += " and  tq.`year` = '"+calendar.get(Calendar.YEAR)+"' ";
			}	
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
				sql += "and tq.`quarter`="+getParam(request,"quarter")+"  ";
			}	
			OutputStream os = response.getOutputStream();
			String filename="出境旅游人天统计（按目的地国家）.xls";
			String newFileName="年第某出境旅游人天统计（按目的地国家）.xls";
			String fileTime = "";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
//				filename =(getParam(request,"year")+filename);
//				if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
//					newFileName = filename.replace("某", getParam(request,"quarter"));
//					System.out.println(newFileName);
//				}
				fileTime = getParam(request,"year")+"年";
			}else{
				fileTime = calendar.get(Calendar.YEAR) +"年";
			}
			if(StringUtil.isNotEmpty(getParam(request,"quarter"))){
				fileTime = fileTime + "第"+getParam(request,"quarter")+"季度";
			}
			newFileName = fileTime+"出境旅游人天统计（按目的地国家）";
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			
			response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(newFileName+".xls","UTF-8"));// 设置文件名
			Map<String, Object> parmass = this.systemService.findOneForJdbc(sql, null);

			
			HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  "/com/zzc/web/export/model/出境旅游人次倒序排列（按目的地国家）.xls", parmass);
			wb.getSheetAt(0).getRow(0).getCell(0).setCellValue(newFileName);
			wb.write(os);
		    return null;
		
		}
	/**
	 * 
	 * @date: 2017年1月11日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:返回出境旅游目的地国家人天统计表（按旅行社）
	 */
	@RequestMapping(params = "outAimByTravel")
	public void outAimByTravel(TravelQuarterOut travelQuarterOut,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterOut.class,
				dataGrid);		
		String licensenumQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterOut.getTraveldata() == null ? "" : travelQuarterOut.getTraveldata().getName();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		
		// 查询条件组装器
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterOut);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

}
