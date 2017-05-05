package com.zzc.app.score;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzc.app.entity.EnterpriseInfoEntity;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/scorecontroller")
public class ScoreController {
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 酒店排行
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getHotelScoreList")
	@ResponseBody
	public List<EnterpriseInfoEntity> getHotelScoreList(
			HttpServletRequest request, HttpServletRequest response) {
		String isLogin = request.getParameter("isLogin");
		List<EnterpriseInfoEntity> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	t.rank, ");
		sql.append(" 	t.unitname `name`, ");
		sql.append(" 	t.address, ");
		sql.append(" 	t.score, ");
		sql.append(" 	t.telephone, ");
		sql.append(" 	cast( ");
		sql.append(" 		floor( ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					(t.rank) / ( ");
		sql.append(" 						SELECT ");
		sql.append(" 							count(id) ");
		sql.append(" 						FROM ");
		sql.append(" 							t_hotelmanage ");
		sql.append(" 					) ");
		sql.append(" 				FROM ");
		sql.append(" 					DUAL ");
		sql.append(" 			) * 100 ");
		sql.append(" 		) AS CHAR (3) ");
		sql.append(" 	) `creditNum`, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '0', ");
		sql.append(" 	'其他', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '1', ");
		sql.append(" 	'舒适', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '2', ");
		sql.append(" 	'高档', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '3', ");
		sql.append(" 	'豪华', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '4', ");
		sql.append(" 	'一星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '5', ");
		sql.append(" 	'二星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '6', ");
		sql.append(" 	'三星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '7', ");
		sql.append(" 	'四星', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.housegrade = '8', ");
		sql.append(" 	'五星', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) `level` ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					count(g.id) + 1 ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							hotel.id, ");
		sql.append(" 							hotel.unitname, ");
		sql.append(" 							score.score ");
		sql.append(" 						FROM ");
		sql.append(" 							t_hotelmanage hotel ");
		sql.append(" 						LEFT JOIN t_user_score score ON hotel.id = score.user_id ");
		sql.append(" 					) g ");
		sql.append(" 				WHERE ");
		sql.append(" 					CAST(g.score AS signed) > CAST(us.score AS signed) ");
		sql.append(" 			) AS rank, ");
		sql.append(" 			us.unitname, ");
		sql.append(" 			us.score, ");
		sql.append(" 			us.housegrade, ");
		sql.append(" 			us.address, ");
		sql.append(" 			us.telephone ");
		sql.append(" 		FROM ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					hotel.id, ");
		sql.append(" 					hotel.unitname, ");
		sql.append(" 					score.score, ");
		sql.append(" 					hotel.housegrade, ");
		sql.append(" 					hotel.address, ");
		sql.append(" 					hotel.telephone ");
		sql.append(" 				FROM ");
		sql.append(" 					t_hotelmanage hotel ");
		sql.append(" 				LEFT JOIN t_user_score score ON hotel.id = score.user_id ");
		sql.append(" 			) us ");
		sql.append(" 	) t ");
		sql.append(" ORDER BY ");
		sql.append(" 	t.rank ASC ");

		// 判断是否登录的请求，如果是则只去前3天数据
		if ("1".equals(isLogin))
			sql.append(" LIMIT 3 ");
		else
			sql.append(" LIMIT 20 ");
		try {
			List<Map<String, Object>> rs = systemService.findForJdbc(sql
					.toString());
			for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
				Map<String, String> map = (Map<String, String>) iterator.next();
				EnterpriseInfoEntity enterprise = new EnterpriseInfoEntity();
				enterprise.setName(map.get("name"));
				enterprise.setLevel(map.get("level"));
				enterprise.setAddress(map.get("address"));
				enterprise.setTelephone(map.get("telephone"));
				enterprise
						.setCreditNum(Integer.parseInt(map.get("creditNum") == null
								|| map.get("creditNum").length() == 0 ? "0"
								: map.get("creditNum")));
				enterprise.setScore(Integer.parseInt(map.get("score") == null
						|| map.get("score").length() == 0 ? "0" : map
						.get("score")));
				list.add(enterprise);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 旅行社排行
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getTravelScoreList")
	@ResponseBody
	public List<EnterpriseInfoEntity> getTravelScoreList(
			HttpServletRequest request, HttpServletRequest response) {
		String isLogin = request.getParameter("isLogin");
		List<EnterpriseInfoEntity> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	t.rank, ");
		sql.append(" 	t.`name`, ");
		sql.append(" 	t.address, ");
		sql.append(" 	t.score, ");
		sql.append(" 	t.phone `telephone`, ");
		sql.append(" 	cast( ");
		sql.append(" 		floor( ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					(t.rank) / ( ");
		sql.append(" 						SELECT ");
		sql.append(" 							count(id) ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_info ");
		sql.append(" 					) ");
		sql.append(" 				FROM ");
		sql.append(" 					DUAL ");
		sql.append(" 			) * 100 ");
		sql.append(" 		) AS CHAR (3) ");
		sql.append(" 	) `creditNum`, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '0', ");
		sql.append(" 	'未评定', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '1', ");
		sql.append(" 	'A', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '2', ");
		sql.append(" 	'AA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '3', ");
		sql.append(" 	'AAA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '4', ");
		sql.append(" 	'AAAA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.scene_spotlevel = '5', ");
		sql.append(" 	'AAAA', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) `level` ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					count(g.id) + 1 ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							agency.id, ");
		sql.append(" 							score.score ");
		sql.append(" 						FROM ");
		sql.append(" 							t_travelagency_info agency ");
		sql.append(" 						LEFT JOIN t_user_score score ON agency.user_id = score.user_id ");
		sql.append(" 					) g ");
		sql.append(" 				WHERE ");
		sql.append(" 					CAST(g.score AS signed) > CAST(us.score AS signed) ");
		sql.append(" 			) AS rank, ");
		sql.append(" 			us.`name`, ");
		sql.append(" 			us.score, ");
		sql.append(" 			us.scene_spotlevel, ");
		sql.append(" 			us.address, ");
		sql.append(" 			us.phone ");
		sql.append(" 		FROM ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					agency.id, ");
		sql.append(" 					agency.`name`, ");
		sql.append(" 					score.score, ");
		sql.append(" 					agency.scene_spotlevel, ");
		sql.append(" 					agency.address, ");
		sql.append(" 					agency.phone ");
		sql.append(" 				FROM ");
		sql.append(" 					t_travelagency_info agency ");
		sql.append(" 				LEFT JOIN t_user_score score ON agency.user_id = score.user_id ");
		sql.append(" 			) us ");
		sql.append(" 	) t ");
		sql.append(" ORDER BY ");
		sql.append(" 	t.rank ASC ");

		// 判断是否登录的请求，如果是则只去前3天数据
		if ("1".equals(isLogin))
			sql.append(" LIMIT 3 ");
		else
			sql.append(" LIMIT 20 ");
		try {
			List<Map<String, Object>> rs = systemService.findForJdbc(sql
					.toString());
			for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
				Map<String, String> map = (Map<String, String>) iterator.next();
				EnterpriseInfoEntity enterprise = new EnterpriseInfoEntity();
				enterprise.setName(map.get("name"));
				enterprise.setLevel(map.get("level"));
				enterprise.setAddress(map.get("address"));
				enterprise.setTelephone(map.get("telephone"));
				enterprise
						.setCreditNum(Integer.parseInt(map.get("creditNum") == null
								|| map.get("creditNum").length() == 0 ? "0"
								: map.get("creditNum")));
				enterprise.setScore(Integer.parseInt(map.get("score") == null
						|| map.get("score").length() == 0 ? "0" : map
						.get("score")));
				list.add(enterprise);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(params = "getScenicspotList")
	@ResponseBody
	public List<EnterpriseInfoEntity> getScenicspotList(
			HttpServletRequest request, HttpServletRequest response) {
		String isLogin = request.getParameter("isLogin");
		List<EnterpriseInfoEntity> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	t.rank, ");
		sql.append(" 	t.`name`, ");
		sql.append(" 	t.address, ");
		sql.append(" 	t.score, ");
		sql.append(" 	t.consult_phone `telephone`, ");
		sql.append(" 	cast( ");
		sql.append(" 		floor( ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					(t.rank) / ( ");
		sql.append(" 						SELECT ");
		sql.append(" 							count(id) ");
		sql.append(" 						FROM ");
		sql.append(" 							t_scenicspot_info ");
		sql.append(" 					) ");
		sql.append(" 				FROM ");
		sql.append(" 					DUAL ");
		sql.append(" 			) * 100 ");
		sql.append(" 		) AS CHAR (3) ");
		sql.append(" 	) `creditNum`, ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '1', ");
		sql.append(" 	'AAAAA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '2', ");
		sql.append(" 	'AAAA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '3', ");
		sql.append(" 	'AAA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '4', ");
		sql.append(" 	'AA', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '5', ");
		sql.append(" 	'A', ");
		sql.append("  ");
		sql.append(" IF ( ");
		sql.append(" 	t.`level` = '6', ");
		sql.append(" 	'未评定', ");
		sql.append(" 	'' ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) ");
		sql.append(" ) `level` ");
		sql.append(" FROM ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					count(g.id) + 1 ");
		sql.append(" 				FROM ");
		sql.append(" 					( ");
		sql.append(" 						SELECT ");
		sql.append(" 							scenicspot.id, ");
		sql.append(" 							scenicspot.`name`, ");
		sql.append(" 							score.score ");
		sql.append(" 						FROM ");
		sql.append(" 							t_scenicspot_info scenicspot ");
		sql.append(" 						LEFT JOIN t_user_score score ON scenicspot.user_id = score.user_id ");
		sql.append(" 					) g ");
		sql.append(" 				WHERE ");
		sql.append(" 					CAST(g.score AS signed) > CAST(us.score AS signed) ");
		sql.append(" 			) AS rank, ");
		sql.append(" 			us.`name`, ");
		sql.append(" 			us.score, ");
		sql.append(" 			us.`level`, ");
		sql.append(" 			us.address, ");
		sql.append(" 			us.consult_phone ");
		sql.append(" 		FROM ");
		sql.append(" 			( ");
		sql.append(" 				SELECT ");
		sql.append(" 					scenicspot.id, ");
		sql.append(" 					scenicspot.`name`, ");
		sql.append(" 					score.score, ");
		sql.append(" 					scenicspot.`level`, ");
		sql.append(" 					scenicspot.address, ");
		sql.append(" 					scenicspot.consult_phone ");
		sql.append(" 				FROM ");
		sql.append(" 					t_scenicspot_info scenicspot ");
		sql.append(" 				LEFT JOIN t_user_score score ON scenicspot.user_id = score.user_id ");
		sql.append(" 			) us ");
		sql.append(" 	) t ");
		sql.append(" ORDER BY ");
		sql.append(" 	t.rank ASC ");

		// 判断是否登录的请求，如果是则只去前3天数据
		if ("1".equals(isLogin))
			sql.append(" LIMIT 3 ");
		else
			sql.append(" LIMIT 20 ");
		try {
			List<Map<String, Object>> rs = systemService.findForJdbc(sql
					.toString());
			for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
				Map<String, String> map = (Map<String, String>) iterator.next();
				EnterpriseInfoEntity enterprise = new EnterpriseInfoEntity();
				enterprise.setName(map.get("name"));
				enterprise.setLevel(map.get("level"));
				enterprise.setAddress(map.get("address"));
				enterprise.setTelephone(map.get("telephone"));
				enterprise
						.setCreditNum(Integer.parseInt(map.get("creditNum") == null
								|| map.get("creditNum").length() == 0 ? "0"
								: map.get("creditNum")));
				enterprise.setScore(Integer.parseInt(map.get("score") == null
						|| map.get("score").length() == 0 ? "0" : map
						.get("score")));
				list.add(enterprise);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
