package com.culture.model;

import java.sql.Timestamp;
import java.util.List;

public class OProperty {

	private int pid;					//属性id
	
	private int pfid;					//父属性id
	
	private String pfname;				//父属性名字
	
	private String pname;				//属性名字
	
	private int ptype;					//属性类别，1为对象属性，2为数据属性
	
	private Timestamp time;				//属性创建时间
	
	private List<OClass> domainList;	//定义域
	
	private List<OClass> rangeList;		//值域	
	
	private String pdomain;				//定义域字符串
	
	private String prange;				//值域字符串
	
	private int isgeneral;				//是否是通用属性
	
	private int del;					//属性是否被删除，0：正常，1：删除
	
	private String username;			//录入人员

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPfid() {
		return pfid;
	}

	public void setPfid(int pfid) {
		this.pfid = pfid;
	}

	public String getPfname() {
		return pfname;
	}

	public void setPfname(String pfname) {
		this.pfname = pfname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public List<OClass> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<OClass> domainList) {
		this.domainList = domainList;
	}

	public List<OClass> getRangeList() {
		return rangeList;
	}

	public void setRangeList(List<OClass> rangeList) {
		this.rangeList = rangeList;
	}

	public int getPtype() {
		return ptype;
	}

	public void setPtype(int ptype) {
		this.ptype = ptype;
	}
	
	public String getPdomain() {
		return pdomain;
	}

	public void setPdomain(String pdomain) {
		this.pdomain = pdomain;
	}

	public String getPrange() {
		return prange;
	}

	public void setPrange(String prange) {
		this.prange = prange;
	}

	public int getIsgeneral() {
		return isgeneral;
	}

	public void setIsgeneral(int isgeneral) {
		this.isgeneral = isgeneral;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
