package com.zzc.web.survey.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzc.core.common.controller.BaseController;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.survey.entity.SurveyBase;
import com.zzc.web.survey.entity.SurveyOption;
import com.zzc.web.survey.entity.SurveyQuestion;
import com.zzc.web.survey.service.SurveyService;

@Controller
@RequestMapping("surveyServiceController")
public class SurveyServiceController extends BaseController{
	
	private SurveyService surveyService;
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	@RequestMapping(params = "survey")
	public ModelAndView survey(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("survey/surveyBaseList");
	}
	
	@RequestMapping(params = "dataGrid")
	public void dataGrid(HttpServletRequest request, HttpServletResponse response,SurveyBase surveyBase, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(SurveyBase.class, dataGrid);
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, surveyBase);
		cq.add();
		surveyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "addSurvey")
	public ModelAndView addSurvey(){
		return new ModelAndView("survey/addSurvey");
	}
	
	@RequestMapping(params = "saveSurvey")
	public void saveSurvey(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		JSONObject obj= JSONObject.parseObject(data);
		try {
			// 基础信息
			String surveyName = obj.get("surveyName").toString();
			String declare = obj.get("declare").toString();
			String startDate = obj.get("startDate").toString();
			String endDate = obj.get("endDate").toString();
			
			SurveyBase surveyBase = new SurveyBase();
			String id = UUID.randomUUID().toString().replaceAll("-","");
			surveyBase.setId(id);
			surveyBase.setName(surveyName);
			surveyBase.setDeclare(declare);
			surveyBase.setStatus("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			surveyBase.setStartDate(sdf.parse(startDate));
			surveyBase.setStartDate(sdf.parse(endDate));
			surveyBase.setCreateTime(new Date());
			// 保存基本信息
			this.surveyService.saveOrUpdate(surveyBase);
			
			// 问题信息
			JSONArray questions = JSONObject.parseArray(obj.get("questions").toString());
			List<SurveyOption> surveyOptionList = new ArrayList<SurveyOption>();
			List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();
			for (int i = 0; i < questions.size(); i++) {
				JSONObject questionObj = JSONObject.parseObject(questions.get(i).toString());
				// 问题
				String question = questionObj.get("question").toString();
				SurveyQuestion surveyQuestion = new SurveyQuestion();
				String questionId = UUID.randomUUID().toString().replaceAll("-","");
				surveyQuestion.setId(questionId);
				surveyQuestion.setSurveyId(id);
				surveyQuestion.setQuestionTitle(question);
				//保存问题信息
				surveyQuestionList.add(surveyQuestion);
				
				// 选项，以数组表示
				JSONArray optionsArray = JSONObject.parseArray(questionObj.get("options").toString());
				for (int j = 0; j < optionsArray.size(); j++) {
					SurveyOption surveyOption = new SurveyOption();
					surveyOption.setId(UUID.randomUUID().toString().replaceAll("-",""));
					surveyOption.setQuestionId(questionId);
					surveyOption.setOptionTitle(optionsArray.get(j).toString());
					surveyOptionList.add(surveyOption);
				}
			}
			this.surveyService.batchSave(surveyQuestionList);
			this.surveyService.batchSave(surveyOptionList);
		} catch (Exception e) {
		}
	}
}
