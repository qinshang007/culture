package com.system.import_data;

import java.io.IOException;
import java.util.List;

import com.culture.model.CulturalBean;

public class Client {
	
	public static void main(String[]args) throws IOException{
		
		String xmlPath = "D:/apache-tomcat-8080/webapps/crelicBase/upload/file/D335FD4D0EF14E65B3E101373C1BB515.xlsx";
		String type = "器物";
		String username = "lyp";
		List<CulturalBean> cblist = new ReadExcel().readExcel(xmlPath,type,username);
		for(int i=0;i<cblist.size();i++){
			System.out.println(cblist.get(i).getTitle());
		}
	}
	
}
