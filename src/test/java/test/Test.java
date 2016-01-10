package test;

import java.text.DecimalFormat;

public class Test {
	
	public static void main(String[] args){
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		int a = 5817;
		int b = 6624;
		float c = (b-a)/(float)b;
		System.out.println(df.format(c));
	}
	
}
