package com.zzc.web.travel.controller;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import com.zzc.web.travel.entity.STQuarterInOreach;
import com.zzc.web.travel.entity.STQuarterOIC;
import com.zzc.web.travel.entity.TravelQuarterIn;


/**
 * 
 *                  
 * @date: 2017年1月9日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: StatisticTravelquarterController.java
 * @Version: 1.0
 * @About: 
 *旅行社季报统计之入境统计
 */
@Scope("prototype")
@Controller
@RequestMapping("instatisticTravelquarterController")
public class InStatisticTravelquarterController extends BaseController {
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

	//返回统计列表
	@RequestMapping(params = "toexports")
	public ModelAndView toInAimlistByAreaOutreach(HttpServletRequest request) {
		List<String> yearList = this.systemService.findListbySql("SELECT distinct ta.`year` AS year FROM  t_travelAgency_quarterly1 ta");		
		request.setAttribute("yearList", yearList);
		return new ModelAndView("travelstatisticOfIn/inQuarter");
	}
	//返回入境旅游目的地国家接待人次统计表(接待 按地区:人次）
	@RequestMapping(params = "toInAimlistByAreaRecp")
	public ModelAndView toOutlistByArea(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inAimByAreaRecp");
	}
	//外联人天和接待人天之和统计（ 按地区:人天）(报送)
	@RequestMapping(params = "toInOuteachRecp")
	public ModelAndView toInOuteachRecp(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inOuteachRecp");
	}
/*	//入境旅游目的地国家人天统计表（按地区:人天）
	@RequestMapping(params = "toAimlistByAreaDay")
	public ModelAndView toOutAimlistByArea2(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inAimbyareaReception");
	}*/
/*	//返回入境旅游大洲人次统计表（按旅行社:人次）列表
	@RequestMapping(params = "tocontinentList")
	public ModelAndView statisticTabs(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/incontinentList");
	}*/
	//入境旅游目的地国家外联人次统计表（按旅行社 外联 人次）
	@RequestMapping(params = "toInAimByTravelOutreach")
	public ModelAndView toInlistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inAimByTravelOutreach");
	}
	//入境旅游目的地国家接待人次统计表（按旅行社 接待 人次）
	@RequestMapping(params = "toInAimByTravelRec")
	public ModelAndView toOutlistByTravel(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inAimByTravelRec");
	}
	//外联人次和接待人次之和排序统计（按旅行社 人次）
	@RequestMapping(params = "toInByTravelOutreachRec")
	public ModelAndView toInByTravelOutreachRec(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inByTravelOutreachRec");
	}
	//外联人天和接待人天之和排序统计（按旅行社 人天）
	@RequestMapping(params = "toInByTravelOutreachRecDay")
	public ModelAndView toInByTravelOutreachRecDay(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/inByTravelOutreachRecDay");
	}

	/*//入境旅游目的地国家人天统计（按旅行社:人天）
	@RequestMapping(params = "toAimlistByTravel")
	public ModelAndView toOutAimlistByTravel2(HttpServletRequest request) {
		return new ModelAndView("travelstatisticOfIn/AimbyTravel2");
	}*/
	
	/**
	 *
	 * @date: 2017年1月9日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:（按旅行社）列表
	 */
	@RequestMapping(params = "inGridbytravel")
	public void entryGridOfAdmin(TravelQuarterIn travelQuarterIn,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterIn.class,
				dataGrid);
		String licensenumQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getName();
		String yearQuery = travelQuarterIn.getYear();
		String quarterQuery = travelQuarterIn.getQuarter();
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
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterIn);
		
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "entryCollection")
	public void wailianMenTimeByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'入境游客', ");
		sql.append(" 	sum(y.one), ");
		sql.append(" 	sum(y.two), ");
		sql.append(" 	cast(sum(y.three) as char ), ");
		sql.append(" 	cast(sum(y.four) as char ) ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			sum(t.hk_com_one) AS `one`, ");
		sql.append(" 			sum(t.hk_com_two) AS `two`, ");
		sql.append(" 			sum(t.hk_com_three) AS `three`, ");
		sql.append(" 			sum(t.hk_com_four) AS `four` ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				sum(t.mc_com_one) AS `one`, ");
		sql.append(" 				sum(t.mc_com_two) AS `two`, ");
		sql.append(" 				sum(t.mc_com_three) AS `three`, ");
		sql.append(" 				sum(t.mc_com_four) AS `four` ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					sum(t.tw_com_one) AS `one`, ");
		sql.append(" 					sum(t.tw_com_two) AS `two`, ");
		sql.append(" 					sum(t.tw_com_three) AS `three`, ");
		sql.append(" 					sum(t.tw_com_four) AS `four` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 				UNION ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						SUM(g.`外联`) AS `one`, ");
		sql.append(" 						SUM(g.`接待`) AS `two`, ");
		sql.append(" 						( ");
		sql.append(" 							SELECT ");
		sql.append(" 								sum(t.foreigners_three) ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 						) AS `three`, ");
		sql.append(" 						( ");
		sql.append(" 							SELECT ");
		sql.append(" 								sum(t.foreigners_four) ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 						) AS `four` ");
		sql.append(" 					FROM ");
		sql.append(" 						( ");
		sql.append(" 							SELECT ");
		sql.append(" 								( ");
		sql.append(" 									sum(t.japan_one) + sum(t.korea_one) + sum(t.mongo_one) + sum(t.indonxy_one) + sum(t.malaxy_one) + sum(t.philipn_one) + sum(t.singapore_one) + sum(t.tailand_one) + sum(t.india_one) + sum(t.vietnam_one) + sum(t.myanmar_one) + sum(t.other_asian_one) ");
		sql.append(" 								) `外联`, ");
		sql.append(" 								( ");
		sql.append(" 									sum(t.japan_two) + sum(t.korea_two) + sum(t.mongo_two) + sum(t.indonxy_two) + sum(t.malaxy_two) + sum(t.philipn_two) + sum(t.singapore_two) + sum(t.tailand_two) + sum(t.india_two) + sum(t.vietnam_two) + sum(t.myanmar_two) + sum(t.other_asian_two) ");
		sql.append(" 								) `接待` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 							UNION ALL ");
		sql.append(" 								SELECT ");
		sql.append(" 									( ");
		sql.append(" 										sum(t.denmark_one) + sum(t.holland_one) + sum(t.england_one) + sum(t.french_one) + sum(t.germany_one) + sum(t.italy_one) + sum(t.swiss_one) + sum(t.swedish_one) + sum(t.russia_one) + sum(t.spain_one) + sum(t.other_eropean_one) ");
		sql.append(" 									) `外联`, ");
		sql.append(" 									( ");
		sql.append(" 										sum(t.denmark_two) + sum(t.holland_two) + sum(t.england_two) + sum(t.french_two) + sum(t.germany_two) + sum(t.italy_two) + sum(t.swiss_two) + sum(t.swedish_two) + sum(t.russia_two) + sum(t.spain_two) + sum(t.other_eropean_two) ");
		sql.append(" 									) `接待` ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 								UNION ALL ");
		sql.append(" 									SELECT ");
		sql.append(" 										( ");
		sql.append(" 											sum(t.us_one) + sum(t.canada_one) + sum(t.other_american_one) ");
		sql.append(" 										) `外联`, ");
		sql.append(" 										( ");
		sql.append(" 											sum(t.us_two) + sum(t.canada_two) + sum(t.other_american_two) ");
		sql.append(" 										) `接待` ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 									UNION ALL ");
		sql.append(" 										SELECT ");
		sql.append(" 											( ");
		sql.append(" 												sum(t.australia_one) + sum(t.newland_one) + sum(t.other_oceania_one) ");
		sql.append(" 											) `外联`, ");
		sql.append(" 											( ");
		sql.append(" 												sum(t.australia_two) + sum(t.newland_two) + sum(t.other_oceania_two) ");
		sql.append(" 											) `接待` ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 										UNION ALL ");
		sql.append(" 											SELECT ");
		sql.append(" 												( ");
		sql.append(" 													sum(t.southafrica_one) + sum(t.egypt_one) + sum(t.kenya_one) + sum(t.africaother_one) ");
		sql.append(" 												) `外联`, ");
		sql.append(" 												( ");
		sql.append(" 													sum(t.southafrica_two) + sum(t.egypt_two) + sum(t.kenya_two) + sum(t.africaother_two) ");
		sql.append(" 												) `接待` ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 						) g ");
		sql.append(" 	) y ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'香港同胞', ");
		sql.append(" 		sum(t.hk_com_one), ");
		sql.append(" 		sum(t.hk_com_two), ");
		sql.append(" 		sum(t.hk_com_three), ");
		sql.append(" 		sum(t.hk_com_four) ");
		sql.append(" 	FROM ");
		sql.append(" 		t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'澳门同胞', ");
		sql.append(" 			sum(t.mc_com_one), ");
		sql.append(" 			sum(t.mc_com_two), ");
		sql.append(" 			sum(t.mc_com_three), ");
		sql.append(" 			sum(t.mc_com_four) ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'台湾同胞', ");
		sql.append(" 				sum(t.tw_com_one), ");
		sql.append(" 				sum(t.tw_com_two), ");
		sql.append(" 				sum(t.tw_com_three), ");
		sql.append(" 				sum(t.tw_com_four) ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'外国人', ");
		sql.append(" 					SUM(g.`外联`) `外联`, ");
		sql.append(" 					SUM(g.`接待`) `接待`, ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							sum(t.foreigners_three) ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 					), ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							sum(t.foreigners_four) ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 					) ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							( ");
		sql.append(" 								sum(t.japan_one) + sum(t.korea_one) + sum(t.mongo_one) + sum(t.indonxy_one) + sum(t.malaxy_one) + sum(t.philipn_one) + sum(t.singapore_one) + sum(t.tailand_one) + sum(t.india_one) + sum(t.vietnam_one) + sum(t.myanmar_one) + sum(t.other_asian_one) ");
		sql.append(" 							) `外联`, ");
		sql.append(" 							( ");
		sql.append(" 								sum(t.japan_two) + sum(t.korea_two) + sum(t.mongo_two) + sum(t.indonxy_two) + sum(t.malaxy_two) + sum(t.philipn_two) + sum(t.singapore_two) + sum(t.tailand_two) + sum(t.india_two) + sum(t.vietnam_two) + sum(t.myanmar_two) + sum(t.other_asian_two) ");
		sql.append(" 							) `接待` ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 						union all ");
		sql.append(" 							select ");
		sql.append(" 								( ");
		sql.append(" 									sum(t.denmark_one) + sum(t.holland_one) + sum(t.england_one) + sum(t.french_one) + sum(t.germany_one) + sum(t.italy_one) + sum(t.swiss_one) + sum(t.swedish_one) + sum(t.russia_one) + sum(t.spain_one) + sum(t.other_eropean_one) ");
		sql.append(" 								) `外联`, ");
		sql.append(" 								( ");
		sql.append(" 									sum(t.denmark_two) + sum(t.holland_two) + sum(t.england_two) + sum(t.french_two) + sum(t.germany_two) + sum(t.italy_two) + sum(t.swiss_two) + sum(t.swedish_two) + sum(t.russia_two) + sum(t.spain_two) + sum(t.other_eropean_two) ");
		sql.append(" 								) `接待` ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 							union all ");
		sql.append(" 								select ");
		sql.append(" 									( ");
		sql.append(" 										sum(t.us_one) + sum(t.canada_one) + sum(t.other_american_one) ");
		sql.append(" 									) `外联`, ");
		sql.append(" 									( ");
		sql.append(" 										sum(t.us_two) + sum(t.canada_two) + sum(t.other_american_two) ");
		sql.append(" 									) `接待` ");
		sql.append(" 								from ");
		sql.append(" 									t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 								union all ");
		sql.append(" 									select ");
		sql.append(" 										( ");
		sql.append(" 											sum(t.australia_one) + sum(t.newland_one) + sum(t.other_oceania_one) ");
		sql.append(" 										) `外联`, ");
		sql.append(" 										( ");
		sql.append(" 											sum(t.australia_two) + sum(t.newland_two) + sum(t.other_oceania_two) ");
		sql.append(" 										) `接待` ");
		sql.append(" 									from ");
		sql.append(" 										t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 									union all ");
		sql.append(" 										select ");
		sql.append(" 											( ");
		sql.append(" 												sum(t.southafrica_one) + sum(t.egypt_one) + sum(t.kenya_one) + sum(t.africaother_one) ");
		sql.append(" 											) `外联`, ");
		sql.append(" 											( ");
		sql.append(" 												sum(t.southafrica_two) + sum(t.egypt_two) + sum(t.kenya_two) + sum(t.africaother_two) ");
		sql.append(" 											) `接待` ");
		sql.append(" 										from ");
		sql.append(" 											t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 					) g ");
		sql.append(" 				union ALL ");
		sql.append(" 					SELECT ");
		sql.append(" 						'亚洲小计', ");
		sql.append(" 						( ");
		sql.append(" 							sum(t.japan_one) + sum(t.korea_one) + sum(t.mongo_one) + sum(t.indonxy_one) + sum(t.malaxy_one) + sum(t.philipn_one) + sum(t.singapore_one) + sum(t.tailand_one) + sum(t.india_one) + sum(t.vietnam_one) + sum(t.myanmar_one) + sum(t.other_asian_one) ");
		sql.append(" 						) `外联`, ");
		sql.append(" 						( ");
		sql.append(" 							sum(t.japan_two) + sum(t.korea_two) + sum(t.mongo_two) + sum(t.indonxy_two) + sum(t.malaxy_two) + sum(t.philipn_two) + sum(t.singapore_two) + sum(t.tailand_two) + sum(t.india_two) + sum(t.vietnam_two) + sum(t.myanmar_two) + sum(t.other_asian_two) ");
		sql.append(" 						) `接待`, ");
		sql.append(" 						'---', ");
		sql.append(" 						'---' ");
		sql.append(" 					FROM ");
		sql.append(" 						t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 					union all ");
		sql.append(" 						select ");
		sql.append(" 							'日本', ");
		sql.append(" 							sum(t.japan_one), ");
		sql.append(" 							sum(t.japan_two), ");
		sql.append(" 							'---', ");
		sql.append(" 							'---' ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 						union all ");
		sql.append(" 							select ");
		sql.append(" 								'韩国', ");
		sql.append(" 								sum(t.korea_one), ");
		sql.append(" 								sum(t.korea_two), ");
		sql.append(" 								'---', ");
		sql.append(" 								'---' ");
		sql.append(" 							FROM ");
		sql.append(" 								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 							union all ");
		sql.append(" 								select ");
		sql.append(" 									'蒙古', ");
		sql.append(" 									sum(t.mongo_one), ");
		sql.append(" 									sum(t.mongo_two), ");
		sql.append(" 									'---', ");
		sql.append(" 									'---' ");
		sql.append(" 								FROM ");
		sql.append(" 									t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 								union all ");
		sql.append(" 									select ");
		sql.append(" 										'印度尼西亚', ");
		sql.append(" 										sum(t.indonxy_one), ");
		sql.append(" 										sum(t.indonxy_two), ");
		sql.append(" 										'---', ");
		sql.append(" 										'---' ");
		sql.append(" 									FROM ");
		sql.append(" 										t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 									union all ");
		sql.append(" 										select ");
		sql.append(" 											'马来西亚', ");
		sql.append(" 											sum(t.malaxy_one), ");
		sql.append(" 											sum(t.malaxy_two), ");
		sql.append(" 											'---', ");
		sql.append(" 											'---' ");
		sql.append(" 										FROM ");
		sql.append(" 											t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 										union all ");
		sql.append(" 											select ");
		sql.append(" 												'菲律宾', ");
		sql.append(" 												sum(t.philipn_one), ");
		sql.append(" 												sum(t.philipn_two), ");
		sql.append(" 												'---', ");
		sql.append(" 												'---' ");
		sql.append(" 											FROM ");
		sql.append(" 												t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 											union all ");
		sql.append(" 												select ");
		sql.append(" 													'新加坡', ");
		sql.append(" 													sum(t.singapore_one), ");
		sql.append(" 													sum(t.singapore_two), ");
		sql.append(" 													'---', ");
		sql.append(" 													'---' ");
		sql.append(" 												FROM ");
		sql.append(" 													t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 												union all ");
		sql.append(" 													select ");
		sql.append(" 														'泰国', ");
		sql.append(" 														sum(t.tailand_one), ");
		sql.append(" 														sum(t.tailand_two), ");
		sql.append(" 														'---', ");
		sql.append(" 														'---' ");
		sql.append(" 													FROM ");
		sql.append(" 														t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 													union all ");
		sql.append(" 														select ");
		sql.append(" 															'印度', ");
		sql.append(" 															sum(t.india_one), ");
		sql.append(" 															sum(t.india_two), ");
		sql.append(" 															'---', ");
		sql.append(" 															'---' ");
		sql.append(" 														FROM ");
		sql.append(" 															t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 														union all ");
		sql.append(" 															select ");
		sql.append(" 																'越南', ");
		sql.append(" 																sum(t.vietnam_one), ");
		sql.append(" 																sum(t.vietnam_two), ");
		sql.append(" 																'---', ");
		sql.append(" 																'---' ");
		sql.append(" 															FROM ");
		sql.append(" 																t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 															union all ");
		sql.append(" 																select ");
		sql.append(" 																	'缅甸', ");
		sql.append(" 																	sum(t.myanmar_one), ");
		sql.append(" 																	sum(t.myanmar_two), ");
		sql.append(" 																	'---', ");
		sql.append(" 																	'---' ");
		sql.append(" 																FROM ");
		sql.append(" 																	t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																union all ");
		sql.append(" 																	select ");
		sql.append(" 																		'亚洲其他', ");
		sql.append(" 																		sum(t.other_asian_one), ");
		sql.append(" 																		sum(t.other_asian_two), ");
		sql.append(" 																		'---', ");
		sql.append(" 																		'---' ");
		sql.append(" 																	FROM ");
		sql.append(" 																		t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																	union ALL ");
		sql.append(" 																		select ");
		sql.append(" 																			'欧洲小计', ");
		sql.append(" 																			( ");
		sql.append(" 																				sum(t.denmark_one) + sum(t.holland_one) + sum(t.england_one) + sum(t.french_one) + sum(t.germany_one) + sum(t.italy_one) + sum(t.swiss_one) + sum(t.swedish_one) + sum(t.russia_one) + sum(t.spain_one) + sum(t.other_eropean_one) ");
		sql.append(" 																			) `外联`, ");
		sql.append(" 																			( ");
		sql.append(" 																				sum(t.denmark_two) + sum(t.holland_two) + sum(t.england_two) + sum(t.french_two) + sum(t.germany_two) + sum(t.italy_two) + sum(t.swiss_two) + sum(t.swedish_two) + sum(t.russia_two) + sum(t.spain_two) + sum(t.other_eropean_two) ");
		sql.append(" 																			) `接待`, ");
		sql.append(" 																			'---', ");
		sql.append(" 																			'---' ");
		sql.append(" 																		FROM ");
		sql.append(" 																			t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																		union all ");
		sql.append(" 																			select ");
		sql.append(" 																				'丹麦', ");
		sql.append(" 																				sum(t.denmark_one), ");
		sql.append(" 																				sum(t.denmark_two), ");
		sql.append(" 																				'---', ");
		sql.append(" 																				'---' ");
		sql.append(" 																			FROM ");
		sql.append(" 																				t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																			union all ");
		sql.append(" 																				select ");
		sql.append(" 																					'荷兰', ");
		sql.append(" 																					sum(t.holland_one), ");
		sql.append(" 																					sum(t.holland_two), ");
		sql.append(" 																					'---', ");
		sql.append(" 																					'---' ");
		sql.append(" 																				FROM ");
		sql.append(" 																					t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																				union all ");
		sql.append(" 																					select ");
		sql.append(" 																						'英国', ");
		sql.append(" 																						sum(t.england_one), ");
		sql.append(" 																						sum(t.england_two), ");
		sql.append(" 																						'---', ");
		sql.append(" 																						'---' ");
		sql.append(" 																					FROM ");
		sql.append(" 																						t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																					union all ");
		sql.append(" 																						select ");
		sql.append(" 																							'法国', ");
		sql.append(" 																							sum(t.french_one), ");
		sql.append(" 																							sum(t.french_two), ");
		sql.append(" 																							'---', ");
		sql.append(" 																							'---' ");
		sql.append(" 																						FROM ");
		sql.append(" 																							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																						union all ");
		sql.append(" 																							select ");
		sql.append(" 																								'德国', ");
		sql.append(" 																								sum(t.germany_one), ");
		sql.append(" 																								sum(t.germany_two), ");
		sql.append(" 																								'---', ");
		sql.append(" 																								'---' ");
		sql.append(" 																							FROM ");
		sql.append(" 																								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																							union all ");
		sql.append(" 																								select ");
		sql.append(" 																									'意大利', ");
		sql.append(" 																									sum(t.italy_one), ");
		sql.append(" 																									sum(t.italy_two), ");
		sql.append(" 																									'---', ");
		sql.append(" 																									'---' ");
		sql.append(" 																								FROM ");
		sql.append(" 																									t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																								union all ");
		sql.append(" 																									select ");
		sql.append(" 																										'瑞士', ");
		sql.append(" 																										sum(t.swiss_one), ");
		sql.append(" 																										sum(t.swiss_two), ");
		sql.append(" 																										'---', ");
		sql.append(" 																										'---' ");
		sql.append(" 																									FROM ");
		sql.append(" 																										t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																									union all ");
		sql.append(" 																										select ");
		sql.append(" 																											'瑞典', ");
		sql.append(" 																											sum(t.swedish_one), ");
		sql.append(" 																											sum(t.swedish_two), ");
		sql.append(" 																											'---', ");
		sql.append(" 																											'---' ");
		sql.append(" 																										FROM ");
		sql.append(" 																											t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																										union all ");
		sql.append(" 																											select ");
		sql.append(" 																												'俄罗斯', ");
		sql.append(" 																												sum(t.russia_one), ");
		sql.append(" 																												sum(t.russia_two), ");
		sql.append(" 																												'---', ");
		sql.append(" 																												'---' ");
		sql.append(" 																											FROM ");
		sql.append(" 																												t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																											union all ");
		sql.append(" 																												select ");
		sql.append(" 																													'西班牙', ");
		sql.append(" 																													sum(t.spain_one), ");
		sql.append(" 																													sum(t.spain_two), ");
		sql.append(" 																													'---', ");
		sql.append(" 																													'---' ");
		sql.append(" 																												FROM ");
		sql.append(" 																													t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																												union all ");
		sql.append(" 																													select ");
		sql.append(" 																														'欧洲其他', ");
		sql.append(" 																														sum(t.other_eropean_one), ");
		sql.append(" 																														sum(t.other_eropean_two), ");
		sql.append(" 																														'---', ");
		sql.append(" 																														'---' ");
		sql.append(" 																													FROM ");
		sql.append(" 																														t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																													union ALL ");
		sql.append(" 																														select ");
		sql.append(" 																															'美洲小计', ");
		sql.append(" 																															( ");
		sql.append(" 																																sum(t.us_one) + sum(t.canada_one) + sum(t.other_american_one) ");
		sql.append(" 																															) `外联`, ");
		sql.append(" 																															( ");
		sql.append(" 																																sum(t.us_two) + sum(t.canada_two) + sum(t.other_american_two) ");
		sql.append(" 																															) `接待`, ");
		sql.append(" 																															'---', ");
		sql.append(" 																															'---' ");
		sql.append(" 																														FROM ");
		sql.append(" 																															t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																														union all ");
		sql.append(" 																															select ");
		sql.append(" 																																'美国', ");
		sql.append(" 																																sum(t.us_one), ");
		sql.append(" 																																sum(t.us_two), ");
		sql.append(" 																																'---', ");
		sql.append(" 																																'---' ");
		sql.append(" 																															FROM ");
		sql.append(" 																																t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																															union all ");
		sql.append(" 																																select ");
		sql.append(" 																																	'加拿大', ");
		sql.append(" 																																	sum(t.canada_one), ");
		sql.append(" 																																	sum(t.canada_two), ");
		sql.append(" 																																	'---', ");
		sql.append(" 																																	'---' ");
		sql.append(" 																																FROM ");
		sql.append(" 																																	t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																union all ");
		sql.append(" 																																	select ");
		sql.append(" 																																		'美洲其他', ");
		sql.append(" 																																		sum(t.other_american_one), ");
		sql.append(" 																																		sum(t.other_american_two), ");
		sql.append(" 																																		'---', ");
		sql.append(" 																																		'---' ");
		sql.append(" 																																	FROM ");
		sql.append(" 																																		t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																	union ALL ");
		sql.append(" 																																		select ");
		sql.append(" 																																			'大洋洲小计', ");
		sql.append(" 																																			( ");
		sql.append(" 																																				sum(t.australia_one) + sum(t.newland_one) + sum(t.other_oceania_one) ");
		sql.append(" 																																			) `外联`, ");
		sql.append(" 																																			( ");
		sql.append(" 																																				sum(t.australia_two) + sum(t.newland_two) + sum(t.other_oceania_two) ");
		sql.append(" 																																			) `接待`, ");
		sql.append(" 																																			'---', ");
		sql.append(" 																																			'---' ");
		sql.append(" 																																		FROM ");
		sql.append(" 																																			t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																		union all ");
		sql.append(" 																																			select ");
		sql.append(" 																																				'澳大利亚', ");
		sql.append(" 																																				sum(t.australia_one), ");
		sql.append(" 																																				sum(t.australia_two), ");
		sql.append(" 																																				'---', ");
		sql.append(" 																																				'---' ");
		sql.append(" 																																			FROM ");
		sql.append(" 																																				t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																			union all ");
		sql.append(" 																																				select ");
		sql.append(" 																																					'新西兰', ");
		sql.append(" 																																					sum(t.newland_one), ");
		sql.append(" 																																					sum(t.newland_two), ");
		sql.append(" 																																					'---', ");
		sql.append(" 																																					'---' ");
		sql.append(" 																																				FROM ");
		sql.append(" 																																					t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																				union all ");
		sql.append(" 																																					select ");
		sql.append(" 																																						'大洋洲其他', ");
		sql.append(" 																																						sum(t.other_oceania_one), ");
		sql.append(" 																																						sum(t.other_oceania_two), ");
		sql.append(" 																																						'---', ");
		sql.append(" 																																						'---' ");
		sql.append(" 																																					FROM ");
		sql.append(" 																																						t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																					union all ");
		sql.append(" 																																						select ");
		sql.append(" 																																							'非洲小计', ");
		sql.append(" 																																							( ");
		sql.append(" 																																								sum(t.southafrica_one) + sum(t.egypt_one) + sum(t.kenya_one) + sum(t.africaother_one) ");
		sql.append(" 																																							) `外联`, ");
		sql.append(" 																																							( ");
		sql.append(" 																																								sum(t.southafrica_two) + sum(t.egypt_two) + sum(t.kenya_two) + sum(t.africaother_two) ");
		sql.append(" 																																							) `接待`, ");
		sql.append(" 																																							'---', ");
		sql.append(" 																																							'---' ");
		sql.append(" 																																						FROM ");
		sql.append(" 																																							t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																						union all ");
		sql.append(" 																																							select ");
		sql.append(" 																																								'南非', ");
		sql.append(" 																																								sum(t.southafrica_one), ");
		sql.append(" 																																								sum(t.southafrica_two), ");
		sql.append(" 																																								'---', ");
		sql.append(" 																																								'---' ");
		sql.append(" 																																							FROM ");
		sql.append(" 																																								t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																							union all ");
		sql.append(" 																																								select ");
		sql.append(" 																																									'埃及', ");
		sql.append(" 																																									sum(t.egypt_one), ");
		sql.append(" 																																									sum(t.egypt_two), ");
		sql.append(" 																																									'---', ");
		sql.append(" 																																									'---' ");
		sql.append(" 																																								FROM ");
		sql.append(" 																																									t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																								union all ");
		sql.append(" 																																									select ");
		sql.append(" 																																										'肯尼亚', ");
		sql.append(" 																																										sum(t.kenya_one), ");
		sql.append(" 																																										sum(t.kenya_two), ");
		sql.append(" 																																										'---', ");
		sql.append(" 																																										'---' ");
		sql.append(" 																																									FROM ");
		sql.append(" 																																										t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		sql.append(" 																																									union all ");
		sql.append(" 																																										select ");
		sql.append(" 																																											'非洲其他', ");
		sql.append(" 																																											sum(t.africaother_one), ");
		sql.append(" 																																											sum(t.africaother_two), ");
		sql.append(" 																																											'---', ");
		sql.append(" 																																											'---' ");
		sql.append(" 																																										FROM ");
		sql.append(" 																																											t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))  ");
		
		ExportService emds = new ExportService(4,5);
		
		String excelName = "旅行社外联接待入境旅游情况汇总统计.xls";
		String modelPath = "/com/zzc/web/export/model/travelagency/"+ excelName;
		
		HSSFWorkbook workbook = null;
		try {
			workbook = emds.getNewModelInfos(modelPath, sql.toString());
			System.out.println(sql.toString());
		} catch (Exception e) {

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
	 *  入境旅游目的地国家接待人次统计表（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "entryJiedaiPeopleTimesByTravelagency")
	public void entryJiedaiPeopleTimesByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" 		SELECT  ");
		sql.append(" 			a.licensenum,  ");
		sql.append(" 			a.`name`,  ");
		sql.append(" 			sum(t.hk_com_two),  ");
		sql.append(" 			sum(t.mc_com_two),  ");
		sql.append(" 			sum(t.tw_com_two),  ");
		sql.append(" 			sum(t.japan_two),  ");
		sql.append(" 			sum(t.korea_two),  ");
		sql.append(" 			sum(t.mongo_two),  ");
		sql.append(" 			sum(t.indonxy_two),  ");
		sql.append(" 			sum(t.malaxy_two),  ");
		sql.append(" 			sum(t.philipn_two),  ");
		sql.append(" 			sum(t.singapore_two),  ");
		sql.append(" 			sum(t.tailand_two),  ");
		sql.append(" 			sum(t.india_two),  ");
		sql.append(" 			sum(t.vietnam_two),  ");
		sql.append(" 			sum(t.myanmar_two),  ");
		sql.append(" 			sum(t.other_asian_two),  ");
		sql.append(" 			sum(t.denmark_two),  ");
		sql.append(" 			sum(t.holland_two),  ");
		sql.append(" 			sum(t.england_two),  ");
		sql.append(" 			sum(t.french_two),  ");
		sql.append(" 			sum(t.germany_two),  ");
		sql.append(" 			sum(t.italy_two),  ");
		sql.append(" 			sum(t.swiss_two),  ");
		sql.append(" 			sum(t.swedish_two),  ");
		sql.append(" 			sum(t.russia_two),  ");
		sql.append(" 			sum(t.spain_two),  ");
		sql.append(" 			sum(t.other_eropean_two),  ");
		sql.append(" 			sum(t.us_two),  ");
		sql.append(" 			sum(t.canada_two),  ");
		sql.append(" 			sum(t.other_american_two),  ");
		sql.append(" 			sum(t.australia_two),  ");
		sql.append(" 			sum(t.newland_two),  ");
		sql.append(" 			sum(t.other_oceania_two),  ");
		sql.append(" 			sum(t.southafrica_two),  ");
		sql.append(" 			sum(t.egypt_two),  ");
		sql.append(" 			sum(t.kenya_two),  ");
		sql.append(" 			sum(t.africaother_two)  ");
		sql.append(" 		FROM  ");
		sql.append(" 			t_travelagency_quarterly1 t  ");
		sql.append(" 		LEFT JOIN t_travelagency_info a ON t.tid = a.id  ");

		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) GROUP BY t.tid  ");

		ExportService emds = new ExportService(2,38);
		
		String excelName = "入境旅游目的地国家接待人次统计表（按旅行社）.xls";
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
	 *  入境旅游目的地国家外联人次统计表（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "entryWailianPeopleTimesByTravelagency")
	public void entryWailianPeopleTimesByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" 		SELECT  ");
		sql.append(" 			a.licensenum,  ");
		sql.append(" 			a.`name`,  ");
		sql.append(" 			sum(t.hk_com_one),  ");
		sql.append(" 			sum(t.mc_com_one),  ");
		sql.append(" 			sum(t.tw_com_one),  ");
		sql.append(" 			sum(t.japan_one),  ");
		sql.append(" 			sum(t.korea_one),  ");
		sql.append(" 			sum(t.mongo_one),  ");
		sql.append(" 			sum(t.indonxy_one),  ");
		sql.append(" 			sum(t.malaxy_one),  ");
		sql.append(" 			sum(t.philipn_one),  ");
		sql.append(" 			sum(t.singapore_one),  ");
		sql.append(" 			sum(t.tailand_one),  ");
		sql.append(" 			sum(t.india_one),  ");
		sql.append(" 			sum(t.vietnam_one),  ");
		sql.append(" 			sum(t.myanmar_one),  ");
		sql.append(" 			sum(t.other_asian_one),  ");
		sql.append(" 			sum(t.denmark_one),  ");
		sql.append(" 			sum(t.holland_one),  ");
		sql.append(" 			sum(t.england_one),  ");
		sql.append(" 			sum(t.french_one),  ");
		sql.append(" 			sum(t.germany_one),  ");
		sql.append(" 			sum(t.italy_one),  ");
		sql.append(" 			sum(t.swiss_one),  ");
		sql.append(" 			sum(t.swedish_one),  ");
		sql.append(" 			sum(t.russia_one),  ");
		sql.append(" 			sum(t.spain_one),  ");
		sql.append(" 			sum(t.other_eropean_one),  ");
		sql.append(" 			sum(t.us_one),  ");
		sql.append(" 			sum(t.canada_one),  ");
		sql.append(" 			sum(t.other_american_one),  ");
		sql.append(" 			sum(t.australia_one),  ");
		sql.append(" 			sum(t.newland_one),  ");
		sql.append(" 			sum(t.other_oceania_one),  ");
		sql.append(" 			sum(t.southafrica_one),  ");
		sql.append(" 			sum(t.egypt_one),  ");
		sql.append(" 			sum(t.kenya_one),  ");
		sql.append(" 			sum(t.africaother_one)  ");
		sql.append(" 		FROM  ");
		sql.append(" 			t_travelagency_quarterly1 t  ");
		sql.append(" 		LEFT JOIN t_travelagency_info a ON t.tid = a.id  ");
		
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) GROUP BY t.tid  ");

		ExportService emds = new ExportService(2,38);
		
		String excelName = "入境旅游目的地国家外联人次统计表（按旅行社）.xls";
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
	 * 入境旅游目的地国家外联人次统计表（按地区）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryWailianPeopleTimesByArea")
	public void entryWailianPeopleTimesByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" 		SELECT  ");
		sql.append(" 			'三亚市', ");
		sql.append(" 			sum(t.hk_com_one),  ");
		sql.append(" 			sum(t.mc_com_one),  ");
		sql.append(" 			sum(t.tw_com_one),  ");
		sql.append(" 			sum(t.japan_one),  ");
		sql.append(" 			sum(t.korea_one),  ");
		sql.append(" 			sum(t.mongo_one),  ");
		sql.append(" 			sum(t.indonxy_one),  ");
		sql.append(" 			sum(t.malaxy_one),  ");
		sql.append(" 			sum(t.philipn_one),  ");
		sql.append(" 			sum(t.singapore_one),  ");
		sql.append(" 			sum(t.tailand_one),  ");
		sql.append(" 			sum(t.india_one),  ");
		sql.append(" 			sum(t.vietnam_one),  ");
		sql.append(" 			sum(t.myanmar_one),  ");
		sql.append(" 			sum(t.other_asian_one),  ");
		sql.append(" 			sum(t.denmark_one),  ");
		sql.append(" 			sum(t.holland_one),  ");
		sql.append(" 			sum(t.england_one),  ");
		sql.append(" 			sum(t.french_one),  ");
		sql.append(" 			sum(t.germany_one),  ");
		sql.append(" 			sum(t.italy_one),  ");
		sql.append(" 			sum(t.swiss_one),  ");
		sql.append(" 			sum(t.swedish_one),  ");
		sql.append(" 			sum(t.russia_one),  ");
		sql.append(" 			sum(t.spain_one),  ");
		sql.append(" 			sum(t.other_eropean_one),  ");
		sql.append(" 			sum(t.us_one),  ");
		sql.append(" 			sum(t.canada_one),  ");
		sql.append(" 			sum(t.other_american_one),  ");
		sql.append(" 			sum(t.australia_one),  ");
		sql.append(" 			sum(t.newland_one),  ");
		sql.append(" 			sum(t.other_oceania_one),  ");
		sql.append(" 			sum(t.southafrica_one),  ");
		sql.append(" 			sum(t.egypt_one),  ");
		sql.append(" 			sum(t.kenya_one),  ");
		sql.append(" 			sum(t.africaother_one)  ");
		sql.append(" 		FROM  ");
		sql.append(" 			t_travelagency_quarterly1 t  ");

		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))   ");

		ExportService emds = new ExportService(2,37);
		
		String excelName = "入境旅游目的地国家外联人次统计表（按地区）.xls";
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
	 * 入境旅游目的地国家接待人次统计表（按地区）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryJiedaiPeopleTimesByArea")
	public void entryJiedaiPeopleTimesByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" 		SELECT  ");
		sql.append(" 			'三亚市', ");
		sql.append(" 			sum(t.hk_com_two),  ");
		sql.append(" 			sum(t.mc_com_two),  ");
		sql.append(" 			sum(t.tw_com_two),  ");
		sql.append(" 			sum(t.japan_two),  ");
		sql.append(" 			sum(t.korea_two),  ");
		sql.append(" 			sum(t.mongo_two),  ");
		sql.append(" 			sum(t.indonxy_two),  ");
		sql.append(" 			sum(t.malaxy_two),  ");
		sql.append(" 			sum(t.philipn_two),  ");
		sql.append(" 			sum(t.singapore_two),  ");
		sql.append(" 			sum(t.tailand_two),  ");
		sql.append(" 			sum(t.india_two),  ");
		sql.append(" 			sum(t.vietnam_two),  ");
		sql.append(" 			sum(t.myanmar_two),  ");
		sql.append(" 			sum(t.other_asian_two),  ");
		sql.append(" 			sum(t.denmark_two),  ");
		sql.append(" 			sum(t.holland_two),  ");
		sql.append(" 			sum(t.england_two),  ");
		sql.append(" 			sum(t.french_two),  ");
		sql.append(" 			sum(t.germany_two),  ");
		sql.append(" 			sum(t.italy_two),  ");
		sql.append(" 			sum(t.swiss_two),  ");
		sql.append(" 			sum(t.swedish_two),  ");
		sql.append(" 			sum(t.russia_two),  ");
		sql.append(" 			sum(t.spain_two),  ");
		sql.append(" 			sum(t.other_eropean_two),  ");
		sql.append(" 			sum(t.us_two),  ");
		sql.append(" 			sum(t.canada_two),  ");
		sql.append(" 			sum(t.other_american_two),  ");
		sql.append(" 			sum(t.australia_two),  ");
		sql.append(" 			sum(t.newland_two),  ");
		sql.append(" 			sum(t.other_oceania_two),  ");
		sql.append(" 			sum(t.southafrica_two),  ");
		sql.append(" 			sum(t.egypt_two),  ");
		sql.append(" 			sum(t.kenya_two),  ");
		sql.append(" 			sum(t.africaother_two)  ");
		sql.append(" 		FROM  ");
		sql.append(" 			t_travelagency_quarterly1 t  ");

		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))   ");

		ExportService emds = new ExportService(2,37);
		
		String excelName = "入境旅游目的地国家接待人次统计表（按地区）.xls";
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
	 * 外联人次和接待人次之和排序统计（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryWailianJiedaiByTravelagency")
	public void entryWailianJiedaiByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	a.`name`, ");
		sql.append(" 	a.licensenum, ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_one) + sum(t.mc_com_one) + sum(t.tw_com_one) + sum(t.japan_one) + sum(t.korea_one) + sum(t.mongo_one) + sum(t.indonxy_one) + sum(t.malaxy_one) + sum(t.philipn_one) + sum(t.singapore_one) + sum(t.tailand_one) + sum(t.india_one) + sum(t.vietnam_one) + sum(t.myanmar_one) + sum(t.other_asian_one) + sum(t.denmark_one) + sum(t.holland_one) + sum(t.england_one) + sum(t.french_one) + sum(t.germany_one) + sum(t.italy_one) + sum(t.swiss_one) + sum(t.swedish_one) + sum(t.russia_one) + sum(t.spain_one) + sum(t.other_eropean_one) + sum(t.us_one) + sum(t.canada_one) + sum(t.other_american_one) + sum(t.australia_one) + sum(t.newland_one) + sum(t.other_oceania_one) + sum(t.southafrica_one) + sum(t.egypt_one) + sum(t.kenya_one) + sum(t.africaother_one) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `外联`, ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_two) + sum(t.mc_com_two) + sum(t.tw_com_two) + sum(t.japan_two) + sum(t.korea_two) + sum(t.mongo_two) + sum(t.indonxy_two) + sum(t.malaxy_two) + sum(t.philipn_two) + sum(t.singapore_two) + sum(t.tailand_two) + sum(t.india_two) + sum(t.vietnam_two) + sum(t.myanmar_two) + sum(t.other_asian_two) + sum(t.denmark_two) + sum(t.holland_two) + sum(t.england_two) + sum(t.french_two) + sum(t.germany_two) + sum(t.italy_two) + sum(t.swiss_two) + sum(t.swedish_two) + sum(t.russia_two) + sum(t.spain_two) + sum(t.other_eropean_two) + sum(t.us_two) + sum(t.canada_two) + sum(t.other_american_two) + sum(t.australia_two) + sum(t.newland_two) + sum(t.other_oceania_two) + sum(t.southafrica_two) + sum(t.egypt_two) + sum(t.kenya_two) + sum(t.africaother_two) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `接待` ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly1 t ");
		sql.append(" LEFT JOIN t_travelagency_info a ON a.ID = t.tid ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))   ");
		sql.append(" GROUP BY ");
		sql.append(" 	t.tid ");
		
		ExportService emds = new ExportService(2,4);
		
		String excelName = "外联人次和接待人次之和统计表（按旅行社）.xls";
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
	 * 外联人天和接待人天之和统计表（按地区）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryWailianJiedaiByArea")
	public void entryWailianJiedaiByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'三亚市', ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_three) + sum(t.mc_com_three) + sum(t.tw_com_three) + sum(t.foreigners_three) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `外联`, ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_four) + sum(t.mc_com_four) + sum(t.tw_com_four) + sum(t.foreigners_four) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `接待` ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly1 t ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))   ");
		
		ExportService emds = new ExportService(2,3);
		
		String excelName = "外联人天和接待人天之和统计表（按地区）.xls";
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
	 * 外联人天和接待人天之和排序统计（按旅行社）
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryWailianJiedaiPeoleDayByTravelagency")
	public void entryWailianJiedaiPeoleDayByTravelagency(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	a.licensenum, ");
		sql.append(" 	a.`name`, ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_three) + sum(t.mc_com_three) + sum(t.tw_com_three) + sum(t.foreigners_three) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `外联`, ");
		sql.append(" 	cast( ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.hk_com_four) + sum(t.mc_com_four) + sum(t.tw_com_four) + sum(t.foreigners_four) ");
		sql.append(" 		) AS CHAR ");
		sql.append(" 	) `接待` ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly1 t ");
		sql.append(" LEFT JOIN t_travelagency_info a ON t.tid = a.id ");
		sql.append(" where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+")))   ");
		sql.append(" GROUP BY ");
		sql.append(" 	t.tid ");
		
		ExportService emds = new ExportService(2,4);
		
		String excelName = "外联人天和接待人天之和统计表（按旅行社）.xls";
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
	 * 入境旅游各洲人次统计
	 * @author Fp
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "entryWailianJiedaiByRegion")
	public void entryWailianJiedaiByRegion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startYear = request.getParameter("startYear");
		String startQuarter = request.getParameter("startQuarter");
		String endYear = request.getParameter("endYear");
		String endQuarter = request.getParameter("endQuarter");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	'亚洲小计', ");
		sql.append(" 	( ");
		sql.append(" 		sum(t.japan_one) + sum(t.korea_one) + sum(t.mongo_one) + sum(t.indonxy_one) + sum(t.malaxy_one) + sum(t.philipn_one) + sum(t.singapore_one) + sum(t.tailand_one) + sum(t.india_one) + sum(t.vietnam_one) + sum(t.myanmar_one) + sum(t.other_asian_one) ");
		sql.append(" 	) `外联`, ");
		sql.append(" 	( ");
		sql.append(" 		sum(t.japan_two) + sum(t.korea_two) + sum(t.mongo_two) + sum(t.indonxy_two) + sum(t.malaxy_two) + sum(t.philipn_two) + sum(t.singapore_two) + sum(t.tailand_two) + sum(t.india_two) + sum(t.vietnam_two) + sum(t.myanmar_two) + sum(t.other_asian_two) ");
		sql.append(" 	) `接待` ");
		sql.append(" FROM ");
		sql.append(" 	t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" UNION ALL ");
		sql.append(" 	SELECT ");
		sql.append(" 		'欧洲小计', ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.denmark_one) + sum(t.holland_one) + sum(t.england_one) + sum(t.french_one) + sum(t.germany_one) + sum(t.italy_one) + sum(t.swiss_one) + sum(t.swedish_one) + sum(t.russia_one) + sum(t.spain_one) + sum(t.other_eropean_one) ");
		sql.append(" 		) `外联`, ");
		sql.append(" 		( ");
		sql.append(" 			sum(t.denmark_two) + sum(t.holland_two) + sum(t.england_two) + sum(t.french_two) + sum(t.germany_two) + sum(t.italy_two) + sum(t.swiss_two) + sum(t.swedish_two) + sum(t.russia_two) + sum(t.spain_two) + sum(t.other_eropean_two) ");
		sql.append(" 		) `接待` ");
		sql.append(" 	FROM ");
		sql.append(" 		t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 	UNION ALL ");
		sql.append(" 		SELECT ");
		sql.append(" 			'美洲小计', ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.us_one) + sum(t.canada_one) + sum(t.other_american_one) ");
		sql.append(" 			) `外联`, ");
		sql.append(" 			( ");
		sql.append(" 				sum(t.us_two) + sum(t.canada_two) + sum(t.other_american_two) ");
		sql.append(" 			) `接待` ");
		sql.append(" 		FROM ");
		sql.append(" 			t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 		UNION ALL ");
		sql.append(" 			SELECT ");
		sql.append(" 				'大洋洲小计', ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.australia_one) + sum(t.newland_one) + sum(t.other_oceania_one) ");
		sql.append(" 				) `外联`, ");
		sql.append(" 				( ");
		sql.append(" 					sum(t.australia_two) + sum(t.newland_two) + sum(t.other_oceania_two) ");
		sql.append(" 				) `接待` ");
		sql.append(" 			FROM ");
		sql.append(" 				t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 			UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'非洲小计', ");
		sql.append(" 					( ");
		sql.append(" 						sum(t.southafrica_one) + sum(t.egypt_one) + sum(t.kenya_one) + sum(t.africaother_one) ");
		sql.append(" 					) `外联`, ");
		sql.append(" 					( ");
		sql.append(" 						sum(t.southafrica_two) + sum(t.egypt_two) + sum(t.kenya_two) + sum(t.africaother_two) ");
		sql.append(" 					) `接待` ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		sql.append(" 				UNION ALL ");
		sql.append(" 				SELECT ");
		sql.append(" 					'其他小计', ");
		sql.append(" 					SUM(t.total_other_one), ");
		sql.append(" 					SUM(t.total_other_two) ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_quarterly1 t where 1=1 and ((t.`year` < "+endYear+" or (t.`year`="+endYear+" and t.`quarter` <= "+endQuarter+")) and (t.`year`>"+startYear+" or (t.`year`="+startYear+" and t.`quarter` >= "+startQuarter+"))) ");
		
		ExportService emds = new ExportService(2,3);
		
		String excelName = "旅行社入境游各洲人次统计.xls";
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
	 * @return: 按地区统计入境各大洲接待人次
	 */
	@RequestMapping(params = "intbyarea")
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
	/**
	 * 
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:入境旅游目的地国家外联人次统计表（按地区 单位:人次)
	 */
	@RequestMapping(params = "inAimbyareaOut")
	public void inaimByAreaOutreach( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hk_com_two) as sumhongkong,SUM(tq.mc_com_two) as summacau,SUM(tq.tw_com_two) as sumtaiwan,SUM(tq.japan_two) as sumjapan,"
				+"SUM(tq.korea_one) as sumkorea,SUM(tq.mongo_one) as sumMongolia,SUM(tq.indonxy_one) as sumIndonesia,SUM(tq.malaxy_one) as sumMalaysia,"
				+"SUM(tq.philipn_one) as sumPhilippines,SUM(tq.singapore_one) as sumSingapore,SUM(tq.tailand_one) as sumThailand,SUM(tq.india_one) as sumIndia,SUM(tq.vietnam_one) as sumVietnam,"
				+"SUM(tq.myanmar_one) as sumBurma,SUM(tq.other_asian_one) as sumAsianOther,SUM(tq.england_one) as sumenglish,SUM(tq.french_one) as sumFrance,"
				+"SUM(tq.germany_one) as sumGermany,SUM(tq.italy_one) as sumItaly,SUM(tq.swiss_one) as sumSwitzerland,SUM(tq.swedish_one) as sumSweden,"
				+"SUM(tq.russia_one) as sumRussia,SUM(tq.spain_one) as sumSpain,SUM(tq.holland_one) as sumHolland,SUM(tq.denmark_one) as sumDenmark,"
				+"SUM(tq.other_eropean_one) as sumEuropeother,SUM(tq.us_one) as sumus,SUM(tq.canada_one) as sumcanada,SUM(tq.other_american_one) as sumUsOther,SUM(tq.australia_one) as sumAustralian,"
				+"SUM(tq.newland_one) as sumZealand,SUM(tq.other_oceania_one) as sumOceaniaother,SUM(tq.southafrica_one) as sumSouthAfrica,SUM(tq.egypt_one) as sumEgypt,"
				+"SUM(tq.kenya_one) as sumKenya,SUM(tq.africaother_one) as sumAfricaother,SUM(tq.total_other_one) as sumOthertotal "
       +"FROM `t_travelAgency_quarterly1` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
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
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 
	 * @date: 2017年1月10日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:入境旅游目的地国家接待人次统计表（按地区 单位:人次)
	 */
	@RequestMapping(params = "inAimbyareaRec")
	public void inaimByAreaRecep( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hk_com_two) as sumhongkong,SUM(tq.mc_com_two) as summacau,SUM(tq.tw_com_two) as sumtaiwan,SUM(tq.japan_two) as sumjapan,"
				+"SUM(tq.korea_two) as sumkorea,SUM(tq.mongo_two) as sumMongolia,SUM(tq.indonxy_two) as sumIndonesia,SUM(tq.malaxy_two) as sumMalaysia,"
				+"SUM(tq.philipn_two) as sumPhilippines,SUM(tq.singapore_two) as sumSingapore,SUM(tq.tailand_two) as sumThailand,SUM(tq.india_two) as sumIndia,SUM(tq.vietnam_two) as sumVietnam,"
				+"SUM(tq.myanmar_two) as sumBurma,SUM(tq.other_asian_two) as sumAsianOther,SUM(tq.england_two) as sumenglish,SUM(tq.french_two) as sumFrance,"
				+"SUM(tq.germany_two) as sumGermany,SUM(tq.italy_two) as sumItaly,SUM(tq.swiss_two) as sumSwitzerland,SUM(tq.swedish_two) as sumSweden,"
				+"SUM(tq.russia_two) as sumRussia,SUM(tq.spain_two) as sumSpain,SUM(tq.holland_two) as sumHolland,SUM(tq.denmark_two) as sumDenmark,"
				+"SUM(tq.other_eropean_two) as sumEuropeother,SUM(tq.us_two) as sumus,SUM(tq.canada_two) as sumcanada,SUM(tq.other_american_two) as sumUsOther,SUM(tq.australia_two) as sumAustralian,"
				+"SUM(tq.newland_two) as sumZealand,SUM(tq.other_oceania_two) as sumOceaniaother,SUM(tq.southafrica_two) as sumSouthAfrica,SUM(tq.egypt_two) as sumEgypt,"
				+"SUM(tq.kenya_two) as sumKenya,SUM(tq.africaother_two) as sumAfricaother,SUM(tq.total_other_two) as sumOthertotal "
       +"FROM `t_travelAgency_quarterly1` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
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
	 * @return:返回入境旅游目的地国家人次统计表（按旅行社）
	 */
	@RequestMapping(params = "inlistByTravel")
	public void inlistByTravel(TravelQuarterIn travelQuarterIn,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterIn.class,
				dataGrid);
		String licensenumQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getName();
		String yearQuery = travelQuarterIn.getYear();
		String quarterQuery = travelQuarterIn.getQuarter();
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
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterIn);
		
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 
	 * @date: 2017年1月12日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:入境旅游目的地国家外联人次统计表（按地区,单位,人次）（报送）
	 */
	@RequestMapping(params = "inAimbyareaWaiLian")
	public void outAimdatagridDay( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hk_com_three) as sumhongkong,SUM(tq.mc_com_three) as summacau,SUM(tq.tw_com_three) as sumtaiwan,SUM(tq.japan_three) as sumjapan,"
				+"SUM(tq.korea_three) as sumkorea,SUM(tq.mongo_three) as sumMongolia,SUM(tq.indonxy_three) as sumIndonesia,SUM(tq.malaxy_three) as sumMalaysia,"
				+"SUM(tq.philipn_three) as sumPhilippines,SUM(tq.singapore_three) as sumSingapore,SUM(tq.tailand_three) as sumThailand,SUM(tq.india_three) as sumIndia,SUM(tq.vietnam_three) as sumVietnam,"
				+"SUM(tq.myanmar_three) as sumBurma,SUM(tq.other_asian_three) as sumAsianOther,SUM(tq.england_three) as sumenglish,SUM(tq.french_three) as sumFrance,"
				+"SUM(tq.germany_three) as sumGermany,SUM(tq.italy_three) as sumItaly,SUM(tq.swiss_three) as sumSwitzerland,SUM(tq.swedish_three) as sumSweden,"
				+"SUM(tq.russia_three) as sumRussia,SUM(tq.spain_three) as sumSpain,SUM(tq.holland_three) as sumHolland,SUM(tq.denmark_three) as sumDenmark,"
				+"SUM(tq.other_eropean_three) as sumEuropeother,SUM(tq.us_three) as sumus,SUM(tq.canada_three) as sumcanada,SUM(tq.other_american_three) as sumUsOther,SUM(tq.australia_three) as sumAustralian,"
				+"SUM(tq.newland_three) as sumZealand,SUM(tq.other_oceania_three) as sumOceaniaother,SUM(tq.southafrica_three) as sumSouthAfrica,SUM(tq.egypt_three) as sumEgypt,"
				+"SUM(tq.kenya_three) as sumKenya,SUM(tq.africaother_three) as sumAfricaother,SUM(tq.total_other_three) as sumOthertotal "
       +"FROM `t_travelAgency_quarterly1` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
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
			sql += "order by "+getParam(request,"scenic_type");*/
		;
		List list = systemService.findForJdbc(sql);
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 
	 * @date: 2017年1月12日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:外联人天和接待人天之和统计（按地区）(报送)
	 */
	@RequestMapping(params = "inOuteachRecp")
	public void inOutreachAndRecep( HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {		

		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.in_guest_three) as sumOutreach,SUM(tq.in_guest_four) as sumRecept "

       +"FROM `t_travelAgency_quarterly1` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
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
			sql += "order by "+getParam(request,"scenic_type");*/
		;
		List list = systemService.findForJdbc(sql);
				
		dataGrid.setResults(list);
		
		dataGrid.setTotal(list.size());
		
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 
	 * @date: 2017年1月11日
	 * @Author: 龙亚辉
	 * @Email: 502230926@qq.com
	 * @param: 
	 * @return:返回入境旅游目的地国家人天统计表（按旅行社）
	 */
	@RequestMapping(params = "outAimByTravel")
	public void inAimByTravel(TravelQuarterIn travelQuarterIn,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(TravelQuarterIn.class,
				dataGrid);
		String licensenumQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getLicensenum();
		String nameQuery = travelQuarterIn.getTraveldata() == null ? "" : travelQuarterIn.getTraveldata().getName();
		String yearQuery = travelQuarterIn.getYear();
		String quarterQuery = travelQuarterIn.getQuarter();
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
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,travelQuarterIn);
		
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
	}
	//**************************入境统计********************************************
	//入境旅游目的地国家接待人次统计表（按旅行社）
	@RequestMapping(params = "exportsxls1")
	public String exportXls1(TravelQuarterIn travelQuarterIn,HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.TravelQuarterIn tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<TravelQuarterIn> travelQuarterIns = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"入境旅游目的地国家接待人次统计表（按旅行社）");
	    map.put(NormalExcelConstants.CLASS,TravelQuarterIn.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("入境旅游目的地国家接待人次统计表（按旅行社）", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,travelQuarterIns);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	@RequestMapping(params = "exportsxls2")
	public String exportXls2(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterInOreach tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterInOreach> sTQuarterInOreachs = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"入境旅游目的地国家外联人次统计表（按旅行社）");
	    map.put(NormalExcelConstants.CLASS,STQuarterInOreach.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("入境旅游目的地国家外联人次统计表（按旅行社）", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterInOreachs);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	@RequestMapping(params = "exportsxls3")
	public String exportXls3(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) {
		
		String hql="from com.zzc.web.travel.entity.STQuarterOIC tq ";
		if (StringUtil.isNotEmpty(getParam(request,"year"))) {
			hql += " where tq.year="+getParam(request,"year")+"  ";
		}
		if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
			hql += "and  tq.quarter="+getParam(request,"quarter")+" ";
		}
	    List<STQuarterOIC> sTQuarterOICs = this.systemService.findHql(hql,null);
	    map.put(NormalExcelConstants.FILE_NAME,"外联人天和接待人天之和统计表（按旅行社）");
	    map.put(NormalExcelConstants.CLASS,STQuarterOIC.class);
	    map.put(NormalExcelConstants.PARAMS,new ExportParams("外联人天和接待人天之和统计表（按旅行社）", "导出人:管理员",
	            "导出信息"));
	    map.put(NormalExcelConstants.DATA_LIST,sTQuarterOICs);
	    return NormalExcelConstants.JEECG_EXCEL_VIEW;
	
	}
	//入境旅游外联人次（按目的地国家）
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportsxls4")
	public String exportXls4(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) throws Exception {
		Calendar calendar = Calendar.getInstance();	
		String sql = "SELECT  tr.area as area,tq.year as year,SUM(tq.hk_com_two) as sumhongkong,SUM(tq.mc_com_two) as summacau,SUM(tq.tw_com_two) as sumtaiwan,SUM(tq.japan_two) as sumjapan,"
				+"SUM(tq.korea_one) as sumkorea,SUM(tq.mongo_one) as sumMongolia,SUM(tq.indonxy_one) as sumIndonesia,SUM(tq.malaxy_one) as sumMalaysia,"
				+"SUM(tq.philipn_one) as sumPhilippines,SUM(tq.singapore_one) as sumSingapore,SUM(tq.tailand_one) as sumThailand,SUM(tq.india_one) as sumIndia,SUM(tq.vietnam_one) as sumVietnam,"
				+"SUM(tq.myanmar_one) as sumBurma,SUM(tq.other_asian_one) as sumAsianOther,SUM(tq.england_one) as sumenglish,SUM(tq.french_one) as sumFrance,"
				+"SUM(tq.germany_one) as sumGermany,SUM(tq.italy_one) as sumItaly,SUM(tq.swiss_one) as sumSwitzerland,SUM(tq.swedish_one) as sumSweden,"
				+"SUM(tq.russia_one) as sumRussia,SUM(tq.spain_one) as sumSpain,SUM(tq.holland_one) as sumHolland,SUM(tq.denmark_one) as sumDenmark,"
				+"SUM(tq.other_eropean_one) as sumEuropeother,SUM(tq.us_one) as sumus,SUM(tq.canada_one) as sumcanada,SUM(tq.other_american_one) as sumUsOther,SUM(tq.australia_one) as sumAustralian,"
				+"SUM(tq.newland_one) as sumZealand,SUM(tq.other_oceania_one) as sumOceaniaother,SUM(tq.southafrica_one) as sumSouthAfrica,SUM(tq.egypt_one) as sumEgypt,"
				+"SUM(tq.kenya_one) as sumKenya,SUM(tq.africaother_one) as sumAfricaother,SUM(tq.total_other_one) as sumOthertotal "
       +"FROM `t_travelAgency_quarterly1` tq  LEFT JOIN t_travelAgency_info tr ON tq.tid=tr.id "
				+ " where  1=1 ";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
				sql += " and  tq.`year`="+getParam(request,"year")+"  ";
			}else{
				sql += " and  tq.`year` = '"+calendar.get(Calendar.YEAR)+"' ";
			}	
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
				sql += " and tq.`quarter`="+getParam(request,"quarter")+"  ";
			}	else{
//				sql += " tq.`quarter`=2 ";
			}
			OutputStream os = response.getOutputStream();
			String filename="入境旅游目的地国家外联人次统计表（按地区）.xls";
			String newFileName="年第某入境旅游目的地国家外联人次统计表（按地区）.xls";
			String fileTime = "";
			if (StringUtil.isNotEmpty(getParam(request,"year"))) {
//				filename =(getParam(request,"year")+filename);
				fileTime = getParam(request,"year")+"年";
			}else{
//				filename="2014"+filename;
				fileTime = calendar.get(Calendar.YEAR) + "年";
			}
				
			if (StringUtil.isNotEmpty(getParam(request,"quarter"))) {
//				newFileName = filename.replace("某", getParam(request,"quarter"));
				fileTime = fileTime + "第"+getParam(request,"quarter")+"季度";
			}else{
//				newFileName = filename.replace("某", "");
//				newFileName = 
			}
			filename = fileTime + filename;
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			
			response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(filename,"UTF-8"));// 设置文件名
			Map<String, Object> parmass = this.systemService.findOneForJdbc(sql, null);

			
			HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  "/com/zzc/web/export/model/出境旅游人次倒序排列（按目的地国家）.xls", parmass);
			wb.getSheetAt(0).getRow(0).getCell(0).setCellValue(fileTime+"入境旅游目的地国家外联人次统计表（按地区）");
			wb.write(os);
		    return null;
		
		}
}
