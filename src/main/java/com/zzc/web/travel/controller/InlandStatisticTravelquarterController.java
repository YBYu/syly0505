package com.zzc.web.travel.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.util.StringUtil;
import com.zzc.poi.excel.entity.ExportParams;
import com.zzc.poi.excel.entity.vo.NormalExcelConstants;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.travel.entity.STQuarterInlandOrg;
import com.zzc.web.travel.entity.STQuarterInlandOrgDay;
import com.zzc.web.travel.entity.STQuarterInlandRec;
import com.zzc.web.travel.entity.STQuarterInlandRecDay;
import com.zzc.web.travel.entity.TravelQuarterInland;

/**
 * 
 *                  
 * @date: 2017年1月9日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: StatisticTravelquarterController.java
 * @Version: 1.0
 * @About: 
 *旅行社季报统计之国内统计
 */
@Scope("prototype")
@Controller
@RequestMapping("inlandStatisticTravelquarterController")
public class InlandStatisticTravelquarterController extends BaseController {
	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(InStatisticTravelquarterController.class);
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	//统计下载页面
	@RequestMapping(params = "toexports")
	public ModelAndView toexports(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct ta.`year` AS year FROM  t_travelAgency_quarterly3 ta");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("travelstatisticOfInand/inlandQuarter");
	}
	
	
	
	//组织人天和接待人天之和统计（按地区）（报送）
	@RequestMapping(params = "toInlandByAreaZuzhiJieDaiDay")
	public ModelAndView toInAimlist(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/innlandByAreaZuzhiJieDaiDay");
	}
	
