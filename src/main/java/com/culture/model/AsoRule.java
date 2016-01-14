package com.culture.model;

/**
 * 关联规则
 * @author WZTCM
 *
 */
public class AsoRule {
	
	private String condition;	//条件
	
	private String conclusion;	//结论
	
	private String rule;		//关联规则
	
	private String support;		//支持度
	
	private String confidence;	//置信度

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

}
