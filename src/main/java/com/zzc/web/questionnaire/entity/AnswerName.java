package com.zzc.web.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷统计，选项统计实体
 * @author 冯勇齐
 * @date 2017-01-07 11:50:55
 */



@Entity
@Table(name = "t_answer_name")
@SuppressWarnings("serial")
public class AnswerName implements java.io.Serializable {

	
	
	
	private String id;
	private String topicId;
	private String optionId;
	//private String questionNameId;
	private QuestionName questionName;
	
	
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
	@Column(name = "topic_answer_id", nullable = true,length = 50)
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	@Column(name = "option_answer_id", nullable = true,length = 50)
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="question_name_id")
	public QuestionName getQuestionName() {
		return questionName;
	}
	public void setQuestionName(QuestionName questionName) {
		this.questionName = questionName;
	}
//	@Column(name = "question_name_id", nullable = true,length = 50)
//	public String getQuestionNameId() {
//		return questionNameId;
//	}
//	public void setQuestionNameId(String questionNameId) {
//		this.questionNameId = questionNameId;
//	}

  
	
	
	
	
	
}
