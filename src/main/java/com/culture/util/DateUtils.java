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
	 * Ĭ�ϰ����ڸ�ʽ����yyyy-mm-dd��ʽ
	 */
	public static String format(Date date) {
		if (date == null){
			return "";
		}else{
			return getFormatter(YYYY_MM_DD).format(date);
		}
	}

	/**
	 * ���ڸ�ʽ��
	 */
	public static String format(Date date, String pattern) {
		if (date == null){
			return "";
		}else{
			return getFormatter(pattern).format(date);
		}
	}
	
	/**
	 * ���ַ�������Ĭ��ת��Ϊyyyy-mm-dd��ʽ��Data����
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
	 * ���ַ�������ת��Ϊfָ����ʽ��Data����
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
	 * ���ڽ�������<code>String</code>���͵����ڽ���Ϊ<code>Date</code>��
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		try {
			return getFormatter(pattern).parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException("Method parse in Class DateUtils  err: parse strDate fail.",pe.getErrorOffset());
		}
	}

	/**
	 * ��ȡ��ǰ����
	 */
	public static synchronized Date getCurrDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * ��ȡ��ǰ����
	 */
	public static String getCurrDateStr() {
		return format(getCurrDate(), YYYY_MM_DD);
	}
	
	
	public static String getCurrDateStr(String pattern) {
		return format(getCurrDate(), pattern);
	}

	/**
	 * ��ȡ��ǰʱ��
	 */
	public static String getCurrTimeStr() {
		return format(getCurrDate(), HH_MM_SS);
	}

	/**
	 * ��ȡ��ǰ����ʱ��,��ʽ: yyyy��MM��dd hh:mm:ss
	 */
	public static String getCurrDateTimeStr() {
		return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * ��ȡ��ǰ��� ��ʽ��yyyy
	 */
	public static String getYear() {
		return format(getCurrDate(), YYYY);
	}

	/**
	 * ��ȡ��ǰ�·ݣ�MM
	 */
	public static String getMonth() {
		return format(getCurrDate(), MM);
	}

	/**
	 * ��ȡ��ǰ������
	 */
	public static String getDay() {
		return format(getCurrDate(), DD);
	}

	/**
	 * ������������ʽ�жϸ����ַ����Ƿ�Ϊ�Ϸ���������
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽ��ݣ���ʽ��yyyy������
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽ�������գ���ʽ��yyyy-MM-dd������
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽ������ʱ���루��ʽ��yyyy-MM-dd HH:mm:ss������
	 * @return true ����ǣ����򷵻�false
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽʱ���루��ʽ��HH:mm:ss������
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽʱ�䣨������ʱ����hh:mm:ss������
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
	 * �жϸ����ַ����Ƿ�Ϊ�ض���ʽ����ʱ�䣨������������ʱ���� yyyy-MM-dd hh:mm:ss������
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
	 * ��ȡһ���򵥵����ڸ�ʽ������
	 */
	private static SimpleDateFormat getFormatter(String parttern) {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * ��ȡ������ǰ�ĺ�intevalDay�������
	 * @param refenceDate���������ڣ���ʽΪ��yyyy-MM-dd��
	 * @param intevalDays���������
	 * @return ����������
	 */
	public static String addDate(String refenceDate, int intevalDays) {
		try {
			return addDate(parse(refenceDate, YYYY_MM_DD), intevalDays);
		} catch (Exception ee) {
			return "";
		}
	}

	/**
	 * ��ȡ������ǰ�ĺ�intevalDay�������
	 * @param refenceDate�� Date ��������
	 * @param intevalDays��int �������
	 * @return String ����������
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
	 * ������ʱ��
	 * Field: Calendar����ı���ֵ�����磺Calendar.MINUTE
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
	 * �õ���������:��ȥ����6��������
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
	 * ��ǰ���ں�ָ���ַ������ڵ��������
	 */
	public static long getIntevalDaysToNow(String startDate) {
		try {
			// ��ǰʱ��
			Date currentDate = new Date();
			// ָ������
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date theDate = myFormatter.parse(startDate);
			// ����ʱ��֮�������
			long days = (currentDate.getTime() - theDate.getTime())/ (24 * 60 * 60 * 1000);
			return days;
		} catch (Exception ee) {
			return 0l;
		}
	}
	/*
	 * �õ���ǰ����
	 */
	public static String getCurrentDate(String parttern) {
		try {
			Date currentDate = new Date();// ��ǰʱ��
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
		Date date = new Date();// ��ǰʱ��
		System.out.println(DateUtils.getCurrDateStr(YYYYMMDD));
	}
}
