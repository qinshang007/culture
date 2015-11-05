package com.culture.model;

import java.sql.Timestamp;

public class OClass {
	
	private int cid;					//概念id
	
	private int cfid;					//父概念id
	
	private String cname;				//概念名字
	
	private Timestamp time;				//创建时间
	
	private int selected;				//是否被选择
	
	private int del;					//删除标志，1表示删除，0表示正常

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCfid() {
		return cfid;
	}

	public void setCfid(int cfid) {
		this.cfid = cfid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

}
