package com.gatepass.bo;

import java.io.Serializable;
import java.util.Date;

public class SearchGatePassBO implements Serializable {
protected int flatNo;
protected int blockNo;
protected Date fromDate;
protected Date toDate;
protected String status;

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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}



}
