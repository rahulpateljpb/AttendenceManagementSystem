package com.tcs.attdmgntsystem.model;
import java.util.Date;
public class ModelDemo {
	private Integer demoId;
	private String demoName;
	private String demoDate;
	private String demoDuration;
	private String startDateTime;
	private String endDateTime;
	private String demoEndDate;
	public ModelDemo(Integer demoId, String demoName, String demoDate, String demoDuration, String startDateTime) {
		super();
		this.demoId = demoId;
		this.demoName = demoName;
		this.demoDate = demoDate;
		this.demoDuration = demoDuration;
		this.startDateTime = startDateTime;
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
	public String getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(String demoDate) {
		this.demoDate = demoDate;
	}
	public String getDemoDuration() {
		return demoDuration;
	}
	public void setDemoDuration(String demoDuration) {
		this.demoDuration = demoDuration;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public ModelDemo() {
		super();
	}
	public String getDemoEndDate() {
		return demoEndDate;
	}
	public void setDemoEndDate(String demoEndDate) {
		this.demoEndDate = demoEndDate;
	} 
	
}
