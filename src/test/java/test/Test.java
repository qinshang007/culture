package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private Map<String,String> propertyMap = new HashMap<String,String>(){{
		put("����", "title");
		put("��������", "used_title");
		put("����", "c_level");
	}};
	
	public void hh(){
		System.out.println(propertyMap.get("����"));
	}

	public static void main(String[]args){
		Test t = new Test();
		t.hh();
	}
	
}
