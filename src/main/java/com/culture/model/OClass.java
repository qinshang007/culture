package com.culture.model;

import java.sql.Timestamp;

public class OClass {
	
	private int cid;					//概念id
	
	private int cfid;				//父概念id
	
	private String cname;				//概念名字
	
	private Timestamp time;				//概念创建时间
	
	private int selected;				//该概念是否被选中
	
	private int del;					//概念是否被删除，0：正常，1：删除

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
