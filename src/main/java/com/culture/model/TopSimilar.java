package com.culture.model;

public class TopSimilar {
	
	private int id;	//序号
	
	private String identifier;	//文物标识符
	
	private String topSimilar;	//最相似的十件文物

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getTopSimilar() {
		return topSimilar;
	}

	public void setTopSimilar(String topSimilar) {
		this.topSimilar = topSimilar;
	}
	
}
