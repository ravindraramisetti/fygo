package com.gatepass.bo;

import java.io.Serializable;
import java.util.Date;

public class GatePassBO implements Serializable  {
protected String visitorName;
protected String mobileNo;
protected String emailAddress;
protected String purpose;
protected Date expectedEntryTime;
protected String whomToMeet;
protected int flatNo;
protected int blockNo;
protected String status;


public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getVisitorName() {
	return visitorName;
}
public void setVisitorName(String visitorName) {
	this.visitorName = visitorName;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public Date getExpectedEntryTime() {
	return expectedEntryTime;
}
public void setExpectedEntryTime(Date expectedEntryTime) {
	this.expectedEntryTime = expectedEntryTime;
}
public String getWhomToMeet() {
	return whomToMeet;
}
public void setWhomToMeet(String whomToMeet) {
	this.whomToMeet = whomToMeet;
}
public int getFlatNo() {
	return flatNo;
}
public void setFlatNo(int flatNo) {
	this.flatNo = flatNo;
}
public int getBlockNo() {
	return blockNo;
}
public void setBlockNo(int blockNo) {
	this.blockNo = blockNo;
}
public GatePassBO(String visitorName, String mobileNo, String emailAddress, String purpose, Date expectedEntryTime,
		String whomToMeet, int flatNo, int blockNo, String status) { 
	this.visitorName = visitorName;
	this.mobileNo = mobileNo;
	this.emailAddress = emailAddress;
	this.purpose = purpose;
	this.expectedEntryTime = expectedEntryTime;
	this.whomToMeet = whomToMeet;
	this.flatNo = flatNo;
	this.blockNo = blockNo;
	this.status = status;
}


}
