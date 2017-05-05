package com.zzc.web.questionnaire.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.model.json.AjaxJson;
import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.common.model.json.Highchart;
import com.zzc.core.constant.Globals;
import com.zzc.core.util.IpUtil;
import com.zzc.core.util.StringUtil;
import com.zzc.tag.core.easyui.TagUtil;
import com.zzc.web.questionnaire.entity.AnswerName;
import com.zzc.web.questionnaire.entity.QuestionData;
import com.zzc.web.questionnaire.entity.QuestionNaireOption;
import com.zzc.web.questionnaire.entity.QuestionName;
import com.zzc.web.questionnaire.entity.QuestionTopic;
import com.zzc.web.scenicmanage.controller.ScenicController;
import com.zzc.web.system.service.SystemService;

/**
 * @Title: Controller
 * @Description: 问卷
 * @author 冯勇齐
 * @date 2016-12-30 18:30:55
 * 
 */

@Scope("prototype")
@Controller
@RequestMapping("/questionNaireController")
public class QuestionnaireController {

	private String message = null;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ScenicController.class);
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "toQuestionAuditList")
	public String questionAuditList(HttpServletRequest request) {

		return "questionnaire/questionAuditList";

	}

	// 本方法跳转到save.jsp页面
	@RequestMapping(params = "add")
	public String questionInfo() {

		return "questionnaire/add";
	}

	@RequestMapping(params = "analyze")
	public String questionAnalyze() {

		return "questionnaire/analyze";
	}

	/**
	 * 添加问卷
	 * @author 冯勇齐
	 * @param
	 * @param req  
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson saveQuestion(HttpServletRequest req,
			QuestionData questionData, QuestionTopic questionTopic,
			QuestionNaireOption questionNaireOption,QuestionName questionName) {
		AjaxJson j = new AjaxJson();
		Date date = new Date();
		try {
			String aa =UUID.randomUUID().toString();
			questionData.setUrl(aa);  
			questionData.setStartTime(date);
			questionData.setStatus("0");
			
			systemService.save(questionData);
			int i = 0;
			if (questionData.getQuestionTopicList() != null) {
				for (QuestionTopic qt : questionData.getQuestionTopicList()) {
				if (qt.getTopicName() == null || qt.getTopicName().equals("")){
					systemService.delete(questionData);
	            	message = "问卷添加失败!";
	            	j.setMsg(message);
	          		return j;
	            } else {  
					String optins = req.getParameter("option"+i);
					int p = optins.length() - optins.replace("," , "").length() + 1;
					String[] opts = optins.split(",");;
					if(optins == "" || p > opts.length) {
						message = "问卷添加失败！问题无选项！";
		            	j.setMsg(message);
		            	systemService.delete(questionData);
		          		return j;
					}
					qt.setQuestionData(questionData);
					qt.setSid(questionData.getId());
					systemService.save(qt);
					
					for(String o :opts){
						QuestionNaireOption qo = new QuestionNaireOption();
						qo.setOptionName(o);
						qo.setQuestionTopic(qt);
						systemService.save(qo);
					}
					i++;
	              } 
				}
				message = "问卷添加成功!";
				systemService.addLog("问卷添加成功:"+questionData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}else{
				message = "问卷添加失败!";
				j.setMsg(message);
				systemService.addLog("问卷添加失败:"+questionData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				return j;
			}	
		} catch (Exception e) {
			 message = "问卷添加失败!";
			 systemService.addLog("问卷添加失败:"+questionData.getName(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 问卷列表
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "questionDatagrid")
	public void monthGrid(QuestionData questionData, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QuestionData.class, dataGrid);
		// 查询条件组装器
		if(questionData.getName() != null && questionData.getName() .length() != 0){
		String sql="this_ .name like '%"+questionData.getName()+"%'";
		cq.add(Restrictions.sqlRestriction(sql));
			}
		questionData.setName(null); 
		com.zzc.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				questionData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除问卷
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(QuestionData questionData, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			questionData = systemService.getEntity(QuestionData.class, id);
			systemService.delete(questionData);
			message = "问卷删除成功";
			systemService.addLog("删除问卷成功:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "问卷删除失败";
			systemService.addLog("删除问卷失败:"+id, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 问卷统计jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "wenjuan")
	public ModelAndView analyze(HttpServletRequest request, QuestionData questionData) {
		String analyzeid = request.getParameter("analyzeid");
		request.setAttribute("analyzeid", analyzeid);
		QuestionData s = this.systemService.get(QuestionData.class, analyzeid);
		List<QuestionTopic> list = this.systemService.findHql(
			" from com.zzc.web.questionnaire.entity.QuestionTopic th where th.sid=? ", analyzeid);
		request.setAttribute("topiclist", list);
		for (QuestionTopic sd : list) {
			request.setAttribute("topicdata", sd);
		}
		request.setAttribute("analyzedata", s);

		return new ModelAndView("questionnaire/analyze1");
	}

	/**
	 * 问卷编辑jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "auditwenjuan", method = RequestMethod.GET)
	public ModelAndView auditAnalyze(HttpServletRequest request, QuestionData questionData) {
		String analyzeid = request.getParameter("analyzeid");
		String bianjiurl = request.getParameter("bianjiurl");

		request.setAttribute("analyzeid", analyzeid);
		request.setAttribute("bianjiurl", bianjiurl);

		QuestionData s = this.systemService.get(QuestionData.class, analyzeid);
		List<QuestionTopic> list = this.systemService.findHql(
			" from com.zzc.web.questionnaire.entity.QuestionTopic th where th.sid=? ", analyzeid);
		request.setAttribute("topiclist", list);
		for (QuestionTopic sd : list) {
			request.setAttribute("topicdata", sd);
		}
		request.setAttribute("analyzedata", s);

		return new ModelAndView("questionnaire/auditAnalyze");
	}
	
	/**
	 * 游客问卷填写jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "guestreportquestion", method = RequestMethod.GET)
	public ModelAndView guestReportQuestion(HttpServletRequest request, QuestionData questionData) {
		String analyzeid = request.getParameter("analyzeid");
		String bianjiurl = request.getParameter("bianjiurl");

		request.setAttribute("analyzeid", analyzeid);
		request.setAttribute("bianjiurl", bianjiurl);

		QuestionData s = this.systemService.get(QuestionData.class, analyzeid);
		List<QuestionTopic> list = this.systemService.findHql(
			" from com.zzc.web.questionnaire.entity.QuestionTopic th where th.sid=? ", analyzeid);
		request.setAttribute("topiclist", list);
		for (QuestionTopic sd : list) {
			request.setAttribute("topicdata", sd);
		}
		request.setAttribute("analyzedata", s);

		return new ModelAndView("questionnaire/guestReportQuestion");
	}

	
	/**
	 * 问卷发送链接jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "reportwenjuan")
	public ModelAndView reportQuestion(HttpServletRequest request, QuestionData questionData) {
		String analyzeid = request.getParameter("analyzeid");
		request.setAttribute("analyzeid", analyzeid);
		QuestionData s = this.systemService.get(QuestionData.class, analyzeid);
		s.setStatus("1");
		systemService.saveOrUpdate(s);
		List<QuestionTopic> list = this.systemService.findHql(
			" from com.zzc.web.questionnaire.entity.QuestionTopic th where th.sid=? ", analyzeid);
		request.setAttribute("topiclist", list);
		request.setAttribute("head",request.getLocalAddr()+":"+request.getServerPort());
		System.out.println(request);  

		return new ModelAndView("questionnaire/reportQuestion");
	}
	
	
	
	/**
	 * 发布问卷,状态:1
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsignOne")
	@ResponseBody
	public AjaxJson addsignOne(QuestionData questionData,QuestionName questionName, HttpServletRequest req) {
		String id = req.getParameter("id");
		String a =UUID.randomUUID().toString();
		questionName.setUrl(a);
		systemService.save(questionName);
		AjaxJson j = new AjaxJson();
		try {
			questionData = systemService.getEntity(QuestionData.class, id);
			questionData.setStatus("1");
			systemService.saveOrUpdate(questionData);
			message = "问卷发布成功";
		} catch (Exception e) {
			message = "问卷发布失败";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 回收问卷,状态:2
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addsignTwo")
	@ResponseBody
	public AjaxJson addsignTwo(QuestionData questionData, HttpServletRequest req) {
		String id = req.getParameter("id");
		AjaxJson j = new AjaxJson();
		try {
			questionData = systemService.getEntity(QuestionData.class, id);
			questionData.setStatus("2");
			systemService.saveOrUpdate(questionData);
			message = "问卷回收成功";
			systemService.addLog("问卷回收成功:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "问卷回收失败";
			systemService.addLog("问卷回收失败:"+id, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 编辑问卷
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "audit")
	@ResponseBody
	public AjaxJson auditQuestion(HttpServletRequest req, QuestionData questionData, QuestionTopic questionTopic,
			QuestionNaireOption questionNaireOption,QuestionName questionName,AnswerName answerName) {
		AjaxJson j = new AjaxJson();
		String ips=IpUtil.getIpAddr(req);   //获取用户的IP地址 
		System.out.println(ips);
	try{
		String uuid =UUID.randomUUID().toString();
		String analyzeid =req.getParameter("analyzeid");
		QuestionData qs = this.systemService.get(QuestionData.class, analyzeid);
		if(!"1".equals(qs.getStatus())){
			j.setMsg("该问卷未处于发布状态!");
			return j;
		}
	    String	bianjiurl	=req.getParameter("bianjiurl");	
		String prefix="topic_";
	   Enumeration<String>names=req.getParameterNames();
       List<QuestionName> list = systemService.findByQueryString("from QuestionName where questionId='"+analyzeid+"' and ip='"+ips+"'");
       if(list.size() == 0){
    	   QuestionName qn = new QuestionName();
    	   qn.setQuestionId(analyzeid); //代表哪一份问卷
    	   qn.setUserId(uuid);            //代表哪一个用户
    	   qn.setUrl(bianjiurl);          //url用来区分每一份问卷
    	   qn.setIp(ips);
    	   systemService.save(qn);
	   while(names.hasMoreElements()){
		   String name=  names.nextElement();		 
		   if(name.startsWith(prefix)){ 
			   String tipicid = name.substring(prefix.length());
			   String value= req.getParameter(name);
             
			  AnswerName an = new AnswerName();
			  an.setQuestionName(qn);
			  an.setTopicId(tipicid);
			  an.setOptionId(value);
			  systemService.save(an);
		   }  
	   }
	   message = "问卷提交成功";
       } else {
    	   message = "问卷已提交，不能重复提交";
       }
	} catch (Exception e) {
		   message = "问卷提交失败";
	}
	systemService.addLog("message:"+req.getParameter("analyzeid"), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
	j.setMsg(message);
		return j;
	}

	/**
	 * 问卷统计
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */	
	@RequestMapping(params = "questionCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Highchart> getBroswerBar(HttpServletRequest request,String reportType,String tipicid,String answerid, HttpServletResponse response) {
		List<Highchart> list = new ArrayList<Highchart>();
		Highchart hc = new Highchart();
		StringBuffer sb = new StringBuffer();
		String bbb = request.getParameter("answerid");
		String sql = "SELECT count(topic_answer_id),t.option_name FROM  t_answer_name tsa LEFT JOIN t_question_option t on t.id = tsa.option_answer_id WHERE 1=1 "; 
		
		if (StringUtil.isNotEmpty(bbb)) {
			sql += "and tsa.topic_answer_id='"+bbb+"' GROUP BY tsa.option_answer_id";
		}

		sb.append(sql);
		List findListbySql = systemService.findListbySql(sb.toString());
		
		hc = new Highchart();
		hc.setName("问卷统计");
		
		List lt1 = new ArrayList<>();
		Map<String, Object> map;
		if(findListbySql.size()>0){
			for (Object object : findListbySql){
				
				map = new HashMap<String, Object>();
				Object[] obj = (Object[]) object;				
				map.put("y",obj[0]);
				map.put("name",obj[1]);
				lt1.add(map);
			}
		}
		hc.setType(reportType);
		hc.setData(lt1);
		list.add(hc);
		
		systemService.addLog("问卷统计成功:"+request.getParameter("answerid"), Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
		return list;
	}
	
	/**
	 * 问卷编辑jsp
	 * @author 冯勇齐
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "toCount")
	public ModelAndView toCount(HttpServletRequest request,
			QuestionData questionData) {
		String answerid = request.getParameter("answerid");

		request.setAttribute("answerid",answerid); 

		return new ModelAndView("questionnaire/count");
	}
	
	@RequestMapping(params = "shortUrl")
	@ResponseBody
	public String shortUrl(HttpServletRequest request, HttpServletResponse response){
		AjaxJson ajaxJson = new AjaxJson();
		String longUrl = "";
		
		String id = request.getParameter("id");
		String sql = "select t.lang_context from t_s_muti_lang t where t.lang_key = 'zzc.platform.appBase' and t.lang_code='zh-cn'";
		
		try{
		Map<String, Object> map = systemService.findOneForJdbc(sql,null);
		if(map.get("lang_context") != null && map.get("lang_context").toString().length() != 0){
			longUrl = map.get("lang_context").toString()+"questionNaireController.do?guestreportquestion&analyzeid="+id;
		}
		String validateURL="http://api.t.sina.com.cn/short_url/shorten.json";
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(validateURL);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
       
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
       
        connection.setInstanceFollowRedirects(true);
       
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "url_long=" + URLEncoder.encode(longUrl, "UTF-8");
        content +="&source="+URLEncoder.encode("1681459862", "UTF-8");;
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);

        out.flush();
        out.close(); 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = reader.readLine();
        
        reader.close();
        connection.disconnect();
        
        JSONArray array = JSON.parseArray(line);
        JSONObject object = JSON.parseObject(array.get(0).toString());
        systemService.addLog("问卷连接转换成功:"+id, Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
        return object.get("url_short").toString();
	}catch (Exception e){
		e.printStackTrace();
		systemService.addLog("问卷连接转换失败:"+id, Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
		return null;
	}
	}
	
}