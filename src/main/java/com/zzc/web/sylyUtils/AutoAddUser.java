package com.zzc.web.sylyUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.PasswordUtil;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.pojo.base.TSRole;
import com.zzc.web.system.pojo.base.TSRoleUser;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.pojo.base.TSUserOrg;
import com.zzc.web.system.pojo.base.UserScore;
import com.zzc.web.system.service.SystemService;
import com.zzc.web.system.service.UserService;

/**
 * 添加企业用户控制器
 * @author Fp
 *
 */
@Component
public class AutoAddUser {

	private static UserService userService;
	
	private static SystemService systemService;
	@Autowired
	public AutoAddUser(SystemService systemService){
		AutoAddUser.systemService = systemService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 
	 * 自动添加用户并关联角色
	 * @author Fp
	 * @param username 企业名称
	 * @param roleId 角色ID roleId 从GlobalParams类中获取，例如 GlobalParams.GAOERFU，代表高尔夫项目上报员角色
	 * @param 真实姓名
	 * @return 用户基础信息
	 */
	@SuppressWarnings("finally")
	public static TSUser add(String username, String roleId, String realName){
		TSRoleUser rUser = new TSRoleUser();
		TSRole role = systemService.getEntity(TSRole.class, roleId);
		TSUser user = new TSUser();
		Long userNumber = systemService.getCountForJdbc("SELECT count(a.username)  FROM ( SELECT t.username username FROM t_s_base_user t LEFT JOIN t_s_role_user r ON t.ID = r.userid WHERE r.roleid = '"+role.getId()+"') a");
		if(role.getRoleName().contains("景区")){
			user.setUserName("J"+String.format("%05d", userNumber+1));
		}else if(role.getRoleName().contains("旅行社")){
			user.setUserName("L"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("空中飞行")){
			user.setUserName("K"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("高尔夫")){
			user.setUserName("G"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("动车")){
			user.setUserName("D"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("游艇")){
			user.setUserName("Y"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("机场")){
			user.setUserName("A"+String.format("%05d", userNumber+1));
		}if(role.getRoleName().contains("酒店")){
			user.setUserName("B"+String.format("%05d", userNumber+1));
		}
		
		user.setRealName(realName);
		user.setPassword(PasswordUtil.encrypt(user.getUserName(), "syly1111", PasswordUtil.getStaticSalt()));
		user.setStatus(Globals.User_Normal);
		String message = "";
		try {
			//保存用户
			systemService.save(user);
			//保存角色
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);
			
			String orgId = "8a8ab0b246dc81120146dc8180a20016";
            TSDepart depart = new TSDepart();
            depart.setId(orgId);
            TSUserOrg userOrg = new TSUserOrg();
            userOrg.setTsUser(user);
            userOrg.setTsDepart(depart);
            systemService.save(userOrg);
            addUserScore(user.getId());
			message = "用户: " + user.getUserName() + "添加成功";
		} catch (Exception e) {
			message = "用户: " + user.getUserName() + "添加失败 ,原因:"+e.toString();
			user = new TSUser();
		}finally{
			return user;
		}
	}
	
	/**
	 * 
	 * 接口同步的企业信息创建用户并关联角色
	 * @author Fp
	 * @param username 企业名称
	 * @param roleId 角色ID roleId 从GlobalParams类中获取，例如 GlobalParams.GAOERFU，代表高尔夫项目上报员角色
	 * @param 真实姓名
	 * @return 用户基础信息
	 */
	@SuppressWarnings("finally")
	public static TSUser interfaceAdd(String username, String roleId, String realName){
		TSUser user = new TSUser();
		user.setUserName(username);
		user.setRealName(realName);
		user.setPassword(PasswordUtil.encrypt(user.getUserName(), "syly1111", PasswordUtil.getStaticSalt()));
		user.setStatus(Globals.User_Normal);
		String message = "";
		try {
			//保存用户
			systemService.save(user);
			//保存角色
			TSRoleUser rUser = new TSRoleUser();
			TSRole role = systemService.getEntity(TSRole.class, roleId);
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);
			
			String orgId = "8a8ab0b246dc81120146dc8180a20016";
            TSDepart depart = new TSDepart();
            depart.setId(orgId);
            TSUserOrg userOrg = new TSUserOrg();
            userOrg.setTsUser(user);
            userOrg.setTsDepart(depart);
            systemService.save(userOrg);
            addUserScore(user.getId());
			message = "用户: " + user.getUserName() + "添加成功";
		} catch (Exception e) {
			message = "用户: " + user.getUserName() + "添加失败 ,原因:"+e.toString();
			user = new TSUser();
		}finally{
			return user;
		}
	}
	
	// 新建用户诚信积分
	public static void addUserScore(String userId){
		UserScore userScore = new UserScore();
		userScore.setUserId(userId);
		userScore.setScore("500");
		systemService.saveOrUpdate(userScore);
	}
	
	public static String lock(String id, HttpServletRequest req) {
		String message = "1";
		TSUser user = systemService.getEntity(TSUser.class, id);
		if("admin".equals(user.getUserName())){
			message = "0";
			return message;
		}
		String lockValue="0";
		user.setStatus(new Short(lockValue));
		try{
		userService.updateEntitie(user);
		}catch(Exception e){
			message = "0";
		}
		return message;
	}
	
}
