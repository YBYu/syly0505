package com.zzc.app.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/scheduleRemindercontroller")
public class ScheduleReminderController {
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "scheduleReminder", method = RequestMethod.GET)
	@ResponseBody
	public String scheduleReminder(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		Map<String, Object> roleMap = systemService
				.findOneForJdbc(
						"select * from t_s_role t where t.id=(select tr.roleid from t_s_role_user tr where tr.userid =?)",
						userId);
		String role = roleMap.get("rolename").toString();
		Calendar calendar = Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);
		int nowWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		int nowWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		List<String> scheduleList = new ArrayList<String>();
		if (role.contains("酒店")) {
			// 年报填报
			if (nowMonth < 2) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			} else if (nowMonth == 2 && nowDay < 15) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			}
			// 季报填报
			if (nowMonth == 4 && nowDay < 10) {
				scheduleList.add(nowYear + "年第1季度季报填报工作开始");
			} else if (nowMonth == 7 && nowDay < 10) {
				scheduleList.add(nowYear + "年第2季度季报填报工作开始");
			} else if (nowMonth == 10 && nowDay < 10) {
				scheduleList.add(nowYear + "年第3季度季报填报工作开始");
			} else if (nowMonth == 1 && nowDay < 10) {
				scheduleList.add(nowYear - 1 + "年第4季度季报填报工作开始");
			}
			// 月报填报
			if (nowMonth == 1) {
				if (nowDay < 3) {
					scheduleList.add(nowYear - 1 + "年12月报填报工作开始");
				}
			} else {
				if (nowDay < 3) {
					scheduleList.add(nowYear + "年" + (nowMonth - 1)
							+ "月报填报工作开始");
				}
			}

		} else if (role.contains("景区")) {
			// 年报填报
			if (nowMonth < 2) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			} else if (nowMonth == 2 && nowDay < 15) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			}
			// 季报填报
			if (nowMonth == 4 && nowDay < 10) {
				scheduleList.add(nowYear + "年第1季度季报填报工作开始");
			} else if (nowMonth == 7 && nowDay < 10) {
				scheduleList.add(nowYear + "年第2季度季报填报工作开始");
			} else if (nowMonth == 10 && nowDay < 10) {
				scheduleList.add(nowYear + "年第3季度季报填报工作开始");
			} else if (nowMonth == 1 && nowDay < 10) {
				scheduleList.add(nowYear - 1 + "年第4季度季报填报工作开始");
			}
			// 月报填报
			if (nowMonth == 1) {
				if (nowDay < 5) {
					scheduleList.add(nowYear - 1 + "年12月报填报工作开始");
				}
			} else {
				if (nowDay < 5) {
					scheduleList.add(nowYear + "年" + (nowMonth - 1)
							+ "月报填报工作开始");
				}
			}
			// 周报填报
			if (nowWeek == 1) {
				if (nowWeekDay == 1 && hour < 15) {
					scheduleList.add(nowYear - 1 + "年第52周周报填报工作开始");
				}
			} else {
				if (nowWeekDay == 1 && hour < 15) {
					scheduleList.add(nowYear + "年第" + (nowWeek - 1)
							+ "周周报填报工作开始");
				}
			}
		} else if (role.contains("旅行社")) {
			// 年报填报
			if (nowMonth < 2) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			} else if (nowMonth == 2 && nowDay < 15) {
				scheduleList.add(nowYear - 1 + "年年报填报工作开始");
			}
			// 季报填报
			if (nowMonth == 4 && nowDay < 10) {
				scheduleList.add(nowYear + "年第1季度季报填报工作开始");
			} else if (nowMonth == 7 && nowDay < 10) {
				scheduleList.add(nowYear + "年第2季度季报填报工作开始");
			} else if (nowMonth == 10 && nowDay < 10) {
				scheduleList.add(nowYear + "年第3季度季报填报工作开始");
			} else if (nowMonth == 1 && nowDay < 10) {
				scheduleList.add(nowYear - 1 + "年第4季度季报填报工作开始");
			}
		} else if (role.contains("机场") || role.contains("动车")
				|| role.contains("高尔夫") || role.contains("游艇")
				|| role.contains("空中飞行")) {
			// 月报填报
			if (nowMonth == 1) {
				if (nowDay < 5) {
					scheduleList.add(nowYear - 1 + "年12月报填报工作开始");
				}
			} else {
				if (nowDay < 5) {
					scheduleList.add(nowYear + "年" + (nowMonth - 1)
							+ "月报填报工作开始");
				}
			}
		}
		String res = JSON.toJSONString(scheduleList);
		return res;
	}
}
