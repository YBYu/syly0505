package com.zzc.web.htoal.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzc.core.util.ResourceUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.web.export.ExportService;
import com.zzc.web.export.POIUtils;
import com.zzc.web.sylyUtils.ExplorerHelper;
import com.zzc.web.system.service.SystemService;

@RequestMapping("/hotelexportcontroller")
@Controller
public class ExportController {
	
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 
	 * 旅游饭店接待外国人分国别（地区）人数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "foreignersReception")
	public void foreignersReception(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "旅游饭店接待外国人分国别（地区）人数.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
     // 获取要查询的年
        String year = request.getParameter("year");
        // 获取要查询的月份
        String month = request.getParameter("month");
        
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH + 1));
        String bay = request.getParameter("bay");
        String star = request.getParameter("star");
        String area = request.getParameter("area");
        String areas="三亚";
        String stars="全部星级";
        String bays="所有湾区";
        if(bay!=null&&bay.length()!=0){
        	bays=getbays(bay);
         }
        if(area!=null&&area.length()!=0){
     	   areas=getarea(area);
        }
        if(star!=null&&star.length()!=0){
     	   stars=getstar(star);
        }
        
        // 获取当前登录用户ID
        String userId = ResourceUtil.getSessionUserName().getId();
        // 酒店ID与用户ID相同
        String hotelId = userId;
        StringBuffer sql = new StringBuffer();
        ExportService emds = new ExportService(4); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        sql.append(" select t.`name`,t.`month`,t.monthyear,truncate((IFNULL((t.`month`/t.monthyear)*100,0)),2) percent from ( ");
      
                sql.append(" select '合计' name,(sum(t.asian_month)+sum(t.europe_month)+sum(t.america_month)+sum(t.oceania_month)+sum(t.africa_month)+sum(t.other_month)) month,(sum(t.asian_monthyear)+sum(t.europe_monthyear)+sum(t.america_monthyear)+sum(t.oceania_monthyear)+sum(t.africa_monthyear)+sum(t.other_monthyear)) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID   WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '1.亚洲小计' name,sum(t.asian_month) month,sum(t.asian_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '日本' name,sum(t.japan_month) month,sum(t.japan_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '韩国' name,sum(t.korea_month) month,sum(t.korea_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '蒙古' name,sum(t.mongolia_month) month,sum(t.mongolia_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '印度尼西亚' name,sum(t.indonesia_month) month,sum(t.indonesia_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID   WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '马来西亚' name,sum(t.malaysia_month) month,sum(t.malaysia_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '菲律宾' name,sum(t.philippines_month) month,sum(t.philippines_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                
        		sql.append(" select '新加坡' name,sum(t.singapore_month) month,sum(t.singapore_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '泰国' name,sum(t.thailand_month) month,sum(t.thailand_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID   WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '印度' name,sum(t.india_month) month,sum(t.india_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '越南' name,sum(t.vietnam_month) month,sum(t.vietnam_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '缅甸' name,sum(t.burma_month) month,sum(t.burma_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '朝鲜' name,sum(t.northkorea_month) month,sum(t.northkorea_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '巴基斯坦' name,sum(t.pakistan_month) month,sum(t.pakistan_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '亚洲其它' name,sum(t.asianother_month) month,sum(t.asianother_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '2.欧洲小计' name,sum(t.europe_month) month,sum(t.europe_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '英国' name,sum(t.england_month) month,sum(t.england_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '法国' name,sum(t.france_month) month,sum(t.france_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID   WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month   );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '德国' name,sum(t.germany_month) month,sum(t.germany_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '意大利' name,sum(t.italy_month) month,sum(t.italy_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '瑞士' name,sum(t.switzerland_month) month,sum(t.switzerland_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '瑞典' name,sum(t.sweden_month) month,sum(t.sweden_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '俄罗斯' name,sum(t.russia_month) month,sum(t.russia_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '西班牙' name,sum(t.spain_month) month,sum(t.spain_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '欧洲其它' name,sum(t.europeother_month) month,sum(t.europeother_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '3.美洲小计' name,sum(t.america_month) month,sum(t.america_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '美国' name,sum(t.usa_month) month,sum(t.usa_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '加拿大' name,sum(t.canada_month) month,sum(t.canada_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '美洲其它' name,sum(t.americaother_month) month,sum(t.americaother_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '4.大洋洲小计' name,sum(t.oceania_month) month,sum(t.oceania_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '澳大利亚' name,sum(t.australia_month) month,sum(t.australia_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '新西兰' name,sum(t.nz_month) month,sum(t.nz_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month);
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '大洋洲其它' name,sum(t.oceaniaother_month) month,sum(t.oceaniaother_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID   WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month );
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}	
        			sql.append(" union all ");
                sql.append(" select '5.非洲小计' name,sum(t.africa_month) month,sum(t.africa_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month  );
                if(bay!=null&&bay.length()!=0){
            		sql.append(" And h.bay_type="+bay);
            	}if(star!=null&&star.length()!=0){
            		sql.append(" And h.housegrade="+star);
            	}if(area!=null&&area.length()!=0){
            		sql.append(" And h.w_county="+area);
            	}	
    			sql.append(" union all ");
                sql.append(" select '6.其它小计' name,sum(t.other_month) month,sum(t.other_monthyear) monthyear from t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  WHERE 1=1 AND t.`year`="+year+" AND t.`month`="+month); 
        		if(bay!=null&&bay.length()!=0){
                		sql.append(" And h.bay_type="+bay);
                	}if(star!=null&&star.length()!=0){
                		sql.append(" And h.housegrade="+star);
                	}if(area!=null&&area.length()!=0){
                		sql.append(" And h.w_county="+area);
                	}
        		sql.append(" ) t ");
        // 根据数据表,页面查询条件, 抓取数据库中对应的数据
        System.out.println(sql.toString());
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
						+ new String((areas+year+"年第"+month+"月"+bays+stars+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(areas+year+"年第"+month+"月"+bays+stars+excelName, "UTF-8"));
	        }
			workbook.setSheetName(0, areas+year+"年第"+month+"月"+bays+stars+excelName);
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
	 * (三亚市)房间出租率汇总
	 * @param year 年，month 月
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "occupancyRate")
	public void occupancyRate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "(三亚市)房间出租率汇总.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        
        // 获取要查询的年
        String year = request.getParameter("year");
        // 获取要查询的月份
        String month = request.getParameter("month");
        String bay = request.getParameter("bay");
        String star = request.getParameter("star");
        String area = request.getParameter("area");
        String areas="三亚";
        String stars="全部星级";
        String bays="所有湾区";
        if(bay!=null&&bay.length()!=0){
        	bays=getbays(bay);
         }
       if(area!=null&&area.length()!=0){
    	   areas=getarea(area);
       }
       if(star!=null&&star.length()!=0){
    	   stars=getstar(star);
       }
       
        
        
        Calendar calendar = Calendar.getInstance();
        if(year.equals(String.valueOf(calendar.get(Calendar.YEAR)))){
        	 if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH )+1);
        }
       
        
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlTotal = new StringBuffer();
        ExportService emds = new ExportService(5); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理
        /*if(area != null || area.length() != 0){
     	   areas=getarea(area);
     	  for (int i = 1; i <= Integer.parseInt(month); i++) {
          	sql.append(" SELECT ");
          	sql.append(" 	'"+i+"月' MONTH, ");
          	sql.append(" 	'"+areas+"' cityname, ");
          	sql.append(" 	IFNULL(sum(t.actual_rentnum),0) actualrent, ");
          	sql.append(" 	IFNULL(sum(t.rent_num),0) rentnum, ");
          	sql.append(" 	TRUNCATE ( ");
          	sql.append(" 		( ");
          	sql.append(" 			IFNULL( ");
          	sql.append(" 				( ");
          	sql.append(" 					sum(t.actual_rentnum) / sum(t.rent_num) ");
          	sql.append(" 				) * 100, ");
          	sql.append(" 				0 ");
          	sql.append(" 			) ");
          	sql.append(" 		), ");
          	sql.append(" 		2 ");
          	sql.append(" 	) percent ");
          	sql.append(" FROM ");
          	sql.append(" 	t_hotelmonthly t ");
          	sql.append(" WHERE ");
          	sql.append(" 	t.`year` = '"+year+"' ");
          	//sql.append(" AND t.`month` = '"+i+"' ");
          	sql.append(" AND t.`month` = '"+i+"' ");
          	// 判断是否为最后一个月份
          	if(i != Integer.parseInt(month)) sql.append(" union all ");
  		}
        }else{*/
        for (int i = 1; i <= Integer.parseInt(month); i++) {
        	sql.append(" SELECT ");
        	sql.append(" 	'"+i+"月' MONTH, ");
        	sql.append(" 	'"+areas+"' cityname, ");
/*        	sql.append("if(h.w_county='0','市辖区',if(h.w_county='1','吉阳区',if(h.w_county='2','崖州区',if(h.w_county='3','天涯区',if(h.w_county='4','海棠区','未填写'))))),");
*/        	sql.append(" 	IFNULL(sum(t.actual_rentnum),0) actualrent, ");
        	sql.append(" 	IFNULL(sum(t.rent_num),0) rentnum, ");
        	sql.append(" 	TRUNCATE ( ");
        	sql.append(" 		( ");
        	sql.append(" 			IFNULL( ");
        	sql.append(" 				( ");
        	sql.append(" 					sum(t.actual_rentnum) / sum(t.rent_num) ");
        	sql.append(" 				) * 100, ");
        	sql.append(" 				0 ");
        	sql.append(" 			) ");
        	sql.append(" 		), ");
        	sql.append(" 		2 ");
        	sql.append(" 	) percent ");
        	sql.append(" FROM ");
        	sql.append(" 	t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        	sql.append(" WHERE ");
        	sql.append(" 	t.`year` = '"+year+"' ");
        	sql.append(" AND t.`month` = '"+i+"' ");
        	if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        	// 判断是否为最后一个月份
        	if(i != Integer.parseInt(month)) sql.append(" union all ");
		}
        
        
        sqlTotal.append(" SELECT ");
        sqlTotal.append(" 	'总计' MONTH, ");
        sqlTotal.append(" 	'' cityname,");
        sqlTotal.append(" 	sum(t.actualrent) actualrent, ");
        sqlTotal.append(" 	sum(t.rentnum) rentnum, ");
        sqlTotal.append(" 	TRUNCATE ( ");
        sqlTotal.append(" 		( ");
        sqlTotal.append(" 			IFNULL( ");
        sqlTotal.append(" 				( ");
        sqlTotal.append(" 					sum(t.actualrent) / sum(t.rentnum) ");
        sqlTotal.append(" 				) * 100, ");
        sqlTotal.append(" 				0 ");
        sqlTotal.append(" 			) ");
        sqlTotal.append(" 		), ");
        sqlTotal.append(" 		2 ");
        sqlTotal.append(" 	) percent ");
        sqlTotal.append(" FROM ");
        sqlTotal.append(" 	( ");
        sqlTotal.append(new String(sql));
        sqlTotal.append(" ) t ");
        
        sql.append(" union all");
        sql.append(new String(sqlTotal));

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
						+ new String((areas+year+"年第"+month+"月"+bays+stars+"房间出租率汇总.xls").getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode((areas+year+"年第"+month+"月"+bays+stars+"房间出租率汇总.xls"), "UTF-8"));
	        }
			workbook.setSheetName(0, areas+year+"年第"+month+"月"+bays+stars+"房间出租率汇总.xls");
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
	 * 地方接待过夜游客情况汇总
	 * @param year 年，month 月
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "overnightCollect")
	public void overnightCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "地方接待过夜游客情况汇总.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        
        // 获取要查询的年
        String year = request.getParameter("year");
        // 获取要查询的月份
        String month = request.getParameter("month");
        String bay = request.getParameter("bay");
        String star = request.getParameter("star");
        String area = request.getParameter("area");
        String areas="三亚";
        String stars="全部星级";
        String bays="所有湾区";
        if(bay!=null&&bay.length()!=0){
        	bays=getbays(bay);
         }
        if(area!=null&&area.length()!=0){
     	   areas=getarea(area);
        }
        if(star!=null&&star.length()!=0){
     	   stars=getstar(star);
        }
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
        
        StringBuffer sql = new StringBuffer();
        ExportService emds = new ExportService(5); // 数字代表excel表格的列数,只导出1列时会有bug,暂时不做处理

        sql.append(" SELECT ");
        sql.append(" 	'一、国内过夜游客' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.inland_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t ");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}	
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.inland_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.inland_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'二、入境过夜游客' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.entry_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.entry_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.entry_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(一)香港同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.hongkong_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.hongkong_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.hongkong_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.hongkong_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.hongkong_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.hongkong_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(二)澳门同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.macau_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.macau_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.macau_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(三)台湾同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.taiwan_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.taiwan_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.taiwan_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(四)外国人' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthtime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthtime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.foreign_monthtime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.foreign_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.foreign_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" select '1' type,'1' t1,'1' t2,'1' t3,'1' t4 from dual union all ");
        sql.append(" SELECT ");
        sql.append(" 	'一、国内过夜游客' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.inland_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.inland_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.inland_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.inland_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'二、入境过夜游客' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.entry_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.entry_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.entry_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(一)香港同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.entry_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.entry_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.hongkong_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.hongkong_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.hongkong_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(二)澳门同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.macau_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.macau_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.macau_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.macau_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(三)台湾同胞' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.taiwan_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.taiwan_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.taiwan_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.taiwan_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" union all ");
        sql.append(" SELECT ");
        sql.append(" 	'(四)外国人' type, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthyeartime), '0') AS CHAR) t1, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.foreign_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) as DECIMAL(10,2))*100 - 100 t2, ");
        sql.append(" 	CAST(IFNULL(sum(t.foreign_monthyeartime), '0') AS CHAR) t3, ");
        sql.append(" 	IFNULL(sum(t.foreign_monthyeartime), '0') / IFNULL( ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.foreign_monthyeartime) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t. YEAR = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
        sql.append(" 		), ");
        sql.append(" 		'0' ");
        sql.append(" 	) t4 ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" WHERE ");
        sql.append(" 	t. YEAR = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
        if(bay!=null&&bay.length()!=0){
    		sql.append(" And h.bay_type="+bay);
    	}if(star!=null&&star.length()!=0){
    		sql.append(" And h.housegrade="+star);
    	}if(area!=null&&area.length()!=0){
    		sql.append(" And h.w_county="+area);
    	}
    	System.out.println(sql.toString());
        
        // 根据数据表,页面查询条件, 抓取数据库中对应的数据
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row1 = sheet.getRow(0);
            row1.getCell(0).setCellValue("接待过夜游客人次数(指标)");
            row1.getCell(1).setCellValue(year+"年"+month+"月接待人数（人次）");
            row1.getCell(2).setCellValue(lastYear+"年"+month+"月同比增长（%）");
            row1.getCell(3).setCellValue(year+"年初-"+month+"月累计接待人数（人次）");
            row1.getCell(4).setCellValue(lastYear+"年初-"+month+"月累计接待人数（人次）");
            HSSFRow row2 = sheet.getRow(7);
            row2.getCell(0).setCellValue("接待过夜游客人天数(指标)");
            row2.getCell(1).setCellValue(year+"年"+month+"月接待人数（人次）");
            row2.getCell(2).setCellValue(lastYear+"年"+month+"月同比增长（%）");
            row2.getCell(3).setCellValue(year+"年初-"+month+"月累计接待人数（人次）");
            row2.getCell(4).setCellValue(lastYear+"年初-"+month+"月累计接待人数（人次）");
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
						+ new String((areas+year+"年第"+month+"月"+bays+stars+"地方接待过夜游客情况汇总.xls").getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(areas+year+"年第"+month+"月"+bays+stars+"地方接待过夜游客情况汇总.xls", "UTF-8"));

	        }
            workbook.setSheetName(0, areas+year+"年第"+month+"月"+bays+stars+"地方接待过夜游客情况汇总");

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
	 * @date: 2017年2月5日
	 * @Author:long
	 * @Email: 
	 * @param: 
	 * @return:
	 * 星级非星级住宿接待情况 
	 */
	@RequestMapping(params = "exportsxls4")
	public String exportXls4(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) throws Exception {
		StringBuffer sql = new StringBuffer();
	    // 获取要查询的年
        String year = request.getParameter("year");//今年
        // 获取要查询的月份
        String month = request.getParameter("month");//月份
        String bay = request.getParameter("bay");
        String star = request.getParameter("star");
        String area = request.getParameter("area");
        String areas="三亚";
        String stars="全部星级";
        String bays="所有湾区";
        if(bay!=null&&bay.length()!=0){
        	bays=getbays(bay);
         }

       if(area!=null&&area.length()!=0){
    	   areas=getarea(area);
       }
       if(star!=null&&star.length()!=0){
    	   stars=getstar(star);
       }
        
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
       

        sql.append(" SELECT ");
        sql.append(" 	SUM(t.total_monthtime) totalMonthTime, ");
        sql.append(" 	SUM(t.total_monthyeartime) totalMonthYearTime, ");
        sql.append(" 	SUM(t.total_monthday) totalMonthDay, ");
        sql.append(" 	SUM(t.total_monthyearday) totalMonthYearDay, ");
        sql.append(" 	SUM(t.inland_monthtime) inlandMonthTime, ");
        sql.append(" 	SUM(t.inland_monthtime) inlandMonthTime, ");
        sql.append(" 	SUM(t.inland_monthyeartime) inlandMonthYearTime, ");
        sql.append(" 	SUM(t.inland_monthday) inlandMonthDay, ");
        sql.append(" 	SUM(t.inland_monthyearday) inlandMonthYearDay, ");
        sql.append(" 	SUM(t.entry_monthtime) entryMonthTime, ");
        sql.append(" 	SUM(t.entry_monthyeartime) entryMonthYearTime, ");
        sql.append(" 	SUM(t.entry_monthday) entryMonthDay, ");
        sql.append(" 	SUM(t.entry_monthyearday) entryMonthYearDay, ");
        sql.append(" 	SUM(t.hongkong_monthtime) hkMonthTime, ");
        sql.append(" 	SUM(t.hongkong_monthyeartime) hkMonthYearTime, ");
        sql.append(" 	SUM(t.hongkong_monthday) hkonthDay, ");
        sql.append(" 	SUM(t.hongkong_monthyearday) hkMonthYearDay, ");
        sql.append(" 	SUM(t.macau_monthtime) macauMonthTime, ");
        sql.append(" 	SUM(t.macau_monthyeartime) macauMonthYearTime, ");
        sql.append(" 	SUM(t.macau_monthday) macauMonthDay, ");
        sql.append(" 	SUM(t.macau_monthyearday) macauMonthYearDay, ");
        sql.append(" 	SUM(t.taiwan_monthtime) taiwanMonthTime, ");
        sql.append(" 	SUM(t.taiwan_monthyeartime) taiwanMonthYearTime, ");
        sql.append(" 	SUM(t.taiwan_monthday) taiwanMonthDay, ");
        sql.append(" 	SUM(t.taiwan_monthyearday) taiwanMonthYearDay, ");
        sql.append(" 	SUM(t.foreign_monthtime) foreignMonthTime, ");
        sql.append(" 	SUM(t.foreign_monthyeartime) foreignMonthYearTime, ");
        sql.append(" 	SUM(t.foreign_monthday) foreignMonthDay, ");
        sql.append(" 	SUM(t.foreign_monthyearday) foreignMonthYearDay, ");
        sql.append(" 	SUM(t.asian_month) asianMonth, ");
        sql.append(" 	SUM(t.asian_monthyear) asianMonthYear, ");
        sql.append(" 	SUM(t.japan_month) japanMonth, ");
        sql.append(" 	SUM(t.japan_monthyear) japanMonthYear, ");
        sql.append(" 	SUM(t.korea_month) koreaMonth, ");
        sql.append(" 	SUM(t.korea_monthyear) koreaMonthYear, ");
        sql.append(" 	SUM(t.mongolia_month) mongoliaMonth, ");
        sql.append(" 	SUM(t.mongolia_monthyear) mongoliaMonthYear, ");
        sql.append(" 	SUM(t.indonesia_month) indonesiaMonth, ");
        sql.append(" 	SUM(t.indonesia_monthyear) indonesiaMonthYear, ");
        sql.append(" 	SUM(t.malaysia_month) malaysiaMonth, ");
        sql.append(" 	SUM(t.malaysia_monthyear) malaysiaMonthYear, ");
        sql.append(" 	SUM(t.philippines_month) philippinesMonth, ");
        sql.append(" 	SUM(t.philippines_monthyear) philippinesMonthYear, ");
        sql.append(" 	SUM(t.singapore_month) singaporeMonth, ");
        sql.append(" 	SUM(t.singapore_monthyear) singaporeMonthYear, ");
        sql.append(" 	SUM(t.thailand_month) thailandMonth, ");
        sql.append(" 	SUM(t.thailand_monthyear) thailandMonthYear, ");
        sql.append(" 	SUM(t.india_month) indiaMonth, ");
        sql.append(" 	SUM(t.india_monthyear) indiaMonthYear, ");
        sql.append(" 	SUM(t.vietnam_month) vietnamMonth, ");
        sql.append(" 	SUM(t.vietnam_monthyear) vietnamMonthYear, ");
        sql.append(" 	SUM(t.burma_month) burmaMonth, ");
        sql.append(" 	SUM(t.burma_monthyear) burmaMonthYear, ");
        sql.append(" 	SUM(t.northkorea_month) northkoreaMonth, ");
        sql.append(" 	SUM(t.northkorea_monthyear) northkoreaMonthYear, ");
        sql.append(" 	SUM(t.pakistan_month) pakistanMonth, ");
        sql.append(" 	SUM(t.pakistan_monthyear) pakistanMonthYear, ");
        sql.append(" 	SUM(t.asianother_month) asianotherMonth, ");
        sql.append(" 	SUM(t.asianother_monthyear) asianotherMonthYear, ");
        sql.append(" 	SUM(t.europe_month) europeMonth, ");
        sql.append(" 	SUM(t.europe_monthyear) europeMonthYear, ");
        sql.append(" 	SUM(t.england_month) englandMonth, ");
        sql.append(" 	SUM(t.england_monthyear) englandMonthYear, ");
        sql.append(" 	SUM(t.france_month) franceMonth, ");
        sql.append(" 	SUM(t.france_monthyear) franceMonthYear, ");
        sql.append(" 	SUM(t.germany_month) germanyMonth, ");
        sql.append(" 	SUM(t.germany_monthyear) germanyMonthYear, ");
        sql.append(" 	SUM(t.italy_month) italyMonth, ");
        sql.append(" 	SUM(t.italy_monthyear) italyMonthYear, ");
        sql.append(" 	SUM(t.switzerland_month) switzerlandMonth, ");
        sql.append(" 	SUM(t.switzerland_monthyear) switzerlandMonthYear, ");
        sql.append(" 	SUM(t.sweden_month) swedenMonth, ");
        sql.append(" 	SUM(t.sweden_monthyear) swedenMonthYear, ");
        sql.append(" 	SUM(t.russia_month) russiaMonth, ");
        sql.append(" 	SUM(t.russia_monthyear) russiaMonthYear, ");
        sql.append(" 	SUM(t.spain_month) spainMonth, ");
        sql.append(" 	SUM(t.spain_monthyear) spainMonthYear, ");
        sql.append(" 	SUM(t.europeother_month) europeotherMonth, ");
        sql.append(" 	SUM(t.europeother_monthyear) europeotherMonthYear, ");
        sql.append(" 	SUM(t.america_month) americaMonth, ");
        sql.append(" 	SUM(t.america_monthyear) americaMonthYear, ");
        sql.append(" 	SUM(t.usa_month) usaMonth, ");
        sql.append(" 	SUM(t.usa_monthyear) usaMonthYear, ");
        sql.append(" 	SUM(t.canada_month) canadaMonth, ");
        sql.append(" 	SUM(t.canada_monthyear) canadaMonthYear, ");
        sql.append(" 	SUM(t.americaother_month) americaotherMonth, ");
        sql.append(" 	SUM(t.americaother_monthyear) americaotherMonthYear, ");
        sql.append(" 	SUM(t.oceania_month) oceaniaMonth, ");
        sql.append(" 	SUM(t.oceania_monthyear) oceaniaMonthYear, ");
        sql.append(" 	SUM(t.australia_month) australiaMonth, ");
        sql.append(" 	SUM(t.australia_monthyear) australiaMonthYear, ");
        sql.append(" 	SUM(t.nz_month) nzMonth, ");
        sql.append(" 	SUM(t.nz_monthyear) nzMonthYear, ");
        sql.append(" 	SUM(t.oceaniaother_month) octherMonth, ");
        sql.append(" 	SUM(t.oceaniaother_monthyear) ocotherMonthYear, ");
        sql.append(" 	SUM(t.africa_month) africaMonth, ");
        sql.append(" 	SUM(t.africa_monthyear) africaMonthYear, ");
        sql.append(" 	SUM(t.other_month) otherMonth, ");
        sql.append(" 	SUM(t.other_monthyear) otherMonthYear ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" WHERE ");
        sql.append(" 	1 = 1 ");
			if (StringUtil.isNotEmpty(year)) {
				sql.append(" AND  t.`year`="+year+"  ");
			}	
			if (StringUtil.isNotEmpty(month)) {
				sql.append(" AND t.`month`="+month+"  ");
			}	
			if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
			System.out.println(sql.toString());
			OutputStream os = response.getOutputStream();
			String filename="星级非星级住宿接待情况.xls";
			
			
			//response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			
			response.addHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(areas+year+"年第"+month+"月"+bays+stars+filename,"UTF-8"));// 设置文件名
			List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(sql.toString(), null);
			Map<String, Object> parmass = findForJdbc.get(0);
			//Map<String, Object>  = this.systemService.findOneForJdbc(sql.toString(), null);

			
			HSSFWorkbook wb = POIUtils.replaceExcel("Sheet1",  "/com/zzc/web/export/model/星级非星级住宿接待情况.xls", parmass);
			wb.write(os);
		    return null;
		
		}
	
	
	
	/**
	 * 
	 * 地方接待过夜游客情况汇总
	 * @param year 年，month 月
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "exportsxls5")
	public void exportsxls5(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String excelName = "接待入境过夜游客分国别情况.xls";
        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
        
        // 获取要查询的年
        String year = request.getParameter("year");//今年
        // 获取要查询的月份
        String month = request.getParameter("month");//月份
        String bay = request.getParameter("bay");
        String star = request.getParameter("star");
        String area = request.getParameter("area");
        String areas="三亚";
        String stars="全部星级";
        String bays="所有湾区";
        if(bay!=null&&bay.length()!=0){
        	bays=getbays(bay);
         }

       if(area!=null&&area.length()!=0){
    	   areas=getarea(area);
       }
       if(star!=null&&star.length()!=0){
    	   stars=getstar(star);
       }
        
        Calendar calendar = Calendar.getInstance();
        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
        if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);//去年
        ExportService emds = new ExportService(1,7);//从第一行开始写,一共7列
        
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" 	'合计' `type`, ");
        sql.append(" 	sum(t.total_monthtime) `本月`, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			sum(t.total_monthtime) ");
        sql.append(" 		FROM ");
       sql.append(" 			t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
		
        sql.append(" 		WHERE ");
        sql.append(" 			t.`year` = '"+lastYear+"' ");
        sql.append(" 		AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 	) `去年本月`, ");
        sql.append(" 	round( ");
        sql.append(" 		( ");
        sql.append(" 			sum(t.total_monthtime) / ( ");
        sql.append(" 				SELECT ");
        sql.append(" 					sum(t.total_monthtime) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 				WHERE ");
        sql.append(" 					t.`year` = '"+lastYear+"' ");
        sql.append(" 				AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 			) - 1 ");
        sql.append(" 		) * 100, ");
        sql.append(" 		2 ");
        sql.append(" 	) `本月同比增长`, ");
        sql.append(" 	sum(t.total_monthyeartime) `年初至本月`, ");
        sql.append(" 	( ");
        sql.append(" 		SELECT ");
        sql.append(" 			sum(t.total_monthyeartime) ");
        sql.append(" 		FROM ");
       sql.append(" 			t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 		WHERE ");
        sql.append(" 			t.`year` = '"+lastYear+"' ");
        sql.append(" 		AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 	) `去年年初至本月`, ");
        sql.append(" 	ROUND( ");
        sql.append(" 		( ");
        sql.append(" 			sum(t.total_monthyeartime) / ( ");
        sql.append(" 				SELECT ");
        sql.append(" 					sum(t.total_monthyeartime) ");
        sql.append(" 				FROM ");
        sql.append(" 					t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 				WHERE ");
        sql.append(" 					t.`year` = '"+lastYear+"' ");
        sql.append(" 				AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 			) - 1 ");
        sql.append(" 		) * 100, ");
        sql.append(" 		2 ");
        sql.append(" 	) `年初至本月同比增长` ");
        sql.append(" FROM ");
        sql.append(" 	t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" WHERE ");
        sql.append(" 	t.`year` = '"+year+"' ");
        sql.append(" AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'1.亚洲小计' `type`, ");
        sql.append(" 		sum(t.asian_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.asian_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.asian_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.asian_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.asian_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.asian_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.asian_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.asian_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'日本' `type`, ");
        sql.append(" 		sum(t.japan_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.japan_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.japan_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.japan_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.japan_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.japan_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.japan_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.japan_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'韩国' `type`, ");
        sql.append(" 		sum(t.korea_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.korea_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.korea_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.korea_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.korea_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.korea_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.korea_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.korea_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'韩国' `type`, ");
        sql.append(" 		sum(t.korea_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.korea_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.korea_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.korea_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.korea_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.korea_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.korea_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.korea_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'蒙古' `type`, ");
        sql.append(" 		sum(t.mongolia_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.mongolia_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.mongolia_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.mongolia_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.mongolia_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.mongolia_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.mongolia_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.mongolia_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'印度尼西亚' `type`, ");
        sql.append(" 		sum(t.indonesia_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.indonesia_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.indonesia_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.indonesia_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.indonesia_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.indonesia_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.indonesia_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.indonesia_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'马来西亚' `type`, ");
        sql.append(" 		sum(t.malaysia_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.malaysia_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.malaysia_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.malaysia_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.malaysia_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.malaysia_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.malaysia_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.malaysia_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'菲律宾' `type`, ");
        sql.append(" 		sum(t.philippines_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.philippines_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.philippines_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.philippines_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.philippines_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.philippines_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.philippines_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.philippines_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'新加坡' `type`, ");
        sql.append(" 		sum(t.singapore_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.singapore_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.singapore_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.singapore_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.singapore_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.singapore_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.singapore_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.singapore_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'泰国' `type`, ");
        sql.append(" 		sum(t.thailand_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.thailand_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.thailand_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.thailand_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.thailand_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.thailand_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.thailand_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.thailand_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'印度' `type`, ");
        sql.append(" 		sum(t.india_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.india_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.india_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.india_month) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.india_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.india_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.india_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.india_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'越南' `type`, ");
        sql.append(" 		sum(t.vietnam_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.vietnam_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.vietnam_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.vietnam_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.vietnam_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.vietnam_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.vietnam_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.vietnam_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t  LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'缅甸' `type`, ");
        sql.append(" 		sum(t.burma_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.burma_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.burma_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.burma_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.burma_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.burma_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.burma_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.burma_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'朝鲜' `type`, ");
        sql.append(" 		sum(t.northkorea_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.northkorea_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.northkorea_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.northkorea_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.northkorea_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.northkorea_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.northkorea_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.northkorea_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'巴基斯坦' `type`, ");
        sql.append(" 		sum(t.pakistan_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.pakistan_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.pakistan_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.pakistan_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.pakistan_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.pakistan_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.pakistan_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.pakistan_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'亚洲其它' `type`, ");
        sql.append(" 		sum(t.asianother_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.asianother_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.asianother_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.asianother_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.asianother_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.asianother_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.asianother_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.asianother_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'2.欧洲小计' `type`, ");
        sql.append(" 		sum(t.europe_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.europe_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.europe_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.europe_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.europe_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.europe_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.europe_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.europe_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'英国' `type`, ");
        sql.append(" 		sum(t.england_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.england_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.england_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.england_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.england_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.england_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.england_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.england_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'法国' `type`, ");
        sql.append(" 		sum(t.france_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.france_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.france_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.france_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.france_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.france_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.france_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.france_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'德国' `type`, ");
        sql.append(" 		sum(t.germany_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.germany_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.germany_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.germany_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.germany_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.germany_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.germany_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.germany_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
		
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'意大利' `type`, ");
        sql.append(" 		sum(t.italy_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.italy_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.italy_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.italy_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.italy_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.italy_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.germany_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.germany_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'瑞士' `type`, ");
        sql.append(" 		sum(t.switzerland_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.switzerland_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.switzerland_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.switzerland_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.switzerland_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.switzerland_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.switzerland_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.switzerland_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'瑞典' `type`, ");
        sql.append(" 		sum(t.sweden_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.sweden_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.sweden_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.sweden_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.sweden_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.sweden_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.sweden_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.sweden_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'俄罗斯' `type`, ");
        sql.append(" 		sum(t.russia_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.russia_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.russia_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.russia_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.russia_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.russia_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.russia_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.russia_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'西班牙' `type`, ");
        sql.append(" 		sum(t.spain_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.spain_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.spain_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.spain_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.spain_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.spain_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.spain_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.spain_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'欧洲其它' `type`, ");
        sql.append(" 		sum(t.europeother_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.europeother_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.europeother_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.europeother_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.europeother_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.europeother_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.europeother_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.europeother_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'3.美洲小计' `type`, ");
        sql.append(" 		sum(t.america_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.america_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.america_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.america_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.america_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.america_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.america_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.america_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'美国' `type`, ");
        sql.append(" 		sum(t.usa_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.usa_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.usa_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.usa_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.usa_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.usa_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.usa_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.usa_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'加拿大' `type`, ");
        sql.append(" 		sum(t.canada_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.canada_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.canada_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.canada_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.canada_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.canada_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.canada_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.canada_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'美洲其它' `type`, ");
        sql.append(" 		sum(t.americaother_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.americaother_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.americaother_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.americaother_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.americaother_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.americaother_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.americaother_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.americaother_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'4.大洋洲小计' `type`, ");
        sql.append(" 		sum(t.oceania_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.oceania_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.oceania_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.oceania_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.oceania_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.oceania_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.oceania_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.oceania_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'澳大利亚' `type`, ");
        sql.append(" 		sum(t.australia_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.australia_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.australia_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.australia_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.australia_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.australia_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.australia_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.australia_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'新西兰' `type`, ");
        sql.append(" 		sum(t.nz_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.nz_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.nz_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.nz_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.nz_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.nz_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.nz_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.nz_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'大洋洲其它' `type`, ");
        sql.append(" 		sum(t.oceaniaother_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.oceaniaother_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.oceaniaother_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.oceaniaother_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.oceaniaother_monthyear) `年初至本月`, ");
		
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.oceaniaother_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.oceaniaother_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.oceaniaother_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'5.非洲小计' `type`, ");
        sql.append(" 		sum(t.africa_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.africa_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.africa_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.africa_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.africa_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.africa_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.africa_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.africa_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append("  ");
        sql.append(" UNION ");
        sql.append(" 	SELECT ");
        sql.append(" 		'6.其它小计' `type`, ");
        sql.append(" 		sum(t.other_month) `本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.other_month) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年本月`, ");
        sql.append(" 		round( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.other_month) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.other_month) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `本月同比增长`, ");
        sql.append(" 		sum(t.other_monthyear) `年初至本月`, ");
        sql.append(" 		( ");
        sql.append(" 			SELECT ");
        sql.append(" 				sum(t.other_monthyear) ");
        sql.append(" 			FROM ");
        sql.append(" 				t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 			WHERE ");
        sql.append(" 				t.`year` = '"+lastYear+"' ");
        sql.append(" 			AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 		) `去年年初至本月`, ");
        sql.append(" 		ROUND( ");
        sql.append(" 			( ");
        sql.append(" 				sum(t.other_monthyear) / ( ");
        sql.append(" 					SELECT ");
        sql.append(" 						sum(t.other_monthyear) ");
        sql.append(" 					FROM ");
        sql.append(" 						t_hotelmonthly t LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID ");
        sql.append(" 					WHERE ");
        sql.append(" 						t.`year` = '"+lastYear+"' ");
        sql.append(" 					AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}
        sql.append(" 				) - 1 ");
        sql.append(" 			) * 100, ");
        sql.append(" 			2 ");
        sql.append(" 		) `年初至本月同比增长` ");
        sql.append(" 	FROM ");
        sql.append(" 		t_hotelmonthly t   LEFT JOIN t_hotelmanage h ON t.hotel_id=h.ID  ");
        sql.append(" 	WHERE ");
        sql.append(" 		t.`year` = '"+year+"' ");
        sql.append(" 	AND t. MONTH = '"+month+"' ");
		if(bay!=null&&bay.length()!=0){
        		sql.append(" And h.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And h.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And h.w_county="+area);
        	}

        // 根据数据表,页面查询条件, 抓取数据库中对应的数据
        HSSFWorkbook workbook = null;
        try
        {
            workbook = emds.getNewModelInfos(modelPath, sql.toString());
            System.out.println(sql.toString());
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
						+ new String((areas+year+"年第"+month+"月"+bays+stars+excelName).getBytes("UTF-8"),"iso-8859-1"));
	        }else{
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(areas+year+"年第"+month+"月"+bays+stars+excelName, "UTF-8"));

	        }
            workbook.setSheetName(0, areas+year+"年第"+month+"月"+bays+stars+excelName);

			POIUtils.writeWorkbook(workbook, response.getOutputStream());
		} else {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode("Errors.xls", "UTF-8"));
			response.getOutputStream().print(
					"You have already downloaded the error excel!");
		}
        
	}
	
	@RequestMapping(params = "exportsxls6")
	public void exportXls6(HttpServletRequest request,HttpServletResponse response
			, ModelMap map) throws Exception {	
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        String excelName = "分地区接待外国过夜游客分国别情况.xls";
	        String modelPath = "/com/zzc/web/export/model/hotel/"+excelName;
	        
	        // 获取参数 
	        String year = request.getParameter("year");
	       String month=request.getParameter("month");
	       Calendar calendar = Calendar.getInstance();
	        if(year == null || year.length() == 0) year = String.valueOf(calendar.get(Calendar.YEAR));
	        if(month == null || month.length() == 0) month = String.valueOf(calendar.get(Calendar.MONTH + 1));
	       
	        String bay = request.getParameter("bay");
	        String star = request.getParameter("star");
	        String area = request.getParameter("area");
	        String areas="三亚";
	        String stars="全部星级";
	        String bays="所有湾区";
	        if(bay!=null&&bay.length()!=0){
	        	bays=getbays(bay);
	         }

	       if(area!=null&&area.length()!=0){
	    	   areas=getarea(area)+"总和";
	       }
	       if(star!=null&&star.length()!=0){
	    	   stars=getstar(star);
	       }
	     
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	tt.w_county = '0', ");
		sql.append(" 	'市辖区', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	tt.w_county = '1', ");
		sql.append(" 	'崖州区', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	tt.w_county = '2', ");
		sql.append(" 	'天涯区', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	tt.w_county = '3', ");
		sql.append(" 	'吉阳区', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	tt.w_county = '4', ");
		sql.append(" 	'海棠区', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) diqu, ");
		sql.append(" tt.regionalism_code, ");
		sql.append(" COUNT(tt.ID) he, ");
		sql.append(" SUM(t.total_monthtime) total, ");
		sql.append(" SUM(t.asian_month) asia, ");
		sql.append(" SUM(t.japan_month) japan, ");
		sql.append(" SUM(t.korea_month) korea, ");
		sql.append(" SUM(t.mongolia_month) mongolia, ");
		sql.append(" SUM(t.indonesia_month) indobesia, ");
		sql.append("  ");
		sql.append(" SUM(t.malaysia_month) malaysiaMonth, ");
		sql.append(" SUM(t.philippines_month) philippinesMonth, ");
		sql.append(" SUM(t.singapore_month) singaporeMonth, ");
		sql.append(" SUM(t.thailand_month) thailandMonth, ");
		sql.append(" SUM(t.india_month) indiaMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.vietnam_month) vietnamMonth, ");
		sql.append(" SUM(t.burma_month) burma_month, ");
		sql.append(" SUM(t.northkorea_month) northkorea_month, ");
		sql.append(" SUM(t.pakistan_month) pakistan_month, ");
		sql.append(" SUM(t.laos_month) laos_month, ");
		sql.append("  ");
		sql.append(" SUM(t.cambodia_month) cambodiaMonth, ");
		sql.append(" SUM(t.nepal_month) nepalMonth, ");
		sql.append(" SUM(t.srilanka_month) srilanka_month, ");
		sql.append(" SUM(t.kz_month) kzMonth, ");
		sql.append(" SUM(t.kyrghyzstan_month) kyrghyzstanMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.tajikistan_month) tajikistanMonth, ");
		sql.append(" SUM(t.uz_month) uzMonth, ");
		sql.append(" SUM(t.asianother_month) asianOtherMonth, ");
		sql.append(" SUM(t.europe_month) europe_month, ");
		sql.append(" SUM(t.england_month) englandMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.france_month) franceMonth, ");
		sql.append(" SUM(t.germany_month) germanyMonth, ");
		sql.append(" SUM(t.italy_month) italy_month, ");
		sql.append(" SUM(t.switzerland_month) switzerlandMonth, ");
		sql.append(" SUM(t.sweden_month) swedenMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.russia_month) russiaMonth, ");
		sql.append(" SUM(t.ireland_month) ireland_month, ");
		sql.append(" SUM(t.ukraine_month) ukraine_month, ");
		sql.append(" SUM(t.belgium_month) belgiumMonth, ");
		sql.append(" SUM(t.cz_month) czMonth, ");
		sql.append(" SUM(t.austria_month) austriaMonth, ");
		sql.append(" SUM(t.holland_month) hollandMonth, ");
		sql.append(" SUM(t.portugal_month) portugalMonth, ");
		sql.append(" SUM(t.spain_month) spain_month, ");
		sql.append("  ");
		sql.append("  ");
		sql.append(" SUM(t.europeother_month) europeOtherMonth, ");
		sql.append(" SUM(t.america_month) america_month, ");
		sql.append(" SUM(t.usa_month) usaMonth, ");
		sql.append(" SUM(t.canada_month) canadaMonth, ");
		sql.append(" SUM(t.mexico_month) mexicoMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.brazil_month) brazilMonth, ");
		sql.append(" SUM(t.argentina_month) argentinaMonth, ");
		sql.append(" SUM(t.chile_month) chileMonth, ");
		sql.append(" SUM(t.peru_month) peruMonth, ");
		sql.append(" SUM(t.americaother_month) americaOtherMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.oceania_month) oceaniaMonth, ");
		sql.append(" SUM(t.australia_month) australiaMonth, ");
		sql.append(" SUM(t.nz_month) nzMonth, ");
		sql.append(" SUM(t.oceaniaother_month) oceaniaOtherMonth, ");
		sql.append(" SUM(t.africa_month) africaMonth, ");
		sql.append("  ");
		sql.append(" SUM(t.other_month) otherMonth ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append(" FROM ");
		sql.append(" 	t_hotelmonthly t ");
		sql.append(" LEFT JOIN t_hotelmanage tt ON tt.id = t.hotel_id ");
		sql.append("WHERE 1=1");
			if (year!=null&&year.length()!=0) {
				sql.append(" AND  t.`year`="+year);
			} 	
			if(month!=null&&month.length()!=0){
				sql.append(" AND t.month="+month);
			}
			if(bay!=null&&bay.length()!=0){
        		sql.append(" And tt.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And tt.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And tt.w_county="+area);
        	}	
			sql.append("  GROUP BY tt.w_county ");
			sql.append(" UNION ");
			sql.append(" SELECT ");
			sql.append("  ");
			sql.append(" '"+areas+"' diqu, ");
			sql.append(" tt.regionalism_code, ");
			sql.append(" COUNT(tt.ID) he, ");
			sql.append(" SUM(t.total_monthtime) total, ");
			sql.append(" SUM(t.asian_month) asia, ");
			sql.append(" SUM(t.japan_month) japan, ");
			sql.append(" SUM(t.korea_month) korea, ");
			sql.append(" SUM(t.mongolia_month) mongolia, ");
			sql.append(" SUM(t.indonesia_month) indobesia, ");
			sql.append("  ");
			sql.append(" SUM(t.malaysia_month) malaysiaMonth, ");
			sql.append(" SUM(t.philippines_month) philippinesMonth, ");
			sql.append(" SUM(t.singapore_month) singaporeMonth, ");
			sql.append(" SUM(t.thailand_month) thailandMonth, ");
			sql.append(" SUM(t.india_month) indiaMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.vietnam_month) vietnamMonth, ");
			sql.append(" SUM(t.burma_month) burma_month, ");
			sql.append(" SUM(t.northkorea_month) northkorea_month, ");
			sql.append(" SUM(t.pakistan_month) pakistan_month, ");
			sql.append(" SUM(t.laos_month) laos_month, ");
			sql.append("  ");
			sql.append(" SUM(t.cambodia_month) cambodiaMonth, ");
			sql.append(" SUM(t.nepal_month) nepalMonth, ");
			sql.append(" SUM(t.srilanka_month) srilanka_month, ");
			sql.append(" SUM(t.kz_month) kzMonth, ");
			sql.append(" SUM(t.kyrghyzstan_month) kyrghyzstanMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.tajikistan_month) tajikistanMonth, ");
			sql.append(" SUM(t.uz_month) uzMonth, ");
			sql.append(" SUM(t.asianother_month) asianOtherMonth, ");
			sql.append(" SUM(t.europe_month) europe_month, ");
			sql.append(" SUM(t.england_month) englandMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.france_month) franceMonth, ");
			sql.append(" SUM(t.germany_month) germanyMonth, ");
			sql.append(" SUM(t.italy_month) italy_month, ");
			sql.append(" SUM(t.switzerland_month) switzerlandMonth, ");
			sql.append(" SUM(t.sweden_month) swedenMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.russia_month) russiaMonth, ");
			sql.append(" SUM(t.ireland_month) ireland_month, ");
			sql.append(" SUM(t.ukraine_month) ukraine_month, ");
			sql.append(" SUM(t.belgium_month) belgiumMonth, ");
			sql.append(" SUM(t.cz_month) czMonth, ");
			sql.append(" SUM(t.austria_month) austriaMonth, ");
			sql.append(" SUM(t.holland_month) hollandMonth, ");
			sql.append(" SUM(t.portugal_month) portugalMonth, ");
			sql.append(" SUM(t.spain_month) spain_month, ");
			sql.append("  ");
			sql.append("  ");
			sql.append(" SUM(t.europeother_month) europeOtherMonth, ");
			sql.append(" SUM(t.america_month) america_month, ");
			sql.append(" SUM(t.usa_month) usaMonth, ");
			sql.append(" SUM(t.canada_month) canadaMonth, ");
			sql.append(" SUM(t.mexico_month) mexicoMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.brazil_month) brazilMonth, ");
			sql.append(" SUM(t.argentina_month) argentinaMonth, ");
			sql.append(" SUM(t.chile_month) chileMonth, ");
			sql.append(" SUM(t.peru_month) peruMonth, ");
			sql.append(" SUM(t.americaother_month) americaOtherMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.oceania_month) oceaniaMonth, ");
			sql.append(" SUM(t.australia_month) australiaMonth, ");
			sql.append(" SUM(t.nz_month) nzMonth, ");
			sql.append(" SUM(t.oceaniaother_month) oceaniaOtherMonth, ");
			sql.append(" SUM(t.africa_month) africaMonth, ");
			sql.append("  ");
			sql.append(" SUM(t.other_month) otherMonth ");
			sql.append("  ");
			sql.append("  ");
			sql.append("  ");
			sql.append("  ");
			sql.append(" FROM ");
			sql.append(" 	t_hotelmonthly t ");
			sql.append(" LEFT JOIN t_hotelmanage tt ON tt.id = t.hotel_id  ");
			sql.append("WHERE 1=1");
			if (year!=null&&year.length()!=0) {
				sql.append(" AND  t.`year`="+year);
			} 	
			if(month!=null&&month.length()!=0){
				sql.append(" AND t.month="+month);
			}
			if(bay!=null&&bay.length()!=0){
        		sql.append(" And tt.bay_type="+bay);
        	}if(star!=null&&star.length()!=0){
        		sql.append(" And tt.housegrade="+star);
        	}if(area!=null&&area.length()!=0){
        		sql.append(" And tt.w_county="+area);
        	}
			  System.out.println(sql.toString()); 
	        ExportService emds = new ExportService(59); 
	        
	        HSSFWorkbook workbook = null;
	        try
	        {
	            workbook = emds.getNewModelInfos(modelPath, sql.toString());
	        } catch (Exception e)
	        {

	        }

	        response.reset();
	        response.setContentType("text/html;charset=UTF-8");

			if (workbook != null) {
				// 针对Firefox做处理
				if ("FF".equals(ExplorerHelper.getBrowser(request))) {
					response.addHeader("Content-Disposition", "attachment;filename="
							+ new String((areas+year+"年第"+month+"月"+bays+stars+"接待外国过夜游客分国别情况.xls").getBytes("UTF-8"),"iso-8859-1"));
		        }else{
		            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(areas+year+"年第"+month+"月"+bays+stars+"接待外国过夜游客分国别情况.xls", "UTF-8"));

		        }
	            workbook.setSheetName(0, areas+year+"年第"+month+"月"+bays+stars+"接待外国过夜游客分国别情况.xls");

				POIUtils.writeWorkbook(workbook, response.getOutputStream());
			} else {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode("Errors.xls", "UTF-8"));
				response.getOutputStream().print(
						"You have already downloaded the error excel!");
			}
	        
	}
	private String getParam(HttpServletRequest request, String string) {
		return (String)request.getParameter(string);
	}
/*	 public  String getarea(String area){
		 if(area.equals("0")){
			 area="市辖区";
		 }else  if(area.equals("1")){
			 area="吉阳区";
		 }else  if(area.equals("2")){
			 area="崖州区";
		 }else  if(area.equals("3")){
			 area="天涯区";
		 }else  if(area.equals("4")){
			 area="海棠区";
		 }
		
		 return area;
	 }*/
	 public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		System.out.println(year);
	}
	 
	 public  String getarea(String area){
		 if(area.equals("0")){
			 area="市辖区";
		 }else  if(area.equals("1")){
			 area="吉阳区";
		 }else  if(area.equals("2")){
			 area="崖州区";
		 }else  if(area.equals("3")){
			 area="天涯区";
		 }else  if(area.equals("4")){
			 area="海棠区";
		 }
		
		 return area;
	 }
	 
	 public  String getstar(String star){
		 if(star.equals("4")){
			 star="一星";
		 }else  if(star.equals("5")){
			 star="二星";
		 }else  if(star.equals("6")){
			 star="三星";
		 }else  if(star.equals("7")){
			 star="四星";
		 }else  if(star.equals("8")){
			 star="五星";
		 }
		
		 return star;
	 }
	 public  String getbays(String bay){
		 if(bay.equals("0")){
			 bay="市区";
		 }else  if(bay.equals("1")){
			 bay="亚龙湾";
		 }else  if(bay.equals("2")){
			 bay="大东海";
		 }else  if(bay.equals("3")){
			 bay="三亚湾";
		 }else  if(bay.equals("4")){
			 bay="海棠湾";
		 }
		
		 return bay;
	 }
	
	
}
