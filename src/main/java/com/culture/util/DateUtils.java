package com.culture.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public final static String YYYY = "yyyy";
	public final static String MM = "MM";
	public final static String DD = "dd";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYY_MM = "yyyy-MM";
	public final static String HH_MM_SS = "HH:mm:ss";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * 默认把日期格式化成yyyy-mm-dd格式
	 */
	public static String format(Date date) {
		if (date == null){
			return "";
		}else{
			return getFormatter(YYYY_MM_DD).format(date);
		}
	}

	/**
	 * 日期格式化
	 */
	public static String format(Date date, String pattern) {
		if (date == null){
			return "";
		}else{
			return getFormatter(pattern).format(date);
		}
	}
	
	/**
	 * 把字符串日期默认转换为yyyy-mm-dd格式的Data对象
	 */
	public static Date format(String strDate) {
		Date date = null;
		if (strDate == ""){
			return null;
		}else{
			try {
				date = getFormatter(YYYY_MM_DD).parse(strDate);
			} catch (ParseException pex) {
				return null;
			}
		}
		return date;
	}

	/**
	 * 把字符串日期转换为f指定格式的Data对象
	 */
	public static Date format(String strDate, String format) {
		Date date = null;
		if (strDate == ""){
			return null;
		}else{
			try {
				date = getFormatter(format).parse(strDate);
			} catch (ParseException pex) {
				return null;
			}
		}
		return date;
	}

	/**
	 * 日期解析－将<code>String</code>类型的日期解析为<code>Date</code>型
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		try {
			return getFormatter(pattern).parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException("Method parse in Class DateUtils  err: parse strDate fail.",pe.getErrorOffset());
		}
	}

	/**
	 * 获取当前日期
	 */
	public static synchronized Date getCurrDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 获取当前日期
	 */
	public static String getCurrDateStr() {
		return format(getCurrDate(), YYYY_MM_DD);
	}
	
	
	public static String getCurrDateStr(String pattern) {
		return format(getCurrDate(), pattern);
	}

	/**
	 * 获取当前时间
	 */
	public static String getCurrTimeStr() {
		return format(getCurrDate(), HH_MM_SS);
	}

	/**
	 * 获取当前完整时间,样式: yyyy－MM－dd hh:mm:ss
	 */
	public static String getCurrDateTimeStr() {
		return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取当前年份 样式：yyyy
	 */
	public static String getYear() {
		return format(getCurrDate(), YYYY);
	}

	/**
	 * 获取当前月份：MM
	 */
	public static String getMonth() {
		return format(getCurrDate(), MM);
	}

	/**
	 * 获取当前日期天
	 */
	public static String getDay() {
		return format(getCurrDate(), DD);
	}

	/**
	 * 按给定日期样式判断给定字符串是否为合法日期数据
	 */
	public static boolean isDate(String strDate, String pattern) {
		try {
			parse(strDate, pattern);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}
	
	/**
	 * 判断给定字符串是否为特定格式年份（格式：yyyy）数据
	 */
	public static boolean isYYYY(String strDate) {
		try {
			parse(strDate, YYYY);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	public static boolean isYYYY_MM(String strDate) {
		try {
			parse(strDate, YYYY_MM);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 判断给定字符串是否为特定格式的年月日（格式：yyyy-MM-dd）数据
	 */
	public static boolean isYYYY_MM_DD(String strDate) {
		try {
			parse(strDate, YYYY_MM_DD);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 判断给定字符串是否为特定格式年月日时分秒（格式：yyyy-MM-dd HH:mm:ss）数据
	 * @return true 如果是，否则返回false
	 */
	public static boolean isYYYY_MM_DD_HH_MM_SS(String strDate) {
		try {
			parse(strDate, YYYY_MM_DD_HH_MM_SS);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 判断给定字符串是否为特定格式时分秒（格式：HH:mm:ss）数据
	 */
	public static boolean isHH_MM_SS(String strDate) {
		try {
			parse(strDate, HH_MM_SS);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 判断给定字符串是否为特定格式时间（包括：时分秒hh:mm:ss）数据
	 */
	public static boolean isTime(String strTime) {
		try {
			parse(strTime, HH_MM_SS);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 判断给定字符串是否为特定格式日期时间（包括：年月日时分秒 yyyy-MM-dd hh:mm:ss）数据
	 */
	public static boolean isDateTime(String strDateTime) {
		try {
			parse(strDateTime, YYYY_MM_DD_HH_MM_SS);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}

	/**
	 * 获取一个简单的日期格式化对象
	 */
	private static SimpleDateFormat getFormatter(String parttern) {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 获取给定日前的后intevalDay天的日期
	 * @param refenceDate：给定日期（格式为：yyyy-MM-dd）
	 * @param intevalDays：间隔天数
	 * @return 计算后的日期
	 */
	public static String addDate(String refenceDate, int intevalDays) {
		try {
			return addDate(parse(refenceDate, YYYY_MM_DD), intevalDays);
		} catch (Exception ee) {
			return "";
		}
	}

	/**
	 * 获取给定日前的后intevalDay天的日期
	 * @param refenceDate： Date 给定日期
	 * @param intevalDays：int 间隔天数
	 * @return String 计算后的日期
	 */
	public static String addDate(Date refenceDate, int intevalDays) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(refenceDate);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+ intevalDays);
			return format(calendar.getTime(), YYYY_MM_DD);
		} catch (Exception ee) {
			return "";
		}
	}
	/*
	 * 计算间隔时间
	 * Field: Calendar定义的变量值，例如：Calendar.MINUTE
	 */
	public static String addDate(Date refenceDate, int Field,int intevalVal) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(refenceDate);
			calendar.add(Field, intevalVal);
			return format(calendar.getTime(), YYYY_MM_DD_HH_MM_SS);
		} catch (Exception ee) {
			return "";
		}
	}
	
	public static String addDate(Date refenceDate, int Field,int intevalVal,String format) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(refenceDate);
			calendar.add(Field, intevalVal);
			return format(calendar.getTime(), format);
		} catch (Exception ee) {
			return "";
		}
	}

	public static long getIntevalDays(String startDate, String endDate) {
		try {
			return getIntevalDays(parse(startDate, YYYY_MM_DD), parse(endDate,YYYY_MM_DD));
		} catch (Exception ee) {
			return 0l;
		}
	}

	public static long getIntevalDays(Date startDate, Date endDate) {
		try {
			return (endDate.getTime()-startDate.getTime())/(24 * 60 * 60 * 1000);
		} catch (Exception ee) {
			return 0l;
		}
	}
	/*
	 * 得到工作日数:除去星期6和星期天
	 */
	public static long getWorkDays(Date startDate, Date endDate) {
		try{
			if (startDate.compareTo(endDate)>0) return 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			int l = 24 * 3600 * 1000;
			long days = Math.abs(endDate.getTime()-startDate.getTime()) / l;
			long n = days % 7;
			if (n + week == 7 || week == 7) {
			    n = (n - 1) > 0 ? n - 1 : 0;
			}else if (n + week > 7) {
			    n = n - 2;
			}
			days = (days - days % 7) * 5 / 7 + n;
			return days;
		}catch (Exception ee) {
			return 0;
		}
    }

	/**
	 * 求当前日期和指定字符串日期的相差天数
	 */
	public static long getIntevalDaysToNow(String startDate) {
		try {
			// 当前时间
			Date currentDate = new Date();
			// 指定日期
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date theDate = myFormatter.parse(startDate);
			// 两个时间之间的天数
			long days = (currentDate.getTime() - theDate.getTime())/ (24 * 60 * 60 * 1000);
			return days;
		} catch (Exception ee) {
			return 0l;
		}
	}
	/*
	 * 得到当前日期
	 */
	public static String getCurrentDate(String parttern) {
		try {
			Date currentDate = new Date();// 当前时间
			SimpleDateFormat myFormatter = new SimpleDateFormat(parttern);
			return myFormatter.format(currentDate);
		} catch (Exception ee) {
			return "";
		}
	}

	public static String dateTimeToString(Date datetime) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(datetime);
		String dateTime = calendar.get(Calendar.YEAR) + ""
				+ (calendar.get(Calendar.MONTH) + 1 > 9 ? "" : "0")
				+ (calendar.get(Calendar.MONTH) + 1) + ""
				+ (calendar.get(Calendar.DATE) > 9 ? "" : "0")
				+ calendar.get(Calendar.DATE) + ""
				+ (calendar.get(Calendar.HOUR_OF_DAY) > 9 ? "" : "0")
				+ calendar.get(Calendar.HOUR_OF_DAY) + ""
				+ (calendar.get(Calendar.MINUTE) > 9 ? "" : "0")
				+ calendar.get(Calendar.MINUTE) + ""
				+ (calendar.get(Calendar.SECOND) > 9 ? "" : "0")
				+ calendar.get(Calendar.SECOND);
		return dateTime;
	}
	
	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();// 当前时间
		System.out.println(DateUtils.getCurrDateStr(YYYYMMDD));
	}
}
