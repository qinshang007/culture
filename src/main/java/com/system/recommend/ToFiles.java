package com.system.recommend;

import java.io.FileWriter;
import java.io.IOException;

public class ToFiles {

	public static void main(String[] args){

		FileWriter filewriter;
		
		for(String tmp:GetDBData.getDetail()){
			
			Integer length = tmp.split(",").length;
			
			try {
  	    		filewriter=new FileWriter("D:/ProgramData/R/culture/files/"+ tmp.split(",")[length-1] +".txt");
	    		String newTmp =tmp.replaceAll(tmp.split(",")[length-1]+"","");
	    		filewriter.write(newTmp);
            	filewriter.flush();
            	filewriter.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	    
		
	}
	
}



