package com.tcs.attdmgntsystem.model;
import java.util.Date;
public class Report {
private Integer associateId;
private String associateName;
private Integer demoId;
private String demoName;
private String startDate;
private String endDate;
private String startTime;
private String endTime;

public Report(Integer associateId, String associateName, Integer demoId, String demoName, String startDate,
		String endDate, String startTime, String endTime) {
	super();
	this.associateId = associateId;
	this.associateName = associateName;
	this.demoId = demoId;
	this.demoName = demoName;
	this.startDate = startDate;
	this.endDate = endDate;
	this.startTime = startTime;
	this.endTime = endTime;
}
public Integer getAssociateId() {
	return associateId;
}
public void setAssociateId(Integer associateId) {
	this.associateId = associateId;
}
public String getAssociateName() {
	return associateName;
}
public void setAssociateName(String associateName) {
	this.associateName = associateName;
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
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
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

}
