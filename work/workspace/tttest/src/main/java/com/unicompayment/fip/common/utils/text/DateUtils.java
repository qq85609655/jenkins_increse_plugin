/*
 * @(#)DateUtils.java     V1.0.0      @2014-6-18
 *
 * Project:unpcommon
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    chenyong       2014-6-18     Create this file
 * 
 * Copyright Notice:
 *     Copyright (c) 2009-2014 Unicompay Co., Ltd. 
 *     1002 Room, No. 133 North Street, Xi Dan, 
 *     Xicheng District, Beijing ,100032, China 
 *     All rights reserved.
 *
 *     This software is the confidential and proprietary information of
 *     Unicompay Co., Ltd. ("Confidential Information").
 *     You shall not disclose such Confidential Information and shall use 
 *     it only in accordance with the terms of the license agreement you 
 *     entered into with Unicompay.
 */
package com.unicompayment.fip.common.utils.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.regex.Pattern;

/**
 * 日期显示Util类
 *
 * @author chenyong
 */
public class DateUtils {
	
	
    private static String defaultDatePattern = "yyyyMMdd";
    
	public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

	private static SimpleDateFormat lenientDateFormat = new SimpleDateFormat(defaultDatePattern);
	
	public static final String suffix0 = "000000";
	
	public static final String suffix23 = "235959";
	
    static {
        //尝试试从messages_zh_Cn.properties中获取defaultDatePattner.
        try {
            //Locale locale = LocaleContextHolder.getLocale();
            //defaultDatePattern = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_KEY, locale).getString("date.default_format");
        }
        catch (MissingResourceException mse) {
            //do nothing
        }
    }
    
    
    /**
     * 
     * 日期格式转换
     * @param srcDate 原日期字符串
     * @param srcPattern 原日期格式
     * @param destPattern 目标日期格式
     * @return 目标日期
     */
     
