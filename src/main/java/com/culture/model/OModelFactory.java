package com.culture.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class OModelFactory {
	
	//owl文件路径
	private String owlFile;
	
	//实例文件路径
	private String instanceFile;
	
	//规则文件路径
	private String ruleFile;
	
	//类命名空间
	public static final String NSC = "http://culture.zju.edu.cn/class#";
	
	//属性命名空间
	public static final String NSP = "http://culture.zju.edu.cn/property#";
	
	//实例命名空间
	public static final String NSI = "http://culture.zju.edu.cn/instance#";
	
	//数据类型
	public static Resource[] resource = new Resource[]{XSD.xstring,XSD.xint,XSD.xfloat,XSD.date};
	
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
	
	public String getRuleFile() {
		return ruleFile;
	}

	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
	}

	//得到本体模型
	public OntModel getModel() {
		OntModel model = ModelFactory.createOntologyModel();
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		File owl = new File(owlFile);
		//如果本体文件存在的话，将本体文件读入模型
		if(owl.exists())
		 try {
			model.read(new FileInputStream(owlFile),"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	//得到实例模型
	public OntModel getInstanceModel() {
		OntModel model = ModelFactory.createOntologyModel();
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		model.setNsPrefix("NSI", NSI);
		File owl = new File(instanceFile);
		//如果本体文件存在的话，将本体文件读入模型
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
