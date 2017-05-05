package com.zzc.web.sylyUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzc.web.system.service.SystemService;

/**
 * 查询某个表格中有哪些年份
 * @author Fp
 *
 */

@Controller
@RequestMapping("calcYear")
public class CalcYear {
	private static SystemService systemService;
	@Autowired
	public CalcYear(SystemService systemService){
		CalcYear.systemService = systemService;
	}
	
	/**
	 * 查询某个表格中有哪些年份
	 * @param tableName
	 * @param columnName
	 * @return 年份集合
	 */
	@RequestMapping(params = "getYearList")
	@ResponseBody
	public static String getYearList(HttpServletRequest request, HttpServletResponse response){
		String tableName = request.getParameter("tableName");
		String columnName = request.getParameter("columnName");
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select distinct(" + columnName + ") from "
					+ tableName + " order by " + columnName + " asc";
			List<Map<String, Object>> result = systemService.findForJdbc(sql,null);
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				if (map.get(columnName) != null
						&& map.get(columnName).toString().length() != 0) {
					list.add(map.get(columnName).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		if(list.size() == 0){
			list.add(String.valueOf(calendar.get(Calendar.YEAR - 1)));
		}
		// 需求变更，取最小的年份到今年的年份
		int minYear = Integer.parseInt(list.get(0));
		int maxYear = calendar.get(Calendar.YEAR);
		// 最小值限制2000
		if(minYear < 2000) minYear = 2000;
		String rs = "";
		for (int i = minYear; i <= maxYear; i++) {
			String year = String.valueOf(i);
			rs += (year + ",");
		}
		return rs.substring(0, rs.length()-1);
	}
	
}