    public static String transformDate(String srcDate,String srcPattern,String destPattern){
    	if(srcDate==null){
    		return "";
    	}
    	if(StringUtils.isBlank(srcPattern)){
    		return "";
    	}
    	if(StringUtils.isBlank(destPattern)){
    		destPattern = "yyyymmdd";
    	}
    	
    	String destDateStr = "";
    	SimpleDateFormat sf1 = new SimpleDateFormat(srcPattern);
    	SimpleDateFormat sf2 = new SimpleDateFormat(destPattern);
    	try {
			Date date = sf1.parse(srcDate);
			destDateStr = sf2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return destDateStr;
    }
    
    /**
     * 得到当前时间
     * @return
     */
    public static String getCurrTime(){
    	return new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
    /**
     * 得到当前时间6位
     * @return
     */
    public static String getCurrTimeString6(){
    	return new SimpleDateFormat("HHmmss").format(new Date());
    }
    
    /**
     * 获得默认的 date pattern
     * @return String
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 返回预设Format的当前日期字符串
     * @return String
     */
    public static String getToday() {
        return format(now());
    }
    
    /**
     * 返回当前时间
     * @return Date实例
     */
    public static Date now() {
    	return nowCal().getTime();
    }
    
    /**
     * 当前时间
     * @return Calendar实例
     */
    public static Calendar nowCal() {
    	return Calendar.getInstance();
    }
    
    /**
     * Date型转化到Calendar型
     * @param date
     * @return Calendar
     */
    public static Calendar date2Cal(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c;
    }
    
    /**
     * 当前时间的下一天
     * @return Calendar
     */
    public static Calendar nextDay() {
    	return nextDay(nowCal());
    }
    
    /**
     * 当前时间的下一月
     * @return Calendar
     */
    public static Calendar nextMonth() {
    	return nextMonth(nowCal());
    }
    
    /**
     * 当前时间的下一年
     * @return Calendar
     */
    public static Calendar nextYear() {
    	return nextMonth(nowCal());
    }
    
    /**
     * 下一天
     * @param cal
     * @return Calendar
     */
    public static Calendar nextDay(Calendar cal) {
    	if (cal == null) {
    		return null;
    	}
    	return afterDays(cal, 1);
    }
    
    /**
     * 下一月
     * @param cal
     * @return Calendar
     */
    public static Calendar nextMonth(Calendar cal) {
    	if (cal == null) {
    		return null;
    	}
    	return afterMonths(cal, 1);
    }
    
    /**
     * 下一年
     * @param cal
     * @return Calendar
     */
    public static Calendar nextYear(Calendar cal) {
    	if (cal == null) {
    		return null;
    	}
    	return afterYesrs(cal, 1);
    }
    
    /**
     * 后n天
     * @param cal
     * @param n
     * @return Calendar
     */
    public static Calendar afterDays(Calendar cal, int n) {
    	if (cal == null) {
    		return null;
    	}
    	Calendar c = (Calendar) cal.clone();
    	c.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + n);
    	return c;
    }
    
    /**
     * 后n月
     * @param cal
     * @param n 
     * @return Calendar
     */
    public static Calendar afterMonths(Calendar cal, int n) {
    	if (cal == null) {
    		return null;
    	}
    	Calendar c = (Calendar) cal.clone();
    	c.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
    	return c;
    }
    
    /**
     * 后n年
     * @param cal
     * @param n
     * @return Calendar
     */
    public static Calendar afterYesrs(Calendar cal, int n) {
    	if (cal == null) {
    		return null;
    	}
    	Calendar c = (Calendar) cal.clone();
    	c.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
    	return c;
    }
    
    /**
     * 使用预设Format格式化Date成字符串
     * @return String
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     * @return String
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 试用参数Format格式化Calendar成字符串
     * @param cal
     * @param pattern
     * @return String
     */
    public static String format(Calendar cal ,String pattern){
    	return cal == null ? "" : new SimpleDateFormat(pattern).format(cal.getTime());
    }
    
    

    /**
     * 使用预设格式将字符串转为Date
     * @return Date
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
    }

    /**
     * 使用参数Format将字符串转为Date
     * @return Date
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
    }

    /**
     * 在日期上增加数个整月
     * @return Date
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    
	/**
	 * get String value(MM/dd/yyyy) of time 
	 * 
	 * @param d
	 * @return String
	 */
	public static String dateToString(Date d) {
		if (d == null) {
			return null;
		}
		return lenientDateFormat.format(d);
	}
	
	public static String formatDateStrNoSplit(String dateStr,String split){
		if(dateStr==null)return null;
		String result = "";
		String[] temp = dateStr.split(split);
		for(int i=0;i<temp.length;i++){
			result+=temp[i];
		}
		return result;
	}

	/**
	 * 得到当前日期
	 * @return
	 */
	public static String getDateStr(){
		String temp_str="";
	    Date dt = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    temp_str=sdf.format(dt);
	    return temp_str;
	}
	
	/**判断是否符合日期格式YyyyMMdd Yyyy-MM-dd*/
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }
    /**判断是否符合日期格式YyyyMMdd Yyyy-MM-dd*/
    public static boolean isTime(String date) {
    	StringBuffer reg = new StringBuffer(
    	"/^([0-1]\\d|2[0-3])[0-5]\\d[0-5]\\d$/");
    	Pattern p = Pattern.compile(reg.toString());
    	return p.matcher(date).matches();
    }
    
