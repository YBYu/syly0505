package com.zzc.web.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zzc.core.common.entity.IdEntity;

@Entity
@Table(name="t_travelagency_quarterly1_record")
public class TravelQuarterOutRecord extends IdEntity{
	
	 private String code;
	 private String year;
	 private String quarter;
	 private String unitMaster; 
	 private String filler;
	 private String fillerTel; 
	 private String sortID; 
	 private String seasonDay; 
	 private String seasonAdmitDay; 
	 private String season; 
	 private String seasonAdmit;
	 private String remark;
	 
	@Column
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="unit_master")
	public String getUnitMaster() {
		return unitMaster;
	}
	public void setUnitMaster(String unitMaster) {
		this.unitMaster = unitMaster;
	}
	@Column
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Column(name="filler_tel")
	public String getFillerTel() {
		return fillerTel;
	}
	public void setFillerTel(String fillerTel) {
		this.fillerTel = fillerTel;
	}
	@Column(name="sort_id")
	public String getSortID() {
		return sortID;
	}
	public void setSortID(String sortID) {
		this.sortID = sortID;
	}
	@Column(name="season_day")
	public String getSeasonDay() {
		return seasonDay;
	}
	public void setSeasonDay(String seasonDay) {
		this.seasonDay = seasonDay;
	}
	@Column(name="season_admitday")
	public String getSeasonAdmitDay() {
		return seasonAdmitDay;
	}
	public void setSeasonAdmitDay(String seasonAdmitDay) {
		this.seasonAdmitDay = seasonAdmitDay;
	}
	@Column
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	@Column(name="season_admit")
	public String getSeasonAdmit() {
		return seasonAdmit;
	}
	public void setSeasonAdmit(String seasonAdmit) {
		this.seasonAdmit = seasonAdmit;
	}
	@Column
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	 
	 
}
