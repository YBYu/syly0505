package com.zzc.web.questionnaire.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷题目实体类
 * @author 冯勇齐
 * @date 2017-01-08 18:35:36
 */

@Entity
@Table(name = "t_question_topic")
@SuppressWarnings("serial")
public class QuestionTopic implements java.io.Serializable {
	
	

	private String id;
	private String sid;
	private String dataId;
	private String topicName;
	private String name;
	private String answer;
	private Integer num;
	private QuestionData questionData;
	private QuestionNaireOption questionNaireOption;
	private List<QuestionNaireOption>questionNaireOptionList;
	
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
	
	
	
	
	
	
	@Column(name ="sid", nullable = true, length = 50)
	public String getSid() {   
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	//@Column(name ="data_id", nullable = true, length = 20)
	public String getDataId() {
		return dataId;  
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	//题目
	@Column(name = "topic_name", nullable = true, length = 20)
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Column(name = "name", nullable = true, length = 20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "answer", nullable = true, length = 50)
	public String getAnswer() {  
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "num", nullable = true, length = 50)
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="data_id")
	public QuestionData getQuestionData() {
		return questionData;
	}
	public void setQuestionData(QuestionData questionData) {
		this.questionData = questionData;
	}
	
	
	@Transient
	public QuestionNaireOption getQuestionNaireOption() {
		return questionNaireOption;
	}
	public void setQuestionNaireOption(QuestionNaireOption questionNaireOption) {
		this.questionNaireOption = questionNaireOption;
	}
	
	
	@OneToMany(mappedBy = "questionTopic", cascade = CascadeType.REMOVE)
	public List<QuestionNaireOption> getQuestionNaireOptionList() {
		return questionNaireOptionList;
	}
	public void setQuestionNaireOptionList(
			List<QuestionNaireOption> questionNaireOptionList) {
		this.questionNaireOptionList = questionNaireOptionList;
	}

	
	
	
	
	

}
