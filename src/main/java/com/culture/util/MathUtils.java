package com.culture.util;

import java.text.NumberFormat;

public class MathUtils {
	
	private static final double EARTH_RADIUS = 6378137;
	private static double rad(double d){
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 根据两个经纬度测出距离
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2, double lat2){
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		 return s;
	}
	
	public static double format(double value,int num){
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(num);
        double res = Double.valueOf(nf.format(value));
        return res;
	}
}
