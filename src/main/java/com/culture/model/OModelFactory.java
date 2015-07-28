package com.culture.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OModelFactory {
	
	//owl�ļ�·��
	private String owlFile;
	
	//ʵ���ļ�·��
	private String instanceFile;
	
	//�������ռ�
	public static final String NSC = "http://culture.zju.edu.cn/class#";
	
	//���������ռ�
	public static final String NSP = "http://culture.zju.edu.cn/property#";
	
	//ʵ�������ռ�
	public static final String NSI = "http://culture.zju.edu.cn/instance#";
	
	public String getOwlFile() {
		return owlFile;
	}

	public void setOwlFile(String owlFile) {
		this.owlFile = owlFile;
	}

	public String getInstanceFile() {
		return instanceFile;
	}

	public void setInstanceFile(String instanceFile) {
		this.instanceFile = instanceFile;
	}

	//�õ�����ģ��
	public OntModel getModel() {
		OntModel model = ModelFactory.createOntologyModel();
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		File owl = new File(owlFile);
		//��������ļ����ڵĻ����������ļ�����ģ��
		if(owl.exists())
		 try {
			model.read(new FileInputStream(owlFile),"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	//�õ�ʵ��ģ��
	public OntModel getInstanceModel() {
		OntModel model = ModelFactory.createOntologyModel();
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		model.setNsPrefix("NSI", NSI);
		File owl = new File(instanceFile);
		//��������ļ����ڵĻ����������ļ�����ģ��
		if(owl.exists())
		 try {
			model.read(new FileInputStream(instanceFile),"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	
}
