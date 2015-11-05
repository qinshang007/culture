package com.culture.model;

import java.sql.Timestamp;

public class ORule {
	
	private int rid;	//规则id
	
	private String rname;	//规则名字
	
	private int type;		//规则类型，1表示一元规则，2表示二元规则
	
	private String rcontent;	//规则内容
	
	private String rule;	//jean规则形式
	
	private String opBodyName1;	//条件属性名1
	
	private String opBodyValue1;	//条件属性值1
	
	private String opBodyName2;	//	条件属性名2
	
	private String opBodyValue2; //条件属性值2
	
	private String opHeadName;	//结论属性名
	
	private String opHeadValue;	//结论属性值
	
	private Timestamp time;				//创建时间
	
	private int del;	//是否删除，1表示删除，0表示正常

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getOpBodyName1() {
		return opBodyName1;
	}

	public void setOpBodyName1(String opBodyName1) {
		this.opBodyName1 = opBodyName1;
	}

	public String getOpBodyValue1() {
		return opBodyValue1;
	}

	public void setOpBodyValue1(String opBodyValue1) {
		this.opBodyValue1 = opBodyValue1;
	}

	public String getOpBodyName2() {
		return opBodyName2;
	}

	public void setOpBodyName2(String opBodyName2) {
		this.opBodyName2 = opBodyName2;
	}

	public String getOpBodyValue2() {
		return opBodyValue2;
	}

	public void setOpBodyValue2(String opBodyValue2) {
		this.opBodyValue2 = opBodyValue2;
	}

	public String getOpHeadName() {
		return opHeadName;
	}

	public void setOpHeadName(String opHeadName) {
		this.opHeadName = opHeadName;
	}

	public String getOpHeadValue() {
		return opHeadValue;
	}

	public void setOpHeadValue(String opHeadValue) {
		this.opHeadValue = opHeadValue;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
