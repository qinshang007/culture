package com.culture.model;

import java.sql.Timestamp;
import java.util.List;

public class OProperty {

	private int pid;					//����id
	
	private int pfid;					//������id
	
	private String pname;				//��������
	
	private int ptype;					//�������1Ϊ�������ԣ�2Ϊ��������
	
	private Timestamp time;				//���Դ���ʱ��
	
	private List<OClass> domainList;	//������
	
	private List<OClass> rangeList;		//ֵ��	
	
	private String domain;				//�������ַ���
	
	private String range;				//ֵ���ַ���
	
	private int isgeneral;				//�Ƿ���ͨ������
	
	private int del;					//�����Ƿ�ɾ����0��������1��ɾ��

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
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getIsgeneral() {
		return isgeneral;
	}

	public void setIsgeneral(int isgeneral) {
		this.isgeneral = isgeneral;
	}

}
