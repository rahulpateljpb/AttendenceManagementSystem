package com.tcs.attdmgntsystem.model;

import java.util.Date;

public class ModelAttendance {
	private Integer associateId;
	private Integer demoId;
//	private Date demoDate;
	public int getAssociateId() {
		return associateId;
	}
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}
	public int getDemoId() {
		return demoId;
	}
	public void setDemoId(int demoId) {
		this.demoId = demoId;
	}
/*	public Date getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(Date demoDate) {
		this.demoDate = demoDate;
	}*/
}
