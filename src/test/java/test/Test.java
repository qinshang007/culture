package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private Map<String,String> propertyMap = new HashMap<String,String>(){{
		put("名称", "title");
		put("其他名称", "used_title");
		put("级别", "c_level");
	}};
	
	public void hh(){
		System.out.println(propertyMap.get("名称"));
	}

	public static void main(String[]args){
		Test t = new Test();
		t.hh();
	}
	
}
