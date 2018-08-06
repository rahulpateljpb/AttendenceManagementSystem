package com.tcs.attdmgntsystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ModelAssociate {
	@NotNull(message="Enter Proper Associate Id") @Min(value=10000,message="Id must be minimum 5 digits")@Max(value=9999999,message="Id must be maximum 7 digits")
	private Integer associateId;
	@Size(min=4,max=30,message="Name must be between {min} and {max} characters")
	private String associateName;
	@Size(min=10,max=10, message="Mobile number must be 10 Digits")
	private String contactNumber;
	@NotEmpty(message="Enter Email") @Email(message= "Enter Well Formed Email")
	private String emailId;

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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}