    /**
	 * 获得默认格式的日期时间字符串
	 * @param date 日期时间。如果这个参数为null，系统会自动生成当前的日期时间并返回
	 * @return 字符串形式显示的日期时间
	 */
	public static String getDefaultString(Date date){
		if(date == null)
			date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");    
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	public static Date getDateTimeFromString(String str){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(str);
			return date;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getStringFromDateTime(Date date){
		try{
			return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getStringFromDate(Date date){
		try{
			return new SimpleDateFormat("yyyyMMdd").format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得邮件发送日期时间字符串
	 * @param date 发送日期时间。如果这个参数为null，系统会自动生成当前的日期时间并返回
	 * @return 字符串形式显示的日期时间
	 */
	public static String getMailSendingDateTime(Date date){
		return getDefaultString(date); 
	}
	
	/**
	 * 获得拍卖物品上架日期时间字符串
	 * @param date 发送日期时间。如果这个参数为null，系统会自动生成当前的日期时间并返回
	 * @return 字符串形式显示的日期时间
	 */
	public static String getGoodsOnSaleTime(Date date){
		return getDefaultString(date);
	}
	
	/**
	 * 获得拍卖物品拍卖截止日期时间字符串
	 * @param date 发送日期时间。如果这个参数为null，系统会自动生成当前的日期时间并返回
	 * @return 字符串形式显示的日期时间
	 */
	public static String getGoodsEndTime(Date date){
		return getDefaultString(date);
	}
//	public static void main(String...strings){
//		System.out.println(DateStringUtil.getMailSendingDateTime(null));
//	}
	
	/**
	 * 根据格式获取格式化后时间显示
	 * @param date 日期时间。formation格式
	 * @return 字符串形式显示的日期时间
	 */
	public static String formatDateByFormation(Date date,String formation){
		try{
			return new SimpleDateFormat(formation).format(date);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * 根据格式将字符串转成Date类型
	 * @param source 日期时间字符串。formation格式
	 * @return 日期时间
	 */
	public static Date parseDateByFormation(String source,String formation){
		try{
			return new SimpleDateFormat(formation).parse(source);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public static long getCurrent_initTime() {
		return getDate_initTime(new Date(System.currentTimeMillis()));
	}
	
	public static long getDate_initTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static int getNextMonth(){
		Calendar clnd = Calendar.getInstance();
		int curMonth = clnd.get(Calendar.MONTH);
		if(curMonth >= 11)
			return 0;
		else
			return ++curMonth;
	}
	
	/**
	 * 判断两个日期是否处于同一年中的同一周
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 如果两个日期处于同一年的同一周，返回{@code true}，反之为{@code false}
	 */
	public static boolean inSameWeek(Date date1, Date date2){
		if(date1 == null || date2 == null)
			return false;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		int weekOfYear1 = c1.get(Calendar.WEEK_OF_YEAR);
		int weekOfYear2 = c2.get(Calendar.WEEK_OF_YEAR);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		if(year1 == year2 && weekOfYear1 == weekOfYear2)
			return true;
		return false;
	}
	
	/**
	 * 查询当前日期是星期几
	 * @param dateStr 8位日期
	 * @return 星期几     0-星期日，1星期1，2星期2，3星期3，4星期4，5星期5，6星期6
	 */
	public static int getDayOfWeek(String dateStr){
		Calendar c = Calendar.getInstance();   	
	    c.setTime(parseDateByFormation(dateStr,"yyyyMMdd"));
	    return c.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static String getCurrentDateString8(){
		return getStringFromDate(new Date(System.currentTimeMillis()));
	}	
	
	public static String getCurrentDateTimeString14(){
		return getStringFromDateTime(new Date(System.currentTimeMillis()));
	}
	
	public static String formatTextDateTime(String dateStr){
		if(dateStr.length() == 8){
			return dateStr.substring(0,4)+"-"+dateStr.substring(4,6)+"-"+dateStr.substring(6,8);
		}else if(dateStr.length() == 14){
			return dateStr.substring(0,4)+"-"+dateStr.substring(4,6)+"-"+dateStr.substring(6,8)
					+" "+dateStr.substring(8,10)+":"+dateStr.substring(10,12)+":"+dateStr.substring(12);
		}else{
			return dateStr;
		}
	}
	
	public static String getBeforeDay(String date8,int beforeDays) throws ParseException{
		Calendar c = Calendar.getInstance();   		
    	c.setTime(new SimpleDateFormat("yyyyMMdd").parse(date8));
    	c.add(Calendar.DAY_OF_MONTH, 0-beforeDays);
    	Date bday =c.getTime();
    	return getStringFromDate(bday);
	}
	
    public static void main(String[]args)throws Exception{
    	String date = "20110707";
	    String d= transformDate(date, "yyyymmdd", "yyyy/mm/dd");
	    Calendar c = Calendar.getInstance();   	
	    c.setTime(new Date());
	    Locale.setDefault(Locale.US);
    	System.out.println(Locale.getDefault() + "   " + c.get(Calendar.DAY_OF_WEEK));   	
    }
    /**
     * 日期后缀“000000”
     * @return
     */
	public static String getSuffix0() {
		return suffix0;
	}
	
	/**
     * 日期后缀“235959”
     * @return
     */
	public static String getSuffix23() {
		return suffix23;
	}
    
    
}