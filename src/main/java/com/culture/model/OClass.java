package com.culture.model;

import java.sql.Timestamp;

public class OClass {
	
	private int cid;					//����id
	
	private int cfid;				//������id
	
	private String cname;				//��������
	
	private Timestamp time;				//�����ʱ��
	
	private int selected;				//�ø����Ƿ�ѡ��
	
	private int del;					//�����Ƿ�ɾ����0��������1��ɾ��

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
