package com.zzc.web.scenicmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_scenicSpot_ticket")
@SuppressWarnings("serial")
public class ScenicSpotTicket implements java.io.Serializable {
		private String id;
		private String type;
		private String beginMonth;
		private String endMonth;
		private String beginDate;
		private String endDate;
		private String openHour;
		private String closeHour;
		private String openMinute;
		private String closeMinute;
		private String price;
		private ScenicData scenicData;
		
		@Id
		@GeneratedValue(generator = "paymentableGenerator")
		@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
		@Column(name ="id",nullable=false,length=50)
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name ="sid")
		public ScenicData getScenicData() {
			return scenicData;
		}
		public void setScenicData(ScenicData scenicData) {
			this.scenicData = scenicData;
		}
		@Column(name="trem_type")
		public String getType() {
			return type;
		}
		@Column(name="begin_month")
		public String getBeginMonth() {
			return beginMonth;
		}
		public void setBeginMonth(String beginMonth) {
			this.beginMonth = beginMonth;
		}
		@Column(name="end_month")
		public String getEndMonth() {
			return endMonth;
		}
		public void setEndMonth(String endMonth) {
			this.endMonth = endMonth;
		}
		@Column(name="begin_date")
		public String getBeginDate() {
			return beginDate;
		}
		public void setBeginDate(String beginDate) {
			this.beginDate = beginDate;
		}
		@Column(name="end_date")
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		@Column(name="open_hour")
		public String getOpenHour() {
			return openHour;
		}
		public void setOpenHour(String openHour) {
			this.openHour = openHour;
		}
		@Column(name="close_hour")
		public String getCloseHour() {
			return closeHour;
		}
		public void setCloseHour(String closeHour) {
			this.closeHour = closeHour;
		}
		@Column(name="open_minute")
		public String getOpenMinute() {
			return openMinute;
		}
		public void setOpenMinute(String openMinute) {
			this.openMinute = openMinute;
		}
		@Column(name="close_minute")
		public String getCloseMinute() {
			return closeMinute;
		}
		public void setCloseMinute(String closeMinute) {
			this.closeMinute = closeMinute;
		}
		@Column(name="term_type")
		public void setType(String type) {
			this.type = type;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
		
}
