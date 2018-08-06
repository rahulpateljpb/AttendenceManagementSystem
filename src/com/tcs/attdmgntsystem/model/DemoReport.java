package com.tcs.attdmgntsystem.model;

import java.util.Date;

public class DemoReport {
private int demoId;
private String demoName;
private Date fromDate;
private Date toDate;

public String getDemoName() {
	return demoName;
}
public void setDemoName(String demoName) {
	this.demoName = demoName;
}
public int getDemoId() {
	return demoId;
}
public void setDemoId(int demoId) {
	this.demoId = demoId;
}
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
public Date getToDate() {
	return toDate;
}
public void setToDate(Date toDate) {
	this.toDate = toDate;
}
}
