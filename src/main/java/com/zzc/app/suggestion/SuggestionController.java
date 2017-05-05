package com.zzc.app.suggestion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzc.app.suggestion.entity.Suggestion;
import com.zzc.web.questionnaire.entity.IdeaBack;
import com.zzc.web.system.pojo.base.TSUser;
import com.zzc.web.system.service.SystemService;

@Controller
@RequestMapping("/appservice/suggestioncontroller")
public class SuggestionController {
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 
	 * 手机端添加意见
	 * 
	 * @param request
	 * @param response
	 * @return 是否成功标志
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");

		String tip = "success";

		try {
			IdeaBack ideaBack = new IdeaBack();
			ideaBack.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			ideaBack.setContent(content);
			ideaBack.setUserId(userId);
			ideaBack.setCreateDate(new Date());
			ideaBack.setStatus("0");
			TSUser user = systemService.get(TSUser.class, userId);
			try {
				ideaBack.setIdeaUserName(user.getRealName());
			} catch (Exception e) {
				e.printStackTrace();
				ideaBack.setIdeaUserName("");
			}
			systemService.save(ideaBack);
		} catch (Exception e) {
			e.printStackTrace();
			tip = "添加失败";
		}
		return tip;
	}

	/**
	 * 
	 * 获取意见列表
	 * 
	 * @param request
	 * @param response
	 * @return List<Suggestion> 意见列表
	 * 
	 */
	@RequestMapping(params = "getAllList")
	@ResponseBody
	public List<Suggestion> getAllList(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		List<Suggestion> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<IdeaBack> rs = systemService.findByProperty(IdeaBack.class,
					"userId", userId);
			for (int i = 0; i < rs.size(); i++) {
				Suggestion suggestion = new Suggestion();
				IdeaBack ideaBack = rs.get(i);
				suggestion.setId(ideaBack.getId());
				suggestion.setContent(ideaBack.getContent());
				suggestion.setReply(ideaBack.getReplyContent());
				try {
					suggestion
							.setReplyTime(sdf.format(ideaBack.getReplyDate()));
				} catch (Exception e) {
					e.printStackTrace();
					suggestion.setReplyTime("");
				}
				try {
					suggestion.setCreateTime(sdf.format(ideaBack
							.getCreateDate()));
				} catch (Exception e) {
					e.printStackTrace();
					suggestion.setCreateTime("");
				}
				List<TSUser> user = systemService.findByProperty(TSUser.class,
						"id", ideaBack.getReplyUserId());
				suggestion.setReplyPerson(user.size() == 0 ? "" : user.get(0)
						.getRealName());
				list.add(suggestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 获取详细意见详细信息
	 * 
	 * @param request
	 * @param response
	 * @return Suggestion
	 */
	@RequestMapping(params = "getDetailInfo")
	@ResponseBody
	public Suggestion getDetailInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		Suggestion suggestion = new Suggestion();
		try {
			List<IdeaBack> list = systemService.findByProperty(IdeaBack.class,
					"id", id);
			IdeaBack ideaBack = list.get(0);
			suggestion = new Suggestion();
			suggestion.setId(id);
			suggestion.setContent(ideaBack.getContent());
			suggestion.setReply(ideaBack.getReplyContent());
			List<TSUser> user = systemService.findByProperty(TSUser.class,
					"id", ideaBack.getReplyUserId());
			suggestion.setReplyPerson(user.get(0).getRealName());
			suggestion.setReplyTime(sdf.format(ideaBack.getReplyDate()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suggestion;
	}

}
