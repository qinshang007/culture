package com.culture.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CulturalBean 
{
	private int id;							//id
	private String classification;			//类型
	private String title;					//名称
	private String used_title;				//其他名称
	private String c_level;					//级别
	private String creation_date;			//创作朝代
	private String creation_time;			//创作时间
	private String place_of_origin;			//产地
	private String creator;					//创作者
	private String measurement;				//度量
	private String excavation_date;			//出土时间
	private String excavation_place;		//出土地点
	private String current_location;		//现藏地点
	private String exhibition_history;		//展览历史
	private String location;				//地域环境,给建筑使用的
	
	private String shape;					//器形
	private String pattern;					//纹饰
	private String color;					//色彩
	private String structure;				//结构，建筑使用
	private String decoration;				//装饰，建筑使用

	
	private String scene;					//使用情境
	private String c_usage;					//使用方式
	private String symbolic_meaning;		//象征意义
	private String aesthetic_desc;			//审美
	private String social_history_info;		//社会历史信息，建筑使用
	
	private String material;				//材质
	private String technique;				//工艺
	
	private String history_info;			//历史信息
	private String folklore;				//民间典故
	
	private String relation;				//关联
	private String identifier;				//标识符
	private String source;					//资料来源
	private String rights;					//作品授权
	
	private int complete;					//是否编辑完成 ，1：未完成  2：完成
	private String manager;					//录入者
	private String type;					//大类，qiwu,zhiwu,jianzhu,bihua
	private Timestamp crtime;				//创建时间
	private int sernum;						//检索次数
	private String mainpic;					//主图
	
	private List<UploadFile> ewPicture;		//二维图片
	private List<UploadFile> swPicture;		//三维图片
	private List<UploadFile> video;			//视频
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsed_title() {
		return used_title;
	}
	public void setUsed_title(String used_title) {
		this.used_title = used_title;
	}
	public String getC_level() {
		return c_level;
	}
	public void setC_level(String c_level) {
		this.c_level = c_level;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getPlace_of_origin() {
		return place_of_origin;
	}
	public void setPlace_of_origin(String place_of_origin) {
		this.place_of_origin = place_of_origin;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getExcavation_date() {
		return excavation_date;
	}
	public void setExcavation_date(String excavation_date) {
		this.excavation_date = excavation_date;
	}
	public String getExcavation_place() {
		return excavation_place;
	}
	public void setExcavation_place(String excavation_place) {
		this.excavation_place = excavation_place;
	}
	public String getCurrent_location() {
		return current_location;
	}
	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}
	public String getExhibition_history() {
		return exhibition_history;
	}
	public void setExhibition_history(String exhibition_history) {
		this.exhibition_history = exhibition_history;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public String getC_usage() {
		return c_usage;
	}
	public void setC_usage(String c_usage) {
		this.c_usage = c_usage;
	}
	public String getSymbolic_meaning() {
		return symbolic_meaning;
	}
	public void setSymbolic_meaning(String symbolic_meaning) {
		this.symbolic_meaning = symbolic_meaning;
	}
	public String getAesthetic_desc() {
		return aesthetic_desc;
	}
	public void setAesthetic_desc(String aesthetic_desc) {
		this.aesthetic_desc = aesthetic_desc;
	}
	public String getSocial_history_info() {
		return social_history_info;
	}
	public void setSocial_history_info(String social_history_info) {
		this.social_history_info = social_history_info;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	public String getHistory_info() {
		return history_info;
	}
	public void setHistory_info(String history_info) {
		this.history_info = history_info;
	}
	public String getFolklore() {
		return folklore;
	}
	public void setFolklore(String folklore) {
		this.folklore = folklore;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public int getComplete() {
		return complete;
	}
	public void setComplete(int complete) {
		this.complete = complete;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getCrtime() {
		return crtime;
	}
	public void setCrtime(Timestamp crtime) {
		this.crtime = crtime;
	}
	public int getSernum() {
		return sernum;
	}
	public void setSernum(int sernum) {
		this.sernum = sernum;
	}
	public String getMainpic() {
		return mainpic;
	}
	public void setMainpic(String mainpic) {
		this.mainpic = mainpic;
	}
	public List<UploadFile> getEwPicture() {
		return ewPicture;
	}
	public void setEwPicture(List<UploadFile> ewPicture) {
		this.ewPicture = ewPicture;
	}
	public List<UploadFile> getSwPicture() {
		return swPicture;
	}
	public void setSwPicture(List<UploadFile> swPicture) {
		this.swPicture = swPicture;
	}
	public List<UploadFile> getVideo() {
		return video;
	}
	public void setVideo(List<UploadFile> video) {
		this.video = video;
	}
	
}
