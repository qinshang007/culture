package com.culture.model;

import java.io.Serializable;

public class UploadFile implements Serializable{
	
	private String identifier;		//��������ı�ʶ��
	private String fileId;			//�ļ�id
	private String fileName;		//�ļ�����
	private String fileType;		//�ļ�����
	private long fileSize;			//�ļ���С
	private String fileSrc;			//�ļ�·��
	private String fileDesc;		//�ļ�����
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileSrc() {
		return fileSrc;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
}
