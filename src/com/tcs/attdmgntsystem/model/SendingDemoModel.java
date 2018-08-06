package com.tcs.attdmgntsystem.model;

import java.util.Date;

import com.sun.istack.internal.NotNull;

public class SendingDemoModel {

	private Integer demoId;
	private String demoName;
	private Date startDateAndTime;
	private Date endDateAndTime;
	private String stringStartDateAndTime;
	private String stringEndDateAndTime;
	
	

	public String getStringStartDateAndTime() {
		return stringStartDateAndTime;
	}
	public void setStringStartDateAndTime(String stringStartDateAndTime) {
		this.stringStartDateAndTime = stringStartDateAndTime;
	}
	public String getStringEndDateAndTime() {
		return stringEndDateAndTime;
	}
	public void setStringEndDateAndTime(String stringEndDateAndTime) {
		this.stringEndDateAndTime = stringEndDateAndTime;
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
	public Date getStartDateAndTime() {
		return startDateAndTime;
	}
	public void setStartDateAndTime(Date startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}
	public Date getEndDateAndTime() {
		return endDateAndTime;
	}
	public void setEndDateAndTime(Date endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}
}
