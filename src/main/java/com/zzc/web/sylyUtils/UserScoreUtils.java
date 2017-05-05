package com.zzc.web.sylyUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.core.util.ResourceUtil;
import com.zzc.web.system.pojo.base.UserScore;
import com.zzc.web.system.pojo.base.UserScoreRecord;
import com.zzc.web.system.service.SystemService;

/**
 * 企业积分
 * @author Fp
 *
 */
@Component
public class UserScoreUtils {
	
	private static SystemService systemService;
	@Autowired
	public UserScoreUtils(SystemService systemService){
		UserScoreUtils.systemService = systemService;
	}
	
	/**
	 * PC端操作-积分改变
	 * @param score
	 * @return 成功标志
	 */
	public static String scoreChange(int score){
		String tip = "1";
		try {
			//获取当前登录用户ID
			String userId = ResourceUtil.getSessionUserName().getId();
			// 更改用户诚信积分
			List<UserScore> userScoreList = systemService.findByProperty(
					UserScore.class, "userId", userId);
			UserScore userScore = userScoreList.get(0);
			String scoreBefore = userScore.getScore() == null ? "0" : userScore
					.getScore();
			userScore.setScore(String.valueOf(Integer.parseInt(scoreBefore)
					+ score));
			systemService.saveOrUpdate(userScore);
			// 进行积分变更记录
			UserScoreRecord userScoreRecord = new UserScoreRecord();
			userScoreRecord.setUserId(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userScoreRecord.setTime(sdf.format(new Date()));
			userScoreRecord.setScoreChange(String.valueOf(score));
			systemService.save(userScoreRecord);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "0";
		}
		return tip;
	}
	
	/**
	 * 手机端操作-积分改变
	 * @param score
	 * @param userId
	 * @return 成功标志
	 */
	public static String scoreChange(int score, String userId){
		String tip = "1";
		try {
			// 更改用户诚信积分
			List<UserScore> userScoreList = systemService.findByProperty(
					UserScore.class, "userId", userId);
			UserScore userScore = userScoreList.get(0);
			String scoreBefore = userScore.getScore() == null ? "0" : userScore
					.getScore();
			userScore.setScore(String.valueOf(Integer.parseInt(scoreBefore)
					+ score));
			systemService.saveOrUpdate(userScore);
			// 进行积分变更记录
			UserScoreRecord userScoreRecord = new UserScoreRecord();
			userScoreRecord.setUserId(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userScoreRecord.setTime(sdf.format(new Date()));
			userScoreRecord.setScoreChange(String.valueOf(score));
			systemService.save(userScoreRecord);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "0";
		}
		return tip;
	}
}
