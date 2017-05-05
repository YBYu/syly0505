package com.zzc.web.questionnaire.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷基本信息实体
 * @author 冯勇齐
 * @date 2017-01-03 08:50:55
 */  

@Entity
@Table(name = "t_question_info")
@SuppressWarnings("serial")
public class QuestionData implements java.io.Serializable {

	
	
	private String id;
	private String name;
	private String url;
	private Date   startTime;
	private Date   endTime;
	private String status;
	private QuestionTopic questionTopic;
	private List<QuestionTopic>questionTopicList;
	
	
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//问卷名称
	@Column(name = "name", nullable = true, length = 20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	@Column(name = "url", nullable = true, length = 50)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	//开始时间
	@Column(name = "start_time", nullable = true )
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//结束时间
	@Column(name = "end_time", nullable = true )
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	//问卷状态
	@Column(name = "status", nullable = true, length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
	@OneToMany(mappedBy = "questionData", cascade = CascadeType.REMOVE)
	public List<QuestionTopic> getQuestionTopicList() {
		return questionTopicList;
	}
	public void setQuestionTopicList(List<QuestionTopic> questionTopicList) {
		this.questionTopicList = questionTopicList;
	}
	
	
	@Transient
	public QuestionTopic getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(QuestionTopic questionTopic) {
		this.questionTopic = questionTopic;
	}
	

	    
	
	
}
