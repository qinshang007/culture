package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args){
		String cid = "123";
		String str = "0,123";
		int index = str.indexOf(cid);
		System.out.println(index);
		String tail = str.substring(index+cid.length());
		System.out.println("0"+tail);
	}
	
}
