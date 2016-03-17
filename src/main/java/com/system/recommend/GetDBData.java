package com.system.recommend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.culture.model.CulturalBean;

public class GetDBData {	

	static String sql = null;

	static DBConnect db = null;

	static ResultSet ret = null;

	public static ArrayList<String> getDetail(){
		ArrayList<String> detailList=new ArrayList<String> ();
		sql = "select classification,title,used_title,c_level,creation_date,creation_time,place_of_origin,creator,"
				+ "measurement,excavation_date,excavation_place,current_location,location,shape,pattern,color,structure,"
				+ "decoration,scene,c_usage,symbolic_meaning,aesthetic_desc,social_history_info,material,technique,history_info,"
				+ "folklore,relation,type,identifier from culture ";//SQL语句
		db = new DBConnect(sql);
		try {
			ret = db.pst.executeQuery();//执行语句，得到结果集
			while (ret.next()) {
				String classification = ret.getString(1);
				String title = ret.getString(2);
				String used_title=ret.getString(3);
				String c_level=ret.getString(4);
				String creation_date=ret.getString(5);
				String creation_time=ret.getString(6);
				String place_of_origin=ret.getString(7);
				String creator=ret.getString(8);
				String measurement=ret.getString(9);
				String excavation_date=ret.getString(10);
				String excavation_place=ret.getString(11);
				String current_location=ret.getString(12);
				String location=ret.getString(13);
				String shape=ret.getString(14);
				String pattern=ret.getString(15);
				String color=ret.getString(16);
				String structure=ret.getString(17);
				String decoration=ret.getString(18);
				String scene=ret.getString(19);
				String c_usage=ret.getString(20);
				String symbolic_meaning=ret.getString(21);
				String aesthetic_desc=ret.getString(22);
				String social_history_info=ret.getString(23);
				String material=ret.getString(24);
				String technique=ret.getString(25);
				String history_info=ret.getString(26);
				String folklore=ret.getString(27);
				String relation=ret.getString(28);
				String type=ret.getString(29);
				String identifier=ret.getString(30);
				
				detailList.add(classification + "," + title + "," + used_title + "," + c_level + ","+ creation_date + "," + creation_time + ","
						+ place_of_origin + "," + creator + "," + measurement + "," + excavation_date + "," + excavation_place + "," + current_location + "," 
						+ location + "," + shape + "," + pattern + "," + color + "," + structure + "," + decoration + "," + scene + "," + c_usage + "," 
						+ symbolic_meaning + "," + aesthetic_desc + "," + social_history_info + "," + material + "," + technique + "," + history_info + "," 
						+ folklore + "," + relation + "," + type + "," + identifier+ ",");
			}
			ret.close();
			db.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detailList;
	}

	public static HashMap<String,String> getAllCul(){
		HashMap<String,String> resMap = new HashMap<String,String>();
		ArrayList<String> detailList=new ArrayList<String> ();
		sql = "select classification,title,used_title,c_level,creation_date,creation_time,place_of_origin,creator,"
				+ "measurement,excavation_date,excavation_place,current_location,location,shape,pattern,color,structure,"
				+ "decoration,scene,c_usage,symbolic_meaning,aesthetic_desc,social_history_info,material,technique,history_info,"
				+ "folklore,relation,type,identifier from culture ";//SQL语句
		db = new DBConnect(sql);
		try {
			ret = db.pst.executeQuery();//执行语句，得到结果集
			while (ret.next()) {
				String classification = ret.getString(1);
				String title = ret.getString(2);
				String used_title=ret.getString(3);
				String c_level=ret.getString(4);
				String creation_date=ret.getString(5);
				String creation_time=ret.getString(6);
				String place_of_origin=ret.getString(7);
				String creator=ret.getString(8);
				String measurement=ret.getString(9);
				String excavation_date=ret.getString(10);
				String excavation_place=ret.getString(11);
				String current_location=ret.getString(12);
				String location=ret.getString(13);
				String shape=ret.getString(14);
				String pattern=ret.getString(15);
				String color=ret.getString(16);
				String structure=ret.getString(17);
				String decoration=ret.getString(18);
				String scene=ret.getString(19);
				String c_usage=ret.getString(20);
				String symbolic_meaning=ret.getString(21);
				String aesthetic_desc=ret.getString(22);
				String social_history_info=ret.getString(23);
				String material=ret.getString(24);
				String technique=ret.getString(25);
				String history_info=ret.getString(26);
				String folklore=ret.getString(27);
				String relation=ret.getString(28);
				String type=ret.getString(29);
				String identifier=ret.getString(30);
				
				String detail = classification + "," + title + "," + used_title + "," + c_level + ","+ creation_date + "," + creation_time + ","
						+ place_of_origin + "," + creator + "," + measurement + "," + excavation_date + "," + excavation_place + "," + current_location + "," 
						+ location + "," + shape + "," + pattern + "," + color + "," + structure + "," + decoration + "," + scene + "," + c_usage + "," 
						+ symbolic_meaning + "," + aesthetic_desc + "," + social_history_info + "," + material + "," + technique + "," + history_info + "," 
						+ folklore + "," + relation + "," + type + "," ;//+ identifier+ ",";
				resMap.put(identifier, detail);
			}
			ret.close();
			db.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resMap;
	}

	
	public static List<CulturalBean> selectAll() throws SQLException {
		
		List<CulturalBean> cbList = new ArrayList<CulturalBean>();
		sql = "select id,identifier,keywords,classification,title,used_title,c_level from culture ";
			   
	    try {
	    	db = new DBConnect(sql);	        
	    	ret = db.pst.executeQuery();
	    	while(ret.next()){
            	CulturalBean cb = new CulturalBean();
            	int id = ret.getInt("id");
            	String identifier = ret.getString("identifier");
            	String keywords = ret.getString("keywords");
            	String classification = ret.getString("classification");
            	String title = ret.getString("title");
            	String used_title = ret.getString("used_title");
            	String c_level = ret.getString("c_level");
            	cb.setId(id);
            	cb.setIdentifier(identifier);
            	cb.setKeywords(keywords);
            	cb.setClassification(classification);
            	cb.setTitle(title);
            	cb.setUsed_title(used_title);
            	cb.setC_level(c_level);
            	cbList.add(cb);
            }
	    	
	    	ret.close();
			db.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	    return cbList;
	}	
	
	  

}







