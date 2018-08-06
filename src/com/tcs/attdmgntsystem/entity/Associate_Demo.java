package com.tcs.attdmgntsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Associate_Demo {
	@Id
	@GeneratedValue
	private Integer mappingId;
	@Column
	private Integer associate_Id;
	private Integer demo_Id;

	
	
	public Integer getMappingId() {
		return mappingId;
	}
	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}
	public Integer getAssociate_Id() {
		return associate_Id;
	}
	public void setAssociate_Id(Integer associate_Id) {
		this.associate_Id = associate_Id;
	}
	public Integer getDemo_Id() {
		return demo_Id;
	}
	public void setDemo_Id(Integer demo_Id) {
		this.demo_Id = demo_Id;
	}
}
