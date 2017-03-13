/*
 * @(#)SerNoUtil.java     V1.0.0      @2014-6-18
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
import com.unicompayment.fip.common.utils.date.DateUtil;
/**
 * 流水号文本Util类
 *
 * @author chenyong
 */
public class SerNoUtil {

	/**
     * @return String
     * 20位随机流水号生成方法
     */
	public static final String getSerNo20(){
		String times = System.currentTimeMillis()+"";
		int scale = 20 - times.length();
		return times + MathUtil.randomNumber(scale);
	}
	/**
	 * 
	 * @return String 20位流水号(14位日期+6位随机数)
	 */
	public static final String getSeqNo20()
	{
		String time = DateUtil.getCurrentDate14();
		int scale = 20 - time.length();
		return time + MathUtil.randomNumber(scale);
	}
	/**
     * @return String
     * 18位随机流水号生成方法
     */
	public static final String getSerNo18(){
		String times = System.currentTimeMillis()+"";
		int scale = 18 - times.length();
		return times + MathUtil.randomNumber(scale);
	}
	/**
	 * 
	 */
	public static final String getSerNo16()
	{
		String times = System.currentTimeMillis()+"";
		int scale = 16 - times.length();
		return times + MathUtil.randomNumber(scale);
	}
	/**
     * @return String
     * 12位随机流水号生成方法
     */
	public static final String getSerNo12(){
		long initTime = DateUtils.getCurrent_initTime();
		long sc = System.currentTimeMillis() - initTime;
		String times = ("0000000"+Long.toHexString(sc));
		times = times.substring(times.length()-7);
		return times + MathUtil.randomNumber(5);
	}
	/**
     * @return String
     * 32位随机流水号生成方法
     */
	public static final String getSerNo32(){ 
		return DateUtils.getCurrentDateTimeString14() + getSerNo18();
	}
	public static final String getSerNo30()
	{
		return DateUtils.getCurrentDateTimeString14() + getSerNo16();
	}
}
