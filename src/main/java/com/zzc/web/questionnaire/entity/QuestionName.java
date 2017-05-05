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
 * 问卷答案主表，保存用户id,url,关联子表:t_answer_name
 * @author 冯勇齐
 * @date 2017-01-03 09:35:55
 */

@Entity
@Table(name = "t_question_name")
@SuppressWarnings("serial")
public class QuestionName implements java.io.Serializable {
	
	
	private String id;
	private String userId;
	private String userName;
	private String questionId;           //问卷id
	private Date   reportTime;
	private String url;
	private String ip;
	private AnswerName answerName;
	private List<AnswerName>answerNameList;
	
	
	
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
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "user_id", nullable = true,length = 50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "user_name", nullable = true,length = 50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "question_id", nullable = true,length = 50)
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	@Column(name = "report_time", nullable = true)
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	@Column(name = "url", nullable = true,length = 50)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Transient
	public AnswerName getAnswerName() {
		return answerName;
	}
	public void setAnswerName(AnswerName answerName) {
		this.answerName = answerName;
	}
	@OneToMany(mappedBy = "questionName", cascade = CascadeType.REMOVE)
	public List<AnswerName> getAnswerNameList() {
		return answerNameList;
	}
	public void setAnswerNameList(List<AnswerName> answerNameList) {
		this.answerNameList = answerNameList;
	}
	
	
	
	
	

}
