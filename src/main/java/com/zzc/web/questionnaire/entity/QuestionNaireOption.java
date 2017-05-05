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
 * 问卷选项实体
 * @author 冯勇齐
 * @date 2017-01-03 09:35:55
 */

@Entity
@Table(name = "t_question_option")
@SuppressWarnings("serial")
public class QuestionNaireOption implements java.io.Serializable {

	
	private String id;
	private String optionName;
	private Integer num;
	private QuestionTopic questionTopic;
	
	
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
	//选项
	@Column(name = "option_name", nullable = true)
	public String getOptionName() {
		return optionName;
	}	
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	
	
	@Column(name = "num", nullable = true,length = 50)
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="topic_id")
	public QuestionTopic getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(QuestionTopic questionTopic) {
		this.questionTopic = questionTopic;
	}
	
	
}
