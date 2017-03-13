/*
 * @(#)StringUtils.java     V1.0.0      @2014-6-18
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;


/**
 * 字符串显示工具类
 * 
 * @author chenyong
 */
public final class StringUtils {

	/**
	 * 判断空白
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() < 1);
	}
	
	/**
	 * 判断非空白
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str) ;
	}

	/**
	 * 判断数字为空或0
	 * @param number
	 * @return boolean
	 */
	public static boolean isBlank(Number number) {
		return (number == null || number.intValue() == 0);
	}
	

	/**
	 * 字符串连接方法
	 * @param s1
	 * @param s2
	 * @return String
	 */
	public static String mergeStrings(String s1, String s2) {
		if (s1 == null) {
			return s2;
		} else if (s2 == null) {
			return s1;
		} else {
			return s1.concat(s2);
		}
	}

	/**
	 * 获取参数的字符表示形式
	 * 
	 * @param o
	 * @return String
	 */
	public static String getString(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof Date) {
			Date new_name = (Date) o;
			return DateUtils.dateToString(new_name);
		}
		return String.valueOf(o);
	}

	/**
	 * 去null的字符串形式
	 * @param o
	 * @return String
	 */
	public static String getNullString(Object o) {
		return org.apache.commons.lang3.StringUtils.trimToNull(getString(o));
	}
	
	/**
	 * 去null的字符串形式
	 * @param o
	 * @return String
	 */
	public static String getStringTrimNotNull(Object o) {
		return o == null?"":o.toString().trim();
	}

	/**
	 * 获取字符所表示的Double类型的值
	 * @param o
	 * @return Double
	 */
	public static Double getDouble(String o) {
		if (isBlank(o)) {
			return Double.valueOf(0.0);
		} else {
			return NumberUtils.createDouble(o);
		}
	}

	/**
	 * 获取字符所表示的Long类型的值
	 * 
	 * @param o
	 * @return Long
	 */
	public static Long getLong(String o) {
		if (!isBlank(o)) {
			try {
				return NumberUtils.createLong(o);
			} catch (NumberFormatException e) {
				// ignore it;
			}
		}
		return Long.valueOf("0");
	}

	/**
	 * 获取对象的.toString表示形式
	 * @param o
	 * @return String
	 */
	public static String exportString(Object o) {
		if (o == null) {
			return "";
		}
		return StringUtils.getString(o);
	}

	/**
	 * 获取字符串长度
	 * 
	 * @param s
	 * @return int
	 */
	public static int getLength(String s) {
		if (isBlank(s)) {
			return 0;
		}
		return s.length();
	}


	/**
	 * 连接字符串数组每个元素，以‘，’分割
	 * @param array
	 * @return String
	 */
	public static String mergeStringArrays(String[] array) {
		if (array == null || array.length == 0) {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		} else {
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				result.append(array[i] + ",");
			}
			return result.toString();
		}
	}

	/**
	 * 返回字符串的后range位
	 * @param range
	 * @param target
	 * @return String
	 */
	public static String getStringInRange(int range, String target) {
		if (target == null || target.length() < range) {
			return target;
		} else {
			return target.substring(target.length() - range, target.length());
		}
	}
	
	/**
	 * 用于截获YYYYMMDDHH24MISS格式的前面八个字符，用于设定手机票信息，手机票信息的日期为四个字节
	 * @param src
	 * @return String
	 */
	public static String getEightSubStr(String src) {
		if(src==null) return "00000000";
		String rst;
		if(src.length()>=8){
			rst = src.substring(0, 8);
		}else{
			String temp="";
			for(int i=0;i<8-src.length();i++)temp+="0";
			rst = src+temp;
		}
		return rst;
	}
	
	/**
	 * 字符串去NULL
	 * @param string
	 * @return String
	 */
	public static String convertString(String string) {
		
		return string == null ? "" : string;
	}
	
	/**
	 * 将对象以toString()形式输出
	 */
	public static <T> String objToString(T obj){
		String str = "";
		try{
			Class<? extends Object> cla = null;
			Field[] fdAry = null;
			Method[] mdAry = null;
			for(cla =obj.getClass() ; cla != Object.class ;cla = cla.getSuperclass()){
				fdAry = cla.getDeclaredFields();
				mdAry = cla.getDeclaredMethods();
				mdAry[0].getName();
				for(int i=0;i<fdAry.length ; i++){
					String fdName = fdAry[i].getName();
					char[] cs= fdName.toCharArray();
				    cs[0]-=32;
				    String mdName = "get"+String.valueOf(cs);
				    for(Method mn :mdAry){
			      	if(mn.getName().equals(mdName)){
			       		str += fdName + ":" + mn.invoke(obj)+"  ";
			       	}
			      }
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
