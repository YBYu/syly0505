package com.zzc.app.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzc.core.constant.Globals;
import com.zzc.core.util.PasswordUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.core.util.oConvertUtils;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/appusercontroller")
public class AppUserController {

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * 更改密码
	 * 
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(params = "changePassword")
	@ResponseBody
	public String changePassword(HttpServletRequest request,
			HttpServletResponse respone) {
		String tip = "success";
		try {
			String id = oConvertUtils.getString(request.getParameter("userId"));
			String password = oConvertUtils.getString(request
					.getParameter("password"));
			if (StringUtil.isNotEmpty(id)) {
				TSUser users = systemService.getEntity(TSUser.class, id);
				System.out.println(users.getUserName());
				users.setPassword(PasswordUtil.encrypt(users.getUserName(),
						password, PasswordUtil.getStaticSalt()));
				users.setStatus(Globals.User_Normal);
				users.setActivitiSync(users.getActivitiSync());
				systemService.updateEntitie(users);
				String message = "用户: " + users.getUserName() + "密码重置成功";
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tip = "操作失败";
		}
		return tip;
	}

}
