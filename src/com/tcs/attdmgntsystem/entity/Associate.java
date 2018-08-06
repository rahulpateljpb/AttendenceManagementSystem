package com.tcs.attdmgntsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Associate {

	@Id
	private Integer associateId;
	@Column
	private String associateName;
	private String associateContact;
	private String associateEmail;
	
	
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
	public String getAssociateContact() {
		return associateContact;
	}
	public void setAssociateContact(String associateContact) {
		this.associateContact = associateContact;
	}
	public String getAssociateEmail() {
		return associateEmail;
	}
	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}
}