	//旅行社国内游接待人次情况表(按旅行社 接待人次)
	@RequestMapping(params = "toJiedaiByTravel")
	public ModelAndView toInAimlistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/inlandJiedaiByTravel");
	}
	//旅行社国内游接待人天情况表(按旅行社 接待人天)
	@RequestMapping(params = "toJiedaiByTravelDay")
	public ModelAndView toInAimlistByTravelDay(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/inlandJiedaiByTravelDay");
	}
	//旅行社国内游组织人次情况表(按旅行社 组织人次) 再试一次
	@RequestMapping(params = "toZuzhiByTravel")
	public ModelAndView toZuzhilistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/inlandZuzhiByTravel");
	}
	//旅行社国内游组织人天情况表(按旅行社 组织人天)
	@RequestMapping(params = "toZuzhiByTravelDay")
	public ModelAndView toZuzhilistByTravelDay(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/inlandZuzhiByTravelDay");
	}
	//组织人次和接待人次之和(按旅行社 人次)（报送）
	@RequestMapping(params = "toInlandBytravelZuzhiJieDai")
	public ModelAndView toAllList(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/innlandBytravelZuzhiJieDai");
	}
	//组织人次和接待人次之和(按旅行社 人天)（报送）
	@RequestMapping(params = "toInlandBytravelZuzhiJieDaiDay")
	public ModelAndView toAllListDay(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfInand/innlandBytravelZuzhiJieDaiDay");
	}
	
	/**
	 * 
	 * 组织接待国内旅游情况一览表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandCollection")
	public void inlandCollection( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select '合计',sum(g.`组织人次`),sum(g.`接待人次`), sum(g.`组织人天`),sum(g.`接待人天`) from (SELECT ");
		sql.append(" 	'北京', ");
		sql.append(" 	sum(t.bj_one) '组织人次', ");
		sql.append(" 	sum(t.bj_two) '接待人次', ");
		sql.append(" 	sum(t.bj_three) '组织人天', ");
		sql.append(" 	sum(t.bj_four) '接待人天' ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'天津', ");
		sql.append(" 		sum(t.tj_one) '组织人次', ");
		sql.append(" 		sum(t.tj_two) '接待人次', ");
		sql.append(" 		sum(t.tj_three) '组织人天', ");
		sql.append(" 		sum(t.tj_four) '接待人天' ");
		sql.append(" 	FROM ");
		sql.append(" 		t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'河北', ");
		sql.append(" 			sum(t.hb_one) '组织人次', ");
		sql.append(" 			sum(t.hb_two) '接待人次', ");
		sql.append(" 			sum(t.hb_three) '组织人天', ");
		sql.append(" 			sum(t.hb_four) '接待人天' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'山西', ");
		sql.append(" 				sum(t.sx_one) '组织人次', ");
		sql.append(" 				sum(t.sx_two) '接待人次', ");
		sql.append(" 				sum(t.sx_three) '组织人天', ");
		sql.append(" 				sum(t.sx_four) '接待人天' ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'内蒙古', ");
		sql.append(" 					sum(t.nmg_one) '组织人次', ");
		sql.append(" 					sum(t.nmg_two) '接待人次', ");
		sql.append(" 					sum(t.nmg_three) '组织人天', ");
		sql.append(" 					sum(t.nmg_four) '接待人天' ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'辽宁', ");
		sql.append(" 						sum(t.ln_one) '组织人次', ");
		sql.append(" 						sum(t.ln_two) '接待人次', ");
		sql.append(" 						sum(t.ln_three) '组织人天', ");
		sql.append(" 						sum(t.ln_four) '接待人天' ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'吉林', ");
		sql.append(" 							sum(t.jl_one) '组织人次', ");
		sql.append(" 							sum(t.jl_two) '接待人次', ");
		sql.append(" 							sum(t.jl_three) '组织人天', ");
		sql.append(" 							sum(t.jl_four) '接待人天' ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'黑龙江', ");
		sql.append(" 								sum(t.hlj_one) '组织人次', ");
		sql.append(" 								sum(t.hlj_two) '接待人次', ");
		sql.append(" 								sum(t.hlj_three) '组织人天', ");
		sql.append(" 								sum(t.hlj_four) '接待人天' ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'上海', ");
		sql.append(" 									sum(t.sh_one) '组织人次', ");
		sql.append(" 									sum(t.sh_two) '接待人次', ");
		sql.append(" 									sum(t.sh_three) '组织人天', ");
		sql.append(" 									sum(t.sh_four) '接待人天' ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'江苏', ");
		sql.append(" 										sum(t.js_one) '组织人次', ");
		sql.append(" 										sum(t.js_two) '接待人次', ");
		sql.append(" 										sum(t.js_three) '组织人天', ");
		sql.append(" 										sum(t.js_four) '接待人天' ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'浙江', ");
		sql.append(" 											sum(t.zj_one) '组织人次', ");
		sql.append(" 											sum(t.zj_two) '接待人次', ");
		sql.append(" 											sum(t.zj_three) '组织人天', ");
		sql.append(" 											sum(t.zj_four) '接待人天' ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'安徽', ");
		sql.append(" 												sum(t.ah_one) '组织人次', ");
		sql.append(" 												sum(t.ah_two) '接待人次', ");
		sql.append(" 												sum(t.ah_three) '组织人天', ");
		sql.append(" 												sum(t.ah_four) '接待人天' ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'福建', ");
		sql.append(" 													sum(t.fj_one) '组织人次', ");
		sql.append(" 													sum(t.fj_two) '接待人次', ");
		sql.append(" 													sum(t.fj_three) '组织人天', ");
		sql.append(" 													sum(t.fj_four) '接待人天' ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'江西', ");
		sql.append(" 														sum(t.jx_one) '组织人次', ");
		sql.append(" 														sum(t.jx_two) '接待人次', ");
		sql.append(" 														sum(t.jx_three) '组织人天', ");
		sql.append(" 														sum(t.jx_four) '接待人天' ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'山东', ");
		sql.append(" 															sum(t.sd_one) '组织人次', ");
		sql.append(" 															sum(t.sd_two) '接待人次', ");
		sql.append(" 															sum(t.sd_three) '组织人天', ");
		sql.append(" 															sum(t.sd_four) '接待人天' ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 														UNION ALL ");
		sql.append(" 															SELECT ");
		sql.append(" 																'河南', ");
		sql.append(" 																sum(t.henan_one) '组织人次', ");
		sql.append(" 																sum(t.henan_two) '接待人次', ");
		sql.append(" 																sum(t.henan_three) '组织人天', ");
		sql.append(" 																sum(t.henan_four) '接待人天' ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 															UNION ALL ");
		sql.append(" 																SELECT ");
		sql.append(" 																	'湖北', ");
		sql.append(" 																	sum(t.hubei_one) '组织人次', ");
		sql.append(" 																	sum(t.hubei_two) '接待人次', ");
		sql.append(" 																	sum(t.hubei_three) '组织人天', ");
		sql.append(" 																	sum(t.hubei_four) '接待人天' ");
		sql.append(" 																FROM ");
		sql.append(" 																	t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																UNION ALL ");
		sql.append(" 																	SELECT ");
		sql.append(" 																		'湖南', ");
		sql.append(" 																		sum(t.hl_one) '组织人次', ");
		sql.append(" 																		sum(t.hl_two) '接待人次', ");
		sql.append(" 																		sum(t.hl_three) '组织人天', ");
		sql.append(" 																		sum(t.hl_four) '接待人天' ");
		sql.append(" 																	FROM ");
		sql.append(" 																		t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																	UNION ALL ");
		sql.append(" 																		SELECT ");
		sql.append(" 																			'广东', ");
		sql.append(" 																			sum(t.gd_one) '组织人次', ");
		sql.append(" 																			sum(t.gd_two) '接待人次', ");
		sql.append(" 																			sum(t.gd_three) '组织人天', ");
		sql.append(" 																			sum(t.gd_four) '接待人天' ");
		sql.append(" 																		FROM ");
		sql.append(" 																			t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																		UNION ALL ");
		sql.append(" 																			SELECT ");
		sql.append(" 																				'广西', ");
		sql.append(" 																				sum(t.gx_one) '组织人次', ");
		sql.append(" 																				sum(t.gx_two) '接待人次', ");
		sql.append(" 																				sum(t.gx_three) '组织人天', ");
		sql.append(" 																				sum(t.gx_four) '接待人天' ");
		sql.append(" 																			FROM ");
		sql.append(" 																				t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																			UNION ALL ");
		sql.append(" 																				SELECT ");
		sql.append(" 																					'海南', ");
		sql.append(" 																					sum(t.hainan_one) '组织人次', ");
		sql.append(" 																					sum(t.hainan_two) '接待人次', ");
		sql.append(" 																					sum(t.hainan_three) '组织人天', ");
		sql.append(" 																					sum(t.hainan_four) '接待人天' ");
		sql.append(" 																				FROM ");
		sql.append(" 																					t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																				UNION ALL ");
		sql.append(" 																					SELECT ");
		sql.append(" 																						'重庆', ");
		sql.append(" 																						sum(t.cq_one) '组织人次', ");
		sql.append(" 																						sum(t.cq_two) '接待人次', ");
		sql.append(" 																						sum(t.cq_three) '组织人天', ");
		sql.append(" 																						sum(t.cq_four) '接待人天' ");
		sql.append(" 																					FROM ");
		sql.append(" 																						t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																					UNION ALL ");
		sql.append(" 																						SELECT ");
		sql.append(" 																							'四川', ");
		sql.append(" 																							sum(t.sc_one) '组织人次', ");
		sql.append(" 																							sum(t.sc_two) '接待人次', ");
		sql.append(" 																							sum(t.sc_three) '组织人天', ");
		sql.append(" 																							sum(t.sc_four) '接待人天' ");
		sql.append(" 																						FROM ");
		sql.append(" 																							t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																						UNION ALL ");
		sql.append(" 																							SELECT ");
		sql.append(" 																								'贵州', ");
		sql.append(" 																								sum(t.gz_one) '组织人次', ");
		sql.append(" 																								sum(t.gz_two) '接待人次', ");
		sql.append(" 																								sum(t.gz_three) '组织人天', ");
		sql.append(" 																								sum(t.gz_four) '接待人天' ");
		sql.append(" 																							FROM ");
		sql.append(" 																								t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																							UNION ALL ");
		sql.append(" 																								SELECT ");
		sql.append(" 																									'云南', ");
		sql.append(" 																									sum(t.yn_one) '组织人次', ");
		sql.append(" 																									sum(t.yn_two) '接待人次', ");
		sql.append(" 																									sum(t.yn_three) '组织人天', ");
		sql.append(" 																									sum(t.yn_four) '接待人天' ");
		sql.append(" 																								FROM ");
		sql.append(" 																									t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																								UNION ALL ");
		sql.append(" 																									SELECT ");
		sql.append(" 																										'西藏', ");
		sql.append(" 																										sum(t.xz_one) '组织人次', ");
		sql.append(" 																										sum(t.xz_two) '接待人次', ");
		sql.append(" 																										sum(t.xz_three) '组织人天', ");
		sql.append(" 																										sum(t.xz_four) '接待人天' ");
		sql.append(" 																									FROM ");
		sql.append(" 																										t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																									UNION ALL ");
		sql.append(" 																										SELECT ");
		sql.append(" 																											'陕西', ");
		sql.append(" 																											sum(t.shanxi_one) '组织人次', ");
		sql.append(" 																											sum(t.shanxi_two) '接待人次', ");
		sql.append(" 																											sum(t.shanxi_three) '组织人天', ");
		sql.append(" 																											sum(t.shanxi_four) '接待人天' ");
		sql.append(" 																										FROM ");
		sql.append(" 																											t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																										UNION ALL ");
		sql.append(" 																											SELECT ");
		sql.append(" 																												'甘肃', ");
		sql.append(" 																												sum(t.gansu_one) '组织人次', ");
		sql.append(" 																												sum(t.gansu_two) '接待人次', ");
		sql.append(" 																												sum(t.gansu_three) '组织人天', ");
		sql.append(" 																												sum(t.gansu_four) '接待人天' ");
		sql.append(" 																											FROM ");
		sql.append(" 																												t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																											UNION ALL ");
		sql.append(" 																												SELECT ");
		sql.append(" 																													'青海', ");
		sql.append(" 																													sum(t.qinghai_one) '组织人次', ");
		sql.append(" 																													sum(t.qinghai_two) '接待人次', ");
		sql.append(" 																													sum(t.qinghai_three) '组织人天', ");
		sql.append(" 																													sum(t.qinghai_four) '接待人天' ");
		sql.append(" 																												FROM ");
		sql.append(" 																													t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																												UNION ALL ");
		sql.append(" 																													SELECT ");
		sql.append(" 																														'宁夏', ");
		sql.append(" 																														sum(t.nx_one) '组织人次', ");
		sql.append(" 																														sum(t.nx_two) '接待人次', ");
		sql.append(" 																														sum(t.nx_three) '组织人天', ");
		sql.append(" 																														sum(t.nx_four) '接待人天' ");
		sql.append(" 																													FROM ");
		sql.append(" 																														t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																													UNION ALL ");
		sql.append(" 																														SELECT ");
		sql.append(" 																															'新疆', ");
		sql.append(" 																															sum(t.xj_one) '组织人次', ");
		sql.append(" 																															sum(t.xj_two) '接待人次', ");
		sql.append(" 																															sum(t.xj_three) '组织人天', ");
		sql.append(" 																															sum(t.xj_four) '接待人天' ");
		sql.append(" 																														FROM ");
		sql.append(" 																															t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) g union ALL ");
		sql.append(" SELECT ");
		sql.append(" 	'北京', ");
		sql.append(" 	sum(t.bj_one) '组织人次', ");
		sql.append(" 	sum(t.bj_two) '接待人次', ");
		sql.append(" 	sum(t.bj_three) '组织人天', ");
		sql.append(" 	sum(t.bj_four) '接待人天' ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'天津', ");
		sql.append(" 		sum(t.tj_one) '组织人次', ");
		sql.append(" 		sum(t.tj_two) '接待人次', ");
		sql.append(" 		sum(t.tj_three) '组织人天', ");
		sql.append(" 		sum(t.tj_four) '接待人天' ");
		sql.append(" 	FROM ");
		sql.append(" 		t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'河北', ");
		sql.append(" 			sum(t.hb_one) '组织人次', ");
		sql.append(" 			sum(t.hb_two) '接待人次', ");
		sql.append(" 			sum(t.hb_three) '组织人天', ");
		sql.append(" 			sum(t.hb_four) '接待人天' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'山西', ");
		sql.append(" 				sum(t.sx_one) '组织人次', ");
		sql.append(" 				sum(t.sx_two) '接待人次', ");
		sql.append(" 				sum(t.sx_three) '组织人天', ");
		sql.append(" 				sum(t.sx_four) '接待人天' ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'内蒙古', ");
		sql.append(" 					sum(t.nmg_one) '组织人次', ");
		sql.append(" 					sum(t.nmg_two) '接待人次', ");
		sql.append(" 					sum(t.nmg_three) '组织人天', ");
		sql.append(" 					sum(t.nmg_four) '接待人天' ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'辽宁', ");
		sql.append(" 						sum(t.ln_one) '组织人次', ");
		sql.append(" 						sum(t.ln_two) '接待人次', ");
		sql.append(" 						sum(t.ln_three) '组织人天', ");
		sql.append(" 						sum(t.ln_four) '接待人天' ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 					UNION ALL ");
		sql.append(" 						SELECT ");
		sql.append(" 							'吉林', ");
		sql.append(" 							sum(t.jl_one) '组织人次', ");
		sql.append(" 							sum(t.jl_two) '接待人次', ");
		sql.append(" 							sum(t.jl_three) '组织人天', ");
		sql.append(" 							sum(t.jl_four) '接待人天' ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 						UNION ALL ");
		sql.append(" 							SELECT ");
		sql.append(" 								'黑龙江', ");
		sql.append(" 								sum(t.hlj_one) '组织人次', ");
		sql.append(" 								sum(t.hlj_two) '接待人次', ");
		sql.append(" 								sum(t.hlj_three) '组织人天', ");
		sql.append(" 								sum(t.hlj_four) '接待人天' ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									'上海', ");
		sql.append(" 									sum(t.sh_one) '组织人次', ");
		sql.append(" 									sum(t.sh_two) '接待人次', ");
		sql.append(" 									sum(t.sh_three) '组织人天', ");
		sql.append(" 									sum(t.sh_four) '接待人天' ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										'江苏', ");
		sql.append(" 										sum(t.js_one) '组织人次', ");
		sql.append(" 										sum(t.js_two) '接待人次', ");
		sql.append(" 										sum(t.js_three) '组织人天', ");
		sql.append(" 										sum(t.js_four) '接待人天' ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											'浙江', ");
		sql.append(" 											sum(t.zj_one) '组织人次', ");
		sql.append(" 											sum(t.zj_two) '接待人次', ");
		sql.append(" 											sum(t.zj_three) '组织人天', ");
		sql.append(" 											sum(t.zj_four) '接待人天' ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												'安徽', ");
		sql.append(" 												sum(t.ah_one) '组织人次', ");
		sql.append(" 												sum(t.ah_two) '接待人次', ");
		sql.append(" 												sum(t.ah_three) '组织人天', ");
		sql.append(" 												sum(t.ah_four) '接待人天' ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 											UNION ALL ");
		sql.append(" 												SELECT ");
		sql.append(" 													'福建', ");
		sql.append(" 													sum(t.fj_one) '组织人次', ");
		sql.append(" 													sum(t.fj_two) '接待人次', ");
		sql.append(" 													sum(t.fj_three) '组织人天', ");
		sql.append(" 													sum(t.fj_four) '接待人天' ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 												UNION ALL ");
		sql.append(" 													SELECT ");
		sql.append(" 														'江西', ");
		sql.append(" 														sum(t.jx_one) '组织人次', ");
		sql.append(" 														sum(t.jx_two) '接待人次', ");
		sql.append(" 														sum(t.jx_three) '组织人天', ");
		sql.append(" 														sum(t.jx_four) '接待人天' ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 													UNION ALL ");
		sql.append(" 														SELECT ");
		sql.append(" 															'山东', ");
		sql.append(" 															sum(t.sd_one) '组织人次', ");
		sql.append(" 															sum(t.sd_two) '接待人次', ");
		sql.append(" 															sum(t.sd_three) '组织人天', ");
		sql.append(" 															sum(t.sd_four) '接待人天' ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 														UNION ALL ");
		sql.append(" 															SELECT ");
		sql.append(" 																'河南', ");
		sql.append(" 																sum(t.henan_one) '组织人次', ");
		sql.append(" 																sum(t.henan_two) '接待人次', ");
		sql.append(" 																sum(t.henan_three) '组织人天', ");
		sql.append(" 																sum(t.henan_four) '接待人天' ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 															UNION ALL ");
		sql.append(" 																SELECT ");
		sql.append(" 																	'湖北', ");
		sql.append(" 																	sum(t.hubei_one) '组织人次', ");
		sql.append(" 																	sum(t.hubei_two) '接待人次', ");
		sql.append(" 																	sum(t.hubei_three) '组织人天', ");
		sql.append(" 																	sum(t.hubei_four) '接待人天' ");
		sql.append(" 																FROM ");
		sql.append(" 																	t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																UNION ALL ");
		sql.append(" 																	SELECT ");
		sql.append(" 																		'湖南', ");
		sql.append(" 																		sum(t.hl_one) '组织人次', ");
		sql.append(" 																		sum(t.hl_two) '接待人次', ");
		sql.append(" 																		sum(t.hl_three) '组织人天', ");
		sql.append(" 																		sum(t.hl_four) '接待人天' ");
		sql.append(" 																	FROM ");
		sql.append(" 																		t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																	UNION ALL ");
		sql.append(" 																		SELECT ");
		sql.append(" 																			'广东', ");
		sql.append(" 																			sum(t.gd_one) '组织人次', ");
		sql.append(" 																			sum(t.gd_two) '接待人次', ");
		sql.append(" 																			sum(t.gd_three) '组织人天', ");
		sql.append(" 																			sum(t.gd_four) '接待人天' ");
		sql.append(" 																		FROM ");
		sql.append(" 																			t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																		UNION ALL ");
		sql.append(" 																			SELECT ");
		sql.append(" 																				'广西', ");
		sql.append(" 																				sum(t.gx_one) '组织人次', ");
		sql.append(" 																				sum(t.gx_two) '接待人次', ");
		sql.append(" 																				sum(t.gx_three) '组织人天', ");
		sql.append(" 																				sum(t.gx_four) '接待人天' ");
		sql.append(" 																			FROM ");
		sql.append(" 																				t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																			UNION ALL ");
		sql.append(" 																				SELECT ");
		sql.append(" 																					'海南', ");
		sql.append(" 																					sum(t.hainan_one) '组织人次', ");
		sql.append(" 																					sum(t.hainan_two) '接待人次', ");
		sql.append(" 																					sum(t.hainan_three) '组织人天', ");
		sql.append(" 																					sum(t.hainan_four) '接待人天' ");
		sql.append(" 																				FROM ");
		sql.append(" 																					t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																				UNION ALL ");
		sql.append(" 																					SELECT ");
		sql.append(" 																						'重庆', ");
		sql.append(" 																						sum(t.cq_one) '组织人次', ");
		sql.append(" 																						sum(t.cq_two) '接待人次', ");
		sql.append(" 																						sum(t.cq_three) '组织人天', ");
		sql.append(" 																						sum(t.cq_four) '接待人天' ");
		sql.append(" 																					FROM ");
		sql.append(" 																						t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																					UNION ALL ");
		sql.append(" 																						SELECT ");
		sql.append(" 																							'四川', ");
		sql.append(" 																							sum(t.sc_one) '组织人次', ");
		sql.append(" 																							sum(t.sc_two) '接待人次', ");
		sql.append(" 																							sum(t.sc_three) '组织人天', ");
		sql.append(" 																							sum(t.sc_four) '接待人天' ");
		sql.append(" 																						FROM ");
		sql.append(" 																							t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																						UNION ALL ");
		sql.append(" 																							SELECT ");
		sql.append(" 																								'贵州', ");
		sql.append(" 																								sum(t.gz_one) '组织人次', ");
		sql.append(" 																								sum(t.gz_two) '接待人次', ");
		sql.append(" 																								sum(t.gz_three) '组织人天', ");
		sql.append(" 																								sum(t.gz_four) '接待人天' ");
		sql.append(" 																							FROM ");
		sql.append(" 																								t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																							UNION ALL ");
		sql.append(" 																								SELECT ");
		sql.append(" 																									'云南', ");
		sql.append(" 																									sum(t.yn_one) '组织人次', ");
		sql.append(" 																									sum(t.yn_two) '接待人次', ");
		sql.append(" 																									sum(t.yn_three) '组织人天', ");
		sql.append(" 																									sum(t.yn_four) '接待人天' ");
		sql.append(" 																								FROM ");
		sql.append(" 																									t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																								UNION ALL ");
		sql.append(" 																									SELECT ");
		sql.append(" 																										'西藏', ");
		sql.append(" 																										sum(t.xz_one) '组织人次', ");
		sql.append(" 																										sum(t.xz_two) '接待人次', ");
		sql.append(" 																										sum(t.xz_three) '组织人天', ");
		sql.append(" 																										sum(t.xz_four) '接待人天' ");
		sql.append(" 																									FROM ");
		sql.append(" 																										t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																									UNION ALL ");
		sql.append(" 																										SELECT ");
		sql.append(" 																											'陕西', ");
		sql.append(" 																											sum(t.shanxi_one) '组织人次', ");
		sql.append(" 																											sum(t.shanxi_two) '接待人次', ");
		sql.append(" 																											sum(t.shanxi_three) '组织人天', ");
		sql.append(" 																											sum(t.shanxi_four) '接待人天' ");
		sql.append(" 																										FROM ");
		sql.append(" 																											t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																										UNION ALL ");
		sql.append(" 																											SELECT ");
		sql.append(" 																												'甘肃', ");
		sql.append(" 																												sum(t.gansu_one) '组织人次', ");
		sql.append(" 																												sum(t.gansu_two) '接待人次', ");
		sql.append(" 																												sum(t.gansu_three) '组织人天', ");
		sql.append(" 																												sum(t.gansu_four) '接待人天' ");
		sql.append(" 																											FROM ");
		sql.append(" 																												t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																											UNION ALL ");
		sql.append(" 																												SELECT ");
		sql.append(" 																													'青海', ");
		sql.append(" 																													sum(t.qinghai_one) '组织人次', ");
		sql.append(" 																													sum(t.qinghai_two) '接待人次', ");
		sql.append(" 																													sum(t.qinghai_three) '组织人天', ");
		sql.append(" 																													sum(t.qinghai_four) '接待人天' ");
		sql.append(" 																												FROM ");
		sql.append(" 																													t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																												UNION ALL ");
		sql.append(" 																													SELECT ");
		sql.append(" 																														'宁夏', ");
		sql.append(" 																														sum(t.nx_one) '组织人次', ");
		sql.append(" 																														sum(t.nx_two) '接待人次', ");
		sql.append(" 																														sum(t.nx_three) '组织人天', ");
		sql.append(" 																														sum(t.nx_four) '接待人天' ");
		sql.append(" 																													FROM ");
		sql.append(" 																														t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		sql.append(" 																													UNION ALL ");
		sql.append(" 																														SELECT ");
		sql.append(" 																															'新疆', ");
		sql.append(" 																															sum(t.xj_one) '组织人次', ");
		sql.append(" 																															sum(t.xj_two) '接待人次', ");
		sql.append(" 																															sum(t.xj_three) '组织人天', ");
		sql.append(" 																															sum(t.xj_four) '接待人天' ");
		sql.append(" 																														FROM ");
		sql.append(" 																															t_travelagency_quarterly3 t where 1=1  and (t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")) ");
		
		ExportService emds = new ExportService(4,5);
		
		String excelName = "组织接待国内旅游情况一览表.xls";
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
	 * 旅行社国内游接待人次情况表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleTimesByTravelagency")
	public void inlandPeopleTimesByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum,a.`name`, ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.bj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.tj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hb_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sx_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nmg_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ln_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jl_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hlj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sh_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.js_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.zj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ah_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.fj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jx_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sd_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.henan_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hubei_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hl_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gd_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gx_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hainan_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	 ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.cq_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sc_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gz_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.yn_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xz_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.shanxi_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gansu_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.qinghai_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nx_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xj_one) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");


		ExportService emds = new ExportService(2,33);
		
		String excelName = "旅行社国内游组织人次情况表.xls";
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
	 * 旅行社国内游接待人次情况表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleTimesJiedaiByTravelagency")
	public void inlandPeopleTimesJiedaiByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum,a.`name`, ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.bj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.tj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hb_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sx_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nmg_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ln_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jl_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hlj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sh_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.js_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.zj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ah_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.fj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jx_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sd_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.henan_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hubei_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hl_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gd_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gx_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hainan_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	 ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.cq_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sc_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gz_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.yn_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xz_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.shanxi_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gansu_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.qinghai_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nx_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xj_two) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");

		ExportService emds = new ExportService(2,33);
		
		String excelName = "旅行社国内游接待人次情况表.xls";
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
	 * 旅行社国内游组织人天情况表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleDayByTravelagency")
	public void inlandPeopleDayByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum,a.`name`, ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.bj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.tj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hb_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sx_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nmg_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ln_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jl_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hlj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sh_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.js_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.zj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ah_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.fj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jx_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sd_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.henan_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hubei_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hl_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gd_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gx_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hainan_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	 ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.cq_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sc_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gz_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.yn_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xz_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.shanxi_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gansu_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.qinghai_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nx_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xj_three) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");
		ExportService emds = new ExportService(2,33);
		
		String excelName = "旅行社国内游组织人天情况表.xls";
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
	 * 旅行社国内游接待人天情况表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleDayJiedaiByTravelagency")
	public void inlandPeopleDayJiedaiByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum,a.`name`, ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.bj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.tj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hb_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sx_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nmg_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ln_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jl_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hlj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sh_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.js_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.zj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.ah_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.fj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.jx_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sd_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.henan_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hubei_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hl_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gd_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gx_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0), ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hainan_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	 ");
		sql.append(" 	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.cq_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.sc_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gz_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.yn_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xz_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.shanxi_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.gansu_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.qinghai_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.nx_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0),	IFNULL(( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.xj_four) '组织人次' ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	),0) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");
		ExportService emds = new ExportService(2,33);
		
		String excelName = "旅行社国内游接待人天情况表.xls";
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
	 * 组织人次和接待人次之和统计表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleTimesTotalByTravelagency")
	public void inlandPeopleTimesTotalByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum, ");
		sql.append(" a.`name`, ");
		sql.append(" 	IFNULL( ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.bj_one) + sum(t.tj_one) + sum(t.hb_one) + sum(t.sx_one) + sum(t.nmg_one) + sum(t.ln_one) + sum(t.jl_one) + sum(t.hlj_one) + sum(t.sh_one) + sum(t.js_one) + sum(t.zj_one) + sum(t.ah_one) + sum(t.fj_one) + sum(t.jx_one) + sum(t.sd_one) + sum(t.henan_one) + sum(t.hubei_one) + sum(t.hl_one) + sum(t.gd_one) + sum(t.gx_one) + sum(t.hainan_one) + sum(t.cq_one) + sum(t.sc_one) + sum(t.gz_one) + sum(t.yn_one) + sum(t.xz_one) + sum(t.shanxi_one) + sum(t.gansu_one) + sum(t.qinghai_one) + sum(t.nx_one) + sum(t.xj_one) ");
		sql.append(" 				) ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		), ");
		sql.append(" 		0 ");
		sql.append(" 	), ");
		sql.append(" 	IFNULL( ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.bj_two) + sum(t.tj_two) + sum(t.hb_two) + sum(t.sx_two) + sum(t.nmg_two) + sum(t.ln_two) + sum(t.jl_two) + sum(t.hlj_two) + sum(t.sh_two) + sum(t.js_two) + sum(t.zj_two) + sum(t.ah_two) + sum(t.fj_two) + sum(t.jx_two) + sum(t.sd_two) + sum(t.henan_two) + sum(t.hubei_two) + sum(t.hl_two) + sum(t.gd_two) + sum(t.gx_two) + sum(t.hainan_two) + sum(t.cq_two) + sum(t.sc_two) + sum(t.gz_two) + sum(t.yn_two) + sum(t.xz_two) + sum(t.shanxi_two) + sum(t.gansu_two) + sum(t.qinghai_two) + sum(t.nx_two) + sum(t.xj_two) ");
		sql.append(" 				) ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		), ");
		sql.append(" 		0 ");
		sql.append(" 	) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");

		ExportService emds = new ExportService(2,4);
		
		String excelName = "组织人次和接待人次之和统计表.xls";
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
	 * 组织人次和接待人天之和统计表
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "inlandPeopleDayTotalByTravelagency")
	public void inlandPeopleDayTotalByTravelagency( HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" a.licensenum, ");
		sql.append(" a.`name`, ");
		sql.append(" 	IFNULL( ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.bj_three) + sum(t.tj_three) + sum(t.hb_three) + sum(t.sx_three) + sum(t.nmg_three) + sum(t.ln_three) + sum(t.jl_three) + sum(t.hlj_three) + sum(t.sh_three) + sum(t.js_three) + sum(t.zj_three) + sum(t.ah_three) + sum(t.fj_three) + sum(t.jx_three) + sum(t.sd_three) + sum(t.henan_three) + sum(t.hubei_three) + sum(t.hl_three) + sum(t.gd_three) + sum(t.gx_three) + sum(t.hainan_three) + sum(t.cq_three) + sum(t.sc_three) + sum(t.gz_three) + sum(t.yn_three) + sum(t.xz_three) + sum(t.shanxi_three) + sum(t.gansu_three) + sum(t.qinghai_three) + sum(t.nx_three) + sum(t.xj_three) ");
		sql.append(" 				) ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		), ");
		sql.append(" 		0 ");
		sql.append(" 	), ");
		sql.append(" 	IFNULL( ");
		sql.append(" 		( ");
		sql.append(" 			SELECT ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.bj_four) + sum(t.tj_four) + sum(t.hb_four) + sum(t.sx_four) + sum(t.nmg_four) + sum(t.ln_four) + sum(t.jl_four) + sum(t.hlj_four) + sum(t.sh_four) + sum(t.js_four) + sum(t.zj_four) + sum(t.ah_four) + sum(t.fj_four) + sum(t.jx_four) + sum(t.sd_four) + sum(t.henan_four) + sum(t.hubei_four) + sum(t.hl_four) + sum(t.gd_four) + sum(t.gx_four) + sum(t.hainan_four) + sum(t.cq_four) + sum(t.sc_four) + sum(t.gz_four) + sum(t.yn_four) + sum(t.xz_four) + sum(t.shanxi_four) + sum(t.gansu_four) + sum(t.qinghai_four) + sum(t.nx_four) + sum(t.xj_four) ");
		sql.append(" 				) ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly3 t where 1=1 and t.tid=a.id  and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		), ");
		sql.append(" 		0 ");
		sql.append(" 	) ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_info a ");

		ExportService emds = new ExportService(2,4);
		
		String excelName = "组织人次和接待人天之和统计表.xls";
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
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return: 组织人天和接待人天之和统计（按地区）（报送）
	 */
	@RequestMapping(params = "zuzhiJieDaiDay")
	public void datagrid( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,tq.quarter as quarter,SUM(tq.totalThree) as sumZuzhiDay,SUM(tq.totalFour) as sumJiedaiDay "
       +" FROM `t_travelagency_quarterly3` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
				+ " where  1=1 ";
		if (StringUtil.isNotEmpty(getParam(request,"area"))) {
			sql += "and tr.area like '%"+getParam(request,"area")+"%'  ";
		}
		
		if (StringUtil.isNotEmpty(getParam(request,"year"))){
			sql += "and tq.`year` ="+getParam(request,"year");
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))){
			sql += "and tq.quarter in ('"+getParam(request,"quarter")+"') ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"order"))){
			sql += "order by "+getParam(request,"area");
		}
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
	/**
	 * 
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return: 按旅行社排序后台（按旅行社）（报送）
	 */
	@RequestMapping(params = "inlandlistByTravel")
	public void listByTravel(TravelQuarterInland travelQuarterInland,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterInland.class,
				dataGrid);
		String licensenumQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterInland.getTraveldata() == null ? "" : travelQuarterInland.getTraveldata().getName();
		String yearQuery = travelQuarterInland.getYear();
		String quarterQuery = travelQuarterInland.getQuarter();
		if(licensenumQuery != null && licensenumQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.licensenum like '%"+licensenumQuery+"%' and t.id=tid)"));
		}
		if(nameQuery != null && nameQuery.length() != 0){
			cq.add(Restrictions.sqlRestriction(" 1=1 and exists(select * from t_travelagency_info t where t.name like '%"+nameQuery+"%' and t.id=tid)"));
		}
		if(yearQuery !=null && yearQuery.length() != 0){
			cq.add(Restrictions.like("year", "%"+yearQuery+"%"));
		}
		if(quarterQuery !=null && quarterQuery.length() != 0){
			cq.add(Restrictions.like("quarter", quarterQuery));
		}
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterInland);
		
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
//*********************************导出*************************************************************	
	//旅行社国内游接待人次情况表(按旅行社)
	@RequestMapping(params = "exportsxls1")
	public String exportXls1(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterInlandRec tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterInlandRec> sTQuarterInlandRecs = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"旅行社国内游接待人次情况表(按旅行社)");
	    map.put(NormalExcelConstants.CLASS,STQuarterInlandRec.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("旅行社国内游接待人次情况表(按旅行社)", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterInlandRecs);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	//旅行社国内游接待人天情况表(按旅行社)
	@RequestMapping(params = "exportsxls2")
	public String exportXls2(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterInlandRecDay tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterInlandRecDay> sTQuarterInlandRecDays = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"旅行社国内游接待人天情况表(按旅行社)");
	    map.put(NormalExcelConstants.CLASS,STQuarterInlandRecDay.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("旅行社国内游接待人天情况表(按旅行社)", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterInlandRecDays);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	//旅行社国内游组织人次情况表(按旅行社)
	@RequestMapping(params = "exportsxls3")
	public String exportXls3(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterInlandOrg tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterInlandOrg> sTQuarterInlandOrgs = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"旅行社国内游组织人次情况表(按旅行社)）");
	    map.put(NormalExcelConstants.CLASS,STQuarterInlandOrg.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("旅行社国内游组织人次情况表(按旅行社)", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterInlandOrgs);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	//旅行社国内游组织人天情况表(按旅行社)
	@RequestMapping(params = "exportsxls4")
	public String exportXls4(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterInlandOrgDay tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterInlandOrgDay> sTQuarterInlandOrgDays = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"旅行社国内游组织人天情况表(按旅行社)");
	    map.put(NormalExcelConstants.CLASS,STQuarterInlandOrgDay.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("旅行社国内游组织人天情况表(按旅行社)", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterInlandOrgDays);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}

	
}
