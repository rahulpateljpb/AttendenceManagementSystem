package com.tcs.attdmgntsystem.model;

import java.util.Date;

import com.sun.istack.internal.NotNull;


public class DemoDetailModel {
	@NotNull
	private Integer demoId;
	@NotNull
	private String demoName;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	@NotNull
	private String startTime;
	@NotNull
	private String endTime;


	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getDemoId() {
		return demoId;
	}
	public void setDemoId(Integer demoId) {
		this.demoId = demoId;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}


